package com.freetmp.investigate.social.sdk;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.belerweb.social.bean.Result;
import com.belerweb.social.weibo.api.Weibo;
import com.belerweb.social.weibo.bean.AccessToken;
import com.belerweb.social.weibo.bean.TokenInfo;
import com.belerweb.social.weibo.bean.UserCounts;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.google.common.base.Predicate;

public class WeiboSDK {
	
	private static final String id = "926729591";
	
	private static final String secret = "1c96cfd680e74d493a15ac4358c077cf";
	
	private static final String acnt = "ams@palmaplus.com";
	private static final String pwd	 = "Abc123";
	
	@Deprecated
	public static String resolveCode(String url) {
		WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_11);
        webClient.setJavaScriptTimeout(5000);
        webClient.getOptions().setRedirectEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getCookieManager().setCookiesEnabled(true);
        
		
		HtmlPage page = null;
		try {
			page = webClient.getPage(url);
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
			return null;
		}
		page.getWebClient().waitForBackgroundJavaScript(10000);		
		HtmlForm form = page.getFormByName("authZForm");
		if(form != null){
			try {
				HtmlElement button = (HtmlElement) page.querySelector(".WB_btn_login");
				HtmlTextInput account = (HtmlTextInput)form.querySelector("#userId");
				HtmlPasswordInput password = (HtmlPasswordInput) form.querySelector("#passwd");
				account.setValueAttribute(acnt);
				password.setValueAttribute(pwd);
				page = button.click();
				System.out.println("[LOGIN] click the link " + button);
			} catch (ElementNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		page.getWebClient().waitForBackgroundJavaScript(5000);
		URL redirectURL = page.getWebResponse().getWebRequest().getUrl();
		System.out.println(page.getTitleText());
		System.out.println(redirectURL);
		
		ScriptResult sr = page.executeJavaScript("(function(){ console.log(document.location.href);})()");
		page = (HtmlPage) sr.getNewPage();
		System.out.println(page.asText());
		
		HtmlElement element = (HtmlElement) page.querySelector("a.WB_btn_oauth.formbtn_01");
		if(element != null){
			try {
				page = element.click();
				System.out.println("[AUTHORIZED] click the link " + element);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		page.getWebClient().waitForBackgroundJavaScript(10000);

		redirectURL = page.getWebResponse().getWebRequest().getUrl();
		System.out.println(page.getTitleText());
		System.out.println(redirectURL);
		
		//System.out.println(page.asXml());
		return null;
	}
	
	public static String resolveCodeUseDriver(String url){
/*		HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_11);
		driver.setJavascriptEnabled(true);*/
		
		System.setProperty("webdriver.firefox.bin", "E:/Program Files/Mozilla Firefox/firefox.exe"); 
		WebDriver driver = new FirefoxDriver(); 
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		driver.get(url);

		Boolean logined = false;
		
		try {
			List<WebElement> list = new WebDriverWait(driver, 10).until(new ExpectedCondition<List<WebElement>>(){

				@Override
				public List<WebElement> apply(WebDriver input) {
					return driver.findElements(By.cssSelector("a.WB_btn_login"));
				}}
			);
			if(list.isEmpty()) logined = true;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if(!logined){
			WebElement account = new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
	
				@Override
				public WebElement apply(WebDriver input) {
					return input.findElement(By.id("userId"));
				}
	
			});
			WebElement password = new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
	
				@Override
				public WebElement apply(WebDriver input) {
					return input.findElement(By.id("passwd"));
				}
	
			});
			WebElement button = new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
	
				@Override
				public WebElement apply(WebDriver input) {
					return input.findElement(By.cssSelector(".WB_btn_login"));
				}
	
			});
			account.sendKeys(acnt);
			password.sendKeys(pwd);
			button.click();
		}
		
		Boolean authorized = false;
		
		try {
			//Thread.sleep(3000);
			List<WebElement> list = new WebDriverWait(driver, 10).until(new ExpectedCondition<List<WebElement>>(){

				@Override
				public List<WebElement> apply(WebDriver input) {
					return driver.findElements(By.cssSelector("a.formbtn_01"));
				}}
			);
			
			if(list.isEmpty()) authorized = true;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if(!authorized){
		
			WebElement authorize = new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
	
				@Override
				public WebElement apply(WebDriver input) {
					return input.findElement(By.cssSelector("a.formbtn_01"));
				}
	
			});
			
			authorize.click();
		}
/*		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		new WebDriverWait(driver, 10).until(new Predicate<WebDriver>() {
			
			@Override
			public boolean apply(WebDriver input) {
				return input.getCurrentUrl().contains("code=");
			}
		});
		
		System.out.println(driver.getCurrentUrl());
		
		String redirectUrl = driver.getCurrentUrl();
		
		driver.close();
		
		return redirectUrl.substring(redirectUrl.lastIndexOf("=")+1);
		
	}
	
	public static void main(String...args){
		Weibo weibo = new Weibo(id, secret,"http://www.baidu.com");
		
		String url = weibo.getOAuth2().authorize();
	    // 浏览器打开URL获取code用于下一步测试
	    System.out.println(url);
		//String code = resolveCode(url);
	    
	    String code = resolveCodeUseDriver(url);
		System.out.println(code);
		
	    Result<AccessToken> tokenResult = weibo.getOAuth2().accessToken(code);

	    System.out.println(tokenResult.getResult().getJsonObject());
		
	    String accessToken = tokenResult.getResult().getJsonObject().getString("access_token");
	    Result<TokenInfo> tokenInfoResult = weibo.getOAuth2().getTokenInfo(accessToken);

	    System.out.println(tokenInfoResult.getResult().getJsonObject());
	    Long uid = tokenInfoResult.getResult().getJsonObject().getLong("uid");
	    List<String> uids = new ArrayList<String>();
	    uids.add(uid.toString());
	    Result<UserCounts> result = weibo.getUser().counts(null, accessToken, uids);
	    List<UserCounts> results = result.getResults();
	    for (UserCounts userCounts : results) {
	      System.out.println(userCounts.getJsonObject());
	    }
	    
	    Result<com.belerweb.social.weibo.bean.User> resultUser =
	            weibo.getUser().show(weibo.getClientId(), null, uid.toString(), null);
	    System.out.println(resultUser.getResult().getJsonObject());
	}

}
