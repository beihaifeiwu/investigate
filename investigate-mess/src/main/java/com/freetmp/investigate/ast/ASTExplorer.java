package com.freetmp.investigate.ast;

/**
 * Parses a source file and displays its nodes in a SWT Tree widget
 * You should run the main method inside the Eclipse IDE. 
 * Do not forget to add the jvm option java.library.path specifing
 * the SWT native library. On a Windows system it may look like this:
 * -Djava.library.path=C:\eclipse\plugins\org.eclipse.swt.win32_3.0.2\os\win32\x86  
 * @author Manoel Marques
 */
import java.io.*;
import org.eclipse.core.runtime.*;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.jface.dialogs.*;
import org.eclipse.jface.operation.*;
import java.lang.reflect.*;

public class ASTExplorer extends Composite {
	
	private Text addressControl;
	private Tree treeControl;
	private StyledText textControl;
	private StyledText errorsTextControl;
	private IProblem[] problems;
	private Color blue;
	private Color red;
		
	public ASTExplorer(Composite parent,int style) {
		super(parent,style);
		this.problems = new IProblem[0];
		this.blue = getDisplay().getSystemColor(SWT.COLOR_BLUE);
		this.red = getDisplay().getSystemColor(SWT.COLOR_RED);
			
		GridLayout gridLayout = new GridLayout();
		super.setLayout(gridLayout);
		
		Composite composite = new Composite(this,SWT.NONE);
		gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		composite.setLayout(gridLayout);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Button button = new Button(composite,SWT.PUSH);
		button.setText("File...");
		button.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				Shell shell = ((Button)event.widget).getShell();  
				FileDialog dialog = new FileDialog(shell,SWT.PRIMARY_MODAL | SWT.OPEN);
				dialog.setText("Java Source File");
				dialog.setFilterExtensions(new String[] {"*.java"});
				String file = dialog.open(); 
				if (file == null) return; 
				addressControl.setText(file);
				go();
			}
		});
		
		this.addressControl = new Text(composite,SWT.BORDER); 
		this.addressControl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
									
		button = new Button(composite,SWT.PUSH);
		button.setText("Go");
		button.getShell().setDefaultButton(button); 
		button.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				go();
			}
		});
		
		SashForm sashFormVertical = new SashForm(this,SWT.VERTICAL);
		sashFormVertical.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | 
				GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL));
		
		SashForm sashFormHorizontal = new SashForm(sashFormVertical,SWT.HORIZONTAL);
		sashFormHorizontal.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | 
				GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL));
			
		this.treeControl = new Tree(sashFormHorizontal,SWT.BORDER | SWT.SINGLE);
		this.treeControl.setLayoutData(new GridData(GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_FILL));
		this.treeControl.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// first reset any previous selections 
				StyleRange styleRange = new StyleRange();
				styleRange.start = 0;
				styleRange.length = textControl.getCharCount();
				textControl.setStyleRange(styleRange);
				
				// select text
				int startSelection = -1;
				Tree tree = (Tree) e.widget;
				Display display = tree.getDisplay(); 
				TreeItem[] items = tree.getSelection();
				for (int i=0; i < items.length; ++i) {
					ASTNode node = (ASTNode) items[i].getData(ASTExplorerVisitor.NODE);
					if (node != null) {
						styleRange = createRange(node.getStartPosition(),node.getLength(),blue);
						textControl.setStyleRange(styleRange);
						if (startSelection < 0 || styleRange.start < startSelection)
							startSelection = styleRange.start;
					}
				}
				if (startSelection >= 0)
					textControl.setSelection(startSelection);
				
				// select errors
				for (int i=0; i < problems.length; ++i) {
					IProblem problem = problems[i];
					String msg = problem.getMessage();
					styleRange = createRange(problem.getSourceStart(),
								problem.getSourceEnd() - problem.getSourceStart() + 1,red);
					textControl.setStyleRange(styleRange);
				}
			}
		});
			
		this.textControl = new StyledText(sashFormHorizontal, SWT.BORDER | SWT.READ_ONLY | SWT.H_SCROLL | SWT.V_SCROLL);
		this.textControl.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL));
		
		this.errorsTextControl = new StyledText(sashFormVertical, SWT.BORDER | SWT.READ_ONLY | SWT.H_SCROLL | SWT.V_SCROLL);
		this.errorsTextControl.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		
		sashFormVertical.setWeights(new int[]{80, 20});
	}
	
	static private StyleRange createRange(int start,int length,Color color) {
		StyleRange styleRange = new StyleRange();
		styleRange.start = start;
		styleRange.length = length; 
		styleRange.fontStyle = SWT.BOLD;
		styleRange.foreground = color;
		return styleRange;
	}
	
	private void go() {
		try {			
			SetFile(addressControl.getText());
		}
		catch (InterruptedException e) {	
			reset();
		}
		catch (InvocationTargetException e) {
			reset();
			showError(e.getTargetException());
		}
		catch (Exception e) {
			reset();
			showError(e);
		}
	}
	
	private void reset() {
		this.treeControl.removeAll();
		this.textControl.setText("");
		this.errorsTextControl.setText("");
		this.problems = new IProblem[0];
	}
	
	private void SetFile(final String path) throws InterruptedException, InvocationTargetException {
		reset();
		ProgressMonitorDialog dialog = new ProgressMonitorDialog(getShell());
		dialog.run(true, true, new IRunnableWithProgress() {
			public void run(final IProgressMonitor monitor) throws InvocationTargetException {
				try {	
					File file = new File(path);
					BufferedReader in = new BufferedReader(new FileReader(file));
					StringBuffer buffer = new StringBuffer(); 
					String line = null;
					while (null != (line = in.readLine())) {
						buffer.append("\t" + line);
						buffer.append("\n");
						if (monitor.isCanceled()) return;
					}	
					
					ASTParser parser = ASTParser.newParser(AST.JLS2);
					if (monitor.isCanceled()) return;
					parser.setKind(ASTParser.K_COMPILATION_UNIT);
					if (monitor.isCanceled()) return;
					final String text = buffer.toString();
					parser.setSource(text.toCharArray());
					if (monitor.isCanceled()) return;
					final CompilationUnit node = (CompilationUnit) parser.createAST(monitor);
					if (monitor.isCanceled()) return;
					getDisplay().syncExec(new Runnable() {
						public void run() {
							ASTVisitor visitor = new ASTExplorerVisitor(treeControl,monitor);
							node.accept(visitor);
							if (!monitor.isCanceled()) {
								textControl.setText(text);	
								IProblem[] errors = node.getProblems();
								if (errors != null && errors.length > 0) {
									problems = errors;
									int startSelection = -1;
									StringBuffer msg = new StringBuffer(); 
									for (int i=0; i < problems.length; ++i) {
										IProblem problem = problems[i];
										StyleRange errorRange = createRange(problem.getSourceStart(),
													problem.getSourceEnd() - problem.getSourceStart() + 1,red);
										textControl.setStyleRange(errorRange);
										if (startSelection < 0 || errorRange.start < startSelection)
											startSelection = errorRange.start;
										
										String message = problem.getMessage() + " line: " + 
												problem.getSourceLineNumber();	
										msg.append(message);
										msg.append("\n");
									}
									if (startSelection >= 0)
										textControl.setSelection(startSelection);
									
									if (msg.length() > 0)
										errorsTextControl.setText(msg.toString());
								}
							}	
						}
					}); 					
				}
				catch (IOException e) {
					throw new InvocationTargetException(e);
				}
			}
		});	
	}

	// do not allow layout modifications
	public void setLayout(Layout layout) {
	}
	
	private void showError(Throwable e) {
		MessageBox msgBox = new MessageBox(addressControl.getShell(),
				SWT.APPLICATION_MODAL | SWT.OK | SWT.ICON_ERROR); 
		msgBox.setText("AST Explorer Error");
		String msg = e.getMessage();
		if (null == msg) 
			msg = e.toString();
		
		msgBox.setMessage(msg);
		msgBox.open(); 
	}
	
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("AST Explorer");
		shell.setLayout(new FillLayout());
					
		ASTExplorer astExplorer = new ASTExplorer(shell,SWT.NONE);
		final Point minimum = shell.computeSize(SWT.DEFAULT,SWT.DEFAULT,true);
		shell.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				Shell shell = (Shell)e.widget;
				Point size = shell.getSize();
				boolean change = false; 
				if (size.x < minimum.x) {
					size.x = minimum.x;
					change = true; 
				}	
				if (size.y < minimum.y) {
					size.y = minimum.y;
					change = true; 
				}				
				if (change) 
					shell.setSize(size);
			}
		});
		shell.open();
					
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}