package sustech.ooad.a2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sustech.ooad.a2.entity.UserEntity;
import sustech.ooad.a2.mapper.UserDao;
import sustech.ooad.a2.service.UserService;

@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public boolean checkUsername(String username) {
        UserEntity entity = this.baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("username", username));
        return entity != null;
    }
}
