package com.lizikj.api.utils;

/**
 * @author zhoufe
 * @date 2018/5/3 16:17
 */

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.zip.GZIPInputStream;

public abstract class WebUtils {
    private static final Logger logger = LoggerFactory.getLogger(WebUtils.class);
    
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String METHOD_POST = "POST";

    public WebUtils() {
    }

    public static String doPost(String url, Map<String, String> params, String charset, String rid, int connectTimeout, int readTimeout, Map<String, String> headerMap) throws IOException {
        String ctype = "application/x-www-form-urlencoded;charset=" + charset;
        String query = buildQuery(params, charset);
        setLogInfo("request: " + query);
        byte[] content = new byte[0];
        if (query != null) {
            content = query.getBytes(charset);
        }

        return _doPost(url, ctype, content, rid, connectTimeout, readTimeout, headerMap);
    }

    public static String doPost(String url, String ctype, byte[] content, String rid, int connectTimeout, int readTimeout) throws SocketTimeoutException, IOException {
        return _doPost(url, ctype, content, rid, connectTimeout, readTimeout, (Map)null);
    }

    private static String _doPost(String url, String ctype, byte[] content, String rid, int connectTimeout, int readTimeout, Map<String, String> headerMap) throws SocketTimeoutException, IOException {
        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;

        try {
            conn = getConnection(new URL(url), "POST", ctype, headerMap, rid);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            out = conn.getOutputStream();
            out.write(content);
            rsp = getResponseAsString(conn);
        } finally {
            if (out != null) {
                out.close();
            }

            if (conn != null) {
                conn.disconnect();
            }

        }

        setLogInfo("response: " + rsp);
        return rsp;
    }

    private static HttpURLConnection getConnection(URL url, String method, String ctype, Map<String, String> headerMap, String rid) throws IOException {
        Object conn;
        if ("https".equals(url.getProtocol())) {
            SSLContext ctx;
            try {
                ctx = SSLContext.getInstance("TLS");
                ctx.init(new KeyManager[0], new TrustManager[]{new WebUtils.DefaultTrustManager()}, new SecureRandom());
            } catch (Exception var9) {
                throw new IOException(var9);
            }

            HttpsURLConnection connHttps = (HttpsURLConnection)url.openConnection();
            connHttps.setSSLSocketFactory(ctx.getSocketFactory());
            connHttps.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            conn = connHttps;
        } else {
            conn = (HttpURLConnection)url.openConnection();
        }

        ((HttpURLConnection)conn).setRequestMethod(method);
        ((HttpURLConnection)conn).setDoInput(true);
        ((HttpURLConnection)conn).setDoOutput(true);
        ((HttpURLConnection)conn).setRequestProperty("Accept", "text/xml,text/javascript,text/html");
        ((HttpURLConnection)conn).setRequestProperty("Content-Type", ctype);
        ((HttpURLConnection)conn).setRequestProperty("Accept-Encoding", "gzip");
        ((HttpURLConnection)conn).setRequestProperty("User-Agent", "eleme-openapi-java-sdk");
        String elemeRequestId = getReqID(rid);
        ((HttpURLConnection)conn).setRequestProperty("x-eleme-requestid", elemeRequestId);
        if (headerMap != null) {
            Iterator var11 = headerMap.entrySet().iterator();

            while(var11.hasNext()) {
                Entry<String, String> entry = (Entry)var11.next();
                ((HttpURLConnection)conn).setRequestProperty((String)entry.getKey(), (String)entry.getValue());
            }
        }

        return (HttpURLConnection)conn;
    }

    public static String buildQuery(Map<String, String> params, String charset) throws IOException {
        if (params != null && !params.isEmpty()) {
            StringBuilder query = new StringBuilder();
            Set<Entry<String, String>> entries = params.entrySet();
            boolean hasParam = false;
            Iterator var5 = entries.iterator();

            while(var5.hasNext()) {
                Entry<String, String> entry = (Entry)var5.next();
                String name = (String)entry.getKey();
                String value = (String)entry.getValue();
                if (hasParam) {
                    query.append("&");
                } else {
                    hasParam = true;
                }

                query.append(name).append("=").append(URLEncoder.encode(value, charset));
            }

            return query.toString();
        } else {
            return null;
        }
    }

    protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
        String charset = getResponseCharset(conn.getContentType());
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), charset, conn);
        } else {
            String msg = getStreamAsString(es, charset, conn);
            if (StringUtils.isEmpty(msg)) {
                throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
            } else {
                return msg;
            }
        }
    }

    private static String getStreamAsString(InputStream stream, String charset, HttpURLConnection conn) throws IOException {
        try {
            InputStreamReader reader;
            if ("gzip".equals(conn.getContentEncoding())) {
                reader = new InputStreamReader(new GZIPInputStream(stream), charset);
            } else {
                reader = new InputStreamReader(stream, charset);
            }

            StringBuilder response = new StringBuilder();
            char[] buff = new char[1024];
            boolean var6 = false;

            int read;
            while((read = reader.read(buff)) > 0) {
                response.append(buff, 0, read);
            }

            String var7 = response.toString();
            return var7;
        } finally {
            if (stream != null) {
                stream.close();
            }

        }
    }

    private static String getReqID(String rid) {
        String reqId = rid + "|" + System.currentTimeMillis();
        return reqId;
    }

    public static String generateUUID() {
        try {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            if (uuid.length() > 32) {
                uuid = uuid.substring(0, 32);
            }

            return uuid.toUpperCase();
        } catch (Exception var1) {
            return "00112233445566778899AABBCCDDEEFF";
        }
    }

    private static String getResponseCharset(String ctype) {
        String charset = "UTF-8";
        if (!StringUtils.isEmpty(ctype)) {
            String[] params = ctype.split(";");
            String[] var3 = params;
            int var4 = params.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String param = var3[var5];
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2 && !StringUtils.isEmpty(pair[1])) {
                        charset = pair[1].trim();
                    }
                    break;
                }
            }
        }

        return charset;
    }

    public static <T> T call(String url, Map<String, Object> parameters) throws Exception {
        long timestamp = System.currentTimeMillis();
        String requestId = generateUUID();
        if (logger.isInfoEnabled()) {
            logger.error("requestId:", requestId);
        }
        String requestJson = null;
        String responsePayload;
        try {
            if(parameters != null){
                requestJson = JSONObject.toJSONString(parameters);
                responsePayload = doRequest(url, requestJson, requestId);
            }else{
                responsePayload = doRequest(url);
            }
        } catch (SocketTimeoutException var18) {
            throw var18;
        } catch (IOException var19) {
            throw var19;
        }

        setLogInfo("requestJson: " + requestJson);

        return (T) responsePayload;
    }


    private static String doRequest(String url) throws SocketTimeoutException, IOException {
        String response = doPost(url, "application/json; charset=utf-8", "".getBytes("UTF-8"), "", 15000, 15000);
        setLogInfo("response: " + response);
        return response;
    }


    private static String doRequest(String url, String requestJson, String rid) throws SocketTimeoutException, IOException {
        String response = doPost(url, "application/json; charset=utf-8", requestJson.getBytes("UTF-8"), rid, 15000, 15000);
        setLogInfo("response: " + response);
        return response;
    }



    private static void setLogInfo(String msg) {
       if (logger.isInfoEnabled()){
           logger.info(msg);
       }

    }

    private static void setLogError(String msg) {
        if (logger.isErrorEnabled()){
            logger.error(msg);
        }


    }

    private static class DefaultTrustManager implements X509TrustManager {
        private DefaultTrustManager() {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    }


    public static void main(String[] args) throws Exception {
        //String url = "http://api.lizikj.com/virtual/shop/data/genData/ddxx";
        String url = "http://192.168.5.173:9005/virtual/shop/data/genData?key=ddd";
        String respone = call(url, null);
        System.out.println("respone:"+respone);
    }
}
