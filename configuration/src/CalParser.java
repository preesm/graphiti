// $ANTLR 3.1.2 D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g 2009-03-30 17:53:41

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class CalParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BinOp", "Boolean", "Expression", "Integer", "Minus", "Not", "String", "UnOp", "Var", "Parameter", "Type", "TypeAttr", "ExprAttr", "TypePar", "Actor", "Name", "Inputs", "Outputs", "PortDecl", "ACTOR", "ID", "LBRACKET", "RBRACKET", "LPAREN", "RPAREN", "DOUBLE_EQUAL_ARROW", "COLON", "ALL", "ARROW", "CARET", "COLON_EQUAL", "COMMA", "DIV", "DOT", "DOUBLE_DASH_ARROW", "DOUBLE_DOT", "DOUBLE_COLON", "EQ", "FALSE", "GE", "GT", "LBRACE", "LE", "LT", "MINUS", "MULTI", "NE", "NOT", "NUMBER", "PLUS", "RBRACE", "SEMICOLON", "SHARP", "STRING", "TIMES", "TRUE", "IMPORT", "LINE_COMMENT", "MULTI_LINE_COMMENT", "WHITESPACE"
    };
    public static final int LT=47;
    public static final int Inputs=20;
    public static final int LBRACE=45;
    public static final int MULTI=49;
    public static final int Actor=18;
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
    public static final int ALL=31;
    public static final int CARET=33;
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
    public static final int TypePar=17;
    public static final int COLON=30;
    public static final int UnOp=11;
    public static final int Minus=8;
    public static final int DOUBLE_COLON=40;
    public static final int Boolean=5;
    public static final int ExprAttr=16;
    public static final int ACTOR=23;
    public static final int Not=9;
    public static final int Name=19;
    public static final int GT=44;
    public static final int ARROW=32;
    public static final int DIV=36;
    public static final int TIMES=58;
    public static final int BinOp=4;
    public static final int FALSE=42;
    public static final int PortDecl=22;
    public static final int LE=46;
    public static final int Var=12;
    public static final int Integer=7;
    public static final int STRING=57;

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
    public String getGrammarFileName() { return "D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g"; }


    public static class actor_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "actor"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:62:1: actor : ( oneImport )* ACTOR ID ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN (inputs= portDecls )? DOUBLE_EQUAL_ARROW (outputs= portDecls )? COLON ( ignore )* EOF -> ^( Actor ^( Name ID ) ( parameters )? ^( Inputs ( $inputs)? ) ^( Outputs ( $outputs)? ) ) ;
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
        Token DOUBLE_EQUAL_ARROW10=null;
        Token COLON11=null;
        Token EOF13=null;
        CalParser.portDecls_return inputs = null;

        CalParser.portDecls_return outputs = null;

        CalParser.oneImport_return oneImport1 = null;

        CalParser.typePars_return typePars5 = null;

        CalParser.parameters_return parameters8 = null;

        CalParser.ignore_return ignore12 = null;


        Object ACTOR2_tree=null;
        Object ID3_tree=null;
        Object LBRACKET4_tree=null;
        Object RBRACKET6_tree=null;
        Object LPAREN7_tree=null;
        Object RPAREN9_tree=null;
        Object DOUBLE_EQUAL_ARROW10_tree=null;
        Object COLON11_tree=null;
        Object EOF13_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_DOUBLE_EQUAL_ARROW=new RewriteRuleTokenStream(adaptor,"token DOUBLE_EQUAL_ARROW");
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_RBRACKET=new RewriteRuleTokenStream(adaptor,"token RBRACKET");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleTokenStream stream_ACTOR=new RewriteRuleTokenStream(adaptor,"token ACTOR");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_typePars=new RewriteRuleSubtreeStream(adaptor,"rule typePars");
        RewriteRuleSubtreeStream stream_portDecls=new RewriteRuleSubtreeStream(adaptor,"rule portDecls");
        RewriteRuleSubtreeStream stream_parameters=new RewriteRuleSubtreeStream(adaptor,"rule parameters");
        RewriteRuleSubtreeStream stream_oneImport=new RewriteRuleSubtreeStream(adaptor,"rule oneImport");
        RewriteRuleSubtreeStream stream_ignore=new RewriteRuleSubtreeStream(adaptor,"rule ignore");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:62:6: ( ( oneImport )* ACTOR ID ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN (inputs= portDecls )? DOUBLE_EQUAL_ARROW (outputs= portDecls )? COLON ( ignore )* EOF -> ^( Actor ^( Name ID ) ( parameters )? ^( Inputs ( $inputs)? ) ^( Outputs ( $outputs)? ) ) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:62:8: ( oneImport )* ACTOR ID ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN (inputs= portDecls )? DOUBLE_EQUAL_ARROW (outputs= portDecls )? COLON ( ignore )* EOF
            {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:62:8: ( oneImport )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==IMPORT) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:62:8: oneImport
            	    {
            	    pushFollow(FOLLOW_oneImport_in_actor148);
            	    oneImport1=oneImport();

            	    state._fsp--;

            	    stream_oneImport.add(oneImport1.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            ACTOR2=(Token)match(input,ACTOR,FOLLOW_ACTOR_in_actor151);  
            stream_ACTOR.add(ACTOR2);

            ID3=(Token)match(input,ID,FOLLOW_ID_in_actor153);  
            stream_ID.add(ID3);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:63:3: ( LBRACKET ( typePars )? RBRACKET )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==LBRACKET) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:63:4: LBRACKET ( typePars )? RBRACKET
                    {
                    LBRACKET4=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_actor158);  
                    stream_LBRACKET.add(LBRACKET4);

                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:63:13: ( typePars )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==ID) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:63:13: typePars
                            {
                            pushFollow(FOLLOW_typePars_in_actor160);
                            typePars5=typePars();

                            state._fsp--;

                            stream_typePars.add(typePars5.getTree());

                            }
                            break;

                    }

                    RBRACKET6=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_actor163);  
                    stream_RBRACKET.add(RBRACKET6);


                    }
                    break;

            }

            LPAREN7=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_actor169);  
            stream_LPAREN.add(LPAREN7);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:64:10: ( parameters )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==ID) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:64:10: parameters
                    {
                    pushFollow(FOLLOW_parameters_in_actor171);
                    parameters8=parameters();

                    state._fsp--;

                    stream_parameters.add(parameters8.getTree());

                    }
                    break;

            }

            RPAREN9=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_actor174);  
            stream_RPAREN.add(RPAREN9);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:65:9: (inputs= portDecls )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==ID||LA5_0==MULTI) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:65:9: inputs= portDecls
                    {
                    pushFollow(FOLLOW_portDecls_in_actor180);
                    inputs=portDecls();

                    state._fsp--;

                    stream_portDecls.add(inputs.getTree());

                    }
                    break;

            }

            DOUBLE_EQUAL_ARROW10=(Token)match(input,DOUBLE_EQUAL_ARROW,FOLLOW_DOUBLE_EQUAL_ARROW_in_actor183);  
            stream_DOUBLE_EQUAL_ARROW.add(DOUBLE_EQUAL_ARROW10);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:65:47: (outputs= portDecls )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==ID||LA6_0==MULTI) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:65:47: outputs= portDecls
                    {
                    pushFollow(FOLLOW_portDecls_in_actor187);
                    outputs=portDecls();

                    state._fsp--;

                    stream_portDecls.add(outputs.getTree());

                    }
                    break;

            }

            COLON11=(Token)match(input,COLON,FOLLOW_COLON_in_actor190);  
            stream_COLON.add(COLON11);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:65:65: ( ignore )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>=ID && LA7_0<=TRUE)) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:65:65: ignore
            	    {
            	    pushFollow(FOLLOW_ignore_in_actor192);
            	    ignore12=ignore();

            	    state._fsp--;

            	    stream_ignore.add(ignore12.getTree());

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            EOF13=(Token)match(input,EOF,FOLLOW_EOF_in_actor195);  
            stream_EOF.add(EOF13);



            // AST REWRITE
            // elements: inputs, parameters, ID, outputs
            // token labels: 
            // rule labels: retval, inputs, outputs
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_inputs=new RewriteRuleSubtreeStream(adaptor,"rule inputs",inputs!=null?inputs.tree:null);
            RewriteRuleSubtreeStream stream_outputs=new RewriteRuleSubtreeStream(adaptor,"rule outputs",outputs!=null?outputs.tree:null);

            root_0 = (Object)adaptor.nil();
            // 65:77: -> ^( Actor ^( Name ID ) ( parameters )? ^( Inputs ( $inputs)? ) ^( Outputs ( $outputs)? ) )
            {
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:66:5: ^( Actor ^( Name ID ) ( parameters )? ^( Inputs ( $inputs)? ) ^( Outputs ( $outputs)? ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Actor, "Actor"), root_1);

                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:66:13: ^( Name ID )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Name, "Name"), root_2);

                adaptor.addChild(root_2, stream_ID.nextNode());

                adaptor.addChild(root_1, root_2);
                }
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:66:24: ( parameters )?
                if ( stream_parameters.hasNext() ) {
                    adaptor.addChild(root_1, stream_parameters.nextTree());

                }
                stream_parameters.reset();
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:66:36: ^( Inputs ( $inputs)? )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Inputs, "Inputs"), root_2);

                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:66:45: ( $inputs)?
                if ( stream_inputs.hasNext() ) {
                    adaptor.addChild(root_2, stream_inputs.nextTree());

                }
                stream_inputs.reset();

                adaptor.addChild(root_1, root_2);
                }
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:66:55: ^( Outputs ( $outputs)? )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Outputs, "Outputs"), root_2);

                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:66:65: ( $outputs)?
                if ( stream_outputs.hasNext() ) {
                    adaptor.addChild(root_2, stream_outputs.nextTree());

                }
                stream_outputs.reset();

                adaptor.addChild(root_1, root_2);
                }

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
    // $ANTLR end "actor"

    public static class ignore_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ignore"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:68:1: ignore : ( ALL | ARROW | CARET | COLON | COLON_EQUAL | COMMA | DIV | DOT | DOUBLE_DASH_ARROW | DOUBLE_EQUAL_ARROW | DOUBLE_DOT | DOUBLE_COLON | EQ | FALSE | GE | GT | ID | LBRACE | LBRACKET | LPAREN | LE | LT | MINUS | MULTI | NE | NOT | NUMBER | PLUS | RBRACE | RBRACKET | RPAREN | SEMICOLON | SHARP | STRING | TIMES | TRUE );
    public final CalParser.ignore_return ignore() throws RecognitionException {
        CalParser.ignore_return retval = new CalParser.ignore_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set14=null;

        Object set14_tree=null;

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:68:7: ( ALL | ARROW | CARET | COLON | COLON_EQUAL | COMMA | DIV | DOT | DOUBLE_DASH_ARROW | DOUBLE_EQUAL_ARROW | DOUBLE_DOT | DOUBLE_COLON | EQ | FALSE | GE | GT | ID | LBRACE | LBRACKET | LPAREN | LE | LT | MINUS | MULTI | NE | NOT | NUMBER | PLUS | RBRACE | RBRACKET | RPAREN | SEMICOLON | SHARP | STRING | TIMES | TRUE )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:
            {
            root_0 = (Object)adaptor.nil();

            set14=(Token)input.LT(1);
            if ( (input.LA(1)>=ID && input.LA(1)<=TRUE) ) {
                input.consume();
                adaptor.addChild(root_0, (Object)adaptor.create(set14));
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:108:1: oneImport : IMPORT importRest SEMICOLON ;
    public final CalParser.oneImport_return oneImport() throws RecognitionException {
        CalParser.oneImport_return retval = new CalParser.oneImport_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IMPORT15=null;
        Token SEMICOLON17=null;
        CalParser.importRest_return importRest16 = null;


        Object IMPORT15_tree=null;
        Object SEMICOLON17_tree=null;

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:108:10: ( IMPORT importRest SEMICOLON )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:108:12: IMPORT importRest SEMICOLON
            {
            root_0 = (Object)adaptor.nil();

            IMPORT15=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_oneImport388); 
            IMPORT15_tree = (Object)adaptor.create(IMPORT15);
            adaptor.addChild(root_0, IMPORT15_tree);

            pushFollow(FOLLOW_importRest_in_oneImport390);
            importRest16=importRest();

            state._fsp--;

            adaptor.addChild(root_0, importRest16.getTree());
            SEMICOLON17=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_oneImport392); 
            SEMICOLON17_tree = (Object)adaptor.create(SEMICOLON17);
            adaptor.addChild(root_0, SEMICOLON17_tree);


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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:110:1: importRest : ( ALL qualifiedId | qualifiedId ( EQ ID )? );
    public final CalParser.importRest_return importRest() throws RecognitionException {
        CalParser.importRest_return retval = new CalParser.importRest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ALL18=null;
        Token EQ21=null;
        Token ID22=null;
        CalParser.qualifiedId_return qualifiedId19 = null;

        CalParser.qualifiedId_return qualifiedId20 = null;


        Object ALL18_tree=null;
        Object EQ21_tree=null;
        Object ID22_tree=null;

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:110:11: ( ALL qualifiedId | qualifiedId ( EQ ID )? )
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
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:110:13: ALL qualifiedId
                    {
                    root_0 = (Object)adaptor.nil();

                    ALL18=(Token)match(input,ALL,FOLLOW_ALL_in_importRest400); 
                    ALL18_tree = (Object)adaptor.create(ALL18);
                    adaptor.addChild(root_0, ALL18_tree);

                    pushFollow(FOLLOW_qualifiedId_in_importRest402);
                    qualifiedId19=qualifiedId();

                    state._fsp--;

                    adaptor.addChild(root_0, qualifiedId19.getTree());

                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:111:5: qualifiedId ( EQ ID )?
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_qualifiedId_in_importRest408);
                    qualifiedId20=qualifiedId();

                    state._fsp--;

                    adaptor.addChild(root_0, qualifiedId20.getTree());
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:111:17: ( EQ ID )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==EQ) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:111:18: EQ ID
                            {
                            EQ21=(Token)match(input,EQ,FOLLOW_EQ_in_importRest411); 
                            EQ21_tree = (Object)adaptor.create(EQ21);
                            adaptor.addChild(root_0, EQ21_tree);

                            ID22=(Token)match(input,ID,FOLLOW_ID_in_importRest413); 
                            ID22_tree = (Object)adaptor.create(ID22);
                            adaptor.addChild(root_0, ID22_tree);


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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:113:1: qualifiedId : ID ( DOT ID )+ ;
    public final CalParser.qualifiedId_return qualifiedId() throws RecognitionException {
        CalParser.qualifiedId_return retval = new CalParser.qualifiedId_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID23=null;
        Token DOT24=null;
        Token ID25=null;

        Object ID23_tree=null;
        Object DOT24_tree=null;
        Object ID25_tree=null;

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:113:12: ( ID ( DOT ID )+ )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:113:14: ID ( DOT ID )+
            {
            root_0 = (Object)adaptor.nil();

            ID23=(Token)match(input,ID,FOLLOW_ID_in_qualifiedId423); 
            ID23_tree = (Object)adaptor.create(ID23);
            adaptor.addChild(root_0, ID23_tree);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:113:17: ( DOT ID )+
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
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:113:18: DOT ID
            	    {
            	    DOT24=(Token)match(input,DOT,FOLLOW_DOT_in_qualifiedId426); 
            	    DOT24_tree = (Object)adaptor.create(DOT24);
            	    adaptor.addChild(root_0, DOT24_tree);

            	    ID25=(Token)match(input,ID,FOLLOW_ID_in_qualifiedId428); 
            	    ID25_tree = (Object)adaptor.create(ID25);
            	    adaptor.addChild(root_0, ID25_tree);


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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:118:1: parameter : typeAndId ( EQ expression -> ^( Parameter typeAndId ^( Expression expression ) ) | -> ^( Parameter typeAndId ) ) ;
    public final CalParser.parameter_return parameter() throws RecognitionException {
        CalParser.parameter_return retval = new CalParser.parameter_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EQ27=null;
        CalParser.typeAndId_return typeAndId26 = null;

        CalParser.expression_return expression28 = null;


        Object EQ27_tree=null;
        RewriteRuleTokenStream stream_EQ=new RewriteRuleTokenStream(adaptor,"token EQ");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_typeAndId=new RewriteRuleSubtreeStream(adaptor,"rule typeAndId");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:118:10: ( typeAndId ( EQ expression -> ^( Parameter typeAndId ^( Expression expression ) ) | -> ^( Parameter typeAndId ) ) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:118:12: typeAndId ( EQ expression -> ^( Parameter typeAndId ^( Expression expression ) ) | -> ^( Parameter typeAndId ) )
            {
            pushFollow(FOLLOW_typeAndId_in_parameter441);
            typeAndId26=typeAndId();

            state._fsp--;

            stream_typeAndId.add(typeAndId26.getTree());
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:119:3: ( EQ expression -> ^( Parameter typeAndId ^( Expression expression ) ) | -> ^( Parameter typeAndId ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==EQ) ) {
                alt11=1;
            }
            else if ( (LA11_0==RPAREN||LA11_0==COMMA) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:119:4: EQ expression
                    {
                    EQ27=(Token)match(input,EQ,FOLLOW_EQ_in_parameter446);  
                    stream_EQ.add(EQ27);

                    pushFollow(FOLLOW_expression_in_parameter448);
                    expression28=expression();

                    state._fsp--;

                    stream_expression.add(expression28.getTree());


                    // AST REWRITE
                    // elements: typeAndId, expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 119:18: -> ^( Parameter typeAndId ^( Expression expression ) )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:119:21: ^( Parameter typeAndId ^( Expression expression ) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Parameter, "Parameter"), root_1);

                        adaptor.addChild(root_1, stream_typeAndId.nextTree());
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:119:43: ^( Expression expression )
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
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:120:5: 
                    {

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
                    // 120:5: -> ^( Parameter typeAndId )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:120:8: ^( Parameter typeAndId )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Parameter, "Parameter"), root_1);

                        adaptor.addChild(root_1, stream_typeAndId.nextTree());

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
    // $ANTLR end "parameter"

    public static class parameters_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parameters"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:122:1: parameters : parameter ( COMMA parameter )* -> ( parameter )+ ;
    public final CalParser.parameters_return parameters() throws RecognitionException {
        CalParser.parameters_return retval = new CalParser.parameters_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA30=null;
        CalParser.parameter_return parameter29 = null;

        CalParser.parameter_return parameter31 = null;


        Object COMMA30_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_parameter=new RewriteRuleSubtreeStream(adaptor,"rule parameter");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:122:11: ( parameter ( COMMA parameter )* -> ( parameter )+ )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:122:13: parameter ( COMMA parameter )*
            {
            pushFollow(FOLLOW_parameter_in_parameters482);
            parameter29=parameter();

            state._fsp--;

            stream_parameter.add(parameter29.getTree());
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:122:23: ( COMMA parameter )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==COMMA) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:122:24: COMMA parameter
            	    {
            	    COMMA30=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameters485);  
            	    stream_COMMA.add(COMMA30);

            	    pushFollow(FOLLOW_parameter_in_parameters487);
            	    parameter31=parameter();

            	    state._fsp--;

            	    stream_parameter.add(parameter31.getTree());

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);



            // AST REWRITE
            // elements: parameter
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 122:42: -> ( parameter )+
            {
                if ( !(stream_parameter.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_parameter.hasNext() ) {
                    adaptor.addChild(root_0, stream_parameter.nextTree());

                }
                stream_parameter.reset();

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
    // $ANTLR end "parameters"

    public static class portDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "portDecl"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:127:1: portDecl : ( MULTI )? typeAndId -> ^( PortDecl typeAndId ) ;
    public final CalParser.portDecl_return portDecl() throws RecognitionException {
        CalParser.portDecl_return retval = new CalParser.portDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MULTI32=null;
        CalParser.typeAndId_return typeAndId33 = null;


        Object MULTI32_tree=null;
        RewriteRuleTokenStream stream_MULTI=new RewriteRuleTokenStream(adaptor,"token MULTI");
        RewriteRuleSubtreeStream stream_typeAndId=new RewriteRuleSubtreeStream(adaptor,"rule typeAndId");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:127:9: ( ( MULTI )? typeAndId -> ^( PortDecl typeAndId ) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:127:11: ( MULTI )? typeAndId
            {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:127:11: ( MULTI )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==MULTI) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:127:11: MULTI
                    {
                    MULTI32=(Token)match(input,MULTI,FOLLOW_MULTI_in_portDecl504);  
                    stream_MULTI.add(MULTI32);


                    }
                    break;

            }

            pushFollow(FOLLOW_typeAndId_in_portDecl507);
            typeAndId33=typeAndId();

            state._fsp--;

            stream_typeAndId.add(typeAndId33.getTree());


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
            // 127:28: -> ^( PortDecl typeAndId )
            {
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:127:31: ^( PortDecl typeAndId )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PortDecl, "PortDecl"), root_1);

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
    // $ANTLR end "portDecl"

    public static class portDecls_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "portDecls"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:129:1: portDecls : portDecl ( COMMA portDecl )* -> ( portDecl )+ ;
    public final CalParser.portDecls_return portDecls() throws RecognitionException {
        CalParser.portDecls_return retval = new CalParser.portDecls_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA35=null;
        CalParser.portDecl_return portDecl34 = null;

        CalParser.portDecl_return portDecl36 = null;


        Object COMMA35_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_portDecl=new RewriteRuleSubtreeStream(adaptor,"rule portDecl");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:129:10: ( portDecl ( COMMA portDecl )* -> ( portDecl )+ )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:129:12: portDecl ( COMMA portDecl )*
            {
            pushFollow(FOLLOW_portDecl_in_portDecls522);
            portDecl34=portDecl();

            state._fsp--;

            stream_portDecl.add(portDecl34.getTree());
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:129:21: ( COMMA portDecl )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==COMMA) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:129:22: COMMA portDecl
            	    {
            	    COMMA35=(Token)match(input,COMMA,FOLLOW_COMMA_in_portDecls525);  
            	    stream_COMMA.add(COMMA35);

            	    pushFollow(FOLLOW_portDecl_in_portDecls527);
            	    portDecl36=portDecl();

            	    state._fsp--;

            	    stream_portDecl.add(portDecl36.getTree());

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);



            // AST REWRITE
            // elements: portDecl
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 129:39: -> ( portDecl )+
            {
                if ( !(stream_portDecl.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_portDecl.hasNext() ) {
                    adaptor.addChild(root_0, stream_portDecl.nextTree());

                }
                stream_portDecl.reset();

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
    // $ANTLR end "portDecls"

    public static class mainParameter_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "mainParameter"
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:134:1: mainParameter : typeAndId EOF -> ^( Parameter typeAndId ) ;
    public final CalParser.mainParameter_return mainParameter() throws RecognitionException {
        CalParser.mainParameter_return retval = new CalParser.mainParameter_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF38=null;
        CalParser.typeAndId_return typeAndId37 = null;


        Object EOF38_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_typeAndId=new RewriteRuleSubtreeStream(adaptor,"rule typeAndId");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:134:14: ( typeAndId EOF -> ^( Parameter typeAndId ) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:134:16: typeAndId EOF
            {
            pushFollow(FOLLOW_typeAndId_in_mainParameter545);
            typeAndId37=typeAndId();

            state._fsp--;

            stream_typeAndId.add(typeAndId37.getTree());
            EOF38=(Token)match(input,EOF,FOLLOW_EOF_in_mainParameter547);  
            stream_EOF.add(EOF38);



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
            // 134:30: -> ^( Parameter typeAndId )
            {
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:134:33: ^( Parameter typeAndId )
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:136:1: typeAndId : typeName= ID ( ( typeRest )? varName= ID -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName) | -> ^( Var $typeName) ) ;
    public final CalParser.typeAndId_return typeAndId() throws RecognitionException {
        CalParser.typeAndId_return retval = new CalParser.typeAndId_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token typeName=null;
        Token varName=null;
        CalParser.typeRest_return typeRest39 = null;


        Object typeName_tree=null;
        Object varName_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_typeRest=new RewriteRuleSubtreeStream(adaptor,"rule typeRest");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:136:10: (typeName= ID ( ( typeRest )? varName= ID -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName) | -> ^( Var $typeName) ) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:136:12: typeName= ID ( ( typeRest )? varName= ID -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName) | -> ^( Var $typeName) )
            {
            typeName=(Token)match(input,ID,FOLLOW_ID_in_typeAndId564);  
            stream_ID.add(typeName);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:137:3: ( ( typeRest )? varName= ID -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName) | -> ^( Var $typeName) )
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
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:137:4: ( typeRest )? varName= ID
                    {
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:137:4: ( typeRest )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==LBRACKET||LA15_0==LPAREN) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:137:4: typeRest
                            {
                            pushFollow(FOLLOW_typeRest_in_typeAndId569);
                            typeRest39=typeRest();

                            state._fsp--;

                            stream_typeRest.add(typeRest39.getTree());

                            }
                            break;

                    }

                    varName=(Token)match(input,ID,FOLLOW_ID_in_typeAndId574);  
                    stream_ID.add(varName);



                    // AST REWRITE
                    // elements: typeName, varName, typeRest
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
                    // 137:25: -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName)
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:137:28: ^( Type ^( Var $typeName) ( typeRest )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Type, "Type"), root_1);

                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:137:35: ^( Var $typeName)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_2);

                        adaptor.addChild(root_2, stream_typeName.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:137:52: ( typeRest )?
                        if ( stream_typeRest.hasNext() ) {
                            adaptor.addChild(root_1, stream_typeRest.nextTree());

                        }
                        stream_typeRest.reset();

                        adaptor.addChild(root_0, root_1);
                        }
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:137:63: ^( Var $varName)
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
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:138:5: 
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
                    // 138:5: -> ^( Var $typeName)
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:138:8: ^( Var $typeName)
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:140:1: type : ID ( typeRest )? -> ^( Type ^( Var ID ) ( typeRest )? ) ;
    public final CalParser.type_return type() throws RecognitionException {
        CalParser.type_return retval = new CalParser.type_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID40=null;
        CalParser.typeRest_return typeRest41 = null;


        Object ID40_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_typeRest=new RewriteRuleSubtreeStream(adaptor,"rule typeRest");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:140:5: ( ID ( typeRest )? -> ^( Type ^( Var ID ) ( typeRest )? ) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:140:7: ID ( typeRest )?
            {
            ID40=(Token)match(input,ID,FOLLOW_ID_in_type618);  
            stream_ID.add(ID40);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:140:10: ( typeRest )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==LBRACKET||LA17_0==LPAREN) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:140:10: typeRest
                    {
                    pushFollow(FOLLOW_typeRest_in_type620);
                    typeRest41=typeRest();

                    state._fsp--;

                    stream_typeRest.add(typeRest41.getTree());

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
            // 140:20: -> ^( Type ^( Var ID ) ( typeRest )? )
            {
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:140:23: ^( Type ^( Var ID ) ( typeRest )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Type, "Type"), root_1);

                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:140:30: ^( Var ID )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_2);

                adaptor.addChild(root_2, stream_ID.nextNode());

                adaptor.addChild(root_1, root_2);
                }
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:140:40: ( typeRest )?
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:142:1: typeRest : ( LBRACKET ( typePars )? RBRACKET -> ( typePars )? | LPAREN ( typeAttrs )? RPAREN -> ( typeAttrs )? );
    public final CalParser.typeRest_return typeRest() throws RecognitionException {
        CalParser.typeRest_return retval = new CalParser.typeRest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LBRACKET42=null;
        Token RBRACKET44=null;
        Token LPAREN45=null;
        Token RPAREN47=null;
        CalParser.typePars_return typePars43 = null;

        CalParser.typeAttrs_return typeAttrs46 = null;


        Object LBRACKET42_tree=null;
        Object RBRACKET44_tree=null;
        Object LPAREN45_tree=null;
        Object RPAREN47_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_RBRACKET=new RewriteRuleTokenStream(adaptor,"token RBRACKET");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_typePars=new RewriteRuleSubtreeStream(adaptor,"rule typePars");
        RewriteRuleSubtreeStream stream_typeAttrs=new RewriteRuleSubtreeStream(adaptor,"rule typeAttrs");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:142:9: ( LBRACKET ( typePars )? RBRACKET -> ( typePars )? | LPAREN ( typeAttrs )? RPAREN -> ( typeAttrs )? )
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
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:142:11: LBRACKET ( typePars )? RBRACKET
                    {
                    LBRACKET42=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_typeRest643);  
                    stream_LBRACKET.add(LBRACKET42);

                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:142:20: ( typePars )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==ID) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:142:20: typePars
                            {
                            pushFollow(FOLLOW_typePars_in_typeRest645);
                            typePars43=typePars();

                            state._fsp--;

                            stream_typePars.add(typePars43.getTree());

                            }
                            break;

                    }

                    RBRACKET44=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_typeRest648);  
                    stream_RBRACKET.add(RBRACKET44);



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
                    // 142:39: -> ( typePars )?
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:142:42: ( typePars )?
                        if ( stream_typePars.hasNext() ) {
                            adaptor.addChild(root_0, stream_typePars.nextTree());

                        }
                        stream_typePars.reset();

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:143:5: LPAREN ( typeAttrs )? RPAREN
                    {
                    LPAREN45=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_typeRest659);  
                    stream_LPAREN.add(LPAREN45);

                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:143:12: ( typeAttrs )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==ID) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:143:12: typeAttrs
                            {
                            pushFollow(FOLLOW_typeAttrs_in_typeRest661);
                            typeAttrs46=typeAttrs();

                            state._fsp--;

                            stream_typeAttrs.add(typeAttrs46.getTree());

                            }
                            break;

                    }

                    RPAREN47=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_typeRest664);  
                    stream_RPAREN.add(RPAREN47);



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
                    // 143:30: -> ( typeAttrs )?
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:143:33: ( typeAttrs )?
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:145:1: typeAttrs : typeAttr ( COMMA typeAttr )* -> ( typeAttr )+ ;
    public final CalParser.typeAttrs_return typeAttrs() throws RecognitionException {
        CalParser.typeAttrs_return retval = new CalParser.typeAttrs_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA49=null;
        CalParser.typeAttr_return typeAttr48 = null;

        CalParser.typeAttr_return typeAttr50 = null;


        Object COMMA49_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_typeAttr=new RewriteRuleSubtreeStream(adaptor,"rule typeAttr");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:145:10: ( typeAttr ( COMMA typeAttr )* -> ( typeAttr )+ )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:145:12: typeAttr ( COMMA typeAttr )*
            {
            pushFollow(FOLLOW_typeAttr_in_typeAttrs676);
            typeAttr48=typeAttr();

            state._fsp--;

            stream_typeAttr.add(typeAttr48.getTree());
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:145:21: ( COMMA typeAttr )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==COMMA) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:145:22: COMMA typeAttr
            	    {
            	    COMMA49=(Token)match(input,COMMA,FOLLOW_COMMA_in_typeAttrs679);  
            	    stream_COMMA.add(COMMA49);

            	    pushFollow(FOLLOW_typeAttr_in_typeAttrs681);
            	    typeAttr50=typeAttr();

            	    state._fsp--;

            	    stream_typeAttr.add(typeAttr50.getTree());

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
            // 145:39: -> ( typeAttr )+
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:147:1: typeAttr : ID typeAttrRest -> typeAttrRest ;
    public final CalParser.typeAttr_return typeAttr() throws RecognitionException {
        CalParser.typeAttr_return retval = new CalParser.typeAttr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID51=null;
        CalParser.typeAttrRest_return typeAttrRest52 = null;


        Object ID51_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_typeAttrRest=new RewriteRuleSubtreeStream(adaptor,"rule typeAttrRest");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:147:9: ( ID typeAttrRest -> typeAttrRest )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:147:11: ID typeAttrRest
            {
            ID51=(Token)match(input,ID,FOLLOW_ID_in_typeAttr695);  
            stream_ID.add(ID51);

            pushFollow(FOLLOW_typeAttrRest_in_typeAttr697);
            typeAttrRest52=typeAttrRest();

            state._fsp--;

            stream_typeAttrRest.add(typeAttrRest52.getTree());


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
            // 147:27: -> typeAttrRest
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:149:1: typeAttrRest : ( COLON type -> ^( TypeAttr type ) | EQ expression -> ^( ExprAttr ^( Expression expression ) ) );
    public final CalParser.typeAttrRest_return typeAttrRest() throws RecognitionException {
        CalParser.typeAttrRest_return retval = new CalParser.typeAttrRest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON53=null;
        Token EQ55=null;
        CalParser.type_return type54 = null;

        CalParser.expression_return expression56 = null;


        Object COLON53_tree=null;
        Object EQ55_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_EQ=new RewriteRuleTokenStream(adaptor,"token EQ");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:149:13: ( COLON type -> ^( TypeAttr type ) | EQ expression -> ^( ExprAttr ^( Expression expression ) ) )
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
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:149:15: COLON type
                    {
                    COLON53=(Token)match(input,COLON,FOLLOW_COLON_in_typeAttrRest708);  
                    stream_COLON.add(COLON53);

                    pushFollow(FOLLOW_type_in_typeAttrRest710);
                    type54=type();

                    state._fsp--;

                    stream_type.add(type54.getTree());


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
                    // 149:26: -> ^( TypeAttr type )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:149:29: ^( TypeAttr type )
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
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:150:3: EQ expression
                    {
                    EQ55=(Token)match(input,EQ,FOLLOW_EQ_in_typeAttrRest722);  
                    stream_EQ.add(EQ55);

                    pushFollow(FOLLOW_expression_in_typeAttrRest724);
                    expression56=expression();

                    state._fsp--;

                    stream_expression.add(expression56.getTree());


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
                    // 150:17: -> ^( ExprAttr ^( Expression expression ) )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:150:20: ^( ExprAttr ^( Expression expression ) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ExprAttr, "ExprAttr"), root_1);

                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:150:31: ^( Expression expression )
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:152:1: typePars : typePar ( COMMA typePar )* -> ( typePar )+ ;
    public final CalParser.typePars_return typePars() throws RecognitionException {
        CalParser.typePars_return retval = new CalParser.typePars_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA58=null;
        CalParser.typePar_return typePar57 = null;

        CalParser.typePar_return typePar59 = null;


        Object COMMA58_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_typePar=new RewriteRuleSubtreeStream(adaptor,"rule typePar");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:152:9: ( typePar ( COMMA typePar )* -> ( typePar )+ )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:152:11: typePar ( COMMA typePar )*
            {
            pushFollow(FOLLOW_typePar_in_typePars743);
            typePar57=typePar();

            state._fsp--;

            stream_typePar.add(typePar57.getTree());
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:152:19: ( COMMA typePar )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==COMMA) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:152:20: COMMA typePar
            	    {
            	    COMMA58=(Token)match(input,COMMA,FOLLOW_COMMA_in_typePars746);  
            	    stream_COMMA.add(COMMA58);

            	    pushFollow(FOLLOW_typePar_in_typePars748);
            	    typePar59=typePar();

            	    state._fsp--;

            	    stream_typePar.add(typePar59.getTree());

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
            // 152:36: -> ( typePar )+
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:154:1: typePar : ID ( LT type )? -> ^( TypePar ID ( type )? ) ;
    public final CalParser.typePar_return typePar() throws RecognitionException {
        CalParser.typePar_return retval = new CalParser.typePar_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID60=null;
        Token LT61=null;
        CalParser.type_return type62 = null;


        Object ID60_tree=null;
        Object LT61_tree=null;
        RewriteRuleTokenStream stream_LT=new RewriteRuleTokenStream(adaptor,"token LT");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:154:8: ( ID ( LT type )? -> ^( TypePar ID ( type )? ) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:154:10: ID ( LT type )?
            {
            ID60=(Token)match(input,ID,FOLLOW_ID_in_typePar762);  
            stream_ID.add(ID60);

            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:154:13: ( LT type )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==LT) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:154:14: LT type
                    {
                    LT61=(Token)match(input,LT,FOLLOW_LT_in_typePar765);  
                    stream_LT.add(LT61);

                    pushFollow(FOLLOW_type_in_typePar767);
                    type62=type();

                    state._fsp--;

                    stream_type.add(type62.getTree());

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
            // 154:24: -> ^( TypePar ID ( type )? )
            {
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:154:27: ^( TypePar ID ( type )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TypePar, "TypePar"), root_1);

                adaptor.addChild(root_1, stream_ID.nextNode());
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:154:40: ( type )?
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:159:1: mainExpression : expression EOF -> ^( Expression expression ) ;
    public final CalParser.mainExpression_return mainExpression() throws RecognitionException {
        CalParser.mainExpression_return retval = new CalParser.mainExpression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF64=null;
        CalParser.expression_return expression63 = null;


        Object EOF64_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:159:15: ( expression EOF -> ^( Expression expression ) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:159:17: expression EOF
            {
            pushFollow(FOLLOW_expression_in_mainExpression790);
            expression63=expression();

            state._fsp--;

            stream_expression.add(expression63.getTree());
            EOF64=(Token)match(input,EOF,FOLLOW_EOF_in_mainExpression792);  
            stream_EOF.add(EOF64);



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
            // 159:32: -> ^( Expression expression )
            {
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:159:35: ^( Expression expression )
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:161:1: expression : factor ( binop factor )* ;
    public final CalParser.expression_return expression() throws RecognitionException {
        CalParser.expression_return retval = new CalParser.expression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CalParser.factor_return factor65 = null;

        CalParser.binop_return binop66 = null;

        CalParser.factor_return factor67 = null;



        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:161:11: ( factor ( binop factor )* )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:161:13: factor ( binop factor )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_factor_in_expression807);
            factor65=factor();

            state._fsp--;

            adaptor.addChild(root_0, factor65.getTree());
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:161:20: ( binop factor )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==CARET||LA25_0==DIV||LA25_0==MINUS||LA25_0==PLUS||LA25_0==TIMES) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:161:21: binop factor
            	    {
            	    pushFollow(FOLLOW_binop_in_expression810);
            	    binop66=binop();

            	    state._fsp--;

            	    adaptor.addChild(root_0, binop66.getTree());
            	    pushFollow(FOLLOW_factor_in_expression812);
            	    factor67=factor();

            	    state._fsp--;

            	    adaptor.addChild(root_0, factor67.getTree());

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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:163:1: unop : (op= MINUS | op= NOT ) -> ^( UnOp $op) ;
    public final CalParser.unop_return unop() throws RecognitionException {
        CalParser.unop_return retval = new CalParser.unop_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token op=null;

        Object op_tree=null;
        RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
        RewriteRuleTokenStream stream_MINUS=new RewriteRuleTokenStream(adaptor,"token MINUS");

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:163:5: ( (op= MINUS | op= NOT ) -> ^( UnOp $op) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:163:7: (op= MINUS | op= NOT )
            {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:163:7: (op= MINUS | op= NOT )
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
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:163:8: op= MINUS
                    {
                    op=(Token)match(input,MINUS,FOLLOW_MINUS_in_unop824);  
                    stream_MINUS.add(op);


                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:163:19: op= NOT
                    {
                    op=(Token)match(input,NOT,FOLLOW_NOT_in_unop830);  
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
            // 163:27: -> ^( UnOp $op)
            {
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:163:30: ^( UnOp $op)
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:165:1: binop : (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET ) -> ^( BinOp $op) ;
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
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:165:6: ( (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET ) -> ^( BinOp $op) )
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:165:8: (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET )
            {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:165:8: (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET )
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
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:165:9: op= PLUS
                    {
                    op=(Token)match(input,PLUS,FOLLOW_PLUS_in_binop850);  
                    stream_PLUS.add(op);


                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:165:19: op= MINUS
                    {
                    op=(Token)match(input,MINUS,FOLLOW_MINUS_in_binop856);  
                    stream_MINUS.add(op);


                    }
                    break;
                case 3 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:165:30: op= TIMES
                    {
                    op=(Token)match(input,TIMES,FOLLOW_TIMES_in_binop862);  
                    stream_TIMES.add(op);


                    }
                    break;
                case 4 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:165:41: op= DIV
                    {
                    op=(Token)match(input,DIV,FOLLOW_DIV_in_binop868);  
                    stream_DIV.add(op);


                    }
                    break;
                case 5 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:165:50: op= CARET
                    {
                    op=(Token)match(input,CARET,FOLLOW_CARET_in_binop874);  
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
            // 165:60: -> ^( BinOp $op)
            {
                // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:165:63: ^( BinOp $op)
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:167:1: factor : ( term | unop term -> ^( Expression unop term ) );
    public final CalParser.factor_return factor() throws RecognitionException {
        CalParser.factor_return retval = new CalParser.factor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CalParser.term_return term68 = null;

        CalParser.unop_return unop69 = null;

        CalParser.term_return term70 = null;


        RewriteRuleSubtreeStream stream_unop=new RewriteRuleSubtreeStream(adaptor,"rule unop");
        RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:167:7: ( term | unop term -> ^( Expression unop term ) )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==ID||LA28_0==LPAREN||LA28_0==FALSE||LA28_0==NUMBER||LA28_0==STRING||LA28_0==TRUE) ) {
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
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:167:9: term
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_term_in_factor891);
                    term68=term();

                    state._fsp--;

                    adaptor.addChild(root_0, term68.getTree());

                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:168:3: unop term
                    {
                    pushFollow(FOLLOW_unop_in_factor895);
                    unop69=unop();

                    state._fsp--;

                    stream_unop.add(unop69.getTree());
                    pushFollow(FOLLOW_term_in_factor897);
                    term70=term();

                    state._fsp--;

                    stream_term.add(term70.getTree());


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
                    // 168:13: -> ^( Expression unop term )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:168:16: ^( Expression unop term )
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:170:1: term : ( atom | LPAREN expression RPAREN -> ^( Expression expression ) );
    public final CalParser.term_return term() throws RecognitionException {
        CalParser.term_return retval = new CalParser.term_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LPAREN72=null;
        Token RPAREN74=null;
        CalParser.atom_return atom71 = null;

        CalParser.expression_return expression73 = null;


        Object LPAREN72_tree=null;
        Object RPAREN74_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:170:5: ( atom | LPAREN expression RPAREN -> ^( Expression expression ) )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==ID||LA29_0==FALSE||LA29_0==NUMBER||LA29_0==STRING||LA29_0==TRUE) ) {
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
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:170:7: atom
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_atom_in_term914);
                    atom71=atom();

                    state._fsp--;

                    adaptor.addChild(root_0, atom71.getTree());

                    }
                    break;
                case 2 :
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:171:5: LPAREN expression RPAREN
                    {
                    LPAREN72=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_term920);  
                    stream_LPAREN.add(LPAREN72);

                    pushFollow(FOLLOW_expression_in_term922);
                    expression73=expression();

                    state._fsp--;

                    stream_expression.add(expression73.getTree());
                    RPAREN74=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_term924);  
                    stream_RPAREN.add(RPAREN74);



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
                    // 171:30: -> ^( Expression expression )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:171:33: ^( Expression expression )
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
    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:173:1: atom : ( ID -> ^( Var ID ) | NUMBER -> ^( Integer NUMBER ) | STRING -> ^( String STRING ) | TRUE -> ^( Boolean TRUE ) | FALSE -> ^( Boolean FALSE ) );
    public final CalParser.atom_return atom() throws RecognitionException {
        CalParser.atom_return retval = new CalParser.atom_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID75=null;
        Token NUMBER76=null;
        Token STRING77=null;
        Token TRUE78=null;
        Token FALSE79=null;

        Object ID75_tree=null;
        Object NUMBER76_tree=null;
        Object STRING77_tree=null;
        Object TRUE78_tree=null;
        Object FALSE79_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_FALSE=new RewriteRuleTokenStream(adaptor,"token FALSE");
        RewriteRuleTokenStream stream_TRUE=new RewriteRuleTokenStream(adaptor,"token TRUE");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");
        RewriteRuleTokenStream stream_NUMBER=new RewriteRuleTokenStream(adaptor,"token NUMBER");

        try {
            // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:173:5: ( ID -> ^( Var ID ) | NUMBER -> ^( Integer NUMBER ) | STRING -> ^( String STRING ) | TRUE -> ^( Boolean TRUE ) | FALSE -> ^( Boolean FALSE ) )
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
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:173:7: ID
                    {
                    ID75=(Token)match(input,ID,FOLLOW_ID_in_atom939);  
                    stream_ID.add(ID75);



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
                    // 173:10: -> ^( Var ID )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:173:13: ^( Var ID )
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
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:174:3: NUMBER
                    {
                    NUMBER76=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_atom951);  
                    stream_NUMBER.add(NUMBER76);



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
                    // 174:10: -> ^( Integer NUMBER )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:174:13: ^( Integer NUMBER )
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
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:175:3: STRING
                    {
                    STRING77=(Token)match(input,STRING,FOLLOW_STRING_in_atom963);  
                    stream_STRING.add(STRING77);



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
                    // 175:10: -> ^( String STRING )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:175:13: ^( String STRING )
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
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:176:3: TRUE
                    {
                    TRUE78=(Token)match(input,TRUE,FOLLOW_TRUE_in_atom975);  
                    stream_TRUE.add(TRUE78);



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
                    // 176:8: -> ^( Boolean TRUE )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:176:11: ^( Boolean TRUE )
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
                    // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:177:3: FALSE
                    {
                    FALSE79=(Token)match(input,FALSE,FOLLOW_FALSE_in_atom987);  
                    stream_FALSE.add(FALSE79);



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
                    // 177:9: -> ^( Boolean FALSE )
                    {
                        // D:\\repositories\\mwipliez\\graphiti-editor\\configuration\\src\\Cal.g:177:12: ^( Boolean FALSE )
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


 

    public static final BitSet FOLLOW_oneImport_in_actor148 = new BitSet(new long[]{0x1000000000800000L});
    public static final BitSet FOLLOW_ACTOR_in_actor151 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_actor153 = new BitSet(new long[]{0x000000000A000000L});
    public static final BitSet FOLLOW_LBRACKET_in_actor158 = new BitSet(new long[]{0x0000000005000000L});
    public static final BitSet FOLLOW_typePars_in_actor160 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_RBRACKET_in_actor163 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_LPAREN_in_actor169 = new BitSet(new long[]{0x0000000011000000L});
    public static final BitSet FOLLOW_parameters_in_actor171 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_RPAREN_in_actor174 = new BitSet(new long[]{0x0002000021000000L});
    public static final BitSet FOLLOW_portDecls_in_actor180 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_DOUBLE_EQUAL_ARROW_in_actor183 = new BitSet(new long[]{0x0002000041000000L});
    public static final BitSet FOLLOW_portDecls_in_actor187 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_COLON_in_actor190 = new BitSet(new long[]{0x0FFFFFFFFF000000L});
    public static final BitSet FOLLOW_ignore_in_actor192 = new BitSet(new long[]{0x0FFFFFFFFF000000L});
    public static final BitSet FOLLOW_EOF_in_actor195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_ignore0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_oneImport388 = new BitSet(new long[]{0x0000000081000000L});
    public static final BitSet FOLLOW_importRest_in_oneImport390 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_oneImport392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALL_in_importRest400 = new BitSet(new long[]{0x0000000081000000L});
    public static final BitSet FOLLOW_qualifiedId_in_importRest402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualifiedId_in_importRest408 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_EQ_in_importRest411 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_importRest413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_qualifiedId423 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_DOT_in_qualifiedId426 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_qualifiedId428 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_typeAndId_in_parameter441 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_EQ_in_parameter446 = new BitSet(new long[]{0x0A19040009000000L});
    public static final BitSet FOLLOW_expression_in_parameter448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parameter_in_parameters482 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_COMMA_in_parameters485 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_parameter_in_parameters487 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_MULTI_in_portDecl504 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_typeAndId_in_portDecl507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_portDecl_in_portDecls522 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_COMMA_in_portDecls525 = new BitSet(new long[]{0x0002000001000000L});
    public static final BitSet FOLLOW_portDecl_in_portDecls527 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_typeAndId_in_mainParameter545 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_mainParameter547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_typeAndId564 = new BitSet(new long[]{0x000000000B000002L});
    public static final BitSet FOLLOW_typeRest_in_typeAndId569 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_ID_in_typeAndId574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_type618 = new BitSet(new long[]{0x000000000A000002L});
    public static final BitSet FOLLOW_typeRest_in_type620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_typeRest643 = new BitSet(new long[]{0x0000000005000000L});
    public static final BitSet FOLLOW_typePars_in_typeRest645 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_RBRACKET_in_typeRest648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_typeRest659 = new BitSet(new long[]{0x0000000011000000L});
    public static final BitSet FOLLOW_typeAttrs_in_typeRest661 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_RPAREN_in_typeRest664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeAttr_in_typeAttrs676 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_COMMA_in_typeAttrs679 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_typeAttr_in_typeAttrs681 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_ID_in_typeAttr695 = new BitSet(new long[]{0x0000020040000000L});
    public static final BitSet FOLLOW_typeAttrRest_in_typeAttr697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_typeAttrRest708 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_type_in_typeAttrRest710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_typeAttrRest722 = new BitSet(new long[]{0x0A19040009000000L});
    public static final BitSet FOLLOW_expression_in_typeAttrRest724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typePar_in_typePars743 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_COMMA_in_typePars746 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_typePar_in_typePars748 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_ID_in_typePar762 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_LT_in_typePar765 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_type_in_typePar767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_mainExpression790 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_mainExpression792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_factor_in_expression807 = new BitSet(new long[]{0x0421001200000002L});
    public static final BitSet FOLLOW_binop_in_expression810 = new BitSet(new long[]{0x0A19040009000000L});
    public static final BitSet FOLLOW_factor_in_expression812 = new BitSet(new long[]{0x0421001200000002L});
    public static final BitSet FOLLOW_MINUS_in_unop824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_unop830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_binop850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_binop856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TIMES_in_binop862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIV_in_binop868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CARET_in_binop874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_factor891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unop_in_factor895 = new BitSet(new long[]{0x0A10040009000000L});
    public static final BitSet FOLLOW_term_in_factor897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_term914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_term920 = new BitSet(new long[]{0x0A19040009000000L});
    public static final BitSet FOLLOW_expression_in_term922 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_RPAREN_in_term924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_atom939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_atom951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_atom975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_atom987 = new BitSet(new long[]{0x0000000000000002L});

}