package sustech.ooad.a2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sustech.ooad.a2.service.CourseService;

@Controller
@RequestMapping("course")
public class CourseController {
    @Autowired
    CourseService courseService;
}
