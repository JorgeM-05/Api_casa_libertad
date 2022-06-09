package com.casalibertad.user_records.services;

import com.casalibertad.user_records.DTOS.DemographicDTO;
import com.casalibertad.user_records.DTOS.NewDemographicDTO;
import com.casalibertad.user_records.DTOS.UpdateDemographicDTO;
import com.casalibertad.user_records.DTOS.UpdatedUserRecordsDTO;
import com.casalibertad.user_records.entities.*;
import com.casalibertad.user_records.enums.ErrorMessageEnum;
import com.casalibertad.user_records.exceptions.ConflictException;
import com.casalibertad.user_records.exceptions.NotFoundException;
import com.casalibertad.user_records.loggin.ExceptionLoggin;
import com.casalibertad.user_records.repositories.UserDemographicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

@Service
public class DemographicService {
    Logger logger = LoggerFactory.getLogger(DemographicService.class);

    @Autowired
    private UserDemographicRepository userDemographicRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private ExceptionLoggin exceptionLoggin;


    // *** POST data user to demographic ***
    public DemographicDTO createDemographicUser(NewDemographicDTO newDemographic)
           throws NotFoundException, ConflictException, ParseException {
       return mapToUserDemographicDTO(createUserDemographic(newDemographic));
     }

    public UserDemographicEntity createUserDemographic(NewDemographicDTO demographicDTO) throws NotFoundException, ConflictException, ParseException {
        logger.info("******  >>>> " + demographicDTO);

        UserDemographicEntity userDemographicEntity = null;

        if(isValidNewUserReceptionRecord(demographicDTO)) {

            userDemographicEntity = new UserDemographicEntity();

            UserEntity userEntity = userService.getUserEntity(demographicDTO.getUser_uniqid());

            userDemographicEntity.setUser(userEntity);
            userDemographicEntity.setBirthDate(DateFormat.getDateInstance().parse(demographicDTO.getBirthDate()));
            userDemographicEntity.setNationality(demographicDTO.getNationality());
            userDemographicEntity.setCountry(demographicDTO.getCountry());
            userDemographicEntity.setMaritalStatus(demographicDTO.getMaritalStatus());
            userDemographicEntity.setEthnicity(demographicDTO.getEthnicity());
            userDemographicEntity.setEthnicityOther(demographicDTO.getEthnicityOther());
            userDemographicEntity.setSex(demographicDTO.getSex());
            userDemographicEntity.setGender_Identity(demographicDTO.getGender_Identity());
            userDemographicEntity.setSexual_Orientation(demographicDTO.getSexual_Orientation());
            userDemographicEntity.setDisability(demographicDTO.getDisability());
            userDemographicEntity.setMobilityHelp(demographicDTO.getMobilityHelp());
            userDemographicEntity.setReadingLiteracyHelp(demographicDTO.getReadingLiteracyHelp());
            userDemographicEntity.setTranslation_Help(demographicDTO.getTranslation_Help());
            userDemographicEntity.setVictim_Armed_Conflict(demographicDTO.getVictim_Armed_Conflict());

            userDemographicRepository.save(userDemographicEntity);

            System.out.println("userEntity.getUniqid() ::: --> " + userEntity.getUniqid());

        }
        return userDemographicEntity;
    }

    public boolean isValidNewUserReceptionRecord(NewDemographicDTO newDemographicDTO)
            throws NotFoundException, ConflictException {
        boolean isValid = true;

        System.out.println("newUniq :: " + newDemographicDTO.getUser_uniqid());
        UserEntity user_Entity = userService.getUserEntity(newDemographicDTO.getUser_uniqid());
        logger.info("----->> :: " + user_Entity);
        System.out.println(user_Entity);
        UserDemographicEntity userDemographicEntity = userDemographicRepository.findByUser(user_Entity);

        logger.info(">> userRecep..Entity encontrado  :: " +  userDemographicEntity);

        if(userDemographicEntity != null) {
            String cause = String.format("There is already a User Reception Record for user whit id %d", newDemographicDTO.getUser_uniqid());
            String id = exceptionLoggin.getUUID();
            String message = exceptionLoggin.buildMessage(ErrorMessageEnum.ConflictException, id, cause,this.getClass().toString());
            exceptionLoggin.saveLog(message, id);

            throw new ConflictException(message);
        }
        return isValid;
    }

