package com.casalibertad.user_records.controllers;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casalibertad.user_records.DTOS.NewUserRecordsDTO;
import com.casalibertad.user_records.DTOS.UpdatedUserRecordsDTO;
import com.casalibertad.user_records.DTOS.UserRecordsDTO;
import com.casalibertad.user_records.exceptions.ConflictException;
import com.casalibertad.user_records.exceptions.NotFoundException;
import com.casalibertad.user_records.services.UserReceptionRecordsService;


@RestController
@RequestMapping("records")
public class UserRecordController {
	Logger logger = LoggerFactory.getLogger(UserRecordController.class);

	@Autowired
	private UserReceptionRecordsService receptionRecordsService;

	@GetMapping("/{user_id}")
	public ResponseEntity<UserRecordsDTO> getUserRecordsDTO(@PathVariable int user_id) throws NotFoundException{
		logger.info("obteniendo informacion id :: " + user_id);
		UserRecordsDTO userRecordsDTO = receptionRecordsService.getUserReceptionRecordDTO(user_id);
		HttpStatus httpStatus = userRecordsDTO.getUser_uniqid() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		return new ResponseEntity<UserRecordsDTO>(userRecordsDTO, httpStatus);
	}

	@PostMapping
	public ResponseEntity<UserRecordsDTO> createUserRecordsDTO(@RequestBody NewUserRecordsDTO newUserRecordsDTO) throws NotFoundException, ConflictException, ParseException{
		logger.info("************** info :: " + newUserRecordsDTO);
		UserRecordsDTO userRecordsDTO = receptionRecordsService.createUserReceptionRecord(newUserRecordsDTO);
		return new ResponseEntity<UserRecordsDTO>(userRecordsDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{user_id}")
	public ResponseEntity<UserRecordsDTO> getUserRecordsDTO(@PathVariable int user_id,
			@RequestBody UpdatedUserRecordsDTO updatedUserRecordsDTO) throws NotFoundException, ConflictException, ParseException{
		UserRecordsDTO userRecordsDTO =
				receptionRecordsService.updateUserReceptionRecord(user_id,updatedUserRecordsDTO);
		return new ResponseEntity<UserRecordsDTO>(userRecordsDTO, HttpStatus.OK);
	}

}
