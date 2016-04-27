package investigate.jface.jseditor.action;

import investigate.jface.jseditor.JSEditor;
import investigate.jface.jseditor.ResourceManager;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

public class UndoAction extends Action {
	private JSEditor editor;
	public UndoAction(JSEditor editor) {
		super("撤销@Ctrl+Z");
		this.setImageDescriptor(ImageDescriptor.createFromFile(ResourceManager.class,"icons\\undo.gif"));
		this.editor = editor;
	}

	public void run() {
		editor.getUndoManager().undo();
	}
}
