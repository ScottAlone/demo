package com.xay.MySQL.DO;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/9.
 */
public class CityDO {
    private Integer city_id;
    private Integer code;
    private String name;
    private Integer parent_id;
    private String first_letter;
    private Integer level;

    public CityDO(Integer city_id, Integer code, String name, Integer parent_id, String first_letter, Integer level) {
        this.city_id = city_id;
        this.code = code;
        this.name = name;
        this.parent_id = parent_id;
        this.first_letter = first_letter;
        this.level = level;
    }

    public CityDO() {

    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getFirst_letter() {
        return first_letter;
    }

    public void setFirst_letter(String first_letter) {
        this.first_letter = first_letter;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
