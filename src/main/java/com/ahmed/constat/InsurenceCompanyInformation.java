package com.ahmed.constat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@Getter
@Setter
@Data
@Entity(name = "Insurence_company_information")
public class InsurenceCompanyInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,name = "insurence_company_name")
    private String insurenceCompanyName;

    @Column(nullable = false, name = "insurence_company_number")
    private int insurenceCompanyNumber;

    @Column(nullable = false, name ="agency_name")
    private String agencyName;
    
    @Column(nullable = false, name="begin_date")
    private Date beginDate;
    
    @Column(nullable = false,name="end_date")
    private Date endDate;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_vehicule_information_id", referencedColumnName = "id")
    private VehiculeInformation vehiculeInformation;

    public InsurenceCompanyInformation() {

    }

    public InsurenceCompanyInformation( String insurenceCompanyName, int insurenceCompanyNumber, String agencyName, Date beginDate, Date endDate) {
        this.insurenceCompanyName = insurenceCompanyName;
        this.insurenceCompanyNumber = insurenceCompanyNumber;
        this.agencyName = agencyName;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }
}
