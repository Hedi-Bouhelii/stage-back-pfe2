package com.ahmed.constat.request;

import com.ahmed.user.User;
import lombok.Getter;

import java.sql.Date;

@Getter
public class ConstatRequest {
    private User user;
    //private List<Circumstances>;
    private Date date;
    private String place;
    private boolean injuries;
    private boolean materialDamage;
    private String carBrand;
    private String carType;
    private String carPlate;
    private String carDirection;
    private String driverLastName;
    private String driverFirstName;
    private String driverAddress;
    private String licenceNumber;
    private Date licenceDate;
    private String insuredLastName;
    private String insuredFirstName;
    private String insuredAddress;
    private int insuredPhone;
    private String insurenceCompanyName;
    private int insurenceCompanyNumber;
    private String agencyName;
    private Date beginDate;
    private Date endDate;
    private String witnessFullName;
    private String witnessAddress;
    private int witnessPhone;
    private String apperantDamage;
    private String observation;
    private String signature;
    private String initialChocPoint;

}



