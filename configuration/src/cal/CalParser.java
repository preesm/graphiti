// $ANTLR 3.1.2 D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g 2009-03-30 13:59:22

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class CalParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "Expression", "Integer", "String", "Operator", "Var", "Parameter", "Type", "TypeAttr", "TypePar", "ACTOR", "ID", "LBRACKET", "RBRACKET", "LPAREN", "RPAREN", "DOUBLE_EQUAL_ARROW", "COLON", "ALL", "IMPORT", "MULTI", "ARROW", "CARET", "COLON_EQUAL", "COMMA", "DIV", "DOT", "DOUBLE_DASH_ARROW", "DOUBLE_DOT", "DOUBLE_COLON", "EQ", "GE", "GT", "LBRACE", "LE", "LT", "MINUS", "NUMBER", "PLUS", "RBRACE", "SEMICOLON", "SHARP", "STRING", "TIMES", "LINE_COMMENT", "MULTI_LINE_COMMENT", "WHITESPACE", "NE"
    };
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
    public static final int ALL=21;
    public static final int CARET=25;
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
    public static final int ARROW=24;
    public static final int GT=35;
    public static final int DIV=28;
    public static final int TIMES=46;
    public static final int Var=8;
    public static final int Integer=5;
    public static final int LE=37;
    public static final int STRING=45;

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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:49:1: actor : ( oneImport )* ACTOR ID ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN ( portDecls )? DOUBLE_EQUAL_ARROW ( portDecls )? COLON ( ignore )* EOF ;
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:49:6: ( ( oneImport )* ACTOR ID ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN ( portDecls )? DOUBLE_EQUAL_ARROW ( portDecls )? COLON ( ignore )* EOF )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:49:8: ( oneImport )* ACTOR ID ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN ( portDecls )? DOUBLE_EQUAL_ARROW ( portDecls )? COLON ( ignore )* EOF
            {
            root_0 = (Object)adaptor.nil();

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:49:8: ( oneImport )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==IMPORT) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:49:8: oneImport
            	    {
            	    pushFollow(FOLLOW_oneImport_in_actor83);
            	    oneImport1=oneImport();

            	    state._fsp--;

            	    adaptor.addChild(root_0, oneImport1.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            ACTOR2=(Token)match(input,ACTOR,FOLLOW_ACTOR_in_actor86); 
            ACTOR2_tree = (Object)adaptor.create(ACTOR2);
            adaptor.addChild(root_0, ACTOR2_tree);

            ID3=(Token)match(input,ID,FOLLOW_ID_in_actor88); 
            ID3_tree = (Object)adaptor.create(ID3);
            adaptor.addChild(root_0, ID3_tree);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:50:3: ( LBRACKET ( typePars )? RBRACKET )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==LBRACKET) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:50:4: LBRACKET ( typePars )? RBRACKET
                    {
                    LBRACKET4=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_actor93); 
                    LBRACKET4_tree = (Object)adaptor.create(LBRACKET4);
                    adaptor.addChild(root_0, LBRACKET4_tree);

                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:50:13: ( typePars )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==ID) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:50:13: typePars
                            {
                            pushFollow(FOLLOW_typePars_in_actor95);
                            typePars5=typePars();

                            state._fsp--;

                            adaptor.addChild(root_0, typePars5.getTree());

                            }
                            break;

                    }

                    RBRACKET6=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_actor98); 
                    RBRACKET6_tree = (Object)adaptor.create(RBRACKET6);
                    adaptor.addChild(root_0, RBRACKET6_tree);


                    }
                    break;

            }

            LPAREN7=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_actor104); 
            LPAREN7_tree = (Object)adaptor.create(LPAREN7);
            adaptor.addChild(root_0, LPAREN7_tree);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:51:10: ( parameters )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==ID) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:51:10: parameters
                    {
                    pushFollow(FOLLOW_parameters_in_actor106);
                    parameters8=parameters();

                    state._fsp--;

                    adaptor.addChild(root_0, parameters8.getTree());

                    }
                    break;

            }

            RPAREN9=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_actor109); 
            RPAREN9_tree = (Object)adaptor.create(RPAREN9);
            adaptor.addChild(root_0, RPAREN9_tree);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:52:3: ( portDecls )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==ID||LA5_0==MULTI) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:52:3: portDecls
                    {
                    pushFollow(FOLLOW_portDecls_in_actor113);
                    portDecls10=portDecls();

                    state._fsp--;

                    adaptor.addChild(root_0, portDecls10.getTree());

                    }
                    break;

            }

            DOUBLE_EQUAL_ARROW11=(Token)match(input,DOUBLE_EQUAL_ARROW,FOLLOW_DOUBLE_EQUAL_ARROW_in_actor116); 
            DOUBLE_EQUAL_ARROW11_tree = (Object)adaptor.create(DOUBLE_EQUAL_ARROW11);
            adaptor.addChild(root_0, DOUBLE_EQUAL_ARROW11_tree);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:52:33: ( portDecls )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==ID||LA6_0==MULTI) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:52:33: portDecls
                    {
                    pushFollow(FOLLOW_portDecls_in_actor118);
                    portDecls12=portDecls();

                    state._fsp--;

                    adaptor.addChild(root_0, portDecls12.getTree());

                    }
                    break;

            }

            COLON13=(Token)match(input,COLON,FOLLOW_COLON_in_actor121); 
            COLON13_tree = (Object)adaptor.create(COLON13);
            adaptor.addChild(root_0, COLON13_tree);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:52:50: ( ignore )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>=ACTOR && LA7_0<=TIMES)) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:52:50: ignore
            	    {
            	    pushFollow(FOLLOW_ignore_in_actor123);
            	    ignore14=ignore();

            	    state._fsp--;

            	    adaptor.addChild(root_0, ignore14.getTree());

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            EOF15=(Token)match(input,EOF,FOLLOW_EOF_in_actor126); 
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:54:1: ignore : ( ALL | ACTOR | IMPORT | MULTI | ARROW | CARET | COLON | COLON_EQUAL | COMMA | DIV | DOT | DOUBLE_DASH_ARROW | DOUBLE_EQUAL_ARROW | DOUBLE_DOT | DOUBLE_COLON | EQ | GE | GT | ID | LBRACE | LBRACKET | LPAREN | LE | LT | MINUS | NUMBER | PLUS | RBRACE | RBRACKET | RPAREN | SEMICOLON | SHARP | STRING | TIMES );
    public final CalParser.ignore_return ignore() throws RecognitionException {
        CalParser.ignore_return retval = new CalParser.ignore_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set16=null;

        Object set16_tree=null;

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:54:7: ( ALL | ACTOR | IMPORT | MULTI | ARROW | CARET | COLON | COLON_EQUAL | COMMA | DIV | DOT | DOUBLE_DASH_ARROW | DOUBLE_EQUAL_ARROW | DOUBLE_DOT | DOUBLE_COLON | EQ | GE | GT | ID | LBRACE | LBRACKET | LPAREN | LE | LT | MINUS | NUMBER | PLUS | RBRACE | RBRACKET | RPAREN | SEMICOLON | SHARP | STRING | TIMES )
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:92:1: oneImport : IMPORT importRest SEMICOLON ;
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:92:10: ( IMPORT importRest SEMICOLON )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:92:12: IMPORT importRest SEMICOLON
            {
            root_0 = (Object)adaptor.nil();

            IMPORT17=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_oneImport276); 
            IMPORT17_tree = (Object)adaptor.create(IMPORT17);
            adaptor.addChild(root_0, IMPORT17_tree);

            pushFollow(FOLLOW_importRest_in_oneImport278);
            importRest18=importRest();

            state._fsp--;

            adaptor.addChild(root_0, importRest18.getTree());
            SEMICOLON19=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_oneImport280); 
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:94:1: importRest : ( ALL qualifiedId | qualifiedId ( EQ ID )? );
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:94:11: ( ALL qualifiedId | qualifiedId ( EQ ID )? )
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
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:94:13: ALL qualifiedId
                    {
                    root_0 = (Object)adaptor.nil();

                    ALL20=(Token)match(input,ALL,FOLLOW_ALL_in_importRest288); 
                    ALL20_tree = (Object)adaptor.create(ALL20);
                    adaptor.addChild(root_0, ALL20_tree);

                    pushFollow(FOLLOW_qualifiedId_in_importRest290);
                    qualifiedId21=qualifiedId();

                    state._fsp--;

                    adaptor.addChild(root_0, qualifiedId21.getTree());

                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:95:5: qualifiedId ( EQ ID )?
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_qualifiedId_in_importRest296);
                    qualifiedId22=qualifiedId();

                    state._fsp--;

                    adaptor.addChild(root_0, qualifiedId22.getTree());
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:95:17: ( EQ ID )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==EQ) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:95:18: EQ ID
                            {
                            EQ23=(Token)match(input,EQ,FOLLOW_EQ_in_importRest299); 
                            EQ23_tree = (Object)adaptor.create(EQ23);
                            adaptor.addChild(root_0, EQ23_tree);

                            ID24=(Token)match(input,ID,FOLLOW_ID_in_importRest301); 
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:97:1: qualifiedId : ID ( DOT ID )+ ;
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:97:12: ( ID ( DOT ID )+ )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:97:14: ID ( DOT ID )+
            {
            root_0 = (Object)adaptor.nil();

            ID25=(Token)match(input,ID,FOLLOW_ID_in_qualifiedId311); 
            ID25_tree = (Object)adaptor.create(ID25);
            adaptor.addChild(root_0, ID25_tree);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:97:17: ( DOT ID )+
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
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:97:18: DOT ID
            	    {
            	    DOT26=(Token)match(input,DOT,FOLLOW_DOT_in_qualifiedId314); 
            	    DOT26_tree = (Object)adaptor.create(DOT26);
            	    adaptor.addChild(root_0, DOT26_tree);

            	    ID27=(Token)match(input,ID,FOLLOW_ID_in_qualifiedId316); 
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:102:1: parameter : ( type )? ID ( EQ expression )? ;
    public final CalParser.parameter_return parameter() throws RecognitionException {
        CalParser.parameter_return retval = new CalParser.parameter_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID29=null;
        Token EQ30=null;
        CalParser.type_return type28 = null;

        CalParser.expression_return expression31 = null;


        Object ID29_tree=null;
        Object EQ30_tree=null;

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:102:10: ( ( type )? ID ( EQ expression )? )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:102:12: ( type )? ID ( EQ expression )?
            {
            root_0 = (Object)adaptor.nil();

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:102:12: ( type )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==ID) ) {
                int LA11_1 = input.LA(2);

                if ( ((LA11_1>=ID && LA11_1<=LBRACKET)||LA11_1==LPAREN) ) {
                    alt11=1;
                }
            }
            switch (alt11) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:102:12: type
                    {
                    pushFollow(FOLLOW_type_in_parameter329);
                    type28=type();

                    state._fsp--;

                    adaptor.addChild(root_0, type28.getTree());

                    }
                    break;

            }

            ID29=(Token)match(input,ID,FOLLOW_ID_in_parameter332); 
            ID29_tree = (Object)adaptor.create(ID29);
            adaptor.addChild(root_0, ID29_tree);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:102:21: ( EQ expression )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==EQ) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:102:22: EQ expression
                    {
                    EQ30=(Token)match(input,EQ,FOLLOW_EQ_in_parameter335); 
                    EQ30_tree = (Object)adaptor.create(EQ30);
                    adaptor.addChild(root_0, EQ30_tree);

                    pushFollow(FOLLOW_expression_in_parameter337);
                    expression31=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression31.getTree());

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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:104:1: parameters : parameter ( COMMA parameter )* ;
    public final CalParser.parameters_return parameters() throws RecognitionException {
        CalParser.parameters_return retval = new CalParser.parameters_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA33=null;
        CalParser.parameter_return parameter32 = null;

        CalParser.parameter_return parameter34 = null;


        Object COMMA33_tree=null;

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:104:11: ( parameter ( COMMA parameter )* )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:104:13: parameter ( COMMA parameter )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_parameter_in_parameters347);
            parameter32=parameter();

            state._fsp--;

            adaptor.addChild(root_0, parameter32.getTree());
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:104:23: ( COMMA parameter )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==COMMA) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:104:24: COMMA parameter
            	    {
            	    COMMA33=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameters350); 
            	    COMMA33_tree = (Object)adaptor.create(COMMA33);
            	    adaptor.addChild(root_0, COMMA33_tree);

            	    pushFollow(FOLLOW_parameter_in_parameters352);
            	    parameter34=parameter();

            	    state._fsp--;

            	    adaptor.addChild(root_0, parameter34.getTree());

            	    }
            	    break;

            	default :
            	    break loop13;
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:109:1: portDecl : ( MULTI )? ( type )? ID ;
    public final CalParser.portDecl_return portDecl() throws RecognitionException {
        CalParser.portDecl_return retval = new CalParser.portDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MULTI35=null;
        Token ID37=null;
        CalParser.type_return type36 = null;


        Object MULTI35_tree=null;
        Object ID37_tree=null;

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:109:9: ( ( MULTI )? ( type )? ID )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:109:11: ( MULTI )? ( type )? ID
            {
            root_0 = (Object)adaptor.nil();

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:109:11: ( MULTI )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==MULTI) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:109:11: MULTI
                    {
                    MULTI35=(Token)match(input,MULTI,FOLLOW_MULTI_in_portDecl365); 
                    MULTI35_tree = (Object)adaptor.create(MULTI35);
                    adaptor.addChild(root_0, MULTI35_tree);


                    }
                    break;

            }

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:109:18: ( type )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==ID) ) {
                int LA15_1 = input.LA(2);

                if ( ((LA15_1>=ID && LA15_1<=LBRACKET)||LA15_1==LPAREN) ) {
                    alt15=1;
                }
            }
            switch (alt15) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:109:18: type
                    {
                    pushFollow(FOLLOW_type_in_portDecl368);
                    type36=type();

                    state._fsp--;

                    adaptor.addChild(root_0, type36.getTree());

                    }
                    break;

            }

            ID37=(Token)match(input,ID,FOLLOW_ID_in_portDecl371); 
            ID37_tree = (Object)adaptor.create(ID37);
            adaptor.addChild(root_0, ID37_tree);


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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:111:1: portDecls : portDecl ( COMMA portDecl )* ;
    public final CalParser.portDecls_return portDecls() throws RecognitionException {
        CalParser.portDecls_return retval = new CalParser.portDecls_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA39=null;
        CalParser.portDecl_return portDecl38 = null;

        CalParser.portDecl_return portDecl40 = null;


        Object COMMA39_tree=null;

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:111:10: ( portDecl ( COMMA portDecl )* )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:111:12: portDecl ( COMMA portDecl )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_portDecl_in_portDecls379);
            portDecl38=portDecl();

            state._fsp--;

            adaptor.addChild(root_0, portDecl38.getTree());
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:111:21: ( COMMA portDecl )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==COMMA) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:111:22: COMMA portDecl
            	    {
            	    COMMA39=(Token)match(input,COMMA,FOLLOW_COMMA_in_portDecls382); 
            	    COMMA39_tree = (Object)adaptor.create(COMMA39);
            	    adaptor.addChild(root_0, COMMA39_tree);

            	    pushFollow(FOLLOW_portDecl_in_portDecls384);
            	    portDecl40=portDecl();

            	    state._fsp--;

            	    adaptor.addChild(root_0, portDecl40.getTree());

            	    }
            	    break;

            	default :
            	    break loop16;
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:116:1: mainParameter : typeAndId EOF -> ^( Parameter typeAndId ) ;
    public final CalParser.mainParameter_return mainParameter() throws RecognitionException {
        CalParser.mainParameter_return retval = new CalParser.mainParameter_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF42=null;
        CalParser.typeAndId_return typeAndId41 = null;


        Object EOF42_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_typeAndId=new RewriteRuleSubtreeStream(adaptor,"rule typeAndId");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:116:14: ( typeAndId EOF -> ^( Parameter typeAndId ) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:116:16: typeAndId EOF
            {
            pushFollow(FOLLOW_typeAndId_in_mainParameter397);
            typeAndId41=typeAndId();

            state._fsp--;

            stream_typeAndId.add(typeAndId41.getTree());
            EOF42=(Token)match(input,EOF,FOLLOW_EOF_in_mainParameter399);  
            stream_EOF.add(EOF42);



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
            // 116:30: -> ^( Parameter typeAndId )
            {
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:116:33: ^( Parameter typeAndId )
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:118:1: typeAndId : ( type )? ID ;
    public final CalParser.typeAndId_return typeAndId() throws RecognitionException {
        CalParser.typeAndId_return retval = new CalParser.typeAndId_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID44=null;
        CalParser.type_return type43 = null;


        Object ID44_tree=null;

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:118:10: ( ( type )? ID )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:118:12: ( type )? ID
            {
            root_0 = (Object)adaptor.nil();

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:118:12: ( type )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==ID) ) {
                int LA17_1 = input.LA(2);

                if ( ((LA17_1>=ID && LA17_1<=LBRACKET)||LA17_1==LPAREN) ) {
                    alt17=1;
                }
            }
            switch (alt17) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:118:12: type
                    {
                    pushFollow(FOLLOW_type_in_typeAndId414);
                    type43=type();

                    state._fsp--;

                    adaptor.addChild(root_0, type43.getTree());

                    }
                    break;

            }

            ID44=(Token)match(input,ID,FOLLOW_ID_in_typeAndId417); 
            ID44_tree = (Object)adaptor.create(ID44);
            adaptor.addChild(root_0, ID44_tree);


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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:120:1: type : ID ( typeRest )? -> ^( Type ID ( typeRest )? ) ;
    public final CalParser.type_return type() throws RecognitionException {
        CalParser.type_return retval = new CalParser.type_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID45=null;
        CalParser.typeRest_return typeRest46 = null;


        Object ID45_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_typeRest=new RewriteRuleSubtreeStream(adaptor,"rule typeRest");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:120:5: ( ID ( typeRest )? -> ^( Type ID ( typeRest )? ) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:120:7: ID ( typeRest )?
            {
            ID45=(Token)match(input,ID,FOLLOW_ID_in_type424);  
            stream_ID.add(ID45);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:120:10: ( typeRest )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==LBRACKET||LA18_0==LPAREN) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:120:10: typeRest
                    {
                    pushFollow(FOLLOW_typeRest_in_type426);
                    typeRest46=typeRest();

                    state._fsp--;

                    stream_typeRest.add(typeRest46.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: typeRest, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 120:20: -> ^( Type ID ( typeRest )? )
            {
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:120:23: ^( Type ID ( typeRest )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Type, "Type"), root_1);

                adaptor.addChild(root_1, stream_ID.nextNode());
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:120:33: ( typeRest )?
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:122:1: typeRest : ( LBRACKET ( typePars )? RBRACKET -> ( typePars )? | LPAREN ( typeAttrs )? RPAREN -> ( typeAttrs )? );
    public final CalParser.typeRest_return typeRest() throws RecognitionException {
        CalParser.typeRest_return retval = new CalParser.typeRest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LBRACKET47=null;
        Token RBRACKET49=null;
        Token LPAREN50=null;
        Token RPAREN52=null;
        CalParser.typePars_return typePars48 = null;

        CalParser.typeAttrs_return typeAttrs51 = null;


        Object LBRACKET47_tree=null;
        Object RBRACKET49_tree=null;
        Object LPAREN50_tree=null;
        Object RPAREN52_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_RBRACKET=new RewriteRuleTokenStream(adaptor,"token RBRACKET");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_typePars=new RewriteRuleSubtreeStream(adaptor,"rule typePars");
        RewriteRuleSubtreeStream stream_typeAttrs=new RewriteRuleSubtreeStream(adaptor,"rule typeAttrs");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:122:9: ( LBRACKET ( typePars )? RBRACKET -> ( typePars )? | LPAREN ( typeAttrs )? RPAREN -> ( typeAttrs )? )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==LBRACKET) ) {
                alt21=1;
            }
            else if ( (LA21_0==LPAREN) ) {
                alt21=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:122:11: LBRACKET ( typePars )? RBRACKET
                    {
                    LBRACKET47=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_typeRest445);  
                    stream_LBRACKET.add(LBRACKET47);

                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:122:20: ( typePars )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==ID) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:122:20: typePars
                            {
                            pushFollow(FOLLOW_typePars_in_typeRest447);
                            typePars48=typePars();

                            state._fsp--;

                            stream_typePars.add(typePars48.getTree());

                            }
                            break;

                    }

                    RBRACKET49=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_typeRest450);  
                    stream_RBRACKET.add(RBRACKET49);



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
                    // 122:39: -> ( typePars )?
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:122:42: ( typePars )?
                        if ( stream_typePars.hasNext() ) {
                            adaptor.addChild(root_0, stream_typePars.nextTree());

                        }
                        stream_typePars.reset();

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:123:5: LPAREN ( typeAttrs )? RPAREN
                    {
                    LPAREN50=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_typeRest461);  
                    stream_LPAREN.add(LPAREN50);

                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:123:12: ( typeAttrs )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==ID) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:123:12: typeAttrs
                            {
                            pushFollow(FOLLOW_typeAttrs_in_typeRest463);
                            typeAttrs51=typeAttrs();

                            state._fsp--;

                            stream_typeAttrs.add(typeAttrs51.getTree());

                            }
                            break;

                    }

                    RPAREN52=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_typeRest466);  
                    stream_RPAREN.add(RPAREN52);



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
                    // 123:30: -> ( typeAttrs )?
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:123:33: ( typeAttrs )?
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:125:1: typeAttrs : typeAttr ( COMMA typeAttr )* -> ( typeAttr )+ ;
    public final CalParser.typeAttrs_return typeAttrs() throws RecognitionException {
        CalParser.typeAttrs_return retval = new CalParser.typeAttrs_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA54=null;
        CalParser.typeAttr_return typeAttr53 = null;

        CalParser.typeAttr_return typeAttr55 = null;


        Object COMMA54_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_typeAttr=new RewriteRuleSubtreeStream(adaptor,"rule typeAttr");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:125:10: ( typeAttr ( COMMA typeAttr )* -> ( typeAttr )+ )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:125:12: typeAttr ( COMMA typeAttr )*
            {
            pushFollow(FOLLOW_typeAttr_in_typeAttrs478);
            typeAttr53=typeAttr();

            state._fsp--;

            stream_typeAttr.add(typeAttr53.getTree());
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:125:21: ( COMMA typeAttr )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==COMMA) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:125:22: COMMA typeAttr
            	    {
            	    COMMA54=(Token)match(input,COMMA,FOLLOW_COMMA_in_typeAttrs481);  
            	    stream_COMMA.add(COMMA54);

            	    pushFollow(FOLLOW_typeAttr_in_typeAttrs483);
            	    typeAttr55=typeAttr();

            	    state._fsp--;

            	    stream_typeAttr.add(typeAttr55.getTree());

            	    }
            	    break;

            	default :
            	    break loop22;
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
            // 125:39: -> ( typeAttr )+
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:127:1: typeAttr : ( ID COLON type -> ^( TypeAttr ID Type type ) | ID EQ expression -> ^( TypeAttr ID Expression expression ) );
    public final CalParser.typeAttr_return typeAttr() throws RecognitionException {
        CalParser.typeAttr_return retval = new CalParser.typeAttr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID56=null;
        Token COLON57=null;
        Token ID59=null;
        Token EQ60=null;
        CalParser.type_return type58 = null;

        CalParser.expression_return expression61 = null;


        Object ID56_tree=null;
        Object COLON57_tree=null;
        Object ID59_tree=null;
        Object EQ60_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_EQ=new RewriteRuleTokenStream(adaptor,"token EQ");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:127:9: ( ID COLON type -> ^( TypeAttr ID Type type ) | ID EQ expression -> ^( TypeAttr ID Expression expression ) )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==ID) ) {
                int LA23_1 = input.LA(2);

                if ( (LA23_1==COLON) ) {
                    alt23=1;
                }
                else if ( (LA23_1==EQ) ) {
                    alt23=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:127:11: ID COLON type
                    {
                    ID56=(Token)match(input,ID,FOLLOW_ID_in_typeAttr497);  
                    stream_ID.add(ID56);

                    COLON57=(Token)match(input,COLON,FOLLOW_COLON_in_typeAttr499);  
                    stream_COLON.add(COLON57);

                    pushFollow(FOLLOW_type_in_typeAttr501);
                    type58=type();

                    state._fsp--;

                    stream_type.add(type58.getTree());


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
                    // 127:25: -> ^( TypeAttr ID Type type )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:127:28: ^( TypeAttr ID Type type )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TypeAttr, "TypeAttr"), root_1);

                        adaptor.addChild(root_1, stream_ID.nextNode());
                        adaptor.addChild(root_1, (Object)adaptor.create(Type, "Type"));
                        adaptor.addChild(root_1, stream_type.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:128:3: ID EQ expression
                    {
                    ID59=(Token)match(input,ID,FOLLOW_ID_in_typeAttr517);  
                    stream_ID.add(ID59);

                    EQ60=(Token)match(input,EQ,FOLLOW_EQ_in_typeAttr519);  
                    stream_EQ.add(EQ60);

                    pushFollow(FOLLOW_expression_in_typeAttr521);
                    expression61=expression();

                    state._fsp--;

                    stream_expression.add(expression61.getTree());


                    // AST REWRITE
                    // elements: expression, ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 128:20: -> ^( TypeAttr ID Expression expression )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:128:23: ^( TypeAttr ID Expression expression )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TypeAttr, "TypeAttr"), root_1);

                        adaptor.addChild(root_1, stream_ID.nextNode());
                        adaptor.addChild(root_1, (Object)adaptor.create(Expression, "Expression"));
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
    // $ANTLR end "typeAttr"

    public static class typePars_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "typePars"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:130:1: typePars : typePar ( COMMA typePar )* -> ( typePar )+ ;
    public final CalParser.typePars_return typePars() throws RecognitionException {
        CalParser.typePars_return retval = new CalParser.typePars_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA63=null;
        CalParser.typePar_return typePar62 = null;

        CalParser.typePar_return typePar64 = null;


        Object COMMA63_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_typePar=new RewriteRuleSubtreeStream(adaptor,"rule typePar");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:130:9: ( typePar ( COMMA typePar )* -> ( typePar )+ )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:130:11: typePar ( COMMA typePar )*
            {
            pushFollow(FOLLOW_typePar_in_typePars540);
            typePar62=typePar();

            state._fsp--;

            stream_typePar.add(typePar62.getTree());
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:130:19: ( COMMA typePar )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==COMMA) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:130:20: COMMA typePar
            	    {
            	    COMMA63=(Token)match(input,COMMA,FOLLOW_COMMA_in_typePars543);  
            	    stream_COMMA.add(COMMA63);

            	    pushFollow(FOLLOW_typePar_in_typePars545);
            	    typePar64=typePar();

            	    state._fsp--;

            	    stream_typePar.add(typePar64.getTree());

            	    }
            	    break;

            	default :
            	    break loop24;
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
            // 130:36: -> ( typePar )+
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:132:1: typePar : ID ( LT type )? -> ^( TypePar ID ( type )? ) ;
    public final CalParser.typePar_return typePar() throws RecognitionException {
        CalParser.typePar_return retval = new CalParser.typePar_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID65=null;
        Token LT66=null;
        CalParser.type_return type67 = null;


        Object ID65_tree=null;
        Object LT66_tree=null;
        RewriteRuleTokenStream stream_LT=new RewriteRuleTokenStream(adaptor,"token LT");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:132:8: ( ID ( LT type )? -> ^( TypePar ID ( type )? ) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:132:10: ID ( LT type )?
            {
            ID65=(Token)match(input,ID,FOLLOW_ID_in_typePar559);  
            stream_ID.add(ID65);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:132:13: ( LT type )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==LT) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:132:14: LT type
                    {
                    LT66=(Token)match(input,LT,FOLLOW_LT_in_typePar562);  
                    stream_LT.add(LT66);

                    pushFollow(FOLLOW_type_in_typePar564);
                    type67=type();

                    state._fsp--;

                    stream_type.add(type67.getTree());

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
            // 132:24: -> ^( TypePar ID ( type )? )
            {
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:132:27: ^( TypePar ID ( type )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TypePar, "TypePar"), root_1);

                adaptor.addChild(root_1, stream_ID.nextNode());
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:132:40: ( type )?
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:137:1: mainExpression : expression EOF -> ^( Expression expression ) ;
    public final CalParser.mainExpression_return mainExpression() throws RecognitionException {
        CalParser.mainExpression_return retval = new CalParser.mainExpression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF69=null;
        CalParser.expression_return expression68 = null;


        Object EOF69_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:137:15: ( expression EOF -> ^( Expression expression ) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:137:17: expression EOF
            {
            pushFollow(FOLLOW_expression_in_mainExpression587);
            expression68=expression();

            state._fsp--;

            stream_expression.add(expression68.getTree());
            EOF69=(Token)match(input,EOF,FOLLOW_EOF_in_mainExpression589);  
            stream_EOF.add(EOF69);



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
            // 137:32: -> ^( Expression expression )
            {
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:137:35: ^( Expression expression )
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:139:1: expression : factor ( operator factor )* ;
    public final CalParser.expression_return expression() throws RecognitionException {
        CalParser.expression_return retval = new CalParser.expression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CalParser.factor_return factor70 = null;

        CalParser.operator_return operator71 = null;

        CalParser.factor_return factor72 = null;



        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:139:11: ( factor ( operator factor )* )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:139:13: factor ( operator factor )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_factor_in_expression604);
            factor70=factor();

            state._fsp--;

            adaptor.addChild(root_0, factor70.getTree());
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:139:20: ( operator factor )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==CARET||LA26_0==DIV||LA26_0==MINUS||LA26_0==PLUS||LA26_0==TIMES) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:139:21: operator factor
            	    {
            	    pushFollow(FOLLOW_operator_in_expression607);
            	    operator71=operator();

            	    state._fsp--;

            	    adaptor.addChild(root_0, operator71.getTree());
            	    pushFollow(FOLLOW_factor_in_expression609);
            	    factor72=factor();

            	    state._fsp--;

            	    adaptor.addChild(root_0, factor72.getTree());

            	    }
            	    break;

            	default :
            	    break loop26;
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

    public static class operator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "operator"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:141:1: operator : (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET ) -> ^( Operator $op) ;
    public final CalParser.operator_return operator() throws RecognitionException {
        CalParser.operator_return retval = new CalParser.operator_return();
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:141:9: ( (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET ) -> ^( Operator $op) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:141:11: (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET )
            {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:141:11: (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET )
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
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:141:12: op= PLUS
                    {
                    op=(Token)match(input,PLUS,FOLLOW_PLUS_in_operator621);  
                    stream_PLUS.add(op);


                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:141:22: op= MINUS
                    {
                    op=(Token)match(input,MINUS,FOLLOW_MINUS_in_operator627);  
                    stream_MINUS.add(op);


                    }
                    break;
                case 3 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:141:33: op= TIMES
                    {
                    op=(Token)match(input,TIMES,FOLLOW_TIMES_in_operator633);  
                    stream_TIMES.add(op);


                    }
                    break;
                case 4 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:141:44: op= DIV
                    {
                    op=(Token)match(input,DIV,FOLLOW_DIV_in_operator639);  
                    stream_DIV.add(op);


                    }
                    break;
                case 5 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:141:53: op= CARET
                    {
                    op=(Token)match(input,CARET,FOLLOW_CARET_in_operator645);  
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
            // 141:63: -> ^( Operator $op)
            {
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:141:66: ^( Operator $op)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Operator, "Operator"), root_1);

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
    // $ANTLR end "operator"

    public static class factor_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "factor"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:143:1: factor : ( atom | LPAREN expression RPAREN -> ^( Expression expression ) );
    public final CalParser.factor_return factor() throws RecognitionException {
        CalParser.factor_return retval = new CalParser.factor_return();
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:143:7: ( atom | LPAREN expression RPAREN -> ^( Expression expression ) )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==ID||LA28_0==NUMBER||LA28_0==STRING) ) {
                alt28=1;
            }
            else if ( (LA28_0==LPAREN) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:143:9: atom
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_atom_in_factor662);
                    atom73=atom();

                    state._fsp--;

                    adaptor.addChild(root_0, atom73.getTree());

                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:144:5: LPAREN expression RPAREN
                    {
                    LPAREN74=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_factor668);  
                    stream_LPAREN.add(LPAREN74);

                    pushFollow(FOLLOW_expression_in_factor670);
                    expression75=expression();

                    state._fsp--;

                    stream_expression.add(expression75.getTree());
                    RPAREN76=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_factor672);  
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
                    // 144:30: -> ^( Expression expression )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:144:33: ^( Expression expression )
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
    // $ANTLR end "factor"

    public static class atom_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "atom"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:146:1: atom : ( ID -> ^( Var ID ) | NUMBER -> ^( Integer NUMBER ) | STRING -> ^( String STRING ) );
    public final CalParser.atom_return atom() throws RecognitionException {
        CalParser.atom_return retval = new CalParser.atom_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID77=null;
        Token NUMBER78=null;
        Token STRING79=null;

        Object ID77_tree=null;
        Object NUMBER78_tree=null;
        Object STRING79_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");
        RewriteRuleTokenStream stream_NUMBER=new RewriteRuleTokenStream(adaptor,"token NUMBER");

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:146:5: ( ID -> ^( Var ID ) | NUMBER -> ^( Integer NUMBER ) | STRING -> ^( String STRING ) )
            int alt29=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt29=1;
                }
                break;
            case NUMBER:
                {
                alt29=2;
                }
                break;
            case STRING:
                {
                alt29=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }

            switch (alt29) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:146:7: ID
                    {
                    ID77=(Token)match(input,ID,FOLLOW_ID_in_atom687);  
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
                    // 146:10: -> ^( Var ID )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:146:13: ^( Var ID )
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
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:147:3: NUMBER
                    {
                    NUMBER78=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom699);  
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
                    // 147:10: -> ^( Integer NUMBER )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:147:13: ^( Integer NUMBER )
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
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:148:3: STRING
                    {
                    STRING79=(Token)match(input,STRING,FOLLOW_STRING_in_atom711);  
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
                    // 148:10: -> ^( String STRING )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\cal\\Cal.g:148:13: ^( String STRING )
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


 

    public static final BitSet FOLLOW_oneImport_in_actor83 = new BitSet(new long[]{0x0000000000402000L});
    public static final BitSet FOLLOW_ACTOR_in_actor86 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_actor88 = new BitSet(new long[]{0x0000000000028000L});
    public static final BitSet FOLLOW_LBRACKET_in_actor93 = new BitSet(new long[]{0x0000000000014000L});
    public static final BitSet FOLLOW_typePars_in_actor95 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_RBRACKET_in_actor98 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_LPAREN_in_actor104 = new BitSet(new long[]{0x0000000000044000L});
    public static final BitSet FOLLOW_parameters_in_actor106 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_RPAREN_in_actor109 = new BitSet(new long[]{0x0000000000884000L});
    public static final BitSet FOLLOW_portDecls_in_actor113 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_DOUBLE_EQUAL_ARROW_in_actor116 = new BitSet(new long[]{0x0000000000904000L});
    public static final BitSet FOLLOW_portDecls_in_actor118 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_COLON_in_actor121 = new BitSet(new long[]{0x00007FFFFFFFE000L});
    public static final BitSet FOLLOW_ignore_in_actor123 = new BitSet(new long[]{0x00007FFFFFFFE000L});
    public static final BitSet FOLLOW_EOF_in_actor126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_ignore0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_oneImport276 = new BitSet(new long[]{0x0000000000204000L});
    public static final BitSet FOLLOW_importRest_in_oneImport278 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_oneImport280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALL_in_importRest288 = new BitSet(new long[]{0x0000000000204000L});
    public static final BitSet FOLLOW_qualifiedId_in_importRest290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualifiedId_in_importRest296 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_EQ_in_importRest299 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_importRest301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_qualifiedId311 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_DOT_in_qualifiedId314 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_qualifiedId316 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_type_in_parameter329 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_parameter332 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_EQ_in_parameter335 = new BitSet(new long[]{0x0000210000024000L});
    public static final BitSet FOLLOW_expression_in_parameter337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parameter_in_parameters347 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_COMMA_in_parameters350 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_parameter_in_parameters352 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_MULTI_in_portDecl365 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_type_in_portDecl368 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_portDecl371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_portDecl_in_portDecls379 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_COMMA_in_portDecls382 = new BitSet(new long[]{0x0000000000804000L});
    public static final BitSet FOLLOW_portDecl_in_portDecls384 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_typeAndId_in_mainParameter397 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_mainParameter399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_typeAndId414 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ID_in_typeAndId417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_type424 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_typeRest_in_type426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_typeRest445 = new BitSet(new long[]{0x0000000000014000L});
    public static final BitSet FOLLOW_typePars_in_typeRest447 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_RBRACKET_in_typeRest450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_typeRest461 = new BitSet(new long[]{0x0000000000044000L});
    public static final BitSet FOLLOW_typeAttrs_in_typeRest463 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_RPAREN_in_typeRest466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeAttr_in_typeAttrs478 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_COMMA_in_typeAttrs481 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_typeAttr_in_typeAttrs483 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_ID_in_typeAttr497 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_COLON_in_typeAttr499 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_type_in_typeAttr501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_typeAttr517 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_EQ_in_typeAttr519 = new BitSet(new long[]{0x0000210000024000L});
    public static final BitSet FOLLOW_expression_in_typeAttr521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typePar_in_typePars540 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_COMMA_in_typePars543 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_typePar_in_typePars545 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_ID_in_typePar559 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_LT_in_typePar562 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_type_in_typePar564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_mainExpression587 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_mainExpression589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_factor_in_expression604 = new BitSet(new long[]{0x0000428012000002L});
    public static final BitSet FOLLOW_operator_in_expression607 = new BitSet(new long[]{0x0000210000024000L});
    public static final BitSet FOLLOW_factor_in_expression609 = new BitSet(new long[]{0x0000428012000002L});
    public static final BitSet FOLLOW_PLUS_in_operator621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_operator627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TIMES_in_operator633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIV_in_operator639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CARET_in_operator645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_factor662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_factor668 = new BitSet(new long[]{0x0000210000024000L});
    public static final BitSet FOLLOW_expression_in_factor670 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_RPAREN_in_factor672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_atom687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_atom699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom711 = new BitSet(new long[]{0x0000000000000002L});

}