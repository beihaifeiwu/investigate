package investigate.jface.jseditor.action;

import investigate.jface.jseditor.JSEditor;
import investigate.jface.jseditor.ResourceManager;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

public class RedoAction extends Action {
	private JSEditor editor;
	public RedoAction(JSEditor editor) {
		super("重做@Ctrl+Y");
		this.setImageDescriptor(ImageDescriptor.createFromFile(ResourceManager.class,"icons\\redo.gif"));
		this.editor = editor;
	}
	public void run() {
		editor.getUndoManager().redo();
	}
}
