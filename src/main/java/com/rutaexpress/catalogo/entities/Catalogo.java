package com.rutaexpress.catalogo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del servicio es obligatorio")
    private String nombre;

    private String descripcion;

    @PositiveOrZero(message = "La tarifa no puede ser negativa")
    private double precio;

    @NotNull(message = "La capacidad disponible es obligatoria")
    @PositiveOrZero(message = "La capacidad disponible no puede ser negativa")
    private Integer cantidadDisponible;
}
