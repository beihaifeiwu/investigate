import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Generator {

  private JFrame frame;
  private JTextField ip;
  private JTextField port;
  private JTextField mapId;
  private ModelPanel model;
  private JTextField frequency;
  private JRadioButton loopBroadcast;
  private PushTarget pushTarget = null;

  static final Executor executor = Executors.newCachedThreadPool();

  private PushTask pushTask = null;
  private JTextField floor;
  private JTextField mac;
  private JTextField equipageNum;

  private DatagramSocket socket;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      try {
        Generator window = new Generator();
        window.frame.setVisible(true);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    executor.execute(Generator::testTheUDP);
  }

  public static void testTheUDP() {
    try {
      @SuppressWarnings("resource")
      DatagramSocket server = new DatagramSocket(55555);
      byte buf[] = new byte[1024];
      while (true) {
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        server.receive(packet);
        System.out.print("Received from:" + packet.getSocketAddress());
        System.out.println(" Data is:"
            + new String(packet.getData(), 0, packet.getLength()));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Create the application.
   */
  public Generator() {
    initialize();
    setDefaultPushTarget();
  }

  private void setDefaultPushTarget() {
    this.ip.setText("172.16.10.26");
    this.port.setText("28084");
    this.mapId.setText("1672");
    this.frequency.setText("1");
    this.floor.setText("1672");
    this.mac.setText("00:50:56:C0:00:08");
    this.equipageNum.setText("5");
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setTitle("位置生成器");
    frame.setBounds(100, 100, 800, 538);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    JPanel target = new JPanel();
    target.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u63A8\u9001\u5BF9\u8C61", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
    target.setBounds(511, 10, 263, 187);
    frame.getContentPane().add(target);
    target.setLayout(null);

    JLabel lblNewLabel = new JLabel("IP地址：");
    lblNewLabel.setBounds(10, 24, 55, 19);
    target.add(lblNewLabel);
    lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));

    ip = new JTextField();
    ip.setBounds(75, 24, 164, 21);
    target.add(ip);
    ip.setColumns(10);

    JLabel label = new JLabel("端口：");
    label.setFont(new Font("宋体", Font.PLAIN, 14));
    label.setBounds(10, 53, 49, 15);
    target.add(label);

    port = new JTextField();
    port.setBounds(75, 53, 66, 21);
    target.add(port);
    port.setColumns(10);

    JLabel lblid = new JLabel("地图ID：");
    lblid.setFont(new Font("宋体", Font.PLAIN, 14));
    lblid.setBounds(10, 84, 57, 19);
    target.add(lblid);

    mapId = new JTextField();
    mapId.setBounds(75, 84, 95, 21);
    target.add(mapId);
    mapId.setColumns(10);

    JLabel label_1 = new JLabel("频率：");
    label_1.setFont(new Font("宋体", Font.PLAIN, 14));
    label_1.setBounds(148, 58, 42, 15);
    target.add(label_1);

    frequency = new JTextField();
    frequency.setBounds(194, 55, 34, 21);
    target.add(frequency);
    frequency.setColumns(10);

    JLabel lblNewLabel_1 = new JLabel("s/c");
    lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
    lblNewLabel_1.setBounds(233, 58, 34, 15);
    target.add(lblNewLabel_1);

    JLabel label_2 = new JLabel("楼层：");
    label_2.setFont(new Font("宋体", Font.PLAIN, 14));
    label_2.setBounds(10, 117, 55, 15);
    target.add(label_2);

    floor = new JTextField();
    floor.setBounds(75, 115, 95, 21);
    target.add(floor);
    floor.setColumns(10);

    JLabel lblMac = new JLabel("MAC：");
    lblMac.setFont(new Font("宋体", Font.PLAIN, 14));
    lblMac.setBounds(11, 148, 54, 15);
    target.add(lblMac);

    mac = new JTextField();
    mac.setBounds(75, 146, 164, 21);
    target.add(mac);
    mac.setColumns(10);

    JPanel modelContainer = new JPanel();
    modelContainer.setBorder(new TitledBorder(null, "\u5750\u6807\u6A21\u578B", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
    modelContainer.setBounds(26, 10, 475, 461);
    frame.getContentPane().add(modelContainer);
    modelContainer.setLayout(null);

    model = new ModelPanel();
    model.setBackground(Color.WHITE);
    model.setBounds(10, 21, 455, 430);
    modelContainer.add(model);

    JPanel control = new JPanel();
    control.setBorder(new TitledBorder(null, "\u63A7\u5236\u9762\u677F", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
    control.setBounds(527, 288, 222, 187);
    frame.getContentPane().add(control);
    control.setLayout(null);

    JToggleButton startOrPause = new JToggleButton("开始\\暂停");
    startOrPause.addActionListener(e -> {
      if (pushTask == null || !pushTask.isRunning()) {
        if (pushTask == null) {
          try {
            pushTask = new PushTaskAggregator(getPushTarget(), getModel().getPoints(), getCloneNum());
          } catch (SocketException | CloneNotSupportedException e1) {
            e1.printStackTrace();
          }
        }
        pushTask.run();
      } else {
        pushTask.pause();
      }
    });
    startOrPause.setBounds(35, 154, 151, 23);
    control.add(startOrPause);

    JToggleButton recordOrPause = new JToggleButton("录制\\暂停");
    recordOrPause.addActionListener(e -> {
      boolean recording = getModel().isRecording();
      //System.out.println("recording: " + recording);
      getModel().setRecording(!recording);
    });
    recordOrPause.setBounds(35, 96, 151, 23);
    control.add(recordOrPause);

    JButton clear = new JButton("清空坐标");
    clear.addActionListener(e -> {
      getModel().clear();
      pushTask = null;
    });
    clear.setBounds(35, 63, 151, 23);
    control.add(clear);

    JToggleButton lockOrReleasePushTarget = new JToggleButton("锁定\\释放推送对象");
    lockOrReleasePushTarget.addActionListener(e -> {
      if (getPushTarget() == null) {
        PushTarget pushTarget1 = new PushTarget();
        // collect ip address
        String ipstr = ip.getText();
        if (ipstr != null && !ipstr.trim().equals("")) {
          pushTarget1.setIp(ipstr);
        } else {
          JOptionPane.showMessageDialog(null, "请输入目标IP地址", "警告", JOptionPane.WARNING_MESSAGE);
          return;
        }
        // collect port number
        String portStr = port.getText();
        if (portStr != null && !portStr.trim().equals("")) {
          pushTarget1.setPort(Integer.parseInt(portStr));
        } else {
          JOptionPane.showMessageDialog(null, "请输入目标端口", "警告", JOptionPane.WARNING_MESSAGE);
          return;
        }
        // collect map identify
        String idStr = mapId.getText();
        if (idStr != null && !idStr.trim().equals("")) {
          pushTarget1.setMapId(Long.parseLong(idStr));
        } else {
        }
        // collect floor
        String floorStr = floor.getText();
        if (floorStr != null && !floorStr.trim().equals("")) {
          pushTarget1.setFloor(floorStr);
        } else {
          if (pushTarget1.getMapId() == null) {
            JOptionPane.showMessageDialog(null, "请输入地图ID或者楼层代码", "警告", JOptionPane.WARNING_MESSAGE);
            return;
          }
        }
        // collect frequency
        String freStr = frequency.getText();
        if (freStr != null && !freStr.trim().equals("")) {
          pushTarget1.setFrequency(Integer.parseInt(freStr));
        }
        // collect mac
        String macStr = mac.getText();
        if (macStr != null && !macStr.trim().equals("")) {
          pushTarget1.setMac(macStr);
        } else {
          JOptionPane.showMessageDialog(null, "请输入设备mac地址", "警告", JOptionPane.WARNING_MESSAGE);
          return;

        }
        setPushTarget(pushTarget1);
        // disable all the push input fields
        enablePushInputFields(false);
      } else {
        setPushTarget(null);
        // enable all the push input fields
        enablePushInputFields(true);
      }
    });
    lockOrReleasePushTarget.setBounds(35, 30, 151, 23);
    control.add(lockOrReleasePushTarget);

    loopBroadcast = new JRadioButton("循环播放");
    loopBroadcast.setBounds(35, 125, 151, 23);
    control.add(loopBroadcast);

    JPanel panel = new JPanel();
    panel.setBorder(new TitledBorder(null, "\u6A21\u62DF\u53C2\u6570", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
    panel.setBounds(511, 207, 263, 71);
    frame.getContentPane().add(panel);
    panel.setLayout(null);

    JLabel label_3 = new JLabel("设备数量：");
    label_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    label_3.setBounds(10, 28, 77, 15);
    panel.add(label_3);

    equipageNum = new JTextField();
    equipageNum.setBounds(79, 26, 66, 21);
    panel.add(equipageNum);
    equipageNum.setColumns(10);
  }

  protected void enablePushInputFields(boolean enable) {
    ip.setEnabled(enable);
    port.setEnabled(enable);
    mapId.setEnabled(enable);
    frequency.setEnabled(enable);
    floor.setEnabled(enable);
    mac.setEnabled(enable);
  }

  protected ModelPanel getModel() {
    return model;
  }

  protected PushTarget getPushTarget() {
    return pushTarget;
  }

  protected void setPushTarget(PushTarget target) {
    this.pushTarget = target;
  }

  protected void highLightThePoint(Point point, String mac) {
    this.model.highLightPoint(point, mac);
  }

  protected boolean isLoopBroadcast() {
    return this.loopBroadcast.isSelected();
  }

  protected int getCloneNum() {
    return equipageNum.getText() != null ? Integer.parseInt(equipageNum.getText()) : 5;
  }

  protected synchronized void send(DatagramPacket packet) throws IOException {
    if (socket == null) socket = new DatagramSocket();
    socket.send(packet);
  }

  /**
   * hold the information about the push traget
   *
   * @author Pin Liu
   */
  public static class PushTarget {

    private String ip;

    private int port;

    private Long mapId;

    private String floor;

    private String mac;

    /**
     * 向目标推送的频率
     */
    private int frequency;

    public String getIp() {
      return ip;
    }

    public void setIp(String ip) {
      this.ip = ip;
    }

    public int getPort() {
      return port;
    }

    public void setPort(int port) {
      this.port = port;
    }

    public Long getMapId() {
      return mapId;
    }

    public void setMapId(Long mapId) {
      this.mapId = mapId;
    }

    public int getFrequency() {
      return frequency;
    }

    public void setFrequency(int frequency) {
      this.frequency = frequency;
    }

    public String getFloor() {
      return floor;
    }

    public void setFloor(String floor) {
      this.floor = floor;
    }

    public String getMac() {
      return mac;
    }

    public void setMac(String mac) {
      this.mac = mac;
    }

    private String randomMACAddress() {
      Random rand = new Random();
      byte[] macAddr = new byte[6];
      rand.nextBytes(macAddr);

      macAddr[0] = (byte) (macAddr[0] & (byte) 254); // zeroing last 2 bytes to
      // make it unicast and
      // locally adminstrated

      StringBuilder sb = new StringBuilder(18);
      for (byte b : macAddr) {
        if (sb.length() > 0)
          sb.append(":");

        sb.append(String.format("%02x", b));
      }
      return sb.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
      PushTarget target = new PushTarget();
      target.setFloor(floor);
      target.setFrequency(frequency);
      target.setIp(ip);
      target.setMapId(mapId);
      target.setPort(port);
      target.setMac(randomMACAddress());
      return target;
    }
  }

  interface PushTask extends Runnable {
    /**
     * 是否正在运行
     *
     * @author Pin Liu
     */
    boolean isRunning();

    /**
     * 暂停任务运行
     *
     * @author Pin Liu
     */
    void pause();
  }

  /**
   * the runnable task for the executing of the model recording
   *
   * @author Pin Liu
   */
  class DefaultPushTask implements PushTask {

    private final PushTarget target;

    private Timer timer = new Timer();

    private final List<Point> points;

    private int index = 0;

    private boolean isRunning = false;

    public DefaultPushTask(PushTarget target, List<Point> points) throws SocketException {
      this.target = target;
      this.points = points;
    }

    public DefaultPushTask(PushTarget target, List<Point> points, int startIndex) throws SocketException {
      this(target, points);
      this.index = startIndex;
    }

    @Override
    public void run() {
      executor.execute(this::timeShedule);
    }

    protected void timeShedule() {
      timer.scheduleAtFixedRate(new TimerTask() {
                                  @Override
                                  public void run() {
                                    if (index < points.size()) {
                                      push(points.get(index++));
                                      isRunning = true;
                                    } else {
                                      checkToRunOrStop();
                                    }
                                  }
                                }, new Random(System.currentTimeMillis()).nextInt(target.getFrequency() * 1000),
          target.getFrequency() == 0 ? 1000 : target.getFrequency() * 1000);
    }

    void push(Point point) {
      byte[] buf = buildBuf(point);
      DatagramPacket packet = new DatagramPacket(buf, buf.length);
      packet.setSocketAddress(new InetSocketAddress(target.getIp(), target.getPort()));
      try {
        send(packet);
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        highLightThePoint(point, target.getMac());
      }
    }

    byte[] buildBuf(Point point) {

//      Transport.LocationSource location = Transport.LocationSource.newBuilder()
//          .setClientTime(System.currentTimeMillis())
//          .setIdentity(Transport.Identity.newBuilder()
//              .setType(Transport.Identity.Type.MAC)
//              .setData(ByteString.copyFrom(macStringToBytes("e4:95:6e:40:55:1c"))))
//          .setPlanarGraphId(100l)
//          .setExpiresIn(6000)
//          .setPositioningEngine("test")
//          .setX((float) (point.x / 10 + 13526590.33017226))
//          .setY((float)(-point.y / 10 + 3663422.983396762)).build();
//
//      byte[] locationByte = new byte[43];
//      byte[] header_length = {0x7b, (byte) 129,0x2B};
//      System.arraycopy(header_length,0,locationByte,0,header_length.length);
//      System.arraycopy(longToByte(location.getPlanarGraphId()),0,locationByte,4,4);
//      System.arraycopy(location.getIdentity().getData().toByteArray(), 0, locationByte, 16, 6);
//      System.arraycopy(longToByte(location.getClientTime()), 0, locationByte, 22, 8);
//      System.arraycopy(intToByte(Float.floatToIntBits((float) location.getX())), 0, locationByte, 30, 4);
//      System.arraycopy(intToByte(Float.floatToIntBits((float) location.getY())), 0, locationByte, 34, 4);
//
//
//      return locationByte;

      Phone.Result.Builder builder = Phone.Result.newBuilder();
      builder.setFloorId(Integer.parseInt(target.getFloor()));
      builder.setPhoneMac(target.getMac());
      builder.setPositionX((float) (point.x / 10 + 13526590.554108866));
      builder.setPositionY((float) (-point.y / 10 + 3663423.9911114876));
      builder.setScene(16);
      builder.setStamp(new Long(System.currentTimeMillis()).intValue());

      StringBuilder sb = new StringBuilder();
      String mac = target.getMac();
      if (mac != null) mac = mac.replaceAll(":", "");
      sb.append(mac).append(",");
      sb.append(point.x * 4).append(",");
      sb.append(-point.y * 4).append(",");
      sb.append(target.getFloor());
      return builder.build().toByteArray();
    }


    private final byte[] macStringToBytes(String mac) {
      String[] macStr = mac.split(":");
      byte[] b = new byte[6];
      for (int i = 0; i < b.length; ++i) {
        b[i] = Integer.valueOf(macStr[i], 16).byteValue();
      }
      return b;

    }

    //long类型转成byte数组
    public byte[] longToByte(long number) {
      long temp = number;
      byte[] b = new byte[8];
      for (int i = 0; i < b.length; i++) {
        b[i] = new Long(temp & 0xff).byteValue();// 将最低位保存在最低位
        temp = temp >> 8; // 向右移8位
      }
      return b;
    }

    //int转byte数组
    public byte[] intToByte(int data) {
      byte[] bytes = new byte[4];
      bytes[0] = (byte) (data & 0xff);
      bytes[1] = (byte) ((data & 0xff00) >> 8);
      bytes[2] = (byte) ((data & 0xff0000) >> 16);
      bytes[3] = (byte) ((data & 0xff000000) >> 24);
      return bytes;
    }


    public void checkToRunOrStop() {
      if (isLoopBroadcast()) {
        index = 0;
        isRunning = true;
      } else {
        stop();
      }
    }

    public boolean isRunning() {
      return isRunning;
    }

    public void pause() {
      stop();
    }

    public void stop() {
      timer.cancel();
      isRunning = false;
      timer = new Timer();
    }
  }

  /**
   * 聚合推送任务组，便于集中操作
   *
   * @author Pin Liu
   */
  class PushTaskAggregator implements PushTask {

    List<PushTask> tasks = new ArrayList<PushTask>();

    public PushTaskAggregator() {
    }

    public PushTaskAggregator(PushTarget target, List<Point> points, int cloneNum) throws SocketException, CloneNotSupportedException {
      if (cloneNum <= 0) cloneNum = 5;
      tasks.add(new DefaultPushTask(target, points));
      Random random = new Random(System.currentTimeMillis());
      for (int i = 0; i < cloneNum - 1; i++) {
        tasks.add(new DefaultPushTask((PushTarget) target.clone(), points, random.nextInt(points.size())));
      }
    }

    @Override
    public void run() {
      tasks.stream().forEach(Runnable::run);
    }

    @Override
    public boolean isRunning() {
      return tasks.stream().anyMatch(PushTask::isRunning);
    }

    @Override
    public void pause() {
      tasks.stream().forEach(PushTask::pause);
    }

  }

  /**
   * 绘制坐标模型的面板
   *
   * @author Pin Liu
   */
  class ModelPanel extends JPanel {

    private static final long serialVersionUID = -2093810457948808431L;

    private List<Point> points = new CopyOnWriteArrayList<Point>();

    private boolean recording = false;

    private Map<String, Point> redPoints = new ConcurrentHashMap<>(5);

    public ModelPanel() {
      this.setDoubleBuffered(true);
      this.addMouseMotionListener(new MouseMotionAdapter() {

        @Override
        public void mouseDragged(MouseEvent e) {
          if (!recording) return;
          if (!points.isEmpty()) {
            Point point = points.get(points.size() - 1);
            if (e.getPoint().equals(point)) return;
          }
          points.add(e.getPoint());
          repaint();
        }

      });
    }

    public void clear() {
      points.clear();
      redPoints.clear();
      repaint();
    }

    public void highLightPoint(Point point, String mac) {
      this.redPoints.put(mac, point);
      repaint();
    }

    @Override
    public void paint(Graphics g) {
      // 先绘制面板中的其它内容
      super.paint(g);

      // 绘制点坐标
      Color color = g.getColor();
      g.setColor(Color.BLUE);
      points.stream().forEachOrdered((point) -> {
        //g.fillOval(point.x - 3, point.y - 3, 6, 6);
        g.fillOval(point.x - 2, point.y - 2, 4, 4);
      });

      // 绘制高亮坐标点
      if (redPoints != null) {
        g.setColor(Color.RED);
        for (Map.Entry<String, Point> entry : redPoints.entrySet()) {
          String mac = entry.getKey();
          Point redPoint = entry.getValue();
          if (redPoint != null) {
            //System.out.println("#########"+redPoint);
            g.clearRect(redPoint.x - 2, redPoint.y - 2, 4, 4);
            g.fillOval(redPoint.x - 2, redPoint.y - 2, 4, 4);
            g.drawOval(redPoint.x - 4, redPoint.y - 4, 8, 8);
            g.drawOval(redPoint.x - 6, redPoint.y - 6, 12, 12);

            // 在高亮的当前点上绘制出mac地址
            g.drawString(mac, redPoint.x + 6, redPoint.y - 6);
          }
        }
      }
      g.setColor(color);

    }

    public List<Point> getPoints() {
      return points;
    }

    public void setPoints(List<Point> points) {
      this.points = points;
    }

    public boolean isRecording() {
      return recording;
    }

    public void setRecording(boolean recording) {
      this.recording = recording;
    }

  }
}
