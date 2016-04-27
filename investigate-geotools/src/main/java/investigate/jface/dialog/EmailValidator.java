package investigate.jface.dialog;

import org.eclipse.jface.dialogs.IInputValidator;

public class EmailValidator implements IInputValidator{
	public String isValid(String newText) {
		//如果输如的字符串中不含@字符，输入无效
		if (newText.indexOf("@")==-1)
			return "错误！请输入有效的电子邮件地址。";
		return null;
	}
}
