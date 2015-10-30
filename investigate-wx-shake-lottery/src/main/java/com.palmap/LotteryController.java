package com.palmap;

import com.palmap.service.LotteryService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by vae on 2015/10/30.
 */
@Controller
@Slf4j @Getter @Setter
public class LotteryController {

  ClassPathResource resource = new ClassPathResource("static/lottery.html");

  @Autowired LotteryService service;

  @RequestMapping(path = "/lottery/shake", method = {RequestMethod.GET, RequestMethod.POST})
  @ResponseBody
  public String shake(HttpEntity<String> entity, Model model) {
    String body = entity.getBody();
    String openId = Jsoup.parse(body).select("FromUserName").first().data();
    model.addAttribute("OpenID", openId);
    return "";
  }

  @RequestMapping(path = "/lottery/draw", method = RequestMethod.GET)
  @ResponseBody
  public LotteryResult lottery(@ModelAttribute("OpenID") String openID) {
    Objects.requireNonNull(openID);
    return service.lottery(openID);
  }

  @RequestMapping(path = "/go", method = RequestMethod.GET)
  public void go(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
    log.info("request: {}", request.getParameterMap());
    session.setAttribute("OpenID", "doubi");
    response.setContentType("text/html");
    response.setHeader("Cache-Control", "no-cache");
    FileCopyUtils.copy(resource.getInputStream(), response.getOutputStream());
  }

}
