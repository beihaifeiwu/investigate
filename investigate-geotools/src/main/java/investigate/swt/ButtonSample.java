package investigate.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

/**
 * Created by liupin on 2016/4/9.
 */
public class ButtonSample {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    showPushButton(shell);
    showRadioButton(shell);
    showToggleButton(shell);
    showCheckButton(shell);
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();

  }

  private static void showPushButton(Shell shell) {
    shell.setText("SWT.PUSH");

    RowLayout layout = new RowLayout(SWT.HORIZONTAL);
    layout.marginWidth = 10;
    shell.setLayout(layout);

    Button bt1 = new Button(shell, SWT.PUSH | SWT.LEFT);
    // 设置文本
    bt1.setText("SWT.LEFT");
    // 设置悬浮提示
    bt1.setToolTipText("SWT.LEFT");

    Button bt2 = new Button(shell, SWT.PUSH | SWT.RIGHT);
    bt2.setText("SWT.RIGHT");
    bt2.setToolTipText("SWT.RIGHT");

    Button bt3 = new Button(shell, SWT.PUSH | SWT.CENTER);
    bt3.setText("SWT.CENTER");
    bt3.setToolTipText("SWT.CENTER");

    Button bt4 = new Button(shell, SWT.PUSH | SWT.FLAT);
    bt4.setText("SWT.FLAT");
    bt4.setToolTipText("SWT.FLAT");

    Button bt5 = new Button(shell, SWT.PUSH | SWT.BORDER);
    bt5.setText("SWT.BORDER");
    bt5.setToolTipText("SWT.BORDER");

    shell.layout();
    shell.pack();
    shell.open();
  }

  private static void showRadioButton(Shell parent) {
    Shell shell = new Shell(parent);
    shell.setText("SWT.RADIO");

    RowLayout layout = new RowLayout(SWT.VERTICAL);
    layout.marginWidth = 10;
    shell.setLayout(layout);

    // 这是第一组单选按钮
    Group group1 = new Group(shell, SWT.SHADOW_ETCHED_OUT);
    group1.setLayout(new FillLayout(SWT.VERTICAL));
    group1.setText("这是一组样式");

    Button bt1 = new Button(group1, SWT.RADIO | SWT.LEFT);
    bt1.setText("SWT.LEFT");
    bt1.setToolTipText("SWT.LEFT");

    Button bt2 = new Button(group1, SWT.RADIO | SWT.RIGHT);
    bt2.setText("SWT.RIGHT");
    bt2.setToolTipText("SWT.RIGHT");

    Button bt3 = new Button(group1, SWT.RADIO | SWT.CENTER);
    bt3.setText("SWT.CENTER");
    bt3.setToolTipText("SWT.CENTER");

    // 这是第二组单选按钮
    Group group2 = new Group(shell, SWT.SHADOW_ETCHED_OUT);
    group2.setLayout(new FillLayout(SWT.VERTICAL));
    group2.setText("这是另一组样式");

    Button bt4 = new Button(group2, SWT.RADIO | SWT.FLAT);
    bt4.setText("SWT.FLAT");
    bt4.setToolTipText("SWT.FLAT");
    bt4.setSelection(true);

    Button bt5 = new Button(group2, SWT.RADIO | SWT.BORDER);
    bt5.setText("SWT.BORDER");
    bt5.setToolTipText("SWT.BORDER");

    Button bt6 = new Button(group2, SWT.RADIO);
    bt6.setText("SWT.RADIO");
    bt6.setToolTipText("SWT.RADIO");

    shell.layout();
    shell.pack();
    shell.open();
  }

  private static void showToggleButton(Shell parent) {
    Shell shell = new Shell(parent);
    shell.setText("SWT.TOGGLE");

    RowLayout layout = new RowLayout(SWT.HORIZONTAL);
    layout.marginWidth = 10;
    shell.setLayout(layout);

    Button bt1 = new Button(shell, SWT.TOGGLE | SWT.LEFT);
    bt1.setText("SWT.LEFT");
    bt1.setToolTipText("SWT.LEFT");

    Button bt2 = new Button(shell, SWT.TOGGLE | SWT.FLAT);
    bt2.setText("SWT.FLAT");
    bt2.setToolTipText("SWT.FLAT");

    Button bt3 = new Button(shell, SWT.TOGGLE | SWT.BORDER);
    bt3.setText("SWT.BORDER");
    bt3.setToolTipText("SWT.BORDER");

    shell.layout();
    shell.pack();
    shell.open();
  }

  private static void showCheckButton(Shell parent) {
    Shell shell = new Shell(parent);
    shell.setText("SWT.CHECK");

    RowLayout layout = new RowLayout(SWT.VERTICAL);
    layout.marginWidth = 10;
    shell.setLayout(layout);

    Group group = new Group(shell, SWT.SHADOW_ETCHED_OUT);
    group.setLayout(new FillLayout(SWT.VERTICAL));
    group.setText("不同样式的多选按钮");

    Button bt1 = new Button(group, SWT.CHECK | SWT.LEFT);
    bt1.setText("SWT.LEFT");
    bt1.setToolTipText("SWT.LEFT");

    Button bt2 = new Button(group, SWT.CHECK | SWT.RIGHT);
    bt2.setText("SWT.RIGHT");
    bt2.setToolTipText("SWT.RIGHT");

    Button bt3 = new Button(group, SWT.CHECK | SWT.CENTER);
    bt3.setText("SWT.CENTER");
    bt3.setToolTipText("SWT.CENTER");

    Button bt4 = new Button(group, SWT.CHECK | SWT.FLAT);
    bt4.setText("SWT.FLAT");
    bt4.setToolTipText("SWT.FLAT");

    Button bt5 = new Button(group, SWT.CHECK | SWT.BORDER);
    bt5.setText("SWT.BORDER");
    bt5.setToolTipText("SWT.BORDER");

    shell.layout();
    shell.pack();
    shell.open();
  }
}
