package ru.croc.coder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

import ru.croc.coder.domain.User;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.service.exceptions.NotFoundException;

@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testFindByEmail() {
		Optional<User> user = userRepository.findByEmailIgnoreCase("episarenko@croc.ru");
		assertThat(user).isNotEmpty();
	}

		@Test
	public void testFindByEmail1() {
		User user = userRepository.findByEmailIgnoreCase("episarenko@croc.ru").orElseThrow(NotFoundException::new);
		assertEquals(user.getFirstName(), "Evgeny");
	}

	//Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(NotFoundException::new);


}
