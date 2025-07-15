
package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.StCactivityApply;
import io.renren.modules.sys.entity.StUser;

import java.util.Map;

public interface StCactivityApplyService extends IService<StCactivityApply> {

    PageUtils queryPage(Map<String, Object> params);

    void auditById(String state, int id);

}

