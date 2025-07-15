package io.renren.modules.sys.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.sys.entity.StClub;
import io.renren.modules.sys.entity.StUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StUserDao extends BaseMapper<StUser> {

}