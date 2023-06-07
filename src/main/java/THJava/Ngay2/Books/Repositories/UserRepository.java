package THJava.Ngay2.Books.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import THJava.Ngay2.Books.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.username = :username")
	public User getUserByUsername(@Param("username") String username);
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User getUserByEmail(@Param("email")String email);
	@Query("SELECT u FROM User u WHERE u.tokenforgotpassword = :token")
    public User getUserBytokenforgotpassword(String token);
}
