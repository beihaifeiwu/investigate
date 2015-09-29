package geotools.generator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
   * 绘制坐标模型的面板
   * @author Pin Liu
   */
public class ModelPanel extends JPanel {

    private static final long serialVersionUID = -2093810457948808431L;

    private List<Point> points = new CopyOnWriteArrayList<>();
    
    private boolean recording = false;
    
    private Map<String, Point> redPoints = new ConcurrentHashMap<>(5);
       
    public ModelPanel(){
      this.setDoubleBuffered(true);
      this.addMouseMotionListener(new MouseMotionAdapter() {

        @Override
        public void mouseDragged(MouseEvent e) {
          if(!recording) return;
          if(!points.isEmpty()){
            Point point = points.get(points.size()-1);
            if(e.getPoint().equals(point)) return;
          }
          points.add(e.getPoint());
          repaint();
        }
        
      });
    }
    
    public void clear(){
      points.clear();
      redPoints.clear();
      repaint();
    }
    
    public void highLightPoint(Point point, String mac){
      this.redPoints.put(mac,point);
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
      if(redPoints != null){
        g.setColor(Color.RED);
        for(Map.Entry<String, Point> entry : redPoints.entrySet()){
          String mac = entry.getKey();
          Point redPoint = entry.getValue();
          if(redPoint != null){
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