package com.freetmp.investigate.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by LiuPin on 2016/4/21.
 */
public class XmlCompare {

  public static void main(String[] args) throws Exception {
    if (args.length >= 2) {
      compare(args);

    } else {
      System.out.println("Please specify the xml and log file locations");
    }
  }

  private static void compare(String[] args) throws Exception {
    HashSet<EntityData> xml = null;
    HashSet<EntityData> log = null;
    HashSet<EntityData> diff = new HashSet<>();

    for (int i = 0; i < 2; i++) {
      if (args[i].endsWith("xml")) {
        xml = parseXml(new File(args[i]));
      }
      if (args[i].endsWith("log")) {
        log = parseLog(new File(args[i]));
      }
    }

    if (xml == null || log == null) return;

    System.out.println("Xml contains mac size: " + xml.size());
    System.out.println("Log contains mac size: " + log.size());

    HashSet<EntityData> finalLog = log;
    xml.parallelStream().forEach(ed -> {
      if (!finalLog.contains(ed)) diff.add(ed);
    });

    List<EntityData> mnv = diff.parallelStream().filter(e -> (e.x.equals("0") || e.x.equals("-0")) && (e.y.equals("0") || e.y.equals("-0"))).collect(Collectors.toList());
    System.out.println("Missing mac with coordinate not valid size: " + mnv.size());

    List<EntityData> mv = diff.parallelStream().filter(e -> !e.x.equals("0") && !e.x.equals("-0") && !e.y.equals("0") && !e.y.equals("-0")).collect(Collectors.toList());
    System.out.println("Missing mac with coordinate valid size: " + mv.size());
    System.out.println("Missing mac is: ");
    mv.forEach(System.out::println);
  }

  public static HashSet<EntityData> parseXml(File file) throws Exception {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document document = db.parse(file);
    NodeList nodes = document.getElementsByTagName("history_MuPositionLog");
    HashSet<EntityData> set = new HashSet<>();
    for (int i = 0; i < nodes.getLength(); i++) {
      Element e = (Element) nodes.item(i);
      EntityData ed = new EntityData();
      ed.mac = e.getElementsByTagName("TagMac").item(0).getTextContent();
      ed.x = e.getElementsByTagName("X").item(0).getTextContent();
      ed.y = e.getElementsByTagName("Y").item(0).getTextContent();
      ed.time = e.getElementsByTagName("WriteTime").item(0).getTextContent();
      set.add(ed);
    }
    return set;
  }

  public static HashSet<EntityData> parseLog(File file) throws Exception {
    FileInputStream fis = new FileInputStream(file);
    BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
    HashSet<EntityData> set = new HashSet<>();
    reader.lines().forEach(line -> {
      EntityData ed = new EntityData();
      for (String s : line.split(" ")) {
        if (s.startsWith("mac->")) ed.mac = s.split("->")[1];
        if (s.startsWith("current_time->")) ed.time = s.split("->")[1];
      }
      if (ed.mac != null) set.add(ed);
    });
    return set;
  }

  public static class EntityData {
    String mac;
    String x;
    String y;
    String time;

    @Override public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      EntityData that = (EntityData) o;

      return mac != null ? mac.equals(that.mac) : that.mac == null;

    }

    @Override public int hashCode() {
      return mac != null ? mac.hashCode() : 0;
    }

    @Override public String toString() {
      final StringBuilder sb = new StringBuilder("EntityData{");
      sb.append("mac='").append(mac).append('\'');
      sb.append(", x='").append(x).append('\'');
      sb.append(", y='").append(y).append('\'');
      sb.append(", time='").append(time).append('\'');
      sb.append('}');
      return sb.toString();
    }
  }
}
