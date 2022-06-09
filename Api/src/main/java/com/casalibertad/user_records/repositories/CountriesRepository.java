package com.casalibertad.user_records.repositories;

import com.casalibertad.user_records.entities.CountriesEntity;
import com.casalibertad.user_records.entities.CrimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountriesRepository extends JpaRepository<CountriesEntity, Integer> {

}

