package com.antonyash.ruz.controller;

import com.antonyash.ruz.model.Lesson;
import com.antonyash.ruz.model.Teacher;
import com.antonyash.ruz.repos.LessonRepo;
import com.antonyash.ruz.repos.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/create")
public class CreatingController {

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    LessonRepo lessonRepo;



    @PostMapping("/lesson")
    public String lesson(@RequestParam Integer day, @RequestParam Integer lesson,@RequestParam String name,@RequestParam Integer group,@RequestParam String teacher, @RequestParam(required = false, defaultValue = "false") boolean isLecture){
        String[] strings = teacher.split(" ",3);
        Teacher findedTeacher;
        if(strings.length==3) {
             findedTeacher = teacherRepo.findByFirstNameAndLastNameAndMiddleName(strings[1], strings[0], strings[2]);
        }
        else {
             findedTeacher = teacherRepo.findByFirstNameAndAndLastName(strings[1], strings[0]);
        }
        if(findedTeacher==null)
            findedTeacher = new Teacher(strings[1], strings[0], strings[2]);
        teacherRepo.save(findedTeacher);
        Lesson lesson1 = new Lesson(day, name, lesson, group, findedTeacher,isLecture);
        lessonRepo.save(lesson1);
        return "create";
    }

    @PostMapping("/teacher")
    public String teacher(@RequestParam String lastName, @RequestParam String firstName, @RequestParam String middleName, @RequestParam Integer salary, Model model){
        try {
            Teacher teacher = new Teacher(firstName, middleName, lastName, salary);
            teacherRepo.save(teacher);
        }
        catch (Exception e){
            model.addAttribute("exception",e.getMessage());
        }
        return "create";
    }
}
