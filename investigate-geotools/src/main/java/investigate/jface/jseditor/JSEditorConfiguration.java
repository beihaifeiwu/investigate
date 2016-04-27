package investigate.jface.jseditor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.*;
import org.eclipse.jface.text.presentation.*;
import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.source.*;

public class JSEditorConfiguration extends SourceViewerConfiguration {

	private JSEditor editor ;
	public JSEditorConfiguration( JSEditor editor ){
		this.editor=editor;
	}
	//覆盖父类中的方法，主要提供代码着色功能
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
	    DefaultDamagerRepairer dr = new DefaultDamagerRepairer(new JSCodeScanner());
	    reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
	    reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
	    return reconciler;
	}
	//覆盖父类中的方法，主要提供内容辅助功能
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		//创建内容助手对象
		ContentAssistant contentAssistant = new ContentAssistant();
		//设置提示的内容
		contentAssistant.setContentAssistProcessor(new ObjectContentAssistant(),IDocument.DEFAULT_CONTENT_TYPE);
		//设置自动激活提示
		contentAssistant.enableAutoActivation(true);
		//设置自动激活提示的时间为500毫秒
		contentAssistant.setAutoActivationDelay(500);
		return contentAssistant;
	}
}
