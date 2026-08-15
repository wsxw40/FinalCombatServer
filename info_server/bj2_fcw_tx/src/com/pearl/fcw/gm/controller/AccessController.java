package com.pearl.fcw.gm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.pearl.fcw.core.controller.DmController;
import com.pearl.fcw.gm.pojo.enums.ItemSubType;
import com.pearl.fcw.gm.pojo.enums.ItemType;
import com.pearl.fcw.gm.service.WGmUserService;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.JsonUtil;
import com.pearl.fcw.utils.StringUtil;
import com.pearl.o2o.dao.impl.nonjoin.GmUserDao;
import com.pearl.o2o.pojo.GmUser;
import com.pearl.o2o.utils.PasswordUtil;


/**
 * GM初始页面以及登录登出
 */
@Controller
@RequestMapping("fcw")
public class AccessController extends DmController {
    @Resource
    private GmUserDao gmUserDao;
    @Resource
    private WGmUserService wGmUserService;

    /**
     * 已登录用户到GM页面，未登录用户到登录页面
     * @param request
     * @return
     */
    @RequestMapping("")
    public ModelAndView gmLogin(HttpServletRequest request) {
        if (null == request.getSession().getAttribute(Constants.GM_SESSION_KEY)) {
            return new ModelAndView("login");
        }

        Map<String, Object> data = new HashMap<>();
        List<Integer> itemTypeList = Stream.of(ItemType.values()).map(ItemType::getValue).collect(Collectors.toList());
        data.put("itemTypeList", itemTypeList);
        return new ModelAndView("gm/index", data);
    }

    /**
     * 登录
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("login")
    public void login(HttpServletRequest request, HttpServletResponse response, GmUser gmUser) throws Exception {
        List<GmUser> list = gmUserDao.getLoginGmUser(gmUser.getUserName(), PasswordUtil.encrypt(gmUser.getPassword()));
        if (null != list && !list.isEmpty()) {
            //用户权限
            request.getSession().setAttribute(Constants.GM_SESSION_KEY, list.get(0));
            request.getSession().setAttribute(Constants.GM_SESSION_PRIVILEGE, wGmUserService.findPrivilegeList(list.get(0).getId()));
            //系统物品类型
            Map<Integer, String> itemTypes = Stream.of(ItemType.values()).collect(Collectors.toMap(ItemType::getValue, p -> {
                return StringUtil.getI18nWord("game.sysItem.type." + p.getValue());
            }));
            request.getSession().setAttribute(Constants.ITEM_TYPES_KEY, itemTypes);
            Map<Integer, Map<Object, Object>> itemSubTypes = new HashMap<>();
            ItemSubType.values().forEach((k, v) -> {
                Map<Object, Object> map = v.stream().collect(Collectors.toMap(p -> p, p -> StringUtil.getI18nWord("game.sysItem.subType." + k + "." + p)));
                itemSubTypes.put(k, map);
            });
            request.getSession().setAttribute(Constants.ITEM_SUB_TYPES_KEY, itemSubTypes);
            response.sendRedirect(request.getContextPath() + "/fcw/");
        } else {
            throw new Exception();
        }
    }

    /**
     * 登出
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().removeAttribute(Constants.GM_SESSION_KEY);
        request.getSession().removeAttribute(Constants.GM_SESSION_PRIVILEGE);
        response.sendRedirect(request.getContextPath() + "/fcw/");
    }

    /**
     * GM修改密码
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("gmResetPwd")
    public @ResponseBody Object gmResetPwd(HttpServletRequest request, HttpServletResponse response, @RequestParam("pwd") String pwd) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        gmUser.setPassword(PasswordUtil.encrypt(pwd));
        gmUserDao.update(gmUser);
        gmUser = gmUserDao.getLoginGmUser(gmUser.getUserName(), gmUser.getPassword()).get(0);
        request.getSession().setAttribute(Constants.GM_SESSION_KEY, gmUser);
        JsonObject jo = new JsonObject();
        jo.addProperty("msg", StringUtil.getI18nWord("web.gm.hidden.alert.resetPwdSuccess"));
        return jo.toString();
    }

    /**
     * 权限不足
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "roleError")
    public ModelAndView roleError(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("roleError");
    }

    /**
     * 测试JSON输出。输出实体不使用produces = "text/json;charset=UTF-8"
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "gm/user/get")
    public @ResponseBody Object gmUserGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, String> map = new HashMap<>();
            for (int j = 0; j < 2; j++) {
                map.put("var" + i + j, "中var" + j);
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 测试JSON输出。输出字符串使用produces = "text/json;charset=UTF-8"，避免中文乱码
     * @param request
     * @return
     */
    @RequestMapping(value = "testJson", produces = "text/json;charset=UTF-8")
    public @ResponseBody String testJson(HttpServletRequest request) {
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, String> map = new HashMap<>();
            for (int j = 0; j < 2; j++) {
                map.put("var" + i + j, "中var" + j);
            }
            list.add(map);
        }
        return JsonUtil.writeAsString(list);
    }

    @RequestMapping("test")
    public @ResponseBody Object test() throws Exception {
        //        WSysItem w1 = wSysItemService.findEntity(295);
        //        String str = ZipUtil.zipJson(w1);
        //        WSysItem w2 = ZipUtil.unzipJson(str, WSysItem.class);
        //        w2.setBackBoostPlus(15);
        //
        //        List<?> list = CompareUtil.compareDmModel(w1, w2);
        //        System.out.println(list);

        //        FileWriter fw = new FileWriter("d:/0.txt", true);
        ////        BufferedWriter writer = new BufferedWriter(fw);
        ////        writer.write(str);
        //                fw.append(str);
        //        fw.append("\r\n");
        ////        writer.close();
        //        fw.close();


        return null;
    }

}
