// $ANTLR 3.1.2 D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g 2009-04-05 15:21:07

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class CalParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "Connector", "EntityDecl", "EntityExpr", "EntityPar", "Network", "StructureStmt", "VarDecl", "Actor", "Name", "Inputs", "Outputs", "PortDecl", "Parameter", "Type", "TypeAttr", "ExprAttr", "TypePar", "BinOp", "Boolean", "Expression", "Integer", "List", "Minus", "Not", "Real", "String", "UnOp", "Var", "NETWORK", "LBRACKET", "RBRACKET", "LPAREN", "RPAREN", "DOUBLE_EQUAL_ARROW", "COLON", "END", "VAR", "MUTABLE", "EQ", "COLON_EQUAL", "SEMICOLON", "ENTITIES", "ID", "COMMA", "STRUCTURE", "DOUBLE_DASH_ARROW", "DOT", "LBRACE", "RBRACE", "ACTOR", "ALL", "ARROW", "CARET", "DIV", "DOUBLE_DOT", "DOUBLE_COLON", "FALSE", "FLOAT", "GE", "GT", "INTEGER", "LE", "LT", "MINUS", "MULTI", "NE", "NOT", "PLUS", "SHARP", "STRING", "TIMES", "TRUE", "IMPORT", "LINE_COMMENT", "MULTI_LINE_COMMENT", "WHITESPACE"
    };
    public static final int StructureStmt=9;
    public static final int LT=66;
    public static final int Inputs=13;
    public static final int LBRACE=51;
    public static final int FLOAT=61;
    public static final int MULTI=68;
    public static final int Actor=11;
    public static final int VarDecl=10;
    public static final int NOT=70;
    public static final int ID=46;
    public static final int EOF=-1;
    public static final int DOUBLE_DOT=58;
    public static final int STRUCTURE=48;
    public static final int LPAREN=35;
    public static final int LBRACKET=33;
    public static final int RPAREN=36;
    public static final int IMPORT=76;
    public static final int COLON_EQUAL=43;
    public static final int COMMA=47;
    public static final int CARET=56;
    public static final int ALL=54;
    public static final int TypeAttr=18;
    public static final int ENTITIES=45;
    public static final int Real=28;
    public static final int PLUS=71;
    public static final int VAR=40;
    public static final int Network=8;
    public static final int String=29;
    public static final int RBRACKET=34;
    public static final int EQ=42;
    public static final int DOT=50;
    public static final int EntityDecl=5;
    public static final int NE=69;
    public static final int INTEGER=64;
    public static final int Outputs=14;
    public static final int DOUBLE_EQUAL_ARROW=37;
    public static final int GE=62;
    public static final int DOUBLE_DASH_ARROW=49;
    public static final int EntityPar=7;
    public static final int SHARP=72;
    public static final int RBRACE=52;
    public static final int Type=17;
    public static final int LINE_COMMENT=77;
    public static final int WHITESPACE=79;
    public static final int SEMICOLON=44;
    public static final int NETWORK=32;
    public static final int MINUS=67;
    public static final int TRUE=75;
    public static final int Expression=23;
    public static final int MULTI_LINE_COMMENT=78;
    public static final int Parameter=16;
    public static final int List=25;
    public static final int TypePar=20;
    public static final int COLON=38;
    public static final int UnOp=30;
    public static final int Minus=26;
    public static final int Connector=4;
    public static final int DOUBLE_COLON=59;
    public static final int Boolean=22;
    public static final int ACTOR=53;
    public static final int ExprAttr=19;
    public static final int EntityExpr=6;
    public static final int Not=27;
    public static final int Name=12;
    public static final int GT=63;
    public static final int ARROW=55;
    public static final int DIV=57;
    public static final int TIMES=74;
    public static final int BinOp=21;
    public static final int END=39;
    public static final int FALSE=60;
    public static final int PortDecl=15;
    public static final int MUTABLE=41;
    public static final int LE=65;
    public static final int Integer=24;
    public static final int Var=31;
    public static final int STRING=73;

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
    public String getGrammarFileName() { return "D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g"; }


    public static class network_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "network"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:76:1: network : NETWORK qualifiedId ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN (inputs= portDecls )? DOUBLE_EQUAL_ARROW (outputs= portDecls )? COLON ( oneImport )* ( varDeclSection )? ( entitySection )? ( structureSection )? END EOF -> ^( Network ^( Name ID ) ( parameters )? ^( Inputs ( $inputs)? ) ^( Outputs ( $outputs)? ) ( varDeclSection )? ( entitySection )? ( structureSection )? ) ;
    public final CalParser.network_return network() throws RecognitionException {
        CalParser.network_return retval = new CalParser.network_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NETWORK1=null;
        Token LBRACKET3=null;
        Token RBRACKET5=null;
        Token LPAREN6=null;
        Token RPAREN8=null;
        Token DOUBLE_EQUAL_ARROW9=null;
        Token COLON10=null;
        Token END15=null;
        Token EOF16=null;
        CalParser.portDecls_return inputs = null;

        CalParser.portDecls_return outputs = null;

        CalParser.qualifiedId_return qualifiedId2 = null;

        CalParser.typePars_return typePars4 = null;

        CalParser.parameters_return parameters7 = null;

        CalParser.oneImport_return oneImport11 = null;

        CalParser.varDeclSection_return varDeclSection12 = null;

        CalParser.entitySection_return entitySection13 = null;

        CalParser.structureSection_return structureSection14 = null;


        Object NETWORK1_tree=null;
        Object LBRACKET3_tree=null;
        Object RBRACKET5_tree=null;
        Object LPAREN6_tree=null;
        Object RPAREN8_tree=null;
        Object DOUBLE_EQUAL_ARROW9_tree=null;
        Object COLON10_tree=null;
        Object END15_tree=null;
        Object EOF16_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_DOUBLE_EQUAL_ARROW=new RewriteRuleTokenStream(adaptor,"token DOUBLE_EQUAL_ARROW");
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_NETWORK=new RewriteRuleTokenStream(adaptor,"token NETWORK");
        RewriteRuleTokenStream stream_RBRACKET=new RewriteRuleTokenStream(adaptor,"token RBRACKET");
        RewriteRuleTokenStream stream_END=new RewriteRuleTokenStream(adaptor,"token END");
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_qualifiedId=new RewriteRuleSubtreeStream(adaptor,"rule qualifiedId");
        RewriteRuleSubtreeStream stream_typePars=new RewriteRuleSubtreeStream(adaptor,"rule typePars");
        RewriteRuleSubtreeStream stream_varDeclSection=new RewriteRuleSubtreeStream(adaptor,"rule varDeclSection");
        RewriteRuleSubtreeStream stream_structureSection=new RewriteRuleSubtreeStream(adaptor,"rule structureSection");
        RewriteRuleSubtreeStream stream_entitySection=new RewriteRuleSubtreeStream(adaptor,"rule entitySection");
        RewriteRuleSubtreeStream stream_portDecls=new RewriteRuleSubtreeStream(adaptor,"rule portDecls");
        RewriteRuleSubtreeStream stream_parameters=new RewriteRuleSubtreeStream(adaptor,"rule parameters");
        RewriteRuleSubtreeStream stream_oneImport=new RewriteRuleSubtreeStream(adaptor,"rule oneImport");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:76:8: ( NETWORK qualifiedId ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN (inputs= portDecls )? DOUBLE_EQUAL_ARROW (outputs= portDecls )? COLON ( oneImport )* ( varDeclSection )? ( entitySection )? ( structureSection )? END EOF -> ^( Network ^( Name ID ) ( parameters )? ^( Inputs ( $inputs)? ) ^( Outputs ( $outputs)? ) ( varDeclSection )? ( entitySection )? ( structureSection )? ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:76:10: NETWORK qualifiedId ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN (inputs= portDecls )? DOUBLE_EQUAL_ARROW (outputs= portDecls )? COLON ( oneImport )* ( varDeclSection )? ( entitySection )? ( structureSection )? END EOF
            {
            NETWORK1=(Token)match(input,NETWORK,FOLLOW_NETWORK_in_network196);  
            stream_NETWORK.add(NETWORK1);

            pushFollow(FOLLOW_qualifiedId_in_network198);
            qualifiedId2=qualifiedId();

            state._fsp--;

            stream_qualifiedId.add(qualifiedId2.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:76:30: ( LBRACKET ( typePars )? RBRACKET )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==LBRACKET) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:76:31: LBRACKET ( typePars )? RBRACKET
                    {
                    LBRACKET3=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_network201);  
                    stream_LBRACKET.add(LBRACKET3);

                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:76:40: ( typePars )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==ID) ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:76:40: typePars
                            {
                            pushFollow(FOLLOW_typePars_in_network203);
                            typePars4=typePars();

                            state._fsp--;

                            stream_typePars.add(typePars4.getTree());

                            }
                            break;

                    }

                    RBRACKET5=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_network206);  
                    stream_RBRACKET.add(RBRACKET5);


                    }
                    break;

            }

            LPAREN6=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_network212);  
            stream_LPAREN.add(LPAREN6);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:77:10: ( parameters )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==ID) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:77:10: parameters
                    {
                    pushFollow(FOLLOW_parameters_in_network214);
                    parameters7=parameters();

                    state._fsp--;

                    stream_parameters.add(parameters7.getTree());

                    }
                    break;

            }

            RPAREN8=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_network217);  
            stream_RPAREN.add(RPAREN8);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:78:9: (inputs= portDecls )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==ID||LA4_0==MULTI) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:78:9: inputs= portDecls
                    {
                    pushFollow(FOLLOW_portDecls_in_network223);
                    inputs=portDecls();

                    state._fsp--;

                    stream_portDecls.add(inputs.getTree());

                    }
                    break;

            }

            DOUBLE_EQUAL_ARROW9=(Token)match(input,DOUBLE_EQUAL_ARROW,FOLLOW_DOUBLE_EQUAL_ARROW_in_network226);  
            stream_DOUBLE_EQUAL_ARROW.add(DOUBLE_EQUAL_ARROW9);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:78:47: (outputs= portDecls )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==ID||LA5_0==MULTI) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:78:47: outputs= portDecls
                    {
                    pushFollow(FOLLOW_portDecls_in_network230);
                    outputs=portDecls();

                    state._fsp--;

                    stream_portDecls.add(outputs.getTree());

                    }
                    break;

            }

            COLON10=(Token)match(input,COLON,FOLLOW_COLON_in_network233);  
            stream_COLON.add(COLON10);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:79:3: ( oneImport )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==IMPORT) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:79:3: oneImport
            	    {
            	    pushFollow(FOLLOW_oneImport_in_network237);
            	    oneImport11=oneImport();

            	    state._fsp--;

            	    stream_oneImport.add(oneImport11.getTree());

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:79:14: ( varDeclSection )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==VAR) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:79:14: varDeclSection
                    {
                    pushFollow(FOLLOW_varDeclSection_in_network240);
                    varDeclSection12=varDeclSection();

                    state._fsp--;

                    stream_varDeclSection.add(varDeclSection12.getTree());

                    }
                    break;

            }

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:80:3: ( entitySection )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==ENTITIES) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:80:3: entitySection
                    {
                    pushFollow(FOLLOW_entitySection_in_network245);
                    entitySection13=entitySection();

                    state._fsp--;

                    stream_entitySection.add(entitySection13.getTree());

                    }
                    break;

            }

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:80:18: ( structureSection )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==STRUCTURE) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:80:18: structureSection
                    {
                    pushFollow(FOLLOW_structureSection_in_network248);
                    structureSection14=structureSection();

                    state._fsp--;

                    stream_structureSection.add(structureSection14.getTree());

                    }
                    break;

            }

            END15=(Token)match(input,END,FOLLOW_END_in_network253);  
            stream_END.add(END15);

            EOF16=(Token)match(input,EOF,FOLLOW_EOF_in_network255);  
            stream_EOF.add(EOF16);



            // AST REWRITE
            // elements: outputs, inputs, varDeclSection, parameters, structureSection, entitySection
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
            // 81:11: -> ^( Network ^( Name ID ) ( parameters )? ^( Inputs ( $inputs)? ) ^( Outputs ( $outputs)? ) ( varDeclSection )? ( entitySection )? ( structureSection )? )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:82:5: ^( Network ^( Name ID ) ( parameters )? ^( Inputs ( $inputs)? ) ^( Outputs ( $outputs)? ) ( varDeclSection )? ( entitySection )? ( structureSection )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Network, "Network"), root_1);

                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:82:15: ^( Name ID )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Name, "Name"), root_2);

                adaptor.addChild(root_2, (Object)adaptor.create(ID, "ID"));

                adaptor.addChild(root_1, root_2);
                }
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:82:26: ( parameters )?
                if ( stream_parameters.hasNext() ) {
                    adaptor.addChild(root_1, stream_parameters.nextTree());

                }
                stream_parameters.reset();
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:82:38: ^( Inputs ( $inputs)? )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Inputs, "Inputs"), root_2);

                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:82:47: ( $inputs)?
                if ( stream_inputs.hasNext() ) {
                    adaptor.addChild(root_2, stream_inputs.nextTree());

                }
                stream_inputs.reset();

                adaptor.addChild(root_1, root_2);
                }
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:82:57: ^( Outputs ( $outputs)? )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Outputs, "Outputs"), root_2);

                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:82:67: ( $outputs)?
                if ( stream_outputs.hasNext() ) {
                    adaptor.addChild(root_2, stream_outputs.nextTree());

                }
                stream_outputs.reset();

                adaptor.addChild(root_1, root_2);
                }
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:83:7: ( varDeclSection )?
                if ( stream_varDeclSection.hasNext() ) {
                    adaptor.addChild(root_1, stream_varDeclSection.nextTree());

                }
                stream_varDeclSection.reset();
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:83:23: ( entitySection )?
                if ( stream_entitySection.hasNext() ) {
                    adaptor.addChild(root_1, stream_entitySection.nextTree());

                }
                stream_entitySection.reset();
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:83:38: ( structureSection )?
                if ( stream_structureSection.hasNext() ) {
                    adaptor.addChild(root_1, stream_structureSection.nextTree());

                }
                stream_structureSection.reset();

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
    // $ANTLR end "network"

    public static class varDeclSection_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "varDeclSection"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:86:1: varDeclSection : VAR ( varDecl )+ -> ( varDecl )+ ;
    public final CalParser.varDeclSection_return varDeclSection() throws RecognitionException {
        CalParser.varDeclSection_return retval = new CalParser.varDeclSection_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token VAR17=null;
        CalParser.varDecl_return varDecl18 = null;


        Object VAR17_tree=null;
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleSubtreeStream stream_varDecl=new RewriteRuleSubtreeStream(adaptor,"rule varDecl");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:86:15: ( VAR ( varDecl )+ -> ( varDecl )+ )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:86:17: VAR ( varDecl )+
            {
            VAR17=(Token)match(input,VAR,FOLLOW_VAR_in_varDeclSection313);  
            stream_VAR.add(VAR17);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:86:21: ( varDecl )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==MUTABLE||LA10_0==ID) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:86:21: varDecl
            	    {
            	    pushFollow(FOLLOW_varDecl_in_varDeclSection315);
            	    varDecl18=varDecl();

            	    state._fsp--;

            	    stream_varDecl.add(varDecl18.getTree());

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



            // AST REWRITE
            // elements: varDecl
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 86:30: -> ( varDecl )+
            {
                if ( !(stream_varDecl.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_varDecl.hasNext() ) {
                    adaptor.addChild(root_0, stream_varDecl.nextTree());

                }
                stream_varDecl.reset();

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
    // $ANTLR end "varDeclSection"

    public static class varDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "varDecl"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:88:1: varDecl : ( MUTABLE )? typeAndId ( ( EQ | COLON_EQUAL ) expression -> ^( VarDecl typeAndId ^( Expression expression ) ) | -> ^( VarDecl typeAndId ) ) SEMICOLON ;
    public final CalParser.varDecl_return varDecl() throws RecognitionException {
        CalParser.varDecl_return retval = new CalParser.varDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MUTABLE19=null;
        Token EQ21=null;
        Token COLON_EQUAL22=null;
        Token SEMICOLON24=null;
        CalParser.typeAndId_return typeAndId20 = null;

        CalParser.expression_return expression23 = null;


        Object MUTABLE19_tree=null;
        Object EQ21_tree=null;
        Object COLON_EQUAL22_tree=null;
        Object SEMICOLON24_tree=null;
        RewriteRuleTokenStream stream_COLON_EQUAL=new RewriteRuleTokenStream(adaptor,"token COLON_EQUAL");
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleTokenStream stream_EQ=new RewriteRuleTokenStream(adaptor,"token EQ");
        RewriteRuleTokenStream stream_MUTABLE=new RewriteRuleTokenStream(adaptor,"token MUTABLE");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_typeAndId=new RewriteRuleSubtreeStream(adaptor,"rule typeAndId");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:88:8: ( ( MUTABLE )? typeAndId ( ( EQ | COLON_EQUAL ) expression -> ^( VarDecl typeAndId ^( Expression expression ) ) | -> ^( VarDecl typeAndId ) ) SEMICOLON )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:88:10: ( MUTABLE )? typeAndId ( ( EQ | COLON_EQUAL ) expression -> ^( VarDecl typeAndId ^( Expression expression ) ) | -> ^( VarDecl typeAndId ) ) SEMICOLON
            {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:88:10: ( MUTABLE )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==MUTABLE) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:88:10: MUTABLE
                    {
                    MUTABLE19=(Token)match(input,MUTABLE,FOLLOW_MUTABLE_in_varDecl328);  
                    stream_MUTABLE.add(MUTABLE19);


                    }
                    break;

            }

            pushFollow(FOLLOW_typeAndId_in_varDecl331);
            typeAndId20=typeAndId();

            state._fsp--;

            stream_typeAndId.add(typeAndId20.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:89:3: ( ( EQ | COLON_EQUAL ) expression -> ^( VarDecl typeAndId ^( Expression expression ) ) | -> ^( VarDecl typeAndId ) )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( ((LA13_0>=EQ && LA13_0<=COLON_EQUAL)) ) {
                alt13=1;
            }
            else if ( (LA13_0==SEMICOLON) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:89:4: ( EQ | COLON_EQUAL ) expression
                    {
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:89:4: ( EQ | COLON_EQUAL )
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==EQ) ) {
                        alt12=1;
                    }
                    else if ( (LA12_0==COLON_EQUAL) ) {
                        alt12=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 12, 0, input);

                        throw nvae;
                    }
                    switch (alt12) {
                        case 1 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:89:5: EQ
                            {
                            EQ21=(Token)match(input,EQ,FOLLOW_EQ_in_varDecl337);  
                            stream_EQ.add(EQ21);


                            }
                            break;
                        case 2 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:89:10: COLON_EQUAL
                            {
                            COLON_EQUAL22=(Token)match(input,COLON_EQUAL,FOLLOW_COLON_EQUAL_in_varDecl341);  
                            stream_COLON_EQUAL.add(COLON_EQUAL22);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_expression_in_varDecl344);
                    expression23=expression();

                    state._fsp--;

                    stream_expression.add(expression23.getTree());


                    // AST REWRITE
                    // elements: expression, typeAndId
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 89:34: -> ^( VarDecl typeAndId ^( Expression expression ) )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:89:37: ^( VarDecl typeAndId ^( Expression expression ) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VarDecl, "VarDecl"), root_1);

                        adaptor.addChild(root_1, stream_typeAndId.nextTree());
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:89:57: ^( Expression expression )
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
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:90:5: 
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
                    // 90:5: -> ^( VarDecl typeAndId )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:90:8: ^( VarDecl typeAndId )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VarDecl, "VarDecl"), root_1);

                        adaptor.addChild(root_1, stream_typeAndId.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }

            SEMICOLON24=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_varDecl373);  
            stream_SEMICOLON.add(SEMICOLON24);


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
    // $ANTLR end "varDecl"

    public static class entitySection_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "entitySection"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:93:1: entitySection : ENTITIES ( entityDecl )+ -> ( entityDecl )+ ;
    public final CalParser.entitySection_return entitySection() throws RecognitionException {
        CalParser.entitySection_return retval = new CalParser.entitySection_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ENTITIES25=null;
        CalParser.entityDecl_return entityDecl26 = null;


        Object ENTITIES25_tree=null;
        RewriteRuleTokenStream stream_ENTITIES=new RewriteRuleTokenStream(adaptor,"token ENTITIES");
        RewriteRuleSubtreeStream stream_entityDecl=new RewriteRuleSubtreeStream(adaptor,"rule entityDecl");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:93:14: ( ENTITIES ( entityDecl )+ -> ( entityDecl )+ )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:93:16: ENTITIES ( entityDecl )+
            {
            ENTITIES25=(Token)match(input,ENTITIES,FOLLOW_ENTITIES_in_entitySection381);  
            stream_ENTITIES.add(ENTITIES25);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:93:25: ( entityDecl )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==ID) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:93:25: entityDecl
            	    {
            	    pushFollow(FOLLOW_entityDecl_in_entitySection383);
            	    entityDecl26=entityDecl();

            	    state._fsp--;

            	    stream_entityDecl.add(entityDecl26.getTree());

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



            // AST REWRITE
            // elements: entityDecl
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 93:37: -> ( entityDecl )+
            {
                if ( !(stream_entityDecl.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_entityDecl.hasNext() ) {
                    adaptor.addChild(root_0, stream_entityDecl.nextTree());

                }
                stream_entityDecl.reset();

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
    // $ANTLR end "entitySection"

    public static class entityDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "entityDecl"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:95:1: entityDecl : ID EQ entityExpr SEMICOLON -> ^( EntityDecl ^( Var ID ) entityExpr ) ;
    public final CalParser.entityDecl_return entityDecl() throws RecognitionException {
        CalParser.entityDecl_return retval = new CalParser.entityDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID27=null;
        Token EQ28=null;
        Token SEMICOLON30=null;
        CalParser.entityExpr_return entityExpr29 = null;


        Object ID27_tree=null;
        Object EQ28_tree=null;
        Object SEMICOLON30_tree=null;
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleTokenStream stream_EQ=new RewriteRuleTokenStream(adaptor,"token EQ");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_entityExpr=new RewriteRuleSubtreeStream(adaptor,"rule entityExpr");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:95:11: ( ID EQ entityExpr SEMICOLON -> ^( EntityDecl ^( Var ID ) entityExpr ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:95:13: ID EQ entityExpr SEMICOLON
            {
            ID27=(Token)match(input,ID,FOLLOW_ID_in_entityDecl396);  
            stream_ID.add(ID27);

            EQ28=(Token)match(input,EQ,FOLLOW_EQ_in_entityDecl398);  
            stream_EQ.add(EQ28);

            pushFollow(FOLLOW_entityExpr_in_entityDecl400);
            entityExpr29=entityExpr();

            state._fsp--;

            stream_entityExpr.add(entityExpr29.getTree());
            SEMICOLON30=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_entityDecl402);  
            stream_SEMICOLON.add(SEMICOLON30);



            // AST REWRITE
            // elements: entityExpr, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 95:40: -> ^( EntityDecl ^( Var ID ) entityExpr )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:95:43: ^( EntityDecl ^( Var ID ) entityExpr )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EntityDecl, "EntityDecl"), root_1);

                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:95:56: ^( Var ID )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_2);

                adaptor.addChild(root_2, stream_ID.nextNode());

                adaptor.addChild(root_1, root_2);
                }
                adaptor.addChild(root_1, stream_entityExpr.nextTree());

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
    // $ANTLR end "entityDecl"

    public static class entityExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "entityExpr"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:97:1: entityExpr : ID LPAREN ( entityPars )? RPAREN -> ^( EntityExpr ^( Var ID ) ( entityPars )? ) ;
    public final CalParser.entityExpr_return entityExpr() throws RecognitionException {
        CalParser.entityExpr_return retval = new CalParser.entityExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID31=null;
        Token LPAREN32=null;
        Token RPAREN34=null;
        CalParser.entityPars_return entityPars33 = null;


        Object ID31_tree=null;
        Object LPAREN32_tree=null;
        Object RPAREN34_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_entityPars=new RewriteRuleSubtreeStream(adaptor,"rule entityPars");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:97:11: ( ID LPAREN ( entityPars )? RPAREN -> ^( EntityExpr ^( Var ID ) ( entityPars )? ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:97:13: ID LPAREN ( entityPars )? RPAREN
            {
            ID31=(Token)match(input,ID,FOLLOW_ID_in_entityExpr423);  
            stream_ID.add(ID31);

            LPAREN32=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_entityExpr425);  
            stream_LPAREN.add(LPAREN32);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:97:23: ( entityPars )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==ID) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:97:23: entityPars
                    {
                    pushFollow(FOLLOW_entityPars_in_entityExpr427);
                    entityPars33=entityPars();

                    state._fsp--;

                    stream_entityPars.add(entityPars33.getTree());

                    }
                    break;

            }

            RPAREN34=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_entityExpr430);  
            stream_RPAREN.add(RPAREN34);



            // AST REWRITE
            // elements: ID, entityPars
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 97:42: -> ^( EntityExpr ^( Var ID ) ( entityPars )? )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:97:45: ^( EntityExpr ^( Var ID ) ( entityPars )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EntityExpr, "EntityExpr"), root_1);

                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:97:58: ^( Var ID )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_2);

                adaptor.addChild(root_2, stream_ID.nextNode());

                adaptor.addChild(root_1, root_2);
                }
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:97:68: ( entityPars )?
                if ( stream_entityPars.hasNext() ) {
                    adaptor.addChild(root_1, stream_entityPars.nextTree());

                }
                stream_entityPars.reset();

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
    // $ANTLR end "entityExpr"

    public static class entityPars_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "entityPars"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:99:1: entityPars : entityPar ( COMMA entityPar )* -> ( entityPar )+ ;
    public final CalParser.entityPars_return entityPars() throws RecognitionException {
        CalParser.entityPars_return retval = new CalParser.entityPars_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA36=null;
        CalParser.entityPar_return entityPar35 = null;

        CalParser.entityPar_return entityPar37 = null;


        Object COMMA36_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_entityPar=new RewriteRuleSubtreeStream(adaptor,"rule entityPar");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:99:11: ( entityPar ( COMMA entityPar )* -> ( entityPar )+ )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:99:13: entityPar ( COMMA entityPar )*
            {
            pushFollow(FOLLOW_entityPar_in_entityPars452);
            entityPar35=entityPar();

            state._fsp--;

            stream_entityPar.add(entityPar35.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:99:23: ( COMMA entityPar )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==COMMA) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:99:24: COMMA entityPar
            	    {
            	    COMMA36=(Token)match(input,COMMA,FOLLOW_COMMA_in_entityPars455);  
            	    stream_COMMA.add(COMMA36);

            	    pushFollow(FOLLOW_entityPar_in_entityPars457);
            	    entityPar37=entityPar();

            	    state._fsp--;

            	    stream_entityPar.add(entityPar37.getTree());

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);



            // AST REWRITE
            // elements: entityPar
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 99:42: -> ( entityPar )+
            {
                if ( !(stream_entityPar.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_entityPar.hasNext() ) {
                    adaptor.addChild(root_0, stream_entityPar.nextTree());

                }
                stream_entityPar.reset();

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
    // $ANTLR end "entityPars"

    public static class entityPar_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "entityPar"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:101:1: entityPar : ID EQ expression -> ^( EntityPar ^( Var ID ) ^( Expression expression ) ) ;
    public final CalParser.entityPar_return entityPar() throws RecognitionException {
        CalParser.entityPar_return retval = new CalParser.entityPar_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID38=null;
        Token EQ39=null;
        CalParser.expression_return expression40 = null;


        Object ID38_tree=null;
        Object EQ39_tree=null;
        RewriteRuleTokenStream stream_EQ=new RewriteRuleTokenStream(adaptor,"token EQ");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:101:10: ( ID EQ expression -> ^( EntityPar ^( Var ID ) ^( Expression expression ) ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:101:12: ID EQ expression
            {
            ID38=(Token)match(input,ID,FOLLOW_ID_in_entityPar471);  
            stream_ID.add(ID38);

            EQ39=(Token)match(input,EQ,FOLLOW_EQ_in_entityPar473);  
            stream_EQ.add(EQ39);

            pushFollow(FOLLOW_expression_in_entityPar475);
            expression40=expression();

            state._fsp--;

            stream_expression.add(expression40.getTree());


            // AST REWRITE
            // elements: ID, expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 101:29: -> ^( EntityPar ^( Var ID ) ^( Expression expression ) )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:101:32: ^( EntityPar ^( Var ID ) ^( Expression expression ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EntityPar, "EntityPar"), root_1);

                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:101:44: ^( Var ID )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_2);

                adaptor.addChild(root_2, stream_ID.nextNode());

                adaptor.addChild(root_1, root_2);
                }
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:101:54: ^( Expression expression )
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
    // $ANTLR end "entityPar"

    public static class structureSection_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "structureSection"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:104:1: structureSection : STRUCTURE ( structureStmt )+ -> ( structureStmt )+ ;
    public final CalParser.structureSection_return structureSection() throws RecognitionException {
        CalParser.structureSection_return retval = new CalParser.structureSection_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token STRUCTURE41=null;
        CalParser.structureStmt_return structureStmt42 = null;


        Object STRUCTURE41_tree=null;
        RewriteRuleTokenStream stream_STRUCTURE=new RewriteRuleTokenStream(adaptor,"token STRUCTURE");
        RewriteRuleSubtreeStream stream_structureStmt=new RewriteRuleSubtreeStream(adaptor,"rule structureStmt");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:104:17: ( STRUCTURE ( structureStmt )+ -> ( structureStmt )+ )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:104:19: STRUCTURE ( structureStmt )+
            {
            STRUCTURE41=(Token)match(input,STRUCTURE,FOLLOW_STRUCTURE_in_structureSection501);  
            stream_STRUCTURE.add(STRUCTURE41);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:104:29: ( structureStmt )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==ID) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:104:29: structureStmt
            	    {
            	    pushFollow(FOLLOW_structureStmt_in_structureSection503);
            	    structureStmt42=structureStmt();

            	    state._fsp--;

            	    stream_structureStmt.add(structureStmt42.getTree());

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



            // AST REWRITE
            // elements: structureStmt
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 104:44: -> ( structureStmt )+
            {
                if ( !(stream_structureStmt.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_structureStmt.hasNext() ) {
                    adaptor.addChild(root_0, stream_structureStmt.nextTree());

                }
                stream_structureStmt.reset();

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
    // $ANTLR end "structureSection"

    public static class structureStmt_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "structureStmt"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:106:1: structureStmt : c1= connector DOUBLE_DASH_ARROW c2= connector ( attributeSection )? SEMICOLON -> ^( StructureStmt $c1 $c2) ;
    public final CalParser.structureStmt_return structureStmt() throws RecognitionException {
        CalParser.structureStmt_return retval = new CalParser.structureStmt_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DOUBLE_DASH_ARROW43=null;
        Token SEMICOLON45=null;
        CalParser.connector_return c1 = null;

        CalParser.connector_return c2 = null;

        CalParser.attributeSection_return attributeSection44 = null;


        Object DOUBLE_DASH_ARROW43_tree=null;
        Object SEMICOLON45_tree=null;
        RewriteRuleTokenStream stream_DOUBLE_DASH_ARROW=new RewriteRuleTokenStream(adaptor,"token DOUBLE_DASH_ARROW");
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleSubtreeStream stream_attributeSection=new RewriteRuleSubtreeStream(adaptor,"rule attributeSection");
        RewriteRuleSubtreeStream stream_connector=new RewriteRuleSubtreeStream(adaptor,"rule connector");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:106:14: (c1= connector DOUBLE_DASH_ARROW c2= connector ( attributeSection )? SEMICOLON -> ^( StructureStmt $c1 $c2) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:106:16: c1= connector DOUBLE_DASH_ARROW c2= connector ( attributeSection )? SEMICOLON
            {
            pushFollow(FOLLOW_connector_in_structureStmt518);
            c1=connector();

            state._fsp--;

            stream_connector.add(c1.getTree());
            DOUBLE_DASH_ARROW43=(Token)match(input,DOUBLE_DASH_ARROW,FOLLOW_DOUBLE_DASH_ARROW_in_structureStmt520);  
            stream_DOUBLE_DASH_ARROW.add(DOUBLE_DASH_ARROW43);

            pushFollow(FOLLOW_connector_in_structureStmt524);
            c2=connector();

            state._fsp--;

            stream_connector.add(c2.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:106:60: ( attributeSection )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==LBRACE) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:106:60: attributeSection
                    {
                    pushFollow(FOLLOW_attributeSection_in_structureStmt526);
                    attributeSection44=attributeSection();

                    state._fsp--;

                    stream_attributeSection.add(attributeSection44.getTree());

                    }
                    break;

            }

            SEMICOLON45=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_structureStmt529);  
            stream_SEMICOLON.add(SEMICOLON45);



            // AST REWRITE
            // elements: c1, c2
            // token labels: 
            // rule labels: retval, c1, c2
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_c1=new RewriteRuleSubtreeStream(adaptor,"rule c1",c1!=null?c1.tree:null);
            RewriteRuleSubtreeStream stream_c2=new RewriteRuleSubtreeStream(adaptor,"rule c2",c2!=null?c2.tree:null);

            root_0 = (Object)adaptor.nil();
            // 106:88: -> ^( StructureStmt $c1 $c2)
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:107:3: ^( StructureStmt $c1 $c2)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(StructureStmt, "StructureStmt"), root_1);

                adaptor.addChild(root_1, stream_c1.nextTree());
                adaptor.addChild(root_1, stream_c2.nextTree());

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
    // $ANTLR end "structureStmt"

    public static class connector_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "connector"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:109:1: connector : v1= ID ( DOT v2= ID -> ^( Connector ^( Var $v1) ^( Var $v2) ) | -> ^( Connector ^( Var $v1) ) ) ;
    public final CalParser.connector_return connector() throws RecognitionException {
        CalParser.connector_return retval = new CalParser.connector_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token v1=null;
        Token v2=null;
        Token DOT46=null;

        Object v1_tree=null;
        Object v2_tree=null;
        Object DOT46_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");

        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:109:10: (v1= ID ( DOT v2= ID -> ^( Connector ^( Var $v1) ^( Var $v2) ) | -> ^( Connector ^( Var $v1) ) ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:109:12: v1= ID ( DOT v2= ID -> ^( Connector ^( Var $v1) ^( Var $v2) ) | -> ^( Connector ^( Var $v1) ) )
            {
            v1=(Token)match(input,ID,FOLLOW_ID_in_connector553);  
            stream_ID.add(v1);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:109:18: ( DOT v2= ID -> ^( Connector ^( Var $v1) ^( Var $v2) ) | -> ^( Connector ^( Var $v1) ) )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==DOT) ) {
                alt19=1;
            }
            else if ( (LA19_0==SEMICOLON||LA19_0==DOUBLE_DASH_ARROW||LA19_0==LBRACE) ) {
                alt19=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:110:3: DOT v2= ID
                    {
                    DOT46=(Token)match(input,DOT,FOLLOW_DOT_in_connector559);  
                    stream_DOT.add(DOT46);

                    v2=(Token)match(input,ID,FOLLOW_ID_in_connector563);  
                    stream_ID.add(v2);



                    // AST REWRITE
                    // elements: v1, v2
                    // token labels: v1, v2
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_v1=new RewriteRuleTokenStream(adaptor,"token v1",v1);
                    RewriteRuleTokenStream stream_v2=new RewriteRuleTokenStream(adaptor,"token v2",v2);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 110:13: -> ^( Connector ^( Var $v1) ^( Var $v2) )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:110:16: ^( Connector ^( Var $v1) ^( Var $v2) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Connector, "Connector"), root_1);

                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:110:28: ^( Var $v1)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_2);

                        adaptor.addChild(root_2, stream_v1.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:110:39: ^( Var $v2)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_2);

                        adaptor.addChild(root_2, stream_v2.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:111:5: 
                    {

                    // AST REWRITE
                    // elements: v1
                    // token labels: v1
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_v1=new RewriteRuleTokenStream(adaptor,"token v1",v1);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 111:5: -> ^( Connector ^( Var $v1) )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:111:8: ^( Connector ^( Var $v1) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Connector, "Connector"), root_1);

                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:111:20: ^( Var $v1)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_2);

                        adaptor.addChild(root_2, stream_v1.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }

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
    // $ANTLR end "connector"

    public static class attributeSection_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "attributeSection"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:113:1: attributeSection : LBRACE ( attributeDecl )* RBRACE ;
    public final CalParser.attributeSection_return attributeSection() throws RecognitionException {
        CalParser.attributeSection_return retval = new CalParser.attributeSection_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LBRACE47=null;
        Token RBRACE49=null;
        CalParser.attributeDecl_return attributeDecl48 = null;


        Object LBRACE47_tree=null;
        Object RBRACE49_tree=null;

        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:113:17: ( LBRACE ( attributeDecl )* RBRACE )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:113:19: LBRACE ( attributeDecl )* RBRACE
            {
            root_0 = (Object)adaptor.nil();

            LBRACE47=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_attributeSection608); 
            LBRACE47_tree = (Object)adaptor.create(LBRACE47);
            adaptor.addChild(root_0, LBRACE47_tree);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:113:26: ( attributeDecl )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==ID) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:113:26: attributeDecl
            	    {
            	    pushFollow(FOLLOW_attributeDecl_in_attributeSection610);
            	    attributeDecl48=attributeDecl();

            	    state._fsp--;

            	    adaptor.addChild(root_0, attributeDecl48.getTree());

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            RBRACE49=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_attributeSection613); 
            RBRACE49_tree = (Object)adaptor.create(RBRACE49);
            adaptor.addChild(root_0, RBRACE49_tree);


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
    // $ANTLR end "attributeSection"

    public static class attributeDecl_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "attributeDecl"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:115:1: attributeDecl : ID ( EQ expression SEMICOLON | COLON type SEMICOLON ) ;
    public final CalParser.attributeDecl_return attributeDecl() throws RecognitionException {
        CalParser.attributeDecl_return retval = new CalParser.attributeDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID50=null;
        Token EQ51=null;
        Token SEMICOLON53=null;
        Token COLON54=null;
        Token SEMICOLON56=null;
        CalParser.expression_return expression52 = null;

        CalParser.type_return type55 = null;


        Object ID50_tree=null;
        Object EQ51_tree=null;
        Object SEMICOLON53_tree=null;
        Object COLON54_tree=null;
        Object SEMICOLON56_tree=null;

        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:115:14: ( ID ( EQ expression SEMICOLON | COLON type SEMICOLON ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:115:16: ID ( EQ expression SEMICOLON | COLON type SEMICOLON )
            {
            root_0 = (Object)adaptor.nil();

            ID50=(Token)match(input,ID,FOLLOW_ID_in_attributeDecl621); 
            ID50_tree = (Object)adaptor.create(ID50);
            adaptor.addChild(root_0, ID50_tree);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:115:19: ( EQ expression SEMICOLON | COLON type SEMICOLON )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==EQ) ) {
                alt21=1;
            }
            else if ( (LA21_0==COLON) ) {
                alt21=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:115:20: EQ expression SEMICOLON
                    {
                    EQ51=(Token)match(input,EQ,FOLLOW_EQ_in_attributeDecl624); 
                    EQ51_tree = (Object)adaptor.create(EQ51);
                    adaptor.addChild(root_0, EQ51_tree);

                    pushFollow(FOLLOW_expression_in_attributeDecl626);
                    expression52=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression52.getTree());
                    SEMICOLON53=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_attributeDecl628); 
                    SEMICOLON53_tree = (Object)adaptor.create(SEMICOLON53);
                    adaptor.addChild(root_0, SEMICOLON53_tree);


                    }
                    break;
                case 2 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:115:46: COLON type SEMICOLON
                    {
                    COLON54=(Token)match(input,COLON,FOLLOW_COLON_in_attributeDecl632); 
                    COLON54_tree = (Object)adaptor.create(COLON54);
                    adaptor.addChild(root_0, COLON54_tree);

                    pushFollow(FOLLOW_type_in_attributeDecl634);
                    type55=type();

                    state._fsp--;

                    adaptor.addChild(root_0, type55.getTree());
                    SEMICOLON56=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_attributeDecl636); 
                    SEMICOLON56_tree = (Object)adaptor.create(SEMICOLON56);
                    adaptor.addChild(root_0, SEMICOLON56_tree);


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
    // $ANTLR end "attributeDecl"

    public static class actor_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "actor"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:120:1: actor : ( oneImport )* ACTOR ID ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN (inputs= portDecls )? DOUBLE_EQUAL_ARROW (outputs= portDecls )? COLON ( ignore )* EOF -> ^( Actor ^( Name ID ) ( parameters )? ^( Inputs ( $inputs)? ) ^( Outputs ( $outputs)? ) ) ;
    public final CalParser.actor_return actor() throws RecognitionException {
        CalParser.actor_return retval = new CalParser.actor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ACTOR58=null;
        Token ID59=null;
        Token LBRACKET60=null;
        Token RBRACKET62=null;
        Token LPAREN63=null;
        Token RPAREN65=null;
        Token DOUBLE_EQUAL_ARROW66=null;
        Token COLON67=null;
        Token EOF69=null;
        CalParser.portDecls_return inputs = null;

        CalParser.portDecls_return outputs = null;

        CalParser.oneImport_return oneImport57 = null;

        CalParser.typePars_return typePars61 = null;

        CalParser.parameters_return parameters64 = null;

        CalParser.ignore_return ignore68 = null;


        Object ACTOR58_tree=null;
        Object ID59_tree=null;
        Object LBRACKET60_tree=null;
        Object RBRACKET62_tree=null;
        Object LPAREN63_tree=null;
        Object RPAREN65_tree=null;
        Object DOUBLE_EQUAL_ARROW66_tree=null;
        Object COLON67_tree=null;
        Object EOF69_tree=null;
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:120:6: ( ( oneImport )* ACTOR ID ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN (inputs= portDecls )? DOUBLE_EQUAL_ARROW (outputs= portDecls )? COLON ( ignore )* EOF -> ^( Actor ^( Name ID ) ( parameters )? ^( Inputs ( $inputs)? ) ^( Outputs ( $outputs)? ) ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:120:8: ( oneImport )* ACTOR ID ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN (inputs= portDecls )? DOUBLE_EQUAL_ARROW (outputs= portDecls )? COLON ( ignore )* EOF
            {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:120:8: ( oneImport )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==IMPORT) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:120:8: oneImport
            	    {
            	    pushFollow(FOLLOW_oneImport_in_actor648);
            	    oneImport57=oneImport();

            	    state._fsp--;

            	    stream_oneImport.add(oneImport57.getTree());

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            ACTOR58=(Token)match(input,ACTOR,FOLLOW_ACTOR_in_actor651);  
            stream_ACTOR.add(ACTOR58);

            ID59=(Token)match(input,ID,FOLLOW_ID_in_actor653);  
            stream_ID.add(ID59);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:121:3: ( LBRACKET ( typePars )? RBRACKET )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==LBRACKET) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:121:4: LBRACKET ( typePars )? RBRACKET
                    {
                    LBRACKET60=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_actor658);  
                    stream_LBRACKET.add(LBRACKET60);

                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:121:13: ( typePars )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==ID) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:121:13: typePars
                            {
                            pushFollow(FOLLOW_typePars_in_actor660);
                            typePars61=typePars();

                            state._fsp--;

                            stream_typePars.add(typePars61.getTree());

                            }
                            break;

                    }

                    RBRACKET62=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_actor663);  
                    stream_RBRACKET.add(RBRACKET62);


                    }
                    break;

            }

            LPAREN63=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_actor669);  
            stream_LPAREN.add(LPAREN63);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:122:10: ( parameters )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==ID) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:122:10: parameters
                    {
                    pushFollow(FOLLOW_parameters_in_actor671);
                    parameters64=parameters();

                    state._fsp--;

                    stream_parameters.add(parameters64.getTree());

                    }
                    break;

            }

            RPAREN65=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_actor674);  
            stream_RPAREN.add(RPAREN65);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:123:9: (inputs= portDecls )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==ID||LA26_0==MULTI) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:123:9: inputs= portDecls
                    {
                    pushFollow(FOLLOW_portDecls_in_actor680);
                    inputs=portDecls();

                    state._fsp--;

                    stream_portDecls.add(inputs.getTree());

                    }
                    break;

            }

            DOUBLE_EQUAL_ARROW66=(Token)match(input,DOUBLE_EQUAL_ARROW,FOLLOW_DOUBLE_EQUAL_ARROW_in_actor683);  
            stream_DOUBLE_EQUAL_ARROW.add(DOUBLE_EQUAL_ARROW66);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:123:47: (outputs= portDecls )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==ID||LA27_0==MULTI) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:123:47: outputs= portDecls
                    {
                    pushFollow(FOLLOW_portDecls_in_actor687);
                    outputs=portDecls();

                    state._fsp--;

                    stream_portDecls.add(outputs.getTree());

                    }
                    break;

            }

            COLON67=(Token)match(input,COLON,FOLLOW_COLON_in_actor690);  
            stream_COLON.add(COLON67);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:123:65: ( ignore )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( ((LA28_0>=NETWORK && LA28_0<=RBRACE)||(LA28_0>=ALL && LA28_0<=TRUE)) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:123:65: ignore
            	    {
            	    pushFollow(FOLLOW_ignore_in_actor692);
            	    ignore68=ignore();

            	    state._fsp--;

            	    stream_ignore.add(ignore68.getTree());

            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

            EOF69=(Token)match(input,EOF,FOLLOW_EOF_in_actor695);  
            stream_EOF.add(EOF69);



            // AST REWRITE
            // elements: ID, parameters, outputs, inputs
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
            // 123:77: -> ^( Actor ^( Name ID ) ( parameters )? ^( Inputs ( $inputs)? ) ^( Outputs ( $outputs)? ) )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:124:5: ^( Actor ^( Name ID ) ( parameters )? ^( Inputs ( $inputs)? ) ^( Outputs ( $outputs)? ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Actor, "Actor"), root_1);

                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:124:13: ^( Name ID )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Name, "Name"), root_2);

                adaptor.addChild(root_2, stream_ID.nextNode());

                adaptor.addChild(root_1, root_2);
                }
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:124:24: ( parameters )?
                if ( stream_parameters.hasNext() ) {
                    adaptor.addChild(root_1, stream_parameters.nextTree());

                }
                stream_parameters.reset();
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:124:36: ^( Inputs ( $inputs)? )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Inputs, "Inputs"), root_2);

                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:124:45: ( $inputs)?
                if ( stream_inputs.hasNext() ) {
                    adaptor.addChild(root_2, stream_inputs.nextTree());

                }
                stream_inputs.reset();

                adaptor.addChild(root_1, root_2);
                }
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:124:55: ^( Outputs ( $outputs)? )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Outputs, "Outputs"), root_2);

                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:124:65: ( $outputs)?
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:126:1: ignore : ( ALL | ARROW | CARET | COLON | COLON_EQUAL | COMMA | DIV | DOT | DOUBLE_DASH_ARROW | DOUBLE_EQUAL_ARROW | DOUBLE_DOT | DOUBLE_COLON | EQ | END | ENTITIES | FALSE | FLOAT | GE | GT | ID | INTEGER | LBRACE | LBRACKET | LPAREN | LE | LT | MINUS | MULTI | MUTABLE | NE | NETWORK | NOT | PLUS | RBRACE | RBRACKET | RPAREN | SEMICOLON | SHARP | STRING | STRUCTURE | TIMES | TRUE | VAR );
    public final CalParser.ignore_return ignore() throws RecognitionException {
        CalParser.ignore_return retval = new CalParser.ignore_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set70=null;

        Object set70_tree=null;

        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:126:7: ( ALL | ARROW | CARET | COLON | COLON_EQUAL | COMMA | DIV | DOT | DOUBLE_DASH_ARROW | DOUBLE_EQUAL_ARROW | DOUBLE_DOT | DOUBLE_COLON | EQ | END | ENTITIES | FALSE | FLOAT | GE | GT | ID | INTEGER | LBRACE | LBRACKET | LPAREN | LE | LT | MINUS | MULTI | MUTABLE | NE | NETWORK | NOT | PLUS | RBRACE | RBRACKET | RPAREN | SEMICOLON | SHARP | STRING | STRUCTURE | TIMES | TRUE | VAR )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:
            {
            root_0 = (Object)adaptor.nil();

            set70=(Token)input.LT(1);
            if ( (input.LA(1)>=NETWORK && input.LA(1)<=RBRACE)||(input.LA(1)>=ALL && input.LA(1)<=TRUE) ) {
                input.consume();
                adaptor.addChild(root_0, (Object)adaptor.create(set70));
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:173:1: oneImport : IMPORT importRest SEMICOLON ;
    public final CalParser.oneImport_return oneImport() throws RecognitionException {
        CalParser.oneImport_return retval = new CalParser.oneImport_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IMPORT71=null;
        Token SEMICOLON73=null;
        CalParser.importRest_return importRest72 = null;


        Object IMPORT71_tree=null;
        Object SEMICOLON73_tree=null;

        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:173:10: ( IMPORT importRest SEMICOLON )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:173:12: IMPORT importRest SEMICOLON
            {
            root_0 = (Object)adaptor.nil();

            IMPORT71=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_oneImport915); 
            IMPORT71_tree = (Object)adaptor.create(IMPORT71);
            adaptor.addChild(root_0, IMPORT71_tree);

            pushFollow(FOLLOW_importRest_in_oneImport917);
            importRest72=importRest();

            state._fsp--;

            adaptor.addChild(root_0, importRest72.getTree());
            SEMICOLON73=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_oneImport919); 
            SEMICOLON73_tree = (Object)adaptor.create(SEMICOLON73);
            adaptor.addChild(root_0, SEMICOLON73_tree);


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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:175:1: importRest : ( ALL qualifiedId | qualifiedId ( EQ ID )? );
    public final CalParser.importRest_return importRest() throws RecognitionException {
        CalParser.importRest_return retval = new CalParser.importRest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ALL74=null;
        Token EQ77=null;
        Token ID78=null;
        CalParser.qualifiedId_return qualifiedId75 = null;

        CalParser.qualifiedId_return qualifiedId76 = null;


        Object ALL74_tree=null;
        Object EQ77_tree=null;
        Object ID78_tree=null;

        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:175:11: ( ALL qualifiedId | qualifiedId ( EQ ID )? )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==ALL) ) {
                alt30=1;
            }
            else if ( (LA30_0==ID) ) {
                alt30=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:175:13: ALL qualifiedId
                    {
                    root_0 = (Object)adaptor.nil();

                    ALL74=(Token)match(input,ALL,FOLLOW_ALL_in_importRest927); 
                    ALL74_tree = (Object)adaptor.create(ALL74);
                    adaptor.addChild(root_0, ALL74_tree);

                    pushFollow(FOLLOW_qualifiedId_in_importRest929);
                    qualifiedId75=qualifiedId();

                    state._fsp--;

                    adaptor.addChild(root_0, qualifiedId75.getTree());

                    }
                    break;
                case 2 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:176:5: qualifiedId ( EQ ID )?
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_qualifiedId_in_importRest935);
                    qualifiedId76=qualifiedId();

                    state._fsp--;

                    adaptor.addChild(root_0, qualifiedId76.getTree());
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:176:17: ( EQ ID )?
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0==EQ) ) {
                        alt29=1;
                    }
                    switch (alt29) {
                        case 1 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:176:18: EQ ID
                            {
                            EQ77=(Token)match(input,EQ,FOLLOW_EQ_in_importRest938); 
                            EQ77_tree = (Object)adaptor.create(EQ77);
                            adaptor.addChild(root_0, EQ77_tree);

                            ID78=(Token)match(input,ID,FOLLOW_ID_in_importRest940); 
                            ID78_tree = (Object)adaptor.create(ID78);
                            adaptor.addChild(root_0, ID78_tree);


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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:178:1: qualifiedId : ID ( DOT ID )+ ;
    public final CalParser.qualifiedId_return qualifiedId() throws RecognitionException {
        CalParser.qualifiedId_return retval = new CalParser.qualifiedId_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID79=null;
        Token DOT80=null;
        Token ID81=null;

        Object ID79_tree=null;
        Object DOT80_tree=null;
        Object ID81_tree=null;

        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:178:12: ( ID ( DOT ID )+ )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:178:14: ID ( DOT ID )+
            {
            root_0 = (Object)adaptor.nil();

            ID79=(Token)match(input,ID,FOLLOW_ID_in_qualifiedId950); 
            ID79_tree = (Object)adaptor.create(ID79);
            adaptor.addChild(root_0, ID79_tree);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:178:17: ( DOT ID )+
            int cnt31=0;
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==DOT) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:178:18: DOT ID
            	    {
            	    DOT80=(Token)match(input,DOT,FOLLOW_DOT_in_qualifiedId953); 
            	    DOT80_tree = (Object)adaptor.create(DOT80);
            	    adaptor.addChild(root_0, DOT80_tree);

            	    ID81=(Token)match(input,ID,FOLLOW_ID_in_qualifiedId955); 
            	    ID81_tree = (Object)adaptor.create(ID81);
            	    adaptor.addChild(root_0, ID81_tree);


            	    }
            	    break;

            	default :
            	    if ( cnt31 >= 1 ) break loop31;
                        EarlyExitException eee =
                            new EarlyExitException(31, input);
                        throw eee;
                }
                cnt31++;
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:183:1: parameter : typeAndId ( EQ expression -> ^( Parameter typeAndId ^( Expression expression ) ) | -> ^( Parameter typeAndId ) ) ;
    public final CalParser.parameter_return parameter() throws RecognitionException {
        CalParser.parameter_return retval = new CalParser.parameter_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EQ83=null;
        CalParser.typeAndId_return typeAndId82 = null;

        CalParser.expression_return expression84 = null;


        Object EQ83_tree=null;
        RewriteRuleTokenStream stream_EQ=new RewriteRuleTokenStream(adaptor,"token EQ");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_typeAndId=new RewriteRuleSubtreeStream(adaptor,"rule typeAndId");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:183:10: ( typeAndId ( EQ expression -> ^( Parameter typeAndId ^( Expression expression ) ) | -> ^( Parameter typeAndId ) ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:183:12: typeAndId ( EQ expression -> ^( Parameter typeAndId ^( Expression expression ) ) | -> ^( Parameter typeAndId ) )
            {
            pushFollow(FOLLOW_typeAndId_in_parameter968);
            typeAndId82=typeAndId();

            state._fsp--;

            stream_typeAndId.add(typeAndId82.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:184:3: ( EQ expression -> ^( Parameter typeAndId ^( Expression expression ) ) | -> ^( Parameter typeAndId ) )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==EQ) ) {
                alt32=1;
            }
            else if ( (LA32_0==RPAREN||LA32_0==COMMA) ) {
                alt32=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:184:4: EQ expression
                    {
                    EQ83=(Token)match(input,EQ,FOLLOW_EQ_in_parameter973);  
                    stream_EQ.add(EQ83);

                    pushFollow(FOLLOW_expression_in_parameter975);
                    expression84=expression();

                    state._fsp--;

                    stream_expression.add(expression84.getTree());


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
                    // 184:18: -> ^( Parameter typeAndId ^( Expression expression ) )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:184:21: ^( Parameter typeAndId ^( Expression expression ) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Parameter, "Parameter"), root_1);

                        adaptor.addChild(root_1, stream_typeAndId.nextTree());
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:184:43: ^( Expression expression )
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
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:185:5: 
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
                    // 185:5: -> ^( Parameter typeAndId )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:185:8: ^( Parameter typeAndId )
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:187:1: parameters : parameter ( COMMA parameter )* -> ( parameter )+ ;
    public final CalParser.parameters_return parameters() throws RecognitionException {
        CalParser.parameters_return retval = new CalParser.parameters_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA86=null;
        CalParser.parameter_return parameter85 = null;

        CalParser.parameter_return parameter87 = null;


        Object COMMA86_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_parameter=new RewriteRuleSubtreeStream(adaptor,"rule parameter");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:187:11: ( parameter ( COMMA parameter )* -> ( parameter )+ )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:187:13: parameter ( COMMA parameter )*
            {
            pushFollow(FOLLOW_parameter_in_parameters1009);
            parameter85=parameter();

            state._fsp--;

            stream_parameter.add(parameter85.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:187:23: ( COMMA parameter )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==COMMA) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:187:24: COMMA parameter
            	    {
            	    COMMA86=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameters1012);  
            	    stream_COMMA.add(COMMA86);

            	    pushFollow(FOLLOW_parameter_in_parameters1014);
            	    parameter87=parameter();

            	    state._fsp--;

            	    stream_parameter.add(parameter87.getTree());

            	    }
            	    break;

            	default :
            	    break loop33;
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
            // 187:42: -> ( parameter )+
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:192:1: portDecl : ( MULTI )? typeAndId -> ^( PortDecl typeAndId ) ;
    public final CalParser.portDecl_return portDecl() throws RecognitionException {
        CalParser.portDecl_return retval = new CalParser.portDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MULTI88=null;
        CalParser.typeAndId_return typeAndId89 = null;


        Object MULTI88_tree=null;
        RewriteRuleTokenStream stream_MULTI=new RewriteRuleTokenStream(adaptor,"token MULTI");
        RewriteRuleSubtreeStream stream_typeAndId=new RewriteRuleSubtreeStream(adaptor,"rule typeAndId");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:192:9: ( ( MULTI )? typeAndId -> ^( PortDecl typeAndId ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:192:11: ( MULTI )? typeAndId
            {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:192:11: ( MULTI )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==MULTI) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:192:11: MULTI
                    {
                    MULTI88=(Token)match(input,MULTI,FOLLOW_MULTI_in_portDecl1031);  
                    stream_MULTI.add(MULTI88);


                    }
                    break;

            }

            pushFollow(FOLLOW_typeAndId_in_portDecl1034);
            typeAndId89=typeAndId();

            state._fsp--;

            stream_typeAndId.add(typeAndId89.getTree());


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
            // 192:28: -> ^( PortDecl typeAndId )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:192:31: ^( PortDecl typeAndId )
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:194:1: portDecls : portDecl ( COMMA portDecl )* -> ( portDecl )+ ;
    public final CalParser.portDecls_return portDecls() throws RecognitionException {
        CalParser.portDecls_return retval = new CalParser.portDecls_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA91=null;
        CalParser.portDecl_return portDecl90 = null;

        CalParser.portDecl_return portDecl92 = null;


        Object COMMA91_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_portDecl=new RewriteRuleSubtreeStream(adaptor,"rule portDecl");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:194:10: ( portDecl ( COMMA portDecl )* -> ( portDecl )+ )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:194:12: portDecl ( COMMA portDecl )*
            {
            pushFollow(FOLLOW_portDecl_in_portDecls1049);
            portDecl90=portDecl();

            state._fsp--;

            stream_portDecl.add(portDecl90.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:194:21: ( COMMA portDecl )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==COMMA) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:194:22: COMMA portDecl
            	    {
            	    COMMA91=(Token)match(input,COMMA,FOLLOW_COMMA_in_portDecls1052);  
            	    stream_COMMA.add(COMMA91);

            	    pushFollow(FOLLOW_portDecl_in_portDecls1054);
            	    portDecl92=portDecl();

            	    state._fsp--;

            	    stream_portDecl.add(portDecl92.getTree());

            	    }
            	    break;

            	default :
            	    break loop35;
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
            // 194:39: -> ( portDecl )+
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:199:1: mainParameter : typeAndId EOF -> ^( Parameter typeAndId ) ;
    public final CalParser.mainParameter_return mainParameter() throws RecognitionException {
        CalParser.mainParameter_return retval = new CalParser.mainParameter_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF94=null;
        CalParser.typeAndId_return typeAndId93 = null;


        Object EOF94_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_typeAndId=new RewriteRuleSubtreeStream(adaptor,"rule typeAndId");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:199:14: ( typeAndId EOF -> ^( Parameter typeAndId ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:199:16: typeAndId EOF
            {
            pushFollow(FOLLOW_typeAndId_in_mainParameter1072);
            typeAndId93=typeAndId();

            state._fsp--;

            stream_typeAndId.add(typeAndId93.getTree());
            EOF94=(Token)match(input,EOF,FOLLOW_EOF_in_mainParameter1074);  
            stream_EOF.add(EOF94);



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
            // 199:30: -> ^( Parameter typeAndId )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:199:33: ^( Parameter typeAndId )
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:201:1: typeAndId : typeName= ID ( ( typeRest )? varName= ID -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName) | -> ^( Var $typeName) ) ;
    public final CalParser.typeAndId_return typeAndId() throws RecognitionException {
        CalParser.typeAndId_return retval = new CalParser.typeAndId_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token typeName=null;
        Token varName=null;
        CalParser.typeRest_return typeRest95 = null;


        Object typeName_tree=null;
        Object varName_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_typeRest=new RewriteRuleSubtreeStream(adaptor,"rule typeRest");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:201:10: (typeName= ID ( ( typeRest )? varName= ID -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName) | -> ^( Var $typeName) ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:201:12: typeName= ID ( ( typeRest )? varName= ID -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName) | -> ^( Var $typeName) )
            {
            typeName=(Token)match(input,ID,FOLLOW_ID_in_typeAndId1091);  
            stream_ID.add(typeName);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:202:3: ( ( typeRest )? varName= ID -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName) | -> ^( Var $typeName) )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==LBRACKET||LA37_0==LPAREN||LA37_0==ID) ) {
                alt37=1;
            }
            else if ( (LA37_0==EOF||(LA37_0>=RPAREN && LA37_0<=COLON)||(LA37_0>=EQ && LA37_0<=SEMICOLON)||LA37_0==COMMA) ) {
                alt37=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:202:4: ( typeRest )? varName= ID
                    {
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:202:4: ( typeRest )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==LBRACKET||LA36_0==LPAREN) ) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:202:4: typeRest
                            {
                            pushFollow(FOLLOW_typeRest_in_typeAndId1096);
                            typeRest95=typeRest();

                            state._fsp--;

                            stream_typeRest.add(typeRest95.getTree());

                            }
                            break;

                    }

                    varName=(Token)match(input,ID,FOLLOW_ID_in_typeAndId1101);  
                    stream_ID.add(varName);



                    // AST REWRITE
                    // elements: typeRest, typeName, varName
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
                    // 202:25: -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName)
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:202:28: ^( Type ^( Var $typeName) ( typeRest )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Type, "Type"), root_1);

                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:202:35: ^( Var $typeName)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_2);

                        adaptor.addChild(root_2, stream_typeName.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:202:52: ( typeRest )?
                        if ( stream_typeRest.hasNext() ) {
                            adaptor.addChild(root_1, stream_typeRest.nextTree());

                        }
                        stream_typeRest.reset();

                        adaptor.addChild(root_0, root_1);
                        }
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:202:63: ^( Var $varName)
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
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:203:5: 
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
                    // 203:5: -> ^( Var $typeName)
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:203:8: ^( Var $typeName)
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:205:1: type : ID ( typeRest )? -> ^( Type ^( Var ID ) ( typeRest )? ) ;
    public final CalParser.type_return type() throws RecognitionException {
        CalParser.type_return retval = new CalParser.type_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID96=null;
        CalParser.typeRest_return typeRest97 = null;


        Object ID96_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_typeRest=new RewriteRuleSubtreeStream(adaptor,"rule typeRest");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:205:5: ( ID ( typeRest )? -> ^( Type ^( Var ID ) ( typeRest )? ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:205:7: ID ( typeRest )?
            {
            ID96=(Token)match(input,ID,FOLLOW_ID_in_type1145);  
            stream_ID.add(ID96);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:205:10: ( typeRest )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==LBRACKET||LA38_0==LPAREN) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:205:10: typeRest
                    {
                    pushFollow(FOLLOW_typeRest_in_type1147);
                    typeRest97=typeRest();

                    state._fsp--;

                    stream_typeRest.add(typeRest97.getTree());

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
            // 205:20: -> ^( Type ^( Var ID ) ( typeRest )? )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:205:23: ^( Type ^( Var ID ) ( typeRest )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Type, "Type"), root_1);

                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:205:30: ^( Var ID )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_2);

                adaptor.addChild(root_2, stream_ID.nextNode());

                adaptor.addChild(root_1, root_2);
                }
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:205:40: ( typeRest )?
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:207:1: typeRest : ( LBRACKET ( typePars )? RBRACKET -> ( typePars )? | LPAREN ( typeAttrs )? RPAREN -> ( typeAttrs )? );
    public final CalParser.typeRest_return typeRest() throws RecognitionException {
        CalParser.typeRest_return retval = new CalParser.typeRest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LBRACKET98=null;
        Token RBRACKET100=null;
        Token LPAREN101=null;
        Token RPAREN103=null;
        CalParser.typePars_return typePars99 = null;

        CalParser.typeAttrs_return typeAttrs102 = null;


        Object LBRACKET98_tree=null;
        Object RBRACKET100_tree=null;
        Object LPAREN101_tree=null;
        Object RPAREN103_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_RBRACKET=new RewriteRuleTokenStream(adaptor,"token RBRACKET");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_typePars=new RewriteRuleSubtreeStream(adaptor,"rule typePars");
        RewriteRuleSubtreeStream stream_typeAttrs=new RewriteRuleSubtreeStream(adaptor,"rule typeAttrs");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:207:9: ( LBRACKET ( typePars )? RBRACKET -> ( typePars )? | LPAREN ( typeAttrs )? RPAREN -> ( typeAttrs )? )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==LBRACKET) ) {
                alt41=1;
            }
            else if ( (LA41_0==LPAREN) ) {
                alt41=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:207:11: LBRACKET ( typePars )? RBRACKET
                    {
                    LBRACKET98=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_typeRest1170);  
                    stream_LBRACKET.add(LBRACKET98);

                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:207:20: ( typePars )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==ID) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:207:20: typePars
                            {
                            pushFollow(FOLLOW_typePars_in_typeRest1172);
                            typePars99=typePars();

                            state._fsp--;

                            stream_typePars.add(typePars99.getTree());

                            }
                            break;

                    }

                    RBRACKET100=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_typeRest1175);  
                    stream_RBRACKET.add(RBRACKET100);



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
                    // 207:39: -> ( typePars )?
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:207:42: ( typePars )?
                        if ( stream_typePars.hasNext() ) {
                            adaptor.addChild(root_0, stream_typePars.nextTree());

                        }
                        stream_typePars.reset();

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:208:5: LPAREN ( typeAttrs )? RPAREN
                    {
                    LPAREN101=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_typeRest1186);  
                    stream_LPAREN.add(LPAREN101);

                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:208:12: ( typeAttrs )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==ID) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:208:12: typeAttrs
                            {
                            pushFollow(FOLLOW_typeAttrs_in_typeRest1188);
                            typeAttrs102=typeAttrs();

                            state._fsp--;

                            stream_typeAttrs.add(typeAttrs102.getTree());

                            }
                            break;

                    }

                    RPAREN103=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_typeRest1191);  
                    stream_RPAREN.add(RPAREN103);



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
                    // 208:30: -> ( typeAttrs )?
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:208:33: ( typeAttrs )?
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:210:1: typeAttrs : typeAttr ( COMMA typeAttr )* -> ( typeAttr )+ ;
    public final CalParser.typeAttrs_return typeAttrs() throws RecognitionException {
        CalParser.typeAttrs_return retval = new CalParser.typeAttrs_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA105=null;
        CalParser.typeAttr_return typeAttr104 = null;

        CalParser.typeAttr_return typeAttr106 = null;


        Object COMMA105_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_typeAttr=new RewriteRuleSubtreeStream(adaptor,"rule typeAttr");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:210:10: ( typeAttr ( COMMA typeAttr )* -> ( typeAttr )+ )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:210:12: typeAttr ( COMMA typeAttr )*
            {
            pushFollow(FOLLOW_typeAttr_in_typeAttrs1203);
            typeAttr104=typeAttr();

            state._fsp--;

            stream_typeAttr.add(typeAttr104.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:210:21: ( COMMA typeAttr )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==COMMA) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:210:22: COMMA typeAttr
            	    {
            	    COMMA105=(Token)match(input,COMMA,FOLLOW_COMMA_in_typeAttrs1206);  
            	    stream_COMMA.add(COMMA105);

            	    pushFollow(FOLLOW_typeAttr_in_typeAttrs1208);
            	    typeAttr106=typeAttr();

            	    state._fsp--;

            	    stream_typeAttr.add(typeAttr106.getTree());

            	    }
            	    break;

            	default :
            	    break loop42;
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
            // 210:39: -> ( typeAttr )+
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:212:1: typeAttr : ID typeAttrRest -> typeAttrRest ;
    public final CalParser.typeAttr_return typeAttr() throws RecognitionException {
        CalParser.typeAttr_return retval = new CalParser.typeAttr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID107=null;
        CalParser.typeAttrRest_return typeAttrRest108 = null;


        Object ID107_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_typeAttrRest=new RewriteRuleSubtreeStream(adaptor,"rule typeAttrRest");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:212:9: ( ID typeAttrRest -> typeAttrRest )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:212:11: ID typeAttrRest
            {
            ID107=(Token)match(input,ID,FOLLOW_ID_in_typeAttr1222);  
            stream_ID.add(ID107);

            pushFollow(FOLLOW_typeAttrRest_in_typeAttr1224);
            typeAttrRest108=typeAttrRest();

            state._fsp--;

            stream_typeAttrRest.add(typeAttrRest108.getTree());


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
            // 212:27: -> typeAttrRest
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:214:1: typeAttrRest : ( COLON type -> ^( TypeAttr type ) | EQ expression -> ^( ExprAttr ^( Expression expression ) ) );
    public final CalParser.typeAttrRest_return typeAttrRest() throws RecognitionException {
        CalParser.typeAttrRest_return retval = new CalParser.typeAttrRest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON109=null;
        Token EQ111=null;
        CalParser.type_return type110 = null;

        CalParser.expression_return expression112 = null;


        Object COLON109_tree=null;
        Object EQ111_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_EQ=new RewriteRuleTokenStream(adaptor,"token EQ");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:214:13: ( COLON type -> ^( TypeAttr type ) | EQ expression -> ^( ExprAttr ^( Expression expression ) ) )
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==COLON) ) {
                alt43=1;
            }
            else if ( (LA43_0==EQ) ) {
                alt43=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }
            switch (alt43) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:214:15: COLON type
                    {
                    COLON109=(Token)match(input,COLON,FOLLOW_COLON_in_typeAttrRest1235);  
                    stream_COLON.add(COLON109);

                    pushFollow(FOLLOW_type_in_typeAttrRest1237);
                    type110=type();

                    state._fsp--;

                    stream_type.add(type110.getTree());


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
                    // 214:26: -> ^( TypeAttr type )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:214:29: ^( TypeAttr type )
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
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:215:3: EQ expression
                    {
                    EQ111=(Token)match(input,EQ,FOLLOW_EQ_in_typeAttrRest1249);  
                    stream_EQ.add(EQ111);

                    pushFollow(FOLLOW_expression_in_typeAttrRest1251);
                    expression112=expression();

                    state._fsp--;

                    stream_expression.add(expression112.getTree());


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
                    // 215:17: -> ^( ExprAttr ^( Expression expression ) )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:215:20: ^( ExprAttr ^( Expression expression ) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ExprAttr, "ExprAttr"), root_1);

                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:215:31: ^( Expression expression )
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:217:1: typePars : typePar ( COMMA typePar )* -> ( typePar )+ ;
    public final CalParser.typePars_return typePars() throws RecognitionException {
        CalParser.typePars_return retval = new CalParser.typePars_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA114=null;
        CalParser.typePar_return typePar113 = null;

        CalParser.typePar_return typePar115 = null;


        Object COMMA114_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_typePar=new RewriteRuleSubtreeStream(adaptor,"rule typePar");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:217:9: ( typePar ( COMMA typePar )* -> ( typePar )+ )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:217:11: typePar ( COMMA typePar )*
            {
            pushFollow(FOLLOW_typePar_in_typePars1270);
            typePar113=typePar();

            state._fsp--;

            stream_typePar.add(typePar113.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:217:19: ( COMMA typePar )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==COMMA) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:217:20: COMMA typePar
            	    {
            	    COMMA114=(Token)match(input,COMMA,FOLLOW_COMMA_in_typePars1273);  
            	    stream_COMMA.add(COMMA114);

            	    pushFollow(FOLLOW_typePar_in_typePars1275);
            	    typePar115=typePar();

            	    state._fsp--;

            	    stream_typePar.add(typePar115.getTree());

            	    }
            	    break;

            	default :
            	    break loop44;
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
            // 217:36: -> ( typePar )+
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:219:1: typePar : ID ( LT type )? -> ^( TypePar ID ( type )? ) ;
    public final CalParser.typePar_return typePar() throws RecognitionException {
        CalParser.typePar_return retval = new CalParser.typePar_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID116=null;
        Token LT117=null;
        CalParser.type_return type118 = null;


        Object ID116_tree=null;
        Object LT117_tree=null;
        RewriteRuleTokenStream stream_LT=new RewriteRuleTokenStream(adaptor,"token LT");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:219:8: ( ID ( LT type )? -> ^( TypePar ID ( type )? ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:219:10: ID ( LT type )?
            {
            ID116=(Token)match(input,ID,FOLLOW_ID_in_typePar1289);  
            stream_ID.add(ID116);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:219:13: ( LT type )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==LT) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:219:14: LT type
                    {
                    LT117=(Token)match(input,LT,FOLLOW_LT_in_typePar1292);  
                    stream_LT.add(LT117);

                    pushFollow(FOLLOW_type_in_typePar1294);
                    type118=type();

                    state._fsp--;

                    stream_type.add(type118.getTree());

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
            // 219:24: -> ^( TypePar ID ( type )? )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:219:27: ^( TypePar ID ( type )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TypePar, "TypePar"), root_1);

                adaptor.addChild(root_1, stream_ID.nextNode());
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:219:40: ( type )?
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:224:1: mainExpression : expression EOF -> ^( Expression expression ) ;
    public final CalParser.mainExpression_return mainExpression() throws RecognitionException {
        CalParser.mainExpression_return retval = new CalParser.mainExpression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF120=null;
        CalParser.expression_return expression119 = null;


        Object EOF120_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:224:15: ( expression EOF -> ^( Expression expression ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:224:17: expression EOF
            {
            pushFollow(FOLLOW_expression_in_mainExpression1317);
            expression119=expression();

            state._fsp--;

            stream_expression.add(expression119.getTree());
            EOF120=(Token)match(input,EOF,FOLLOW_EOF_in_mainExpression1319);  
            stream_EOF.add(EOF120);



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
            // 224:32: -> ^( Expression expression )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:224:35: ^( Expression expression )
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:226:1: expression : factor ( binop factor )* ;
    public final CalParser.expression_return expression() throws RecognitionException {
        CalParser.expression_return retval = new CalParser.expression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CalParser.factor_return factor121 = null;

        CalParser.binop_return binop122 = null;

        CalParser.factor_return factor123 = null;



        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:226:11: ( factor ( binop factor )* )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:226:13: factor ( binop factor )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_factor_in_expression1334);
            factor121=factor();

            state._fsp--;

            adaptor.addChild(root_0, factor121.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:226:20: ( binop factor )*
            loop46:
            do {
                int alt46=2;
                int LA46_0 = input.LA(1);

                if ( ((LA46_0>=CARET && LA46_0<=DIV)||LA46_0==MINUS||LA46_0==PLUS||LA46_0==TIMES) ) {
                    alt46=1;
                }


                switch (alt46) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:226:21: binop factor
            	    {
            	    pushFollow(FOLLOW_binop_in_expression1337);
            	    binop122=binop();

            	    state._fsp--;

            	    adaptor.addChild(root_0, binop122.getTree());
            	    pushFollow(FOLLOW_factor_in_expression1339);
            	    factor123=factor();

            	    state._fsp--;

            	    adaptor.addChild(root_0, factor123.getTree());

            	    }
            	    break;

            	default :
            	    break loop46;
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:228:1: unop : (op= MINUS | op= NOT ) -> ^( UnOp $op) ;
    public final CalParser.unop_return unop() throws RecognitionException {
        CalParser.unop_return retval = new CalParser.unop_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token op=null;

        Object op_tree=null;
        RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
        RewriteRuleTokenStream stream_MINUS=new RewriteRuleTokenStream(adaptor,"token MINUS");

        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:228:5: ( (op= MINUS | op= NOT ) -> ^( UnOp $op) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:228:7: (op= MINUS | op= NOT )
            {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:228:7: (op= MINUS | op= NOT )
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==MINUS) ) {
                alt47=1;
            }
            else if ( (LA47_0==NOT) ) {
                alt47=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;
            }
            switch (alt47) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:228:8: op= MINUS
                    {
                    op=(Token)match(input,MINUS,FOLLOW_MINUS_in_unop1351);  
                    stream_MINUS.add(op);


                    }
                    break;
                case 2 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:228:19: op= NOT
                    {
                    op=(Token)match(input,NOT,FOLLOW_NOT_in_unop1357);  
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
            // 228:27: -> ^( UnOp $op)
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:228:30: ^( UnOp $op)
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:230:1: binop : (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET ) -> ^( BinOp $op) ;
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:230:6: ( (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET ) -> ^( BinOp $op) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:230:8: (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET )
            {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:230:8: (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET )
            int alt48=5;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt48=1;
                }
                break;
            case MINUS:
                {
                alt48=2;
                }
                break;
            case TIMES:
                {
                alt48=3;
                }
                break;
            case DIV:
                {
                alt48=4;
                }
                break;
            case CARET:
                {
                alt48=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;
            }

            switch (alt48) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:230:9: op= PLUS
                    {
                    op=(Token)match(input,PLUS,FOLLOW_PLUS_in_binop1377);  
                    stream_PLUS.add(op);


                    }
                    break;
                case 2 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:230:19: op= MINUS
                    {
                    op=(Token)match(input,MINUS,FOLLOW_MINUS_in_binop1383);  
                    stream_MINUS.add(op);


                    }
                    break;
                case 3 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:230:30: op= TIMES
                    {
                    op=(Token)match(input,TIMES,FOLLOW_TIMES_in_binop1389);  
                    stream_TIMES.add(op);


                    }
                    break;
                case 4 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:230:41: op= DIV
                    {
                    op=(Token)match(input,DIV,FOLLOW_DIV_in_binop1395);  
                    stream_DIV.add(op);


                    }
                    break;
                case 5 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:230:50: op= CARET
                    {
                    op=(Token)match(input,CARET,FOLLOW_CARET_in_binop1401);  
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
            // 230:60: -> ^( BinOp $op)
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:230:63: ^( BinOp $op)
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:232:1: factor : ( term | unop term -> ^( Expression unop term ) );
    public final CalParser.factor_return factor() throws RecognitionException {
        CalParser.factor_return retval = new CalParser.factor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CalParser.term_return term124 = null;

        CalParser.unop_return unop125 = null;

        CalParser.term_return term126 = null;


        RewriteRuleSubtreeStream stream_unop=new RewriteRuleSubtreeStream(adaptor,"rule unop");
        RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:232:7: ( term | unop term -> ^( Expression unop term ) )
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==LBRACKET||LA49_0==LPAREN||LA49_0==ID||(LA49_0>=FALSE && LA49_0<=FLOAT)||LA49_0==INTEGER||LA49_0==STRING||LA49_0==TRUE) ) {
                alt49=1;
            }
            else if ( (LA49_0==MINUS||LA49_0==NOT) ) {
                alt49=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;
            }
            switch (alt49) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:232:9: term
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_term_in_factor1418);
                    term124=term();

                    state._fsp--;

                    adaptor.addChild(root_0, term124.getTree());

                    }
                    break;
                case 2 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:233:3: unop term
                    {
                    pushFollow(FOLLOW_unop_in_factor1422);
                    unop125=unop();

                    state._fsp--;

                    stream_unop.add(unop125.getTree());
                    pushFollow(FOLLOW_term_in_factor1424);
                    term126=term();

                    state._fsp--;

                    stream_term.add(term126.getTree());


                    // AST REWRITE
                    // elements: unop, term
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 233:13: -> ^( Expression unop term )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:233:16: ^( Expression unop term )
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:235:1: term : ( atom | LPAREN expression RPAREN -> ^( Expression expression ) );
    public final CalParser.term_return term() throws RecognitionException {
        CalParser.term_return retval = new CalParser.term_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LPAREN128=null;
        Token RPAREN130=null;
        CalParser.atom_return atom127 = null;

        CalParser.expression_return expression129 = null;


        Object LPAREN128_tree=null;
        Object RPAREN130_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:235:5: ( atom | LPAREN expression RPAREN -> ^( Expression expression ) )
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==LBRACKET||LA50_0==ID||(LA50_0>=FALSE && LA50_0<=FLOAT)||LA50_0==INTEGER||LA50_0==STRING||LA50_0==TRUE) ) {
                alt50=1;
            }
            else if ( (LA50_0==LPAREN) ) {
                alt50=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;
            }
            switch (alt50) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:235:7: atom
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_atom_in_term1441);
                    atom127=atom();

                    state._fsp--;

                    adaptor.addChild(root_0, atom127.getTree());

                    }
                    break;
                case 2 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:236:5: LPAREN expression RPAREN
                    {
                    LPAREN128=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_term1447);  
                    stream_LPAREN.add(LPAREN128);

                    pushFollow(FOLLOW_expression_in_term1449);
                    expression129=expression();

                    state._fsp--;

                    stream_expression.add(expression129.getTree());
                    RPAREN130=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_term1451);  
                    stream_RPAREN.add(RPAREN130);



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
                    // 236:30: -> ^( Expression expression )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:236:33: ^( Expression expression )
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:238:1: atom : ( ID -> ^( Var ID ) | FLOAT -> ^( Real FLOAT ) | INTEGER -> ^( Integer INTEGER ) | STRING -> ^( String STRING ) | TRUE -> ^( Boolean TRUE ) | FALSE -> ^( Boolean FALSE ) | LBRACKET ( expression ( COMMA expression )* )? RBRACKET -> ^( List ( expression )* ) );
    public final CalParser.atom_return atom() throws RecognitionException {
        CalParser.atom_return retval = new CalParser.atom_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID131=null;
        Token FLOAT132=null;
        Token INTEGER133=null;
        Token STRING134=null;
        Token TRUE135=null;
        Token FALSE136=null;
        Token LBRACKET137=null;
        Token COMMA139=null;
        Token RBRACKET141=null;
        CalParser.expression_return expression138 = null;

        CalParser.expression_return expression140 = null;


        Object ID131_tree=null;
        Object FLOAT132_tree=null;
        Object INTEGER133_tree=null;
        Object STRING134_tree=null;
        Object TRUE135_tree=null;
        Object FALSE136_tree=null;
        Object LBRACKET137_tree=null;
        Object COMMA139_tree=null;
        Object RBRACKET141_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_INTEGER=new RewriteRuleTokenStream(adaptor,"token INTEGER");
        RewriteRuleTokenStream stream_FLOAT=new RewriteRuleTokenStream(adaptor,"token FLOAT");
        RewriteRuleTokenStream stream_RBRACKET=new RewriteRuleTokenStream(adaptor,"token RBRACKET");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_FALSE=new RewriteRuleTokenStream(adaptor,"token FALSE");
        RewriteRuleTokenStream stream_TRUE=new RewriteRuleTokenStream(adaptor,"token TRUE");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:238:5: ( ID -> ^( Var ID ) | FLOAT -> ^( Real FLOAT ) | INTEGER -> ^( Integer INTEGER ) | STRING -> ^( String STRING ) | TRUE -> ^( Boolean TRUE ) | FALSE -> ^( Boolean FALSE ) | LBRACKET ( expression ( COMMA expression )* )? RBRACKET -> ^( List ( expression )* ) )
            int alt53=7;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt53=1;
                }
                break;
            case FLOAT:
                {
                alt53=2;
                }
                break;
            case INTEGER:
                {
                alt53=3;
                }
                break;
            case STRING:
                {
                alt53=4;
                }
                break;
            case TRUE:
                {
                alt53=5;
                }
                break;
            case FALSE:
                {
                alt53=6;
                }
                break;
            case LBRACKET:
                {
                alt53=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;
            }

            switch (alt53) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:238:7: ID
                    {
                    ID131=(Token)match(input,ID,FOLLOW_ID_in_atom1466);  
                    stream_ID.add(ID131);



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
                    // 238:10: -> ^( Var ID )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:238:13: ^( Var ID )
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
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:239:3: FLOAT
                    {
                    FLOAT132=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_atom1478);  
                    stream_FLOAT.add(FLOAT132);



                    // AST REWRITE
                    // elements: FLOAT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 239:9: -> ^( Real FLOAT )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:239:12: ^( Real FLOAT )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Real, "Real"), root_1);

                        adaptor.addChild(root_1, stream_FLOAT.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 3 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:240:3: INTEGER
                    {
                    INTEGER133=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_atom1490);  
                    stream_INTEGER.add(INTEGER133);



                    // AST REWRITE
                    // elements: INTEGER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 240:11: -> ^( Integer INTEGER )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:240:14: ^( Integer INTEGER )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Integer, "Integer"), root_1);

                        adaptor.addChild(root_1, stream_INTEGER.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 4 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:241:3: STRING
                    {
                    STRING134=(Token)match(input,STRING,FOLLOW_STRING_in_atom1502);  
                    stream_STRING.add(STRING134);



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
                    // 241:10: -> ^( String STRING )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:241:13: ^( String STRING )
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
                case 5 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:242:3: TRUE
                    {
                    TRUE135=(Token)match(input,TRUE,FOLLOW_TRUE_in_atom1514);  
                    stream_TRUE.add(TRUE135);



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
                    // 242:8: -> ^( Boolean TRUE )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:242:11: ^( Boolean TRUE )
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
                case 6 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:243:3: FALSE
                    {
                    FALSE136=(Token)match(input,FALSE,FOLLOW_FALSE_in_atom1526);  
                    stream_FALSE.add(FALSE136);



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
                    // 243:9: -> ^( Boolean FALSE )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:243:12: ^( Boolean FALSE )
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
                case 7 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:244:3: LBRACKET ( expression ( COMMA expression )* )? RBRACKET
                    {
                    LBRACKET137=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_atom1538);  
                    stream_LBRACKET.add(LBRACKET137);

                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:244:12: ( expression ( COMMA expression )* )?
                    int alt52=2;
                    int LA52_0 = input.LA(1);

                    if ( (LA52_0==LBRACKET||LA52_0==LPAREN||LA52_0==ID||(LA52_0>=FALSE && LA52_0<=FLOAT)||LA52_0==INTEGER||LA52_0==MINUS||LA52_0==NOT||LA52_0==STRING||LA52_0==TRUE) ) {
                        alt52=1;
                    }
                    switch (alt52) {
                        case 1 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:244:13: expression ( COMMA expression )*
                            {
                            pushFollow(FOLLOW_expression_in_atom1541);
                            expression138=expression();

                            state._fsp--;

                            stream_expression.add(expression138.getTree());
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:244:24: ( COMMA expression )*
                            loop51:
                            do {
                                int alt51=2;
                                int LA51_0 = input.LA(1);

                                if ( (LA51_0==COMMA) ) {
                                    alt51=1;
                                }


                                switch (alt51) {
                            	case 1 :
                            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:244:25: COMMA expression
                            	    {
                            	    COMMA139=(Token)match(input,COMMA,FOLLOW_COMMA_in_atom1544);  
                            	    stream_COMMA.add(COMMA139);

                            	    pushFollow(FOLLOW_expression_in_atom1546);
                            	    expression140=expression();

                            	    state._fsp--;

                            	    stream_expression.add(expression140.getTree());

                            	    }
                            	    break;

                            	default :
                            	    break loop51;
                                }
                            } while (true);


                            }
                            break;

                    }

                    RBRACKET141=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_atom1552);  
                    stream_RBRACKET.add(RBRACKET141);



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
                    // 244:55: -> ^( List ( expression )* )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:244:58: ^( List ( expression )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(List, "List"), root_1);

                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:244:65: ( expression )*
                        while ( stream_expression.hasNext() ) {
                            adaptor.addChild(root_1, stream_expression.nextTree());

                        }
                        stream_expression.reset();

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


 

    public static final BitSet FOLLOW_NETWORK_in_network196 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_qualifiedId_in_network198 = new BitSet(new long[]{0x0000000A00000000L});
    public static final BitSet FOLLOW_LBRACKET_in_network201 = new BitSet(new long[]{0x0000400400000000L});
    public static final BitSet FOLLOW_typePars_in_network203 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_RBRACKET_in_network206 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LPAREN_in_network212 = new BitSet(new long[]{0x0000401000000000L});
    public static final BitSet FOLLOW_parameters_in_network214 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RPAREN_in_network217 = new BitSet(new long[]{0x0000402000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_portDecls_in_network223 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_DOUBLE_EQUAL_ARROW_in_network226 = new BitSet(new long[]{0x0000404000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_portDecls_in_network230 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_COLON_in_network233 = new BitSet(new long[]{0x0001218000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_oneImport_in_network237 = new BitSet(new long[]{0x0001218000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_varDeclSection_in_network240 = new BitSet(new long[]{0x0001208000000000L});
    public static final BitSet FOLLOW_entitySection_in_network245 = new BitSet(new long[]{0x0001008000000000L});
    public static final BitSet FOLLOW_structureSection_in_network248 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_END_in_network253 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_network255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_varDeclSection313 = new BitSet(new long[]{0x0000420000000000L});
    public static final BitSet FOLLOW_varDecl_in_varDeclSection315 = new BitSet(new long[]{0x0000420000000002L});
    public static final BitSet FOLLOW_MUTABLE_in_varDecl328 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_typeAndId_in_varDecl331 = new BitSet(new long[]{0x00001C0000000000L});
    public static final BitSet FOLLOW_EQ_in_varDecl337 = new BitSet(new long[]{0x3000400A00000000L,0x0000000000000A49L});
    public static final BitSet FOLLOW_COLON_EQUAL_in_varDecl341 = new BitSet(new long[]{0x3000400A00000000L,0x0000000000000A49L});
    public static final BitSet FOLLOW_expression_in_varDecl344 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_varDecl373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENTITIES_in_entitySection381 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_entityDecl_in_entitySection383 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_ID_in_entityDecl396 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_EQ_in_entityDecl398 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_entityExpr_in_entityDecl400 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_entityDecl402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_entityExpr423 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LPAREN_in_entityExpr425 = new BitSet(new long[]{0x0000401000000000L});
    public static final BitSet FOLLOW_entityPars_in_entityExpr427 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RPAREN_in_entityExpr430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entityPar_in_entityPars452 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_entityPars455 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_entityPar_in_entityPars457 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_ID_in_entityPar471 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_EQ_in_entityPar473 = new BitSet(new long[]{0x3000400A00000000L,0x0000000000000A49L});
    public static final BitSet FOLLOW_expression_in_entityPar475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRUCTURE_in_structureSection501 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_structureStmt_in_structureSection503 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_connector_in_structureStmt518 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_DOUBLE_DASH_ARROW_in_structureStmt520 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_connector_in_structureStmt524 = new BitSet(new long[]{0x0008100000000000L});
    public static final BitSet FOLLOW_attributeSection_in_structureStmt526 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_structureStmt529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_connector553 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_DOT_in_connector559 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_ID_in_connector563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_attributeSection608 = new BitSet(new long[]{0x0010400000000000L});
    public static final BitSet FOLLOW_attributeDecl_in_attributeSection610 = new BitSet(new long[]{0x0010400000000000L});
    public static final BitSet FOLLOW_RBRACE_in_attributeSection613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_attributeDecl621 = new BitSet(new long[]{0x0000044000000000L});
    public static final BitSet FOLLOW_EQ_in_attributeDecl624 = new BitSet(new long[]{0x3000400A00000000L,0x0000000000000A49L});
    public static final BitSet FOLLOW_expression_in_attributeDecl626 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_attributeDecl628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_attributeDecl632 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_type_in_attributeDecl634 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_attributeDecl636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_oneImport_in_actor648 = new BitSet(new long[]{0x0020000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_ACTOR_in_actor651 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_ID_in_actor653 = new BitSet(new long[]{0x0000000A00000000L});
    public static final BitSet FOLLOW_LBRACKET_in_actor658 = new BitSet(new long[]{0x0000400400000000L});
    public static final BitSet FOLLOW_typePars_in_actor660 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_RBRACKET_in_actor663 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_LPAREN_in_actor669 = new BitSet(new long[]{0x0000401000000000L});
    public static final BitSet FOLLOW_parameters_in_actor671 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actor674 = new BitSet(new long[]{0x0000402000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_portDecls_in_actor680 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_DOUBLE_EQUAL_ARROW_in_actor683 = new BitSet(new long[]{0x0000404000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_portDecls_in_actor687 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_COLON_in_actor690 = new BitSet(new long[]{0xFFDFFFFF00000000L,0x0000000000000FFFL});
    public static final BitSet FOLLOW_ignore_in_actor692 = new BitSet(new long[]{0xFFDFFFFF00000000L,0x0000000000000FFFL});
    public static final BitSet FOLLOW_EOF_in_actor695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_ignore0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_oneImport915 = new BitSet(new long[]{0x0040400000000000L});
    public static final BitSet FOLLOW_importRest_in_oneImport917 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_oneImport919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALL_in_importRest927 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_qualifiedId_in_importRest929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualifiedId_in_importRest935 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_EQ_in_importRest938 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_ID_in_importRest940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_qualifiedId950 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_DOT_in_qualifiedId953 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_ID_in_qualifiedId955 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_typeAndId_in_parameter968 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_EQ_in_parameter973 = new BitSet(new long[]{0x3000400A00000000L,0x0000000000000A49L});
    public static final BitSet FOLLOW_expression_in_parameter975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parameter_in_parameters1009 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_parameters1012 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_parameter_in_parameters1014 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_MULTI_in_portDecl1031 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_typeAndId_in_portDecl1034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_portDecl_in_portDecls1049 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_portDecls1052 = new BitSet(new long[]{0x0000400000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_portDecl_in_portDecls1054 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_typeAndId_in_mainParameter1072 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_mainParameter1074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_typeAndId1091 = new BitSet(new long[]{0x0000400A00000002L});
    public static final BitSet FOLLOW_typeRest_in_typeAndId1096 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_ID_in_typeAndId1101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_type1145 = new BitSet(new long[]{0x0000000A00000002L});
    public static final BitSet FOLLOW_typeRest_in_type1147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_typeRest1170 = new BitSet(new long[]{0x0000400400000000L});
    public static final BitSet FOLLOW_typePars_in_typeRest1172 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_RBRACKET_in_typeRest1175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_typeRest1186 = new BitSet(new long[]{0x0000401000000000L});
    public static final BitSet FOLLOW_typeAttrs_in_typeRest1188 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RPAREN_in_typeRest1191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeAttr_in_typeAttrs1203 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_typeAttrs1206 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_typeAttr_in_typeAttrs1208 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_ID_in_typeAttr1222 = new BitSet(new long[]{0x0000044000000000L});
    public static final BitSet FOLLOW_typeAttrRest_in_typeAttr1224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_typeAttrRest1235 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_type_in_typeAttrRest1237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_typeAttrRest1249 = new BitSet(new long[]{0x3000400A00000000L,0x0000000000000A49L});
    public static final BitSet FOLLOW_expression_in_typeAttrRest1251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typePar_in_typePars1270 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_typePars1273 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_typePar_in_typePars1275 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_ID_in_typePar1289 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_LT_in_typePar1292 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_type_in_typePar1294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_mainExpression1317 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_mainExpression1319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_factor_in_expression1334 = new BitSet(new long[]{0x0300000000000002L,0x0000000000000488L});
    public static final BitSet FOLLOW_binop_in_expression1337 = new BitSet(new long[]{0x3000400A00000000L,0x0000000000000A49L});
    public static final BitSet FOLLOW_factor_in_expression1339 = new BitSet(new long[]{0x0300000000000002L,0x0000000000000488L});
    public static final BitSet FOLLOW_MINUS_in_unop1351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_unop1357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_binop1377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_binop1383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TIMES_in_binop1389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIV_in_binop1395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CARET_in_binop1401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_factor1418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unop_in_factor1422 = new BitSet(new long[]{0x3000400A00000000L,0x0000000000000A01L});
    public static final BitSet FOLLOW_term_in_factor1424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_term1441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_term1447 = new BitSet(new long[]{0x3000400A00000000L,0x0000000000000A49L});
    public static final BitSet FOLLOW_expression_in_term1449 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RPAREN_in_term1451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_atom1466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_atom1478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_atom1490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom1502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_atom1514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_atom1526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_atom1538 = new BitSet(new long[]{0x3000400E00000000L,0x0000000000000A49L});
    public static final BitSet FOLLOW_expression_in_atom1541 = new BitSet(new long[]{0x0000800400000000L});
    public static final BitSet FOLLOW_COMMA_in_atom1544 = new BitSet(new long[]{0x3000400A00000000L,0x0000000000000A49L});
    public static final BitSet FOLLOW_expression_in_atom1546 = new BitSet(new long[]{0x0000800400000000L});
    public static final BitSet FOLLOW_RBRACKET_in_atom1552 = new BitSet(new long[]{0x0000000000000002L});

}