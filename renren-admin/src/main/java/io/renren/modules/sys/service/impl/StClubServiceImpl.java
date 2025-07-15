package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.StClubDao;
import io.renren.modules.sys.entity.StClub;
import io.renren.modules.sys.service.StClubService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("stClubService")
public  class StClubServiceImpl extends ServiceImpl<StClubDao, StClub> implements StClubService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String)params.get("clubname");
        IPage<StClub> page = this.page(
            new Query<StClub>().getPage(params),
            new QueryWrapper<StClub>()
                .like(StringUtils.isNotBlank(name),"clubname", name)
        );
        return new PageUtils(page);
    }

}
