package investigate.jface.actions;

import investigate.jface.MainWindow;
import investigate.jface.util.FileManager;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.operation.ModalContext;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import java.lang.reflect.InvocationTargetException;


public class OpenAction extends Action {

	public OpenAction() {
		super();
		setText("打开(&O)");
		setToolTipText("打开文件");
		setImageDescriptor(ImageDescriptor.createFromFile(NewAction.class,"icons/open.gif"));
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		//打开一个文件对话框，选择一个文件
		FileDialog dialog = new FileDialog(MainWindow.getApp().getShell(), SWT.OPEN);
		dialog.setFilterExtensions(new String[] { "*.java", "*.*" });
		final String name = dialog.open();
		if ((name == null) || (name.length() == 0))
			return;
		//创建一个FileManager对象，该对象封装了从文件中读取字符串的方法
		final FileManager fileManager = MainWindow.getApp().getManager();
		try {
			//创建一个线程打开文件
			ModalContext.run(new IRunnableWithProgress() {
				//线程运行的主体
				public void run(IProgressMonitor progressMonitor) {
					progressMonitor.beginTask("打开文件", IProgressMonitor.UNKNOWN);
					fileManager.load(name);
					//为了模拟一个较大的文件，让该线程休息1秒。
					//取消该注释运行可以看到进度情况
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					progressMonitor.done();
				}
			}, true, MainWindow.getApp().getStatusLineManager().getProgressMonitor(), MainWindow.getApp().getShell().getDisplay());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//装载后将字符显示到文本框中
		MainWindow.getApp().getContent().setText(fileManager.getContent());
		//设置状态栏显示的为打开的文件目录
		MainWindow.getApp().getStatusLineManager().setMessage("当前打开的文件是："+name);
	}

}
