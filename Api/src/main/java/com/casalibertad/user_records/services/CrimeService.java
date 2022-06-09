package com.casalibertad.user_records.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.user_records.DTOS.CrimeDTO;
import com.casalibertad.user_records.DTOS.CrimesDTO;
import com.casalibertad.user_records.entities.CrimeEntity;
import com.casalibertad.user_records.enums.ErrorMessageEnum;
import com.casalibertad.user_records.exceptions.ConflictException;
import com.casalibertad.user_records.exceptions.NotFoundException;
import com.casalibertad.user_records.loggin.ExceptionLoggin;
import com.casalibertad.user_records.repositories.CrimeRepository;


@Service
public class CrimeService {
	
	@Autowired
	private CrimeRepository crimeRepository;
	@Autowired
	private ExceptionLoggin exceptionLoggin;
	
	public CrimeEntity getCrimeEntityById(int uniqid) {
		CrimeEntity crimeEntity = crimeRepository.findByUniqid(uniqid);

		return crimeEntity;
	}

	public CrimeEntity getCrimeEntity(int id) {
		CrimeEntity crimeEntity = crimeRepository.findByUniqid(id);

		return crimeEntity;
	}
	
	public boolean valideCrimeExistence(List<Integer> newCrimes) throws NotFoundException{
		for(Integer newCrime : newCrimes) {
			if(newCrime != 0) {
				CrimeEntity crimeEntity = crimeRepository.findByUniqid(newCrime);

				if(crimeEntity == null) {
					String cause = String.format("There is not a Crime with id %d", newCrime);
					String id = exceptionLoggin.getUUID();
					String message = exceptionLoggin.buildMessage(ErrorMessageEnum.NotFoundException, id, cause
							,this.getClass().toString());
					exceptionLoggin.saveLog(message, id);
					throw new NotFoundException(message);

				}
			}
		}

		return true;
	}

	public boolean valideCrimeInexistence(List<String> newCrimes) throws ConflictException{
		for(String newCrime : newCrimes) {
			CrimeEntity crimeEntity = crimeRepository.findByCrimeIgnoreCase(newCrime);

			if(crimeEntity != null) {
				String cause = String.format("There is already a Crime named %s and it's id is %d"
						, newCrime, crimeEntity.getUniqid());
				String id = exceptionLoggin.getUUID();
				String message = exceptionLoggin.buildMessage(ErrorMessageEnum.ConflictException, id, cause
						,this.getClass().toString());
				exceptionLoggin.saveLog(message, id);
				throw new ConflictException(message);

			}
		}
		return true;
	}

	public List<Integer> createCrimes(List<String> crimes) {
		List<Integer> newCrimesIds = new ArrayList<Integer>();
		for(String crime : crimes) {
			CrimeEntity newCrime = new CrimeEntity();
			newCrime.setCrime(crime);
			
			crimeRepository.save(newCrime);
			newCrimesIds.add(newCrime.getUniqid());
		}
		return newCrimesIds;
	}
	
	public List<CrimeEntity> getCrimes(){
		System.out.println("6");

		System.out.println(" crimes :::: -> "+crimeRepository.findAll());
		return crimeRepository.findAll();
	}
	public CrimesDTO getCrimesDTO() {
		System.out.println("7");

		CrimesDTO crimesDTO = new CrimesDTO();
		System.out.println("-->  --> "+getCrimes());
		crimesDTO.setCrimes(mapToCrimesDTO(getCrimes()));
		return crimesDTO;
	}
	
	public List<CrimeDTO> mapToCrimesDTO(List<CrimeEntity> crimes){
		System.out.println("8");

		List<CrimeDTO> crimesDTO = new ArrayList<CrimeDTO>();
		
		if(crimes.size() > 0) {
			for(CrimeEntity crime : crimes) {
//				System.out.println(crime.getCrime());

				CrimeDTO crimeDTO = new CrimeDTO();
				crimeDTO.setUniqid(crime.getUniqid());
				crimeDTO.setCrime(crime.getCrime());
				crimesDTO.add(crimeDTO);
//				System.out.println(crime.getCrime());
			}
		}

		return crimesDTO;
	}
}
