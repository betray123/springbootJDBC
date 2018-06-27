package com.zk.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by zk on 18/6/26.
 */
@Controller
public class testController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/test")
    @ResponseBody
    public List<Map<String, Object>> test(){
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM department");
        return maps;
    }
}
