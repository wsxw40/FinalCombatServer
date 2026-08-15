package com.pearl.fcw.gm.controller;

import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.pearl.fcw.core.controller.DmController;
import com.pearl.fcw.core.controller.UrlCode;
import com.pearl.fcw.core.service.FileService;
import com.pearl.fcw.utils.Constants;
import com.pearl.o2o.pojo.GmUser;

/**
 * GM操作系统表导入导出
 */
@Controller
@RequestMapping(value = "fcw/gm")
@UrlCode(24)
public class ImportController extends DmController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private FileService fileService;

    @RequestMapping("import")
    public @ResponseBody Object importFile(HttpServletRequest request,    @RequestParam("file") MultipartFile file) {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        try {
            fileService.importFile(file,gmUser);
            return "";
        } catch (Exception e) {
            logger.error(e.toString());
            Stream.of(e.getStackTrace()).forEach(p -> logger.error(p.toString()));
            JsonObject jo = new JsonObject();
            jo.addProperty("error", e.toString());
            return jo.toString();
        }
    }
}
