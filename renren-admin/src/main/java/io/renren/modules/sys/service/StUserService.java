
package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.StUser;

import java.util.Map;

public interface StUserService extends IService<StUser> {

    PageUtils queryPage(Map<String, Object> params);
}

