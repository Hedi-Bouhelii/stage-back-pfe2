package com.ahmed.constat;


import com.ahmed.user.User;
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

@Entity
public class Constat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "fk_user_id", referencedColumnName = "id")
    private User user;
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private Boolean injuries;

    @Column(nullable = false)
    private Boolean materialDamage;
    @Enumerated(EnumType.STRING)
    private Circumstances circumstances;
    @OneToOne(mappedBy = "constat")
    @JoinColumn(name = "fk_vehicule_information_id", referencedColumnName = "id")
    private VehiculeInformation vehiculeInformation;
    @OneToOne(mappedBy = "constat")
    @JoinColumn(name = "constat_id", referencedColumnName = "id")
    private  Witness witness;

    public Constat() {

    }
}
