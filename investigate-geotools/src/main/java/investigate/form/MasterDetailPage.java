package investigate.form;

import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ScrolledForm;

public class MasterDetailPage extends FormPage {

	public static final String ID = "com.fengmanfei.myrcp.forms.MasterDetailPage";

	// 声明MasterDetail页面部分对象
	private FileMasterDetailsBlock block;

	public MasterDetailPage(FormEditor editor) {
		super(editor, ID, "Master/Detail页");
		block = new FileMasterDetailsBlock(this);
	}

	/**
	 * ManagedForm封装了form元素的生命周期管理与各个form元素之间的事件通知
	 * ManagedForm本身并不是一个form，他包含了一个form并且可以注册IFormPart。
	 * 可以将ManagedForm看作是'viewers'，form和managed form之间的关系就好像
	 * Table与TableViewer的关系一样。
	 */
	protected void createFormContent(IManagedForm managedForm) {
		// 获得表单对象
		ScrolledForm form = managedForm.getForm();
		// 设置表单的标题
		form.setText("这是一个浏览文件的Master/Detail页面");
		// 该方法非常重要，负责创建Master和Detail区域，尽量在最后调用
		block.createContent(managedForm);
	}

}
