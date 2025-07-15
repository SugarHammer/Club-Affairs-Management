package io.renren.modules.sys.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.StZx;
import io.renren.modules.sys.service.StZxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/stZx")
public class StZxController extends AbstractController {
    @Autowired
    private StZxService stZxService;
    @RequestMapping("/list")
    public R list(Map<String, Object> params){
        PageUtils page = stZxService.queryPage(params);
        return R.ok().put("page", page);
    }
    @RequestMapping("/test")
    public R test2(){
        List<StZx> list = stZxService.list();
        return R.ok().put("mapList",list);
    }
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        StZx stZx = stZxService.getById(id);
        return R.ok().put("stZx", stZx);
    }
    @RequestMapping("/save")
    public R save(@RequestBody StZx stZx){
        stZx.setState("未审核");
        stZxService.save(stZx);
        return R.ok();
    }

    @RequestMapping("/auditById")
    public R auditById(@RequestParam("id") Integer id, @RequestParam("state") String state){
        stZxService.auditById(state,id);
        return R.ok();
    }

    @RequestMapping("/auditList")//审核列表 显示所有未审核的
    public R auditList(Map<String, Object> params){
        params.put("state","未审核");
        PageUtils page = stZxService.queryPage(params);
        return R.ok().put("page", page);
    }
    @RequestMapping("/update")
    public R update(@RequestBody StZx stZx){
        stZxService.updateById(stZx);
        return R.ok();
    }
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        stZxService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
}