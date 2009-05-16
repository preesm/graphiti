// $ANTLR 3.1.2 D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g 2009-05-16 11:00:23

package net.sf.graphiti.grammar;


import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

public class CalLexer extends Lexer {
    public static final int StructureStmt=10;
    public static final int LT=61;
    public static final int Inputs=16;
    public static final int LBRACE=55;
    public static final int Empty=14;
    public static final int FLOAT=68;
    public static final int VarDecl=11;
    public static final int Actor=12;
    public static final int MULTI=60;
    public static final int NOT=63;
    public static final int ID=50;
    public static final int Attribute=4;
    public static final int EOF=-1;
    public static final int DOUBLE_DOT=81;
    public static final int STRUCTURE=52;
    public static final int LPAREN=39;
    public static final int LBRACKET=37;
    public static final int RPAREN=40;
    public static final int IMPORT=58;
    public static final int COLON_EQUAL=47;
    public static final int COMMA=51;
    public static final int CARET=67;
    public static final int ALL=59;
    public static final int TypeAttr=22;
    public static final int ENTITIES=49;
    public static final int Real=32;
    public static final int PLUS=64;
    public static final int VAR=44;
    public static final int Network=9;
    public static final int String=33;
    public static final int EQ=46;
    public static final int RBRACKET=38;
    public static final int DOT=54;
    public static final int EntityDecl=6;
    public static final int NE=79;
    public static final int INTEGER=69;
    public static final int Outputs=17;
    public static final int DOUBLE_EQUAL_ARROW=43;
    public static final int GE=76;
    public static final int DOUBLE_DASH_ARROW=53;
    public static final int EntityPar=8;
    public static final int Type=21;
    public static final int RBRACE=56;
    public static final int SHARP=83;
    public static final int LINE_COMMENT=73;
    public static final int QualifiedId=19;
    public static final int WHITESPACE=75;
    public static final int NETWORK=36;
    public static final int SEMICOLON=48;
    public static final int MINUS=62;
    public static final int Expression=27;
    public static final int TRUE=71;
    public static final int MULTI_LINE_COMMENT=74;
    public static final int List=29;
    public static final int Parameter=20;
    public static final int COLON=41;
    public static final int TypePar=24;
    public static final int UnOp=34;
    public static final int Minus=30;
    public static final int Connector=5;
    public static final int DOUBLE_COLON=82;
    public static final int Boolean=26;
    public static final int ExprAttr=23;
    public static final int EntityExpr=7;
    public static final int ACTOR=57;
    public static final int Not=31;
    public static final int Dot=13;
    public static final int Name=15;
    public static final int GT=77;
    public static final int ARROW=80;
    public static final int DIV=66;
    public static final int END=42;
    public static final int BinOp=25;
    public static final int TIMES=65;
    public static final int FALSE=72;
    public static final int PortDecl=18;
    public static final int MUTABLE=45;
    public static final int Var=35;
    public static final int Integer=28;
    public static final int LE=78;
    public static final int STRING=70;

    // delegates
    // delegators

    public CalLexer() {;} 
    public CalLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public CalLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g"; }

