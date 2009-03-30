// $ANTLR 3.1.2 D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g 2009-03-30 13:59:22

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CalLexer extends Lexer {
    public static final int LT=38;
    public static final int Operator=7;
    public static final int LBRACE=36;
    public static final int MULTI=23;
    public static final int ID=14;
    public static final int EOF=-1;
    public static final int DOUBLE_DOT=31;
    public static final int LPAREN=17;
    public static final int LBRACKET=15;
    public static final int RPAREN=18;
    public static final int IMPORT=22;
    public static final int COLON_EQUAL=26;
    public static final int COMMA=27;
    public static final int CARET=25;
    public static final int ALL=21;
    public static final int TypeAttr=11;
    public static final int PLUS=41;
    public static final int String=6;
    public static final int RBRACKET=16;
    public static final int EQ=33;
    public static final int DOT=29;
    public static final int NE=50;
    public static final int DOUBLE_EQUAL_ARROW=19;
    public static final int DOUBLE_DASH_ARROW=30;
    public static final int GE=34;
    public static final int SHARP=44;
    public static final int Type=10;
    public static final int RBRACE=42;
    public static final int LINE_COMMENT=47;
    public static final int NUMBER=40;
    public static final int WHITESPACE=49;
    public static final int SEMICOLON=43;
    public static final int MINUS=39;
    public static final int Expression=4;
    public static final int MULTI_LINE_COMMENT=48;
    public static final int Parameter=9;
    public static final int TypePar=12;
    public static final int COLON=20;
    public static final int DOUBLE_COLON=32;
    public static final int ACTOR=13;
    public static final int GT=35;
    public static final int ARROW=24;
    public static final int DIV=28;
    public static final int TIMES=46;
    public static final int LE=37;
    public static final int Integer=5;
    public static final int Var=8;
    public static final int STRING=45;

    // delegates
    // delegators

    public CalLexer() {;} 
    public CalLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public CalLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g"; }

    // $ANTLR start "ALL"
    public final void mALL() throws RecognitionException {
        try {
            int _type = ALL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:153:4: ( 'all' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:153:6: 'all'
            {
            match("all"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ALL"

    // $ANTLR start "ACTOR"
    public final void mACTOR() throws RecognitionException {
        try {
            int _type = ACTOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:154:6: ( 'actor' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:154:8: 'actor'
            {
            match("actor"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ACTOR"

    // $ANTLR start "IMPORT"
    public final void mIMPORT() throws RecognitionException {
        try {
            int _type = IMPORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:155:7: ( 'import' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:155:9: 'import'
            {
            match("import"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IMPORT"

    // $ANTLR start "MULTI"
    public final void mMULTI() throws RecognitionException {
        try {
            int _type = MULTI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:156:6: ( 'multi' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:156:8: 'multi'
            {
            match("multi"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MULTI"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:158:3: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '$' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '$' | '0' .. '9' )* )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:158:5: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '$' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '$' | '0' .. '9' )*
            {
            if ( input.LA(1)=='$'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:158:39: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '$' | '0' .. '9' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='$'||(LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:
            	    {
            	    if ( input.LA(1)=='$'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:159:7: ( ( '0' .. '9' )+ )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:159:9: ( '0' .. '9' )+
            {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:159:9: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:159:10: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:160:7: ( '\\\"' ( . )* '\\\"' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:160:9: '\\\"' ( . )* '\\\"'
            {
            match('\"'); 
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:160:14: ( . )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='\"') ) {
                    alt3=2;
                }
                else if ( ((LA3_0>='\u0000' && LA3_0<='!')||(LA3_0>='#' && LA3_0<='\uFFFF')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:160:14: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "LINE_COMMENT"
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:162:13: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:162:15: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:162:20: (~ ( '\\n' | '\\r' ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='\u0000' && LA4_0<='\t')||(LA4_0>='\u000B' && LA4_0<='\f')||(LA4_0>='\u000E' && LA4_0<='\uFFFF')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:162:20: ~ ( '\\n' | '\\r' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:162:34: ( '\\r' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='\r') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:162:34: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 
            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LINE_COMMENT"

    // $ANTLR start "MULTI_LINE_COMMENT"
    public final void mMULTI_LINE_COMMENT() throws RecognitionException {
        try {
            int _type = MULTI_LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:163:19: ( '/*' ( . )* '*/' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:163:21: '/*' ( . )* '*/'
            {
            match("/*"); 

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:163:26: ( . )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='*') ) {
                    int LA6_1 = input.LA(2);

                    if ( (LA6_1=='/') ) {
                        alt6=2;
                    }
                    else if ( ((LA6_1>='\u0000' && LA6_1<='.')||(LA6_1>='0' && LA6_1<='\uFFFF')) ) {
                        alt6=1;
                    }


                }
                else if ( ((LA6_0>='\u0000' && LA6_0<=')')||(LA6_0>='+' && LA6_0<='\uFFFF')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:163:26: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            match("*/"); 

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MULTI_LINE_COMMENT"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:164:11: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:164:13: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHITESPACE"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:166:3: ( '=' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:166:5: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "GE"
    public final void mGE() throws RecognitionException {
        try {
            int _type = GE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:167:3: ( '>=' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:167:5: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GE"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:168:3: ( '>' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:168:5: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "LE"
    public final void mLE() throws RecognitionException {
        try {
            int _type = LE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:169:3: ( '<=' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:169:5: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LE"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:170:3: ( '<' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:170:5: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "NE"
    public final void mNE() throws RecognitionException {
        try {
            int _type = NE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:171:3: ( '!=' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:171:5: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NE"

    // $ANTLR start "ARROW"
    public final void mARROW() throws RecognitionException {
        try {
            int _type = ARROW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:173:6: ( '->' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:173:8: '->'
            {
            match("->"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ARROW"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:174:6: ( ':' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:174:8: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "COLON_EQUAL"
    public final void mCOLON_EQUAL() throws RecognitionException {
        try {
            int _type = COLON_EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:175:12: ( ':=' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:175:14: ':='
            {
            match(":="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON_EQUAL"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:176:6: ( ',' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:176:8: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:177:4: ( '.' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:177:6: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "DOUBLE_DASH_ARROW"
    public final void mDOUBLE_DASH_ARROW() throws RecognitionException {
        try {
            int _type = DOUBLE_DASH_ARROW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:178:18: ( '-->' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:178:20: '-->'
            {
            match("-->"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOUBLE_DASH_ARROW"

    // $ANTLR start "DOUBLE_EQUAL_ARROW"
    public final void mDOUBLE_EQUAL_ARROW() throws RecognitionException {
        try {
            int _type = DOUBLE_EQUAL_ARROW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:179:19: ( '==>' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:179:21: '==>'
            {
            match("==>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOUBLE_EQUAL_ARROW"

    // $ANTLR start "DOUBLE_DOT"
    public final void mDOUBLE_DOT() throws RecognitionException {
        try {
            int _type = DOUBLE_DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:180:11: ( '..' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:180:13: '..'
            {
            match(".."); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOUBLE_DOT"

    // $ANTLR start "DOUBLE_COLON"
    public final void mDOUBLE_COLON() throws RecognitionException {
        try {
            int _type = DOUBLE_COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:181:13: ( '::' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:181:15: '::'
            {
            match("::"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOUBLE_COLON"

    // $ANTLR start "LBRACE"
    public final void mLBRACE() throws RecognitionException {
        try {
            int _type = LBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:183:7: ( '{' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:183:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LBRACE"

    // $ANTLR start "RBRACE"
    public final void mRBRACE() throws RecognitionException {
        try {
            int _type = RBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:184:7: ( '}' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:184:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RBRACE"

    // $ANTLR start "LBRACKET"
    public final void mLBRACKET() throws RecognitionException {
        try {
            int _type = LBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:185:9: ( '[' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:185:11: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LBRACKET"

    // $ANTLR start "RBRACKET"
    public final void mRBRACKET() throws RecognitionException {
        try {
            int _type = RBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:186:9: ( ']' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:186:11: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RBRACKET"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:187:7: ( '(' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:187:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:188:7: ( ')' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:188:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "CARET"
    public final void mCARET() throws RecognitionException {
        try {
            int _type = CARET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:190:6: ( '^' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:190:8: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CARET"

    // $ANTLR start "DIV"
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:191:4: ( '/' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:191:6: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIV"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:192:6: ( '-' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:192:8: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:193:5: ( '+' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:193:7: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "TIMES"
    public final void mTIMES() throws RecognitionException {
        try {
            int _type = TIMES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:194:6: ( '*' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:194:8: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TIMES"

    // $ANTLR start "SEMICOLON"
    public final void mSEMICOLON() throws RecognitionException {
        try {
            int _type = SEMICOLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:196:10: ( ';' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:196:12: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEMICOLON"

    // $ANTLR start "SHARP"
    public final void mSHARP() throws RecognitionException {
        try {
            int _type = SHARP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:197:6: ( '#' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:197:8: '#'
            {
            match('#'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SHARP"

    public void mTokens() throws RecognitionException {
        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:8: ( ALL | ACTOR | IMPORT | MULTI | ID | NUMBER | STRING | LINE_COMMENT | MULTI_LINE_COMMENT | WHITESPACE | EQ | GE | GT | LE | LT | NE | ARROW | COLON | COLON_EQUAL | COMMA | DOT | DOUBLE_DASH_ARROW | DOUBLE_EQUAL_ARROW | DOUBLE_DOT | DOUBLE_COLON | LBRACE | RBRACE | LBRACKET | RBRACKET | LPAREN | RPAREN | CARET | DIV | MINUS | PLUS | TIMES | SEMICOLON | SHARP )
        int alt7=38;
        alt7 = dfa7.predict(input);
        switch (alt7) {
            case 1 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:10: ALL
                {
                mALL(); 

                }
                break;
            case 2 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:14: ACTOR
                {
                mACTOR(); 

                }
                break;
            case 3 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:20: IMPORT
                {
                mIMPORT(); 

                }
                break;
            case 4 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:27: MULTI
                {
                mMULTI(); 

                }
                break;
            case 5 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:33: ID
                {
                mID(); 

                }
                break;
            case 6 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:36: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 7 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:43: STRING
                {
                mSTRING(); 

                }
                break;
            case 8 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:50: LINE_COMMENT
                {
                mLINE_COMMENT(); 

                }
                break;
            case 9 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:63: MULTI_LINE_COMMENT
                {
                mMULTI_LINE_COMMENT(); 

                }
                break;
            case 10 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:82: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;
            case 11 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:93: EQ
                {
                mEQ(); 

                }
                break;
            case 12 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:96: GE
                {
                mGE(); 

                }
                break;
            case 13 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:99: GT
                {
                mGT(); 

                }
                break;
            case 14 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:102: LE
                {
                mLE(); 

                }
                break;
            case 15 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:105: LT
                {
                mLT(); 

                }
                break;
            case 16 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:108: NE
                {
                mNE(); 

                }
                break;
            case 17 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:111: ARROW
                {
                mARROW(); 

                }
                break;
            case 18 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:117: COLON
                {
                mCOLON(); 

                }
                break;
            case 19 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:123: COLON_EQUAL
                {
                mCOLON_EQUAL(); 

                }
                break;
            case 20 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:135: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 21 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:141: DOT
                {
                mDOT(); 

                }
                break;
            case 22 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:145: DOUBLE_DASH_ARROW
                {
                mDOUBLE_DASH_ARROW(); 

                }
                break;
            case 23 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:163: DOUBLE_EQUAL_ARROW
                {
                mDOUBLE_EQUAL_ARROW(); 

                }
                break;
            case 24 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:182: DOUBLE_DOT
                {
                mDOUBLE_DOT(); 

                }
                break;
            case 25 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:193: DOUBLE_COLON
                {
                mDOUBLE_COLON(); 

                }
                break;
            case 26 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:206: LBRACE
                {
                mLBRACE(); 

                }
                break;
            case 27 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:213: RBRACE
                {
                mRBRACE(); 

                }
                break;
            case 28 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:220: LBRACKET
                {
                mLBRACKET(); 

                }
                break;
            case 29 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:229: RBRACKET
                {
                mRBRACKET(); 

                }
                break;
            case 30 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:238: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 31 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:245: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 32 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:252: CARET
                {
                mCARET(); 

                }
                break;
            case 33 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:258: DIV
                {
                mDIV(); 

                }
                break;
            case 34 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:262: MINUS
                {
                mMINUS(); 

                }
                break;
            case 35 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:268: PLUS
                {
                mPLUS(); 

                }
                break;
            case 36 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:273: TIMES
                {
                mTIMES(); 

                }
                break;
            case 37 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:279: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 38 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:1:289: SHARP
                {
                mSHARP(); 

                }
                break;

        }

    }


    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA7_eotS =
        "\1\uffff\3\4\3\uffff\1\42\1\uffff\1\44\1\46\1\50\1\uffff\1\53\1"+
        "\56\1\uffff\1\60\13\uffff\4\4\21\uffff\1\65\3\4\1\uffff\3\4\1\74"+
        "\1\4\1\76\1\uffff\1\77\2\uffff";
    static final String DFA7_eofS =
        "\100\uffff";
    static final String DFA7_minS =
        "\1\11\1\143\1\155\1\165\3\uffff\1\52\1\uffff\3\75\1\uffff\1\55"+
        "\1\72\1\uffff\1\56\13\uffff\1\154\1\164\1\160\1\154\21\uffff\1\44"+
        "\2\157\1\164\1\uffff\2\162\1\151\1\44\1\164\1\44\1\uffff\1\44\2"+
        "\uffff";
    static final String DFA7_maxS =
        "\1\175\1\154\1\155\1\165\3\uffff\1\57\1\uffff\3\75\1\uffff\1\76"+
        "\1\75\1\uffff\1\56\13\uffff\1\154\1\164\1\160\1\154\21\uffff\1\172"+
        "\2\157\1\164\1\uffff\2\162\1\151\1\172\1\164\1\172\1\uffff\1\172"+
        "\2\uffff";
    static final String DFA7_acceptS =
        "\4\uffff\1\5\1\6\1\7\1\uffff\1\12\3\uffff\1\20\2\uffff\1\24\1\uffff"+
        "\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\43\1\44\1\45\1\46\4\uffff"+
        "\1\10\1\11\1\41\1\27\1\13\1\14\1\15\1\16\1\17\1\21\1\26\1\42\1\23"+
        "\1\31\1\22\1\30\1\25\4\uffff\1\1\6\uffff\1\2\1\uffff\1\4\1\3";
    static final String DFA7_specialS =
        "\100\uffff}>";
    static final String[] DFA7_transitionS = {
            "\2\10\1\uffff\2\10\22\uffff\1\10\1\14\1\6\1\33\1\4\3\uffff"+
            "\1\25\1\26\1\31\1\30\1\17\1\15\1\20\1\7\12\5\1\16\1\32\1\13"+
            "\1\11\1\12\2\uffff\32\4\1\23\1\uffff\1\24\1\27\1\4\1\uffff\1"+
            "\1\7\4\1\2\3\4\1\3\15\4\1\21\1\uffff\1\22",
            "\1\35\10\uffff\1\34",
            "\1\36",
            "\1\37",
            "",
            "",
            "",
            "\1\41\4\uffff\1\40",
            "",
            "\1\43",
            "\1\45",
            "\1\47",
            "",
            "\1\52\20\uffff\1\51",
            "\1\55\2\uffff\1\54",
            "",
            "\1\57",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\4\13\uffff\12\4\7\uffff\32\4\4\uffff\1\4\1\uffff\32\4",
            "\1\66",
            "\1\67",
            "\1\70",
            "",
            "\1\71",
            "\1\72",
            "\1\73",
            "\1\4\13\uffff\12\4\7\uffff\32\4\4\uffff\1\4\1\uffff\32\4",
            "\1\75",
            "\1\4\13\uffff\12\4\7\uffff\32\4\4\uffff\1\4\1\uffff\32\4",
            "",
            "\1\4\13\uffff\12\4\7\uffff\32\4\4\uffff\1\4\1\uffff\32\4",
            "",
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( ALL | ACTOR | IMPORT | MULTI | ID | NUMBER | STRING | LINE_COMMENT | MULTI_LINE_COMMENT | WHITESPACE | EQ | GE | GT | LE | LT | NE | ARROW | COLON | COLON_EQUAL | COMMA | DOT | DOUBLE_DASH_ARROW | DOUBLE_EQUAL_ARROW | DOUBLE_DOT | DOUBLE_COLON | LBRACE | RBRACE | LBRACKET | RBRACKET | LPAREN | RPAREN | CARET | DIV | MINUS | PLUS | TIMES | SEMICOLON | SHARP );";
        }
    }
 

}