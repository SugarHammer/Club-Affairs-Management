package io.renren.modules.sys.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("st_zx")
public class StZx {
    @TableId
    private Long id;
    private String stName;
    private String place;
    private String user;
    private String startTime;
    private String zxlc;//流程
    private String state;




}



