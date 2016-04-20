grammar DECAF;

IF: 'if';
WHILE: 'while';
ELSE: 'else';
CLASS: 'class';
STRUCT: 'struct';
VOID: 'void';
INT: 'int';
CHAR: 'char';
BOOL: 'boolean';
TRUE: 'true';
FALSE: 'false';
RETURN: 'return';


fragment LETTER: ('a'..'z'|'A'..'Z') ;
fragment DIGIT : '0'..'9' ;
fragment CARACTER : (' ' ..'~') | '\\' | '\'' | '\"' | '\t' | '\n' ;
ID : LETTER( LETTER | DIGIT)* ;
NUM: DIGIT(DIGIT)* ; 
CHR: '\'' CARACTER '\'';  
WS : [ \t\r\n]+ -> skip ; 
COMMENTS: '//' ~('\r' | '\n' )*  -> channel(HIDDEN);


program : CLASS ID '{' (declaration)* '}'  ;

declaration: structDeclaration 
			| varDeclaration 
			| methodDeclaration  ;

varDeclaration: varType ID ';' 
			  | varType ID '[' NUM ']' ';'  ;

structDeclaration : STRUCT ID '{' (varDeclaration)* '}'  ;

varType: INT 
		| CHAR 
		| BOOL 
		| STRUCT ID 
		| structDeclaration 
		| VOID;

methodDeclaration : methodType ID '(' (parameter (',' parameter)*)* ')' block  ;

methodType : INT 
		   | CHAR 
		   | BOOL 
		   | VOID;

parameter : parameterType ID | parameterType ID '[' ']' ;

parameterType: INT 
			 | CHAR 
			 | BOOL;

block : '{' (varDeclaration)* (statement)* '}' ;

statement : IF '(' expression ')' block ( statementELSE )?  #statementIF
           | WHILE '(' expression ')' block #statementWHILE
           | RETURN (expression)? ';' #statementRETURN
           | methodCall ';'  #statementMETHOD
           | block  #statementBLOCK
           | location '=' expression #statementLOC  
           | (expression)? ';' #statementEXPR ;  
        
        
statementELSE: ELSE block;

location: simpleLocation
		| structLocation ;

structLocation: simpleVariable ('.' location)
			| arrayVariable ('.' location) ;
	
simpleLocation: simpleVariable 
			| arrayVariable ;

simpleVariable: ID;			

arrayVariable: ID '[' expression ']' ;

// Expression 

expression : andExpr 
			| expression '||' andExpr  ;

andExpr: eqExpr 
		| andExpr '&&' eqExpr ;

eqExpr: relationExpr 
		| eqExpr eq_op relationExpr ; 

relationExpr: addExpr 
			| relationExpr rel_op addExpr ;

addExpr: multExpr 
		| addExpr add_op multExpr ;

multExpr: resExpr 
		| multExpr mult_op resExpr ;

resExpr: unaryExpr 
		| resExpr red_op unaryExpr;

unaryExpr:  '-' value | '!' value | value   ;

value: location 
	  | methodCall 
	  | literal 
	  | exp1;

exp1: '(' expression ')';
	
methodCall: ID '(' arg ')';

arg : (arg2 (',' arg2)* )? ; 

arg2: expression;

arrayCall :   '[' arg_comma ']' ;

arg_comma    :   arg_exp;

arg_exp    :   (arg2) (',' arg2)* ;

add_op: '+'| '-';
	
mult_op: '*' | '/';

red_op: '%';

rel_op: '<' | '>' | '<=' | '>=';

eq_op : '==' | '!=' ;

literal : int_literal | char_literal | bool_literal;

int_literal : NUM ;

char_literal : CHR ;

bool_literal : TRUE | FALSE ;