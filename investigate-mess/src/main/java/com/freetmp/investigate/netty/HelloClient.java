package com.freetmp.investigate.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Netty客户端Demo
 * @author Pin Liu
 * @编写日期 2015年1月5日上午10:55:21
 */
@SuppressWarnings("unused")
public class HelloClient {
  
  public static String host = "127.0.0.1";
  public static int port = 8000;
  
  public static void main(String args[]){
    // Client服务启动器 3.x的ClientBootstrap改为Bootstrap，且构造函数变化很大，这里用无参构造
    Bootstrap bootstrap = new Bootstrap();
    // 指定channel类型
    bootstrap.channel(NioSocketChannel.class);
    // 指定Handler
    bootstrap.handler(new HelloClientInitializer());
    // 指定EventLoopGroup
    EventLoopGroup group = new NioEventLoopGroup();
    bootstrap.group(group);
    // 连接到本地的8000端口的服务端
    try {
      Channel ch = bootstrap.connect(host, port).sync().channel();
      //控制台输入
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      for(;;){
        String line = in.readLine();
        if(line == null){
          continue;
        }
        if(line.equalsIgnoreCase("quit")){
          break;
        }
        /*
         * 向服务端发送在控制台输入的文本 并用"\r\n"结尾
         * 之所以用\r\n结尾 是因为我们在handler中添加了 DelimiterBasedFrameDecoder 帧解码。
         * 这个解码器是一个根据\n符号位分隔符的解码器。所以每条消息的最后必须加上\n否则无法识别和解码
         */
        ch.writeAndFlush(line + "\r\n");
      }
    } catch (InterruptedException | IOException e) {
      e.printStackTrace();
    } finally {
      // The connection is closed automatically on shutdown
      group.shutdownGracefully();
    }
    
  }
  
  private static class HelloClientInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
      ChannelPipeline pipeline = ch.pipeline();
      
      //这个地方的 必须和服务端对应上。否则无法正常解码和编码
      pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
      pipeline.addLast("decoder", new StringDecoder());
      pipeline.addLast("encoder", new StringEncoder());
      
      // 客户端的逻辑
      pipeline.addLast("handler",new HelloClientHandler());
    }
    
  }
  
  private static class HelloClientHandler extends SimpleChannelInboundHandler<String>{

    /**
     * 当绑定到服务端的时候触发
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
      System.out.println("Client active.");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
      System.out.println("Client close.");
    }

    @Override protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
      System.out.println("Server say : " + msg);
    }
  }
}
