package com.casalibertad.user_records.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.user_records.entities.UserEntity;
import com.casalibertad.user_records.enums.ErrorMessageEnum;
import com.casalibertad.user_records.exceptions.NotFoundException;
import com.casalibertad.user_records.loggin.ExceptionLoggin;
import com.casalibertad.user_records.repositories.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ExceptionLoggin exceptionLoggin;
	Logger logger = LoggerFactory.getLogger(UserService.class);

	public UserEntity getUserEntity(int userId) throws NotFoundException {

		UserEntity userEntity = userRepository.findByUniqid(userId);

		if(userEntity == null) {
			String cause = String.format("Does not exist an user with userId %s", userId);
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.NotFoundException, id, cause
					,this.getClass().toString());
			exceptionLoggin.saveLog(message, id);
			
			throw new NotFoundException(message);
		}
			
		return userEntity;
	}
	
}
