package ru.croc.coder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.croc.coder.domain.User;
import ru.croc.coder.repository.UserRepository;

@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testFindByEmail() {
		Optional<User> user = userRepository.findПожалуйстаByEmailIgnoreCase("episarenko@croc.ru");
		assertThat(user).isNotEmpty();
	}
}
