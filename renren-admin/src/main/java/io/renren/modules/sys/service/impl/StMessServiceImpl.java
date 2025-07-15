package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.CityDao;
import io.renren.modules.sys.dao.StMessDao;
import io.renren.modules.sys.entity.City;
import io.renren.modules.sys.entity.StMess;
import io.renren.modules.sys.service.CityService;
import io.renren.modules.sys.service.StMessService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("stMessService")
public class StMessServiceImpl extends ServiceImpl<StMessDao, StMess> implements StMessService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String)params.get("name");
        IPage<StMess> page = this.page(
            new Query<StMess>().getPage(params),
            new QueryWrapper<StMess>()
                .like(StringUtils.isNotBlank(name),"name", name)
        );
        return new PageUtils(page);
    }

}
