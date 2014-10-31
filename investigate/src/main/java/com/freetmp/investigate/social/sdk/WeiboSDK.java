package com.freetmp.investigate.social.sdk;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.belerweb.social.bean.Result;
import com.belerweb.social.weibo.api.Weibo;
import com.belerweb.social.weibo.bean.AccessToken;
import com.belerweb.social.weibo.bean.TokenInfo;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DownloadedContent;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.History;
import com.gargoylesoftware.htmlunit.HttpWebConnection;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.google.common.base.Predicate;

class CustomWebConnection extends HttpWebConnection{

	public CustomWebConnection(WebClient webClient) {
		super(webClient);
	}

	@Override
	protected void onResponseGenerated(HttpUriRequest httpMethod) {
		super.onResponseGenerated(httpMethod);
		System.out.println(httpMethod.getURI());
	}

	@Override
	protected DownloadedContent downloadResponseBody(HttpResponse httpResponse) throws IOException {
		System.out.println(httpResponse.getStatusLine());
		Header[] locations = httpResponse.getHeaders("Location");
		if(locations.length > 0){
			System.out.println(locations[0]);
		}
		return super.downloadResponseBody(httpResponse);
	}
	
}

public class WeiboSDK {
	
	private static final String id = "435214623";
	
	private static final String secret = "1c00fba6c840e5d106b524ce9cfdccd3";
	
	private static final String acnt = "liuhuizhang@foxmail.com";
	private static final String pwd	 = "!@#$%^";
	
	@Deprecated
	public static String resolveCode(String url) {
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
        webClient.setJavaScriptTimeout(5000);
        webClient.getOptions().setRedirectEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getCookieManager().setCookiesEnabled(true);
        
        webClient.setWebConnection(new CustomWebConnection(webClient));
		
		HtmlPage page = null;
		try {
			page = webClient.getPage(url);
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
			return null;
		}
		page.getWebClient().waitForBackgroundJavaScript(10000);		
		try {
			HtmlForm form = page.getFormByName("authZForm");
			HtmlElement button = (HtmlElement) page.querySelector(".WB_btn_login");
			HtmlTextInput account = (HtmlTextInput)form.querySelector("#userId");
			HtmlPasswordInput password = (HtmlPasswordInput) form.querySelector("#passwd");
			account.setValueAttribute(acnt);
			password.setValueAttribute(pwd);
			page = button.click();
		} catch (ElementNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	
		page.getWebClient().waitForBackgroundJavaScript(10000);
		System.out.println(page.asXml());
		HtmlElement element = (HtmlElement) page.querySelector("a.WB_btn_oauth.formbtn_01");
		System.out.println(element);
		try {
			page = element.click();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		page.getWebClient().waitForBackgroundJavaScript(10000);
		History history = webClient.getCurrentWindow().getHistory();
		System.out.println(history);
		URL redirectURL = page.getUrl();
		System.out.println(redirectURL);
		
		//System.out.println(page.asXml());
		return null;
	}
	
	public static String resolveCodeUseDriver(String url){
/*		HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_24);
		driver.setJavascriptEnabled(true);*/
		
		System.setProperty("webdriver.firefox.bin", "E:/Program Files/Mozilla Firefox/firefox.exe"); 
		WebDriver driver = new FirefoxDriver(); 
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		driver.get(url);

		Boolean logined = false;
		
		try {
			List<WebElement> list = driver.findElements(By.id("userId"));
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
			List<WebElement> list = driver.findElements(By.cssSelector("a.WB_btn_oauth"));
			if(list.isEmpty()) authorized = true;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if(!authorized){
		
			WebElement authorize = new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
	
				@Override
				public WebElement apply(WebDriver input) {
					return input.findElement(By.cssSelector("a.WB_btn_oauth"));
				}
	
			});
			
			authorize.click();
		}
		
		new WebDriverWait(driver, 10).until(new Predicate<WebDriver>() {
			
			@Override
			public boolean apply(WebDriver input) {
				if(input.getTitle().trim().equals("百度一下，你就知道")) return true;
				return false;
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
		//resolveCode(url);
	    
	    String code = resolveCodeUseDriver(url);
		System.out.println(code);
		
	    Result<AccessToken> tokenResult = weibo.getOAuth2().accessToken(code);

	    System.out.println(tokenResult.getResult().getJsonObject());
		
	    String accessToken = tokenResult.getResult().getJsonObject().getString("access_token");
	    Result<TokenInfo> tokenInfoResult = weibo.getOAuth2().getTokenInfo(accessToken);

	    System.out.println(tokenInfoResult.getResult().getJsonObject());
	
	}

}
