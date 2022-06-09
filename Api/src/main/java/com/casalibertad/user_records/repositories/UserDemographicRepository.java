package com.casalibertad.user_records.repositories;

import com.casalibertad.user_records.entities.UserDemographicEntity;
import com.casalibertad.user_records.entities.UserEntity;
import com.casalibertad.user_records.entities.UserReceptionRecordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDemographicRepository extends JpaRepository<UserDemographicEntity, Integer> {
	public UserDemographicEntity findByUser(UserEntity user);
}
