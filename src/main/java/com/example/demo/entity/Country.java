package com.example.demo.entity;

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
@ApiModel(value="Country对象", description="")
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("Code")
    private String Code;

    @TableField("Name")
    private String Name;

    @TableField("Continent")
    private String Continent;

    @TableField("Region")
    private String Region;

    @TableField("SurfaceArea")
    private Float SurfaceArea;

    @TableField("IndepYear")
    private Integer IndepYear;

    @TableField("Population")
    private Integer Population;

    @TableField("LifeExpectancy")
    private Float LifeExpectancy;

    @TableField("GNP")
    private Float gnp;

    @TableField("GNPOld")
    private Float GNPOld;

    @TableField("LocalName")
    private String LocalName;

    @TableField("GovernmentForm")
    private String GovernmentForm;

    @TableField("HeadOfState")
    private String HeadOfState;

    @TableField("Capital")
    private Integer Capital;

    @TableField("Code2")
    private String Code2;

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public String getContinent() {
        return Continent;
    }

    public void setContinent(String Continent) {
        this.Continent = Continent;
    }
    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }
    public Float getSurfaceArea() {
        return SurfaceArea;
    }

    public void setSurfaceArea(Float SurfaceArea) {
        this.SurfaceArea = SurfaceArea;
    }
    public Integer getIndepYear() {
        return IndepYear;
    }

    public void setIndepYear(Integer IndepYear) {
        this.IndepYear = IndepYear;
    }
    public Integer getPopulation() {
        return Population;
    }

    public void setPopulation(Integer Population) {
        this.Population = Population;
    }
    public Float getLifeExpectancy() {
        return LifeExpectancy;
    }

    public void setLifeExpectancy(Float LifeExpectancy) {
        this.LifeExpectancy = LifeExpectancy;
    }
    public Float getGnp() {
        return gnp;
    }

    public void setGnp(Float gnp) {
        this.gnp = gnp;
    }
    public Float getGNPOld() {
        return GNPOld;
    }

    public void setGNPOld(Float GNPOld) {
        this.GNPOld = GNPOld;
    }
    public String getLocalName() {
        return LocalName;
    }

    public void setLocalName(String LocalName) {
        this.LocalName = LocalName;
    }
    public String getGovernmentForm() {
        return GovernmentForm;
    }

    public void setGovernmentForm(String GovernmentForm) {
        this.GovernmentForm = GovernmentForm;
    }
    public String getHeadOfState() {
        return HeadOfState;
    }

    public void setHeadOfState(String HeadOfState) {
        this.HeadOfState = HeadOfState;
    }
    public Integer getCapital() {
        return Capital;
    }

    public void setCapital(Integer Capital) {
        this.Capital = Capital;
    }
    public String getCode2() {
        return Code2;
    }

    public void setCode2(String Code2) {
        this.Code2 = Code2;
    }

    @Override
    public String toString() {
        return "Country{" +
            "Code=" + Code +
            ", Name=" + Name +
            ", Continent=" + Continent +
            ", Region=" + Region +
            ", SurfaceArea=" + SurfaceArea +
            ", IndepYear=" + IndepYear +
            ", Population=" + Population +
            ", LifeExpectancy=" + LifeExpectancy +
            ", gnp=" + gnp +
            ", GNPOld=" + GNPOld +
            ", LocalName=" + LocalName +
            ", GovernmentForm=" + GovernmentForm +
            ", HeadOfState=" + HeadOfState +
            ", Capital=" + Capital +
            ", Code2=" + Code2 +
        "}";
    }
}
