package investigate.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;

public class SashFormSample {

	public static void main(String[] args) {
		final Display display = new Display ();
		Shell shell = new Shell(display);
    showSashForm(shell);
    // 创建图片对象，该图片对象设置选项卡上的图标
    Image image = new Image(display, TabFolderSample.class.getClassLoader().getResourceAsStream("icons/etool16/samples.gif"));
    showEclipseSample(shell, image);

		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
    image.dispose();
	}

  private static void showSashForm(Shell shell) {
    shell.setLayout (new FillLayout());
    shell.setText("SashForm");

    //创建窗框对象，设置样式为水平排列
    SashForm form = new SashForm(shell, SWT.HORIZONTAL|SWT.BORDER);
    form.setLayout(new FillLayout());
    //创建窗口1的面板
    Composite child1 = new Composite(form,SWT.NONE);
    child1.setLayout(new FillLayout());
    new Text(child1,SWT.MULTI).setText("窗口1");
    //创建窗口2的面板
    Composite child2 = new Composite(form,SWT.NONE);
    child2.setLayout(new FillLayout());
    new Text(child2,SWT.MULTI).setText("窗口2");

    form.setWeights(new int[] {30,70});
    //form.setMaximizedControl( child1 );
    //form.setMaximizedControl( null );
    shell.setSize( 200,150);
    shell.open ();
  }

  @SuppressWarnings("Duplicates")
  private static void showEclipseSample(Shell parent, Image image){
    final Shell shell = new Shell(parent);
    shell.setText("仿Eclipse编辑区的选项卡-SashForm");
    shell.setLayout(new FillLayout());

    final SashForm form = new SashForm(shell,SWT.HORIZONTAL|SWT.FLAT);
    form.setLayout(new FillLayout());

    Composite child1 = new Composite(form,SWT.NONE);
    child1.setLayout(new FillLayout());
    new Label(child1,SWT.NONE).setText("窗口1");

    // 创建自定义选项卡对象
    final CTabFolder folder = new CTabFolder(form, SWT.BORDER);
    // 设置选项卡的布局，通过布局的设置呈现出最大化和最小化的外观
    folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
    // 设置复杂的选项卡，也就是带有圆角的选项卡标签
    folder.setSimple(false);
    // 设置未选中标签，图标和关闭按钮的状态
    folder.setUnselectedImageVisible(true);
    folder.setUnselectedCloseVisible(true);
    // 设置前景色和背景色
    folder.setSelectionForeground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
    folder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
    // 显示最大化和最小化按钮
    folder.setMinimizeVisible(true);
    folder.setMaximizeVisible(true);
    // 创建选项卡标签对象
    for (int i = 1; i < 5; i++) {
      CTabItem item = new CTabItem(folder, SWT.CLOSE);
      item.setText("选项卡 " + i);
      item.setImage(image);
      // 每个选项卡中放置一个Text文本框
      Text text = new Text(folder, SWT.MULTI | SWT.V_SCROLL
          | SWT.H_SCROLL);
      // 文本框中的文字带有\n表示，显示时换到下一行
      text.setText("这是第" + i + "页:\n该选项卡仿照Eclipse设计\n最大化和最小化按钮都可以使用");
      item.setControl(text);
    }
    // 注册选项卡事件
    folder.addCTabFolder2Listener(new CTabFolder2Adapter() {
      // 当单击最小化按钮时触发的事件
      public void minimize(CTabFolderEvent event) {
        // 设置选项卡的状态为最小化，选项卡的状态决定显示在右上角的窗口按钮
        folder.setMinimized(true);
        // 改变选项卡的布局，呈现最小化状态
        folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
            false));
        // 刷新布局，否则重新设置的布局将不起作用
        shell.layout(true);
      }

      // 当单击最大化按钮时触发的事件
      public void maximize(CTabFolderEvent event) {
        folder.setMaximized(true);
        //folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true));
        form.setMaximizedControl(folder);
        shell.layout(true);
      }

      // 当单击还原按钮时触发的事件
      public void restore(CTabFolderEvent event) {
        folder.setMinimized(false);
        folder.setMaximized(false);
        //folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,false));
        form.setMaximizedControl(null);
        shell.layout(true);
      }
    });

    form.setWeights(new int[] {30,70});
    shell.setSize(300, 200);
    shell.open();
  }

}