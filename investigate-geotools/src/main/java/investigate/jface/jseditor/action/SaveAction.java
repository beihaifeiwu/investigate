package investigate.jface.jseditor.action;

import investigate.jface.jseditor.JSEditor;
import investigate.jface.jseditor.ResourceManager;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

public class SaveAction extends Action {
	private JSEditor editor;
	public SaveAction(JSEditor editor) {
		super("保存@Ctrl+S");
		this.setImageDescriptor(ImageDescriptor.createFromFile(ResourceManager.class,"icons\\save.gif"));
		this.editor = editor;
	}
	public void run() {
		editor.getEventManager().saveFile();
	}
}
