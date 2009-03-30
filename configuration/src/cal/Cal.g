/*
 * Copyright (c) 2008, IETR/INSA of Rennes
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *   * Neither the name of the IETR/INSA of Rennes nor the names of its
 *     contributors may be used to endorse or promote products derived from this
 *     software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */

grammar Cal;
options {
  output = AST;
}
tokens {
  // expressions
  Expression;
  Integer;
  String;
  Operator;
  Var;
  
  // types
  Parameter;
  Type;
  TypeAttr;
  TypePar;
}

actor: oneImport* ACTOR ID
  (LBRACKET typePars? RBRACKET)?
  LPAREN parameters? RPAREN
  portDecls? DOUBLE_EQUAL_ARROW portDecls? COLON ignore* EOF;

ignore: ALL
| ACTOR
| IMPORT
| MULTI
| ARROW
| CARET
| COLON
| COLON_EQUAL
| COMMA
| DIV
| DOT
| DOUBLE_DASH_ARROW
| DOUBLE_EQUAL_ARROW
| DOUBLE_DOT
| DOUBLE_COLON
| EQ
| GE
| GT
| ID
| LBRACE
| LBRACKET
| LPAREN
| LE
| LT
| MINUS
| NUMBER
| PLUS
| RBRACE
| RBRACKET
| RPAREN
| SEMICOLON
| SHARP
| STRING
| TIMES ;

///////////////////////////////////////////////////////////////////////////////
// IMPORTS

oneImport: IMPORT importRest SEMICOLON ;

importRest: ALL qualifiedId
  | qualifiedId (EQ ID)? ;

qualifiedId: ID (DOT ID)+ ;

///////////////////////////////////////////////////////////////////////////////
// PARAMETERS

parameter: type? ID (EQ expression)? ;

parameters: parameter (COMMA parameter)* ;

///////////////////////////////////////////////////////////////////////////////
// PORT DECLARATIONS

portDecl: MULTI? type? ID ;

portDecls: portDecl (COMMA portDecl)* ;

///////////////////////////////////////////////////////////////////////////////
// TYPES

mainParameter: typeAndId EOF -> ^(Parameter typeAndId);

typeAndId: type? ID;

type: ID typeRest? -> ^(Type ID typeRest?);

typeRest: LBRACKET typePars? RBRACKET -> typePars?
  | LPAREN typeAttrs? RPAREN -> typeAttrs?;

typeAttrs: typeAttr (COMMA typeAttr)* -> typeAttr+;

typeAttr: ID COLON type -> ^(TypeAttr ID Type type)
| ID EQ expression -> ^(TypeAttr ID Expression expression);

typePars: typePar (COMMA typePar)* -> typePar+;

typePar: ID (LT type)? -> ^(TypePar ID type?);

///////////////////////////////////////////////////////////////////////////////
// EXPRESSIONS

mainExpression: expression EOF -> ^(Expression expression);

expression: factor (operator factor)*;

operator: (op=PLUS | op=MINUS | op=TIMES | op=DIV | op=CARET) -> ^(Operator $op);

factor: atom
  | LPAREN expression RPAREN -> ^(Expression expression);

atom: ID -> ^(Var ID)
| NUMBER -> ^(Integer NUMBER)
| STRING -> ^(String STRING);

///////////////////////////////////////////////////////////////////////////////
// TOKENS

ALL: 'all';
ACTOR: 'actor';
IMPORT: 'import';
MULTI: 'multi';

ID: ('a'..'z' | 'A'..'Z' | '_' | '$') ('a'..'z' | 'A'..'Z' | '_' | '$' | '0' .. '9')* ;
NUMBER: ('0'..'9')+ ;
STRING: '\"' .* '\"';

LINE_COMMENT: '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;};
MULTI_LINE_COMMENT: '/*' .* '*/' {$channel=HIDDEN;};
WHITESPACE: (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;};

EQ: '=';
GE: '>=';
GT: '>';
LE: '<=';
LT: '<';
NE: '!=';

ARROW: '->';
COLON: ':';
COLON_EQUAL: ':=';
COMMA: ',';
DOT: '.';
DOUBLE_DASH_ARROW: '-->';
DOUBLE_EQUAL_ARROW: '==>';
DOUBLE_DOT: '..';
DOUBLE_COLON: '::';

LBRACE: '{';
RBRACE: '}';
LBRACKET: '[';
RBRACKET: ']';
LPAREN: '(';
RPAREN: ')';

CARET: '^';
DIV: '/';
MINUS: '-';
PLUS: '+';
TIMES: '*';

SEMICOLON: ';';
SHARP: '#';
