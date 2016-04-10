package investigate.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class EventSample {

  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    showSelectionEvent(shell);
    showKeyEvent(shell);
    showModifyEvent(shell);
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) display.sleep();
    }
    display.dispose();
  }

  private static void showSelectionEvent(Shell shell) {
    shell.setLayout(new FillLayout());
    shell.setText("Simple Event");

    final List list = new List(shell, SWT.NONE);
    for (int i = 0; i < 10; i++)
      list.add("Item" + i);
    list.setData(shell);
    SelectionListener listener = new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        System.out.println("widgetSelected事件");
      }

      public void widgetDefaultSelected(SelectionEvent e) {
        System.out.println("widgetDefaultSelected事件");

      }
    };
    list.addSelectionListener(listener);
    list.removeSelectionListener(listener);
    shell.pack();
    shell.open();
  }

  public class MyAdapter extends SelectionAdapter {

    public void widgetSelected(SelectionEvent e) {
    }

    public void widgetDefaultSelected(SelectionEvent e) {
    }

  }

  public static void showKeyEvent(Shell parent) {
    Shell shell = new Shell(parent);
    shell.setText("可移动的按钮");
    Button button = new Button(shell, SWT.NONE);
    button.setBounds(5, 5, 50, 25);
    button.setText("移动我");
    // 注册键盘事件监听器
    button.addKeyListener(new KeyListener() {
      // 键盘按下后
      public void keyPressed(KeyEvent e) {
        int bits = SWT.CTRL | SWT.ALT | SWT.SHIFT;
        if ((e.stateMask & bits) != 0) {
          e.doit = false;//取消该事件
          return;//并返回
        }
        // 获得触发该事件的控件对象
        Control control = (Control) e.widget;
        // 获得该控件的位置和大小
        Rectangle current = control.getBounds();
        if (e.keyCode == SWT.ARROW_DOWN)// 如果按下了“下”按键
          current.y++;// 下移一个像素
        else if (e.keyCode == SWT.ARROW_UP)// 如果按下了“上”按键
          current.y--;// 上移一个像素
        else if (e.keyCode == SWT.ARROW_LEFT)// 如果按下了“左”按键
          current.x--;// 左移一个像素
        else if (e.keyCode == SWT.ARROW_RIGHT)// 如果按下了“右”按键
          current.x++;// 右移一个像素
        // 重新设置控件的位置
        control.setBounds(current);
      }

      public void keyReleased(KeyEvent e) {

      }
    });

    // 创建监听器对象
    MoveButtonListener listener = new MoveButtonListener(button, shell);
    // 注册鼠标监听器
    button.addMouseListener(listener);
    // 注册鼠标跟踪监听器
    button.addMouseTrackListener(listener);

    shell.setSize(200, 150);
    shell.open();
  }

  //监听器类，实现了鼠标监听器的接口
  public static class MoveButtonListener implements MouseMoveListener, MouseListener, MouseTrackListener {

    Button button;
    Shell shell;

    public MoveButtonListener(Button button, Shell shell) {
      this.button = button;
      this.shell = shell;
    }

    //当鼠标移动时
    public void mouseMove(MouseEvent e) {
      //首先当前鼠标的位置转化为窗口的坐标位置
      Point convertPoint = Display.getCurrent().map(button, shell, e.x, e.y);
      Rectangle current = button.getBounds();
      //重新设置按钮的位置，使之跟随鼠标移动
      button.setBounds(convertPoint.x, convertPoint.y, current.width, current.height);
    }

    //鼠标双击事件
    public void mouseDoubleClick(MouseEvent e) {
      System.out.println(e.toString());
      System.out.println("双击了该按钮");
    }

    //当鼠标按下时，为按钮注册鼠标移动监听器
    public void mouseDown(MouseEvent e) {
      System.out.println(e.toString());
      button.addMouseMoveListener(this);

    }

    //当鼠标抬起时，停止拖放，移除鼠标移动监听器
    public void mouseUp(MouseEvent e) {
      button.removeMouseMoveListener(this);
    }

    //当鼠标进入到按钮区域时
    public void mouseEnter(MouseEvent e) {
      System.out.println(e.toString());
      System.out.println("鼠标进入到按钮区域");
    }

    //当鼠标离开到按钮区域时
    public void mouseExit(MouseEvent e) {
      System.out.println("鼠标离开按钮区域");
    }

    //当鼠标在按钮区域时
    public void mouseHover(MouseEvent e) {
      System.out.println("鼠标在按钮区域");
    }
  }

  public static void showModifyEvent(Shell parent) {
    Shell shell = new Shell(parent);
    shell.setText("文本改变事件示例");
    shell.setLayout(new GridLayout(2, false));
    new Label(shell, SWT.NONE).setText("请输入小写字母：");
    Text top = new Text(shell, SWT.SINGLE);
    top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    new Label(shell, SWT.NONE).setText("相应的大写字母：");
    Text bottom = new Text(shell, SWT.SINGLE);
    bottom.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    bottom.setEditable(false);

    top.addVerifyListener(e -> {
      char c = e.character;// 获得输入的字符
      // 如果该字符不为字母或者不是小写字母，取消键入
      if (!Character.isLetter(c) || Character.isUpperCase(c)) {
        e.doit = false;
        return;
      }
      // 在下边的文本框中输入相对应的大写字母
      bottom.append("" + Character.toUpperCase(c));
    });

    shell.setSize(300, 100);
    shell.open();
  }

}
