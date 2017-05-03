package com.xay.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/3.
 */
@RestController
public class MySQL {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/getUsers")
    public List<Map<String, Object>> getDbType(){
        String sql = "select * from authors";
        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
        for (Map<String, Object> map : list) {
            Set<Map.Entry<String, Object>> entries = map.entrySet( );
            for (Map.Entry<String, Object> entry : entries) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                System.out.println(key + ":" + value);
            }
        }
        return list;
    }
}
