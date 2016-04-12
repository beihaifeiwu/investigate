package investigate.swt;

import investigate.swt.util.ImageFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;


public class TraySample {

	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		// 创建系统托盘
		final Tray tray = display.getSystemTray();
		// 如果系统不支持托盘部件
		if (tray == null) {
			System.out.println("该系统不支持系统托盘");
			return;
		}
		final Menu trayMenu = createTrayMenu(display, shell);
		// 创建系统托盘的工作项
		final TrayItem item = new TrayItem(tray, SWT.NONE);
		item.setToolTipText("这是一个TrayItem");
		//设置显示系统托盘项的图标，显示在桌面的右下角
		item.setImage(ImageFactory.loadImage(display, ImageFactory.SAMPLES));
		//集中处理事件
		Listener listner = new Listener(){

			public void handleEvent(Event event) {
			    
				if ( event.type==SWT.Show )//当显示系统托盘时
					System.out.println("显示");
				else if ( event.type==SWT.Hide )//当隐藏系统托盘时
					System.out.println("隐藏");
				else if  ( event.type==SWT.Selection )//当单击系统托盘时
					System.out.println("选中");
				else if ( event.type==SWT.DefaultSelection )//当双击系统托盘时
					System.out.println("默认选中");
				else if ( event.type==SWT.MenuDetect )//当右击系统托盘时
					trayMenu.setVisible(true);//设置菜单为显示状态
			}
			
		};
		//为该系统托盘项注册事件
		item.addListener( SWT.Show, listner );
		item.addListener( SWT.Hide , listner );
		item.addListener( SWT.Selection , listner );
		item.addListener( SWT.DefaultSelection , listner );
		item.addListener( SWT.MenuDetect , listner );
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		ImageFactory.dispose();
		display.dispose();

	}

	private static Menu createTrayMenu(Display display, final Shell shell) {
		// 定义一个弹出式菜单，使用常量SWT.POP_UP
		final Menu trayMenu = new Menu(shell, SWT.POP_UP);
		// 定义菜单中的菜单项
		MenuItem welcomeItem = new MenuItem(trayMenu, SWT.PUSH);
		welcomeItem.setText("欢迎");
		welcomeItem.setImage(ImageFactory.loadImage(display,
				ImageFactory.ECLIPSE));

		new MenuItem(trayMenu, SWT.SEPARATOR);

		MenuItem updatetem = new MenuItem(trayMenu, SWT.PUSH);
		updatetem.setText("在线更新");

		MenuItem aboutItem = new MenuItem(trayMenu, SWT.PUSH);
		aboutItem.setText("关于我们");
		aboutItem.setImage(ImageFactory.loadImage(display, ImageFactory.SAMPLES));

		trayMenu.setDefaultItem(welcomeItem);
		return trayMenu;
	}

}
