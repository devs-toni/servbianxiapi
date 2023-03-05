package com.ecommerce.bikes.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "colors")
public class Color {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "color", nullable = false)
	private String color;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "color")
	private List<BikeConfiguration> configuration;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<BikeConfiguration> getConfiguration() {
		return configuration;
	}

	public void setConfiguration(List<BikeConfiguration> configuration) {
		this.configuration = configuration;
	}

	@Override
	public String toString() {
		return "Colors [id=" + id + ", color=" + color + "]";
	}
}