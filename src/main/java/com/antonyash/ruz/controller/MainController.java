package com.antonyash.ruz.controller;

import com.antonyash.ruz.model.Lesson;
import com.antonyash.ruz.repos.LessonRepo;
import com.antonyash.ruz.repos.TeacherRepo;
import com.antonyash.ruz.utils.DaysOfWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    LessonRepo lessonRepo;


    @RequestMapping("/")
    public String def(Model model){
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(Model model){
        List<Lesson> all = lessonRepo.findAll();
        List<Integer> groups = new ArrayList<>();
        for (Lesson lesson:all
             ) {
            groups.add(lesson.getGroupNumber());
        }
        model.addAttribute("groups",groups);
        model.addAttribute("teachers",teacherRepo.findAll());
        return "index";}

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("teachers",teacherRepo.findAll());
        return "create";
    }

    @GetMapping("/group")
    public String group(@RequestParam(name = "groupNumber") Integer groupNumber, Model model){

        DaysOfWeek[] days = DaysOfWeek.values();
        for(int i=0;i<6;i++){
            ArrayList<Lesson> lessons = (ArrayList<Lesson>) lessonRepo.findByGroupNumberAndDayOrderByTime(groupNumber, days[i]);
            if(lessons.size()!=0)
            {
                switch (i){
                    case 0:model.addAttribute("monday",lessons);
                    break;
                    case 1:model.addAttribute("tuesday",lessons);
                        break;
                    case 2:model.addAttribute("wednesday",lessons);
                        break;
                    case 3:model.addAttribute("thursday",lessons);
                        break;
                    case 4:model.addAttribute("friday",lessons);
                        break;
                    case 5:model.addAttribute("sunday",lessons);
                        break;
                }
            }

        }

        //Iterable<Lesson> lessons = lessonRepo.findByGroupNumber(groupNumber);
       // model.addAttribute("days",days);


        List<Lesson> all = lessonRepo.findAll();
        List<Integer> groups = new ArrayList<>();
        for (Lesson lesson:all
        ) {
            groups.add(lesson.getGroupNumber());
        }
        model.addAttribute("groups",groups);
        model.addAttribute("teachers",teacherRepo.findAll());
        return "index";
    }

    @GetMapping("/teacher")
    public String teacher(@RequestParam(name = "teacher") String teacher, Model model){
        String[] strings = teacher.split(" ",3);
        Iterable<Lesson> lessons = lessonRepo.findByTeacher_FirstNameAndTeacher_LastName(strings[1], strings[0]);
        model.addAttribute("lessons",lessons);


        List<Lesson> all = lessonRepo.findAll();
        List<Integer> groups = new ArrayList<>();
        for (Lesson lesson:all
        ) {
            groups.add(lesson.getGroupNumber());
        }
        model.addAttribute("groups",groups);
        model.addAttribute("teachers",teacherRepo.findAll());
        return "index";
    }
}
