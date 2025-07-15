package io.renren.modules.sys.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.sys.entity.City;
import io.renren.modules.sys.entity.StMess;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StMessDao extends BaseMapper<StMess> {

}