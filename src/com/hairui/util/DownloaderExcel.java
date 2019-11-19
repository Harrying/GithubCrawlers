package com.hairui.util;
import com.hairui.Repositories;

import java.util.*;


/**
 * 创建excel，写入数据，然后下载到本地
 */
public class DownloaderExcel {

    public void excel(ArrayList<Repositories> list){
        Map<String, ArrayList<String>> memberMap = getMember(list);
        String[] strArray = excelTitle();
        ExcelUtil.createExcel(memberMap, strArray);
    }
    /**
     * 初始化数据，将会被存储到excel表格中
     *
     * @return
     * @throws Exception
     */
    private static Map<String, ArrayList<String>> getMember(ArrayList<Repositories> list) {
        Map<String, ArrayList<String>> map = new HashMap<>();
        try {
            for (int i = 0; i < list.size(); i++) {
                ArrayList<String> members = new ArrayList<String>();
                members.add(list.get(i).getUsername() + "");
                members.add(list.get(i).getReposName());
                members.add(list.get(i).getLanguage() + "");
                members.add(list.get(i).getStarsCount() + "");
                members.add(list.get(i).getBranchesCount() + "");
                map.put(list.get(i).getUsername() + "", members);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 创建excel title
     */
    public static String[] excelTitle() {
        String[] strArray = { "用户名", "资源名", "语言","星数", "分支数" };
        return strArray;
    }
}
