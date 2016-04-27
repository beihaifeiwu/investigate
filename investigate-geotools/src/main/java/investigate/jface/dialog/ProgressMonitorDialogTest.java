package investigate.jface.dialog;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import java.lang.reflect.InvocationTargetException;

public class ProgressMonitorDialogTest extends ApplicationWindow {

	public ProgressMonitorDialogTest() {
		super(null);
	}

	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.VERTICAL));
		Button bt1 = new Button(composite, SWT.NONE);
		bt1.setText("打开进度条对话框");
		bt1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				showDialog();
			}
		});
		return parent;
	}

	private void showDialog() {
		try {
			ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(Display.getCurrent().getActiveShell());
			// 创建后台线程对象
			IRunnableWithProgress runnalble = new IRunnableWithProgress() {
				// 线程运行的代码部分
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					// 设置显示在UI界面上的线程信息
					monitor.beginTask("开始执行任务...", 100);
					// 该线程在用户没有取消操作的情况下循环10次
					// 并且每次循环后设置进度增加10,表示一个任务已完成
					for (int i = 0; i < 10 && !monitor.isCanceled(); i++) {
						Thread.sleep(500);// 为了模拟耗时的操作，每次循环后让该线程休息半秒钟
						monitor.worked(10);// 进度增加10
						monitor.subTask("已完成第" + i + "个任务");// 显示任务状态
					}
					// 循环完成后设置此任务已完成
					monitor.done();
					// 如果此时为用户取消的操作
					if (monitor.isCanceled())
						throw new InterruptedException("用户已取消了操作");
				}
			};
			progressDialog.run(true, true, runnalble);// 启动线程
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ProgressMonitorDialogTest test = new ProgressMonitorDialogTest();
		test.setBlockOnOpen(true);
		test.open();
		Display.getCurrent().dispose();
	}
}
