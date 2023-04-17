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
@Entity(name = "Driver_identity")
public class DriverIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,name = "last_name")
    private String driverLastName;

    @Column(nullable = false, name = "first_name")
    private String driverFirstName;

    @Column(nullable = false, name ="address")
    private String driverAddress;
    
    @Column(nullable = false, name="licence_number")
    private String licenceNumber;
    
    @Column(nullable = false,name="licence_date")
    private Date licenceDate;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_vehicule_information_id", referencedColumnName = "id")
    private VehiculeInformation vehiculeInformation;


    public DriverIdentity() {

    }

    public DriverIdentity(String lastName, String firstName, String address, String licenceNumber, Date licenceDate) {
        this.driverLastName = lastName;
        this.driverFirstName = firstName;
        this.driverAddress = address;
        this.licenceNumber = licenceNumber;
        this.licenceDate = licenceDate;
    }
}
