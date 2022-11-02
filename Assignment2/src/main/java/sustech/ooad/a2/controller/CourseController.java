package sustech.ooad.a2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sustech.ooad.a2.entity.CourseEntity;
import sustech.ooad.a2.mapper.CourseDao;
import sustech.ooad.a2.service.CourseService;
import sustech.ooad.a2.utils.JsonResult;
import sustech.ooad.a2.vo.CourseVo;
import sustech.ooad.a2.vo.SearchVo;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("course")
public class CourseController {
    @Autowired
    CourseDao courseDao;
    @Autowired
    CourseService courseService;

    @RequestMapping("/course.html")
    public String courseInfo(SearchVo vo, Model model) {
        String code = vo.getCode();
        String date = vo.getDate();
        List<CourseEntity> entities = null;
        if ((code != null && !StringUtils.isEmpty(code)) && (date != null && !StringUtils.isEmpty(date))) {
            entities = courseDao.selectByCodeAndDate(code, date);
        } else if (code != null && !StringUtils.isEmpty(code)) {
            entities = courseDao.selectByCode(code);
        } else if (date != null && !StringUtils.isEmpty(date)) {
            entities = courseDao.selectByDate(date);
        }
        if (entities != null) {
            model.addAttribute("courses", entities);
        } else {
            entities = courseService.list();
            model.addAttribute("courses", entities);
        }
        return "course";
    }

    @RequestMapping("/search")
    public String search(SearchVo vo) {
        StringBuilder url = new StringBuilder("redirect:");
        url.append("course.html");
        if (vo.getCode() != null || vo.getDate() != null) {
            url.append("?");
            url.append("code=").append(vo.getCode() == null ? "" : vo.getCode());
            url.append("&");
            url.append("date=").append(vo.getDate() == null ? "" : vo.getDate());
        }
        return url.toString();
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
