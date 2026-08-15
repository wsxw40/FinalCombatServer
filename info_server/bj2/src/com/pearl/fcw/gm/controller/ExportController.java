package com.pearl.fcw.gm.controller;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pearl.fcw.core.controller.DmController;
import com.pearl.fcw.core.controller.UrlCode;
import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.service.FileService;

/**
 * GM操作系统表导入导出
 */
@Controller
@RequestMapping(value = "fcw/gm")
@UrlCode(25)
public class ExportController extends DmController {
    @Resource
    private FileService fileService;

    @RequestMapping("exportedTables")
    public @ResponseBody Object getExportTables() throws Exception {
        return fileService.getExportTables();
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("export")
    public @ResponseBody Object exportFile(@RequestParam("t") String t) throws Exception {
        Class<?> clazz = Class.forName(t);
        File file = fileService.exportFile((Class<? extends DmModel>) clazz);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", clazz.getSimpleName().replaceAll("^W", "") + ".xlsx");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

}
