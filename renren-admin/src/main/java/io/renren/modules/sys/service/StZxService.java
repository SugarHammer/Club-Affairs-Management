
package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.StUser;
import io.renren.modules.sys.entity.StZx;

import java.util.Map;

public interface StZxService extends IService<StZx> {

    PageUtils queryPage(Map<String, Object> params);
    void auditById(String state, int id);

}

