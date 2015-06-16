package com.freetmp.investigate.netty;

import java.net.InetAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Netty服务器端Demo
 *
 * @author Pin Liu
 * @编写日期 2015年1月5日上午10:26:13
 */
public class HelloServer {

  public static void main(String args[]) {
    // EventLoop 代替原来的 ChannelFactory
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    ServerBootstrap serverBootstrap = new ServerBootstrap();
    // Server端采用简洁的连写方式，client端才用分段普通写法
    try {
      serverBootstrap.group(bossGroup, workerGroup)
          .channel(NioServerSocketChannel.class)
          .childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
              // 以("\n")为结尾分割的 解码器
              ch.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()))
                  // 字符串解码 和 编码
                  .addLast("decoder", new StringDecoder())
                  .addLast("encoder", new StringEncoder())
                      // 自己的逻辑Handler
                  .addLast("handler", new HelloServerHandler());
            }

          }).option(ChannelOption.SO_KEEPALIVE, true);

      ChannelFuture f = serverBootstrap.bind(8000).sync();
      f.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }

  private static class HelloServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 当有客户端绑定到服务端的时候触发
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
      System.out.println("RemoteAddress : " + ctx.channel().remoteAddress() + " active !");
      ctx.writeAndFlush("Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");
    }

    @Override protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
      //收到消息直接打印输出
      System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);
      //返回客户端消息 - 我已经收到了你的消息
      ctx.writeAndFlush("Received your message !\n");
    }
  }

}
