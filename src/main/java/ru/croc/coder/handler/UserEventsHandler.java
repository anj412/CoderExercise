package ru.croc.coder.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import ru.croc.coder.domain.User;

@Component
@RepositoryEventHandler
public class UserEventsHandler {
	private static final Logger log = LoggerFactory.getLogger(UserEventsHandler.class);

	@HandleBeforeSave
	@HandleBeforeCreate
	public void handleUserBeforeSave(User user) {
		log.info("!!!: userId {}", user.getId());
		//user.setFirstName(user.getFirstName() + "TestBefore");
		//user.setPassword("hash:" + user.getPassword().hashCode() );
	}
	
	@HandleAfterSave
	@HandleAfterCreate
	public void handleUserAfterSave(User user) {
		log.info("!!!: userId {}", user.getId());
		//user.setFirstName(user.getFirstName() + "TestAfter");
		//user.setPassword("hash" + user.getPassword().hashCode() );		
	}

}
