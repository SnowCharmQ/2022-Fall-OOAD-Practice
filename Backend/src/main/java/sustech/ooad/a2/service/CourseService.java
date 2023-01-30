package sustech.ooad.a2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.ooad.a2.entity.CourseEntity;
import sustech.ooad.a2.vo.CourseVo;

import java.text.ParseException;

public interface CourseService extends IService<CourseEntity> {

    void add(CourseVo vo) throws ParseException;

    void update(CourseVo vo) throws ParseException;
}
