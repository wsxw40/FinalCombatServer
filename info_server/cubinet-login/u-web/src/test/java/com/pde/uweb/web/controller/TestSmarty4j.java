package com.pde.uweb.web.controller;

import org.apache.http.HttpRequest;
import org.lilystudio.smarty4j.Context;
import org.lilystudio.smarty4j.Engine;
import org.lilystudio.smarty4j.Template;
import org.lilystudio.util.StringWriter;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: lifengyang
 * Date: 12-9-24
 * Time: 下午5:45
 * To change this template use File | Settings | File Templates.
 */
public class TestSmarty4j {
    private String name = "abc";

    public String test(String value) {
        return value;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) throws Exception {
        Engine engine = new Engine();
        engine.setDebug(true);
        engine.setEncoding("utf-8");
        engine.setLeftDelimiter("#");
        engine.setRightDelimiter("#");
        engine.setTemplatePath("D:\\log\\");

        Template template = engine.getTemplate("Team.st");
        Context context = new Context();
        context.set("aaa", new TestSmarty4j());
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("ttt", "tttt");
        context.set("aaa", new TestSmarty4j());
        context.set("session", session);

        session.getAttribute("");

        StringWriter stringWriter = new StringWriter();
        template.merge(context, stringWriter);
        System.out.println(stringWriter.toString());
    }
}
