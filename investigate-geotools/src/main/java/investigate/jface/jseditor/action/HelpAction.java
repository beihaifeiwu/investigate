package investigate.jface.jseditor.action;

import investigate.jface.jseditor.JSEditor;
import investigate.jface.jseditor.ResourceManager;
import investigate.jface.jseditor.dialog.AboutDialog;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

public class HelpAction extends Action {
	private JSEditor editor;
	public HelpAction(JSEditor editor) {
		super("帮助@Ctrl+O");
		this.setImageDescriptor(ImageDescriptor.createFromFile(ResourceManager.class,"icons\\help.gif"));
		this.editor = editor;
	}
	public void run() {
		AboutDialog dlg = new AboutDialog( editor.getShell());
		dlg.open();
	}
}
