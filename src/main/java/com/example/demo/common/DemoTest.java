package com.example.demo.common;

import com.example.demo.entity.User;

import java.io.Serializable;
import org.mvel2.MVEL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoTest {
    public static void main(String[] args) {
        String str = "D00000023/D00000024*100";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data1_code", 30);
        Serializable expression = MVEL.compileExpression(str.replaceAll("\\s*", ""));
        BigDecimal result = MVEL.executeExpression(expression, map, BigDecimal.class);
        System.out.println(result);

    }
}