    public DemographicDTO mapToUserDemographicDTO(UserDemographicEntity userDemographicEntity) {
        logger.info("****************** " +  userDemographicEntity);
        DemographicDTO demographicDTO = new DemographicDTO();

        if(userDemographicEntity != null) {
            demographicDTO.setUser_uniqid(userDemographicEntity.getUniqid());
            demographicDTO.setBirthDate(userDemographicEntity.getBirthDate());
            demographicDTO.setCountry(userDemographicEntity.getCountry());
            demographicDTO.setNationality(userDemographicEntity.getNationality());
            demographicDTO.setMaritalStatus(userDemographicEntity.getMaritalStatus());
            demographicDTO.setEthnicity(userDemographicEntity.getEthnicity());
            demographicDTO.setEthnicityOther(userDemographicEntity.getEthnicityOther());
            demographicDTO.setSex(userDemographicEntity.getSex());
            demographicDTO.setGender_Identity(userDemographicEntity.getGender_Identity());
            demographicDTO.setSexual_Orientation(userDemographicEntity.getSexual_Orientation());
            demographicDTO.setDisability(userDemographicEntity.getDisability());
            demographicDTO.setMobilityHelp(userDemographicEntity.getMobilityHelp());
            demographicDTO.setReadingLiteracyHelp(userDemographicEntity.getReadingLiteracyHelp());
            demographicDTO.setTranslation_Help(userDemographicEntity.getTranslation_Help());
            demographicDTO.setVictim_Armed_Conflict(userDemographicEntity.getVictim_Armed_Conflict());
        }

        return demographicDTO;
    }



    // *** Get data user to demographic ***
    public DemographicDTO getDataDemographicUser(int user_id) throws NotFoundException{
        logger.info("user :: " + user_id);
        UserEntity userEntity = userService.getUserEntity(user_id);
        logger.info("userEntity ::  "+userEntity);
        return mapToUserDemographicDTO(userDemographicRepository.findByUser(userEntity));
    }


    // *** UPDATE data user to demographic ***

    public DemographicDTO updateDataDemographic(int user_id, UpdateDemographicDTO updateDemographicDTO) throws NotFoundException, ConflictException, ParseException{
        System.out.println("\n " + "user id : " + user_id);
        System.out.println("\n " + updateDemographicDTO);
        return mapToUserDemographicDTO(updateDemographic(user_id,updateDemographicDTO));
    }

    public UserDemographicEntity updateDemographic(int userId, UpdateDemographicDTO updateDemographicDTO) throws NotFoundException, ConflictException, ParseException{

        UserEntity userEntity = userService.getUserEntity(userId);

        UserDemographicEntity userDemographicEntity = userDemographicRepository.findByUser(userEntity);
        System.out.println("\n  entity: " + userDemographicEntity );
        if(userDemographicEntity == null){
            String cause = String.format("There is not a User demographic Record for user whit id %d", userId);
            String id = exceptionLoggin.getUUID();
            String message = exceptionLoggin.buildMessage(ErrorMessageEnum.NotFoundException, id, cause
                    ,this.getClass().toString());
            exceptionLoggin.saveLog(message, id);

            throw new NotFoundException(message);
        }

        System.out.println("ok");
        userDemographicEntity.setUser(userEntity);
        userDemographicEntity.setBirthDate(DateFormat.getDateInstance().parse(updateDemographicDTO.getBirthDate()));
        userDemographicEntity.setNationality(updateDemographicDTO.getNationality());
        userDemographicEntity.setCountry(updateDemographicDTO.getCountry());
        userDemographicEntity.setMaritalStatus(updateDemographicDTO.getMaritalStatus());
        userDemographicEntity.setEthnicity(updateDemographicDTO.getEthnicity());
        userDemographicEntity.setEthnicityOther(updateDemographicDTO.getEthnicityOther());
        userDemographicEntity.setSex(updateDemographicDTO.getSex());
        userDemographicEntity.setGender_Identity(updateDemographicDTO.getGender_Identity());
        userDemographicEntity.setSexual_Orientation(updateDemographicDTO.getSexual_Orientation());
        userDemographicEntity.setDisability(updateDemographicDTO.getDisability());
        userDemographicEntity.setMobilityHelp(updateDemographicDTO.getMobilityHelp());
        userDemographicEntity.setReadingLiteracyHelp(updateDemographicDTO.getReadingLiteracyHelp());
        userDemographicEntity.setTranslation_Help(updateDemographicDTO.getTranslation_Help());
        userDemographicEntity.setVictim_Armed_Conflict(updateDemographicDTO.getVictim_Armed_Conflict());

        userDemographicRepository.save(userDemographicEntity);

        return userDemographicEntity;
    }

}