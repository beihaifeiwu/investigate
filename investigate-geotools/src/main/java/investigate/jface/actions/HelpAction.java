package investigate.jface.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

public class HelpAction extends Action{
	public HelpAction(){
		super();
	    setText("帮助内容(&H)");
	    setToolTipText("帮助");
	    setImageDescriptor(ImageDescriptor.createFromFile(NewAction.class,"icons/help.gif"));
	}
	/* （非 Javadoc）
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
	}
}
