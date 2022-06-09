package com.casalibertad.user_records.DTOS;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UserRecordsDTO {
	//
	private int user_uniqid;
	private CourtRecordDTO courtRecords;
	private Date freedom_date;
	private int months_sentence;
	private PrisonEstablishmentDTO prison_establishment;
	private String another_prison_establishment;
	private LegalStatusDTO legal_status;
	private int apprehended_teenager;
	private int apprehended_adult;
	private List<CrimeDTO> crimes;
	private Character actual_process;
}
