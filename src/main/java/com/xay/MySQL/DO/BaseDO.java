package com.xay.MySQL.DO;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */

public class BaseDO implements Serializable{
    protected Integer id;
    protected Timestamp create_time;
    protected Timestamp update_time;

    public BaseDO() {
    }

    public BaseDO(Integer id, Timestamp create_time, Timestamp update_time) {
        this.id = id;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }
}
