package sustech.ooad.a2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import sustech.ooad.a2.entity.UserEntity;

@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
