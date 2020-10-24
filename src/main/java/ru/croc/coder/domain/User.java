package ru.croc.coder.domain;

import ru.croc.coder.school.pearsons.SchoolRank;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

	SchoolRank schoolRank = SchoolRank.STUDENT;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true, nullable = false)
	private String email;

	@Transient
	private Set<Course> accessedCourses;



	private String firstName;

	private String lastName;

	public Long getId() {
		return id;
	}

	public User setId(Long id) {
		this.id = id;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public SchoolRank getSchoolRank() { return schoolRank; }

	public User setSchoolRank(SchoolRank schoolRank) {
		this.schoolRank = schoolRank;
		return this;
	}
}
