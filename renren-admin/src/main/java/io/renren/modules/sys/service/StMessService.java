
package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.City;
import io.renren.modules.sys.entity.StMess;

import java.util.Map;

public interface StMessService extends IService<StMess> {

    PageUtils queryPage(Map<String, Object> params);
}

