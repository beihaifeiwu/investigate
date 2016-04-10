package investigate.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * Created by liupin on 2016/4/9.
 */
public class LayoutSample {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display, SWT.SHELL_TRIM);

    showFillLayout(shell);
    showRowLayout(shell);
    showGridLayout(shell);
    showFormLayout(shell);
    showStackLayout(shell);
    showBorderLayout(shell);

    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }

  private static void showFillLayout(Shell shell) {
    FillLayout layout = new FillLayout();
    layout.type = SWT.HORIZONTAL;
    layout.marginHeight = 10;  //设置上下补白高度
    layout.marginWidth = 20;   //设置左右
    layout.spacing = 5;        //设置空间之间的空隙
    shell.setLayout(layout);

    new Button(shell, SWT.NONE).setText("B1");
    new Button(shell, SWT.NONE).setText("Button2");
    new Button(shell, SWT.NONE).setText("B3");

    shell.layout();
    shell.pack();
    shell.open();
  }

  private static void showRowLayout(Shell parent) {
    Shell shell = new Shell(parent, SWT.SHELL_TRIM);

    RowLayout layout = new RowLayout();
    layout.type = SWT.HORIZONTAL;// 设置水平填充
    layout.marginLeft = 5;// 左补白
    layout.marginTop = 5;// 上补白
    layout.marginRight = 5;// 右补白
    layout.marginBottom = 5;// 下补白
    layout.spacing = 2;// 控件的间隙
    shell.setLayout(layout);

    new Button(shell, SWT.NONE).setText("B1");
    new Button(shell, SWT.NONE).setText("Button2");
    new Button(shell, SWT.NONE).setText("Wide Button3");
    new Button(shell, SWT.NONE).setText("B4");

    shell.layout();
    shell.pack();
    shell.open();
  }

  private static void showGridLayout(Shell parent) {
    Shell shell = new Shell(parent, SWT.SHELL_TRIM);

    GridLayout gridLayout = new GridLayout();
    gridLayout.numColumns = 3;  //设置网格的列数
    gridLayout.makeColumnsEqualWidth = true; //设置网格等宽
    gridLayout.verticalSpacing = 10; //设置垂直间隔
    gridLayout.horizontalSpacing = 10; //设置水平间隔

    shell.setLayout(gridLayout);
    Button button = new Button(shell, SWT.PUSH);
    button.setText("B1");
    // 设置水平填充方式为充满单元格，设置水平抢占空间的方式
    button.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

    new Button(shell, SWT.PUSH).setText("Wide Button 2");
    Button bt3 = new Button(shell, SWT.PUSH);
    bt3.setText("Button 3");
    GridData gd3 = new GridData();
    gd3.verticalSpan = 2;
    gd3.verticalAlignment = SWT.FILL;
    gd3.horizontalAlignment = SWT.FILL;
    // 设置水平抢占
    gd3.grabExcessHorizontalSpace = true;
    // 设置垂直抢占
    gd3.grabExcessVerticalSpace = true;
    bt3.setLayoutData(gd3);

    new Button(shell, SWT.PUSH).setText("B4");

    Button bt5 = new Button(shell, SWT.PUSH);
    bt5.setText("Button 5");
    GridData gridData = new GridData();
    // 设置水平对齐方式
    gridData.horizontalAlignment = SWT.FILL;
    // 设置水平跨越两个单元格
    gridData.horizontalSpan = 2;
    // 设置缩进为20像素大小
    gridData.horizontalIndent = 20;
    bt5.setLayoutData(gridData);

    new Button(shell, SWT.PUSH).setText("B6");

    shell.layout();
    shell.pack();
    shell.open();
  }

  private static void showFormLayout(Shell parent) {
    Shell shell = new Shell(parent, SWT.SHELL_TRIM);

    // 创建FormLayout对象
    FormLayout formLayout = new FormLayout();
    formLayout.marginHeight = 5;// 设置上下补白为5个像素
    formLayout.marginWidth = 5;// 设置左右补白为5个像素
    shell.setLayout(formLayout);

    Button bt1 = new Button(shell, SWT.PUSH);
    bt1.setText("B1");
    // 创建FormData对象
    FormData formData = new FormData();
    // 设定控件的上边框的位置
    formData.top = new FormAttachment(30, 70, 60);
    // 设定控件的下边框的位置
    formData.bottom = new FormAttachment(100, -5);
    bt1.setLayoutData(formData);

    shell.layout();
    shell.pack();
    shell.open();
  }

  private static void showStackLayout(Shell top) {
    Shell shell = new Shell(top);
    shell.setLayout(new GridLayout());
    // 创建放置文本框的面板
    final Composite parent = new Composite(shell, SWT.NONE);
    // 设置面板的布局数据
    parent.setLayoutData(new GridData(GridData.FILL_BOTH));
    // 创建堆栈式布局
    final StackLayout layout = new StackLayout();
    // 将堆拽式布局应用与面板
    parent.setLayout(layout);
    // 创建10个文本框
    final Text[] textArray = new Text[10];
    for (int i = 0; i < 10; i++) {
      textArray[i] = new Text(parent, SWT.MULTI);
      textArray[i].setText("这是第" + i + "个文本框");
    }
    // 设置堆栈中当前显示的控件
    layout.topControl = textArray[0];

    Button b = new Button(shell, SWT.PUSH);
    b.setText("显示下一个文本框");
    // 保存当前显示的文本框的索引值
    final int[] index = new int[1];
    // 为按钮添加点击事件
    b.addListener(SWT.Selection, new Listener() {
      public void handleEvent(Event e) {
        // 计算出下一个文本框的索引数
        index[0] = (index[0] + 1) % 10;
        // 设置当前显示的控件
        layout.topControl = textArray[index[0]];
        // 重新刷新布局
        parent.layout();
      }
    });

    shell.setSize(200, 150);
    shell.open();
  }

  public static final class BorderData {

    public int region = SWT.CENTER;// 默认为中间

    public BorderData() {
    }

    public BorderData(int region) {
      this.region = region;
    }

  }

  public static class BorderLayout extends Layout {

    private Control north;

    private Control south;

    private Control east;

    private Control west;

    private Control center;

    protected Point computeSize(Composite composite, int wHint, int hHint,
                                boolean flushCache) {
      getControls(composite);
      // 定义面板的宽和高
      int width = 0, height = 0;
      // 计算面板的宽度
      width += west == null ? 0 : getSize(west, flushCache).x;
      width += east == null ? 0 : getSize(east, flushCache).x;
      width += center == null ? 0 : getSize(center, flushCache).x;
      // 如果上部和下部有控件，则宽取较大的值
      if (north != null) {
        Point pt = getSize(north, flushCache);
        width = Math.max(width, pt.x);
      }
      if (south != null) {
        Point pt = getSize(south, flushCache);
        width = Math.max(width, pt.x);
      }

      // 计算面板的高度
      height += north == null ? 0 : getSize(north, flushCache).y;
      height += south == null ? 0 : getSize(south, flushCache).y;

      int heightOther = center == null ? 0 : getSize(center, flushCache).y;
      if (west != null) {
        Point pt = getSize(west, flushCache);
        heightOther = Math.max(heightOther, pt.y);
      }
      if (east != null) {
        Point pt = getSize(east, flushCache);
        heightOther = Math.max(heightOther, pt.y);
      }
      height += heightOther;

      // 计算的宽和高与默认的宽和高作比较，返回之中较大的
      return new Point(Math.max(width, wHint), Math.max(height, hHint));
    }

    protected void layout(Composite composite, boolean flushCache) {
      getControls(composite);
      // 获得当前面板可显示的区域
      Rectangle rect = composite.getClientArea();
      int left = rect.x, right = rect.width, top = rect.y, bottom = rect.height;
      // 将各个控件放置到面板的
      if (north != null) {
        Point pt = getSize(north, flushCache);
        north.setBounds(left, top, rect.width, pt.y);
        top += pt.y;
      }
      if (south != null) {
        Point pt = getSize(south, flushCache);
        south.setBounds(left, rect.height - pt.y, rect.width, pt.y);
        bottom -= pt.y;
      }
      if (east != null) {
        Point pt = getSize(east, flushCache);
        east.setBounds(rect.width - pt.x, top, pt.x, (bottom - top));
        right -= pt.x;
      }
      if (west != null) {
        Point pt = getSize(west, flushCache);
        west.setBounds(left, top, pt.x, (bottom - top));
        left += pt.x;
      }
      if (center != null) {
        center.setBounds(left, top, (right - left), (bottom - top));
      }
    }

    // 计算某一控件当前的大小，长和宽
    protected Point getSize(Control control, boolean flushCache) {
      return control.computeSize(SWT.DEFAULT, SWT.DEFAULT, flushCache);
    }

    // 设置该类中每个位置控件的属性
    protected void getControls(Composite composite) {
      // 获得当前面板中所有的控件对象
      Control[] children = composite.getChildren();
      // 循环所有控件，并将每个控件所放的位置对号入座
      for (int i = 0, n = children.length; i < n; i++) {
        Control child = children[i];
        BorderData borderData = (BorderData) child.getLayoutData();
        if (borderData.region == SWT.TOP)
          north = child;
        else if (borderData.region == SWT.BOTTOM)
          south = child;
        else if (borderData.region == SWT.RIGHT)
          east = child;
        else if (borderData.region == SWT.LEFT)
          west = child;
        else
          center = child;
      }
    }

  }

  public static void showBorderLayout(Shell parent) {
    Shell shell = new Shell(parent, SWT.SHELL_TRIM);
    shell.setSize(200, 150);
    shell.setLayout(new BorderLayout());

    Button buttonWest = new Button(shell, SWT.PUSH);
    buttonWest.setText("左");
    buttonWest.setLayoutData(new BorderData(SWT.LEFT));

    Button buttonEast = new Button(shell, SWT.PUSH);
    buttonEast.setText("右");
    buttonEast.setLayoutData(new BorderData(SWT.RIGHT));

    Button buttonNorth = new Button(shell, SWT.PUSH);
    buttonNorth.setText("上");
    buttonNorth.setLayoutData(new BorderData(SWT.TOP));

    Button buttonSouth = new Button(shell, SWT.PUSH);
    buttonSouth.setText("下");
    buttonSouth.setLayoutData(new BorderData(SWT.BOTTOM));

    Text text = new Text(shell, SWT.MULTI);
    text.setText("中间");
    text.setLayoutData(new BorderData());

    shell.pack();
    shell.open();
  }
}
