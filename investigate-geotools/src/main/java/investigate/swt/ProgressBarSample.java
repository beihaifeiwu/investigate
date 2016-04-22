package investigate.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class ProgressBarSample {

  public static void main(String[] args) {
    final Display display = new Display();
    Shell shell = new Shell(display);
    showProgressBar(display, shell);
    showProgressBarComplex(shell);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();

  }

  private static void showProgressBar(final Display display, Shell shell) {
    shell.setText("ProgressBar");
    shell.setLayout(new GridLayout());

    //创建滚动条实例
    final ProgressBar progressBar = new ProgressBar(shell, SWT.HORIZONTAL);
    progressBar.setMaximum(100);//设置最大值
    progressBar.setMinimum(0);//设置最小值
    final int maximum = progressBar.getMaximum();//获取最大值
    final int minimus = progressBar.getMinimum();//获取最小值
    //创建一个线程，该线程每0.1秒更新一次滚动条的值
    Runnable runnable = new Runnable() {
      public void run() {
        //线程运行的主体
        for (int i = minimus; i < maximum; i++) {
          try {
            //让线程睡眠0.1秒
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          //如果使用以下以行代码更新滚动条的值，运行时会出现Invalid thread access
          //progressBar.setSelection(progressBar.getSelection() + 1);
          //让UI线程更新滚动条的值
          if (!display.isDisposed()) {
            display.asyncExec(new Runnable() {
              //这也是一个线程，该线程的功能是更新滚动条的值，一瞬间就结束了
              public void run() {
                if (progressBar.isDisposed())
                  return;
                progressBar.setSelection(progressBar.getSelection() + 1);
              }
            });
          } else {
            break;
          }
        }
      }
    };
    //启动这个线程
    new Thread(runnable).start();

    shell.pack();
  }

  // 表示线程的状态
  static boolean bCancel = false;

  private static void showProgressBarComplex(Shell parent){
    final Shell shell = new Shell(parent);
    shell.setText("ProgressBar");
    shell.setLayout(new GridLayout(2, false));
    // 开始和取消按钮
    final Button start = new Button(shell, SWT.NONE);
    start.setText("开始");
    Button cancel = new Button(shell, SWT.NONE);
    cancel.setText("取消");
    // 创建滚动条实例
    final ProgressBar progressBar = new ProgressBar(shell, SWT.HORIZONTAL);
    GridData data = new GridData();
    data.horizontalSpan = 2;
    data.grabExcessHorizontalSpace = true;
    progressBar.setLayoutData(data);
    progressBar.setMaximum(100);// 设置最大值
    progressBar.setMinimum(0);// 设置最小值
    final int maximum = progressBar.getMaximum();// 获取最大值
    final int minimus = progressBar.getMinimum();// 获取最小值
    // 为开始按钮注册事件
    start.addSelectionListener(new SelectionAdapter() {
      // 当单击开始按钮时
      public void widgetSelected(SelectionEvent e) {
        // 首先设置开始按钮不可用状态
        start.setEnabled(false);
        // 创建更新进度条的线程
        Runnable runnable = new Runnable() {
          public void run() {
            for (int i = minimus; i < maximum; i++) {
              try {
                Thread.sleep(100);
              } catch (InterruptedException e) {
              }
              // 注意在更新进度条时加上了判断线程状态的条件
              shell.getDisplay().asyncExec(new Runnable() {
                public void run() {
                  if (progressBar.isDisposed())
                    return;
                  // 如果此时取消了线程，将进度条设置为初始状态
                  if (bCancel) {
                    progressBar.setSelection(0);
                  }
                  progressBar.setSelection(progressBar.getSelection() + 1);
                }
              });
              // 如果此时取消了线程，结束该循环，这个线程也就结束了
              // 并重置线程状态
              if (bCancel) {
                bCancel = false;
                break;
              }
            }
          }
        };
        // 启动这个线程
        new Thread(runnable).start();
      }
    });
    // 注册取消按钮事件
    cancel.addSelectionListener(new SelectionAdapter() {
      // 当单击取消按钮时
      public void widgetSelected(SelectionEvent e) {
        // 如果此时线程在执行，则取消线程并将开始按钮置为可用
        if (!bCancel) {
          bCancel = true;
          start.setEnabled(true);
        }
      }
    });
    shell.pack();
    shell.open();
  }

}
