package com.petex.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "REPORT-TAB")
public class ReportEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long petId;

	private String userName;

	private String userEmail;

	private String address;

	private Long userMoblieNumber;

	private String petName;

	private String petType;

	private String petBreed;

	private String petColour;

	private LocalDate dateOfLost;

	@CreationTimestamp
	private LocalDate date;

	@CreationTimestamp
	private LocalTime times;

}
