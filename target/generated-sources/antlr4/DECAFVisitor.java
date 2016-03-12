// Generated from DECAF.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DECAFParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DECAFVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DECAFParser#structDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDeclaration(@NotNull DECAFParser.StructDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDeclaration(@NotNull DECAFParser.MethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#mult_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult_op(@NotNull DECAFParser.Mult_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#add_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd_op(@NotNull DECAFParser.Add_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#red_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRed_op(@NotNull DECAFParser.Red_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(@NotNull DECAFParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#bool_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool_literal(@NotNull DECAFParser.Bool_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#rel_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRel_op(@NotNull DECAFParser.Rel_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(@NotNull DECAFParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#eq_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEq_op(@NotNull DECAFParser.Eq_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#arrayVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayVariable(@NotNull DECAFParser.ArrayVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#int_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt_literal(@NotNull DECAFParser.Int_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#varType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarType(@NotNull DECAFParser.VarTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#methodType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodType(@NotNull DECAFParser.MethodTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementEXPR}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementEXPR(@NotNull DECAFParser.StatementEXPRContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#unaryExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(@NotNull DECAFParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementBLOCK}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementBLOCK(@NotNull DECAFParser.StatementBLOCKContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(@NotNull DECAFParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(@NotNull DECAFParser.ArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(@NotNull DECAFParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#exp1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp1(@NotNull DECAFParser.Exp1Context ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(@NotNull DECAFParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementIF}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementIF(@NotNull DECAFParser.StatementIFContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#structLocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructLocation(@NotNull DECAFParser.StructLocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#relationExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationExpr(@NotNull DECAFParser.RelationExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#arrayCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayCall(@NotNull DECAFParser.ArrayCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#char_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChar_literal(@NotNull DECAFParser.Char_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#statementELSE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementELSE(@NotNull DECAFParser.StatementELSEContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#resExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResExpr(@NotNull DECAFParser.ResExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#parameterType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterType(@NotNull DECAFParser.ParameterTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(@NotNull DECAFParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#simpleVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleVariable(@NotNull DECAFParser.SimpleVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#arg_exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg_exp(@NotNull DECAFParser.Arg_expContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(@NotNull DECAFParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#multExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultExpr(@NotNull DECAFParser.MultExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(@NotNull DECAFParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementLOC}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementLOC(@NotNull DECAFParser.StatementLOCContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementMETHOD}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementMETHOD(@NotNull DECAFParser.StatementMETHODContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#simpleLocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleLocation(@NotNull DECAFParser.SimpleLocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#arg2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg2(@NotNull DECAFParser.Arg2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code statementWHILE}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementWHILE(@NotNull DECAFParser.StatementWHILEContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#eqExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqExpr(@NotNull DECAFParser.EqExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#addExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpr(@NotNull DECAFParser.AddExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#arg_comma}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg_comma(@NotNull DECAFParser.Arg_commaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementRETURN}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementRETURN(@NotNull DECAFParser.StatementRETURNContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#location}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocation(@NotNull DECAFParser.LocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#andExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(@NotNull DECAFParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DECAFParser#methodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCall(@NotNull DECAFParser.MethodCallContext ctx);
}