package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.StCactivityApplyDao;
import io.renren.modules.sys.dao.StCactivityApplyDao;
import io.renren.modules.sys.entity.StCactivityApply;
import io.renren.modules.sys.service.StCactivityApplyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("stCactivityApplyService")
public class StCactivityApplyServiceImpl extends ServiceImpl<StCactivityApplyDao, StCactivityApply> implements StCactivityApplyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String)params.get("name");
        String state = (String)params.get("state");
        IPage<StCactivityApply> page = this.page(
            new Query<StCactivityApply>().getPage(params),
            new QueryWrapper<StCactivityApply>()
                .like(StringUtils.isNotBlank(name),"name", name)
                    .eq(StringUtils.isNotBlank(state),"state", state)

        );
        return new PageUtils(page);
    }

    @Override
    public void auditById(String state, int id) {
        baseMapper.auditById(state,id);
    }

}
