package com.xpto.sales_score_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "salesperson")
public class Salesperson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @NotNull(message = "name cannot be blank")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "registration cannot be blank")
    @Column(name = "registration", nullable = false, unique = true)
    private String registration;
}
