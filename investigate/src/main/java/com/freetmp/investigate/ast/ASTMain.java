package com.freetmp.investigate.ast;

/**
 * Demonstrates AST nodes manipulation by creating the following java class:
 *
 * public class SampleComposite extends Composite {
 * 	   private class ControlAdapterImpl extends ControlAdapter {
 * 	      private Point minimumSize;
 * 	      ControlAdapterImpl(Point size) {
 *           this.minimumSize = size;
 *        }
 * 	      public void controlResized(ControlEvent e) {
 *		     Shell shell = (Shell) e.widget;
 * 		      Point size = shell.getSize();
 * 		      boolean change = false;
 * 		      if (size.x < this.minimumSize.x) {
 * 		 	      size.x = this.minimumSize.x;
 * 			      change = true;
 * 		      }
 * 		      if (size.y < this.minimumSize.y) {
 * 		 	      size.y = this.minimumSize.y;
 * 			      change = true;
 * 		      }
 * 		      if (change)
 * 			      shell.setSize(size);
 * 	      }
 *     }
 * 	   public SampleComposite(Composite parent, int style) {
 * 		   super(parent, style);
 * 		   GridLayout gridLayout = new GridLayout();
 * 		   setLayout(gridLayout);
 * 		   Label label = new Label(this, SWT.NONE);
 * 		   label.setText("Press the button to close:");
 * 		   label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
 * 		   Button button = new Button(this, SWT.PUSH);
 * 		   button.setText("OK");
 * 		   button.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
 * 		   button.addSelectionListener(new SelectionAdapter() {
 * 		      public void widgetSelected(SelectionEvent e) {
 * 				   Shell shell = ((Button) e.widget).getShell();
 * 				   shell.close();
 * 		      }
 * 		   });
 * 	   }
 * 	   public static void main(String[] args) {
 * 		   try {
 * 			   Display display = new Display();
 * 			   Shell shell = new Shell(display);
 * 			   shell.setText("Sample Composite");
 * 			   shell.setLayout(new FillLayout());
 * 			   SampleComposite sampleComposite = new SampleComposite(shell, SWT.NONE);
 * 			   shell.pack();
 * 			   shell.addControlListener(sampleComposite.new ControlAdapterImpl(shell.getSize()));
 * 			   shell.open();
 * 			   while (!shell.isDisposed()) {
 * 			      if (!display.readAndDispatch())
 * 				     display.sleep();
 * 			   }
 * 			   display.dispose();
 * 	 	   } catch (Exception e) {
 * 			   e.printStackTrace();
 * 		   }
 * 	   }
 * }
 * 
 * You should run the main method inside the Eclipse IDE. 
 * Do not forget to add the jvm option java.library.path specifing
 * the SWT native library. On a Windows system it may look like this:
 * -Djava.library.path=C:\eclipse\plugins\org.eclipse.swt.win32_3.0.2\os\win32\x86  
 * @author Manoel Marques
 */

import java.io.*;
import java.util.*;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.compiler.CharOperation;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.internal.compiler.ClassFile;
import org.eclipse.jdt.internal.compiler.CompilationResult;
import org.eclipse.jdt.internal.compiler.Compiler;
import org.eclipse.jdt.internal.compiler.DefaultErrorHandlingPolicies;
import org.eclipse.jdt.internal.compiler.ICompilerRequestor;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileReader;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFormatException;
import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;
import org.eclipse.jdt.internal.compiler.env.INameEnvironment;
import org.eclipse.jdt.internal.compiler.env.NameEnvironmentAnswer;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;
import org.eclipse.jdt.internal.compiler.problem.DefaultProblemFactory;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.text.edits.TextEdit;
import java.lang.reflect.Method;

public class ASTMain {
	
	/**
	 * ICompilationUnit implementation
	 */
	static private class CompilationUnitImpl implements ICompilationUnit {

		private CompilationUnit unit;
		
		CompilationUnitImpl(CompilationUnit unit) {
			this.unit = unit;
		}

		public char[] getContents() {
			char[] contents = null;
			try {
				Document doc = new Document();
			 	TextEdit edits = unit.rewrite(doc,null);
		  		edits.apply(doc);
			 	String sourceCode = doc.get();
			 	if (sourceCode != null) 
			 		contents = sourceCode.toCharArray(); 
			}
			catch (BadLocationException e) {
				throw new RuntimeException(e);
			}
			return contents;
		}

		public char[] getMainTypeName() {
			TypeDeclaration classType = (TypeDeclaration) unit.types().get(0); 
			return classType.getName().getFullyQualifiedName().toCharArray(); 
		}

		public char[][] getPackageName() {
			String[] names = getSimpleNames(this.unit.getPackage().getName().getFullyQualifiedName()); 
			char[][] packages = new char[names.length][];
			for (int i=0;i < names.length; ++i)
				packages[i] = names[i].toCharArray();
			
			return packages;
		}

