package investigate.jface.jseditor.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class AboutDialog extends Dialog {

	public AboutDialog(Shell parentShell) {
		super(parentShell);
	}
	protected Control createContents(Composite parent) {
		this.getShell().setSize(200,150);
		this.getShell().setText("关于我们");
		parent.setLayout( new GridLayout());
		new Label(parent, SWT.CENTER).setText("JS编辑器 Verson 1.0");
		new Label(parent, SWT.CENTER).setText("作者：Janet");
		new Label(parent, SWT.RIGHT).setText("2006.8");
		return parent;
	}
}
