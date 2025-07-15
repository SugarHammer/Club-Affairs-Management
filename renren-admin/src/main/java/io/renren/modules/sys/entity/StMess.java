package io.renren.modules.sys.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("st_mess")
public class StMess {
    @TableId
    private Long id;
    private String name;
    private Date createTime;
    private String users;
    private String jianjie;
    private String zhiwu;
    private String field;
}



