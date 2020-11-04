package ru.croc.coder.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "registrations")
public class UserCourseRegistration {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Long getId() {
		return id;
	}

	public UserCourseRegistration setId(Long id) {
		this.id = id;
		return this;
	}

	public Course getCourse() {
		return course;
	}

	public UserCourseRegistration setCourse(Course course) {
		this.course = course;
		return this;
	}

	public User getUser() {
		return user;
	}

	public UserCourseRegistration setUser(User user) {
		this.user = user;
		return this;
	}
}
