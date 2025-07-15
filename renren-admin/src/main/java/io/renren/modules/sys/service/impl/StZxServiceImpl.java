package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.StZxDao;
import io.renren.modules.sys.dao.StZxDao;
import io.renren.modules.sys.entity.StZx;
import io.renren.modules.sys.service.StZxService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("stZxService")
public class StZxServiceImpl extends ServiceImpl<StZxDao, StZx> implements StZxService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String)params.get("name");
        IPage<StZx> page = this.page(
            new Query<StZx>().getPage(params),
            new QueryWrapper<StZx>()
                .like(StringUtils.isNotBlank(name),"name", name)
        );
        return new PageUtils(page);
    }

    @Override
    public void auditById(String state, int id) {
        baseMapper.auditById(state,id);
    }
}
