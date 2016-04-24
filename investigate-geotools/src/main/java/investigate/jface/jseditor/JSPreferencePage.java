package investigate.jface.jseditor;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FontFieldEditor;

public class JSPreferencePage extends FieldEditorPreferencePage{

	private ColorFieldEditor keyword;
	private ColorFieldEditor comment;
	private ColorFieldEditor string;
	private ColorFieldEditor object;
	private FontFieldEditor codeFont;
	public JSPreferencePage() {
		super(GRID);
	}

	protected void createFieldEditors() {
		keyword = new ColorFieldEditor(Constants.COLOR_KEYWORD,"关键字颜色：",getFieldEditorParent());
		addField(keyword);
		comment = new ColorFieldEditor(Constants.COLOR_COMMENT,"注释颜色：",getFieldEditorParent());
		addField(comment);
		string = new ColorFieldEditor(Constants.COLOR_STRING,"字符串颜色：",getFieldEditorParent());
		addField(string);
		object = new ColorFieldEditor(Constants.COLOR_OBJECT,"内置对象颜色：",getFieldEditorParent());
		addField(object);
		codeFont= new FontFieldEditor(Constants.CODE_FONT,"字体：",getFieldEditorParent());
		addField(codeFont);
	}
	
}
