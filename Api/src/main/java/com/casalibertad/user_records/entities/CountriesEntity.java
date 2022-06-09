package com.casalibertad.user_records.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//@Data
//@Entity
//@Table(name = "casa_libertad_countries")
//public class CountriesEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int uniqid;
//    @Column(name = "contries")
//    private String countries;
//    @Column(name = "dial_code")
//    private String dial_code;
//    @Column(name = "code")
//    private String code;
//}

//@Data
//@Entity
//@Table(name = "casa_libertad_countries")
//public class CountriesEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int uniqid;
//    @Column(name = "countries")
//    private String countries;
//    @Column(name = "dial_code")
//    private String dial_code;
//    @Column(name = "code")
//    private String code;
//}
@Data
@Entity
@Table(name = "casa_libertad_pais")
public class CountriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uniqid;
    @Column(name = "countries")
    private String countrie;
//    @Column(name = "dial")
//    private String dial;
//    @Column(name = "codigo")
//    private String codigo;

}
