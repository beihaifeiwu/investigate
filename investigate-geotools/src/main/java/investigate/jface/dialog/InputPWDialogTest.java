package investigate.jface.dialog;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

public class InputPWDialogTest extends ApplicationWindow{

	public InputPWDialogTest() {
		super(null);
	}
	protected Control createContents(Composite parent) {
		Button button = new Button( parent ,SWT.NONE);
		button.setText("打开输入对话框");
		button.addSelectionListener( new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				//调用该对话框
				InputPasswordDialog dialog = new InputPasswordDialog(Display.getCurrent().getActiveShell());
				dialog.open();
			}
		});
		return parent;
	}

	public static void main(String[] args) {
		InputPWDialogTest test = new InputPWDialogTest();
		test.setBlockOnOpen( true );
		test.open();
		Display.getCurrent().dispose();
	}

}
