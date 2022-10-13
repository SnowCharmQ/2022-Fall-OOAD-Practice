package sustech.ooad.a2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sustech.ooad.a2.entity.CourseEntity;
import sustech.ooad.a2.service.CourseService;
import sustech.ooad.a2.utils.JsonResult;
import sustech.ooad.a2.vo.CourseVo;

import java.text.ParseException;
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

    @RequestMapping("/add")
    public String add(CourseVo vo) throws ParseException {
        courseService.add(vo);
        return "redirect:course.html";
    }

    @RequestMapping("/update")
    public String update(CourseVo vo) throws ParseException {
        courseService.update(vo);
        return "redirect:course.html";
    }

    @ResponseBody
    @RequestMapping("/delete")
    public JsonResult<Void> delete(@RequestParam("keyword") String id) {
        courseService.remove(new QueryWrapper<CourseEntity>().eq("course_id", id));
        return new JsonResult<>();
    }
}
