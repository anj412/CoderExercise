package ru.croc.coder.repository;

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

	@HandleBeforeSave
	@HandleBeforeCreate
	public void handleUserBeforeSave(User user) {
		System.out.println("!!!:" + user);
		user.setPassword("hash:" + user.getPassword().hashCode() );
	}
	
	@HandleAfterSave
	@HandleAfterCreate
	public void handleUserAfterSave(User user) {
		System.out.println("!!!:" + user);
		//user.setPassword("hash" + user.getPassword().hashCode() );		
	}

}
