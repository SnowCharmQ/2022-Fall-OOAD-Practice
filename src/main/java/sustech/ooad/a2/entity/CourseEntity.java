package sustech.ooad.a2.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;


@Data
@TableName("t_course")
public class CourseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    Integer courseId;
    String courseName;
    String code;
    String courseLanguage;
    String teacher;
    Timestamp courseTime;
    Double duration;
}
