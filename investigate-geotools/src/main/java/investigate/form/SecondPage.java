package investigate.form;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

public class SecondPage extends FormPage {

	public static final String ID = "com.fengmanfei.myrcp.forms.SecondPage";

	public SecondPage(FormEditor editor) {
		super(editor, ID, "第二页");
	}

	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = managedForm.getForm();
		form.setText("ColumnLayout");
		//创建列布局
		ColumnLayout layout = new ColumnLayout();
		layout.topMargin = 0;//上补白
		layout.bottomMargin = 5;//下补白
		layout.leftMargin = 10;//左补白
		layout.rightMargin = 10;//右补白
		layout.horizontalSpacing = 10;//水平方向控件的距离
		layout.verticalSpacing = 10;//垂直方向控件的距离
		layout.maxNumColumns = 4;//最大的列数
		layout.minNumColumns = 1;//最小的列数
		form.getBody().setLayout(layout);//设置表单的布局为列布局
		//创建四个内容区
		for (int i = 0; i < 4; i++)
			createSectionWithLinks(managedForm, "Section" + i, "This is Section " + i, i);
	}
	//创建内容区及其控件
	private void createSectionWithLinks(IManagedForm mform, String title, String desc, int nlinks) {
		//创建内容去面板
		Composite client = createSection(mform, title, desc, 1);
		FormToolkit toolkit = mform.getToolkit();
		//创建内容区中的控件
		for (int i = 1; i <= nlinks; i++)
			toolkit.createHyperlink(client, "link" + i, SWT.WRAP); 
	}
	//创建内容区的方法
	private Composite createSection(IManagedForm mform, String title, String desc, int numColumns) {
		final ScrolledForm form = mform.getForm();
		FormToolkit toolkit = mform.getToolkit();
		//创建内容区
		Section section = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR | Section.DESCRIPTION | Section.EXPANDED);
		section.setText(title);
		section.setDescription(desc);
		Composite client = toolkit.createComposite(section);
		GridLayout layout = new GridLayout();
		layout.marginWidth = layout.marginHeight = 0;
		layout.numColumns = numColumns;
		client.setLayout(layout);
		//设置内容区的面板
		section.setClient(client);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(false);
			}
		});
		return client;
	}
}
