package com.aura.prestojava.controller;

import com.alibaba.fastjson.JSONObject;
import com.aura.prestojava.bean.AgeQueryVO;
import com.aura.prestojava.bean.BrandQueryVO;
import com.aura.prestojava.service.PrestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrestoController {

    @Autowired
    PrestoService prestoService;

    /**
     * 根据品牌查询
     * @return
     */
    @RequestMapping("/brand")
    public String getBrand(){
        List<BrandQueryVO> list = prestoService.selectBrandPrice();
        return JSONObject.toJSONString(list);
    }

    /**
     * 根据年龄查询
     * @return
     */
    @RequestMapping("/age")
    public String getAge(){
        List<AgeQueryVO> list = prestoService.selectAgePrice();
        return JSONObject.toJSONString(list);
    }

}
