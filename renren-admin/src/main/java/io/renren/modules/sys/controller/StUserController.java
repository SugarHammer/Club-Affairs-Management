package io.renren.modules.sys.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.StClub;
import io.renren.modules.sys.entity.StUser;
import io.renren.modules.sys.service.StClubService;
import io.renren.modules.sys.service.StUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/stUser")
public class StUserController extends AbstractController {
    @Autowired
    private StUserService stUserService;
    @RequestMapping("/list")
    public R list(Map<String, Object> params){
        PageUtils page = stUserService.queryPage(params);
        return R.ok().put("page", page);
    }
    @RequestMapping("/test")
    public R test2(){
        List<StUser> list = stUserService.list();
        return R.ok().put("mapList",list);
    }
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        StUser stUser = stUserService.getById(id);
        return R.ok().put("stUser", stUser);
    }
    @RequestMapping("/save")
    public R save(@RequestBody StUser stUser){
        stUser.setCreateTime(new Date());
        stUserService.save(stUser);
        return R.ok();
    }
    @RequestMapping("/update")
    public R update(@RequestBody StUser stUser){
        stUserService.updateById(stUser);
        return R.ok();
    }
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        stUserService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
}