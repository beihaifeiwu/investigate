package com.freetmp.investigate.graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.IOException;

/**
 * Created by liupin on 2016/4/6.
 */
public class MyFilterUI extends JPanel implements ActionListener {
  private JButton grayBtn;
  private JButton binaryBtn;
  private JButton blurBtn;
  private JButton zoomBtn;
  private JButton browserBtn;

  private JPanel imagePanel = new JPanel() {
    @Override protected void paintComponent(Graphics g) {
      if (srcImage == null) return;

      Graphics2D graphics2D = (Graphics2D) g;



      graphics2D.drawImage(srcImage, 2, 2,
          srcImage.getWidth() <= getWidth() / 2 - 2 ? srcImage.getWidth() : getWidth() / 2 - 2,
          srcImage.getHeight() <= getHeight() - 2 ? srcImage.getHeight() : getHeight() - 2,
          null);

      if (destImage == null) return;
      graphics2D.drawImage(destImage,
          srcImage.getWidth() <= getWidth() / 2 - 2 ? srcImage.getWidth() : getWidth() / 2 - 2 + 4,
          2,
          destImage.getWidth() <= getWidth() / 2 - 2 ? destImage.getWidth() : getWidth() / 2 - 2,
          destImage.getHeight() <= getHeight() - 2 ? destImage.getHeight() : getHeight() - 2,
          null);
    }
  };

  BufferedImage srcImage;
  BufferedImage destImage;

  public MyFilterUI() {
    super();

    setupUI();

    grayBtn.addActionListener(this);
    binaryBtn.addActionListener(this);
    blurBtn.addActionListener(this);
    zoomBtn.addActionListener(this);

    browserBtn.addActionListener(e -> {
      JFileChooser chooser = new JFileChooser();
      chooser.setFileFilter(new FileNameExtensionFilter("PNG and JPG Images", "png", "jpg"));
      int returnVal = chooser.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        try {
          srcImage = ImageIO.read(chooser.getSelectedFile());
          repaint();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    });
  }

  @Override public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "灰度":
        destImage = doColorGray(srcImage);
        break;
      case "黑白":
        destImage = doBinaryImage(srcImage);
        break;
      case "模糊":
        destImage = doBlur(srcImage);
        break;
      case "缩放":
        destImage = doScale(srcImage, 0.5, 0.5);
        break;
    }
    repaint();
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("MyFilterUI");
    frame.setContentPane(new MyFilterUI());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(580, 380));
    frame.setResizable(true);
    frame.pack();
    frame.setVisible(true);
  }

  public BufferedImage doColorGray(BufferedImage bi) {
    ColorConvertOp filterObj = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
    return filterObj.filter(bi, null);
  }

  public BufferedImage doBinaryImage(BufferedImage bi) {
    bi = doColorGray(bi);
    byte[] threshold = new byte[256];
    for (int i = 0; i < threshold.length; i++) {
      threshold[i] = (byte) ((i < 128) ? 0 : 255);
    }
    BufferedImageOp thresholdOp = new LookupOp(new ByteLookupTable(0, threshold), null);
    return thresholdOp.filter(bi, null);
  }

  public BufferedImage doBlur(BufferedImage bi) {
    // fix issue - unable to convolve src image
    if (bi.getType() == BufferedImage.TYPE_3BYTE_BGR) {
      bi = convertType(bi, BufferedImage.TYPE_INT_RGB);
    }

    float ninth = 1.0f / 9.0f;
    float[] blurKernel = {ninth, ninth, ninth, ninth, ninth, ninth, ninth, ninth, ninth};
    BufferedImageOp blurFilter = new ConvolveOp(new Kernel(3, 3, blurKernel));
    return blurFilter.filter(bi, null);
  }

  public BufferedImage convertType(BufferedImage bi, int type) {
    ColorConvertOp cco = new ColorConvertOp(null);
    BufferedImage dest = new BufferedImage(bi.getWidth(), bi.getHeight(), type);
    cco.filter(bi, dest);
    return dest;
  }

  public BufferedImage doScale(BufferedImage bi, double sx, double sy) {
    AffineTransformOp atfFilter = new AffineTransformOp(AffineTransform.getScaleInstance(sx, sy), AffineTransformOp.TYPE_BILINEAR);
    // 计算缩放后图像的宽和高
    int nw = (int) (bi.getWidth() * sx);
    int nh = (int) (bi.getHeight() * sy);
    BufferedImage result = new BufferedImage(nw, nh, BufferedImage.TYPE_3BYTE_BGR);
    // 实现图像缩放
    atfFilter.filter(bi, result);
    return result;
  }

  private void setupUI() {

    this.setLayout(new BorderLayout(0, 0));
    imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    this.add(imagePanel, BorderLayout.CENTER);
    final JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    this.add(panel2, BorderLayout.SOUTH);
    grayBtn = new JButton();
    grayBtn.setText("灰度");
    panel2.add(grayBtn);
    binaryBtn = new JButton();
    binaryBtn.setText("黑白");
    panel2.add(binaryBtn);
    blurBtn = new JButton();
    blurBtn.setText("模糊");
    panel2.add(blurBtn);
    zoomBtn = new JButton();
    zoomBtn.setText("缩放");
    panel2.add(zoomBtn);
    browserBtn = new JButton();
    browserBtn.setText("选择...");
    panel2.add(browserBtn);
  }
}
