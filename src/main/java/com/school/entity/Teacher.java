package com.school.entity;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.Collate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Teacher_Tab")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Teacher implements Serializable {
	private static final long serialVersionUID = 1L; // Set a fixed version

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(length = 30)
	private String name;
	@Column(length = 45)
	private String email;
	@Column(length = 30)
	private String subject;
	private String role = "TEACHER";

	@OneToMany(targetEntity = Student.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Teacher_Id", referencedColumnName = "ID")
	private List<Student> stud;
}
