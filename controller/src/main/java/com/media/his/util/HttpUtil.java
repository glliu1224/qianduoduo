package com.media.his.util;


import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * http 工具类
 *
 * @author dion.li
 * @version v1.0.0
 * @date 2018/03/10 10:41
 */
public class HttpUtil {

    /**
     * @return HttpClient单例
     */
    public static CloseableHttpClient getHttpClient() {
        return HttpClientHolder.INSTANCE;
    }

    /**
     * 安静的关闭，即使抛出异常
     *
     * @param response 响应
     */
    public static void closeQuietly(CloseableHttpResponse response) {
        if (response == null) {
            return;
        }
        try {
            response.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * @param request request对象
     * @return ip
     */
    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("clientip"); // for UC browser
        if (ip == null) {
            ip = request.getHeader("X-Real-IP");
            if (ip == null) {
                ip = request.getHeader("X-Forwarded-For");
                if (ip == null) {
                    ip = request.getRemoteAddr();
                    if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
                        //根据网卡取本机配置的IP
                        InetAddress inet = null;
                        try {
                            inet = InetAddress.getLocalHost();
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }
                        ip = inet.getHostAddress();
                    }
                }
            }
        }
        return ip;
    }

    /**
     * @param request 请求
     * @return ua
     */
    public static String getUA(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        return ua != null ? ua : "";
    }

    /**
     * 获得Int类型的参数。首先从request参数中拿，若不存在，则从Cookie中获得。
     *
     * @param request 请求
     * @param key     参数名
     * @return 参数值
     */
    public static Integer getIntParameter(HttpServletRequest request, String key) {
        String value = getParameter(request, key);
        return Integer.valueOf(value);
    }

    /**
     * 获取Cookie方法
     *
     * @param request request对象
     * @param name    Cookie名称
     * @return 值
     */
    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 获得String类型的参数。首先从request参数中拿，若不存在，则从Cookie中获得。
     *
     * @param request 请求
     * @param key     参数名
     * @return 参数值
     */
    public static String getParameter(HttpServletRequest request, String key) {
        return request.getParameter(key);
    }

    /**
     * 根据request拼接queryString
     *
     * @return queryString
     */
    public static String buildQueryString(HttpServletRequest request) {
        @SuppressWarnings("unchecked") Enumeration<String> es = request.getParameterNames();
        if (!es.hasMoreElements()) {
            return "";
        }
        String parameterName, parameterValue;
        StringBuilder params = new StringBuilder();
        while (es.hasMoreElements()) {
            parameterName = es.nextElement();
            parameterValue = request.getParameter(parameterName);
            params.append(parameterName).append("=").append(parameterValue).append("&");
        }
        return params.deleteCharAt(params.length() - 1).toString();
    }

    /**
     * HttpClient单例持有
     */
    private static class HttpClientHolder {
        private static final CloseableHttpClient INSTANCE = HttpClients.custom()
                .disableAutomaticRetries()
                .setMaxConnTotal(10240)
                .setMaxConnPerRoute(512)
                .setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout((int) (30 * DateUtil.SECOND_LONG)).setSocketTimeout((int) (30 * DateUtil.SECOND_LONG)).build())// TODO 【要设置下httpclient的默认超时时间】
                .setUserAgent("")
                .build();
    }

    /**
     * Http请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String doGet(String url, Map<String, Object> param) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key) != null ? param.get(key).toString() : null);
                }
            }
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);
            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String resultStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                resultString = resultStr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * httpclient doPost
     */
    public static String doPost(String url, Map<String, String> param) {
        CloseableHttpResponse response = null;
        String requestString = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            response = httpClient.execute(httpPost);
            requestString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return requestString;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }


    /**
     * httpclient doPostJson
     */
    public static String doPostJson(String url, String json) {
        CloseableHttpResponse response = null;
        String requestString = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            requestString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return requestString;
    }


}