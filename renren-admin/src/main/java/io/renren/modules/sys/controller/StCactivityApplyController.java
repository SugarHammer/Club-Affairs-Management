package io.renren.modules.sys.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.StCactivityApply;
import io.renren.modules.sys.service.StCactivityApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/stCactivityApply")
public class StCactivityApplyController extends AbstractController {
    @Autowired
    private StCactivityApplyService stCactivityApplyService;


    @RequestMapping("/auditById")
    public R auditById(@RequestParam("id") Integer id, @RequestParam("state") String state){
        stCactivityApplyService.auditById(state,id);
        return R.ok();
    }
    @RequestMapping("/auditList")//审核列表 显示所有未审核的
    public R auditList(Map<String, Object> params){
        params.put("state","未审核");
        PageUtils page = stCactivityApplyService.queryPage(params);
        return R.ok().put("page", page);
    }


    @RequestMapping("/list")
    public R list(Map<String, Object> params){
        PageUtils page = stCactivityApplyService.queryPage(params);
        return R.ok().put("page", page);
    }
    @RequestMapping("/test")
    public R test2(){
        List<StCactivityApply> list = stCactivityApplyService.list();
        return R.ok().put("mapList",list);
    }
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        StCactivityApply stCactivityApply = stCactivityApplyService.getById(id);
        return R.ok().put("stCactivityApply", stCactivityApply);
    }
    @RequestMapping("/save")
    public R save(@RequestBody StCactivityApply stCactivityApply){
        stCactivityApply.setState("未审核");
        stCactivityApplyService.save(stCactivityApply);
        return R.ok();
    }
    @RequestMapping("/update")
    public R update(@RequestBody StCactivityApply stCactivityApply){
        stCactivityApplyService.updateById(stCactivityApply);
        return R.ok();
    }
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        stCactivityApplyService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
}