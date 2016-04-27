package investigate.jface.jseditor.action;

import investigate.jface.jseditor.JSEditor;
import investigate.jface.jseditor.ResourceManager;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

public class OpenAction extends Action {
	private JSEditor editor;
	public OpenAction(JSEditor editor) {
		super("打开@Ctrl+O");
		this.setImageDescriptor(ImageDescriptor.createFromFile(ResourceManager.class,"icons\\open.gif"));
		this.editor = editor;
	}
	public void run() {
		editor.getEventManager().openFile();
	}
}
