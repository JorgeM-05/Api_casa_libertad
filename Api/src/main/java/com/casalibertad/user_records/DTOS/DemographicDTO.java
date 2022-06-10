package com.casalibertad.user_records.DTOS;

import lombok.Data;

import java.util.Date;

@Data
public class DemographicDTO {
    public int user_uniqid;
    public Date birthDate;
    public String nationality;
    public String country;
    public String maritalStatus;
    public String ethnicity;
    public String ethnicityOther;
    public String sex;
    public String gender_Identity;
    public String sexual_Orientation;
    public String disability;
    public String mobilityHelp;
    public String readingLiteracyHelp;
    public String translation_Help;
    public String victim_Armed_Conflict;
}
