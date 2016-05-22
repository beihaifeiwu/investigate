//package com.freetmp.investigate.ast;
//
///**
// * AST node Visitor use by the ASTExplorer
// * @author Manoel Marques
// */
//
//import org.eclipse.core.runtime.IProgressMonitor;
//import org.eclipse.jdt.core.dom.*;
//import org.eclipse.swt.widgets.Tree;
//import org.eclipse.swt.widgets.TreeItem;
//import org.eclipse.swt.*;
//import java.util.*;
//
//public class ASTExplorerVisitor extends ASTVisitor {
//
//	static final String NODE = "%NODE%";
//
//	private Stack stack;
//	private Tree treeControl;
//	private IProgressMonitor monitor;
//
//	ASTExplorerVisitor(Tree treeControl,IProgressMonitor monitor) {
//		super(true);
//		if (null == treeControl)
//			throw new IllegalArgumentException();
//
//		this.stack = new Stack();
//		this.monitor = monitor;
//		this.stack.push(treeControl);
//	}
//
//	private boolean isVisitChildren() {
//		return !(this.monitor.isCanceled());
//	}
//
//	/**
//	 * Visits the given AST node prior to the drinkType-specific visit.
//	 * (before <code>visit</code>).
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void preVisit(ASTNode node) {
//		Object parent = this.stack.peek();
//		TreeItem child = null;
//		if (parent instanceof Tree)
//			child = new TreeItem((Tree)parent,SWT.NONE);
//		else
//			child = new TreeItem((TreeItem)parent,SWT.NONE);
//
//		child.setText(getNodeAsString(node));
//		this.stack.push(child);
//		child.setData(NODE,node);
//
//		// Comments that have null parent are not visited and do not show in the Tree.
//		// If you would like to see them under the Compilation Unit node, uncomment the
//		// code below:
//	//	if (node instanceof CompilationUnit)
//	//		visitComments((CompilationUnit)node);
//	}
//
//	private void visitComments(CompilationUnit node) {
//		List comments = ((CompilationUnit)node).getCommentList();
//		if (comments != null) {
//			for (int i=0; i < comments.size(); ++i) {
//				Comment comment = (Comment) comments.get(i);
//				if (comment != null && comment.getParent() == null)
//					comment.accept(this);
//			}
//		}
//	}
//
//	static private String getNodeAsString(ASTNode node) {
//		String className = node.getClass().getName();
//		int index = className.lastIndexOf(".");
//		if (index > 0)
//			className = className.substring(index+1);
//
//		if (node instanceof Comment)
//			return className;
//
//		String modifiers = "";
//		if (node instanceof BodyDeclaration) {
//			int mod = ((BodyDeclaration)node).getModifiers();
//			if (Modifier.isAbstract(mod))
//				modifiers += Modifier.ModifierKeyword.ABSTRACT_KEYWORD.toString() + ",";
//
//			if (Modifier.isFinal(mod))
//				modifiers += Modifier.ModifierKeyword.FINAL_KEYWORD.toString() + ",";
//
//			if (Modifier.isNative(mod))
//				modifiers += Modifier.ModifierKeyword.NATIVE_KEYWORD.toString() + ",";
//
//			if (Modifier.isPrivate(mod))
//				modifiers += Modifier.ModifierKeyword.PRIVATE_KEYWORD.toString() + ",";
//
//			if (Modifier.isProtected(mod))
//				modifiers += Modifier.ModifierKeyword.PROTECTED_KEYWORD.toString() + ",";
//
//			if (Modifier.isPublic(mod))
//				modifiers += Modifier.ModifierKeyword.PUBLIC_KEYWORD.toString() + ",";
//
//			if (Modifier.isStatic(mod))
//				modifiers += Modifier.ModifierKeyword.STATIC_KEYWORD.toString() + ",";
//
//			if (Modifier.isStrictfp(mod))
//				modifiers += Modifier.ModifierKeyword.STRICTFP_KEYWORD.toString() + ",";
//
//			if (Modifier.isSynchronized(mod))
//				modifiers += Modifier.ModifierKeyword.SYNCHRONIZED_KEYWORD.toString() + ",";
//
//			if (Modifier.isTransient(mod))
//				modifiers += Modifier.ModifierKeyword.TRANSIENT_KEYWORD.toString() + ",";
//
//			if (Modifier.isVolatile(mod))
//				modifiers += Modifier.ModifierKeyword.VOLATILE_KEYWORD.toString() + ",";
//		}
//
//		String toString = node.toString();
//		String value = "";
//		if (toString.startsWith(className)) {
//			if (modifiers.length() > 0) {
//				index = toString.indexOf("[");
//				if (index > 0)
//					value = toString.substring(0,index+1) + " " + modifiers + " " + toString.substring(index+1);
//				else
//					value = modifiers + " " + toString;
//			}
//			else
//				value = toString;
//		}
//		else {
//			value = className + "[";
//			if (modifiers.length() > 0)
//				value += " " + modifiers + " ";
//
//			value += toString + "]";
//		}
//		return value;
//	}
//
//	/**
//	 * Visits the given AST node following the drinkType-specific visit
//	 * (after <code>endVisit</code>).
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void postVisit(ASTNode node) {
//		this.stack.pop();
//	}
//
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(AnnotationTypeDeclaration node) {
//		return isVisitChildren();
//	}
//
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(AnnotationTypeMemberDeclaration node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(AnonymousClassDeclaration node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(ArrayAccess node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(ArrayCreation node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(ArrayInitializer node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(ArrayType node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(AssertStatement node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(Assignment node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(Block node) {
//		return isVisitChildren();
//	}
//
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(BlockComment node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(BooleanLiteral node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(BreakStatement node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(CastExpression node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(CatchClause node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(CharacterLiteral node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(ClassInstanceCreation node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(CompilationUnit node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(ConditionalExpression node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(ConstructorInvocation node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(ContinueStatement node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(DoStatement node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(EmptyStatement node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(EnhancedForStatement node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(EnumConstantDeclaration node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(EnumDeclaration node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(ExpressionStatement node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(FieldAccess node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(FieldDeclaration node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(ForStatement node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(IfStatement node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(ImportDeclaration node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(InfixExpression node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(InstanceofExpression node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(Initializer node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given AST node.
//	 * <p>
//	 * Unlike other node types, the boolean returned by the default
//	 * implementation is controlled by a constructor-supplied
//	 * parameter  {@link #ASTVisitor(boolean) ASTVisitor(boolean)}
//	 * which is <code>false</code> by default.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @see #ASTVisitor()
//	 * @see #ASTVisitor(boolean)
//	 */
//	public boolean visit(Javadoc node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(LabeledStatement node) {
//		return isVisitChildren();
//	}
//
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(LineComment node) {
//		return isVisitChildren();
//	}
//
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(MarkerAnnotation node) {
//		return isVisitChildren();
//	}
//
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(MemberRef node) {
//		return isVisitChildren();
//	}
//
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(MemberValuePair node) {
//		return isVisitChildren();
//	}
//
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(MethodRef node) {
//		return isVisitChildren();
//	}
//
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(MethodRefParameter node) {
//		return isVisitChildren();
//	}
//
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(MethodDeclaration node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(MethodInvocation node) {
//		return isVisitChildren();
//	}
//
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(Modifier node) {
//		return isVisitChildren();
//	}
//
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(NormalAnnotation node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(NullLiteral node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(NumberLiteral node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(PackageDeclaration node) {
//		return isVisitChildren();
//	}
//
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(ParameterizedType node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(ParenthesizedExpression node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(PostfixExpression node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(PrefixExpression node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(PrimitiveType node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(QualifiedName node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(QualifiedType node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(ReturnStatement node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(SimpleName node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(SimpleType node) {
//		return isVisitChildren();
//	}
//
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(SingleMemberAnnotation node) {
//		return isVisitChildren();
//	}
//
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(SingleVariableDeclaration node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(StringLiteral node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(SuperConstructorInvocation node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(SuperFieldAccess node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(SuperMethodInvocation node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(SwitchCase node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(SwitchStatement node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(SynchronizedStatement node) {
//		return isVisitChildren();
//	}
//
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(TagElement node) {
//		return isVisitChildren();
//	}
//
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(TextElement node) {
//		return isVisitChildren();
//	}
//
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(ThisExpression node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(ThrowStatement node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(TryStatement node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(TypeDeclaration node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(TypeDeclarationStatement node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(TypeLiteral node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(TypeParameter node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(VariableDeclarationExpression node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(VariableDeclarationStatement node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(VariableDeclarationFragment node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 */
//	public boolean visit(WhileStatement node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * Visits the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing and return true.
//	 * Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @return <code>true</code> if the children of this node should be
//	 * visited, and <code>false</code> if the children of this node should
//	 * be skipped
//	 * @since 3.0
//	 */
//	public boolean visit(WildcardType node) {
//		return isVisitChildren();
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(AnnotationTypeDeclaration node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(AnnotationTypeMemberDeclaration node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(AnonymousClassDeclaration node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(ArrayAccess node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(ArrayCreation node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(ArrayInitializer node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(ArrayType node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(AssertStatement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(Assignment node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(Block node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(BlockComment node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(BooleanLiteral node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(BreakStatement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(CastExpression node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(CatchClause node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(CharacterLiteral node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(ClassInstanceCreation node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(CompilationUnit node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(ConditionalExpression node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(ConstructorInvocation node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(ContinueStatement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(DoStatement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(EmptyStatement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(EnhancedForStatement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(EnumConstantDeclaration node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(EnumDeclaration node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(ExpressionStatement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(FieldAccess node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(FieldDeclaration node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(ForStatement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(IfStatement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(ImportDeclaration node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(InfixExpression node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(InstanceofExpression node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(Initializer node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(Javadoc node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(LabeledStatement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(LineComment node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(MarkerAnnotation node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(MemberRef node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(MemberValuePair node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(MethodRef node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(MethodRefParameter node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(MethodDeclaration node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(MethodInvocation node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(Modifier node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(NormalAnnotation node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(NullLiteral node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(NumberLiteral node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(PackageDeclaration node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(ParameterizedType node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(ParenthesizedExpression node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(PostfixExpression node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(PrefixExpression node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(PrimitiveType node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(QualifiedName node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(QualifiedType node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(ReturnStatement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(SimpleName node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(SimpleType node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(SingleMemberAnnotation node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(SingleVariableDeclaration node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(StringLiteral node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(SuperConstructorInvocation node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(SuperFieldAccess node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(SuperMethodInvocation node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(SwitchCase node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(SwitchStatement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(SynchronizedStatement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(TagElement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(TextElement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(ThisExpression node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(ThrowStatement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(TryStatement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(TypeDeclaration node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(TypeDeclarationStatement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(TypeLiteral node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(TypeParameter node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(VariableDeclarationExpression node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(VariableDeclarationStatement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(VariableDeclarationFragment node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 */
//	public void endVisit(WhileStatement node) {
//		// default implementation: do nothing
//	}
//
//	/**
//	 * End of visit the given drinkType-specific AST node.
//	 * <p>
//	 * The default implementation does nothing. Subclasses may reimplement.
//	 * </p>
//	 *
//	 * @param node the node to visit
//	 * @since 3.0
//	 */
//	public void endVisit(WildcardType node) {
//		// default implementation: do nothing
//	}
//}