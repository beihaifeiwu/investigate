package investigate.swt;

import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ImageCursor {
	public static void main(String[] args) {
		Display display = new Display();
		ImageData sourceData = new ImageData(
        ImageCursor.class.getClassLoader().getResourceAsStream("icons/progress/waiting.gif"));
		Cursor cursor = new Cursor(display , sourceData ,10, 10);
		Shell shell = new Shell(display);
		shell.setSize(150, 150);
		shell.open();
		shell.setCursor(cursor);
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		cursor.dispose();
		display.dispose();
	}
}
