package com.casalibertad.user_records.services;

import com.casalibertad.user_records.DTOS.CountriesDTO;
import com.casalibertad.user_records.DTOS.CountrieDTO;
import com.casalibertad.user_records.DTOS.CrimesDTO;
import com.casalibertad.user_records.entities.CountriesEntity;
import com.casalibertad.user_records.entities.CrimeEntity;
import com.casalibertad.user_records.repositories.CountriesRepository;
import com.casalibertad.user_records.repositories.CrimeRepository;
import com.casalibertad.user_records.repositories.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoutriesService {
    Logger logger = LoggerFactory.getLogger(CoutriesService.class);

    @Autowired
    private CountriesRepository countriesRepository;
    @Autowired
    private Example example;

    public CountriesDTO getDataCountries(){
        CountriesDTO countriesDTO = new CountriesDTO();
        System.out.println("-->  --> "+getCountries());
        countriesDTO.setCountries(mapToCountriesDTO(getCountries()));
        return countriesDTO;
    }

    public List<CountriesEntity> getCountries(){
        logger.info("countries :::: --> "+countriesRepository.findAll());
        return countriesRepository.findAll();
    }

    public List<CountrieDTO> mapToCountriesDTO(List<CountriesEntity> countries){
        List<CountrieDTO> countriesDTOS = new ArrayList<CountrieDTO>();

        if(countries.size() > 0) {
            for(CountriesEntity country : countries) {
//                System.out.println("**** "+countries);
                CountrieDTO countriesDTO = new CountrieDTO();
                countriesDTO.setUniqid(country.getUniqid());
                countriesDTO.setNameContry(country.getCountrie());
                countriesDTOS.add(countriesDTO);
//                logger.info("--::> "+country.getCode());
            }
        }
//        logger.info("--> "+countriesDTOS);
        return countriesDTOS;
    }

}
