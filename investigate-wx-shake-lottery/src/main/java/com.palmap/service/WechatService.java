package com.palmap.service;

import com.google.common.base.Strings;
import com.squareup.okhttp.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.IOException;

/**
 * Created by LiuPin on 2015/10/30.
 */
@Service @Slf4j
@Cacheable("wechat")
public class WechatService {

  @Value("${wechat.app.id}") String appId;
  @Value("${wechat.app.secret}") String appSecret;

  OkHttpClient client = new OkHttpClient();

  public String getOpenId(String ticket) {
    if(Strings.isNullOrEmpty(ticket)) return null;

    JsonObject json = Json.createObjectBuilder().add("ticket", ticket).add("need_poi", 1).build();

    Request request = new Request.Builder()
        .url("https://api.weixin.qq.com/shakearound/user/getshakeinfo?access_token=" + getAccessToken())
        .post(RequestBody.create(MediaType.parse("application/json"), json.toString()))
        .build();

    try {
      Response response = client.newCall(request).execute();
      json  = Json.createReader(response.body().charStream()).readObject();

      if(json.getInt("errcode") == 0){
        return json.getJsonObject("data").getString("openid");
      }
      return null;
    } catch (IOException e) {
      log.error("get open id from wechat error {}", e.getMessage());
      return null;
    }
  }

  public String getAccessToken(){

    Request request = new Request.Builder()
        .url("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret)
        .get()
        .build();

    try {
      Response response = client.newCall(request).execute();
      JsonObject json  = Json.createReader(response.body().charStream()).readObject();
      return json.getString("access_token");
    } catch (IOException e) {
      log.error("get access token from wechat error {}", e.getMessage());
      return null;
    }
  }
}