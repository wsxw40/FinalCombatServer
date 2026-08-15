package com.pearl.fcw.info;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.jasper.servlet.JspServlet;
import org.apache.tomcat.InstanceManager;
import org.apache.tomcat.SimpleInstanceManager;
import org.eclipse.jetty.annotations.ServletContainerInitializersStarter;
import org.eclipse.jetty.apache.jsp.JettyJasperInitializer;
import org.eclipse.jetty.plus.annotation.ContainerInitializer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.pearl.fcw.info.core.network.SpringBeanExecutableSelector;
import com.pearl.fcw.info.core.persistence.route.cache.CacheSourceRouter;
import com.pearl.fcw.info.core.persistence.utils.ThreadPoolSelector;
import com.pearl.fcw.info.lobby.utils.ConfigurationUtil;
import com.pearl.fcw.info.lobby.utils.ServiceLocator;

public class Bootstrap {

    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    private static final String REQUIRED_RUNTIME_VERSION = "1.0";
    private static final String RUNTIME_VERSION_NAME = "tps.runtime.version";

    private static final String CMD_START = "start";
    private static final String CMD_STOP = "stop";
    private static final String CMD_SHUTDOWN = "SHUTDOWN";

    private static Bootstrap daemon = null;
    private boolean running = false;
    private volatile boolean stopAwait = false;
    private Random random = null;

    private com.pearl.fcw.info.core.network.Server fcwServer = null;
    private org.eclipse.jetty.server.Server jetty = null;
    private FileSystemXmlApplicationContext context = null;
    private ShutdownHookThread shutdownHookThread = null;

    private String webContextPath = "/fcw";
    private String webappPath = "webapp";

    public static void main(String[] args) throws Exception {
        String runtimeVersion = System.getProperty(RUNTIME_VERSION_NAME);
        if (runtimeVersion != null && !REQUIRED_RUNTIME_VERSION.equals(runtimeVersion)) {
            throw new RuntimeException("Needed runtime version is " + REQUIRED_RUNTIME_VERSION + ", but now is " + runtimeVersion);
        }

        System.setProperty("net.spy.log.LoggerImpl", "net.spy.memcached.compat.log.SLF4JLogger");

        if (daemon == null) {
            daemon = new Bootstrap();
        }

        String cmd = null;
        if (args.length > 0) {
            cmd = args[args.length - 1];
        }

        if (cmd == null || CMD_START.equals(cmd)) {
            daemon.start();
        } else if (CMD_STOP.equals(cmd)) {
            daemon.stop();
        } else {
            logger.warn("Bootstrap: Invalid command '{}' received", cmd);
        }
    }

    private void start() throws Exception {

        if (shutdownHookThread == null) {
            shutdownHookThread = new ShutdownHookThread();
            Runtime.getRuntime().addShutdownHook(shutdownHookThread);
        }

        context = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
        ConfigurationUtil.beanFactory = context;

        ThreadPoolSelector threadPoolSelector = new ThreadPoolSelector();
        fcwServer = new com.pearl.fcw.info.core.network.Server(new SpringBeanExecutableSelector(context), threadPoolSelector, ConfigurationUtil.SOCKET_PORT);
//        ServerMessagePusher.getInstance().setServerPusher(new ServerPusherImpl(tpsServer));
        fcwServer.start();

        // start web app server
        startJetty();

        logger.info("fcw, now running");
        String startLogo = "Pearl Digital Entertainment ===================================\n" + "　　　　　　　　　　　★★　　　　　　　　　　　　　　　　　　　　　　　　　　　　　\n" + "　　　　　　　　　　　★★　　　　　　　　　　　　　　　　　　　　　　　　　　　　　\n"
                + "　　　　　　　　　　★★★★★★　　　★★　　★★★　　　　　　★★★　　　　　　　\n" + "　　　　　　　　　★★★★★★★★　★★★★★★★★★　　　　★★★★★★　　　　　\n" + "　　　　　　　　　　　★★　　　　　　　★★★　　★★★　　★★　　　★★★　　　　\n"
                + "　　　　　　　　　　　★★　　　　　　　★★　　　　★★　　★★★★★　★　　　　　\n" + "　　　　　　　　　　　★★　　　　　　　★★　　　　★★　　　★★★★★★　　　　　\n" + "　　　　　　　　　　　★★　　　　　　　★★　　　　★★　　★　　　　★★★　　　　\n"
                + "　　　　　　　　　　　★★　　　★★　　★★★　　★★★　　★★　　　　★★　　　　\n" + "　　　　　　　　　　　★★★★★★★　　★★★★★★★　　　★★★★★★★　　　　　\n" + "　　　　　　　　　　　　★★★★★　　　★★★★★★　　　　★★★★★★　　　　　　\n"
                + "　　　　　　　　　　　　　　　　　　　　★★　　　　　　　　　　　　　　　　　　　　\n" + "　　　　　　　　　　　　　　　　　　　★★★★★　　　　　　　　　　　　　　　　　　\n" + "　　　　　　　　　　　　　　　　　　★★★★★★                               \n"
                + "==================================================== Now Running";
        System.out.println(startLogo);
        running = true;

        if (!stopAwait) {
            await();
        }

        stop();
    }

