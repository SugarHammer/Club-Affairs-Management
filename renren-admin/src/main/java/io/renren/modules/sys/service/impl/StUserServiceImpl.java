package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.StClubDao;
import io.renren.modules.sys.dao.StUserDao;
import io.renren.modules.sys.entity.StClub;
import io.renren.modules.sys.entity.StUser;
import io.renren.modules.sys.service.StClubService;
import io.renren.modules.sys.service.StUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("stUserService")
public class StUserServiceImpl extends ServiceImpl<StUserDao, StUser> implements StUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String)params.get("name");
        IPage<StUser> page = this.page(
            new Query<StUser>().getPage(params),
            new QueryWrapper<StUser>()
                .like(StringUtils.isNotBlank(name),"name", name)
        );
        return new PageUtils(page);
    }

}
