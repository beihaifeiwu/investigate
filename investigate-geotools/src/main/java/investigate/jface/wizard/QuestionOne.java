package investigate.jface.wizard;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.jface.dialogs.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
public class QuestionOne extends WizardPage{

	public QuestionOne() {
		super(BookSurveyWizard.Q1,"问题1:",ImageDescriptor.createFromFile(QuestionOne.class,"q.gif"));
		//设置标题消息
		this.setMessage("您认为本书的难度是：");
	}

	//该方法为必须实现的方法，在该方法中创建向导页面的控件
	public void createControl(Composite parent) {
	    Composite composite = new Composite(parent, SWT.NONE);
	    composite.setLayout(new GridLayout(2,false));
	    new Label(composite, SWT.LEFT).setText("A.");
	    Button b1 = new Button( composite, SWT.RADIO);
	    b1.setText("太简单");
	    b1.setSelection(true);
	    new Label(composite, SWT.LEFT).setText("B.");
	    Button b2 = new Button( composite, SWT.RADIO);
	    b2.setText("还可以");
	    new Label(composite, SWT.LEFT).setText("C.");
	    Button b3 = new Button( composite, SWT.RADIO);
	    b3.setText("太难");
	    //该方法非常重要，否则不能显示设置的控件
	    setControl(composite);
	}
	/* （非 Javadoc）
	 * @see org.eclipse.jface.dialogs.DialogPage#performHelp()
	 */
	public void performHelp() {
		MessageDialog.openInformation(Display.getCurrent().getActiveShell(),"帮助","请联系ABC@yahoo.com.cn!");
	}

}