    private void stop() throws Exception {
        if (running) {
            try {
                jetty.stop();
            } catch (Exception e) {
                logger.error("Stop jetty: ", e);
            }

            try {
                fcwServer.stop();
            } catch (Exception e) {
                logger.error("Stop tps server: ", e);
            }

            try {
                ServiceLocator.scheduledExecutorService.shutdown();
            } catch (Exception e) {
                logger.error("Shutdown schedule task thread pool: ", e);
            }

            try {
                ServiceLocator.asynTakService.shutdown();
            } catch (Exception e) {
                logger.error("Shutdown asyn task thread pool: ", e);
            }

            try {
                ServiceLocator.asynLockService.shutdown();
            } catch (Exception e) {
                logger.error("Shutdown asyn lock thread pool: ", e);
            }

            try {
                CacheSourceRouter.flushAndDestory();
            } catch (Exception e) {
                logger.error("Flush data: ", e);
            }

            try {
                context.close();// flushing orm data
            } catch (Exception e) {
                logger.error("Close spring context: ", e);
            }

            try {
                Runtime.getRuntime().removeShutdownHook(this.shutdownHookThread);
            } catch (Exception e) {

            }

            logger.info("TPS, stopped");
            System.exit(0);

        } else {
            Socket socket = new Socket("127.0.0.1", ConfigurationUtil.WEB_CMD_PORT);
            try {
                socket.getOutputStream().write(CMD_SHUTDOWN.getBytes());
            } finally {
                socket.close();
            }
        }

        running = false;
    }

    private void await() throws Exception {

        ServerSocket awaitSocket = null;
        try {
            awaitSocket = new ServerSocket(ConfigurationUtil.WEB_CMD_PORT, 1);
        } catch (IOException e) {
            logger.error("Go.await: create[localhost:" + ConfigurationUtil.WEB_CMD_PORT + "]: ", e);
            return;
        }

        try {

            // Loop waiting for a connection and a valid command
            while (!stopAwait) {
                ServerSocket serverSocket = awaitSocket;
                if (serverSocket == null) {
                    break;
                }

                // Wait for the next connection
                Socket socket = null;
                StringBuilder command = new StringBuilder();
                try {
                    InputStream stream;
                    try {
                        socket = serverSocket.accept();
                        socket.setSoTimeout(10 * 1000);// Ten seconds
                        stream = socket.getInputStream();
                    } catch (AccessControlException ace) {
                        logger.warn("StandardServer.accept security exception: " + ace.getMessage(), ace);
                        continue;
                    } catch (IOException e) {
                        if (stopAwait) {
                            // Wait was aborted with socket.close()
                            break;
                        }
                        logger.error("StandardServer.await: accept: ", e);
                        break;
                    }

                    // Read a set of characters from the socket
                    int expected = 1024;// Cut off to avoid DoS attack
                    while (expected < CMD_SHUTDOWN.length()) {
                        if (random == null) {
                            random = new Random();
                        }
                        expected += random.nextInt() % 1024;
                    }
                    while (expected > 0) {
                        int ch = -1;
                        try {
                            ch = stream.read();
                        } catch (IOException e) {
                            logger.warn("StandardServer.await: read: ", e);
                            ch = -1;
                        }
                        if (ch < 32) {
                            break;
                        }
                        command.append((char) ch);
                        expected--;
                    }
                } finally {
                    // Close the socket now that we are done with it
                    try {
                        if (socket != null) {
                            socket.close();
                        }
                    } catch (IOException e) {
                        // Ignore
                    }
                }

                // Match against our command string
                String cmd = command.toString();
                if (CMD_SHUTDOWN.equals(cmd)) {
                    break;
                } else {
                    logger.warn("StandardServer.await: Invalid command '" + cmd + "' received");
                }
            }
        } finally {
            ServerSocket serverSocket = awaitSocket;
            awaitSocket = null;

            // Close the server socket and return
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    // Ignore
                }
            }
        }

    }

    private class ShutdownHookThread extends Thread {
        @Override
        public void run() {
            try {
                Bootstrap.this.stop();
            } catch (Exception e) {
                logger.error("ShutdownHookThread: ", e);
            }
        }
    }

    private void startJetty() throws Exception {
        Server jettyServer = new Server(ConfigurationUtil.WEB_APP_PORT);

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

        // Set Classloader of Context to be sane (needed for JSTL)
        // JSP requires a non-System classloader, this simply wraps the
        // embedded System classloader in a way that makes it suitable
        // for JSP to use
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

        jetty = jettyServer;
        jetty.start();

        // logger.info(jetty.dump());
    }
}
