package com.antonyash.ruz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    private String firstName;
    @NotNull
    private String middleName;
    @NotEmpty
    private String lastName;

    @NotNull
    private Integer salary;

    public Teacher() {
    }

    public Teacher(@NotEmpty String firstName, @NotNull String middleName, @NotEmpty String lastName, @NotNull Integer salary) {
        this.setFirstName(firstName);
        this.middleName = middleName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public Teacher(@NotEmpty String firstName, @NotNull String middleName, @NotEmpty String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.setSalary(25000);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName.equals(firstName.replaceAll("[\\W[\\d[_]]]", "")))
            throw new IllegalArgumentException("Некорректное имя пользователя");
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
