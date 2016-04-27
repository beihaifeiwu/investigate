package investigate.jface.preference;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class SystemSettingPage extends PreferencePage {

	private Text userName;
	private Text password;
	//该方法为必须实现的方法，在此方法中创建页面上的各种控件
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		//获取保存此页面的PreferenceStore对象
		IPreferenceStore preferenceStore = getPreferenceStore();

		new Label(composite, SWT.LEFT).setText("登录用户名:");
		userName = new Text(composite, SWT.BORDER);
		userName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//设置用户名为保存在文件中的值
		userName.setText(preferenceStore.getString(Constants.USER_NAME));

		new Label(composite, SWT.LEFT).setText("登录密码:");
		password = new Text(composite, SWT.BORDER);
		password.setEchoChar('*');
		password.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//设置密码为保存在文件中的值
		password.setText(preferenceStore.getString(Constants.PASSWORD));
		return composite;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 * 覆盖父类中的方法，但单击“恢复默认值”按钮时调用该方法
	 */
	protected void performDefaults() {
		IPreferenceStore preferenceStore = getPreferenceStore();
		userName.setText( preferenceStore.getDefaultString(Constants.USER_NAME));
		password.setText( preferenceStore.getDefaultString(Constants.PASSWORD));
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 * 覆盖父类中的方法，但单击“应用”按钮时调用该方法
	 */
	public boolean performOk() {
		IPreferenceStore preferenceStore = getPreferenceStore();
		if (userName != null)
			preferenceStore.setValue(Constants.USER_NAME, userName.getText());
		if (password != null)
			preferenceStore.setValue(Constants.PASSWORD, password.getText());
		return true;
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
