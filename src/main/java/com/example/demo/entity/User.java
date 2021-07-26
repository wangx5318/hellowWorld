package com.example.demo.entity;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangx
 * @since 2021-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "姓名")
    @Excel(name = "name", orderNum = "0")
    private String name;

    @ApiModelProperty(value = "昵称")
    @Excel(name = "nickName", orderNum = "1")
    private String nickName;

    @ApiModelProperty(value = "性别:0：女；1：男")
    @Excel(name = "sex", orderNum = "2")
    private Integer sex;

    @ApiModelProperty(value = "生日")
    @Excel(name = "birthday", orderNum = "3", exportFormat = "yyyy-mm-dd hh:mm:ss",width = 20)
    private Date birthday;

    @ApiModelProperty(value = "手机号码")
    @Excel(name = "mobile", orderNum = "4", width = 12)
    @JsonSerialize(using= ToStringSerializer.class)
    private String mobile;

    @ApiModelProperty(value = "家庭住址")
    private String homeAddress;


}
