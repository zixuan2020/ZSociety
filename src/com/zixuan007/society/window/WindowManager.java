
package com.zixuan007.society.window;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindow;
import com.zixuan007.society.SocietyPlugin;
import com.zixuan007.society.domain.Society;
import com.zixuan007.society.utils.SocietyUtils;
import com.zixuan007.society.window.society.ContributionRankingWindow;
import com.zixuan007.society.window.society.ContributionWindow;
import com.zixuan007.society.window.society.CreateSocietyWindow;
import com.zixuan007.society.window.society.LevelRankWindow;
import com.zixuan007.society.window.society.MemberListWindow;
import com.zixuan007.society.window.society.MessageWindow;
import com.zixuan007.society.window.society.PlayerApplyListWindow;
import com.zixuan007.society.window.society.SocietyListWindow;
import com.zixuan007.society.window.society.SocietyWindow;
import com.zixuan007.society.window.society.president.PresidentWindow;
import com.zixuan007.society.window.society.president.RemoveMemberWindow;
import com.zixuan007.society.window.society.president.SetJobWindow;
import com.zixuan007.society.window.title.admin.CreateShopWindow;
import com.zixuan007.society.window.title.admin.RemoveTitleWindow;
import com.zixuan007.society.window.title.admin.SetTitleWindow;
import com.zixuan007.society.window.title.admin.TitleWindow;
import java.util.HashMap;
import java.util.List;

public class WindowManager {
    public static SocietyPlugin societyPlugin = SocietyPlugin.getInstance();
    private HashMap<String, HashMap<String, FormWindow>> forms = new HashMap();

    public WindowManager() {
    }

    public static SocietyWindow getSocietyWindow(Player player) {
        return new SocietyWindow(player);
    }

    public static MessageWindow getMessageWindow(String content, FormWindow formWindow, String buttonName) {
        String title = (String)societyPlugin.getLangConfig().get("消息窗口标题");
        MessageWindow messageWindow = new MessageWindow(title, content);
        messageWindow.setParent(formWindow);
        messageWindow.addButton(new ElementButton(buttonName));
        return messageWindow;
    }

    public static CreateSocietyWindow getCreateSocietyWindow(boolean isBack) {
        CreateSocietyWindow createSocietyWindow = new CreateSocietyWindow();
        createSocietyWindow.setBack(isBack);
        return createSocietyWindow;
    }

    public static ModalWindow getAffrimWindow(String content, String trueButtonName, String falseButtoName) {
        String affrimTitle = (String)societyPlugin.getLangConfig().get("确认窗口标题");
        ModalWindow modalWindow = new ModalWindow(affrimTitle, content, trueButtonName, falseButtoName);
        return modalWindow;
    }

    public static SocietyListWindow getSocietyListWindow(int cuurent, FormWindow formWindow) {
        List<Society> societyList = SocietyUtils.getSocietyList(cuurent);
        String title = (String)societyPlugin.getLangConfig().get("公会列表窗口标题");
        int limit = 10;
        int totalPage = SocietyUtils.getSocietyListTotalPage(cuurent, limit);
        String content = "§a当前第 §b" + cuurent + " §a总页数 §b" + totalPage;
        SocietyListWindow societyListWindow = new SocietyListWindow(title, content, cuurent, totalPage, societyList);
        societyListWindow.setBack(true);
        societyListWindow.setParent(formWindow);
        societyListWindow.setLimit(limit);
        return societyListWindow;
    }

    public static MemberListWindow getMemberListWindow(Society society, List<String> memberList, FormWindow formWindow) {
        MemberListWindow memberListWindow = new MemberListWindow(society, memberList);
        memberListWindow.setBack(true);
        memberListWindow.setParent(formWindow);
        return memberListWindow;
    }

    public static ContributionRankingWindow getContributionRankingWindow(FormWindow formWindow) {
        ContributionRankingWindow contributionRankingWindow = new ContributionRankingWindow();
        contributionRankingWindow.setBack(true);
        contributionRankingWindow.setParent(formWindow);
        return contributionRankingWindow;
    }

    public static LevelRankWindow getLevelRankWindow(FormWindow formWindow) {
        LevelRankWindow levelRankWindow = new LevelRankWindow();
        levelRankWindow.setBack(true);
        levelRankWindow.setParent(formWindow);
        return levelRankWindow;
    }

    public static PresidentWindow getChairmanWindow(long sid) {
        PresidentWindow chairmanWindow = new PresidentWindow(sid);
        return chairmanWindow;
    }

    public static SetJobWindow getSetJobWindow(FormWindow formWindow) {
        SetJobWindow setJobWindow = new SetJobWindow();
        setJobWindow.setBack(true);
        setJobWindow.setParent(formWindow);
        return setJobWindow;
    }

    public static PlayerApplyListWindow getPlayerApplyListWindow(List<String> tempApply, long sid) {
        PlayerApplyListWindow playerApplyListWindow = new PlayerApplyListWindow(tempApply, sid);
        return playerApplyListWindow;
    }

    public static ContributionWindow getContributionWindow(long sid) {
        return new ContributionWindow(sid);
    }

    public static RemoveMemberWindow getRemoveMemberWindow(long sid, List<String> memberList) {
        return new RemoveMemberWindow(sid, memberList);
    }

    public static TitleWindow getTitleWindow() {
        TitleWindow titleWindow = new TitleWindow();
        return titleWindow;
    }

    public static SetTitleWindow getSetTitleWindow(FormWindow formWindow) {
        SetTitleWindow setTitleWindow = new SetTitleWindow();
        setTitleWindow.setBack(true);
        setTitleWindow.setParent(formWindow);
        return setTitleWindow;
    }

    public static RemoveTitleWindow getRemoveTitleWindow(FormWindow formWindow) {
        RemoveTitleWindow removeTitleWindow = new RemoveTitleWindow();
        removeTitleWindow.setBack(true);
        removeTitleWindow.setParent(formWindow);
        return removeTitleWindow;
    }

    public static CreateShopWindow getCreateTitleShopWindow() {
        return new CreateShopWindow();
    }
}