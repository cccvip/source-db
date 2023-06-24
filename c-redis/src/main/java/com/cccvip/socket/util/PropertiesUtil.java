package com.cccvip.socket.util;

import io.netty.util.internal.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/11
 */
public class PropertiesUtil {

    private PropertiesUtil() {
    }

    private static PropertiesUtil Instance = new PropertiesUtil();

    public static final PropertiesUtil getInstance() {
        return Instance;
    }

    public static Properties getProParams() {
        return PropertiesUtil.getInstance().getProParams("redis-conf.properties");
    }

    public static String getNodeAddress() {
        String address = getProParams().getProperty("ip");
        if (StringUtil.isNullOrEmpty(address)) {
            return "127.0.0.1";
        }
        return address;
    }

    public static Boolean getTcpKeepAlive() {
        String appendOnly = getProParams().getProperty("tcp_keepalive");
        if (!StringUtil.isNullOrEmpty(appendOnly)) {
            return appendOnly.trim().equals("yes");
        }
        return false;
    }

    public static int getNodePort() {

        Integer port = 6379;

        try {
            String strPort = getProParams().getProperty("port");

            port = Integer.parseInt(strPort);

        } catch (Exception e) {
            return 6379;
        }

        if (port <= 0 || port > 60000) {
            return 6379;
        }

        return port;
    }

    public static String getAofPath() {
        String aofDataDir =getProParams().getProperty("aof_data_dir");
        if(StringUtil.isNullOrEmpty(aofDataDir)){
            return "./aof_data_dir/";
        }
        return  aofDataDir;
    }

    private Properties getProParams(String propertiesName) {

        InputStream is = getClass().getClassLoader().getResourceAsStream(propertiesName);

        Properties prop = new Properties();

        try {
            prop.load(is);
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return prop;
    }

    public static void main(String[] args) {
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        InputStream inputStream = propertiesUtil.getClass().getClassLoader().getResourceAsStream("redis-conf.properties");
        Properties prop = new Properties();

        try {
            prop.load(inputStream);
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String port = prop.getProperty("port");

        System.out.println(port);
    }

}
