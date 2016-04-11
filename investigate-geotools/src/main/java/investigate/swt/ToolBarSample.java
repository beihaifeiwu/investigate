package investigate.swt;

import investigate.swt.util.ImageFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class ToolBarSample {

	public static void main(String[] args) {
		Display display = new Display ();
		Shell shell = new Shell (display);
    showToolBar(display, shell);
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		//释放图片资源
		ImageFactory.dispose();
		display.dispose ();

	}

  private static void showToolBar(Display display, Shell shell) {
    shell.setText("ToolBar");
    shell.setLayout( new GridLayout());
    Composite tool = new Composite( shell , SWT.NONE);
    tool.setLayoutData(new GridData(SWT.LEFT,SWT.TOP,true ,false));
    //创建工具栏对象，使用SWT.NONE样式
    ToolBar toolBar = new ToolBar (tool, SWT.FLAT);

    //创建保存工具按钮
    ToolItem saveItem = new ToolItem ( toolBar , SWT.PUSH);
    //设置工具按钮的图片
    saveItem.setImage( ImageFactory.loadImage( display , ImageFactory.SAVE_EDIT));
    //设置工具按钮上的文字
    saveItem.setText("保存");
    //设置工具按钮上的提示信息
    saveItem.setToolTipText("保存");
    //创建打印工具按钮
    //new ToolItem ( toolBar , SWT.SEPARATOR);
    ToolItem printItem = new ToolItem ( toolBar , SWT.PUSH);
    printItem.setImage( ImageFactory.loadImage( display , ImageFactory.PRINT_EDIT));
    printItem.setText("打印");
    printItem.setToolTipText("打印");
    //new ToolItem ( toolBar , SWT.SEPARATOR);
    //创建帮助工具按钮
    ToolItem helpItem = new ToolItem ( toolBar , SWT.PUSH);
    helpItem.setImage( ImageFactory.loadImage( display , ImageFactory.HELP_CONTENTS));
    helpItem.setText("帮助");
    helpItem.setToolTipText("帮助");

    toolBar.pack ();
    Text content = new Text (shell,SWT.MULTI);
    content.setLayoutData( new GridData(SWT.FILL,SWT.FILL,true,true));

    shell.setSize( new Point( 200,150 ));
    shell.open ();
  }

}
