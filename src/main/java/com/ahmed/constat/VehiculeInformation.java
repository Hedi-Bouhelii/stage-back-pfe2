package com.ahmed.constat;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "vehicule_information")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehiculeInformation {

     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Id
     private Long id;
    @Column(nullable = false,name = "apperant_damage")
     private String apperantDamage;
    @Column(nullable = false)
     private String observation;
    @Column(name ="intial_choc_point",nullable = false)
    private String initialChocPoint;
    @Column(nullable = false)
     private String signature;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_constat_id", referencedColumnName = "id")
    private Constat constat;
    @OneToOne(mappedBy = "vehiculeInformation",fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_driver_id", referencedColumnName = "id")
    private DriverIdentity driverIdentity;
    @OneToOne(mappedBy = "vehiculeInformation",fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_insured_info_id", referencedColumnName = "id")
    private  InsuredInformation insuredInformation;
    @OneToOne(mappedBy = "vehiculeInformation",fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_insurence_company_id", referencedColumnName = "id")
    private  InsurenceCompanyInformation insurenceCompanyInformation;
    @OneToOne(mappedBy = "vehiculeInformation",fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_vehicule_desc_id", referencedColumnName = "id")
    private VehiculeDescription vehiculeDescription;




}
