package investigate.jface.actions;

import investigate.jface.MainWindow;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;


public class PasteAction extends Action{
	public PasteAction( ){
		super();
	    setText("粘贴(&C)");
	    setToolTipText("粘贴");
	    setAccelerator( SWT.CTRL + 'V');
	    setImageDescriptor(ImageDescriptor.createFromFile(NewAction.class,"icons/paste.gif"));
	}
	/* （非 Javadoc）
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		MainWindow.getApp().getContent().paste();
	}
}
