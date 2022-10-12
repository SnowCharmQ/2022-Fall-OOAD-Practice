package sustech.ooad.a2.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@TableName("t_user")
public class UserEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    Integer userId;
    String username;
    String pwd;
}
