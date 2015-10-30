package com.palmap.service;

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
public class WechatService {

  @Value("${wechat.access.token}") String accessToken;

  OkHttpClient client = new OkHttpClient();

  @Cacheable
  public String getOpenId(String ticket) {

    JsonObject json = Json.createObjectBuilder().add("ticket", ticket).add("need_poi", 1).build();

    Request request = new Request.Builder()
        .url("https://api.weixin.qq.com/shakearound/user/getshakeinfo?access_token=" + accessToken)
        .method("GET", RequestBody.create(MediaType.parse("application/json"), json.toString()))
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
}