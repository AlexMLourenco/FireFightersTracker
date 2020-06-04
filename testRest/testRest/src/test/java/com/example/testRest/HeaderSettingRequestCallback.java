/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.testRest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.client.RequestCallback;

import java.io.IOException;
import java.util.Map;

public class HeaderSettingRequestCallback implements RequestCallback {
    final Map<String, String> requestHeaders;

    private String body;

    public HeaderSettingRequestCallback(final Map<String, String> headers) {
        this.requestHeaders = headers;
    }

    public void setBody(final String postBody) {
        this.body = postBody;
    }

    @Override
    public void doWithRequest(ClientHttpRequest request) throws IOException {
        final HttpHeaders clientHeaders = request.getHeaders();
        for (final Map.Entry<String, String> entry : requestHeaders.entrySet()) {
            clientHeaders.add(entry.getKey(), entry.getValue());
        }
        if (null != body) {
            request.getBody().write(body.getBytes());
        }
    }
}
