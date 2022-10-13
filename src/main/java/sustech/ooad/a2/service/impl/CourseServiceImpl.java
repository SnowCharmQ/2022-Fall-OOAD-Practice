package sustech.ooad.a2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sustech.ooad.a2.entity.CourseEntity;
import sustech.ooad.a2.mapper.CourseDao;
import sustech.ooad.a2.service.CourseService;
import sustech.ooad.a2.vo.CourseVo;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service("CourseService")
public class CourseServiceImpl extends ServiceImpl<CourseDao, CourseEntity> implements CourseService {

    @Override
    public void add(CourseVo vo) throws ParseException {
        CourseEntity course = new CourseEntity();
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        course.setCourseId(uuid);
        course.setCourseName(vo.getName());
        course.setCode(vo.getCode());
        course.setCourseLanguage(vo.getLanguage());
        course.setTeacher(vo.getTeacher());
        course.setCourseLocation(vo.getLocation());
        course.setDuration(vo.getDuration());
        String time = vo.getDate() + " " + vo.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = formatter.parse(time);
        course.setCourseTime(new Timestamp(date.getTime()));
        this.baseMapper.insert(course);
    }
}
