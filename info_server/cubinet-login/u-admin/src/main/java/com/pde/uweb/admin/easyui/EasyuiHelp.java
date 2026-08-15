package com.pde.uweb.admin.easyui;

import com.pde.uweb.admin.easyui.node.GridNode;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lifengyang
 * Date: 12-8-26
 * Time: 上午1:03
 * To change this template use File | Settings | File Templates.
 */
public class EasyuiHelp {
    public static ModelAndView fillGridData(final Collection<? extends Object> rows) {
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.addObject("rows", rows);
        modelAndView.addObject("total", rows.size());
        return modelAndView;
    }

    public static ModelAndView fillGridDataByMapEntry(final Collection<Map<String, String>> rows) {
        return fillGridData(rows);
    }

    public static ModelAndView fillGridDataByGridNodeList(final List<? extends GridNode> rows) {
        return fillGridData(rows);
    }
}
