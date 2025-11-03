package com.marble.entities;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer staffId;

    // One Staff profile belongs to one User
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private Users user; 

    @Column(name="address")
    private String address;

    @Column(name="joining_date", nullable=false)
    private LocalDate joiningDate;

    @Column(name="leaving_date", nullable=true)
    private LocalDate leavingDate; // nullable true

    @Column(name="salary_type")
    private String salaryType; // Daily/Monthly

    @Column(name="base_salary")
    private Double baseSalary;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
    
    @PrePersist
    public void prePersist() {
        if (this.leavingDate == null) {
            this.leavingDate = LocalDate.of(2050, 12, 31); // placeholder
        }
    }

}
