package THJava.Ngay2.Books.Controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import THJava.Ngay2.Books.Models.User;
import THJava.Ngay2.Books.Services.UserService;
import THJava.Ngay2.Books.Repositories.UserRepository;
import THJava.Ngay2.Books.Utils.FileUploadUtil;
import THJava.Ngay2.Books.Utils.Utilities;
import net.bytebuddy.utility.RandomString;
import THJava.Ngay2.Books.Services.RoleService;
import THJava.Ngay2.Books.Services.SendmailService;

@Controller
@RequestMapping("/users")
@ComponentScan("THJava.Ngay2.Books")
public class UserController {
	@Autowired
	private SendmailService sendmailService;
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public String viewAllUser(Model model) {
		List<User> listUser = userService.listAll();
		model.addAttribute("users", listUser);
		return "user/index";
	}

	@GetMapping("/new")
	public String showNewUserPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("roles", roleService.listAll());
		return "user/new_user";
	}

	@PostMapping("/save")
	public String saveUser(@ModelAttribute("user") User user, @RequestParam("image") MultipartFile multipartFile)
			throws IOException {

		if (user.getPassword().isEmpty()) {
			user.setPassword(userService.get(user.getId()).getPassword());
		}else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		if (!multipartFile.isEmpty()) {
			user.setphotourl(fileName);
		}else {
			user.setphotourl(userService.get(user.getId()).getphotourl());
		}
		user.setEnabled(true);
		User savedUser = userService.save(user);
		if (!multipartFile.getOriginalFilename().isBlank()) {
			String uploadDir = "photos/" + savedUser.getId();
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}	

		return "redirect:/users";
	}

	@GetMapping("/edit/{id}")
	public String showEditUserPage(@PathVariable("id") Long id, Model model) {
		User user = userService.get(id);

		if (user == null) {
			return "notfound";

		} else {
			model.addAttribute("roles", roleService.listAll());
			model.addAttribute("user", user);
			return "user/edit";
		}
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		User user = userService.get(id);
		if (user == null) {
			return "notfound";
		} else {
			userService.delete(id);
			return "redirect:/users";
		}
	}
	
	@GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }
	@PostMapping("/register")
	public String processRegister(User user ,HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
		String encodePaddword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePaddword);
		user.addRoles(roleService.getbyName("USER"));
		user.setVerificationCode(RandomString.make(30));
		userService.save(user);
		sendmailService.sendVerificationEmail(user, Utilities.getSiteURL(request));

        return "redirect:/login";
    }


}
