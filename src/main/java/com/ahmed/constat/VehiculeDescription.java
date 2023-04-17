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
@Entity(name = "vehicule_description")
public class VehiculeDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false, name="car_plate")
    private String carPlate;
    
    @Column(nullable = false)
    private String direction;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_vehicule_information_id", referencedColumnName = "id")
    private VehiculeInformation vehiculeInformation;

    public VehiculeDescription() {

    }

    public VehiculeDescription(String brand, String type, String carPlate, String direction) {
        this.brand = brand;
        this.type = type;
        this.carPlate = carPlate;
        this.direction = direction;
    }

}
