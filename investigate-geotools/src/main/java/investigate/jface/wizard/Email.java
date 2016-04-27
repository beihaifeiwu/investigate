package investigate.jface.wizard;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class Email extends WizardPage {

	private Text email;

	protected Email() {
		super(BookSurveyWizard.EMAIL, "请留下您的电子邮件:", ImageDescriptor.createFromFile(QuestionOne.class, "q.gif"));
		this.setMessage("请输入您的E-mail：");
		//初始状态时设置页面未完成，“下一步”按钮置为不可用
		this.setPageComplete(false);
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		new Label(composite, SWT.LEFT).setText("Email:");
		email = new Text(composite, SWT.BORDER);
		email.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//为电子邮件文件框注册事件
		email.addVerifyListener(new VerifyListener() {
			//当文本框字符改变时
			public void verifyText(VerifyEvent e) {
				//如果此时符合email的格式
				if (email.getText().indexOf('@') > -1) {
					//设置错误消息为null
					setErrorMessage(null);
					//设置页面完成,“下一步”按钮置可用
					setPageComplete(true);
				} else
					//如果不符合email格式，则提示错误消息
					setErrorMessage("请输入有效的Email!");
			}
		});
		setControl(composite);
	}
}
