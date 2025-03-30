package com.school.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.annotations.Collate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Student_Tab")
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Student implements Serializable {
	private static final long serialVersionUID = 1L; // Set a fixed version

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(length = 30)
	private String name;
	@Column(length = 8)
	private String role = "STUDENT";
	@Column(length = 30)
	private String studyClass;
	@Column(length = 45)
	private String address;
	@ManyToOne(targetEntity = Teacher.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Teacher_Id", referencedColumnName = "ID")
	@JsonIgnore
	private Teacher teacher;

}
