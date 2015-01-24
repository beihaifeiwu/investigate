package com.freetmp.investigate.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.client.HttpAsyncClient;

/**
 * @author Pin Liu
 * @编写日期 2014年8月18日上午9:46:52
 */
public class RrtServlet extends HttpServlet {

	private static final long serialVersionUID = 7123743823777818999L;
	
	static int TIMEOUT = 3000;
	
	protected static HttpAsyncClient client;
	
	protected static CountDownLatch latch;
	
	protected static AtomicInteger failed;
	
	protected static AtomicInteger completed;
	
	protected static AtomicInteger cancelled;
	
	static{
		RequestConfig config  = RequestConfig.custom().setConnectTimeout(TIMEOUT)
											 .setSocketTimeout(TIMEOUT).build();
		client = HttpAsyncClients.custom().setDefaultRequestConfig(config).build();
		((CloseableHttpAsyncClient)client).start();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String url = (String) req.getParameter("url");
		PrintWriter pw = res.getWriter();
		if(url == null){
			pw.print("url must be provided!");
			pw.flush();
			return;
		}
		Integer times = Integer.valueOf((String) req.getParameter("times"));
		if(times == null || times < 0 ){
			pw.print("times argument is illegal");
			pw.flush();
			return;			
		}
		
		HttpGet httpGet = new HttpGet(url);
		if(latch != null ){
			if(latch.getCount() != 0){
				pw.print("there is another test running!");
				pw.flush();
				return;
			}
		}else{
			latch = new CountDownLatch(times);
			failed = new AtomicInteger();
			completed = new AtomicInteger();
			cancelled = new AtomicInteger();
		}
		
		for(int i = 0; i < times; i++){
			client.execute(httpGet, new FutureCallback<HttpResponse>() {
				
				public void failed(Exception e) {
					latch.countDown();
					failed.incrementAndGet();
				}
				
				public void completed(HttpResponse res) {
					latch.countDown();
					completed.incrementAndGet();
					
				}
				
				public void cancelled() {
					latch.countDown();
					cancelled.incrementAndGet();
				}
			});
		}
		
		pw.print("Please wait...");
		pw.flush();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		if(latch != null && latch.getCount() != 0){
			pw.print("{\"msg\":\"processing\",\"failed\":"+failed.get()+",\"completed\":"+completed.get()+",\"cancelled\":"+cancelled.get()+"}");
			pw.flush();
		}else if(latch != null && latch.getCount() == 0){
			pw.print("{\"msg\":\"completed\",\"failed\":"+failed.get()+",\"completed\":"+completed.get()+",\"cancelled\":"+cancelled.get()+"}");
			pw.flush();
		}else{
			pw.print("{\"msg\":\"no task\",\"failed\":"+0+",\"completed\":"+0+",\"cancelled\":"+0+"}");
			pw.flush();			
		}

	}

}
