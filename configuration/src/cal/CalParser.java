// $ANTLR 3.1.2 D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g 2009-03-30 15:25:19

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class CalParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BinOp", "Boolean", "Expression", "Integer", "Minus", "Not", "String", "UnOp", "Var", "Parameter", "Type", "TypeAttr", "ExprAttr", "TypePar", "ACTOR", "ID", "LBRACKET", "RBRACKET", "LPAREN", "RPAREN", "DOUBLE_EQUAL_ARROW", "COLON", "ALL", "IMPORT", "MULTI", "ARROW", "CARET", "COLON_EQUAL", "COMMA", "DIV", "DOT", "DOUBLE_DASH_ARROW", "DOUBLE_DOT", "DOUBLE_COLON", "EQ", "GE", "GT", "LBRACE", "LE", "LT", "MINUS", "NUMBER", "PLUS", "RBRACE", "SEMICOLON", "SHARP", "STRING", "TIMES", "NOT", "TRUE", "FALSE", "LINE_COMMENT", "MULTI_LINE_COMMENT", "WHITESPACE", "NE"
    };
    public static final int LT=43;
    public static final int LBRACE=41;
    public static final int MULTI=28;
    public static final int NOT=52;
    public static final int ID=19;
    public static final int EOF=-1;
    public static final int DOUBLE_DOT=36;
    public static final int LPAREN=22;
    public static final int LBRACKET=20;
    public static final int RPAREN=23;
    public static final int IMPORT=27;
    public static final int COLON_EQUAL=31;
    public static final int COMMA=32;
    public static final int ALL=26;
    public static final int CARET=30;
    public static final int TypeAttr=15;
    public static final int PLUS=46;
    public static final int String=10;
    public static final int RBRACKET=21;
    public static final int EQ=38;
    public static final int DOT=34;
    public static final int NE=58;
    public static final int DOUBLE_EQUAL_ARROW=24;
    public static final int DOUBLE_DASH_ARROW=35;
    public static final int GE=39;
    public static final int SHARP=49;
    public static final int RBRACE=47;
    public static final int Type=14;
    public static final int LINE_COMMENT=55;
    public static final int NUMBER=45;
    public static final int WHITESPACE=57;
    public static final int SEMICOLON=48;
    public static final int MINUS=44;
    public static final int TRUE=53;
    public static final int Expression=6;
    public static final int MULTI_LINE_COMMENT=56;
    public static final int Parameter=13;
    public static final int TypePar=17;
    public static final int COLON=25;
    public static final int UnOp=11;
    public static final int Minus=8;
    public static final int DOUBLE_COLON=37;
    public static final int Boolean=5;
    public static final int ACTOR=18;
    public static final int ExprAttr=16;
    public static final int Not=9;
    public static final int ARROW=29;
    public static final int GT=40;
    public static final int DIV=33;
    public static final int TIMES=51;
    public static final int BinOp=4;
    public static final int FALSE=54;
    public static final int Var=12;
    public static final int Integer=7;
    public static final int LE=42;
    public static final int STRING=50;

    // delegates
    // delegators


        public CalParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public CalParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return CalParser.tokenNames; }
    public String getGrammarFileName() { return "D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g"; }


    public static class actor_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "actor"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:55:1: actor : ( oneImport )* ACTOR ID ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN ( portDecls )? DOUBLE_EQUAL_ARROW ( portDecls )? COLON ( ignore )* EOF ;
    public final CalParser.actor_return actor() throws RecognitionException {
        CalParser.actor_return retval = new CalParser.actor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ACTOR2=null;
        Token ID3=null;
        Token LBRACKET4=null;
        Token RBRACKET6=null;
        Token LPAREN7=null;
        Token RPAREN9=null;
        Token DOUBLE_EQUAL_ARROW11=null;
        Token COLON13=null;
        Token EOF15=null;
        CalParser.oneImport_return oneImport1 = null;

        CalParser.typePars_return typePars5 = null;

        CalParser.parameters_return parameters8 = null;

        CalParser.portDecls_return portDecls10 = null;

        CalParser.portDecls_return portDecls12 = null;

        CalParser.ignore_return ignore14 = null;


        Object ACTOR2_tree=null;
        Object ID3_tree=null;
        Object LBRACKET4_tree=null;
        Object RBRACKET6_tree=null;
        Object LPAREN7_tree=null;
        Object RPAREN9_tree=null;
        Object DOUBLE_EQUAL_ARROW11_tree=null;
        Object COLON13_tree=null;
        Object EOF15_tree=null;

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:55:6: ( ( oneImport )* ACTOR ID ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN ( portDecls )? DOUBLE_EQUAL_ARROW ( portDecls )? COLON ( ignore )* EOF )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:55:8: ( oneImport )* ACTOR ID ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN ( portDecls )? DOUBLE_EQUAL_ARROW ( portDecls )? COLON ( ignore )* EOF
            {
            root_0 = (Object)adaptor.nil();

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:55:8: ( oneImport )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==IMPORT) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:55:8: oneImport
            	    {
            	    pushFollow(FOLLOW_oneImport_in_actor117);
            	    oneImport1=oneImport();

            	    state._fsp--;

            	    adaptor.addChild(root_0, oneImport1.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            ACTOR2=(Token)match(input,ACTOR,FOLLOW_ACTOR_in_actor120); 
            ACTOR2_tree = (Object)adaptor.create(ACTOR2);
            adaptor.addChild(root_0, ACTOR2_tree);

            ID3=(Token)match(input,ID,FOLLOW_ID_in_actor122); 
            ID3_tree = (Object)adaptor.create(ID3);
            adaptor.addChild(root_0, ID3_tree);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:56:3: ( LBRACKET ( typePars )? RBRACKET )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==LBRACKET) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:56:4: LBRACKET ( typePars )? RBRACKET
                    {
                    LBRACKET4=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_actor127); 
                    LBRACKET4_tree = (Object)adaptor.create(LBRACKET4);
                    adaptor.addChild(root_0, LBRACKET4_tree);

                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:56:13: ( typePars )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==ID) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:56:13: typePars
                            {
                            pushFollow(FOLLOW_typePars_in_actor129);
                            typePars5=typePars();

                            state._fsp--;

                            adaptor.addChild(root_0, typePars5.getTree());

                            }
                            break;

                    }

                    RBRACKET6=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_actor132); 
                    RBRACKET6_tree = (Object)adaptor.create(RBRACKET6);
                    adaptor.addChild(root_0, RBRACKET6_tree);


                    }
                    break;

            }

            LPAREN7=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_actor138); 
            LPAREN7_tree = (Object)adaptor.create(LPAREN7);
            adaptor.addChild(root_0, LPAREN7_tree);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:57:10: ( parameters )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==ID) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:57:10: parameters
                    {
                    pushFollow(FOLLOW_parameters_in_actor140);
                    parameters8=parameters();

                    state._fsp--;

                    adaptor.addChild(root_0, parameters8.getTree());

                    }
                    break;

            }

            RPAREN9=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_actor143); 
            RPAREN9_tree = (Object)adaptor.create(RPAREN9);
            adaptor.addChild(root_0, RPAREN9_tree);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:58:3: ( portDecls )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==ID||LA5_0==MULTI) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:58:3: portDecls
                    {
                    pushFollow(FOLLOW_portDecls_in_actor147);
                    portDecls10=portDecls();

                    state._fsp--;

                    adaptor.addChild(root_0, portDecls10.getTree());

                    }
                    break;

            }

            DOUBLE_EQUAL_ARROW11=(Token)match(input,DOUBLE_EQUAL_ARROW,FOLLOW_DOUBLE_EQUAL_ARROW_in_actor150); 
            DOUBLE_EQUAL_ARROW11_tree = (Object)adaptor.create(DOUBLE_EQUAL_ARROW11);
            adaptor.addChild(root_0, DOUBLE_EQUAL_ARROW11_tree);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:58:33: ( portDecls )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==ID||LA6_0==MULTI) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:58:33: portDecls
                    {
                    pushFollow(FOLLOW_portDecls_in_actor152);
                    portDecls12=portDecls();

                    state._fsp--;

                    adaptor.addChild(root_0, portDecls12.getTree());

                    }
                    break;

            }

            COLON13=(Token)match(input,COLON,FOLLOW_COLON_in_actor155); 
            COLON13_tree = (Object)adaptor.create(COLON13);
            adaptor.addChild(root_0, COLON13_tree);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:58:50: ( ignore )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>=ACTOR && LA7_0<=TIMES)) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:58:50: ignore
            	    {
            	    pushFollow(FOLLOW_ignore_in_actor157);
            	    ignore14=ignore();

            	    state._fsp--;

            	    adaptor.addChild(root_0, ignore14.getTree());

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            EOF15=(Token)match(input,EOF,FOLLOW_EOF_in_actor160); 
            EOF15_tree = (Object)adaptor.create(EOF15);
            adaptor.addChild(root_0, EOF15_tree);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "actor"

    public static class ignore_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ignore"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:60:1: ignore : ( ALL | ACTOR | IMPORT | MULTI | ARROW | CARET | COLON | COLON_EQUAL | COMMA | DIV | DOT | DOUBLE_DASH_ARROW | DOUBLE_EQUAL_ARROW | DOUBLE_DOT | DOUBLE_COLON | EQ | GE | GT | ID | LBRACE | LBRACKET | LPAREN | LE | LT | MINUS | NUMBER | PLUS | RBRACE | RBRACKET | RPAREN | SEMICOLON | SHARP | STRING | TIMES );
    public final CalParser.ignore_return ignore() throws RecognitionException {
        CalParser.ignore_return retval = new CalParser.ignore_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set16=null;

        Object set16_tree=null;

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:60:7: ( ALL | ACTOR | IMPORT | MULTI | ARROW | CARET | COLON | COLON_EQUAL | COMMA | DIV | DOT | DOUBLE_DASH_ARROW | DOUBLE_EQUAL_ARROW | DOUBLE_DOT | DOUBLE_COLON | EQ | GE | GT | ID | LBRACE | LBRACKET | LPAREN | LE | LT | MINUS | NUMBER | PLUS | RBRACE | RBRACKET | RPAREN | SEMICOLON | SHARP | STRING | TIMES )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:
            {
            root_0 = (Object)adaptor.nil();

            set16=(Token)input.LT(1);
            if ( (input.LA(1)>=ACTOR && input.LA(1)<=TIMES) ) {
                input.consume();
                adaptor.addChild(root_0, (Object)adaptor.create(set16));
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ignore"

    public static class oneImport_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "oneImport"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:98:1: oneImport : IMPORT importRest SEMICOLON ;
    public final CalParser.oneImport_return oneImport() throws RecognitionException {
        CalParser.oneImport_return retval = new CalParser.oneImport_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IMPORT17=null;
        Token SEMICOLON19=null;
        CalParser.importRest_return importRest18 = null;


        Object IMPORT17_tree=null;
        Object SEMICOLON19_tree=null;

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:98:10: ( IMPORT importRest SEMICOLON )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:98:12: IMPORT importRest SEMICOLON
            {
            root_0 = (Object)adaptor.nil();

            IMPORT17=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_oneImport310); 
            IMPORT17_tree = (Object)adaptor.create(IMPORT17);
            adaptor.addChild(root_0, IMPORT17_tree);

            pushFollow(FOLLOW_importRest_in_oneImport312);
            importRest18=importRest();

            state._fsp--;

            adaptor.addChild(root_0, importRest18.getTree());
            SEMICOLON19=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_oneImport314); 
            SEMICOLON19_tree = (Object)adaptor.create(SEMICOLON19);
            adaptor.addChild(root_0, SEMICOLON19_tree);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "oneImport"

    public static class importRest_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "importRest"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:100:1: importRest : ( ALL qualifiedId | qualifiedId ( EQ ID )? );
    public final CalParser.importRest_return importRest() throws RecognitionException {
        CalParser.importRest_return retval = new CalParser.importRest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ALL20=null;
        Token EQ23=null;
        Token ID24=null;
        CalParser.qualifiedId_return qualifiedId21 = null;

        CalParser.qualifiedId_return qualifiedId22 = null;


        Object ALL20_tree=null;
        Object EQ23_tree=null;
        Object ID24_tree=null;

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:100:11: ( ALL qualifiedId | qualifiedId ( EQ ID )? )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==ALL) ) {
                alt9=1;
            }
            else if ( (LA9_0==ID) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:100:13: ALL qualifiedId
                    {
                    root_0 = (Object)adaptor.nil();

                    ALL20=(Token)match(input,ALL,FOLLOW_ALL_in_importRest322); 
                    ALL20_tree = (Object)adaptor.create(ALL20);
                    adaptor.addChild(root_0, ALL20_tree);

                    pushFollow(FOLLOW_qualifiedId_in_importRest324);
                    qualifiedId21=qualifiedId();

                    state._fsp--;

                    adaptor.addChild(root_0, qualifiedId21.getTree());

                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:101:5: qualifiedId ( EQ ID )?
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_qualifiedId_in_importRest330);
                    qualifiedId22=qualifiedId();

                    state._fsp--;

                    adaptor.addChild(root_0, qualifiedId22.getTree());
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:101:17: ( EQ ID )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==EQ) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:101:18: EQ ID
                            {
                            EQ23=(Token)match(input,EQ,FOLLOW_EQ_in_importRest333); 
                            EQ23_tree = (Object)adaptor.create(EQ23);
                            adaptor.addChild(root_0, EQ23_tree);

                            ID24=(Token)match(input,ID,FOLLOW_ID_in_importRest335); 
                            ID24_tree = (Object)adaptor.create(ID24);
                            adaptor.addChild(root_0, ID24_tree);


                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "importRest"

    public static class qualifiedId_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "qualifiedId"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:103:1: qualifiedId : ID ( DOT ID )+ ;
    public final CalParser.qualifiedId_return qualifiedId() throws RecognitionException {
        CalParser.qualifiedId_return retval = new CalParser.qualifiedId_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID25=null;
        Token DOT26=null;
        Token ID27=null;

        Object ID25_tree=null;
        Object DOT26_tree=null;
        Object ID27_tree=null;

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:103:12: ( ID ( DOT ID )+ )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:103:14: ID ( DOT ID )+
            {
            root_0 = (Object)adaptor.nil();

            ID25=(Token)match(input,ID,FOLLOW_ID_in_qualifiedId345); 
            ID25_tree = (Object)adaptor.create(ID25);
            adaptor.addChild(root_0, ID25_tree);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:103:17: ( DOT ID )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==DOT) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:103:18: DOT ID
            	    {
            	    DOT26=(Token)match(input,DOT,FOLLOW_DOT_in_qualifiedId348); 
            	    DOT26_tree = (Object)adaptor.create(DOT26);
            	    adaptor.addChild(root_0, DOT26_tree);

            	    ID27=(Token)match(input,ID,FOLLOW_ID_in_qualifiedId350); 
            	    ID27_tree = (Object)adaptor.create(ID27);
            	    adaptor.addChild(root_0, ID27_tree);


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

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "qualifiedId"

    public static class parameter_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parameter"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:108:1: parameter : typeAndId ( EQ expression )? ;
    public final CalParser.parameter_return parameter() throws RecognitionException {
        CalParser.parameter_return retval = new CalParser.parameter_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EQ29=null;
        CalParser.typeAndId_return typeAndId28 = null;

        CalParser.expression_return expression30 = null;


        Object EQ29_tree=null;

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:108:10: ( typeAndId ( EQ expression )? )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:108:12: typeAndId ( EQ expression )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_typeAndId_in_parameter363);
            typeAndId28=typeAndId();

            state._fsp--;

            adaptor.addChild(root_0, typeAndId28.getTree());
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:108:22: ( EQ expression )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==EQ) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:108:23: EQ expression
                    {
                    EQ29=(Token)match(input,EQ,FOLLOW_EQ_in_parameter366); 
                    EQ29_tree = (Object)adaptor.create(EQ29);
                    adaptor.addChild(root_0, EQ29_tree);

                    pushFollow(FOLLOW_expression_in_parameter368);
                    expression30=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression30.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "parameter"

    public static class parameters_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parameters"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:110:1: parameters : parameter ( COMMA parameter )* ;
    public final CalParser.parameters_return parameters() throws RecognitionException {
        CalParser.parameters_return retval = new CalParser.parameters_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA32=null;
        CalParser.parameter_return parameter31 = null;

        CalParser.parameter_return parameter33 = null;


        Object COMMA32_tree=null;

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:110:11: ( parameter ( COMMA parameter )* )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:110:13: parameter ( COMMA parameter )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_parameter_in_parameters378);
            parameter31=parameter();

            state._fsp--;

            adaptor.addChild(root_0, parameter31.getTree());
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:110:23: ( COMMA parameter )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==COMMA) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:110:24: COMMA parameter
            	    {
            	    COMMA32=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameters381); 
            	    COMMA32_tree = (Object)adaptor.create(COMMA32);
            	    adaptor.addChild(root_0, COMMA32_tree);

            	    pushFollow(FOLLOW_parameter_in_parameters383);
            	    parameter33=parameter();

            	    state._fsp--;

            	    adaptor.addChild(root_0, parameter33.getTree());

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "parameters"

    public static class portDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "portDecl"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:115:1: portDecl : ( MULTI )? typeAndId ;
    public final CalParser.portDecl_return portDecl() throws RecognitionException {
        CalParser.portDecl_return retval = new CalParser.portDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MULTI34=null;
        CalParser.typeAndId_return typeAndId35 = null;


        Object MULTI34_tree=null;

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:115:9: ( ( MULTI )? typeAndId )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:115:11: ( MULTI )? typeAndId
            {
            root_0 = (Object)adaptor.nil();

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:115:11: ( MULTI )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==MULTI) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:115:11: MULTI
                    {
                    MULTI34=(Token)match(input,MULTI,FOLLOW_MULTI_in_portDecl396); 
                    MULTI34_tree = (Object)adaptor.create(MULTI34);
                    adaptor.addChild(root_0, MULTI34_tree);


                    }
                    break;

            }

            pushFollow(FOLLOW_typeAndId_in_portDecl399);
            typeAndId35=typeAndId();

            state._fsp--;

            adaptor.addChild(root_0, typeAndId35.getTree());

            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "portDecl"

    public static class portDecls_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "portDecls"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:117:1: portDecls : portDecl ( COMMA portDecl )* ;
    public final CalParser.portDecls_return portDecls() throws RecognitionException {
        CalParser.portDecls_return retval = new CalParser.portDecls_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA37=null;
        CalParser.portDecl_return portDecl36 = null;

        CalParser.portDecl_return portDecl38 = null;


        Object COMMA37_tree=null;

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:117:10: ( portDecl ( COMMA portDecl )* )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:117:12: portDecl ( COMMA portDecl )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_portDecl_in_portDecls406);
            portDecl36=portDecl();

            state._fsp--;

            adaptor.addChild(root_0, portDecl36.getTree());
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:117:21: ( COMMA portDecl )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==COMMA) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:117:22: COMMA portDecl
            	    {
            	    COMMA37=(Token)match(input,COMMA,FOLLOW_COMMA_in_portDecls409); 
            	    COMMA37_tree = (Object)adaptor.create(COMMA37);
            	    adaptor.addChild(root_0, COMMA37_tree);

            	    pushFollow(FOLLOW_portDecl_in_portDecls411);
            	    portDecl38=portDecl();

            	    state._fsp--;

            	    adaptor.addChild(root_0, portDecl38.getTree());

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "portDecls"

    public static class mainParameter_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "mainParameter"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:122:1: mainParameter : typeAndId EOF -> ^( Parameter typeAndId ) ;
    public final CalParser.mainParameter_return mainParameter() throws RecognitionException {
        CalParser.mainParameter_return retval = new CalParser.mainParameter_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF40=null;
        CalParser.typeAndId_return typeAndId39 = null;


        Object EOF40_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_typeAndId=new RewriteRuleSubtreeStream(adaptor,"rule typeAndId");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:122:14: ( typeAndId EOF -> ^( Parameter typeAndId ) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:122:16: typeAndId EOF
            {
            pushFollow(FOLLOW_typeAndId_in_mainParameter424);
            typeAndId39=typeAndId();

            state._fsp--;

            stream_typeAndId.add(typeAndId39.getTree());
            EOF40=(Token)match(input,EOF,FOLLOW_EOF_in_mainParameter426);  
            stream_EOF.add(EOF40);



            // AST REWRITE
            // elements: typeAndId
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 122:30: -> ^( Parameter typeAndId )
            {
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:122:33: ^( Parameter typeAndId )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Parameter, "Parameter"), root_1);

                adaptor.addChild(root_1, stream_typeAndId.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "mainParameter"

    public static class typeAndId_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typeAndId"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:124:1: typeAndId : typeName= ID ( ( typeRest )? varName= ID -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName) | -> ^( Var $typeName) ) ;
    public final CalParser.typeAndId_return typeAndId() throws RecognitionException {
        CalParser.typeAndId_return retval = new CalParser.typeAndId_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token typeName=null;
        Token varName=null;
        CalParser.typeRest_return typeRest41 = null;


        Object typeName_tree=null;
        Object varName_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_typeRest=new RewriteRuleSubtreeStream(adaptor,"rule typeRest");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:124:10: (typeName= ID ( ( typeRest )? varName= ID -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName) | -> ^( Var $typeName) ) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:124:12: typeName= ID ( ( typeRest )? varName= ID -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName) | -> ^( Var $typeName) )
            {
            typeName=(Token)match(input,ID,FOLLOW_ID_in_typeAndId443);  
            stream_ID.add(typeName);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:125:3: ( ( typeRest )? varName= ID -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName) | -> ^( Var $typeName) )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( ((LA16_0>=ID && LA16_0<=LBRACKET)||LA16_0==LPAREN) ) {
                alt16=1;
            }
            else if ( (LA16_0==EOF||(LA16_0>=RPAREN && LA16_0<=COLON)||LA16_0==COMMA||LA16_0==EQ) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:125:4: ( typeRest )? varName= ID
                    {
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:125:4: ( typeRest )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==LBRACKET||LA15_0==LPAREN) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:125:4: typeRest
                            {
                            pushFollow(FOLLOW_typeRest_in_typeAndId448);
                            typeRest41=typeRest();

                            state._fsp--;

                            stream_typeRest.add(typeRest41.getTree());

                            }
                            break;

                    }

                    varName=(Token)match(input,ID,FOLLOW_ID_in_typeAndId453);  
                    stream_ID.add(varName);



                    // AST REWRITE
                    // elements: varName, typeRest, typeName
                    // token labels: typeName, varName
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_typeName=new RewriteRuleTokenStream(adaptor,"token typeName",typeName);
                    RewriteRuleTokenStream stream_varName=new RewriteRuleTokenStream(adaptor,"token varName",varName);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 125:25: -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName)
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:125:28: ^( Type ^( Var $typeName) ( typeRest )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Type, "Type"), root_1);

                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:125:35: ^( Var $typeName)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_2);

                        adaptor.addChild(root_2, stream_typeName.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:125:52: ( typeRest )?
                        if ( stream_typeRest.hasNext() ) {
                            adaptor.addChild(root_1, stream_typeRest.nextTree());

                        }
                        stream_typeRest.reset();

                        adaptor.addChild(root_0, root_1);
                        }
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:125:63: ^( Var $varName)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_1);

                        adaptor.addChild(root_1, stream_varName.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:126:5: 
                    {

                    // AST REWRITE
                    // elements: typeName
                    // token labels: typeName
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_typeName=new RewriteRuleTokenStream(adaptor,"token typeName",typeName);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 126:5: -> ^( Var $typeName)
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:126:8: ^( Var $typeName)
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_1);

                        adaptor.addChild(root_1, stream_typeName.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "typeAndId"

    public static class type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "type"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:128:1: type : ID ( typeRest )? -> ^( Type ^( Var ID ) ( typeRest )? ) ;
    public final CalParser.type_return type() throws RecognitionException {
        CalParser.type_return retval = new CalParser.type_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID42=null;
        CalParser.typeRest_return typeRest43 = null;


        Object ID42_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_typeRest=new RewriteRuleSubtreeStream(adaptor,"rule typeRest");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:128:5: ( ID ( typeRest )? -> ^( Type ^( Var ID ) ( typeRest )? ) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:128:7: ID ( typeRest )?
            {
            ID42=(Token)match(input,ID,FOLLOW_ID_in_type497);  
            stream_ID.add(ID42);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:128:10: ( typeRest )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==LBRACKET||LA17_0==LPAREN) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:128:10: typeRest
                    {
                    pushFollow(FOLLOW_typeRest_in_type499);
                    typeRest43=typeRest();

                    state._fsp--;

                    stream_typeRest.add(typeRest43.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: ID, typeRest
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 128:20: -> ^( Type ^( Var ID ) ( typeRest )? )
            {
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:128:23: ^( Type ^( Var ID ) ( typeRest )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Type, "Type"), root_1);

                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:128:30: ^( Var ID )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_2);

                adaptor.addChild(root_2, stream_ID.nextNode());

                adaptor.addChild(root_1, root_2);
                }
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:128:40: ( typeRest )?
                if ( stream_typeRest.hasNext() ) {
                    adaptor.addChild(root_1, stream_typeRest.nextTree());

                }
                stream_typeRest.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "type"

    public static class typeRest_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typeRest"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:130:1: typeRest : ( LBRACKET ( typePars )? RBRACKET -> ( typePars )? | LPAREN ( typeAttrs )? RPAREN -> ( typeAttrs )? );
    public final CalParser.typeRest_return typeRest() throws RecognitionException {
        CalParser.typeRest_return retval = new CalParser.typeRest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LBRACKET44=null;
        Token RBRACKET46=null;
        Token LPAREN47=null;
        Token RPAREN49=null;
        CalParser.typePars_return typePars45 = null;

        CalParser.typeAttrs_return typeAttrs48 = null;


        Object LBRACKET44_tree=null;
        Object RBRACKET46_tree=null;
        Object LPAREN47_tree=null;
        Object RPAREN49_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_RBRACKET=new RewriteRuleTokenStream(adaptor,"token RBRACKET");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_typePars=new RewriteRuleSubtreeStream(adaptor,"rule typePars");
        RewriteRuleSubtreeStream stream_typeAttrs=new RewriteRuleSubtreeStream(adaptor,"rule typeAttrs");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:130:9: ( LBRACKET ( typePars )? RBRACKET -> ( typePars )? | LPAREN ( typeAttrs )? RPAREN -> ( typeAttrs )? )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==LBRACKET) ) {
                alt20=1;
            }
            else if ( (LA20_0==LPAREN) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:130:11: LBRACKET ( typePars )? RBRACKET
                    {
                    LBRACKET44=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_typeRest522);  
                    stream_LBRACKET.add(LBRACKET44);

                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:130:20: ( typePars )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==ID) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:130:20: typePars
                            {
                            pushFollow(FOLLOW_typePars_in_typeRest524);
                            typePars45=typePars();

                            state._fsp--;

                            stream_typePars.add(typePars45.getTree());

                            }
                            break;

                    }

                    RBRACKET46=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_typeRest527);  
                    stream_RBRACKET.add(RBRACKET46);



                    // AST REWRITE
                    // elements: typePars
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 130:39: -> ( typePars )?
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:130:42: ( typePars )?
                        if ( stream_typePars.hasNext() ) {
                            adaptor.addChild(root_0, stream_typePars.nextTree());

                        }
                        stream_typePars.reset();

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:131:5: LPAREN ( typeAttrs )? RPAREN
                    {
                    LPAREN47=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_typeRest538);  
                    stream_LPAREN.add(LPAREN47);

                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:131:12: ( typeAttrs )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==ID) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:131:12: typeAttrs
                            {
                            pushFollow(FOLLOW_typeAttrs_in_typeRest540);
                            typeAttrs48=typeAttrs();

                            state._fsp--;

                            stream_typeAttrs.add(typeAttrs48.getTree());

                            }
                            break;

                    }

                    RPAREN49=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_typeRest543);  
                    stream_RPAREN.add(RPAREN49);



                    // AST REWRITE
                    // elements: typeAttrs
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 131:30: -> ( typeAttrs )?
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:131:33: ( typeAttrs )?
                        if ( stream_typeAttrs.hasNext() ) {
                            adaptor.addChild(root_0, stream_typeAttrs.nextTree());

                        }
                        stream_typeAttrs.reset();

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "typeRest"

    public static class typeAttrs_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typeAttrs"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:133:1: typeAttrs : typeAttr ( COMMA typeAttr )* -> ( typeAttr )+ ;
    public final CalParser.typeAttrs_return typeAttrs() throws RecognitionException {
        CalParser.typeAttrs_return retval = new CalParser.typeAttrs_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA51=null;
        CalParser.typeAttr_return typeAttr50 = null;

        CalParser.typeAttr_return typeAttr52 = null;


        Object COMMA51_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_typeAttr=new RewriteRuleSubtreeStream(adaptor,"rule typeAttr");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:133:10: ( typeAttr ( COMMA typeAttr )* -> ( typeAttr )+ )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:133:12: typeAttr ( COMMA typeAttr )*
            {
            pushFollow(FOLLOW_typeAttr_in_typeAttrs555);
            typeAttr50=typeAttr();

            state._fsp--;

            stream_typeAttr.add(typeAttr50.getTree());
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:133:21: ( COMMA typeAttr )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==COMMA) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:133:22: COMMA typeAttr
            	    {
            	    COMMA51=(Token)match(input,COMMA,FOLLOW_COMMA_in_typeAttrs558);  
            	    stream_COMMA.add(COMMA51);

            	    pushFollow(FOLLOW_typeAttr_in_typeAttrs560);
            	    typeAttr52=typeAttr();

            	    state._fsp--;

            	    stream_typeAttr.add(typeAttr52.getTree());

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);



            // AST REWRITE
            // elements: typeAttr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 133:39: -> ( typeAttr )+
            {
                if ( !(stream_typeAttr.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_typeAttr.hasNext() ) {
                    adaptor.addChild(root_0, stream_typeAttr.nextTree());

                }
                stream_typeAttr.reset();

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "typeAttrs"

    public static class typeAttr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typeAttr"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:135:1: typeAttr : ID typeAttrRest -> typeAttrRest ;
    public final CalParser.typeAttr_return typeAttr() throws RecognitionException {
        CalParser.typeAttr_return retval = new CalParser.typeAttr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID53=null;
        CalParser.typeAttrRest_return typeAttrRest54 = null;


        Object ID53_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_typeAttrRest=new RewriteRuleSubtreeStream(adaptor,"rule typeAttrRest");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:135:9: ( ID typeAttrRest -> typeAttrRest )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:135:11: ID typeAttrRest
            {
            ID53=(Token)match(input,ID,FOLLOW_ID_in_typeAttr574);  
            stream_ID.add(ID53);

            pushFollow(FOLLOW_typeAttrRest_in_typeAttr576);
            typeAttrRest54=typeAttrRest();

            state._fsp--;

            stream_typeAttrRest.add(typeAttrRest54.getTree());


            // AST REWRITE
            // elements: typeAttrRest
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 135:27: -> typeAttrRest
            {
                adaptor.addChild(root_0, stream_typeAttrRest.nextTree());

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "typeAttr"

    public static class typeAttrRest_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typeAttrRest"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:137:1: typeAttrRest : ( COLON type -> ^( TypeAttr type ) | EQ expression -> ^( ExprAttr ^( Expression expression ) ) );
    public final CalParser.typeAttrRest_return typeAttrRest() throws RecognitionException {
        CalParser.typeAttrRest_return retval = new CalParser.typeAttrRest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON55=null;
        Token EQ57=null;
        CalParser.type_return type56 = null;

        CalParser.expression_return expression58 = null;


        Object COLON55_tree=null;
        Object EQ57_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_EQ=new RewriteRuleTokenStream(adaptor,"token EQ");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:137:13: ( COLON type -> ^( TypeAttr type ) | EQ expression -> ^( ExprAttr ^( Expression expression ) ) )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==COLON) ) {
                alt22=1;
            }
            else if ( (LA22_0==EQ) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:137:15: COLON type
                    {
                    COLON55=(Token)match(input,COLON,FOLLOW_COLON_in_typeAttrRest587);  
                    stream_COLON.add(COLON55);

                    pushFollow(FOLLOW_type_in_typeAttrRest589);
                    type56=type();

                    state._fsp--;

                    stream_type.add(type56.getTree());


                    // AST REWRITE
                    // elements: type
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 137:26: -> ^( TypeAttr type )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:137:29: ^( TypeAttr type )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TypeAttr, "TypeAttr"), root_1);

                        adaptor.addChild(root_1, stream_type.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:138:3: EQ expression
                    {
                    EQ57=(Token)match(input,EQ,FOLLOW_EQ_in_typeAttrRest601);  
                    stream_EQ.add(EQ57);

                    pushFollow(FOLLOW_expression_in_typeAttrRest603);
                    expression58=expression();

                    state._fsp--;

                    stream_expression.add(expression58.getTree());


                    // AST REWRITE
                    // elements: expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 138:17: -> ^( ExprAttr ^( Expression expression ) )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:138:20: ^( ExprAttr ^( Expression expression ) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ExprAttr, "ExprAttr"), root_1);

                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:138:31: ^( Expression expression )
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Expression, "Expression"), root_2);

                        adaptor.addChild(root_2, stream_expression.nextTree());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "typeAttrRest"

    public static class typePars_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typePars"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:140:1: typePars : typePar ( COMMA typePar )* -> ( typePar )+ ;
    public final CalParser.typePars_return typePars() throws RecognitionException {
        CalParser.typePars_return retval = new CalParser.typePars_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA60=null;
        CalParser.typePar_return typePar59 = null;

        CalParser.typePar_return typePar61 = null;


        Object COMMA60_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_typePar=new RewriteRuleSubtreeStream(adaptor,"rule typePar");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:140:9: ( typePar ( COMMA typePar )* -> ( typePar )+ )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:140:11: typePar ( COMMA typePar )*
            {
            pushFollow(FOLLOW_typePar_in_typePars622);
            typePar59=typePar();

            state._fsp--;

            stream_typePar.add(typePar59.getTree());
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:140:19: ( COMMA typePar )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==COMMA) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:140:20: COMMA typePar
            	    {
            	    COMMA60=(Token)match(input,COMMA,FOLLOW_COMMA_in_typePars625);  
            	    stream_COMMA.add(COMMA60);

            	    pushFollow(FOLLOW_typePar_in_typePars627);
            	    typePar61=typePar();

            	    state._fsp--;

            	    stream_typePar.add(typePar61.getTree());

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);



            // AST REWRITE
            // elements: typePar
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 140:36: -> ( typePar )+
            {
                if ( !(stream_typePar.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_typePar.hasNext() ) {
                    adaptor.addChild(root_0, stream_typePar.nextTree());

                }
                stream_typePar.reset();

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "typePars"

    public static class typePar_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typePar"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:142:1: typePar : ID ( LT type )? -> ^( TypePar ID ( type )? ) ;
    public final CalParser.typePar_return typePar() throws RecognitionException {
        CalParser.typePar_return retval = new CalParser.typePar_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID62=null;
        Token LT63=null;
        CalParser.type_return type64 = null;


        Object ID62_tree=null;
        Object LT63_tree=null;
        RewriteRuleTokenStream stream_LT=new RewriteRuleTokenStream(adaptor,"token LT");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:142:8: ( ID ( LT type )? -> ^( TypePar ID ( type )? ) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:142:10: ID ( LT type )?
            {
            ID62=(Token)match(input,ID,FOLLOW_ID_in_typePar641);  
            stream_ID.add(ID62);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:142:13: ( LT type )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==LT) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:142:14: LT type
                    {
                    LT63=(Token)match(input,LT,FOLLOW_LT_in_typePar644);  
                    stream_LT.add(LT63);

                    pushFollow(FOLLOW_type_in_typePar646);
                    type64=type();

                    state._fsp--;

                    stream_type.add(type64.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: ID, type
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 142:24: -> ^( TypePar ID ( type )? )
            {
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:142:27: ^( TypePar ID ( type )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TypePar, "TypePar"), root_1);

                adaptor.addChild(root_1, stream_ID.nextNode());
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:142:40: ( type )?
                if ( stream_type.hasNext() ) {
                    adaptor.addChild(root_1, stream_type.nextTree());

                }
                stream_type.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "typePar"

    public static class mainExpression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "mainExpression"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:147:1: mainExpression : expression EOF -> ^( Expression expression ) ;
    public final CalParser.mainExpression_return mainExpression() throws RecognitionException {
        CalParser.mainExpression_return retval = new CalParser.mainExpression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF66=null;
        CalParser.expression_return expression65 = null;


        Object EOF66_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:147:15: ( expression EOF -> ^( Expression expression ) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:147:17: expression EOF
            {
            pushFollow(FOLLOW_expression_in_mainExpression669);
            expression65=expression();

            state._fsp--;

            stream_expression.add(expression65.getTree());
            EOF66=(Token)match(input,EOF,FOLLOW_EOF_in_mainExpression671);  
            stream_EOF.add(EOF66);



            // AST REWRITE
            // elements: expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 147:32: -> ^( Expression expression )
            {
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:147:35: ^( Expression expression )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Expression, "Expression"), root_1);

                adaptor.addChild(root_1, stream_expression.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "mainExpression"

    public static class expression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expression"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:149:1: expression : factor ( binop factor )* ;
    public final CalParser.expression_return expression() throws RecognitionException {
        CalParser.expression_return retval = new CalParser.expression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CalParser.factor_return factor67 = null;

        CalParser.binop_return binop68 = null;

        CalParser.factor_return factor69 = null;



        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:149:11: ( factor ( binop factor )* )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:149:13: factor ( binop factor )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_factor_in_expression686);
            factor67=factor();

            state._fsp--;

            adaptor.addChild(root_0, factor67.getTree());
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:149:20: ( binop factor )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==CARET||LA25_0==DIV||LA25_0==MINUS||LA25_0==PLUS||LA25_0==TIMES) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:149:21: binop factor
            	    {
            	    pushFollow(FOLLOW_binop_in_expression689);
            	    binop68=binop();

            	    state._fsp--;

            	    adaptor.addChild(root_0, binop68.getTree());
            	    pushFollow(FOLLOW_factor_in_expression691);
            	    factor69=factor();

            	    state._fsp--;

            	    adaptor.addChild(root_0, factor69.getTree());

            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expression"

    public static class unop_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "unop"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:151:1: unop : (op= MINUS | op= NOT ) -> ^( UnOp $op) ;
    public final CalParser.unop_return unop() throws RecognitionException {
        CalParser.unop_return retval = new CalParser.unop_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token op=null;

        Object op_tree=null;
        RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
        RewriteRuleTokenStream stream_MINUS=new RewriteRuleTokenStream(adaptor,"token MINUS");

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:151:5: ( (op= MINUS | op= NOT ) -> ^( UnOp $op) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:151:7: (op= MINUS | op= NOT )
            {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:151:7: (op= MINUS | op= NOT )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==MINUS) ) {
                alt26=1;
            }
            else if ( (LA26_0==NOT) ) {
                alt26=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:151:8: op= MINUS
                    {
                    op=(Token)match(input,MINUS,FOLLOW_MINUS_in_unop703);  
                    stream_MINUS.add(op);


                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:151:19: op= NOT
                    {
                    op=(Token)match(input,NOT,FOLLOW_NOT_in_unop709);  
                    stream_NOT.add(op);


                    }
                    break;

            }



            // AST REWRITE
            // elements: op
            // token labels: op
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_op=new RewriteRuleTokenStream(adaptor,"token op",op);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 151:27: -> ^( UnOp $op)
            {
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:151:30: ^( UnOp $op)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(UnOp, "UnOp"), root_1);

                adaptor.addChild(root_1, stream_op.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "unop"

    public static class binop_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "binop"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:153:1: binop : (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET ) -> ^( BinOp $op) ;
    public final CalParser.binop_return binop() throws RecognitionException {
        CalParser.binop_return retval = new CalParser.binop_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token op=null;

        Object op_tree=null;
        RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
        RewriteRuleTokenStream stream_MINUS=new RewriteRuleTokenStream(adaptor,"token MINUS");
        RewriteRuleTokenStream stream_DIV=new RewriteRuleTokenStream(adaptor,"token DIV");
        RewriteRuleTokenStream stream_TIMES=new RewriteRuleTokenStream(adaptor,"token TIMES");
        RewriteRuleTokenStream stream_CARET=new RewriteRuleTokenStream(adaptor,"token CARET");

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:153:6: ( (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET ) -> ^( BinOp $op) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:153:8: (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET )
            {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:153:8: (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET )
            int alt27=5;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt27=1;
                }
                break;
            case MINUS:
                {
                alt27=2;
                }
                break;
            case TIMES:
                {
                alt27=3;
                }
                break;
            case DIV:
                {
                alt27=4;
                }
                break;
            case CARET:
                {
                alt27=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }

            switch (alt27) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:153:9: op= PLUS
                    {
                    op=(Token)match(input,PLUS,FOLLOW_PLUS_in_binop729);  
                    stream_PLUS.add(op);


                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:153:19: op= MINUS
                    {
                    op=(Token)match(input,MINUS,FOLLOW_MINUS_in_binop735);  
                    stream_MINUS.add(op);


                    }
                    break;
                case 3 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:153:30: op= TIMES
                    {
                    op=(Token)match(input,TIMES,FOLLOW_TIMES_in_binop741);  
                    stream_TIMES.add(op);


                    }
                    break;
                case 4 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:153:41: op= DIV
                    {
                    op=(Token)match(input,DIV,FOLLOW_DIV_in_binop747);  
                    stream_DIV.add(op);


                    }
                    break;
                case 5 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:153:50: op= CARET
                    {
                    op=(Token)match(input,CARET,FOLLOW_CARET_in_binop753);  
                    stream_CARET.add(op);


                    }
                    break;

            }



            // AST REWRITE
            // elements: op
            // token labels: op
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_op=new RewriteRuleTokenStream(adaptor,"token op",op);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 153:60: -> ^( BinOp $op)
            {
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:153:63: ^( BinOp $op)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BinOp, "BinOp"), root_1);

                adaptor.addChild(root_1, stream_op.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "binop"

    public static class factor_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "factor"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:155:1: factor : ( term | unop term -> ^( Expression unop term ) );
    public final CalParser.factor_return factor() throws RecognitionException {
        CalParser.factor_return retval = new CalParser.factor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CalParser.term_return term70 = null;

        CalParser.unop_return unop71 = null;

        CalParser.term_return term72 = null;


        RewriteRuleSubtreeStream stream_unop=new RewriteRuleSubtreeStream(adaptor,"rule unop");
        RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:155:7: ( term | unop term -> ^( Expression unop term ) )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==ID||LA28_0==LPAREN||LA28_0==NUMBER||LA28_0==STRING||(LA28_0>=TRUE && LA28_0<=FALSE)) ) {
                alt28=1;
            }
            else if ( (LA28_0==MINUS||LA28_0==NOT) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:155:9: term
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_term_in_factor770);
                    term70=term();

                    state._fsp--;

                    adaptor.addChild(root_0, term70.getTree());

                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:156:3: unop term
                    {
                    pushFollow(FOLLOW_unop_in_factor774);
                    unop71=unop();

                    state._fsp--;

                    stream_unop.add(unop71.getTree());
                    pushFollow(FOLLOW_term_in_factor776);
                    term72=term();

                    state._fsp--;

                    stream_term.add(term72.getTree());


                    // AST REWRITE
                    // elements: term, unop
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 156:13: -> ^( Expression unop term )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:156:16: ^( Expression unop term )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Expression, "Expression"), root_1);

                        adaptor.addChild(root_1, stream_unop.nextTree());
                        adaptor.addChild(root_1, stream_term.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "factor"

    public static class term_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "term"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:158:1: term : ( atom | LPAREN expression RPAREN -> ^( Expression expression ) );
    public final CalParser.term_return term() throws RecognitionException {
        CalParser.term_return retval = new CalParser.term_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LPAREN74=null;
        Token RPAREN76=null;
        CalParser.atom_return atom73 = null;

        CalParser.expression_return expression75 = null;


        Object LPAREN74_tree=null;
        Object RPAREN76_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:158:5: ( atom | LPAREN expression RPAREN -> ^( Expression expression ) )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==ID||LA29_0==NUMBER||LA29_0==STRING||(LA29_0>=TRUE && LA29_0<=FALSE)) ) {
                alt29=1;
            }
            else if ( (LA29_0==LPAREN) ) {
                alt29=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:158:7: atom
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_atom_in_term793);
                    atom73=atom();

                    state._fsp--;

                    adaptor.addChild(root_0, atom73.getTree());

                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:159:5: LPAREN expression RPAREN
                    {
                    LPAREN74=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_term799);  
                    stream_LPAREN.add(LPAREN74);

                    pushFollow(FOLLOW_expression_in_term801);
                    expression75=expression();

                    state._fsp--;

                    stream_expression.add(expression75.getTree());
                    RPAREN76=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_term803);  
                    stream_RPAREN.add(RPAREN76);



                    // AST REWRITE
                    // elements: expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 159:30: -> ^( Expression expression )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:159:33: ^( Expression expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Expression, "Expression"), root_1);

                        adaptor.addChild(root_1, stream_expression.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "term"

    public static class atom_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "atom"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:161:1: atom : ( ID -> ^( Var ID ) | NUMBER -> ^( Integer NUMBER ) | STRING -> ^( String STRING ) | TRUE -> ^( Boolean TRUE ) | FALSE -> ^( Boolean FALSE ) );
    public final CalParser.atom_return atom() throws RecognitionException {
        CalParser.atom_return retval = new CalParser.atom_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID77=null;
        Token NUMBER78=null;
        Token STRING79=null;
        Token TRUE80=null;
        Token FALSE81=null;

        Object ID77_tree=null;
        Object NUMBER78_tree=null;
        Object STRING79_tree=null;
        Object TRUE80_tree=null;
        Object FALSE81_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_FALSE=new RewriteRuleTokenStream(adaptor,"token FALSE");
        RewriteRuleTokenStream stream_TRUE=new RewriteRuleTokenStream(adaptor,"token TRUE");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");
        RewriteRuleTokenStream stream_NUMBER=new RewriteRuleTokenStream(adaptor,"token NUMBER");

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:161:5: ( ID -> ^( Var ID ) | NUMBER -> ^( Integer NUMBER ) | STRING -> ^( String STRING ) | TRUE -> ^( Boolean TRUE ) | FALSE -> ^( Boolean FALSE ) )
            int alt30=5;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt30=1;
                }
                break;
            case NUMBER:
                {
                alt30=2;
                }
                break;
            case STRING:
                {
                alt30=3;
                }
                break;
            case TRUE:
                {
                alt30=4;
                }
                break;
            case FALSE:
                {
                alt30=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }

            switch (alt30) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:161:7: ID
                    {
                    ID77=(Token)match(input,ID,FOLLOW_ID_in_atom818);  
                    stream_ID.add(ID77);



                    // AST REWRITE
                    // elements: ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 161:10: -> ^( Var ID )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:161:13: ^( Var ID )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_1);

                        adaptor.addChild(root_1, stream_ID.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:162:3: NUMBER
                    {
                    NUMBER78=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom830);  
                    stream_NUMBER.add(NUMBER78);



                    // AST REWRITE
                    // elements: NUMBER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 162:10: -> ^( Integer NUMBER )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:162:13: ^( Integer NUMBER )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Integer, "Integer"), root_1);

                        adaptor.addChild(root_1, stream_NUMBER.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:163:3: STRING
                    {
                    STRING79=(Token)match(input,STRING,FOLLOW_STRING_in_atom842);  
                    stream_STRING.add(STRING79);



                    // AST REWRITE
                    // elements: STRING
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 163:10: -> ^( String STRING )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:163:13: ^( String STRING )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(String, "String"), root_1);

                        adaptor.addChild(root_1, stream_STRING.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:164:3: TRUE
                    {
                    TRUE80=(Token)match(input,TRUE,FOLLOW_TRUE_in_atom854);  
                    stream_TRUE.add(TRUE80);



                    // AST REWRITE
                    // elements: TRUE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 164:8: -> ^( Boolean TRUE )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:164:11: ^( Boolean TRUE )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Boolean, "Boolean"), root_1);

                        adaptor.addChild(root_1, stream_TRUE.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 5 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:165:3: FALSE
                    {
                    FALSE81=(Token)match(input,FALSE,FOLLOW_FALSE_in_atom866);  
                    stream_FALSE.add(FALSE81);



                    // AST REWRITE
                    // elements: FALSE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 165:9: -> ^( Boolean FALSE )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:165:12: ^( Boolean FALSE )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Boolean, "Boolean"), root_1);

                        adaptor.addChild(root_1, stream_FALSE.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "atom"

    // Delegated rules


 

    public static final BitSet FOLLOW_oneImport_in_actor117 = new BitSet(new long[]{0x0000000008040000L});
    public static final BitSet FOLLOW_ACTOR_in_actor120 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ID_in_actor122 = new BitSet(new long[]{0x0000000000500000L});
    public static final BitSet FOLLOW_LBRACKET_in_actor127 = new BitSet(new long[]{0x0000000000280000L});
    public static final BitSet FOLLOW_typePars_in_actor129 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_RBRACKET_in_actor132 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_LPAREN_in_actor138 = new BitSet(new long[]{0x0000000000880000L});
    public static final BitSet FOLLOW_parameters_in_actor140 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_RPAREN_in_actor143 = new BitSet(new long[]{0x0000000011080000L});
    public static final BitSet FOLLOW_portDecls_in_actor147 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_DOUBLE_EQUAL_ARROW_in_actor150 = new BitSet(new long[]{0x0000000012080000L});
    public static final BitSet FOLLOW_portDecls_in_actor152 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_COLON_in_actor155 = new BitSet(new long[]{0x000FFFFFFFFC0000L});
    public static final BitSet FOLLOW_ignore_in_actor157 = new BitSet(new long[]{0x000FFFFFFFFC0000L});
    public static final BitSet FOLLOW_EOF_in_actor160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_ignore0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_oneImport310 = new BitSet(new long[]{0x0000000004080000L});
    public static final BitSet FOLLOW_importRest_in_oneImport312 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_oneImport314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALL_in_importRest322 = new BitSet(new long[]{0x0000000004080000L});
    public static final BitSet FOLLOW_qualifiedId_in_importRest324 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualifiedId_in_importRest330 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_EQ_in_importRest333 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ID_in_importRest335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_qualifiedId345 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_DOT_in_qualifiedId348 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ID_in_qualifiedId350 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_typeAndId_in_parameter363 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_EQ_in_parameter366 = new BitSet(new long[]{0x0074300000480000L});
    public static final BitSet FOLLOW_expression_in_parameter368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parameter_in_parameters378 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_COMMA_in_parameters381 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_parameter_in_parameters383 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_MULTI_in_portDecl396 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_typeAndId_in_portDecl399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_portDecl_in_portDecls406 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_COMMA_in_portDecls409 = new BitSet(new long[]{0x0000000010080000L});
    public static final BitSet FOLLOW_portDecl_in_portDecls411 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_typeAndId_in_mainParameter424 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_mainParameter426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_typeAndId443 = new BitSet(new long[]{0x0000000000580002L});
    public static final BitSet FOLLOW_typeRest_in_typeAndId448 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ID_in_typeAndId453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_type497 = new BitSet(new long[]{0x0000000000500002L});
    public static final BitSet FOLLOW_typeRest_in_type499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_typeRest522 = new BitSet(new long[]{0x0000000000280000L});
    public static final BitSet FOLLOW_typePars_in_typeRest524 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_RBRACKET_in_typeRest527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_typeRest538 = new BitSet(new long[]{0x0000000000880000L});
    public static final BitSet FOLLOW_typeAttrs_in_typeRest540 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_RPAREN_in_typeRest543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeAttr_in_typeAttrs555 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_COMMA_in_typeAttrs558 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_typeAttr_in_typeAttrs560 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_ID_in_typeAttr574 = new BitSet(new long[]{0x0000004002000000L});
    public static final BitSet FOLLOW_typeAttrRest_in_typeAttr576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_typeAttrRest587 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_type_in_typeAttrRest589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_typeAttrRest601 = new BitSet(new long[]{0x0074300000480000L});
    public static final BitSet FOLLOW_expression_in_typeAttrRest603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typePar_in_typePars622 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_COMMA_in_typePars625 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_typePar_in_typePars627 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_ID_in_typePar641 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_LT_in_typePar644 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_type_in_typePar646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_mainExpression669 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_mainExpression671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_factor_in_expression686 = new BitSet(new long[]{0x0008500240000002L});
    public static final BitSet FOLLOW_binop_in_expression689 = new BitSet(new long[]{0x0074300000480000L});
    public static final BitSet FOLLOW_factor_in_expression691 = new BitSet(new long[]{0x0008500240000002L});
    public static final BitSet FOLLOW_MINUS_in_unop703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_unop709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_binop729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_binop735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TIMES_in_binop741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIV_in_binop747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CARET_in_binop753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_factor770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unop_in_factor774 = new BitSet(new long[]{0x0064200000480000L});
    public static final BitSet FOLLOW_term_in_factor776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_term793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_term799 = new BitSet(new long[]{0x0074300000480000L});
    public static final BitSet FOLLOW_expression_in_term801 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_RPAREN_in_term803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_atom818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_atom830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_atom854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_atom866 = new BitSet(new long[]{0x0000000000000002L});

}