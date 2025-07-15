package io.renren.modules.sys.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.City;
import io.renren.modules.sys.entity.StMess;
import io.renren.modules.sys.service.CityService;
import io.renren.modules.sys.service.StMessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/stMess")
public class StMessController extends AbstractController {
    @Autowired
    private StMessService stMessService;
    @RequestMapping("/list")
    public R list(Map<String, Object> params){
        PageUtils page = stMessService.queryPage(params);
        return R.ok().put("page", page);
    }
    @RequestMapping("/test")
    public R test2(){
        List<StMess> list = stMessService.list();
        return R.ok().put("mapList",list);
    }
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        StMess stMess = stMessService.getById(id);
        return R.ok().put("stMess", stMess);
    }
    @RequestMapping("/save")
    public R save(@RequestBody StMess stMess){
        stMessService.save(stMess);
        return R.ok();
    }
    @RequestMapping("/update")
    public R update(@RequestBody StMess stMess){
        stMessService.updateById(stMess);
        return R.ok();
    }
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        stMessService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
}