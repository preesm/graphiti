// $ANTLR 3.1.2 D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g 2009-03-30 17:53:41

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CalLexer extends Lexer {
    public static final int LT=47;
    public static final int Inputs=20;
    public static final int LBRACE=45;
    public static final int Actor=18;
    public static final int MULTI=49;
    public static final int NOT=51;
    public static final int ID=24;
    public static final int EOF=-1;
    public static final int DOUBLE_DOT=39;
    public static final int LPAREN=27;
    public static final int LBRACKET=25;
    public static final int RPAREN=28;
    public static final int IMPORT=60;
    public static final int COLON_EQUAL=34;
    public static final int COMMA=35;
    public static final int CARET=33;
    public static final int ALL=31;
    public static final int TypeAttr=15;
    public static final int PLUS=53;
    public static final int String=10;
    public static final int RBRACKET=26;
    public static final int EQ=41;
    public static final int DOT=37;
    public static final int NE=50;
    public static final int Outputs=21;
    public static final int DOUBLE_EQUAL_ARROW=29;
    public static final int DOUBLE_DASH_ARROW=38;
    public static final int GE=43;
    public static final int SHARP=56;
    public static final int RBRACE=54;
    public static final int Type=14;
    public static final int LINE_COMMENT=61;
    public static final int NUMBER=52;
    public static final int WHITESPACE=63;
    public static final int SEMICOLON=55;
    public static final int MINUS=48;
    public static final int TRUE=59;
    public static final int Expression=6;
    public static final int MULTI_LINE_COMMENT=62;
    public static final int Parameter=13;
    public static final int COLON=30;
    public static final int TypePar=17;
    public static final int UnOp=11;
    public static final int Minus=8;
    public static final int DOUBLE_COLON=40;
    public static final int Boolean=5;
    public static final int ACTOR=23;
    public static final int ExprAttr=16;
    public static final int Not=9;
    public static final int Name=19;
    public static final int ARROW=32;
    public static final int GT=44;
    public static final int DIV=36;
    public static final int BinOp=4;
    public static final int TIMES=58;
    public static final int FALSE=42;
    public static final int PortDecl=22;
    public static final int Integer=7;
    public static final int Var=12;
    public static final int LE=46;
    public static final int STRING=57;

    // delegates
    // delegators

    public CalLexer() {;} 
    public CalLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public CalLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g"; }

    // $ANTLR start "ALL"
    public final void mALL() throws RecognitionException {
        try {
            int _type = ALL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:182:4: ( 'all' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:182:6: 'all'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:183:6: ( 'actor' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:183:8: 'actor'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:184:7: ( 'import' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:184:9: 'import'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:185:6: ( 'multi' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:185:8: 'multi'
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

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:186:4: ( 'not' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:186:6: 'not'
            {
            match("not"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:188:5: ( 'true' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:188:7: 'true'
            {
            match("true"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRUE"

    // $ANTLR start "FALSE"
    public final void mFALSE() throws RecognitionException {
        try {
            int _type = FALSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:189:6: ( 'false' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:189:8: 'false'
            {
            match("false"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FALSE"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:191:3: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '$' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '$' | '0' .. '9' )* )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:191:5: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '$' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '$' | '0' .. '9' )*
            {
            if ( input.LA(1)=='$'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:191:39: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '$' | '0' .. '9' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='$'||(LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:192:7: ( ( '-' )? ( '0' .. '9' )+ )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:192:9: ( '-' )? ( '0' .. '9' )+
            {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:192:9: ( '-' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='-') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:192:9: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:192:14: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:192:15: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:193:7: ( '\\\"' ( . )* '\\\"' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:193:9: '\\\"' ( . )* '\\\"'
            {
            match('\"'); 
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:193:14: ( . )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='\"') ) {
                    alt4=2;
                }
                else if ( ((LA4_0>='\u0000' && LA4_0<='!')||(LA4_0>='#' && LA4_0<='\uFFFF')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:193:14: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop4;
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:195:13: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:195:15: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:195:20: (~ ( '\\n' | '\\r' ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='\u0000' && LA5_0<='\t')||(LA5_0>='\u000B' && LA5_0<='\f')||(LA5_0>='\u000E' && LA5_0<='\uFFFF')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:195:20: ~ ( '\\n' | '\\r' )
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
            	    break loop5;
                }
            } while (true);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:195:34: ( '\\r' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='\r') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:195:34: '\\r'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:196:19: ( '/*' ( . )* '*/' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:196:21: '/*' ( . )* '*/'
            {
            match("/*"); 

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:196:26: ( . )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='*') ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1=='/') ) {
                        alt7=2;
                    }
                    else if ( ((LA7_1>='\u0000' && LA7_1<='.')||(LA7_1>='0' && LA7_1<='\uFFFF')) ) {
                        alt7=1;
                    }


                }
                else if ( ((LA7_0>='\u0000' && LA7_0<=')')||(LA7_0>='+' && LA7_0<='\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:196:26: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop7;
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:197:11: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:197:13: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:199:3: ( '=' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:199:5: '='
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:200:3: ( '>=' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:200:5: '>='
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:201:3: ( '>' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:201:5: '>'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:202:3: ( '<=' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:202:5: '<='
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:203:3: ( '<' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:203:5: '<'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:204:3: ( '!=' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:204:5: '!='
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:206:6: ( '->' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:206:8: '->'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:207:6: ( ':' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:207:8: ':'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:208:12: ( ':=' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:208:14: ':='
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:209:6: ( ',' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:209:8: ','
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:210:4: ( '.' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:210:6: '.'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:211:18: ( '-->' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:211:20: '-->'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:212:19: ( '==>' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:212:21: '==>'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:213:11: ( '..' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:213:13: '..'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:214:13: ( '::' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:214:15: '::'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:216:7: ( '{' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:216:9: '{'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:217:7: ( '}' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:217:9: '}'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:218:9: ( '[' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:218:11: '['
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:219:9: ( ']' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:219:11: ']'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:220:7: ( '(' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:220:9: '('
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:221:7: ( ')' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:221:9: ')'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:223:6: ( '^' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:223:8: '^'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:224:4: ( '/' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:224:6: '/'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:225:6: ( '-' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:225:8: '-'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:226:5: ( '+' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:226:7: '+'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:227:6: ( '*' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:227:8: '*'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:229:10: ( ';' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:229:12: ';'
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:230:6: ( '#' )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:230:8: '#'
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
        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:8: ( ALL | ACTOR | IMPORT | MULTI | NOT | TRUE | FALSE | ID | NUMBER | STRING | LINE_COMMENT | MULTI_LINE_COMMENT | WHITESPACE | EQ | GE | GT | LE | LT | NE | ARROW | COLON | COLON_EQUAL | COMMA | DOT | DOUBLE_DASH_ARROW | DOUBLE_EQUAL_ARROW | DOUBLE_DOT | DOUBLE_COLON | LBRACE | RBRACE | LBRACKET | RBRACKET | LPAREN | RPAREN | CARET | DIV | MINUS | PLUS | TIMES | SEMICOLON | SHARP )
        int alt8=41;
        alt8 = dfa8.predict(input);
        switch (alt8) {
            case 1 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:10: ALL
                {
                mALL(); 

                }
                break;
            case 2 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:14: ACTOR
                {
                mACTOR(); 

                }
                break;
            case 3 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:20: IMPORT
                {
                mIMPORT(); 

                }
                break;
            case 4 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:27: MULTI
                {
                mMULTI(); 

                }
                break;
            case 5 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:33: NOT
                {
                mNOT(); 

                }
                break;
            case 6 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:37: TRUE
                {
                mTRUE(); 

                }
                break;
            case 7 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:42: FALSE
                {
                mFALSE(); 

                }
                break;
            case 8 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:48: ID
                {
                mID(); 

                }
                break;
            case 9 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:51: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 10 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:58: STRING
                {
                mSTRING(); 

                }
                break;
            case 11 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:65: LINE_COMMENT
                {
                mLINE_COMMENT(); 

                }
                break;
            case 12 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:78: MULTI_LINE_COMMENT
                {
                mMULTI_LINE_COMMENT(); 

                }
                break;
            case 13 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:97: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;
            case 14 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:108: EQ
                {
                mEQ(); 

                }
                break;
            case 15 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:111: GE
                {
                mGE(); 

                }
                break;
            case 16 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:114: GT
                {
                mGT(); 

                }
                break;
            case 17 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:117: LE
                {
                mLE(); 

                }
                break;
            case 18 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:120: LT
                {
                mLT(); 

                }
                break;
            case 19 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:123: NE
                {
                mNE(); 

                }
                break;
            case 20 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:126: ARROW
                {
                mARROW(); 

                }
                break;
            case 21 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:132: COLON
                {
                mCOLON(); 

                }
                break;
            case 22 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:138: COLON_EQUAL
                {
                mCOLON_EQUAL(); 

                }
                break;
            case 23 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:150: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 24 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:156: DOT
                {
                mDOT(); 

                }
                break;
            case 25 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:160: DOUBLE_DASH_ARROW
                {
                mDOUBLE_DASH_ARROW(); 

                }
                break;
            case 26 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:178: DOUBLE_EQUAL_ARROW
                {
                mDOUBLE_EQUAL_ARROW(); 

                }
                break;
            case 27 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:197: DOUBLE_DOT
                {
                mDOUBLE_DOT(); 

                }
                break;
            case 28 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:208: DOUBLE_COLON
                {
                mDOUBLE_COLON(); 

                }
                break;
            case 29 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:221: LBRACE
                {
                mLBRACE(); 

                }
                break;
            case 30 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:228: RBRACE
                {
                mRBRACE(); 

                }
                break;
            case 31 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:235: LBRACKET
                {
                mLBRACKET(); 

                }
                break;
            case 32 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:244: RBRACKET
                {
                mRBRACKET(); 

                }
                break;
            case 33 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:253: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 34 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:260: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 35 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:267: CARET
                {
                mCARET(); 

                }
                break;
            case 36 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:273: DIV
                {
                mDIV(); 

                }
                break;
            case 37 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:277: MINUS
                {
                mMINUS(); 

                }
                break;
            case 38 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:283: PLUS
                {
                mPLUS(); 

                }
                break;
            case 39 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:288: TIMES
                {
                mTIMES(); 

                }
                break;
            case 40 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:294: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 41 :
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:1:304: SHARP
                {
                mSHARP(); 

                }
                break;

        }

    }


    protected DFA8 dfa8 = new DFA8(this);
    static final String DFA8_eotS =
        "\1\uffff\6\7\1\uffff\1\50\2\uffff\1\53\1\uffff\1\55\1\57\1\61\1"+
        "\uffff\1\64\1\uffff\1\66\13\uffff\7\7\21\uffff\1\76\3\7\1\102\2"+
        "\7\1\uffff\3\7\1\uffff\1\110\1\7\1\112\1\7\1\114\1\uffff\1\115\1"+
        "\uffff\1\116\3\uffff";
    static final String DFA8_eofS =
        "\117\uffff";
    static final String DFA8_minS =
        "\1\11\1\143\1\155\1\165\1\157\1\162\1\141\1\uffff\1\55\2\uffff"+
        "\1\52\1\uffff\3\75\1\uffff\1\72\1\uffff\1\56\13\uffff\1\154\1\164"+
        "\1\160\1\154\1\164\1\165\1\154\21\uffff\1\44\2\157\1\164\1\44\1"+
        "\145\1\163\1\uffff\2\162\1\151\1\uffff\1\44\1\145\1\44\1\164\1\44"+
        "\1\uffff\1\44\1\uffff\1\44\3\uffff";
    static final String DFA8_maxS =
        "\1\175\1\154\1\155\1\165\1\157\1\162\1\141\1\uffff\1\76\2\uffff"+
        "\1\57\1\uffff\3\75\1\uffff\1\75\1\uffff\1\56\13\uffff\1\154\1\164"+
        "\1\160\1\154\1\164\1\165\1\154\21\uffff\1\172\2\157\1\164\1\172"+
        "\1\145\1\163\1\uffff\2\162\1\151\1\uffff\1\172\1\145\1\172\1\164"+
        "\1\172\1\uffff\1\172\1\uffff\1\172\3\uffff";
    static final String DFA8_acceptS =
        "\7\uffff\1\10\1\uffff\1\11\1\12\1\uffff\1\15\3\uffff\1\23\1\uffff"+
        "\1\27\1\uffff\1\35\1\36\1\37\1\40\1\41\1\42\1\43\1\46\1\47\1\50"+
        "\1\51\7\uffff\1\24\1\31\1\45\1\13\1\14\1\44\1\32\1\16\1\17\1\20"+
        "\1\21\1\22\1\26\1\34\1\25\1\33\1\30\7\uffff\1\1\3\uffff\1\5\5\uffff"+
        "\1\6\1\uffff\1\2\1\uffff\1\4\1\7\1\3";
    static final String DFA8_specialS =
        "\117\uffff}>";
    static final String[] DFA8_transitionS = {
            "\2\14\1\uffff\2\14\22\uffff\1\14\1\20\1\12\1\36\1\7\3\uffff"+
            "\1\30\1\31\1\34\1\33\1\22\1\10\1\23\1\13\12\11\1\21\1\35\1\17"+
            "\1\15\1\16\2\uffff\32\7\1\26\1\uffff\1\27\1\32\1\7\1\uffff\1"+
            "\1\4\7\1\6\2\7\1\2\3\7\1\3\1\4\5\7\1\5\6\7\1\24\1\uffff\1\25",
            "\1\40\10\uffff\1\37",
            "\1\41",
            "\1\42",
            "\1\43",
            "\1\44",
            "\1\45",
            "",
            "\1\47\2\uffff\12\11\4\uffff\1\46",
            "",
            "",
            "\1\52\4\uffff\1\51",
            "",
            "\1\54",
            "\1\56",
            "\1\60",
            "",
            "\1\63\2\uffff\1\62",
            "",
            "\1\65",
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
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "\1\74",
            "\1\75",
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
            "\1\7\13\uffff\12\7\7\uffff\32\7\4\uffff\1\7\1\uffff\32\7",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\7\13\uffff\12\7\7\uffff\32\7\4\uffff\1\7\1\uffff\32\7",
            "\1\103",
            "\1\104",
            "",
            "\1\105",
            "\1\106",
            "\1\107",
            "",
            "\1\7\13\uffff\12\7\7\uffff\32\7\4\uffff\1\7\1\uffff\32\7",
            "\1\111",
            "\1\7\13\uffff\12\7\7\uffff\32\7\4\uffff\1\7\1\uffff\32\7",
            "\1\113",
            "\1\7\13\uffff\12\7\7\uffff\32\7\4\uffff\1\7\1\uffff\32\7",
            "",
            "\1\7\13\uffff\12\7\7\uffff\32\7\4\uffff\1\7\1\uffff\32\7",
            "",
            "\1\7\13\uffff\12\7\7\uffff\32\7\4\uffff\1\7\1\uffff\32\7",
            "",
            "",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( ALL | ACTOR | IMPORT | MULTI | NOT | TRUE | FALSE | ID | NUMBER | STRING | LINE_COMMENT | MULTI_LINE_COMMENT | WHITESPACE | EQ | GE | GT | LE | LT | NE | ARROW | COLON | COLON_EQUAL | COMMA | DOT | DOUBLE_DASH_ARROW | DOUBLE_EQUAL_ARROW | DOUBLE_DOT | DOUBLE_COLON | LBRACE | RBRACE | LBRACKET | RBRACKET | LPAREN | RPAREN | CARET | DIV | MINUS | PLUS | TIMES | SEMICOLON | SHARP );";
        }
    }
 

}