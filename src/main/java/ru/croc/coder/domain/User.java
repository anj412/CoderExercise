package ru.croc.coder.domain;

import ru.croc.coder.school.pearsons.SchoolRank;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "users")
@Table(name = "users")
public class User {

	@Override
	public String toString() {
		return "User [schoolRank=" + schoolRank + ", email=" + email + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false, length = 12)
	private String password;

	@OneToMany(mappedBy = "user")
	private Set<UserCourseRegistration> registrations;

	private Integer attemptsCount = 0;

	@Enumerated(EnumType.STRING)
	private SchoolRank schoolRank;

	private String firstName;

	private String lastName;

	@OneToMany(mappedBy = "author")
	private Set<Solution> solutions;

	public Set<Solution> getSolutions() {
		return solutions;
	}

	public User setSolutions(Set<Solution> solutions) {
		this.solutions = solutions;
		return this;
	}

	public String getPassword() {return password;}
	public User setPassword(String password) {	this.password = password; return this;}

	public Long getId() {return id;}
	public User setId(Long id) {this.id = id; return this;	}

	public String getEmail() {
		return email;
	}
	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getFirstName() { return firstName; }
	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public Set<UserCourseRegistration> getRegistrations() {
		return registrations;
	}

	public User setRegistrations(Set<UserCourseRegistration> registrations) {
		this.registrations = registrations;
		return this;
	}

	public String getLastName() { return lastName;}
	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public SchoolRank getSchoolRank() { return schoolRank; }
	public User setSchoolRank(SchoolRank schoolRank) {
		this.schoolRank = schoolRank;
		return this;
	}

	public Integer getAttemptsCount() {
		return attemptsCount;
	}
	public User setAttemptsCount(Integer attemptsCount) {
		this.attemptsCount = attemptsCount;
		return this;
	}
}
