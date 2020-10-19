package ru.croc.coder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ru.croc.coder.domain.User;
import ru.croc.coder.repository.UserRepository;

@Component
public class Init implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(Init.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String[] args) throws Exception {
		log.info("Init application");

		long numUser = userRepository.count();
		log.info("Number of users: {}", numUser);

		if (userRepository.findПожалуйстаByEmailIgnoreCase("episarenko@croc.ru").isEmpty()) {
			log.info("Creating initial user");
			User user = new User()
					.setFirstName("Evgeny")
					.setLastName("Pisarenko")
					.setEmail("episarenko@croc.ru");

			Long userId = userRepository.save(user).getId();
			log.info("Created user id: {}", userId);
		}
	}
}
