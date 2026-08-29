package com.pearl.o2o.manager;


import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import org.lilystudio.smarty4j.Engine;
import org.lilystudio.smarty4j.Context;
import org.lilystudio.smarty4j.TemplateException;

import com.pearl.o2o.utils.ConfigurationUtil;

import java.io.StringWriter;

public class SmartyManager{
    private static final Logger log = Logger.getLogger(SmartyManager.class);
    
    public SmartyManager(){
    }    
    
    
    private static Engine e;
    
    protected synchronized static Engine getEngine(){
        if (e == null){
            e = new Engine();
            //e.setTemplatePath(System.getProperty("catalina.base")+e.getTemplatePath());
            e.setTemplatePath(ConfigurationUtil.TEMPLATE_PATH);
            log.debug("Smarty Template Path: "+ e.getTemplatePath());
        }

        return e;
    }    
    
    public static Engine getEngineInstance(){
    	if(e == null){
    		getEngine();
    	}
    	
    	return e;
    }




    public String getEncodedBody(String template, Context context) throws TemplateException
    {
        //Check to see if the template exists
        if (template == null)
            throw new TemplateException("Please identify a template.");

        try
        {
            StringWriter writer = new StringWriter();
            SmartyManager.getEngineInstance().getTemplate(template).merge(context, writer);
            return writer.toString();
        }
        catch (Exception e)
        {
            log.error("Exception getting message body from Smarty: " + e, e);
            return "An error occurred whilst rendering this message.  Please contact the administrators, and inform them of this bug.\n\nDetails:\n-------\n" + ExceptionUtils.getFullStackTrace(e);
        }
    }
}
