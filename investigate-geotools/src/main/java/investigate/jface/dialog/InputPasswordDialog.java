package investigate.jface.dialog;

import investigate.swt.util.ImageFactory;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class InputPasswordDialog extends TitleAreaDialog{

	private Text userName;
	private Text password;
	private Text confirmPassword;
	public final String DEFAULT_INFO="请输入要注册的用户名的和密码";
	public InputPasswordDialog(Shell parentShell) {
		super(parentShell);
	}
	/* （非 Javadoc）
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		super.createContents(parent);
		this.getShell().setText("用户注册对话框");//设置对话框标题栏
		this.setTitle("用户注册");//设置标题信息
		this.setMessage("请输入要注册的用户名和密码",IMessageProvider.INFORMATION);//设置初始化对话框的提示信息
		//设置右侧的图片，一般为48*48大小
		this.setTitleImage( ImageFactory.loadImage( Display.getCurrent(), ImageFactory.SAMPLE_ICON));
		return parent;
	}

	/* （非 Javadoc）
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 * 覆盖该方法，可以创建对话框显示的主体区域
	 */
	protected Control createDialogArea(Composite parent) {
		super.createDialogArea(parent);
		Composite composite = new Composite(parent , SWT.NONE);
		composite.setLayout( new GridLayout(2,true));
		new Label( composite , SWT.NONE).setText("用户名：");
		userName = new Text(composite ,SWT.BORDER);
		userName.addFocusListener( new FocusAdapter(){
			//当用户名文本框失去焦点时，判断是否有效
			public void focusLost(FocusEvent e) {
				checkValid();				
			}	
		});
		new Label( composite , SWT.NONE).setText("密码：");
		password = new Text(composite ,SWT.BORDER);
		password.setEchoChar('*');
		new Label( composite , SWT.NONE).setText("确认密码：");
		confirmPassword = new Text(composite ,SWT.BORDER);
		confirmPassword.setEchoChar('*');
		confirmPassword.addFocusListener( new FocusAdapter(){
			//当确认密码文本框失去焦点时，判断是否有效
			public void focusLost(FocusEvent e) {
				checkValid();				
			}	
		});
		return parent;
	}
	//判断是是否输入有效，并提示用户
	protected void checkValid() {
		if (!password.getText().equals(confirmPassword.getText()))
			setMessage("确认密码不一致，请重新输入!",IMessageProvider.WARNING);
		else if ( userName.getText().equals(""))
			setMessage("用户名不能为空!",IMessageProvider.ERROR);
		else
			setMessage(DEFAULT_INFO);
	}
}
