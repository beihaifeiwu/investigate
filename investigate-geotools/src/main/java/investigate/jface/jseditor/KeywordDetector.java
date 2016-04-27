package investigate.jface.jseditor;

import org.eclipse.jface.text.rules.IWordDetector;
public class KeywordDetector implements IWordDetector {

	// 接口中的方法,字符是否是单词的开始
	public boolean isWordStart(char c) {
		//循环所有的关键字
		//如果有关键字中的第一个字符匹配该字符，则返回true
		for (int i = 0, n = Constants.JS_SYNTAX_KEYWORD.length; i < n; i++)
			if (c == ((String) Constants.JS_SYNTAX_KEYWORD[i]).charAt(0))
				return true;
		return false;
	}

	// 接口中的方法,字符是否是单词中的一部分
	public boolean isWordPart(char c) {
		//循环所有的关键字
		//如果关键字的字符中有该字符，则返回true
		for (int i = 0, n = Constants.JS_SYNTAX_KEYWORD.length; i < n; i++)
			if (((String) Constants.JS_SYNTAX_KEYWORD[i]).indexOf(c) != -1)
				return true;
		return false;
	}

}
