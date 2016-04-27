package investigate.jface.dialog;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class ErrorDialogTest extends ApplicationWindow{
	final String dummyPlugin = "plugin id";
	public ErrorDialogTest() {
		super(null);
	}
	protected Control createContents(Composite parent) {
		Composite composite = new Composite ( parent , SWT.NONE);
		composite.setLayout( new RowLayout(SWT.VERTICAL));
		Button bt1 = new Button(composite ,SWT.NONE);
		bt1.setText("打开一个错误对话框");
		bt1.addSelectionListener( new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				//创建一个Status对象
				Status status = new Status(IStatus.ERROR, dummyPlugin, 1, "未找到该类", new ClassNotFoundException());
				//创建错误提示对话框
				ErrorDialog dlg = new ErrorDialog(Display.getCurrent().getActiveShell(),
						"提示错误", //对话框的标题
						"装载类时出现错误！",//对话框的描述信息 
						status, //Status对象
						IStatus.ERROR);//只显示级别为IStatus.ERROR的错误
				dlg.open();
			}		
		});
		Button bt2 = new Button(composite ,SWT.NONE);
		bt2.setText("打开一个可显示多错误对话框");
		bt2.addSelectionListener( new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				IStatus[] statuses = new IStatus[4];
				statuses[0] = new Status(IStatus.INFO, dummyPlugin, IStatus.OK, "未找到类错误", new ClassNotFoundException());
				statuses[1] = new Status(IStatus.ERROR, dummyPlugin, IStatus.OK, "未找到文件错误", new FileNotFoundException());
				statuses[2] = new Status(IStatus.WARNING, dummyPlugin, IStatus.OK, "运行错误", new RuntimeException());
				statuses[3] = new Status(IStatus.WARNING, dummyPlugin, IStatus.OK, "数据库查询错误", new SQLException());
				MultiStatus multiStatus = new MultiStatus(dummyPlugin, IStatus.OK, statuses, "运行期间错误 ", new Exception());
				ErrorDialog dlg = new ErrorDialog(Display.getCurrent().getActiveShell(),
						"提示错误", //对话框的标题
						"运行JFace期间发生的错误",//对话框的描述信息 
						multiStatus, //Status对象
						IStatus.WARNING|IStatus.ERROR);//显示级别为IStatus.WARNING或IStatus.ERROR的错误
				dlg.open();
			}		
		});
		return parent;
	}

	public static void main(String[] args) {
		ErrorDialogTest test = new ErrorDialogTest();
		test.setBlockOnOpen( true );
		test.open();
		Display.getCurrent().dispose();
	}
}
