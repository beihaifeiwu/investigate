package investigate.jface.wizard;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class Thanks extends WizardPage {

	protected Thanks() {
		super(BookSurveyWizard.THANKS, "感谢:", ImageDescriptor.createFromFile(QuestionOne.class, "q.gif"));
		this.setMessage("非常感谢您参加本次调查！");
	}
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());
		new Label(composite, SWT.CENTER).setText("感谢您的支持");
		setControl(composite);
	}

}
