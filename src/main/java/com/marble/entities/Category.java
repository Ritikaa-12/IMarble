package com.marble.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(nullable = false, unique = true)
    private String title;

    private String description;

    private String images; // URL to an image for the category

    private Boolean status = true; // Default to true



}