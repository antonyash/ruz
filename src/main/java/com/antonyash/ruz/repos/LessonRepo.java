package com.antonyash.ruz.repos;

import com.antonyash.ruz.model.Lesson;
import com.antonyash.ruz.utils.DaysOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepo extends JpaRepository<Lesson,Integer> {
    Iterable<Lesson> findByGroupNumber(Integer groupNumber);
    Iterable<Lesson> findByTeacher_FirstNameAndTeacher_LastName(String firstName,String LastName);
    Iterable<Lesson> findByGroupNumberAndDayOrderByTime(Integer groupNumber, DaysOfWeek day);
}
