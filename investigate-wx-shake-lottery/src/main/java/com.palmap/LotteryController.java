package com.palmap;

import com.palmap.service.LotteryService;
import com.palmap.service.WechatService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vae on 2015/10/30.
 */
@Controller
@Slf4j @Getter @Setter
public class LotteryController {

  @Autowired LotteryService lotteryService;
  @Autowired WechatService wechatService;

  @RequestMapping(path = "/lottery/shake", method = {RequestMethod.GET, RequestMethod.POST})
  @ResponseBody
  public String shake(HttpEntity<String> entity) {
    String body = entity.getBody();
    Document document = Jsoup.parse(body);
    String openId = document.select("FromUserName").first().data();
    String uuid = document.select("ChosenBeacon").first().select("Uuid").first().data();
    log.info("Wechat user[{}] is shaking the mobile neer device[{}]", openId, uuid);
    return "";
  }

  @RequestMapping(path = "/lottery/draw", method = RequestMethod.GET)
  @ResponseBody
  public LotteryResult lottery(@RequestParam("OpenID") String openID) {
    return lotteryService.lottery(openID);
  }

  @RequestMapping(path = "/go", method = RequestMethod.GET)
  public String go(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String ticket = request.getParameter("ticket");
    String openId = wechatService.getOpenId(ticket);
    log.info("request: ticket={}, openId={}", ticket, openId);
    request.setAttribute("OpenID", openId == null ? "doubi" : openId);
    response.setContentType("text/html");
    response.setHeader("Cache-Control", "no-cache");
    return "lottery";
  }

}