		public char[] getFileName() {
			TypeDeclaration classType = (TypeDeclaration) unit.types().get(0); 
			String name = classType.getName().getFullyQualifiedName() + ".java";
			return name.toCharArray();  
		}

		public boolean ignoreOptionalProblems() {
			// TODO Auto-generated method stub
			return false;
		}

	}
	
	/**
	 * ICompilerRequestor implementation
	 */ 
	static private class CompileRequestorImpl implements ICompilerRequestor {

		private List problems;
		private List classes;	
		
		public CompileRequestorImpl() {
			this.problems = new ArrayList();
			this.classes = new ArrayList();
		}

		public void acceptResult(CompilationResult result) {
			boolean errors = false;
			if (result.hasProblems()) {
				IProblem[] problems = result.getProblems();
				for (int i = 0; i < problems.length; i++) {
					if (problems[i].isError()) 
						errors = true;
					
					this.problems.add(problems[i]);
				}
			}
			if (!errors) {
				ClassFile[] classFiles = result.getClassFiles();
				for (int i = 0; i < classFiles.length; i++) 
					this.classes.add(classFiles[i]);
			}
		}
		
		List getProblems() {
			return this.problems; 
		}
		List getResults() {
			return this.classes; 
		}
	}
	
	/**
	 * INameEnvironment implementation
	 */
	static private class NameEnvironmentImpl implements INameEnvironment {

		private ICompilationUnit unit;
		private String fullName;
		
		NameEnvironmentImpl(ICompilationUnit unit) {
			this.unit = unit; 
			this.fullName = CharOperation.toString(this.unit.getPackageName()) + "." +
							new String(this.unit.getMainTypeName());
		}
		
		public NameEnvironmentAnswer findType(char[][] compoundTypeName) {
			return findType(CharOperation.toString(compoundTypeName));
		}

		public NameEnvironmentAnswer findType(char[] typeName, char[][] packageName) {
			String fullName = CharOperation.toString(packageName);
			if (typeName != null) {
				if (fullName.length() > 0)
					fullName += ".";
				
				fullName += new String(typeName); 
			}
			return findType(fullName);
		}

		public boolean isPackage(char[][] parentPackageName, char[] packageName) {
			String fullName = CharOperation.toString(parentPackageName);
			if (packageName != null) {
				if (fullName.length() > 0)
					fullName += ".";
				
				fullName += new String(packageName); 
			}
			if (findType(fullName) != null)
				return false;		
					
			try{
				return (getClass().getClassLoader().loadClass(fullName) == null);
			}
			catch(ClassNotFoundException e) {
				return true;
			}
		}

		public void cleanup() {
		}
		
		private NameEnvironmentAnswer findType(String fullName) {
			if (this.fullName.equals(fullName))
				return new NameEnvironmentAnswer(unit, null);
			
			try {
				InputStream is = getClass().getClassLoader().getResourceAsStream(fullName.replace('.', '/') + ".class");
				if (is != null) { 
					byte[] buffer = new byte[8192];
					int bytes = 0;
					ByteArrayOutputStream os = new ByteArrayOutputStream(buffer.length);
					while ((bytes = is.read(buffer, 0, buffer.length)) > 0) 
						os.write(buffer, 0, bytes);
					
					os.flush();
					ClassFileReader classFileReader = new ClassFileReader(os.toByteArray(),fullName.toCharArray(),true);
					return new NameEnvironmentAnswer(classFileReader, null);
				}
				return null;
			}
			catch (IOException e) {
				throw new RuntimeException(e);
			}
			catch (ClassFormatException e) {
				throw new RuntimeException(e);
			}		
		}
	}
	
	/**
	 * ClassLoader implementation
	 */
	static private class CustomClassLoader extends ClassLoader {
		
		private Map classMap;
		
		CustomClassLoader(ClassLoader parent,List classesList) {
			this.classMap = new HashMap();
			for (int i = 0; i < classesList.size(); i++) {
				ClassFile classFile = (ClassFile)classesList.get(i);
				String className = CharOperation.toString(classFile.getCompoundName());
				this.classMap.put(className,classFile.getBytes());
			}
		}
		public Class findClass(String name) throws ClassNotFoundException {
			byte[] bytes = (byte[]) this.classMap.get(name);
			if (bytes != null)
				return defineClass(name, bytes, 0, bytes.length);
			
			return super.findClass(name);
        }
	};
	
	static private final String[] IMPORTS = {
		"org.eclipse.swt.SWT",
		"org.eclipse.swt.events.*",
		"org.eclipse.swt.graphics.*",
		"org.eclipse.swt.layout.*",
		"org.eclipse.swt.widgets.*"
	};	
	
