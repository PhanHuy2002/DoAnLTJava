package THJava.Ngay2.Books.Services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import THJava.Ngay2.Books.Models.CustomUserDetails;
import THJava.Ngay2.Books.Models.User;
import THJava.Ngay2.Books.Repositories.UserRepository;
import THJava.Ngay2.Books.Services.UserService;
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.getUserByUsername(username);
		if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new CustomUserDetails(user);
	}
//	public Page<User> findpaginated(int pageNo,int pageSize){
//		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//		return this.userRepository.findAll(pageable);
//	}
}
