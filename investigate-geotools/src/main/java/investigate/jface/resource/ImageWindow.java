package investigate.jface.resource;

import org.eclipse.jface.resource.DeviceResourceException;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import java.net.MalformedURLException;
import java.net.URL;

public class ImageWindow extends ApplicationWindow{

	public ImageWindow() {
		super(null);
	}

	protected Control createContents(Composite parent) {
		ImageDescriptor image = ImageDescriptor.createFromFile( ImageWindow.class , "eclipse.jpg" );
		ImageDescriptor urlImage = null;
		try {
			URL url = new URL("http://www.eclipse.org/eclipse.org-common/themes/Phoenix/images/header_logo.gif");
			urlImage = ImageDescriptor.createFromURL(url);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Image i = new Image(Display.getCurrent(), "F:\\icon\\edit.gif");
		Printer printer = new Printer();
		ImageDescriptor id  = ImageDescriptor.createFromImage(i,printer);
		printer.dispose();
		
		ImageData data = new ImageData("F:\\icon\\edit.gif");
		ImageDescriptor imageData  = ImageDescriptor.createFromImageData( data );
		FontData fontData = new FontData("隶书",30, SWT.BOLD);
		FontDescriptor fd = FontDescriptor.createFrom(fontData);
		Button bt = new Button( parent , SWT.NONE);
		//bt.setImage( image.crea teImage());
		bt.setText("设置字体");
		try {
			bt.setFont( fd.createFont(Display.getCurrent()));
		} catch (DeviceResourceException e) {
			e.printStackTrace();
		}
		//id
		//i.dispose();
		//System.out.print(i.isDisposed());
		JFaceResources.getDialogFont();
		return parent;
	}

	public static void main(String[] args) {

		ImageWindow test = new ImageWindow();
		//test.getShell().setImage( id.createImage() );
		test.setBlockOnOpen(true);
		test.open();
		Display.getCurrent().dispose();
	}
}
