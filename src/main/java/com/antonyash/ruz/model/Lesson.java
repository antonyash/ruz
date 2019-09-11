package com.antonyash.ruz.model;

import com.antonyash.ruz.utils.DaysOfWeek;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private DaysOfWeek day;
    @NotEmpty
    private  String name;
    @NotNull
    private Integer time;
    @NotNull
    private boolean isLecture;

    public Lesson() {
    }


    public Lesson(@NotNull Integer day, @NotEmpty String name, @NotNull Integer time, @NotNull Integer groupNumber, @NotNull Teacher teacher,@NotNull boolean isLecture) {
        DaysOfWeek[] days = DaysOfWeek.values();
        this.day = days[day-1];
        this.name = name;
        this.time = time;
        this.groupNumber = groupNumber;
        this.teacher = teacher;
        this.isLecture = isLecture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DaysOfWeek getDay() {
        return day;
    }

    public void setDay(Integer day) {
        DaysOfWeek[] days = DaysOfWeek.values();
        this.day = days[day-1];
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        time = time % 8 + 1;
        this.time = time;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLecture() {
        return isLecture;
    }

    public void setLecture(boolean lecture) {
        isLecture = lecture;
    }

    @NotNull
    private Integer groupNumber;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "teacherId")
    private Teacher teacher;
}
