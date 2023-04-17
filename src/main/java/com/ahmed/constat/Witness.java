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
@Entity(name = "witness")
public class Witness {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,name = "full_name")
    private String witnessFullName;

    @Column(nullable = false, name ="address")
    private String witnessAddress;
    
    @Column(nullable = false, name="phone")
    private int witnessPhone;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_constat_id", referencedColumnName = "id")
    private Constat constat;
    public Witness() {

    }

    public Witness(String fullName, String address, int phone) {
        this.witnessFullName = fullName;
        this.witnessAddress = address;
        this.witnessPhone = phone;
    }
}
