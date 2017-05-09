package com.xay.MySQL.DO;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/9.
 */
public class CityDO {
    private Integer id;
    private String name;
    private Integer parent_id;

    public CityDO(Integer id, Integer code, String name, Integer parent_id, String first_letter, Integer level) {
        this.id = id;
        this.name = name;
        this.parent_id = parent_id;
    }

    public CityDO() {

    }

    public Integer getCity_id() {
        return id;
    }

    public void setCity_id(Integer id) {
        this.id = id;
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

}
