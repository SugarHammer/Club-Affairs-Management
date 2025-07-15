package io.renren.modules.sys.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("st_club")
public class StClub {
    @TableId
    private Long id;
    private String clubname;
    private String clubPlace;
    private String notice;
    private String sort;
    private String clubDesc;
    private Date establishTime;
    private int memberNum;
    private int jf;

}



