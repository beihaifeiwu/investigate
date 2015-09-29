package geotools.generator;

import java.awt.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 聚合推送任务组，便于集中操作
 *
 * @author Pin Liu
 */
public class PushTaskAggregator implements PushTask {

  List<PushTask> tasks = new ArrayList<>();

  public PushTaskAggregator() {
  }

  public PushTaskAggregator(PushTarget target, List<Point> points, int cloneNum, Generator generator) throws SocketException, CloneNotSupportedException {
    if (cloneNum <= 0) cloneNum = 5;
    tasks.add(new DefaultPushTask(target, points, generator));
    Random random = new Random(System.currentTimeMillis());
    for (int i = 0; i < cloneNum - 1; i++) {
      tasks.add(new DefaultPushTask((PushTarget) target.clone(), points, random.nextInt(points.size()), generator));
    }
  }

  @Override
  public void run() {
    tasks.stream().forEach(Runnable::run);
  }

  @Override
  public boolean isRunning() {
    if (tasks.stream().anyMatch(PushTask::isRunning)) return true;
    else return false;
  }

  @Override
  public void pause() {
    tasks.stream().forEach(PushTask::pause);
  }

}