package com.ahmed.constat;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
@Entity(name = "insured_information")
public class InsuredInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,name = "last_name")
    private String insuredLastName;

    @Column(nullable = false, name = "first_name")
    private String insuredFirstName;

    @Column(name ="address")
    private String insuredAddress;
    
    @Column(nullable = false, name="phone")
    private int insuredPhone;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_vehicule_information_id", referencedColumnName = "id")
    private VehiculeInformation vehiculeInformation;

    public InsuredInformation() {

    }
    public InsuredInformation(String lastName, String firstName, String InsuredAddress, int phone) {
        this.insuredLastName = lastName;
        this.insuredFirstName = firstName;
        this.insuredAddress = InsuredAddress;
        this.insuredPhone = phone;

    }
}
