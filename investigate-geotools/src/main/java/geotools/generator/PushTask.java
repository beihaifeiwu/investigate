package geotools.generator;

interface PushTask extends Runnable {
    /**
     * 是否正在运行
     * @author Pin Liu
     */
    boolean isRunning();
    
    /**
     * 暂停任务运行
     * @author Pin Liu
     */
    void pause();
  }
  