//package com.freetmp.investigate.ast;
//
//import org.eclipse.jdt.core.dom.AST;
//import org.eclipse.jdt.core.dom.ASTParser;
//import org.eclipse.jdt.core.dom.BodyDeclaration;
//import org.eclipse.jdt.core.dom.CompilationUnit;
//import org.eclipse.jdt.core.dom.FieldDeclaration;
//import org.eclipse.jdt.core.dom.ImportDeclaration;
//import org.eclipse.jdt.core.dom.MethodDeclaration;
//import org.eclipse.jdt.core.dom.Modifier;
//import org.eclipse.jdt.core.dom.PackageDeclaration;
//import org.eclipse.jdt.core.dom.TypeDeclaration;
//import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
//
///**
// * AST语法树生成
// * @author pin
// */
//public class AstParser {
//
//	@SuppressWarnings("unchecked")
//	public static void main(String[] args) {
//		/*------------ 创建编译单元 ----------------------*/
//		ASTParser parser = ASTParser.newParser(AST.JLS3);
//		//使用空数组初始化解析器，如果不这样做，就会在访问编译单元时遇到异常
//		parser.setSource("".toCharArray());
//		//createAST()方法的IProgressMonitor可以在长时间的解析中提供反馈信息
//		CompilationUnit unit = (CompilationUnit) parser.createAST(null);
//		//启动对节点修改的监控，调用这个方法很重要，因为这样可以在以后通过检索节点的修改来访问源代码
//		unit.recordModifications();
//		//从编译单元中访问AST的所有者，并在后续的节点创建中使用它。AST树中的所有节点都属于同一个所有者。任何不是
//		//该所有者创建的节点都要先通过导入才能加入到树中。
//		AST ast = unit.getAST();
//
//		/*---------------- 创建Package ------------------*/
//		// package com.freetmp.investigate.ast;
//		PackageDeclaration packageDeclaration = ast.newPackageDeclaration();
//		unit.setPackage(packageDeclaration);
//		packageDeclaration.setName(ast.newQualifiedName(ast.newName("com.freetmp.investigate"), ast.newSimpleName("ast")));
//
//		/*----------------- 创建Import声明 ------------------*/
//		// import java.io.Serializable;
//		ImportDeclaration id = ast.newImportDeclaration();
//		id.setName(ast.newQualifiedName(ast.newName("java.io"), ast.newSimpleName("Serializable")));
//		id.setOnDemand(false);
//		unit.imports().add(id);
//
//		/*---------------- 创建类声明 -------------------*/
//		// public class GreetingAST implements Serializable {
//		TypeDeclaration classType = ast.newTypeDeclaration();
//		classType.setInterface(false);
//		classType.modifiers().add(Modifier.PUBLIC);
//		classType.setName(ast.newSimpleName("GreetingAST"));
//		//实现java.io.Serializable
//		classType.superInterfaceTypes().add(ast.newSimpleType(ast.newSimpleName("Serializable")));
//		unit.types().add(classType);
//
//		/*----------------- 创建BodyDeclariation ------*/
//		BodyDeclaration bd;
//
//		/*--------------- 创建VariableDeclaration --------*/
//		// private String greeting = "";
//		VariableDeclarationFragment vdf = ast.newVariableDeclarationFragment();
//		vdf.setName(ast.newSimpleName("greeting"));
//		vdf.setInitializer(ast.newStringLiteral());
//		/*--------------- 创建属性 ---------------------*/
//		FieldDeclaration fd = ast.newFieldDeclaration(vdf);
//		fd.modifiers().add(Modifier.PRIVATE);
//		fd.setType(ast.newSimpleType(ast.newSimpleName("String")));
//
//		/*---------------- 创建构造函数 --------------*/
//		MethodDeclaration md = ast.newMethodDeclaration();
//		md.setConstructor(true);
//
//	}
//}
