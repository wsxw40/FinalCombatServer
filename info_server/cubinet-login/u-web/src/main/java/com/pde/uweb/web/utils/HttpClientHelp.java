package com.pde.uweb.web.utils;

import com.google.common.base.Charsets;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lifengyang
 * Date: 12-11-9
 * Time: 上午10:16
 * To change this template use File | Settings | File Templates.
 */
public class HttpClientHelp {

    public static String requestGet(String host, int port, String path, Map<String, String> http_param) throws IOException {
        List<NameValuePair> qparams = new ArrayList<>();
        for (Map.Entry<String, String> entry : http_param.entrySet()) {
            qparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return requestGet(host, port, path, qparams);
    }

    public static  String requestGet(String host, int port, String path, String key, String value) throws IOException {
        List<NameValuePair> qparams = new ArrayList<>();
        qparams.add(new BasicNameValuePair(key, value));
        return requestGet(host, port, path, qparams);
    }

    public static  String requestGet(String host, int port, String path, List<NameValuePair> qparams) throws IOException {
        String uri = String.format("http://%s:%d%s?%s", host, port, path, URLEncodedUtils.format(qparams, Charsets.UTF_8));
        String response = Request.Get(uri).execute().returnContent().asString();
        return response;
    }

    public static  String requestGet(String fullPath, Map<String, String> http_param) throws IOException {
        List<NameValuePair> qparams = new ArrayList<>();
        for (Map.Entry<String, String> entry : http_param.entrySet()) {
            qparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return requestGet(fullPath, qparams);
    }

    public static  String requestGet(String fullPath, String key, String value) throws IOException {
        List<NameValuePair> qparams = new ArrayList<>();
        qparams.add(new BasicNameValuePair(key, value));
        return requestGet(fullPath, qparams);
    }

    public static  String requestGet(String fullPath, List<NameValuePair> qparams) throws IOException {
        String uri = String.format("%s?%s", fullPath, URLEncodedUtils.format(qparams, Charsets.UTF_8));
        String response = Request.Get(uri).execute().returnContent().asString();
        return response;
    }
}
