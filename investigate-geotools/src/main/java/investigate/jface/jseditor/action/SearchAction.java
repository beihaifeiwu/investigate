package investigate.jface.jseditor.action;

import investigate.jface.jseditor.JSEditor;
import investigate.jface.jseditor.ResourceManager;
import investigate.jface.jseditor.dialog.FindAndReplace;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

public class SearchAction extends Action {
	private JSEditor editor;
	public SearchAction(JSEditor editor) {
		super("查找\\替换@Ctrl+F");
		this.setImageDescriptor(ImageDescriptor.createFromFile(ResourceManager.class,"icons\\search.gif"));
		this.editor = editor;
	}
	public void run() {
		new FindAndReplace( editor , editor.getShell()).open();
	}
}