    // $ANTLR start "ALL"
    public final void mALL() throws RecognitionException {
        try {
            int _type = ALL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:226:4: ( 'all' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:226:6: 'all'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:227:6: ( 'actor' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:227:8: 'actor'
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

    // $ANTLR start "END"
    public final void mEND() throws RecognitionException {
        try {
            int _type = END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:228:4: ( 'end' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:228:6: 'end'
            {
            match("end"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "END"

    // $ANTLR start "ENTITIES"
    public final void mENTITIES() throws RecognitionException {
        try {
            int _type = ENTITIES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:229:9: ( 'entities' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:229:11: 'entities'
            {
            match("entities"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ENTITIES"

    // $ANTLR start "IMPORT"
    public final void mIMPORT() throws RecognitionException {
        try {
            int _type = IMPORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:230:7: ( 'import' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:230:9: 'import'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:231:6: ( 'multi' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:231:8: 'multi'
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

    // $ANTLR start "MUTABLE"
    public final void mMUTABLE() throws RecognitionException {
        try {
            int _type = MUTABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:232:8: ( 'mutable' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:232:10: 'mutable'
            {
            match("mutable"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MUTABLE"

    // $ANTLR start "NETWORK"
    public final void mNETWORK() throws RecognitionException {
        try {
            int _type = NETWORK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:233:8: ( 'network' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:233:10: 'network'
            {
            match("network"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NETWORK"

    // $ANTLR start "STRUCTURE"
    public final void mSTRUCTURE() throws RecognitionException {
        try {
            int _type = STRUCTURE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:234:10: ( 'structure' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:234:12: 'structure'
            {
            match("structure"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRUCTURE"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:235:4: ( 'not' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:235:6: 'not'
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

    // $ANTLR start "VAR"
    public final void mVAR() throws RecognitionException {
        try {
            int _type = VAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:236:4: ( 'var' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:236:6: 'var'
            {
            match("var"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VAR"

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:238:5: ( 'true' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:238:7: 'true'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:239:6: ( 'false' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:239:8: 'false'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:241:3: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '$' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '$' | '0' .. '9' )* )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:241:5: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '$' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '$' | '0' .. '9' )*
            {
            if ( input.LA(1)=='$'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:241:39: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '$' | '0' .. '9' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='$'||(LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:
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

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:242:6: ( ( '-' )? ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ ) ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:242:8: ( '-' )? ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ ) )
            {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:242:8: ( '-' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='-') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:242:8: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:242:13: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ ) )
            int alt15=3;
            alt15 = dfa15.predict(input);
            switch (alt15) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:242:14: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    {
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:242:14: ( '0' .. '9' )+
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
                    	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:242:15: '0' .. '9'
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

                    match('.'); 
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:242:30: ( '0' .. '9' )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:242:31: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);

                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:242:42: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0=='E'||LA7_0=='e') ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:242:43: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                            {
                            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}

                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:242:55: ( '+' | '-' )?
                            int alt5=2;
                            int LA5_0 = input.LA(1);

                            if ( (LA5_0=='+'||LA5_0=='-') ) {
                                alt5=1;
                            }
                            switch (alt5) {
                                case 1 :
                                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:
                                    {
                                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                                        input.consume();

                                    }
                                    else {
                                        MismatchedSetException mse = new MismatchedSetException(null,input);
                                        recover(mse);
                                        throw mse;}


                                    }
                                    break;

                            }

                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:242:68: ( '0' .. '9' )+
                            int cnt6=0;
                            loop6:
                            do {
                                int alt6=2;
                                int LA6_0 = input.LA(1);

                                if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                                    alt6=1;
                                }


                                switch (alt6) {
                            	case 1 :
                            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:242:69: '0' .. '9'
                            	    {
                            	    matchRange('0','9'); 

                            	    }
                            	    break;

                            	default :
                            	    if ( cnt6 >= 1 ) break loop6;
                                        EarlyExitException eee =
                                            new EarlyExitException(6, input);
                                        throw eee;
                                }
                                cnt6++;
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:243:4: '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    {
                    match('.'); 
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:243:8: ( '0' .. '9' )+
                    int cnt8=0;
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0>='0' && LA8_0<='9')) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:243:9: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt8 >= 1 ) break loop8;
                                EarlyExitException eee =
                                    new EarlyExitException(8, input);
                                throw eee;
                        }
                        cnt8++;
                    } while (true);

                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:243:20: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0=='E'||LA11_0=='e') ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:243:21: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                            {
                            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}

                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:243:33: ( '+' | '-' )?
                            int alt9=2;
                            int LA9_0 = input.LA(1);

                            if ( (LA9_0=='+'||LA9_0=='-') ) {
                                alt9=1;
                            }
                            switch (alt9) {
                                case 1 :
                                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:
                                    {
                                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                                        input.consume();

                                    }
                                    else {
                                        MismatchedSetException mse = new MismatchedSetException(null,input);
                                        recover(mse);
                                        throw mse;}


                                    }
                                    break;

                            }

                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:243:46: ( '0' .. '9' )+
                            int cnt10=0;
                            loop10:
                            do {
                                int alt10=2;
                                int LA10_0 = input.LA(1);

                                if ( ((LA10_0>='0' && LA10_0<='9')) ) {
                                    alt10=1;
                                }


                                switch (alt10) {
                            	case 1 :
                            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:243:47: '0' .. '9'
                            	    {
                            	    matchRange('0','9'); 

                            	    }
                            	    break;

                            	default :
                            	    if ( cnt10 >= 1 ) break loop10;
                                        EarlyExitException eee =
                                            new EarlyExitException(10, input);
                                        throw eee;
                                }
                                cnt10++;
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:244:4: ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
                    {
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:244:4: ( '0' .. '9' )+
                    int cnt12=0;
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0>='0' && LA12_0<='9')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:244:5: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt12 >= 1 ) break loop12;
                                EarlyExitException eee =
                                    new EarlyExitException(12, input);
                                throw eee;
                        }
                        cnt12++;
                    } while (true);

                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:244:16: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:244:17: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                    {
                    if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:244:29: ( '+' | '-' )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0=='+'||LA13_0=='-') ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:
                            {
                            if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}


                            }
                            break;

                    }

                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:244:42: ( '0' .. '9' )+
                    int cnt14=0;
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( ((LA14_0>='0' && LA14_0<='9')) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:244:43: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt14 >= 1 ) break loop14;
                                EarlyExitException eee =
                                    new EarlyExitException(14, input);
                                throw eee;
                        }
                        cnt14++;
                    } while (true);


                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FLOAT"

    // $ANTLR start "INTEGER"
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:245:8: ( ( '-' )? ( '0' .. '9' )+ )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:245:10: ( '-' )? ( '0' .. '9' )+
            {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:245:10: ( '-' )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='-') ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:245:10: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:245:15: ( '0' .. '9' )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0>='0' && LA17_0<='9')) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:245:16: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTEGER"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:246:7: ( '\\\"' ( . )* '\\\"' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:246:9: '\\\"' ( . )* '\\\"'
            {
            match('\"'); 
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:246:14: ( . )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0=='\"') ) {
                    alt18=2;
                }
                else if ( ((LA18_0>='\u0000' && LA18_0<='!')||(LA18_0>='#' && LA18_0<='\uFFFF')) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:246:14: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop18;
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:248:13: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:248:15: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:248:20: (~ ( '\\n' | '\\r' ) )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>='\u0000' && LA19_0<='\t')||(LA19_0>='\u000B' && LA19_0<='\f')||(LA19_0>='\u000E' && LA19_0<='\uFFFF')) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:248:20: ~ ( '\\n' | '\\r' )
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
            	    break loop19;
                }
            } while (true);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:248:34: ( '\\r' )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0=='\r') ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:248:34: '\\r'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:249:19: ( '/*' ( . )* '*/' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:249:21: '/*' ( . )* '*/'
            {
            match("/*"); 

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:249:26: ( . )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0=='*') ) {
                    int LA21_1 = input.LA(2);

                    if ( (LA21_1=='/') ) {
                        alt21=2;
                    }
                    else if ( ((LA21_1>='\u0000' && LA21_1<='.')||(LA21_1>='0' && LA21_1<='\uFFFF')) ) {
                        alt21=1;
                    }


                }
                else if ( ((LA21_0>='\u0000' && LA21_0<=')')||(LA21_0>='+' && LA21_0<='\uFFFF')) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:249:26: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop21;
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:250:11: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:250:13: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:252:3: ( '=' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:252:5: '='
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:253:3: ( '>=' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:253:5: '>='
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:254:3: ( '>' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:254:5: '>'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:255:3: ( '<=' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:255:5: '<='
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:256:3: ( '<' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:256:5: '<'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:257:3: ( '!=' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:257:5: '!='
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:259:6: ( '->' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:259:8: '->'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:260:6: ( ':' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:260:8: ':'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:261:12: ( ':=' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:261:14: ':='
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:262:6: ( ',' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:262:8: ','
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:263:4: ( '.' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:263:6: '.'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:264:18: ( '-->' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:264:20: '-->'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:265:19: ( '==>' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:265:21: '==>'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:266:11: ( '..' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:266:13: '..'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:267:13: ( '::' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:267:15: '::'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:269:7: ( '{' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:269:9: '{'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:270:7: ( '}' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:270:9: '}'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:271:9: ( '[' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:271:11: '['
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:272:9: ( ']' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:272:11: ']'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:273:7: ( '(' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:273:9: '('
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:274:7: ( ')' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:274:9: ')'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:276:6: ( '^' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:276:8: '^'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:277:4: ( '/' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:277:6: '/'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:278:6: ( '-' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:278:8: '-'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:279:5: ( '+' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:279:7: '+'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:280:6: ( '*' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:280:8: '*'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:282:10: ( ';' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:282:12: ';'
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:283:6: ( '#' )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:283:8: '#'
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
        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:8: ( ALL | ACTOR | END | ENTITIES | IMPORT | MULTI | MUTABLE | NETWORK | STRUCTURE | NOT | VAR | TRUE | FALSE | ID | FLOAT | INTEGER | STRING | LINE_COMMENT | MULTI_LINE_COMMENT | WHITESPACE | EQ | GE | GT | LE | LT | NE | ARROW | COLON | COLON_EQUAL | COMMA | DOT | DOUBLE_DASH_ARROW | DOUBLE_EQUAL_ARROW | DOUBLE_DOT | DOUBLE_COLON | LBRACE | RBRACE | LBRACKET | RBRACKET | LPAREN | RPAREN | CARET | DIV | MINUS | PLUS | TIMES | SEMICOLON | SHARP )
        int alt22=48;
        alt22 = dfa22.predict(input);
        switch (alt22) {
            case 1 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:10: ALL
                {
                mALL(); 

                }
                break;
            case 2 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:14: ACTOR
                {
                mACTOR(); 

                }
                break;
            case 3 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:20: END
                {
                mEND(); 

                }
                break;
            case 4 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:24: ENTITIES
                {
                mENTITIES(); 

                }
                break;
            case 5 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:33: IMPORT
                {
                mIMPORT(); 

                }
                break;
            case 6 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:40: MULTI
                {
                mMULTI(); 

                }
                break;
            case 7 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:46: MUTABLE
                {
                mMUTABLE(); 

                }
                break;
            case 8 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:54: NETWORK
                {
                mNETWORK(); 

                }
                break;
            case 9 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:62: STRUCTURE
                {
                mSTRUCTURE(); 

                }
                break;
            case 10 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:72: NOT
                {
                mNOT(); 

                }
                break;
            case 11 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:76: VAR
                {
                mVAR(); 

                }
                break;
            case 12 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:80: TRUE
                {
                mTRUE(); 

                }
                break;
            case 13 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:85: FALSE
                {
                mFALSE(); 

                }
                break;
            case 14 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:91: ID
                {
                mID(); 

                }
                break;
            case 15 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:94: FLOAT
                {
                mFLOAT(); 

                }
                break;
            case 16 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:100: INTEGER
                {
                mINTEGER(); 

                }
                break;
            case 17 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:108: STRING
                {
                mSTRING(); 

                }
                break;
            case 18 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:115: LINE_COMMENT
                {
                mLINE_COMMENT(); 

                }
                break;
            case 19 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:128: MULTI_LINE_COMMENT
                {
                mMULTI_LINE_COMMENT(); 

                }
                break;
            case 20 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:147: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;
            case 21 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:158: EQ
                {
                mEQ(); 

                }
                break;
            case 22 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:161: GE
                {
                mGE(); 

                }
                break;
            case 23 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:164: GT
                {
                mGT(); 

                }
                break;
            case 24 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:167: LE
                {
                mLE(); 

                }
                break;
            case 25 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:170: LT
                {
                mLT(); 

                }
                break;
            case 26 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:173: NE
                {
                mNE(); 

                }
                break;
            case 27 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:176: ARROW
                {
                mARROW(); 

                }
                break;
            case 28 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:182: COLON
                {
                mCOLON(); 

                }
                break;
            case 29 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:188: COLON_EQUAL
                {
                mCOLON_EQUAL(); 

                }
                break;
            case 30 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:200: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 31 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:206: DOT
                {
                mDOT(); 

                }
                break;
            case 32 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:210: DOUBLE_DASH_ARROW
                {
                mDOUBLE_DASH_ARROW(); 

                }
                break;
            case 33 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:228: DOUBLE_EQUAL_ARROW
                {
                mDOUBLE_EQUAL_ARROW(); 

                }
                break;
            case 34 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:247: DOUBLE_DOT
                {
                mDOUBLE_DOT(); 

                }
                break;
            case 35 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:258: DOUBLE_COLON
                {
                mDOUBLE_COLON(); 

                }
                break;
            case 36 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:271: LBRACE
                {
                mLBRACE(); 

                }
                break;
            case 37 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:278: RBRACE
                {
                mRBRACE(); 

                }
                break;
            case 38 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:285: LBRACKET
                {
                mLBRACKET(); 

                }
                break;
            case 39 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:294: RBRACKET
                {
                mRBRACKET(); 

                }
                break;
            case 40 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:303: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 41 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:310: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 42 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:317: CARET
                {
                mCARET(); 

                }
                break;
            case 43 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:323: DIV
                {
                mDIV(); 

                }
                break;
            case 44 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:327: MINUS
                {
                mMINUS(); 

                }
                break;
            case 45 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:333: PLUS
                {
                mPLUS(); 

                }
                break;
            case 46 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:338: TIMES
                {
                mTIMES(); 

                }
                break;
            case 47 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:344: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 48 :
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\net\\sf\\graphiti\\grammar\\Cal.g:1:354: SHARP
                {
                mSHARP(); 

                }
                break;

        }

    }


    protected DFA15 dfa15 = new DFA15(this);
    protected DFA22 dfa22 = new DFA22(this);
    static final String DFA15_eotS =
        "\5\uffff";
    static final String DFA15_eofS =
        "\5\uffff";
    static final String DFA15_minS =
        "\2\56\3\uffff";
    static final String DFA15_maxS =
        "\1\71\1\145\3\uffff";
    static final String DFA15_acceptS =
        "\2\uffff\1\2\1\3\1\1";
    static final String DFA15_specialS =
        "\5\uffff}>";
    static final String[] DFA15_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\4\1\uffff\12\1\13\uffff\1\3\37\uffff\1\3",
            "",
            "",
            ""
    };

    static final short[] DFA15_eot = DFA.unpackEncodedString(DFA15_eotS);
    static final short[] DFA15_eof = DFA.unpackEncodedString(DFA15_eofS);
    static final char[] DFA15_min = DFA.unpackEncodedStringToUnsignedChars(DFA15_minS);
    static final char[] DFA15_max = DFA.unpackEncodedStringToUnsignedChars(DFA15_maxS);
    static final short[] DFA15_accept = DFA.unpackEncodedString(DFA15_acceptS);
    static final short[] DFA15_special = DFA.unpackEncodedString(DFA15_specialS);
    static final short[][] DFA15_transition;

    static {
        int numStates = DFA15_transitionS.length;
        DFA15_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA15_transition[i] = DFA.unpackEncodedString(DFA15_transitionS[i]);
        }
    }

    class DFA15 extends DFA {

        public DFA15(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 15;
            this.eot = DFA15_eot;
            this.eof = DFA15_eof;
            this.min = DFA15_min;
            this.max = DFA15_max;
            this.accept = DFA15_accept;
            this.special = DFA15_special;
            this.transition = DFA15_transition;
        }
        public String getDescription() {
            return "242:13: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? | ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ ) )";
        }
    }
    static final String DFA22_eotS =
        "\1\uffff\11\12\1\uffff\1\57\1\61\1\63\1\uffff\1\66\1\uffff\1\70"+
        "\1\72\1\74\1\uffff\1\77\14\uffff\13\12\23\uffff\1\115\1\12\1\117"+
        "\5\12\1\125\1\12\1\127\2\12\1\uffff\1\12\1\uffff\5\12\1\uffff\1"+
        "\12\1\uffff\1\141\1\12\1\143\2\12\1\146\3\12\1\uffff\1\152\1\uffff"+
        "\1\12\1\154\1\uffff\3\12\1\uffff\1\12\1\uffff\1\161\1\162\1\12\1"+
        "\164\2\uffff\1\12\1\uffff\1\166\1\uffff";
    static final String DFA22_eofS =
        "\167\uffff";
    static final String DFA22_minS =
        "\1\11\1\143\1\156\1\155\1\165\1\145\1\164\1\141\1\162\1\141\1\uffff"+
        "\1\55\2\56\1\uffff\1\52\1\uffff\3\75\1\uffff\1\72\14\uffff\1\154"+
        "\1\164\1\144\1\160\1\154\2\164\2\162\1\165\1\154\23\uffff\1\44\1"+
        "\157\1\44\1\151\1\157\1\164\1\141\1\167\1\44\1\165\1\44\1\145\1"+
        "\163\1\uffff\1\162\1\uffff\1\164\1\162\1\151\1\142\1\157\1\uffff"+
        "\1\143\1\uffff\1\44\1\145\1\44\1\151\1\164\1\44\1\154\1\162\1\164"+
        "\1\uffff\1\44\1\uffff\1\145\1\44\1\uffff\1\145\1\153\1\165\1\uffff"+
        "\1\163\1\uffff\2\44\1\162\1\44\2\uffff\1\145\1\uffff\1\44\1\uffff";
    static final String DFA22_maxS =
        "\1\175\1\154\1\156\1\155\1\165\1\157\1\164\1\141\1\162\1\141\1"+
        "\uffff\1\76\1\145\1\71\1\uffff\1\57\1\uffff\3\75\1\uffff\1\75\14"+
        "\uffff\1\154\2\164\1\160\3\164\2\162\1\165\1\154\23\uffff\1\172"+
        "\1\157\1\172\1\151\1\157\1\164\1\141\1\167\1\172\1\165\1\172\1\145"+
        "\1\163\1\uffff\1\162\1\uffff\1\164\1\162\1\151\1\142\1\157\1\uffff"+
        "\1\143\1\uffff\1\172\1\145\1\172\1\151\1\164\1\172\1\154\1\162\1"+
        "\164\1\uffff\1\172\1\uffff\1\145\1\172\1\uffff\1\145\1\153\1\165"+
        "\1\uffff\1\163\1\uffff\2\172\1\162\1\172\2\uffff\1\145\1\uffff\1"+
        "\172\1\uffff";
    static final String DFA22_acceptS =
        "\12\uffff\1\16\3\uffff\1\21\1\uffff\1\24\3\uffff\1\32\1\uffff\1"+
        "\36\1\44\1\45\1\46\1\47\1\50\1\51\1\52\1\55\1\56\1\57\1\60\13\uffff"+
        "\1\33\1\40\1\54\1\17\1\20\1\42\1\37\1\22\1\23\1\53\1\41\1\25\1\26"+
        "\1\27\1\30\1\31\1\35\1\43\1\34\15\uffff\1\1\1\uffff\1\3\5\uffff"+
        "\1\12\1\uffff\1\13\11\uffff\1\14\1\uffff\1\2\2\uffff\1\6\3\uffff"+
        "\1\15\1\uffff\1\5\4\uffff\1\7\1\10\1\uffff\1\4\1\uffff\1\11";
    static final String DFA22_specialS =
        "\167\uffff}>";
    static final String[] DFA22_transitionS = {
            "\2\20\1\uffff\2\20\22\uffff\1\20\1\24\1\16\1\41\1\12\3\uffff"+
            "\1\33\1\34\1\37\1\36\1\26\1\13\1\15\1\17\12\14\1\25\1\40\1\23"+
            "\1\21\1\22\2\uffff\32\12\1\31\1\uffff\1\32\1\35\1\12\1\uffff"+
            "\1\1\3\12\1\2\1\11\2\12\1\3\3\12\1\4\1\5\4\12\1\6\1\10\1\12"+
            "\1\7\4\12\1\27\1\uffff\1\30",
            "\1\43\10\uffff\1\42",
            "\1\44",
            "\1\45",
            "\1\46",
            "\1\47\11\uffff\1\50",
            "\1\51",
            "\1\52",
            "\1\53",
            "\1\54",
            "",
            "\1\56\1\60\1\uffff\12\14\4\uffff\1\55",
            "\1\60\1\uffff\12\14\13\uffff\1\60\37\uffff\1\60",
            "\1\62\1\uffff\12\60",
            "",
            "\1\65\4\uffff\1\64",
            "",
            "\1\67",
            "\1\71",
            "\1\73",
            "",
            "\1\76\2\uffff\1\75",
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
            "\1\100",
            "\1\101",
            "\1\102\17\uffff\1\103",
            "\1\104",
            "\1\105\7\uffff\1\106",
            "\1\107",
            "\1\110",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
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
            "",
            "",
            "\1\12\13\uffff\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32"+
            "\12",
            "\1\116",
            "\1\12\13\uffff\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32"+
            "\12",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\12\13\uffff\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32"+
            "\12",
            "\1\126",
            "\1\12\13\uffff\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32"+
            "\12",
            "\1\130",
            "\1\131",
            "",
            "\1\132",
            "",
            "\1\133",
            "\1\134",
            "\1\135",
            "\1\136",
            "\1\137",
            "",
            "\1\140",
            "",
            "\1\12\13\uffff\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32"+
            "\12",
            "\1\142",
            "\1\12\13\uffff\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32"+
            "\12",
            "\1\144",
            "\1\145",
            "\1\12\13\uffff\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32"+
            "\12",
            "\1\147",
            "\1\150",
            "\1\151",
            "",
            "\1\12\13\uffff\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32"+
            "\12",
            "",
            "\1\153",
            "\1\12\13\uffff\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32"+
            "\12",
            "",
            "\1\155",
            "\1\156",
            "\1\157",
            "",
            "\1\160",
            "",
            "\1\12\13\uffff\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32"+
            "\12",
            "\1\12\13\uffff\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32"+
            "\12",
            "\1\163",
            "\1\12\13\uffff\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32"+
            "\12",
            "",
            "",
            "\1\165",
            "",
            "\1\12\13\uffff\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32"+
            "\12",
            ""
    };

    static final short[] DFA22_eot = DFA.unpackEncodedString(DFA22_eotS);
    static final short[] DFA22_eof = DFA.unpackEncodedString(DFA22_eofS);
    static final char[] DFA22_min = DFA.unpackEncodedStringToUnsignedChars(DFA22_minS);
    static final char[] DFA22_max = DFA.unpackEncodedStringToUnsignedChars(DFA22_maxS);
    static final short[] DFA22_accept = DFA.unpackEncodedString(DFA22_acceptS);
    static final short[] DFA22_special = DFA.unpackEncodedString(DFA22_specialS);
    static final short[][] DFA22_transition;

    static {
        int numStates = DFA22_transitionS.length;
        DFA22_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA22_transition[i] = DFA.unpackEncodedString(DFA22_transitionS[i]);
        }
    }

    class DFA22 extends DFA {

        public DFA22(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 22;
            this.eot = DFA22_eot;
            this.eof = DFA22_eof;
            this.min = DFA22_min;
            this.max = DFA22_max;
            this.accept = DFA22_accept;
            this.special = DFA22_special;
            this.transition = DFA22_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( ALL | ACTOR | END | ENTITIES | IMPORT | MULTI | MUTABLE | NETWORK | STRUCTURE | NOT | VAR | TRUE | FALSE | ID | FLOAT | INTEGER | STRING | LINE_COMMENT | MULTI_LINE_COMMENT | WHITESPACE | EQ | GE | GT | LE | LT | NE | ARROW | COLON | COLON_EQUAL | COMMA | DOT | DOUBLE_DASH_ARROW | DOUBLE_EQUAL_ARROW | DOUBLE_DOT | DOUBLE_COLON | LBRACE | RBRACE | LBRACKET | RBRACKET | LPAREN | RPAREN | CARET | DIV | MINUS | PLUS | TIMES | SEMICOLON | SHARP );";
        }
    }
 

}