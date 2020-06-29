package com.zixuan007.society.window.title.admin;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.utils.Config;
import com.zixuan007.society.SocietyPlugin;
import com.zixuan007.society.domain.Lang;
import com.zixuan007.society.utils.PluginUtils;
import com.zixuan007.society.utils.SocietyUtils;
import com.zixuan007.society.utils.TitleUtils;
import com.zixuan007.society.window.CustomWindow;
import com.zixuan007.society.window.WindowLoader;
import com.zixuan007.society.window.WindowManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zixuan007
 */
public class RemoveTitleWindow extends CustomWindow implements WindowLoader {
    public RemoveTitleWindow() {
        super(PluginUtils.getWindowConfigInfo("removeTitleWindow.title"));

    }

    @Override
    public FormWindow init(Object... objects) {
        getElements().clear();
        addElement(new ElementInput("", "玩家名字"));
        addElement(new ElementInput("", "需要移除的称号(匹配方式移除)"));
        return this;
    }


    @Override
    public void onClick(FormResponseCustom response, Player player) {
        String playerName = response.getInputResponse(0);
        String title = response.getInputResponse(1);
        Config titleConfig = SocietyPlugin.getInstance().getTitleConfig();
        ArrayList<String> titleList = TitleUtils.titleList.get(playerName);
        if (titleList == null || titleList.size() <= 0) {
            player.showFormWindow(WindowManager.getMessageWindow("§c此玩家还没有称号", this, "返回上级"));
            return;
        }
        ArrayList<String> removeTitle = new ArrayList<>();
        for (String s : titleList) {
            if (s.contains(title)) {
                removeTitle.add(s);
            }
        }
        if (removeTitle.size() <= 0) {
            player.showFormWindow(WindowManager.getMessageWindow("§c此玩家称号不存在", this, "返回上级"));
            return;
        }
        titleList.removeAll(removeTitle);
        titleConfig.set(playerName, titleList);
        titleConfig.save();

        player.showFormWindow(WindowManager.getMessageWindow("§a成功移除玩家所匹配的称号!", this, "返回上级"));

        if (PluginUtils.isOnlineByName(playerName)) {
            Player player1 = Server.getInstance().getPlayer(playerName);
            player1.showFormWindow(WindowManager.getMessageWindow("§c你的部分称号已经被管理员移除", null, "关闭窗口"), new Random().nextInt(300));

        }


    }


}


