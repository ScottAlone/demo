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

    /**
     * 查询省和直辖市
     * @return List
     */
    @RequestMapping(value = "/getCity", method = RequestMethod.GET)
    public List<Map<String, Object>> getCity(){
        String sql = "SELECT  city_id, name, first_letter FROM city WHERE parent_id=0";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 查询市辖区
     * @param parentId
     * @return List
     */
    @RequestMapping(value = "/getCounty", method = RequestMethod.GET)
    public List<Map<String, Object>> getCounty(@RequestParam("parentId") int parentId){
        String sql = "SELECT city_id, name, first_letter FROM city WHERE parent_id=" + parentId;
        return jdbcTemplate.queryForList(sql);
    }
}
