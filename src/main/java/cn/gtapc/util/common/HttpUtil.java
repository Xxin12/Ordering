package cn.gtapc.util.common;

import cn.gtapc.util.constant.RequestMethodEnum;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HTTP 工具類
 *
 * @author duanluan
 */
public class HttpUtil {

    private HttpUtil() {
    }

    /**
     * 获取重定向地址
     *
     * @param requestMethodEnum
     * @param uri
     * @param headerMap
     * @return
     */
    public static String getRedirectUrl(RequestMethodEnum requestMethodEnum, String uri, Map<String, String> headerMap) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpRequestBase httpRequest = null;
        if (RequestMethodEnum.GET.equals(requestMethodEnum)) {
            httpRequest = new HttpGet(uri);
        } else if (RequestMethodEnum.DELETE.equals(requestMethodEnum)) {
            httpRequest = new HttpDelete(uri);
        }

        // 禁止重定向
        httpRequest.setConfig(RequestConfig.custom().setRedirectsEnabled(false).build());

        // 设置 Header
        if (headerMap != null) {
            for (String key :
                    headerMap.keySet()) {
                httpRequest.setHeader(key, headerMap.get(key));
            }
        }

        try {
            response = httpClient.execute(httpRequest);
            if (response.getStatusLine().getStatusCode() == 302) {
                return response.getFirstHeader("Location").getValue();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll(response, httpClient);
        }
        return "";
    }

    /**
     * GET/DELETE 方式请求
     *
     * @param requestMethodEnum
     * @param uri
     * @param headerMap         Header
     * @return
     */
    public static String getOrDelete(RequestMethodEnum requestMethodEnum, String uri, Map<String, String> headerMap) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpRequestBase httpRequest = null;
        if (RequestMethodEnum.GET.equals(requestMethodEnum)) {
            httpRequest = new HttpGet(uri);
        } else if (RequestMethodEnum.DELETE.equals(requestMethodEnum)) {
            httpRequest = new HttpDelete(uri);
        }

        // 设置 Header
        if (headerMap != null) {
            for (String key :
                    headerMap.keySet()) {
                httpRequest.setHeader(key, headerMap.get(key));
            }
        }

        try {
            response = httpClient.execute(httpRequest);
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll(response, httpClient);
        }
        return "";
    }

    /**
     * POST 方式请求
     *
     * @param uri
     * @param headerMap Header
     * @param paramMap  Parameter
     * @return
     */
    public static String post(String uri, Map<String, String> headerMap, Map<String, String> paramMap) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(uri);

        // 设置 Header
        if (headerMap != null) {
            for (String key :
                    headerMap.keySet()) {
                httpPost.setHeader(key, headerMap.get(key));
            }
        }

        // 设置参数
        List<NameValuePair> paramsList = null;
        if (paramMap != null) {
            paramsList = new ArrayList<NameValuePair>();
            for (String key :
                    paramMap.keySet()) {
                paramsList.add(new BasicNameValuePair(key, paramMap.get(key)));
            }
        }

        try {
            // 参数转码
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(paramsList, "UTF-8");
            httpPost.setEntity(uefEntity);
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll(response, httpClient);
        }
        return "";
    }

    /**
     * 关闭全部对象
     *
     * @param response
     * @param httpClient
     */
    private static void closeAll(CloseableHttpResponse response, CloseableHttpClient httpClient) {
        try {
            if (response != null) {
                response.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Header 键值对转换为 Map
     *
     * @param keyValues
     * @return
     */
    public static Map<String, String> headerValToMap(String keyValues) {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtil.isNotEmpty(keyValues)) {
            for (String keyValue :
                    keyValues.replace(" ", "").split(";")) {
                String[] keyAndValue = keyValue.split("=");
                if (keyAndValue.length > 1) {
                    map.put(keyAndValue[0], keyAndValue[1]);
                }
            }
        }
        return map;
    }

}
