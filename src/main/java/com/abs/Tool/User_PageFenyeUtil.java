package com.abs.Tool;

import java.util.HashMap;
import java.util.Map;

public class User_PageFenyeUtil {
    /**分页工具
     * pageSize 为 0 即查询全部
     * @param page int  第几页
     * @param pageSize int 每页数据量
     * @return
     */
    public Map<String,Integer> Fenye(int page,int pageSize){
        HashMap<String, Integer> map = new HashMap<>();
        int begin=(page-1)*pageSize;
        if (begin<0){begin=0;}
        map.put("begin",begin);
        map.put("pageSize",pageSize);
        return map;
    }
}
