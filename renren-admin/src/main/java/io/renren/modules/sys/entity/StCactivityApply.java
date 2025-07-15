package io.renren.modules.sys.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("st_cactivity_apply")
public class StCactivityApply {
    @TableId
    private Long id;
    private String name;
    private String club;
    private String place;
    private String starttime;
    private String endtime;
    private String sort;
    private String dc;
    private String matters;
    private String state;
    private String feedback;

}



