package ru.croc.coder.domain;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TR")
public class Teacher extends User {
}
