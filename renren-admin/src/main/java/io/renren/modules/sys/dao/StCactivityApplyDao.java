package io.renren.modules.sys.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.sys.entity.StCactivityApply;
import io.renren.modules.sys.entity.StUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StCactivityApplyDao extends BaseMapper<StCactivityApply> {
    void auditById(@Param("state") String state, @Param("id") int id);

}