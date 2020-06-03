package com.media.his.config.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClient {
    private Integer maxTotal = 100;  //最大连接数
    private Integer defaultMaxPerRoute = 20;//并发数
    private Integer connectTimeout = 1000; //创建链接最长时间
    private Integer connectionRequestTimeout = 500;//从连接池中获取到的最长时间
    private Integer socketTimeout = 10000;//数据传输最长时间
    private Boolean staleConnectionCheckEnabled = true;//提交请求前是否成功连接

    /**
     * 实例化连接池管理器
     */
    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager(){
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        httpClientConnectionManager.setMaxTotal(maxTotal);
        httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return httpClientConnectionManager;
    }

    /**
     * 注入上面连接池管理器
     */
    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder getHttpClientBuilder(@Qualifier("httpClientConnectionManager")PoolingHttpClientConnectionManager httpClientConnectionManager){
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(httpClientConnectionManager);
        return httpClientBuilder;
    }

    /**
     * 注入连接池，用于获取httpClient
     */
    @Bean
    public CloseableHttpClient getCloseableHttpClient(@Qualifier("httpClientBuilder")HttpClientBuilder httpClientBuilder){
        return httpClientBuilder.build();
    }

    /**
     * 设置builder的链接信息
     */
    @Bean(name = "builder")
    public RequestConfig.Builder getBuilder(){
        RequestConfig.Builder builder = RequestConfig.custom();
        return builder.setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout)
                .setStaleConnectionCheckEnabled(staleConnectionCheckEnabled);
    }

    /**
     * 使用builder构建一个RequestConfig对象
     */
    @Bean
    public  RequestConfig getRequestConfig(@Qualifier("builder")RequestConfig.Builder builder){
        return builder.build();
    }
















}
