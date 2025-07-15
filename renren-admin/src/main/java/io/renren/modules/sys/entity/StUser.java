package io.renren.modules.sys.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("st_user")
public class StUser {
    @TableId
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String clubs;
    private String school;
    private Date createTime;
    private String clas;


}



