package com.casalibertad.user_records.controllers;

import com.casalibertad.user_records.DTOS.*;
import com.casalibertad.user_records.exceptions.ConflictException;
import com.casalibertad.user_records.exceptions.NotFoundException;
import com.casalibertad.user_records.services.CoutriesService;
import com.casalibertad.user_records.services.CrimeService;
import com.casalibertad.user_records.services.DemographicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;

@RestController
@RequestMapping("demographic")
public class DemographicController {
    Logger logger = LoggerFactory.getLogger(DemographicController.class);

    @Autowired
    private DemographicService demographicService;

    @Autowired
    private CoutriesService coutriesService;

    @Autowired
    CrimeService crimeService;

    @GetMapping("/country")
    public ResponseEntity<CountriesDTO> getDataCountry(){
        CountriesDTO countriesDTO = coutriesService.getDataCountries();
        ResponseEntity<CountriesDTO> responseEntity
                = new ResponseEntity<CountriesDTO>(countriesDTO, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<DemographicDTO> getDataDemographic(@PathVariable int user_id) throws NotFoundException{
        DemographicDTO demographicDTO = demographicService.getDataDemographicUser(user_id);
        HttpStatus httpStatus = demographicDTO.getUser_uniqid() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<DemographicDTO>(demographicDTO, httpStatus);
    }

    @PostMapping
    public ResponseEntity<DemographicDTO> createNewDataDemo(@RequestBody NewDemographicDTO newDemographic) throws NotFoundException, ConflictException, ParseException {
        logger.info("POST.....");
        DemographicDTO demographicDTO = demographicService.createDemographicUser(newDemographic);
        return new ResponseEntity<DemographicDTO>(demographicDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<DemographicDTO> getUserRecordsDTO(@PathVariable int user_id,
                                                            @RequestBody UpdateDemographicDTO updateDemographicDTO) throws NotFoundException, ConflictException, ParseException{
        DemographicDTO demographicDTO =  demographicService.updateDataDemographic(user_id,updateDemographicDTO);
        return new ResponseEntity<DemographicDTO>(demographicDTO, HttpStatus.OK);
    }
}