package investigate.jface.mvc;

import investigate.swt.util.ImageFactory;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import java.io.File;

public class ListWindow extends ApplicationWindow {

	public ListWindow() {
		super(null);
	}

	protected Control createContents(Composite parent) {
		ListViewer list = new ListViewer(parent);
		list.setContentProvider(new IStructuredContentProvider() {

			public void dispose() {
				// TODO 自动生成方法存根

			}

			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
				// TODO 自动生成方法存根

			}

			public Object[] getElements(Object inputElement) {
				return ((File) inputElement).listFiles();
			}

		});
		list.setLabelProvider(new ILabelProvider() {

			public Image getImage(Object element) {
				File file = (File) element;
				if (file.isDirectory())
					return ImageFactory.loadImage(Display.getCurrent(),
							ImageFactory.FOLDER);
				else
					return ImageFactory.loadImage(Display.getCurrent(),
							ImageFactory.FILE);
			}

			public String getText(Object element) {
				return ((File) element).getName();
			}

			public void addListener(ILabelProviderListener listener) {
				// TODO 自动生成方法存根

			}

			public void dispose() {
				// TODO 自动生成方法存根

			}

			public boolean isLabelProperty(Object element, String property) {
				// TODO 自动生成方法存根
				return false;
			}

			public void removeListener(ILabelProviderListener listener) {
				// TODO 自动生成方法存根

			}

		});
		list.setInput( new File("F:\\"));
		return parent;
	}

	public static void main(String[] args) {
		ListWindow test = new ListWindow();
		test.setBlockOnOpen(true);
		test.open();
		Display.getCurrent().dispose();

	}

}
