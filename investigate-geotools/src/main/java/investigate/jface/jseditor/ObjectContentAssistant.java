package investigate.jface.jseditor;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.text.*;
import org.eclipse.jface.text.contentassist.*;

public class ObjectContentAssistant implements IContentAssistProcessor {

	// 接口中的方法，获得内容的提示数组
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		IDocument doc = viewer.getDocument();
		//获得提示列表中的内容
		List list = computObject(getObjectName(doc, offset), offset);
		return (CompletionProposal[]) list.toArray(new CompletionProposal[list
				.size()]);
	}

	// 接口中的方法，空实现
	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		return null;
	}

	// 接口中的方法，设置何时激活提示，这里当输入.时激活内容提示
	public char[] getCompletionProposalAutoActivationCharacters() {
		return new char[] { '.' };
	}

	// 接口中的方法
	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}

	// 接口中的方法
	public String getErrorMessage() {
		return null;
	}

	// 接口中的方法
	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}
	//获得获得提示时，之前输入的字符
	public String getObjectName(IDocument doc, int offset) {
		//offset为当前光标所在位置，也就是偏移量
		StringBuffer buf = new StringBuffer();
		offset--;
		//依次从当前位置查找，直到字符串为空格或者是"." 时停止，
		//然后将字符串反转
		while (true) {
			try {
				char c = doc.getChar(--offset);//获得前一个字符
				if (Character.isWhitespace(c))//如果为空格，跳出
					break;
				if (c=='.')//如果为“.”则跳出
					break;
				buf.append(c);
			} catch (BadLocationException e) {
				break;
			}
		}
		return buf.reverse().toString();//最后将字符反转
	}

	// 设置内容提示的中的内容
	public List computObject(String objName, int offset) {
		//objName首先看objName是否为内置的对象
		List list = new ArrayList();
		boolean bFind = false;
		for (int i = 0; i < Constants.JS_SYNTAX_BUILDIB_OBJECT.length; i++) {
			String tempString = Constants.JS_SYNTAX_BUILDIB_OBJECT[i];
			if (objName.equals(tempString))
			{
				bFind = true;
				break;
			}
		}
		//如果是内置对象，则将所有的对象的字符添加到内容提示列表中
		if (bFind) {
			for (int i = 0; i < Constants.JS_SYNTAX_BUILDIB_OBJECT.length; i++) {
				String insert = objName + "."+ Constants.JS_SYNTAX_BUILDIB_OBJECT[i];
				//CompletionProposal对象
				CompletionProposal proposal = new CompletionProposal(insert,
						offset - objName.length() - 1, objName.length()+1, insert.length()+1,null,null,null,"aaa"
						);
				list.add(proposal);
				//list.
			}
		}
		return list;
	}

}
