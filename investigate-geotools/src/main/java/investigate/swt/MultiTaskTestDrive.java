package investigate.swt;

import investigate.swt.util.ImageFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by liupin on 2016/4/21.
 */
public class MultiTaskTestDrive {
  public static void main(String[] args) {
    Display display = Display.getDefault();
    MutiTaskGUI mutiTask = new MutiTaskGUI();
    mutiTask.getShell().open();
    while (!mutiTask.getShell().isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    ImageFactory.dispose();
    display.dispose();
  }


  static class MutiTaskGUI {
    private Shell shell = null;
    private Table table = null;

    public MutiTaskGUI() {
      //构造方法中调用初始化窗口的方法
      init();
    }

    //初始化窗口方法
    public void init() {
      shell = new Shell();
      shell.setLayout(new GridLayout());
      shell.setText("多线程");
      Button bt = new Button(shell, SWT.NONE);
      bt.setText("开始一个任务");
      // 创建表格对象
      table = new Table(shell, SWT.BORDER);
      table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
      table.setHeaderVisible(true);
      table.setLinesVisible(true);
      String[] header = new String[]{"任务", "进度", "操作"};
      // 创建表头
      for (int i = 0; i < 3; i++) {
        TableColumn col = new TableColumn(table, SWT.NONE);
        col.setText(header[i]);
      }
      //设置表头宽度
      table.getColumn(0).setWidth(80);
      table.getColumn(1).setWidth(150);
      table.getColumn(2).setWidth(80);
      shell.pack();
      //注册创建任务按钮事件
      bt.addSelectionListener(new SelectionAdapter() {
        //当单击创建一个任务按钮时
        public void widgetSelected(SelectionEvent e) {
          //首先创建一个Task对象
          Task task = new Task(table);
          //然后再表格中添加一行
          task.createTableItem();
          //最后启动该任务，该任务为一个线程
          task.start();
        }
      });
    }

    //获得和设置属性的getter和setter方法
    public Shell getShell() {
      return shell;
    }

    public void setShell(Shell shell) {
      this.shell = shell;
    }

    public Table getTable() {
      return table;
    }

    public void setTable(Table table) {
      this.table = table;
    }
  }

  static class Task extends Thread {
    //该类的一些属性
    private Table table = null;
    //是否停止的标志
    private boolean done = false;
    //声明进度条对象
    private ProgressBar bar = null;
    private int min = 0;
    private int max = 100;

    public Task(Table table) {
      this.table = table;
    }

    //创建表格中的一行
    public void createTableItem() {
      TableItem item = new TableItem(table, SWT.NONE);
      item.setText(this.getName());
      item.setImage(ImageFactory.loadImage(table.getDisplay(), ImageFactory.PROGRESS_TASK));
      // 创建一个进度条
      bar = new ProgressBar(table, SWT.NONE);
      bar.setMinimum(min);
      bar.setMaximum(max);
      // 创建一个可编辑的表格对象
      TableEditor editor = new TableEditor(table);
      editor.grabHorizontal = true;
      editor.grabVertical = true;
      // 将进度条绑定到第二列中
      editor.setEditor(bar, item, 1);
      //重新创建一个可编辑的表格对象
      editor = new TableEditor(table);
      editor.grabHorizontal = true;
      editor.grabVertical = true;
      //创建一个按钮
      Button stop = new Button(table, SWT.NONE);
      stop.setText("Stop");
      editor.setEditor(stop, item, 2);
      stop.addSelectionListener(new SelectionAdapter() {
        //当停止按钮按下时，设置停止标记true
        public void widgetSelected(SelectionEvent e) {
          if (!isDone())
            setDone(true);
        }
      });
    }

    //线程方法体，与前面单个的进度条的程序类似
    public void run() {
      for (int i = min; i < max; i++) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        table.getDisplay().asyncExec(new Runnable() {
          public void run() {
            if (bar.isDisposed())
              return;
            bar.setSelection(bar.getSelection() + 1);
          }
        });
        //如果停止，则结束该线程
        if (isDone()) {
          break;
        }
      }
    }

    //获得和设置属性的getter和setter方法
    public Table getTable() {
      return table;
    }

    public void setTable(Table table) {
      this.table = table;
    }

    public boolean isDone() {
      return done;
    }

    public void setDone(boolean done) {
      this.done = done;
    }
  }

}