package investigate.jface.preference;

import org.eclipse.jface.preference.*;

public class FieldEditorPage extends FieldEditorPreferencePage {

	public FieldEditorPage() {
		super(GRID);//页面的样式，还可以使用FLAT常量
	}
	//该方法为实现父类中的抽象方法，在该方法中添加所需的编辑器对象
	protected void createFieldEditors() {

		//创建一个字符型编辑器对象
		StringFieldEditor userName = new StringFieldEditor(
				Constants.USER_NAME, //选项的key值
				"登录用户名:", //显示的标签名
				getFieldEditorParent());//该字段编辑器的父类面板
		//调用父类方法，将该方法添加到该页中
		addField(userName);
		BooleanFieldEditor bfe = new BooleanFieldEditor("show","Boolean",getFieldEditorParent());
		addField(bfe);
		ColorFieldEditor cfe = new ColorFieldEditor("color","Color",getFieldEditorParent());
		addField(cfe);
		FontFieldEditor ffe = new FontFieldEditor("font","Font",getFieldEditorParent());
		addField(ffe);
		PathEditor pfe = new PathEditor("path","Path","请选择所选的路径",getFieldEditorParent());
		addField(pfe);
		RadioGroupFieldEditor rgfe = new RadioGroupFieldEditor(
				"group", //选项的key
				"RadioGroup",//分组框显示的文本
				2,//一行显示的单选按钮个数
				new String[][] { { "Radio one", "one"},	{"Radio two", "two"},{"Radio three", "three"}	},//单选按钮的标签和值
				getFieldEditorParent(),//父类的面板
				true);//true表示使用分组面板，false将使用普通的面板
		addField(rgfe);
		ScaleFieldEditor sfe = new ScaleFieldEditor("scale","Scale",getFieldEditorParent(),0,100,5,10);
		addField(sfe);

		IntegerFieldEditor ife = new IntegerFieldEditor("int","Int",getFieldEditorParent());
		addField(ife);
		DirectoryFieldEditor dfe = new DirectoryFieldEditor("dirctory","Directory",getFieldEditorParent());
		addField(dfe);
		FileFieldEditor filefe = new FileFieldEditor("file","File",getFieldEditorParent());
		addField(filefe);
	}
}
