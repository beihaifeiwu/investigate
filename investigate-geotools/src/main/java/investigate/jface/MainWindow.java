package investigate.jface;

import investigate.jface.actions.*;
import investigate.jface.util.FileManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.*;

public class MainWindow extends ApplicationWindow {

	private NewAction newAction;// 新建菜单项

	private OpenAction openAction;// 打开菜单项

	private SaveAction saveAction;// 保存菜单项

	private SaveAsAction saveAsAction;// 另存为菜单项

	private ExitAction exitAction;// 退出菜单项

	private CopyAction copyAction;// 复制菜单项

	private CutAction cutAction;// 剪切菜单项

	private PasteAction pasteAction;// 粘贴菜单项

	private HelpAction helpAction;// 帮助菜单项

	private FileManager manager;// 文件管理器

	private Text content;// 文本框

	private static MainWindow app;// 主程序窗口

	private MainWindow() {
		super(null);
		app = this;
		manager = new FileManager();
		// 初始化按钮动作
		newAction = new NewAction();
		openAction = new OpenAction();
		saveAction = new SaveAction();
		saveAsAction = new SaveAsAction();
		exitAction = new ExitAction();
		copyAction = new CopyAction();
		cutAction = new CutAction();
		pasteAction = new PasteAction();
		helpAction = new HelpAction();

		this.addMenuBar();// 添加菜单栏
		this.addToolBar(SWT.FLAT);
		this.addStatusLine();
	}

	/**
	 * @return 返回 app。
	 */
	public static MainWindow getApp() {
		return app;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.jface.window.ApplicationWindow#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setMaximized(true);//设置最大化
		shell.setText("简单写字板");
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		content = new Text(parent, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		content.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				manager.setDirty(true);
			}
		});
		return parent;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.jface.window.ApplicationWindow#createMenuManager()
	 */
	protected MenuManager createMenuManager() {
		MenuManager menuBar = new MenuManager();// 创建得菜单栏对象

		MenuManager fileMenu = new MenuManager("文件(&F)"); // 文件菜单项
		MenuManager editMenu = new MenuManager("编辑(&E)"); // 编辑菜单项
		MenuManager formatMenu = new MenuManager("格式(&M)"); // 格式菜单项
		MenuManager helpMenu = new MenuManager("帮助(&H)"); // 帮助菜单项

		// 将菜单项添加到主菜单中
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(formatMenu);
		menuBar.add(helpMenu);

		// 文件菜单项
		fileMenu.add(newAction);
		fileMenu.add(openAction);
		fileMenu.add(new Separator());
		fileMenu.add(saveAction);
		fileMenu.add(saveAsAction);
		fileMenu.add(new Separator());
		fileMenu.add(exitAction);

		editMenu.add(copyAction);
		editMenu.add(cutAction);
		editMenu.add(pasteAction);

		formatMenu.add(new FormatAction(FormatAction.TYPE_FONT));
		formatMenu.add(new FormatAction(FormatAction.TYPE_BGCOLOR));
		formatMenu.add(new FormatAction(FormatAction.TYPE_FORECOLOR));

		helpMenu.add(helpAction);

		return menuBar;

	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.jface.window.ApplicationWindow#createToolBarManager(int)
	 */
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBar = new ToolBarManager(style);
		toolBar.add(newAction);
		toolBar.add(openAction);
		toolBar.add(saveAction);
		toolBar.add(saveAsAction);
		toolBar.add(new Separator());
		toolBar.add(copyAction);
		toolBar.add(cutAction);
		toolBar.add(pasteAction);
		toolBar.add(new Separator());
		toolBar.add(new FormatAction(FormatAction.TYPE_FONT));
		toolBar.add(new FormatAction(FormatAction.TYPE_BGCOLOR));
		toolBar.add(new FormatAction(FormatAction.TYPE_FORECOLOR));
		return toolBar;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.jface.window.ApplicationWindow#getStatusLineManager()
	 */
	public StatusLineManager getStatusLineManager() {
		return super.getStatusLineManager();
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.jface.window.ApplicationWindow#createStatusLineManager()
	 */
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	public static void main(String[] args) {
		MainWindow main = new MainWindow();
		main.setBlockOnOpen(true);
		main.open();
		Display.getCurrent().dispose();
	}

	/**
	 * @return 返回 content。
	 */
	public Text getContent() {
		return content;
	}

	/**
	 * @return 返回 manager。
	 */
	public FileManager getManager() {
		return manager;
	}

	/**
	 * @param manager
	 *            要设置的 manager。
	 */
	public void setManager(FileManager manager) {
		this.manager = manager;
	}
}
