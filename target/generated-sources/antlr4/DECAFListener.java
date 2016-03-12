// Generated from DECAF.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DECAFParser}.
 */
public interface DECAFListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DECAFParser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStructDeclaration(@NotNull DECAFParser.StructDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStructDeclaration(@NotNull DECAFParser.StructDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(@NotNull DECAFParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(@NotNull DECAFParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#mult_op}.
	 * @param ctx the parse tree
	 */
	void enterMult_op(@NotNull DECAFParser.Mult_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#mult_op}.
	 * @param ctx the parse tree
	 */
	void exitMult_op(@NotNull DECAFParser.Mult_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#add_op}.
	 * @param ctx the parse tree
	 */
	void enterAdd_op(@NotNull DECAFParser.Add_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#add_op}.
	 * @param ctx the parse tree
	 */
	void exitAdd_op(@NotNull DECAFParser.Add_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#red_op}.
	 * @param ctx the parse tree
	 */
	void enterRed_op(@NotNull DECAFParser.Red_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#red_op}.
	 * @param ctx the parse tree
	 */
	void exitRed_op(@NotNull DECAFParser.Red_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull DECAFParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull DECAFParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#bool_literal}.
	 * @param ctx the parse tree
	 */
	void enterBool_literal(@NotNull DECAFParser.Bool_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#bool_literal}.
	 * @param ctx the parse tree
	 */
	void exitBool_literal(@NotNull DECAFParser.Bool_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#rel_op}.
	 * @param ctx the parse tree
	 */
	void enterRel_op(@NotNull DECAFParser.Rel_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#rel_op}.
	 * @param ctx the parse tree
	 */
	void exitRel_op(@NotNull DECAFParser.Rel_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(@NotNull DECAFParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(@NotNull DECAFParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#eq_op}.
	 * @param ctx the parse tree
	 */
	void enterEq_op(@NotNull DECAFParser.Eq_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#eq_op}.
	 * @param ctx the parse tree
	 */
	void exitEq_op(@NotNull DECAFParser.Eq_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#arrayVariable}.
	 * @param ctx the parse tree
	 */
	void enterArrayVariable(@NotNull DECAFParser.ArrayVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#arrayVariable}.
	 * @param ctx the parse tree
	 */
	void exitArrayVariable(@NotNull DECAFParser.ArrayVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#int_literal}.
	 * @param ctx the parse tree
	 */
	void enterInt_literal(@NotNull DECAFParser.Int_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#int_literal}.
	 * @param ctx the parse tree
	 */
	void exitInt_literal(@NotNull DECAFParser.Int_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#varType}.
	 * @param ctx the parse tree
	 */
	void enterVarType(@NotNull DECAFParser.VarTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#varType}.
	 * @param ctx the parse tree
	 */
	void exitVarType(@NotNull DECAFParser.VarTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#methodType}.
	 * @param ctx the parse tree
	 */
	void enterMethodType(@NotNull DECAFParser.MethodTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#methodType}.
	 * @param ctx the parse tree
	 */
	void exitMethodType(@NotNull DECAFParser.MethodTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statementEXPR}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementEXPR(@NotNull DECAFParser.StatementEXPRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statementEXPR}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementEXPR(@NotNull DECAFParser.StatementEXPRContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(@NotNull DECAFParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(@NotNull DECAFParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statementBLOCK}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementBLOCK(@NotNull DECAFParser.StatementBLOCKContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statementBLOCK}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementBLOCK(@NotNull DECAFParser.StatementBLOCKContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(@NotNull DECAFParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(@NotNull DECAFParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(@NotNull DECAFParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(@NotNull DECAFParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(@NotNull DECAFParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(@NotNull DECAFParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#exp1}.
	 * @param ctx the parse tree
	 */
	void enterExp1(@NotNull DECAFParser.Exp1Context ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#exp1}.
	 * @param ctx the parse tree
	 */
	void exitExp1(@NotNull DECAFParser.Exp1Context ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull DECAFParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull DECAFParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statementIF}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementIF(@NotNull DECAFParser.StatementIFContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statementIF}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementIF(@NotNull DECAFParser.StatementIFContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#structLocation}.
	 * @param ctx the parse tree
	 */
	void enterStructLocation(@NotNull DECAFParser.StructLocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#structLocation}.
	 * @param ctx the parse tree
	 */
	void exitStructLocation(@NotNull DECAFParser.StructLocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#relationExpr}.
	 * @param ctx the parse tree
	 */
	void enterRelationExpr(@NotNull DECAFParser.RelationExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#relationExpr}.
	 * @param ctx the parse tree
	 */
	void exitRelationExpr(@NotNull DECAFParser.RelationExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#arrayCall}.
	 * @param ctx the parse tree
	 */
	void enterArrayCall(@NotNull DECAFParser.ArrayCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#arrayCall}.
	 * @param ctx the parse tree
	 */
	void exitArrayCall(@NotNull DECAFParser.ArrayCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#char_literal}.
	 * @param ctx the parse tree
	 */
	void enterChar_literal(@NotNull DECAFParser.Char_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#char_literal}.
	 * @param ctx the parse tree
	 */
	void exitChar_literal(@NotNull DECAFParser.Char_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#statementELSE}.
	 * @param ctx the parse tree
	 */
	void enterStatementELSE(@NotNull DECAFParser.StatementELSEContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#statementELSE}.
	 * @param ctx the parse tree
	 */
	void exitStatementELSE(@NotNull DECAFParser.StatementELSEContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#resExpr}.
	 * @param ctx the parse tree
	 */
	void enterResExpr(@NotNull DECAFParser.ResExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#resExpr}.
	 * @param ctx the parse tree
	 */
	void exitResExpr(@NotNull DECAFParser.ResExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#parameterType}.
	 * @param ctx the parse tree
	 */
	void enterParameterType(@NotNull DECAFParser.ParameterTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#parameterType}.
	 * @param ctx the parse tree
	 */
	void exitParameterType(@NotNull DECAFParser.ParameterTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull DECAFParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull DECAFParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#simpleVariable}.
	 * @param ctx the parse tree
	 */
	void enterSimpleVariable(@NotNull DECAFParser.SimpleVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#simpleVariable}.
	 * @param ctx the parse tree
	 */
	void exitSimpleVariable(@NotNull DECAFParser.SimpleVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#arg_exp}.
	 * @param ctx the parse tree
	 */
	void enterArg_exp(@NotNull DECAFParser.Arg_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#arg_exp}.
	 * @param ctx the parse tree
	 */
	void exitArg_exp(@NotNull DECAFParser.Arg_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(@NotNull DECAFParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(@NotNull DECAFParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#multExpr}.
	 * @param ctx the parse tree
	 */
	void enterMultExpr(@NotNull DECAFParser.MultExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#multExpr}.
	 * @param ctx the parse tree
	 */
	void exitMultExpr(@NotNull DECAFParser.MultExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(@NotNull DECAFParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(@NotNull DECAFParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statementLOC}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementLOC(@NotNull DECAFParser.StatementLOCContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statementLOC}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementLOC(@NotNull DECAFParser.StatementLOCContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statementMETHOD}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementMETHOD(@NotNull DECAFParser.StatementMETHODContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statementMETHOD}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementMETHOD(@NotNull DECAFParser.StatementMETHODContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#simpleLocation}.
	 * @param ctx the parse tree
	 */
	void enterSimpleLocation(@NotNull DECAFParser.SimpleLocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#simpleLocation}.
	 * @param ctx the parse tree
	 */
	void exitSimpleLocation(@NotNull DECAFParser.SimpleLocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#arg2}.
	 * @param ctx the parse tree
	 */
	void enterArg2(@NotNull DECAFParser.Arg2Context ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#arg2}.
	 * @param ctx the parse tree
	 */
	void exitArg2(@NotNull DECAFParser.Arg2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code statementWHILE}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementWHILE(@NotNull DECAFParser.StatementWHILEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statementWHILE}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementWHILE(@NotNull DECAFParser.StatementWHILEContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#eqExpr}.
	 * @param ctx the parse tree
	 */
	void enterEqExpr(@NotNull DECAFParser.EqExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#eqExpr}.
	 * @param ctx the parse tree
	 */
	void exitEqExpr(@NotNull DECAFParser.EqExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#addExpr}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(@NotNull DECAFParser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#addExpr}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(@NotNull DECAFParser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#arg_comma}.
	 * @param ctx the parse tree
	 */
	void enterArg_comma(@NotNull DECAFParser.Arg_commaContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#arg_comma}.
	 * @param ctx the parse tree
	 */
	void exitArg_comma(@NotNull DECAFParser.Arg_commaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statementRETURN}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementRETURN(@NotNull DECAFParser.StatementRETURNContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statementRETURN}
	 * labeled alternative in {@link DECAFParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementRETURN(@NotNull DECAFParser.StatementRETURNContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#location}.
	 * @param ctx the parse tree
	 */
	void enterLocation(@NotNull DECAFParser.LocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#location}.
	 * @param ctx the parse tree
	 */
	void exitLocation(@NotNull DECAFParser.LocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(@NotNull DECAFParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(@NotNull DECAFParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DECAFParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(@NotNull DECAFParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link DECAFParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(@NotNull DECAFParser.MethodCallContext ctx);
}