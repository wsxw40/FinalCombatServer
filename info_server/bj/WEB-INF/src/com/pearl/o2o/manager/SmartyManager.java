package com.pearl.o2o.manager;

import java.io.StringWriter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.lilystudio.smarty4j.Context;
import org.lilystudio.smarty4j.Engine;
import org.lilystudio.smarty4j.Template;
import org.lilystudio.smarty4j.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.ConfigurationUtil;

public class SmartyManager{
    private static final Logger LOG = LoggerFactory.getLogger(SmartyManager.class);
    
    private volatile Engine e;
    private final String templatePath;
    private final Lock lock = new ReentrantLock();
    
    
    public SmartyManager(String templatePath){
    	this.templatePath = templatePath;
    	initliazeEngine(templatePath);
    }
    
    public synchronized  void initliazeEngine(String templatePath){
            e = new Engine();
            //e.setTemplatePath(ConfigurationUtil.TEMPLATE_PATH);
            e.setTemplatePath(templatePath);
            LOG.debug("Smarty Template Path: "+ e.getTemplatePath());

            return ;
    }    

    public String getEncodedBody(String template, Context context) throws TemplateException{
        if (template == null){
            throw new TemplateException("Please identify a template.");
        }

        try {
            StringWriter writer = new StringWriter();
            Template tt = e.getTemplate(template);
            if(ConfigurationUtil.SWITCH_DYNAMIC_TEMPLATE.getIsOn() && tt.isUpdated()){
            	//only the first thread can initliaze the engine
        		if(lock.tryLock()){
        			try{
        				LOG.debug("reload template engine!");
        				initliazeEngine(templatePath);
        			}finally{
        				lock.unlock();
        			}
            	}
        		tt = e.getTemplate(template);
            }
            
            tt.merge(context, writer);
            return writer.toString();
        } catch (Exception e) {
            LOG.error("Exception getting message body from Smarty: " + e, e);
            return "An error occurred whilst rendering this message.  Please contact the administrators, and inform them of this bug.\n\nDetails:\n-------\n" + ExceptionUtils.getFullStackTrace(e);
        }
    }
}
