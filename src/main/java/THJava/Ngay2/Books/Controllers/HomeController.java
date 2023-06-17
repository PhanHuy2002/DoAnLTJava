package THJava.Ngay2.Books.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import THJava.Ngay2.Books.Models.Book;
import THJava.Ngay2.Books.Services.BookServices;

@Controller
public class HomeController {
	@Autowired
	private BookServices bookServices;
	@GetMapping("")
	public String Home(Model model) {
		List<Book> listBook = bookServices.listAllWithOutDelete();
		model.addAttribute("books", listBook);
		return "home/index";
	}

}
