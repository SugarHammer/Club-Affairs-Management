package io.renren.modules.sys.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.StClub;
import io.renren.modules.sys.entity.StMess;
import io.renren.modules.sys.service.StClubService;
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
@RequestMapping("/sys/stClub")
public class StClubController extends AbstractController {
    @Autowired
    private StClubService stClubService;
    @RequestMapping("/list")
    public R list(Map<String, Object> params){
        PageUtils page = stClubService.queryPage(params);
        return R.ok().put("page", page);
    }
    @RequestMapping("/test")
    public R test2(){
        List<StClub> list = stClubService.list();
        return R.ok().put("mapList",list);
    }
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        StClub stClub = stClubService.getById(id);
        return R.ok().put("stClub", stClub);
    }
    @RequestMapping("/save")
    public R save(@RequestBody StClub stClub){
        stClubService.save(stClub);
        return R.ok();
    }
    @RequestMapping("/update")
    public R update(@RequestBody StClub stClub){
        stClubService.updateById(stClub);
        return R.ok();
    }
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        stClubService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
}