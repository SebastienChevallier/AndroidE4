package com.example.seb.androide4.service;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by christian on 07/11/2017.
 */

public class HeaderInterceptor
        implements Interceptor {
    @Override
    public Response intercept(Chain chain)
            throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader("deviceplatform", "android")
                .addHeader( "Accept", "application/x-www-form-urlencoded")
                .addHeader("Accept-Language", "en-US,en;q=0.5")
                .removeHeader("User-Agent")
                .addHeader("User-Agent", "appfrais")
                .build();
        Response response = chain.proceed(request);
        return response;
    }
}

