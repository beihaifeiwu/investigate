package investigate.jface.jseditor.action;

import investigate.jface.jseditor.JSEditor;
import investigate.jface.jseditor.ResourceManager;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

public class PrintAction extends Action{
	private JSEditor editor;
	public PrintAction(JSEditor editor){
		super("打印@Ctrl+P");
		this.setImageDescriptor(ImageDescriptor.createFromFile(ResourceManager.class,"icons\\print.gif"));
		this.editor = editor;
	}
	public void run() {
		editor.getViewer().getTextWidget().print();
	}
}
