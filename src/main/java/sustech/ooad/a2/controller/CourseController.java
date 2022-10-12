package sustech.ooad.a2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sustech.ooad.a2.entity.CourseEntity;
import sustech.ooad.a2.service.CourseService;

import java.util.List;

@Controller
@RequestMapping("course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("/course.html")
    public String courseInfo(Model model) {
        List<CourseEntity> list = courseService.list();
        model.addAttribute("courses", list);
        return "course";
    }
}
