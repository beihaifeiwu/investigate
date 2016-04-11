package investigate.swt.util;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import java.util.Enumeration;
import java.util.Hashtable;

public class ImageFactory {

	//将构造方法设置为private，禁止创建该类的实例
	private ImageFactory() {}
    //图片保存的ClassPath路径
	public static final String CLASS_PATH_PATH = "icons/etool16/";
    //一些图片名称的常量
	public static final String DELETE_EDIT = "delete_edit.gif";

	public static final String SAVE_EDIT = "save_edit.gif";

	public static final String SCOPY_EDIT = "copy_edit.gif";

	public static final String CUT_EDIT = "cut_edit.gif";

	public static final String PRINT_EDIT = "print_edit.gif";

	public static final String HELP_CONTENTS = "help_contents.gif";
	
	public static final String ECLIPSE = "eclipse.gif";
	
	public static final String SAMPLES = "samples.gif";
	public static final String ADD_OBJ = "add_obj.gif";
	public static final String DELETE_OBJ = "delete_obj.gif";
	
	public static final String BACKWARD_NAV = "backward_nav.gif";
	public static final String FORWARD_NAV = "forward_nav.gif";
	public static final String ICON_GIRL = "girl.gif";
	public static final String ICON_BOY = "boy.gif";
	
	public static final String TOC_CLOSED = "toc_closed.gif";
	public static final String TOC_OPEN = "toc_open.gif";
	public static final String TOPIC = "topic.gif";
	
	public static final String UNDERLIN = "underline.gif";
	public static final String ITALIC = "italic.gif";
	public static final String BOLD = "bold.gif";
	public static final String BGCOLOR = "bgcol.gif";
	public static final String FORCOLOR = "forecol.gif";
	public static final String PROGRESS_TASK = "progress_task.gif";
	public static final String SAMPLE_ICON = "sample_icon.gif";
	public static final String FILE = "file.gif";
	public static final String FOLDER = "folder.gif";
    //使用Hashtable保存使用的图片资源 
	private static Hashtable htImage = new Hashtable();
    //加载图片.首先从htImage中获得图片对象，
	//如果没有，则加载新的图片并放入到htImage
	public static Image loadImage(Display display, String imageName) {
		Image image = (Image) htImage.get(imageName.toUpperCase());
		if (image == null) {
			image = new Image(display, ImageFactory.class.getClassLoader().getResourceAsStream(CLASS_PATH_PATH  + imageName));
			htImage.put(imageName.toUpperCase(), image);
		}
		return image;
	}

	//释放htImage中的所有的图片资源
	public static void dispose() {
		Enumeration e = htImage.elements();
		while (e.hasMoreElements()) {
			Image image = (Image) e.nextElement();
			if (!image.isDisposed())
				image.dispose();
		}
	}
}
