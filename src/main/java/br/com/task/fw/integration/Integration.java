package br.com.task.fw.integration;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Integration {

    public static Response requestGet(Request req) throws Exception {
        HttpGet r = new HttpGet(req.getUrl());
        req.getHeaders().entrySet().forEach((entry) -> {
            r.addHeader(entry.getKey(), entry.getValue());
        });
        HttpResponse response = HttpClientBuilder.create().build().execute(r);
        Response res = getResponse(response);
        return res;
    }

    public static ByteArrayOutputStream requestGetDownload(Request req) throws Exception {
        HttpGet r = new HttpGet(req.getUrl());
        req.getHeaders().entrySet().forEach((entry) -> {
            r.addHeader(entry.getKey(), entry.getValue());
        });

        HttpResponse response = HttpClientBuilder.create().build().execute(r);
        HttpEntity entity = response.getEntity();

        BufferedInputStream bis = new BufferedInputStream(entity.getContent());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int inByte;
        while ((inByte = bis.read()) != -1) {
            bos.write(inByte);
        }
        bis.close();
        bos.close();

        return bos;
    }

    public static JSONObject requestGetFile(Request req) throws Exception {
        HttpGet r = new HttpGet(req.getUrl());
        req.getHeaders().entrySet().forEach((entry) -> {
            r.addHeader(entry.getKey(), entry.getValue());
        });

        HttpResponse response = HttpClientBuilder.create().build().execute(r);
        HttpEntity entity = response.getEntity();

        JSONObject j = new JSONObject();
        j.put("contentType", entity.getContentType().getValue());
        j.put("fileContent", new BufferedInputStream(entity.getContent()));
        return j;
    }

    public static Response requestPost(Request req) throws Exception {
        HttpPost r = new HttpPost(req.getUrl());
        req.getHeaders().entrySet().forEach((entry) -> {
            r.addHeader(entry.getKey(), entry.getValue());
        });
        HttpEntity entity = new ByteArrayEntity(req.getBody().getBytes("UTF-8"));
        r.setEntity(entity);
        HttpResponse response = HttpClientBuilder.create().build().execute(r);
        Response res = getResponse(response);
        return res;
    }

    public static Response requestPost(Request req, List<NameValuePair> p) throws Exception {
        HttpPost r = new HttpPost(req.getUrl());
        req.getHeaders().entrySet().forEach((entry) -> {
            r.addHeader(entry.getKey(), entry.getValue());
        });
        r.setEntity(new UrlEncodedFormEntity(p, org.apache.http.Consts.UTF_8));
        HttpResponse response = HttpClientBuilder.create().build().execute(r);
        Response res = getResponse(response);
        return res;
    }

    public static Response requestPost(Request req, JSONObject j) throws Exception {
        HttpPost r = new HttpPost(req.getUrl());
        req.getHeaders().entrySet().forEach((entry) -> {
            r.addHeader(entry.getKey(), entry.getValue());
        });
        r.setEntity(new StringEntity(j.toString()));
        HttpResponse response = HttpClientBuilder.create().build().execute(r);
        Response res = getResponse(response);
        return res;
    }

    public static Response requestPatch(Request req, JSONObject j) throws Exception {
        HttpPatch r = new HttpPatch(req.getUrl());
        req.getHeaders().entrySet().forEach((entry) -> {
            r.addHeader(entry.getKey(), entry.getValue());
        });
        r.setEntity(new StringEntity(j.toString()));
        HttpResponse response = HttpClientBuilder.create().build().execute(r);
        Response res = getResponse(response);
        return res;
    }

    public static Response requestPatch(Request req) throws Exception {
        HttpPatch r = new HttpPatch(req.getUrl());
        req.getHeaders().entrySet().forEach((entry) -> {
            r.addHeader(entry.getKey(), entry.getValue());
        });
        HttpResponse response = HttpClientBuilder.create().build().execute(r);
        Response res = getResponse(response);
        return res;
    }

    public static boolean requestDelete(Request req) throws Exception {
        HttpDelete r = new HttpDelete(req.getUrl());
        req.getHeaders().entrySet().forEach((entry) -> {
            r.addHeader(entry.getKey(), entry.getValue());
        });
        HttpResponse response = HttpClientBuilder.create().build().execute(r);
        return response.getStatusLine().getStatusCode() == 204;
    }

    public static Response requestSendFile(Request req, String file) throws Exception {
        HttpPut r = new HttpPut(req.getUrl());
        req.getHeaders().entrySet().forEach((entry) -> {
            r.addHeader(entry.getKey(), entry.getValue());

        });

        File f = new File(file);

        r.setEntity(new FileEntity(f));

        HttpResponse response = HttpClientBuilder.create().build().execute(r);
        Response res = getResponse(response);
        return res;
    }

    public static Response getResponse(HttpResponse response) throws IOException {
        int responseCode = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        Response r = new Response();
        r.setCode(responseCode);
        r.setBody(body);
        return r;
    }

}
