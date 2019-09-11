package com.antonyash.ruz.repos;

import com.antonyash.ruz.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher,Integer> {
    Teacher findByFirstNameAndAndLastName(String firstName, String lastName);
    Teacher findByFirstNameAndLastNameAndMiddleName(String firstName, String lastName, String middleName);

}
