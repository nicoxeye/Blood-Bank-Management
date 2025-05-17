package com.app.bloodbank.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class BloodType {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RhFactor protein;

    public BloodType() { }

    public BloodType(BloodGroup blood, RhFactor protein) {
        this.bloodGroup = blood;
        this.protein = protein;
    }

    @Override
    public String toString() {
        return bloodGroup.name() + (protein == RhFactor.POSITIVE ? "+" : "-");
    }
}
