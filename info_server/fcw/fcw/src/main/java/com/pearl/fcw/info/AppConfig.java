/*package com.pearl.fcw.info;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.jasper.servlet.JspServlet;
import org.apache.tomcat.InstanceManager;
import org.apache.tomcat.SimpleInstanceManager;
import org.eclipse.jetty.annotations.ServletContainerInitializersStarter;
import org.eclipse.jetty.apache.jsp.JettyJasperInitializer;
import org.eclipse.jetty.plus.annotation.ContainerInitializer;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.pearl.fcw.info.core.network.Server;
import com.pearl.fcw.info.core.network.SpringBeanExecutableSelector;
import com.pearl.fcw.info.core.persistence.utils.ThreadPoolSelector;
import com.pearl.fcw.info.lobby.utils.ConfigurationUtil;

@Configuration
@PropertySource("file:./config/application.properties")
@ImportResource("classpath:applicationContext.xml")
@EnableScheduling
public class AppConfig implements SchedulingConfigurer {

    private String webContextPath = "/fcw";
    private String webappPath = "webapp";

    @Autowired
    private ApplicationContext context;
    @Autowired
    private Environment env;

    @Bean(destroyMethod = "stop")
    public ThreadPoolSelector threadPoolSelector() throws Exception {
        return new ThreadPoolSelector();
    }


    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server server() throws Exception {
        ConfigurationUtil.beanFactory = context;
    //        Integer port = env.getRequiredProperty("port.rpc.socket", Integer.class);
        return new Server(new SpringBeanExecutableSelector(context), threadPoolSelector(), ConfigurationUtil.SOCKET_PORT);
    }

    @Bean(destroyMethod = "stop")
    public org.eclipse.jetty.server.Server jettyServer() throws Exception {
//        Integer webPort = env.getRequiredProperty("port.web.http", Integer.class);
        org.eclipse.jetty.server.Server jettyServer = new org.eclipse.jetty.server.Server(ConfigurationUtil.WEB_APP_PORT);

        // Set JSP to use Standard JavaC always
        System.setProperty("org.apache.jasper.compiler.disablejsr199", "false");

        // Setup the basic application "context" for this application at "/"
        // This is also known as the handler tree (in jetty speak)
        WebAppContext context = new WebAppContext();
        context.setContextPath(webContextPath);
        context.setWar(this.getClass().getClassLoader().getResource(webappPath).getPath());
        context.setMaxFormContentSize(1000000);
        context.setAttribute("javax.servlet.context.tempdir", new File(context.getWar() + "/WEB-INF/work"));
        context.setAttribute(InstanceManager.class.getName(), new SimpleInstanceManager());
        jettyServer.setHandler(context);

        // Ensure the jsp engine is initialized correctly
        JettyJasperInitializer sci = new JettyJasperInitializer();
        ServletContainerInitializersStarter sciStarter = new ServletContainerInitializersStarter(context);
        ContainerInitializer initializer = new ContainerInitializer(sci, null);
        List<ContainerInitializer> initializers = new ArrayList<ContainerInitializer>();
        initializers.add(initializer);

        context.setAttribute("org.eclipse.jetty.containerInitializers", initializers);
        context.addBean(sciStarter, true);

        ClassLoader jspClassLoader = new URLClassLoader(new URL[0], this.getClass().getClassLoader());
        context.setClassLoader(jspClassLoader);

        // Add JSP Servlet (must be named "jsp")
        ServletHolder holderJsp = new ServletHolder("jsp", JspServlet.class);
        holderJsp.setInitOrder(0);
        holderJsp.setInitParameter("logVerbosityLevel", "DEBUG");
        holderJsp.setInitParameter("fork", "false");
        holderJsp.setInitParameter("xpoweredBy", "false");
        holderJsp.setInitParameter("compilerTargetVM", "1.8");
        holderJsp.setInitParameter("compilerSourceVM", "1.8");
        holderJsp.setInitParameter("keepgenerated", "true");
        context.addServlet(holderJsp, "*.jsp");
        jettyServer.start();
        return jettyServer;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);
        taskRegistrar.setScheduler(ses);
    }
}
*/