	private ICompilationUnit generateCompilationUnit() {
		ASTParser parser = ASTParser.newParser(AST.JLS2);
		parser.setSource("".toCharArray());

		CompilationUnit unit = (CompilationUnit) parser.createAST(null); 
		unit.recordModifications();
		
		AST ast = unit.getAST(); 
		
		// Package statement
		// package astexplorer;
		
		PackageDeclaration packageDeclaration = ast.newPackageDeclaration();
		unit.setPackage(packageDeclaration);
		packageDeclaration.setName(ast.newQualifiedName(ast.newName("com.freetmp.investigate"), ast.newSimpleName("ast")));
		
		// imports statements
		// import org.eclipse.swt.SWT;
		// import org.eclipse.swt.events.*;
		// import org.eclipse.swt.graphics.*;
		// import org.eclipse.swt.layout.*;
		// import org.eclipse.swt.widgets.*;
		
		for (int i = 0; i < IMPORTS.length; ++i) {
			ImportDeclaration importDeclaration = ast.newImportDeclaration();
			importDeclaration.setName(ast.newName(getSimpleNames(IMPORTS[i])));
			if (IMPORTS[i].indexOf("*") > 0)
				importDeclaration.setOnDemand(true);
			else
				importDeclaration.setOnDemand(false);
			
			unit.imports().add(importDeclaration);
		}
		
		// class declaration
		// public class SampleComposite extends Composite {
		
		TypeDeclaration classType = ast.newTypeDeclaration();
		classType.setInterface(false);
		classType.setModifiers(Modifier.PUBLIC);
		classType.setName(ast.newSimpleName("SampleComposite"));
		classType.setSuperclass(ast.newSimpleName("Composite"));
		unit.types().add(classType);
		
		// comments
		
/*		Javadoc jc = ast.newJavadoc();
		TagElement tag = ast.newTagElement();
		TextElement te = ast.newTextElement();
		tag.fragments().add(te);
		te.setText("Sample SWT Composite class created using the ASTParser");
		jc.tags().add(tag);
		tag = ast.newTagElement();
		tag.setTagName(TagElement.TAG_AUTHOR);
		tag.fragments().add(ast.newSimpleName("Manoel Marques"));
		jc.tags().add(tag);
		classType.setJavadoc(jc);*/
		
		// private class ControlAdapterImpl extends ControlAdapter {
		
		TypeDeclaration innerClassType = ast.newTypeDeclaration(); 
		innerClassType.setInterface(false);
		innerClassType.setModifiers(Modifier.PRIVATE);
		innerClassType.setName(ast.newSimpleName("ControlAdapterImpl"));
		innerClassType.setSuperclass(ast.newSimpleName("ControlAdapter"));
		classType.bodyDeclarations().add(innerClassType);
		populateInnerClass(innerClassType);
		
		// constructor declaration
		// public SampleComposite(Composite parent,int style) {
		
		MethodDeclaration methodConstructor = ast.newMethodDeclaration();
		methodConstructor.setConstructor(true);
		methodConstructor.setModifiers(Modifier.PUBLIC);
		methodConstructor.setName(ast.newSimpleName("SampleComposite"));
		classType.bodyDeclarations().add(methodConstructor);
		
		// constructor parameters
		
		SingleVariableDeclaration variableDeclaration = ast.newSingleVariableDeclaration();
		variableDeclaration.setModifiers(Modifier.NONE);
		variableDeclaration.setType(ast.newSimpleType(ast.newSimpleName("Composite")));
		variableDeclaration.setName(ast.newSimpleName("parent"));
		methodConstructor.parameters().add(variableDeclaration);
		
		variableDeclaration = ast.newSingleVariableDeclaration();
		variableDeclaration.setModifiers(Modifier.NONE);
		variableDeclaration.setType(ast.newPrimitiveType(PrimitiveType.INT));
		variableDeclaration.setName(ast.newSimpleName("style"));
		methodConstructor.parameters().add(variableDeclaration);
		
		Block constructorBlock = ast.newBlock();
		methodConstructor.setBody(constructorBlock);
		
		// super invocation
		// super(parent,style);
		
		SuperConstructorInvocation superConstructorInvocation = ast.newSuperConstructorInvocation();
		constructorBlock.statements().add(superConstructorInvocation);
		Expression exp = ast.newSimpleName("parent");
		superConstructorInvocation.arguments().add(exp); 
		superConstructorInvocation.arguments().add(ast.newSimpleName("style")); 
		
		// GridLayout gridLayout = new GridLayout(); 
	
		VariableDeclarationFragment vdf = ast.newVariableDeclarationFragment();
		vdf.setName(ast.newSimpleName("gridLayout"));
		VariableDeclarationStatement vds = ast.newVariableDeclarationStatement(vdf);
		vds.setType(ast.newSimpleType(ast.newSimpleName("GridLayout")));
		ClassInstanceCreation cc = ast.newClassInstanceCreation();
		cc.setName(ast.newSimpleName("GridLayout"));	
		vdf.setInitializer(cc);
		constructorBlock.statements().add(vds); 
		
		// setLayout(gridLayout);
		
		MethodInvocation mi = ast.newMethodInvocation();
		mi.setName(ast.newSimpleName("setLayout"));
		mi.arguments().add(ast.newSimpleName("gridLayout")); 
		constructorBlock.statements().add(ast.newExpressionStatement(mi)); 
		
		// Label label = new Label(this,SWT.NONE);
		
		vdf = ast.newVariableDeclarationFragment();
		vdf.setName(ast.newSimpleName("label"));
		vds = ast.newVariableDeclarationStatement(vdf);
		vds.setType(ast.newSimpleType(ast.newSimpleName("Label")));
		constructorBlock.statements().add(vds); 
		
		cc = ast.newClassInstanceCreation();
		cc.setName(ast.newSimpleName("Label"));	
		vdf.setInitializer(cc);
		cc.arguments().add(ast.newThisExpression()); 
		cc.arguments().add(ast.newName(getSimpleNames("SWT.NONE")));
		
		// label.setText("Press the button to close:");
		
		mi = ast.newMethodInvocation();
		mi.setExpression(ast.newSimpleName("label"));
		mi.setName(ast.newSimpleName("setText"));
		StringLiteral sl = ast.newStringLiteral();
		sl.setLiteralValue("Press the button to close:");
		mi.arguments().add(sl); 
		constructorBlock.statements().add(ast.newExpressionStatement(mi));
		
		// label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
		
		mi = ast.newMethodInvocation();
		mi.setExpression(ast.newSimpleName("label"));
		mi.setName(ast.newSimpleName("setLayoutData"));
		
		cc = ast.newClassInstanceCreation();
		cc.setName(ast.newSimpleName("GridData"));	
		cc.arguments().add(ast.newName(getSimpleNames("GridData.HORIZONTAL_ALIGN_CENTER")));
		mi.arguments().add(cc); 
		constructorBlock.statements().add(ast.newExpressionStatement(mi));
		
		// Button button = new Button(this,SWT.PUSH);
		
		vdf = ast.newVariableDeclarationFragment();
		vdf.setName(ast.newSimpleName("button"));
		vds = ast.newVariableDeclarationStatement(vdf);
		vds.setType(ast.newSimpleType(ast.newSimpleName("Button")));
		constructorBlock.statements().add(vds); 
				
		cc = ast.newClassInstanceCreation();
		cc.setName(ast.newSimpleName("Button"));	
		vdf.setInitializer(cc);
		cc.arguments().add(ast.newThisExpression()); 
		cc.arguments().add(ast.newName(getSimpleNames("SWT.PUSH")));
		
		// button.setText("OK");
		
		mi = ast.newMethodInvocation();
		mi.setExpression(ast.newSimpleName("button"));
		mi.setName(ast.newSimpleName("setText"));
		sl = ast.newStringLiteral();
		sl.setLiteralValue("OK");
		mi.arguments().add(sl); 
		constructorBlock.statements().add(ast.newExpressionStatement(mi));
		
		// button.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
		
		mi = ast.newMethodInvocation();
		mi.setExpression(ast.newSimpleName("button"));
		mi.setName(ast.newSimpleName("setLayoutData"));
		
		cc = ast.newClassInstanceCreation();
		cc.setName(ast.newSimpleName("GridData"));	
		cc.arguments().add(ast.newName(getSimpleNames("GridData.HORIZONTAL_ALIGN_CENTER")));
		mi.arguments().add(cc); 
		constructorBlock.statements().add(ast.newExpressionStatement(mi));
		
		// button.addSelectionListener(new SelectionAdapter() {});
			
		mi = ast.newMethodInvocation();
		constructorBlock.statements().add(ast.newExpressionStatement(mi));
		mi.setExpression(ast.newSimpleName("button"));
		mi.setName(ast.newSimpleName("addSelectionListener"));
		
		ClassInstanceCreation ci = ast.newClassInstanceCreation();
		ci.setName(ast.newSimpleName("SelectionAdapter"));
		mi.arguments().add(ci);
		AnonymousClassDeclaration cd = ast.newAnonymousClassDeclaration();
		ci.setAnonymousClassDeclaration(cd);
		
		//	public void widgetSelected(SelectionEvent e) {
		
		MethodDeclaration md = ast.newMethodDeclaration();
		md.setConstructor(false);
		md.setModifiers(Modifier.PUBLIC);
		md.setName(ast.newSimpleName("widgetSelected"));
		cd.bodyDeclarations().add(md);
		
		variableDeclaration = ast.newSingleVariableDeclaration();
		variableDeclaration.setModifiers(Modifier.NONE);
		variableDeclaration.setType(ast.newSimpleType(ast.newSimpleName("SelectionEvent")));
		variableDeclaration.setName(ast.newSimpleName("e"));
		md.parameters().add(variableDeclaration);
		Block methodBlock = ast.newBlock();
		md.setBody(methodBlock);
		
		// Shell shell = ((Button)e.widget).getShell(); 
		
		vdf = ast.newVariableDeclarationFragment();
		vdf.setName(ast.newSimpleName("shell"));
		vds = ast.newVariableDeclarationStatement(vdf); 
		methodBlock.statements().add(vds); 
		vds.setType(ast.newSimpleType(ast.newSimpleName("Shell")));
		
		CastExpression ce = ast.newCastExpression();
		ce.setType(ast.newSimpleType(ast.newSimpleName("Button")));
		FieldAccess fa = ast.newFieldAccess();
		ce.setExpression(fa);
		fa.setExpression(ast.newSimpleName("e"));
		fa.setName(ast.newSimpleName("widget"));	
		
		ParenthesizedExpression pe = ast.newParenthesizedExpression(); 
		pe.setExpression(ce);
		mi = ast.newMethodInvocation();
		mi.setExpression(pe);
		mi.setName(ast.newSimpleName("getShell"));
		vdf.setInitializer(mi);
			
		// shell.close(); 
		
		mi = ast.newMethodInvocation();
		mi.setExpression(ast.newSimpleName("shell"));
		mi.setName(ast.newSimpleName("close"));
		methodBlock.statements().add(ast.newExpressionStatement(mi)); 
		
		// public static void main(String[] args) {
		
		md = ast.newMethodDeclaration();
		classType.bodyDeclarations().add(md);
		md.setModifiers(Modifier.PUBLIC | Modifier.STATIC);
		md.setName(ast.newSimpleName("main"));
		md.setReturnType(ast.newPrimitiveType(PrimitiveType.VOID));
		variableDeclaration = ast.newSingleVariableDeclaration();
		variableDeclaration.setModifiers(Modifier.NONE);
		variableDeclaration.setType(ast.newArrayType(ast.newSimpleType(ast.newSimpleName("String"))));
		variableDeclaration.setName(ast.newSimpleName("args"));
		md.parameters().add(variableDeclaration);
		methodBlock = ast.newBlock();
		md.setBody(methodBlock);
		
		// try 
		
		TryStatement tryStatement = ast.newTryStatement();
		methodBlock.statements().add(tryStatement);
		Block tryBlock = ast.newBlock();
		tryStatement.setBody(tryBlock);
		
		// Display display = new Display();
			
		vdf = ast.newVariableDeclarationFragment();
		vdf.setName(ast.newSimpleName("display"));
		vds = ast.newVariableDeclarationStatement(vdf); 
		tryBlock.statements().add(vds); 
		vds.setType(ast.newSimpleType(ast.newSimpleName("Display")));
				
		cc = ast.newClassInstanceCreation();
		cc.setName(ast.newSimpleName("Display"));	
		vdf.setInitializer(cc);
		
		// Shell shell = new Shell(display);
		
		vdf = ast.newVariableDeclarationFragment();
		vdf.setName(ast.newSimpleName("shell"));
		vds = ast.newVariableDeclarationStatement(vdf); 
		tryBlock.statements().add(vds);
		vds.setType(ast.newSimpleType(ast.newSimpleName("Shell")));
			
		cc = ast.newClassInstanceCreation();
		cc.setName(ast.newSimpleName("Shell"));
		cc.arguments().add(ast.newSimpleName("display"));
		vdf.setInitializer(cc);
				
		// shell.setText("Sample Composite");
		
		mi = ast.newMethodInvocation();
		mi.setExpression(ast.newSimpleName("shell"));
		mi.setName(ast.newSimpleName("setText"));
		sl = ast.newStringLiteral();
		sl.setLiteralValue("Sample Composite");
		mi.arguments().add(sl); 
		tryBlock.statements().add(ast.newExpressionStatement(mi)); 
		
		// shell.setLayout(new FillLayout());
		
		mi = ast.newMethodInvocation();
		mi.setExpression(ast.newSimpleName("shell"));
		mi.setName(ast.newSimpleName("setLayout"));
		cc = ast.newClassInstanceCreation();
		cc.setName(ast.newSimpleName("FillLayout"));
		mi.arguments().add(cc); 
		tryBlock.statements().add(ast.newExpressionStatement(mi)); 
					
		// SampleComposite sampleComposite = new SampleComposite(shell,SWT.NONE);
			
		vdf = ast.newVariableDeclarationFragment();
		vdf.setName(ast.newSimpleName("sampleComposite"));
		vds = ast.newVariableDeclarationStatement(vdf); 
		tryBlock.statements().add(vds);
		vds.setType(ast.newSimpleType(ast.newSimpleName("SampleComposite")));
				
		cc = ast.newClassInstanceCreation();
		cc.setName(ast.newSimpleName("SampleComposite"));
		cc.arguments().add(ast.newSimpleName("shell"));
		cc.arguments().add(ast.newName(getSimpleNames("SWT.NONE")));
		vdf.setInitializer(cc);
		
		// shell.pack(); 
		
		mi = ast.newMethodInvocation();
		mi.setExpression(ast.newSimpleName("shell"));
		mi.setName(ast.newSimpleName("pack"));
		tryBlock.statements().add(ast.newExpressionStatement(mi));
				
		// shell.addControlListener(sampleComposite.new ControlAdapterImpl(shell.getSize()));
		
		mi = ast.newMethodInvocation();
		tryBlock.statements().add(ast.newExpressionStatement(mi));
		mi.setExpression(ast.newSimpleName("shell"));
		mi.setName(ast.newSimpleName("addControlListener"));
		ci = ast.newClassInstanceCreation();
		mi.arguments().add(ci); 
		ci.setName(ast.newSimpleName("ControlAdapterImpl"));
		ci.setExpression(ast.newSimpleName("sampleComposite"));
		mi = ast.newMethodInvocation();
		mi.setExpression(ast.newSimpleName("shell"));
		mi.setName(ast.newSimpleName("getSize"));
		ci.arguments().add(mi); 
				
		// shell.open();
		
		mi = ast.newMethodInvocation();
		mi.setExpression(ast.newSimpleName("shell"));
		mi.setName(ast.newSimpleName("open"));
		tryBlock.statements().add(ast.newExpressionStatement(mi));
		
		// while (!shell.isDisposed()) {
		
		WhileStatement ws = ast.newWhileStatement();
		tryBlock.statements().add(ws);
		Block whileBlock = ast.newBlock();
		ws.setBody(whileBlock);
		
		PrefixExpression prefix = ast.newPrefixExpression();
		ws.setExpression(prefix);
		prefix.setOperator(PrefixExpression.Operator.NOT);
		mi = ast.newMethodInvocation();
		prefix.setOperand(mi);
		mi.setExpression(ast.newSimpleName("shell"));
		mi.setName(ast.newSimpleName("isDisposed"));
				
		// if (!display.readAndDispatch())
		
		IfStatement ifs = ast.newIfStatement();
		whileBlock.statements().add(ifs); 
		prefix = ast.newPrefixExpression();
		prefix.setOperator(PrefixExpression.Operator.NOT);
		mi = ast.newMethodInvocation();
		prefix.setOperand(mi);
		ifs.setExpression(prefix);
		mi.setExpression(ast.newSimpleName("display"));
		mi.setName(ast.newSimpleName("readAndDispatch"));
			
		// display.sleep();
		
		mi = ast.newMethodInvocation();
		mi.setExpression(ast.newSimpleName("display"));
		mi.setName(ast.newSimpleName("sleep"));
		ifs.setThenStatement(ast.newExpressionStatement(mi));
		
		// display.dispose();
		
		mi = ast.newMethodInvocation();
		mi.setExpression(ast.newSimpleName("display"));
		mi.setName(ast.newSimpleName("dispose"));
		tryBlock.statements().add(ast.newExpressionStatement(mi));
				
		// catch (Exception e) {
		CatchClause catchClause = ast.newCatchClause();
		tryStatement.catchClauses().add(catchClause);
		variableDeclaration = ast.newSingleVariableDeclaration();
		variableDeclaration.setModifiers(Modifier.NONE);
		variableDeclaration.setType(ast.newSimpleType(ast.newSimpleName(("Exception"))));
		variableDeclaration.setName(ast.newSimpleName("e"));
		catchClause.setException(variableDeclaration);
		Block catchBlock = ast.newBlock();
		catchClause.setBody(catchBlock);
		
		// e.printStackTrace(); 
		
		mi = ast.newMethodInvocation();
		mi.setExpression(ast.newSimpleName("e"));
		mi.setName(ast.newSimpleName("printStackTrace"));
		catchBlock.statements().add(ast.newExpressionStatement(mi)); 	
		
		return new CompilationUnitImpl(unit);
	}
	
