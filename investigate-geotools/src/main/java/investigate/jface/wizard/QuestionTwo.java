package investigate.jface.wizard;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class QuestionTwo extends WizardPage{
	public QuestionTwo() {
		super(BookSurveyWizard.Q2,"问题2:",ImageDescriptor.createFromFile(QuestionOne.class,"q.gif"));
		this.setMessage("您会考虑在今后的项目中使用SWT开发桌面程序吗？");
	}
	public void createControl(Composite parent) {
	    Composite composite = new Composite(parent, SWT.NONE);
	    composite.setLayout(new GridLayout(2, false));
	    new Label(composite, SWT.LEFT).setText("A.");
	    Button b1 = new Button( composite, SWT.RADIO);
	    b1.setText("会");
	    b1.setSelection(true);
	    new Label(composite, SWT.LEFT).setText("B.");
	    Button b2 = new Button( composite, SWT.RADIO);
	    b2.setText("可能会");
	    new Label(composite, SWT.LEFT).setText("C.");
	    Button b3 = new Button( composite, SWT.RADIO);
	    b3.setText("不会");
	    setControl(composite);
	}
}
