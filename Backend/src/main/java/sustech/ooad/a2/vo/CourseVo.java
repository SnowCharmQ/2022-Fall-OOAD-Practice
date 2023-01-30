package sustech.ooad.a2.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CourseVo {
    private String id;
    private String name;
    private String code;
    private String language;
    private String teacher;
    private String date;
    private String time;
    private String location;
    private Double duration;
}
