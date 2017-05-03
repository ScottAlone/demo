package com.xay.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/3.
 */
@RestController
public class GetRegion {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/getCity", method = RequestMethod.GET)
    public List<Map<String, Object>> getCity(){
        String sql = "select  city_id, name, first_letter from city where parent_id=0";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        list.forEach(m -> m.entrySet().forEach(System.out::println));
        return list;
    }

    @RequestMapping(value = "/getCounty", method = RequestMethod.GET)
    public List<Map<String, Object>> getCounty(@RequestParam("parentId") int id){
        String sql = "select city_id, name, first_letter from city where parent_id=" + id;
        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
        list.forEach(m -> m.entrySet().forEach(System.out::println));
        return list;
    }
}
