package investigate.jface.dialog;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class LoginDialogTest extends ApplicationWindow{

	public LoginDialogTest() {
		super(null);
	}
	protected void configureShell(Shell shell) {
		super.configureShell( shell );
		shell.setText("自定义对话框示例");
	}
	protected Control createContents(Composite parent) {
		Composite composite = new Composite( parent , SWT.NONE);
		composite.setLayout( new GridLayout());
		Button button = new Button( composite ,SWT.NONE);
		button.setText("打开自定义对话框示例");
		button.addSelectionListener( new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				LoginDialog dialog = new LoginDialog( Display.getCurrent().getActiveShell() );
				dialog.open();
			}
		});
		return parent;
	}
	public static void main(String[] args) {
		LoginDialogTest test = new LoginDialogTest();
		test.setBlockOnOpen( true );
		test.open();
		Display.getCurrent().dispose();

	}

}
