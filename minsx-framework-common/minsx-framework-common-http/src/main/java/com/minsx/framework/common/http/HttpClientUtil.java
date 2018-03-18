/*
 *
 *  * Copyright 2017 https://www.minsx.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.minsx.framework.common.http;

import com.mashape.unirest.http.Unirest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public final class HttpClientUtil extends Unirest {

    public static OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    public static Request.Builder okHttpBuilder() {
        return new Request.Builder();
    }

    public static Response newCall(OkHttpClient okHttpClient, Request request) throws IOException {
        return okHttpClient.newCall(request).execute();
    }


}
