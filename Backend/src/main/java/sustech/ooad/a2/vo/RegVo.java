package sustech.ooad.a2.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegVo {
    String username;
    String pwd;
    String confirmPwd;
}
