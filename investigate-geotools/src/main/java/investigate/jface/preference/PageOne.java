package investigate.jface.preference;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class PageOne extends PreferencePage {
	protected Control createContents(Composite parent) {
		return parent;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#contributeButtons(org.eclipse.swt.widgets.Composite)
	 */
	protected void contributeButtons(Composite parent) {
		// super.contributeButtons(parent);
		Button bt1 = new Button(parent, SWT.NONE);
		bt1.setText("按钮一");
		((GridLayout) parent.getLayout()).numColumns++;
		Button bt2 = new Button(parent, SWT.NONE);
		bt2.setText("按钮二");
		((GridLayout) parent.getLayout()).numColumns++;

	}

}
