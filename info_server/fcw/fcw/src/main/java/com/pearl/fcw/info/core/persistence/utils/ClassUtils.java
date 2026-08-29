package com.pearl.fcw.info.core.persistence.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *
 */
public class ClassUtils {

    private static final String PROTOCOL_FILE = "file";
    private static final String PROTOCOL_JAR = "jar";

    private static final String PREFIX_FILE = "file:";

    private static final String JAR_URL_SEPERATOR = "!/";
    private static final String CLASS_FILE = ".class";

    public static List<Class<?>> getClasses(String packageName) {
        return getClasses(packageName, ClassUtils.class.getClassLoader());
    }

    public static List<Class<?>> getClasses(String packageName, ClassLoader classLoader) {
        List<Class<?>> list = new ArrayList<Class<?>>();

        Enumeration<URL> en = null;
        try {
            en = classLoader.getResources(nameToPath(packageName));
        } catch (IOException e) {
            throw new IllegalArgumentException("无效的包名:" + packageName);
        }

        while (en.hasMoreElements()) {
            URL url = en.nextElement();
            if (PROTOCOL_FILE.equals(url.getProtocol())) {
                File root = new File(url.getFile());
                findInDirectory(list, root, root, packageName, classLoader);
            } else if (PROTOCOL_JAR.equals(url.getProtocol())) {
                findInJar(list, getJarFile(url), packageName, classLoader);
            }
        }

        return list;
    }

    public static File getJarFile(URL url) {
        String file = url.getFile();
        if (file.startsWith(PREFIX_FILE)) {
            file = file.substring(PREFIX_FILE.length());
        }
        int end = file.indexOf(JAR_URL_SEPERATOR);
        if (end != -1) {
            file = file.substring(0, end);
        }
        return new File(file);
    }

    public static List<Method> getSortedMethodList(Class<?> clazz){
        List<Method> methods = Arrays.asList(clazz.getDeclaredMethods());
        Collections.sort(methods, new Comparator<Method>() {
            @Override
            public int compare(Method m1, Method m2) {
                String lower1 = m1.toString().toLowerCase();
                String lower2 = m2.toString().toLowerCase();
                return lower1.compareTo(lower2);
            }
        });

        return methods;
    }

    private static void findInDirectory(List<Class<?>> results, File rootDir, File dir, String packageName, ClassLoader classLoader) {
        File[] files = dir.listFiles();
        String rootPath = rootDir.getPath();
        for (File file : files)
            if (file.isFile()) {
                String classFileName = file.getPath();
                if (classFileName.endsWith(CLASS_FILE)) {
                    String className = classFileName.substring(rootPath.length() - packageName.length(), classFileName.length() - CLASS_FILE.length());
                    try {
                        Class<?> clz = classLoader.loadClass(pathToName(className));
                        results.add(clz);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else if (file.isDirectory()) {
                findInDirectory(results, rootDir, file, packageName, classLoader);
            }
    }

    private static void findInJar(List<Class<?>> results, File file, String packageName, ClassLoader classLoader) {
        JarFile jarFile = null;
        String packagePath = nameToPath(packageName) + "/";
        try {
            jarFile = new JarFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (jarFile != null) {
                try {
                    jarFile.close();
                } catch (IOException e) {
                }
            }
        }

        Enumeration<JarEntry> en = jarFile.entries();
        while (en.hasMoreElements()) {
            JarEntry je = en.nextElement();
            String name = je.getName();
            if (name.startsWith(packagePath) && name.endsWith(CLASS_FILE)) {
                String className = name.substring(0, name.length() - CLASS_FILE.length());
                try {
                    Class<?> clz = classLoader.loadClass(pathToName(className));
                    results.add(clz);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    /**
     * 将类名的字符串形式转换为路径表现形式
     * @param className 类名
     * @return 路径的字符串
     */
    private static String nameToPath(String className) {
        return className.replace('.', '/');
    }

    /**
     * 将路径转换为类的字符串表现形式
     * @param path
     * @return 类名的字符串形式
     */
    private static String pathToName(String path) {
        return path.replace('/', '.').replace('\\', '.');
    }

}
