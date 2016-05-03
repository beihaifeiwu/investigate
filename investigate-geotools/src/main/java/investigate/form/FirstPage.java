package investigate.form;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

public class FirstPage extends FormPage {

	public static final String ID = "com.fengmanfei.myrcp.forms.FirstPage";

	public FirstPage(FormEditor editor) {
		// 构造方法，设置Form页的ID和名称
		super(editor, ID, "第一页");
	}

	// 覆盖父类中的方法
	// 在该方法中创建表单区域的各种控件
	protected void createFormContent(IManagedForm managedForm) {
		// 通过managedForm对象获得表单工具对象
		FormToolkit toolkit = managedForm.getToolkit();
		// 通过managedForm对象获得ScrolledForm可滚动的表单对象
		ScrolledForm form = managedForm.getForm();
		// 设置表单文本
		form.setText("这是第一页，Hello, Eclipse 表单");
		// 创建表格布局
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 2;// 表格的列数
		layout.bottomMargin = 10;// 下补白
		layout.topMargin = 10;// 上补白
		layout.leftMargin = 10;// 左补白
		layout.rightMargin = 10;// 右补白
		form.getBody().setLayout(layout);// 设置表格的布局

		// 创建第一个标签
		Label l1 = toolkit.createLabel(form.getBody(), "这是很长的一段文本文本1", SWT.WRAP);
		// 创建第二个标签
		Label l2 = toolkit.createLabel(form.getBody(), "这是文本2", SWT.WRAP);
		// 创建一个TableWrapData对象，设置为水平和垂直充满式填充
		TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
		// 将布局数据应用到第二个标签
		l2.setLayoutData(td);
		Label l3 = toolkit.createLabel(form.getBody(), "这是文本3", SWT.WRAP);
		// 第三个标签的布局数据
		td = new TableWrapData();
		td.colspan = 2;// 设置单元格的跨两列
		l3.setLayoutData(td);
		// 第四个标签的布局数据
		Label l4 = toolkit.createLabel(form.getBody(), "这是文本4", SWT.WRAP);
		td = new TableWrapData();
		td.rowspan = 2;// 设置单元格跨两行
		td.grabVertical = true;// 垂直抢占
		l4.setLayoutData(td);
		Label l5 = toolkit.createLabel(form.getBody(), "这是文本5", SWT.WRAP);
		Label l6 = toolkit.createLabel(form.getBody(), "这是文本6", SWT.WRAP);
		form.getBody().setBackground(form.getBody().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
	}
}
