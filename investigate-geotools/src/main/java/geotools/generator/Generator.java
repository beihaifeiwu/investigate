package geotools.generator;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
  
  public static void testTheUDP(){
    try {
      @SuppressWarnings("resource")
      DatagramSocket server = new DatagramSocket(55555);
      byte buf[] = new byte[1024];
      while(true){
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
  
  private void setDefaultPushTarget(){
    this.ip.setText("172.16.10.26");
    this.port.setText("28084");
    this.mapId.setText("1752");
    this.frequency.setText("4");
    this.floor.setText("1752");
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
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
      if(pushTask == null || !pushTask.isRunning()){
        if(pushTask == null){
          try {
            pushTask = new PushTaskAggregator(getPushTarget(), getModel().getPoints(),getCloneNum(), this);
          } catch (SocketException | CloneNotSupportedException e1) {
            e1.printStackTrace();
          }
        }
        pushTask.run();
      }else{
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
      if(getPushTarget() == null){
        PushTarget pushTarget1 = new PushTarget();
        // collect ip address
        String ipstr = ip.getText();
        if(ipstr != null && !ipstr.trim().equals("")){
          pushTarget1.setIp(ipstr);
        }else{
          JOptionPane.showMessageDialog(null, "请输入目标IP地址","警告",JOptionPane.WARNING_MESSAGE);
          return;
        }
        // collect port number
        String portStr = port.getText();
        if(portStr != null && !portStr.trim().equals("")){
          pushTarget1.setPort(Integer.parseInt(portStr));
        }else{
          JOptionPane.showMessageDialog(null, "请输入目标端口","警告",JOptionPane.WARNING_MESSAGE);
          return;
        }
        // collect map identify
        String idStr = mapId.getText();
        if(idStr != null && !idStr.trim().equals("")){
          pushTarget1.setMapId(Long.parseLong(idStr));
        }else{
        }
        // collect floor
        String floorStr = floor.getText();
        if(floorStr != null && !floorStr.trim().equals("")){
          pushTarget1.setFloor(floorStr);
        }else{
          if(pushTarget1.getMapId() == null){
            JOptionPane.showMessageDialog(null, "请输入地图ID或者楼层代码","警告",JOptionPane.WARNING_MESSAGE);
            return;
          }
        }
        // collect frequency
        String freStr = frequency.getText();
        if(freStr != null && !freStr.trim().equals("")){
          pushTarget1.setFrequency(Integer.parseInt(freStr));
        }
        // collect mac
        String macStr = mac.getText();
        if(macStr != null && !macStr.trim().equals("")){
          pushTarget1.setMac(macStr);
        }else{
          JOptionPane.showMessageDialog(null, "请输入设备mac地址","警告",JOptionPane.WARNING_MESSAGE);
          return;

        }
        setPushTarget(pushTarget1);
        // disable all the push input fields
        enablePushInputFields(false);
      }else{
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
  
  protected PushTarget getPushTarget(){
    return pushTarget;
  }
  
  protected void setPushTarget(PushTarget target){
    this.pushTarget = target;
  }
  
  protected void highLightThePoint(Point point, String mac){
    this.model.highLightPoint(point, mac);
  }
  
  protected boolean isLoopBroadcast(){
    return this.loopBroadcast.isSelected();
  }
  
  protected int getCloneNum(){
    return equipageNum.getText() != null ? Integer.parseInt(equipageNum.getText()) : 5;
  }
  
  protected synchronized void send(DatagramPacket packet) throws IOException{
    if(socket == null) socket = new DatagramSocket();
    socket.send(packet);
  }
}
