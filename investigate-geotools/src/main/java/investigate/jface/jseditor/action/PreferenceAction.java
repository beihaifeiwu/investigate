package investigate.jface.jseditor.action;

import investigate.jface.jseditor.JSEditor;
import investigate.jface.jseditor.JSPreferencePage;
import investigate.jface.jseditor.ResourceManager;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.resource.ImageDescriptor;

public class PreferenceAction extends Action {
	private JSEditor editor;

	public PreferenceAction(JSEditor editor) {
		super("首选项@Ctrl+R");
		this.setImageDescriptor(ImageDescriptor.createFromFile(ResourceManager.class,"icons\\prefs.gif"));
		this.editor = editor;
	}

	public void run() {
		PreferenceManager mgr = new PreferenceManager();
		mgr.addToRoot(new PreferenceNode("edit", "编辑器", null,JSPreferencePage.class.getName()));
		PreferenceDialog dlg = new PreferenceDialog(editor.getShell(), mgr);
		dlg.setPreferenceStore(editor.getPreference());
		dlg.open();

	}

}
