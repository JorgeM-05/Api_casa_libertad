package com.casalibertad.user_records.entities;

import lombok.Data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name="casa_libertad_demographic")
public class UserDemographicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uniqid;

    @ManyToOne
    @JoinColumn(name = "user_uniqid")
    private UserEntity user;

    @JoinColumn(name = "birth_date")
    public Date birthDate;

    @JoinColumn(name = "nationality")
    public String nationality;

    @JoinColumn(name = "country")
    public String country;

    @JoinColumn(name = "marital_status")
    public String maritalStatus;

    @JoinColumn(name = "ethnicity")
    public String ethnicity;

    @JoinColumn(name = "ethnicity_other")
    public String ethnicityOther;

    @JoinColumn(name = "sex")
    public String sex;

    @JoinColumn(name = "gender_identity")
    public String gender_Identity;

    @JoinColumn(name = "sexual_orientation")
    public String sexual_Orientation;

    @JoinColumn(name = "disability")
    public String disability;

    @JoinColumn(name = "mobility_help")
    public String mobilityHelp;

    @JoinColumn(name = "reading_literacy_help")
    public String readingLiteracyHelp;

    @JoinColumn(name = "translation_Help")
    public String Translation_Help;

    @JoinColumn(name = "victim_armed_conflict")
    public String Victim_Armed_Conflict;
}