	static private void populateInnerClass(TypeDeclaration classType) {
		
		AST ast = classType.getAST(); 
		
		// private Point minimumSize;
		
		VariableDeclarationFragment vdf = ast.newVariableDeclarationFragment();
		vdf.setName(ast.newSimpleName("minimumSize"));
		FieldDeclaration fd = ast.newFieldDeclaration(vdf);
		fd.setModifiers(Modifier.PRIVATE);
		fd.setType(ast.newSimpleType(ast.newSimpleName("Point")));
		classType.bodyDeclarations().add(fd); 
		
		// constructor declaration
		// ControlAdapterImpl(Point size) {
		
		MethodDeclaration methodConstructor = ast.newMethodDeclaration();
		methodConstructor.setConstructor(true);
		methodConstructor.setName(ast.newSimpleName("ControlAdapterImpl"));
		classType.bodyDeclarations().add(methodConstructor);
		
		// constructor parameter
		
		SingleVariableDeclaration variableDeclaration = ast.newSingleVariableDeclaration();
		variableDeclaration.setModifiers(Modifier.NONE);
		variableDeclaration.setType(ast.newSimpleType(ast.newSimpleName("Point")));
		variableDeclaration.setName(ast.newSimpleName("size"));
		methodConstructor.parameters().add(variableDeclaration);
			
		Block constructorBlock = ast.newBlock();
		methodConstructor.setBody(constructorBlock);
		
		//	this.minimumSize = size;
		
		Assignment a = ast.newAssignment();
		a.setOperator(Assignment.Operator.ASSIGN);
		constructorBlock.statements().add(ast.newExpressionStatement(a)); 
		
		FieldAccess fa = ast.newFieldAccess();
		fa.setExpression(ast.newThisExpression());
		fa.setName(ast.newSimpleName("minimumSize"));
		a.setLeftHandSide(fa);
		a.setRightHandSide(ast.newSimpleName("size"));
		
		// public void controlResized(ControlEvent e) {
		
		MethodDeclaration md = ast.newMethodDeclaration();
		md.setConstructor(false);
		md.setModifiers(Modifier.PUBLIC);
		md.setName(ast.newSimpleName("controlResized"));
		classType.bodyDeclarations().add(md);
		
		variableDeclaration = ast.newSingleVariableDeclaration();
		variableDeclaration.setModifiers(Modifier.NONE);
		variableDeclaration.setType(ast.newSimpleType(ast.newSimpleName("ControlEvent")));
		variableDeclaration.setName(ast.newSimpleName("e"));
		md.parameters().add(variableDeclaration);
		Block methodBlock = ast.newBlock();
		md.setBody(methodBlock);
		
		// Shell shell = (Shell)e.widget;
		
		vdf = ast.newVariableDeclarationFragment();
		vdf.setName(ast.newSimpleName("shell"));
		VariableDeclarationStatement vds = ast.newVariableDeclarationStatement(vdf);
		vds.setType(ast.newSimpleType(ast.newSimpleName("Shell")));
		methodBlock.statements().add(vds); 
				
		CastExpression ce = ast.newCastExpression();
		ce.setType(ast.newSimpleType(ast.newSimpleName("Shell")));
		fa = ast.newFieldAccess();
		ce.setExpression(fa);
		fa.setExpression(ast.newSimpleName("e"));
		fa.setName(ast.newSimpleName("widget"));	
		vdf.setInitializer(ce);		
		
		// Point size = shell.getSize();
		
		vdf = ast.newVariableDeclarationFragment();
		vdf.setName(ast.newSimpleName("size"));
		vds = ast.newVariableDeclarationStatement(vdf);
		vds.setType(ast.newSimpleType(ast.newSimpleName("Point")));
		methodBlock.statements().add(vds); 
				
		MethodInvocation mi = ast.newMethodInvocation();
		mi.setExpression(ast.newSimpleName("shell"));
		mi.setName(ast.newSimpleName("getSize"));
		vdf.setInitializer(mi);			
		
		// boolean change = false; 
		
		vdf = ast.newVariableDeclarationFragment();
		vdf.setName(ast.newSimpleName("change"));
		vds = ast.newVariableDeclarationStatement(vdf);
		vds.setType(ast.newPrimitiveType(PrimitiveType.BOOLEAN));
		methodBlock.statements().add(vds);
		vdf.setInitializer(ast.newBooleanLiteral(false));
		
		// if (size.x < this.minimumSize.x) {
		
		IfStatement ifs = ast.newIfStatement();
		methodBlock.statements().add(ifs); 
		InfixExpression ie = ast.newInfixExpression(); 
		ie.setOperator(InfixExpression.Operator.LESS);
		ifs.setExpression(ie);
				
		fa = ast.newFieldAccess();
		ie.setLeftOperand(fa);
		fa.setExpression(ast.newSimpleName("size"));
		fa.setName(ast.newSimpleName("x"));
		
		FieldAccess minimumSizeFa = ast.newFieldAccess();
		fa = ast.newFieldAccess();
		fa.setExpression(ast.newThisExpression());
		fa.setName(ast.newSimpleName("minimumSize"));
		minimumSizeFa.setExpression(fa);
		minimumSizeFa.setName(ast.newSimpleName("x"));
		ie.setRightOperand(minimumSizeFa);	
				
		Block thenBlock = ast.newBlock();
		ifs.setThenStatement(thenBlock);
		
		// size.x = this.minimumSize.x;
		
		a = ast.newAssignment();
		a.setOperator(Assignment.Operator.ASSIGN);
		thenBlock.statements().add(ast.newExpressionStatement(a)); 
		fa = ast.newFieldAccess();
		fa.setExpression(ast.newSimpleName("size"));
		fa.setName(ast.newSimpleName("x"));
		a.setLeftHandSide(fa);
		minimumSizeFa = ast.newFieldAccess();
		fa = ast.newFieldAccess();
		fa.setExpression(ast.newThisExpression());
		fa.setName(ast.newSimpleName("minimumSize"));
		minimumSizeFa.setExpression(fa);
		minimumSizeFa.setName(ast.newSimpleName("x"));
		a.setRightHandSide(minimumSizeFa);	
		
		// change = true; 
		
		a = ast.newAssignment();
		a.setOperator(Assignment.Operator.ASSIGN);
		thenBlock.statements().add(ast.newExpressionStatement(a)); 
		a.setLeftHandSide(ast.newSimpleName("change"));
		a.setRightHandSide(ast.newBooleanLiteral(true));
		
		// if (size.y < this.minimumSize.y) {
		
		ifs = ast.newIfStatement();
		methodBlock.statements().add(ifs); 
		ie = ast.newInfixExpression(); 
		ie.setOperator(InfixExpression.Operator.LESS);
		ifs.setExpression(ie);
		
		fa = ast.newFieldAccess();
		ie.setLeftOperand(fa);
		fa.setExpression(ast.newSimpleName("size"));
		fa.setName(ast.newSimpleName("y"));
		
		minimumSizeFa = ast.newFieldAccess();
		fa = ast.newFieldAccess();
		fa.setExpression(ast.newThisExpression());
		fa.setName(ast.newSimpleName("minimumSize"));
		minimumSizeFa.setExpression(fa);
		minimumSizeFa.setName(ast.newSimpleName("y"));
		ie.setRightOperand(minimumSizeFa);			
		
		thenBlock = ast.newBlock();
		ifs.setThenStatement(thenBlock);
		
		// size.y = this.minimumSize.y;
		
		a = ast.newAssignment();
		a.setOperator(Assignment.Operator.ASSIGN);
		thenBlock.statements().add(ast.newExpressionStatement(a)); 
		fa = ast.newFieldAccess();
		fa.setExpression(ast.newSimpleName("size"));
		fa.setName(ast.newSimpleName("y"));
		a.setLeftHandSide(fa);
		minimumSizeFa = ast.newFieldAccess();
		fa = ast.newFieldAccess();
		fa.setExpression(ast.newThisExpression());
		fa.setName(ast.newSimpleName("minimumSize"));
		minimumSizeFa.setExpression(fa);
		minimumSizeFa.setName(ast.newSimpleName("y"));
		a.setRightHandSide(minimumSizeFa);	
		
		// change = true; 
		
		a = ast.newAssignment();
		a.setOperator(Assignment.Operator.ASSIGN);
		thenBlock.statements().add(ast.newExpressionStatement(a)); 
		a.setLeftHandSide(ast.newSimpleName("change"));
		a.setRightHandSide(ast.newBooleanLiteral(true));
		
		// if (change) 
		
		ifs = ast.newIfStatement();
		methodBlock.statements().add(ifs); 
		ifs.setExpression(ast.newSimpleName("change"));
				
		// shell.setSize(size);
		
		mi = ast.newMethodInvocation(); 
		mi.setExpression(ast.newSimpleName("shell"));
		mi.setName(ast.newSimpleName("setSize"));
		mi.arguments().add(ast.newSimpleName("size")); 
		ifs.setThenStatement(ast.newExpressionStatement(mi));
	}
	
