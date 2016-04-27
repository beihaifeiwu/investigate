package investigate.jface.wizard;

import org.eclipse.jface.wizard.Wizard;

public class BookSurveyWizard extends Wizard {

	public static final String Q1 = "QUESTION_1";
	public static final String Q2 = "QUESTION_2";
	public static final String ASK_FOR_CONTACT = "ASK_FOR_CONTACT";
	public static final String EMAIL = "EMAIL";
	public static final String THANKS = "THANKS";
	//声明向导的三个页面
	private QuestionOne one;
	private QuestionTwo two;
	private Thanks thanks;
	public BookSurveyWizard(){
		//创建三个向导页面对象
		one = new QuestionOne();
		two = new QuestionTwo();
		thanks = new Thanks();
		//分别添加这三个页面
		this.addPage( one );
		this.addPage( two );
		this.addPage( new AskForContact());
		this.addPage( new Email());
		this.addPage( thanks );
		this.setWindowTitle("读者调查向导");//向导标题
		this.setHelpAvailable( true );
	}
	/* （非 Javadoc）
	 * @see org.eclipse.jface.wizard.Wizard#canFinish()
	 * 确定“完成”按钮是否可用,true为可用，false为不可用
	 */
	public boolean canFinish() {
		//仅当当前页面为感谢页面时才将“完成”按钮置为可用状态
		if (this.getContainer().getCurrentPage() == thanks )
			return true;
		else
			return false;
	}
	//必须实现该方法，当单击完成按钮后调用此方法
	public boolean performFinish() {
		return true;
	}
}
