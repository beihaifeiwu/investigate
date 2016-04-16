package investigate.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class ProgressBarSample {

  public static void main(String[] args) {
    final Display display = new Display();
    Shell shell = new Shell(display);
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
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();

  }

}
