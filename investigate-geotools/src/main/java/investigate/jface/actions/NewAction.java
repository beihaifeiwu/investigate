package investigate.jface.actions;

import investigate.jface.MainWindow;
import investigate.jface.util.FileManager;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;

public class NewAction extends Action {

	public NewAction( ){
		super();
	    setText("新建(&N)");
	    this.setAccelerator( SWT.ALT + SWT.SHIFT + 'N');
	    setToolTipText("新建");
	    setImageDescriptor(ImageDescriptor.createFromFile(NewAction.class,"icons/new.gif"));
	}
	/* （非 Javadoc）
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		MainWindow.getApp().getContent().setText("");
		MainWindow.getApp().setManager( new FileManager());
	}
	
}
