package com.xay.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/4.
 */
@RestController
public class Account {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private List<Map<String, Object>> queryForAccount(String username, int type){
        String sql;
        if (type == 0){
            sql = "SELECT * FROM guides WHERE username=?";
        }else if (type == 1){
            sql = "SELECT * FROM customers WHERE username=?";
        }else throw new IllegalArgumentException("非法的用户类型");

        return jdbcTemplate.queryForList(sql, username);
    }

    /**
     * 用户注册
     * @param account
     * @return BaseResult
     */
    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public BaseResult register(@RequestBody WebAccount account){
        int type = account.getType();
        String name = account.getName();
        String username = account.getUsername();
        String password = account.getPassword();
        String sql;
        if (queryForAccount(username, type).size() == 0){
            if (type == 0){
                sql = "INSERT INTO guides(name, username, password) VALUES(?, ?, ?)";
            }else if (type == 1){
                sql = "INSERT INTO customers(name, username, password) VALUES(?, ?, ?)";
            }else throw new IllegalArgumentException("非法的用户类型");
            jdbcTemplate.update(sql, name, username, password);
            return new BaseResult();
        }else return new BaseResult(500, "用户已存在");

    }

    /**
     * 用户登陆
     * @param account
     * @return BaseResult
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResult login(@RequestBody WebAccount account){
        int type = account.getType();
        String username = account.getUsername();
        String password = account.getPassword();

        List<Map<String, Object>> list = queryForAccount(username, type);
        if (list.size() == 0){
            return new BaseResult(500, "用户不存在");
        }
        return list.get(0).get("password").equals(password)
                ?new BaseResult():new BaseResult(500, "密码错误");
    }

    /**
     * 用户登陆
     * @param account
     * @return BaseResult
     */
    @RequestMapping(value = "/accounts", method = RequestMethod.PATCH)
    public BaseResult update(@RequestBody WebAccount account){
        int type = account.getType();
        String name = account.getName();
        String username = account.getUsername();
        String password = account.getPassword();
        String sql;

        if (queryForAccount(username, type).size() == 1){
            if (name == null){
                name = queryForAccount(username, type).get(0).get("name").toString();
            }
            if (type == 0){
                sql = "UPDATE guides SET name=?, password=?, guides.update_time=CURRENT_TIMESTAMP WHERE username=?";
            }else if (type == 1){
                sql = "UPDATE customers SET name=?, password=?, customers.update_time=CURRENT_TIMESTAMP WHERE username=?";
            }else throw new IllegalArgumentException("非法的用户类型");
            jdbcTemplate.update(sql, name, password, username);
            return new BaseResult();
        }else return new BaseResult(500, "用户不存在");
    }
}
