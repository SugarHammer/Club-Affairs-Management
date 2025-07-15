package io.renren.modules.sys.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.sys.entity.StUser;
import io.renren.modules.sys.entity.StZx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StZxDao extends BaseMapper<StZx> {

    void auditById(@Param("state") String state, @Param("id") int id);
}