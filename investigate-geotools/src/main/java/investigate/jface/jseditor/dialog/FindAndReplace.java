package investigate.jface.jseditor.dialog;

import investigate.jface.jseditor.JSEditor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class FindAndReplace extends Dialog {

	private JSEditor editor;
	private Button btFind;//查找按钮
	private Button btReplace;//替换按钮
	private Button btFindAndReplace;//查找与替换按钮
	private Button btClose;//关闭按钮
	
	private FindReplaceDocumentAdapter findDdapater;
	public FindAndReplace(JSEditor editor ,Shell parentShell) {
		super(parentShell);
		this.editor = editor ;
		//查找文档字符的适配器对象
		findDdapater = new FindReplaceDocumentAdapter(this.editor.getDocument());
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("查找/替换");
		newShell.setSize(200,270);
	}

	protected Control createContents(Composite parent) {
		//创建对话框的控件
		parent.setLayout(new GridLayout(2, false));
		new Label( parent , SWT.LEFT).setText("查找：");
	    final Text findText = new Text(parent, SWT.BORDER);
	    findText.setLayoutData( new GridData(GridData.FILL_HORIZONTAL));
	    
		new Label( parent , SWT.LEFT).setText("替换为：");
	    final Text replaceText = new Text(parent, SWT.BORDER);
	    replaceText.setLayoutData( new GridData(GridData.FILL_HORIZONTAL));
	    
	    Group group = new Group( parent, SWT.NONE);
	    GridData data = new GridData(GridData.FILL_HORIZONTAL);
	    data.horizontalSpan = 2;
	    group.setLayoutData(data);
	    group.setText("方向");
	    group.setLayout(  new GridLayout(2,true) );
	    
	    final Button forwardButton = new Button(group,SWT.RADIO);
	    forwardButton.setText("前进");
	    
	    final Button backButton = new Button(group,SWT.RADIO);
	    backButton.setText("后退");
	    
	    group = new Group( parent, SWT.NONE);
	    data = new GridData(GridData.FILL_HORIZONTAL);
	    data.horizontalSpan = 2;
	    group.setLayoutData(data);
	    group.setText("选项");
	    group.setLayout(  new GridLayout(2,true) );
	    
	    final Button match = new Button(group,SWT.CHECK);
	    match.setText("区分大小写");
	    
	    final Button wholeWord = new Button(group,SWT.CHECK);
	    wholeWord.setText("整个字");
	    
	    final Button regexp = new Button(group,SWT.CHECK);
	    regexp.setText("正则表达式");
	    
	    Composite composite = new Composite( parent , SWT.NONE);
	    data = new GridData(GridData.FILL_HORIZONTAL);
	    data.horizontalSpan = 2;
	    composite.setLayoutData(data);
	    composite.setLayout( new GridLayout(2,true));
	    
	    btFind = new Button( composite , SWT.PUSH );
	    btFind.setText("查找");
	    btFind.setLayoutData( new GridData(GridData.FILL_HORIZONTAL));
	    
	    btReplace = new Button( composite , SWT.PUSH );
	    btReplace.setText("替换");
	    btReplace.setLayoutData( new GridData(GridData.FILL_HORIZONTAL));
	    
	    btFindAndReplace = new Button( composite , SWT.PUSH );
	    btFindAndReplace.setText("查找/替换");
	    btFindAndReplace.setLayoutData( new GridData(GridData.FILL_HORIZONTAL));
	    
	    btClose = new Button( composite , SWT.PUSH );
	    btClose.setText("关闭窗口");
	    btClose.setLayoutData( new GridData(GridData.FILL_HORIZONTAL));
	    //设置按钮的事件
	    //设置查找选项时，正则表达式与匹配整个字符不能同时使用
	    wholeWord.addSelectionListener( new SelectionAdapter(){
	    	  public void widgetSelected(SelectionEvent event) {
	    		  if ( wholeWord.getSelection() ){
	    			  regexp.setSelection( false );
	    			  regexp.setEnabled( false );
	    		  }else{
	    			  regexp.setEnabled( true );
	    		  }
		        }
	    });
	    regexp.addSelectionListener( new SelectionAdapter(){
	    	  public void widgetSelected(SelectionEvent event) {
	    		  if ( regexp.getSelection() ){
	    			  wholeWord.setSelection( false );
	    			  wholeWord.setEnabled( false );
	    		  }else{
	    			  wholeWord.setEnabled( true );
	    		  }
		        }
	    });
	    //为查找按钮注册事件监听器
		btFind.addSelectionListener(new SelectionAdapter() {
	        public void widgetSelected(SelectionEvent event) {
	          boolean b = editor.getEventManager().isFind( findDdapater,
	        		  findText.getText(),forwardButton.getSelection(),match.getSelection(),
	        		  wholeWord.getSelection(),regexp.getSelection());
	          //如果找到匹配的字符，将替换和替换全部按钮设置为可用状态
	          enableReplaceButtons(b);
	        }
	    });
		//为替换按钮注册事件监听器
		btReplace.addSelectionListener(new SelectionAdapter() {
	        public void widgetSelected(SelectionEvent event) {
	          editor.getEventManager().doReplace( findDdapater , replaceText.getText());
	          enableReplaceButtons(false);
	        }
	    });
		//为查找/按钮注册事件监听器
		btFindAndReplace.addSelectionListener(new SelectionAdapter() {
	        public void widgetSelected(SelectionEvent event) {
	          editor.getEventManager().doReplace( findDdapater , replaceText.getText());
	          boolean b = editor.getEventManager().isFind( findDdapater,
	        		  findText.getText(),forwardButton.getSelection(),match.getSelection(),
	        		  wholeWord.getSelection(),regexp.getSelection());
	          enableReplaceButtons(b);
	        }
	    });
		//为关闭按钮注册监听器
		btClose.addSelectionListener(new SelectionAdapter() {
	        public void widgetSelected(SelectionEvent event) {
	        	getShell().close();
	        }
	    });
		//设置向前为默认选中状态
		forwardButton.setSelection(true);
		//初始化时替换和查找替换按钮不可用
	    enableReplaceButtons(false);
	    //设置焦点为查找的文本框
	    findText.setFocus();
		return parent;
	}

	private void enableReplaceButtons(boolean enable) {
		btReplace.setEnabled(enable);
		btFindAndReplace.setEnabled(enable);
	}
	
	
}