package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangx
 * @since 2021-04-12
 */
@ApiModel(value="City对象", description="")
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "城市名称")
    @TableField("Name")
    private String Name;

    @ApiModelProperty(value = "国家代码")
    @TableField("CountryCode")
    private String CountryCode;

    @ApiModelProperty(value = "地区")
    @TableField("District")
    private String District;

    @ApiModelProperty(value = "人口")
    @TableField("Population")
    private Integer Population;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String CountryCode) {
        this.CountryCode = CountryCode;
    }
    public String getDistrict() {
        return District;
    }

    public void setDistrict(String District) {
        this.District = District;
    }
    public Integer getPopulation() {
        return Population;
    }

    public void setPopulation(Integer Population) {
        this.Population = Population;
    }

    @Override
    public String toString() {
        return "City{" +
            "id=" + id +
            ", Name=" + Name +
            ", CountryCode=" + CountryCode +
            ", District=" + District +
            ", Population=" + Population +
        "}";
    }
}
