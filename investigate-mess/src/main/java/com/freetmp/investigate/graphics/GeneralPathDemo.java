package com.freetmp.investigate.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Created by liupin on 2016/4/6.
 */
public class GeneralPathDemo extends JPanel {

  public GeneralPathDemo() {
    super();
  }

  @Override protected void paintComponent(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;

    // 反锯齿
    g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // 五角星的五个点坐标
    int x1 = this.getWidth() / 2;
    int y1 = 20;
    int x2 = this.getWidth() / 5;
    int y2 = this.getHeight() - 20;
    int x3 = x2 * 4;
    int y3 = this.getHeight() - 20;
    int x4 = 20;
    int y4 = this.getHeight() / 3;
    int x5 = this.getWidth() - 20;
    int y5 = y4;

    // 定义画点的顺序
    int x1Points[] = {x1, x2, x5, x4, x3};
    int[] y1Points = new int[]{y1, y2, y5, y4, y3};

    // 设置填充颜色
    g2D.setPaint(Color.RED);

    // 实例化GeneralPath对象
    GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, x1Points.length);

    // 锚地开始第一个点
    polygon.moveTo(x1Points[0], y1Points[0]);

    // 顺序画出剩下的点
    for (int i = 1; i < x1Points.length; i++) {
      polygon.lineTo(x1Points[i], y1Points[i]);
    }

    // 调用closePath形成一个封闭几何形状
    polygon.closePath();

    // 绘制它
    g2D.draw(polygon);

    // 释放资源
    g2D.dispose();
  }

  public static void main(String[] args) {
    JFrame ui = new JFrame("Demo Graphics");
    ui.setDefaultCloseOperation(EXIT_ON_CLOSE);
    ui.getContentPane().setLayout(new BorderLayout());
    ui.getContentPane().add(new GeneralPathDemo(), BorderLayout.CENTER);
    ui.setPreferredSize(new Dimension(380, 380));
    ui.pack();
    ui.setVisible(true);
  }
}