	private void compileAndRun(ICompilationUnit unit) {
		
		Map settings = new HashMap();
		settings.put(CompilerOptions.OPTION_LineNumberAttribute,CompilerOptions.GENERATE);
		settings.put(CompilerOptions.OPTION_SourceFileAttribute,CompilerOptions.GENERATE);
		
		CompileRequestorImpl requestor = new CompileRequestorImpl();
		Compiler compiler = new Compiler(new NameEnvironmentImpl(unit),
										DefaultErrorHandlingPolicies.proceedWithAllProblems(),
										settings,
										requestor,
										new DefaultProblemFactory(Locale.getDefault()));
	
		compiler.compile(new ICompilationUnit[] { unit });
		
		List problems = requestor.getProblems();
		boolean error = false;
		for (Iterator it = problems.iterator(); it.hasNext();) {
			IProblem problem = (IProblem)it.next();
			StringBuffer buffer = new StringBuffer();
			buffer.append(problem.getMessage());
			buffer.append(" line: ");
			buffer.append(problem.getSourceLineNumber());
			String msg = buffer.toString(); 
			if(problem.isError()) {
				error = true; 
				msg = "Error:\n" + msg;
			}	
			else 
			if(problem.isWarning())
				msg = "Warning:\n" + msg;
			
			System.out.println(msg);  
		}	
		
		if (!error) {
			try {
				ClassLoader loader = new CustomClassLoader(getClass().getClassLoader(),requestor.getResults());
				String className = CharOperation.toString(unit.getPackageName()) + "." + new String(unit.getMainTypeName());
				Class clazz = loader.loadClass(className);
				Method m = clazz.getMethod("main",new Class[] {String[].class});
				m.invoke(clazz,new Object[] { new String[0] });
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	static private String[] getSimpleNames(String qualifiedName) {
		StringTokenizer st = new StringTokenizer(qualifiedName,".");
		ArrayList list = new ArrayList();
		while (st.hasMoreTokens()) {
			String name = st.nextToken().trim();
			if (!name.equals("*"))
				list.add(name);
		}
		return (String[]) list.toArray(new String[list.size()]);
	}
	
	public static void main(String[] args) {
		ASTMain astMain = new ASTMain();
		ICompilationUnit unit = astMain.generateCompilationUnit(); 
		String source = new String(unit.getContents()); 
		StringTokenizer st = new StringTokenizer(source,"\n");
		for (int i=1;st.hasMoreTokens();++i)
			System.out.print(i + " " + st.nextToken());
		
		astMain.compileAndRun(unit);
	}
}