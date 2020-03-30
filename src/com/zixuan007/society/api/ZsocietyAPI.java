package com.zixuan007.society.api;

import com.zixuan007.society.SocietyPlugin;
import com.zixuan007.society.domain.Society;
import com.zixuan007.society.utils.SocietyUtils;

public class ZsocietyAPI {

    /**
     * 获取公会名称
     * @param playerNmae 玩家名字
     * @return
     */
    public static String getSocietyName(String playerNmae) {
        Society society = SocietyUtils.getSocietyByPlayerName(playerNmae);
        return society != null ? society.getSocietyName() : "无公会";
    }

    /**
     * 获取职位名称
     * @param playerName 玩家名字
     * @param society 玩家所在的公会
     * @return
     */
    public static String getPostName(String playerName, Society society) {
        String postByName = null;
        if (society != null) {
            postByName = SocietyUtils.getPostByName(playerName, society);
        }

        return postByName != null ? postByName : "无职位";
    }

    /**
     * 获取公会等级
     * @param society 玩家当前所存在的公会
     * @return
     */
    public static int getSocietyGrade(Society society) {
        return society != null ? society.getGrade() : -1;
    }

    /**
     * 获取职位标题
     * @param playerName 玩家名字
     * @return
     */
    public static String getTitle(String playerName) {
        String title = (String)SocietyPlugin.getInstance().getTitleConfig().get(playerName);
        return title != null ? title : "无称号";
    }
    
}