package investigate.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class MenuSample {

	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("Menu Sample");
		// 创建主菜单，用于放置到菜单条上
		Menu main = new Menu(shell, SWT.BAR);

		// 主菜单的第一个菜单项
		MenuItem file = new MenuItem(main, SWT.CASCADE);
		file.setText("文件(&F)");

		// 文件菜单，为下拉式
		//Menu filemenu = new Menu(shell, SWT.DROP_DOWN);
		Menu filemenu = new Menu(file);
		// 新建菜单项
		MenuItem newItem = new MenuItem(filemenu, SWT.PUSH);
		newItem.setText("新建(&N)  ALT+SHIFT+N");
		// 设置快捷键
		newItem.setAccelerator(SWT.ALT + SWT.SHIFT + 'N');
		// 打开菜单项
		MenuItem openItem = new MenuItem(filemenu, SWT.PUSH);
		openItem.setText("打开(&O)");
		// 分割线菜单项
		new MenuItem(filemenu, SWT.SEPARATOR);
		// 退出菜单项
		MenuItem exitItem = new MenuItem(filemenu, SWT.PUSH);
		exitItem.setText("退出(&E)");
		exitItem.setAccelerator(SWT.ALT + SWT.SHIFT + 'E');
		// 为退出菜单项注册事件
		exitItem.addListener(SWT.Selection, event -> {
      // 当单击退出菜单时，释放窗口
      shell.dispose();
    });
		// 将文件菜单放置到主菜单的第一个菜单项上
		file.setMenu(filemenu);

		// 主菜单的第二个菜单项
		MenuItem view = new MenuItem(main, SWT.CASCADE);
		view.setText("视图(&V)");

		Menu viewMenu = new Menu(shell, SWT.DROP_DOWN);
		MenuItem normalItem = new MenuItem(viewMenu, SWT.RADIO);
		normalItem.setText("普通(&N)");
		
		//
		MenuItem test = new MenuItem(viewMenu, SWT.CASCADE);
		Menu t1 = new Menu( test );
		MenuItem i1 = new MenuItem(t1, SWT.PUSH);
		i1.setText("heh");
		test.setMenu( t1 );
		normalItem.setText("普通(&N)");
		//
		// 将文件菜单放置到主菜单的第一个菜单项上
		view.setMenu(viewMenu);
		// 将主菜单放置到窗口上
		shell.setMenuBar(main);
		//shell.setMenu( main );
		
		shell.setSize(200, 150);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
