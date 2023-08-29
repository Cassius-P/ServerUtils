package com.su.serverutils.helpers;

import com.google.gson.Gson;
import com.mojang.logging.LogUtils;
import com.su.serverutils.config.ApiConfig;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class HttpRequestHelper {

    private static final Logger logger = LogUtils.getLogger();

    public static String sendGetRequest(String url, Map<String, String> urlParameters) throws Exception {
        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String> entry : urlParameters.entrySet()) {
            if (params.length() != 0) {
                params.append('&');
            }
            params.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            params.append('=');
            params.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        URL completeUrl = new URL(ApiConfig.apiURL.get() + url + "?" + params.toString());
        HttpURLConnection connection = (HttpURLConnection) completeUrl.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public static String sendPostRequest(String url, Object body) throws Exception {

        if(!url.startsWith("/")){
            url = "/" + url;
        }
        logger.info("Host :" + ApiConfig.apiURL.get());
        URL completeUrl = new URL(ApiConfig.apiURL.get() +url);
        logger.info("Sending POST request to " + completeUrl.toString());

        HttpURLConnection connection = (HttpURLConnection) completeUrl.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        String bodyString;
        if (body instanceof String) {
            bodyString = (String) body;
            connection.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
        } else if (body instanceof List || body instanceof Map) {
            Gson gson = new Gson();
            bodyString = gson.toJson(body);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        } else {
            throw new IllegalArgumentException("Unsupported body type");
        }
        logger.info("With body: " + bodyString);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = bodyString.getBytes("utf-8");
            os.write(input, 0, input.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

}

