package sustech.ooad.a2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sustech.ooad.a2.entity.CourseEntity;

import java.util.List;

@Mapper
public interface CourseDao extends BaseMapper<CourseEntity> {
    List<CourseEntity> selectByCode(@Param("code") String code);

    List<CourseEntity> selectByDate(@Param("date") String date);

    List<CourseEntity> selectByCodeAndDate(@Param("code") String code, @Param("date") String date);
}
