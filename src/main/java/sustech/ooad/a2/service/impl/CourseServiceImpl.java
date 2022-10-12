package sustech.ooad.a2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sustech.ooad.a2.entity.CourseEntity;
import sustech.ooad.a2.mapper.CourseDao;
import sustech.ooad.a2.service.CourseService;

@Service("CourseService")
public class CourseServiceImpl extends ServiceImpl<CourseDao, CourseEntity> implements CourseService {

}
