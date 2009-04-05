// $ANTLR 3.1.2 D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g 2009-04-05 18:37:01

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class CalParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "Connector", "EntityDecl", "EntityExpr", "EntityPar", "Network", "StructureStmt", "VarDecl", "Actor", "Dot", "Empty", "Name", "Inputs", "Outputs", "PortDecl", "QualifiedId", "Parameter", "Type", "TypeAttr", "ExprAttr", "TypePar", "BinOp", "Boolean", "Expression", "Integer", "List", "Minus", "Not", "Real", "String", "UnOp", "Var", "NETWORK", "LBRACKET", "RBRACKET", "LPAREN", "RPAREN", "COLON", "END", "DOUBLE_EQUAL_ARROW", "VAR", "MUTABLE", "EQ", "COLON_EQUAL", "SEMICOLON", "ENTITIES", "ID", "COMMA", "STRUCTURE", "DOUBLE_DASH_ARROW", "DOT", "LBRACE", "RBRACE", "ACTOR", "IMPORT", "ALL", "MULTI", "LT", "MINUS", "NOT", "PLUS", "TIMES", "DIV", "CARET", "FLOAT", "INTEGER", "STRING", "TRUE", "FALSE", "LINE_COMMENT", "MULTI_LINE_COMMENT", "WHITESPACE", "GE", "GT", "LE", "NE", "ARROW", "DOUBLE_DOT", "DOUBLE_COLON", "SHARP"
    };
    public static final int StructureStmt=9;
    public static final int LT=60;
    public static final int Inputs=15;
    public static final int LBRACE=54;
    public static final int Empty=13;
    public static final int FLOAT=67;
    public static final int MULTI=59;
    public static final int Actor=11;
    public static final int VarDecl=10;
    public static final int NOT=62;
    public static final int ID=49;
    public static final int EOF=-1;
    public static final int DOUBLE_DOT=80;
    public static final int STRUCTURE=51;
    public static final int LPAREN=38;
    public static final int LBRACKET=36;
    public static final int RPAREN=39;
    public static final int IMPORT=57;
    public static final int COLON_EQUAL=46;
    public static final int COMMA=50;
    public static final int CARET=66;
    public static final int ALL=58;
    public static final int TypeAttr=21;
    public static final int ENTITIES=48;
    public static final int Real=31;
    public static final int PLUS=63;
    public static final int VAR=43;
    public static final int Network=8;
    public static final int String=32;
    public static final int EQ=45;
    public static final int RBRACKET=37;
    public static final int DOT=53;
    public static final int EntityDecl=5;
    public static final int NE=78;
    public static final int INTEGER=68;
    public static final int Outputs=16;
    public static final int DOUBLE_EQUAL_ARROW=42;
    public static final int GE=75;
    public static final int DOUBLE_DASH_ARROW=52;
    public static final int EntityPar=7;
    public static final int SHARP=82;
    public static final int RBRACE=55;
    public static final int Type=20;
    public static final int LINE_COMMENT=72;
    public static final int QualifiedId=18;
    public static final int WHITESPACE=74;
    public static final int SEMICOLON=47;
    public static final int NETWORK=35;
    public static final int MINUS=61;
    public static final int TRUE=70;
    public static final int Expression=26;
    public static final int MULTI_LINE_COMMENT=73;
    public static final int Parameter=19;
    public static final int List=28;
    public static final int TypePar=23;
    public static final int COLON=40;
    public static final int UnOp=33;
    public static final int Minus=29;
    public static final int Connector=4;
    public static final int DOUBLE_COLON=81;
    public static final int Boolean=25;
    public static final int ACTOR=56;
    public static final int EntityExpr=6;
    public static final int ExprAttr=22;
    public static final int Not=30;
    public static final int Name=14;
    public static final int Dot=12;
    public static final int ARROW=79;
    public static final int GT=76;
    public static final int DIV=65;
    public static final int TIMES=64;
    public static final int BinOp=24;
    public static final int END=41;
    public static final int FALSE=71;
    public static final int MUTABLE=44;
    public static final int PortDecl=17;
    public static final int LE=77;
    public static final int Integer=27;
    public static final int Var=34;
    public static final int STRING=69;

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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:79:1: network : NETWORK qualifiedId ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN portSignature COLON ( oneImport )* ( varDeclSection )? ( entitySection )? ( structureSection )? END EOF -> ^( Network qualifiedId ( parameters )? portSignature ( varDeclSection )? ( entitySection )? ( structureSection )? ) ;
    public final CalParser.network_return network() throws RecognitionException {
        CalParser.network_return retval = new CalParser.network_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NETWORK1=null;
        Token LBRACKET3=null;
        Token RBRACKET5=null;
        Token LPAREN6=null;
        Token RPAREN8=null;
        Token COLON10=null;
        Token END15=null;
        Token EOF16=null;
        CalParser.qualifiedId_return qualifiedId2 = null;

        CalParser.typePars_return typePars4 = null;

        CalParser.parameters_return parameters7 = null;

        CalParser.portSignature_return portSignature9 = null;

        CalParser.oneImport_return oneImport11 = null;

        CalParser.varDeclSection_return varDeclSection12 = null;

        CalParser.entitySection_return entitySection13 = null;

        CalParser.structureSection_return structureSection14 = null;


        Object NETWORK1_tree=null;
        Object LBRACKET3_tree=null;
        Object RBRACKET5_tree=null;
        Object LPAREN6_tree=null;
        Object RPAREN8_tree=null;
        Object COLON10_tree=null;
        Object END15_tree=null;
        Object EOF16_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
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
        RewriteRuleSubtreeStream stream_portSignature=new RewriteRuleSubtreeStream(adaptor,"rule portSignature");
        RewriteRuleSubtreeStream stream_parameters=new RewriteRuleSubtreeStream(adaptor,"rule parameters");
        RewriteRuleSubtreeStream stream_oneImport=new RewriteRuleSubtreeStream(adaptor,"rule oneImport");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:79:8: ( NETWORK qualifiedId ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN portSignature COLON ( oneImport )* ( varDeclSection )? ( entitySection )? ( structureSection )? END EOF -> ^( Network qualifiedId ( parameters )? portSignature ( varDeclSection )? ( entitySection )? ( structureSection )? ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:79:10: NETWORK qualifiedId ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN portSignature COLON ( oneImport )* ( varDeclSection )? ( entitySection )? ( structureSection )? END EOF
            {
            NETWORK1=(Token)match(input,NETWORK,FOLLOW_NETWORK_in_network211);  
            stream_NETWORK.add(NETWORK1);

            pushFollow(FOLLOW_qualifiedId_in_network213);
            qualifiedId2=qualifiedId();

            state._fsp--;

            stream_qualifiedId.add(qualifiedId2.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:79:30: ( LBRACKET ( typePars )? RBRACKET )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==LBRACKET) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:79:31: LBRACKET ( typePars )? RBRACKET
                    {
                    LBRACKET3=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_network216);  
                    stream_LBRACKET.add(LBRACKET3);

                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:79:40: ( typePars )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==ID) ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:79:40: typePars
                            {
                            pushFollow(FOLLOW_typePars_in_network218);
                            typePars4=typePars();

                            state._fsp--;

                            stream_typePars.add(typePars4.getTree());

                            }
                            break;

                    }

                    RBRACKET5=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_network221);  
                    stream_RBRACKET.add(RBRACKET5);


                    }
                    break;

            }

            LPAREN6=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_network227);  
            stream_LPAREN.add(LPAREN6);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:80:10: ( parameters )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==ID) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:80:10: parameters
                    {
                    pushFollow(FOLLOW_parameters_in_network229);
                    parameters7=parameters();

                    state._fsp--;

                    stream_parameters.add(parameters7.getTree());

                    }
                    break;

            }

            RPAREN8=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_network232);  
            stream_RPAREN.add(RPAREN8);

            pushFollow(FOLLOW_portSignature_in_network236);
            portSignature9=portSignature();

            state._fsp--;

            stream_portSignature.add(portSignature9.getTree());
            COLON10=(Token)match(input,COLON,FOLLOW_COLON_in_network238);  
            stream_COLON.add(COLON10);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:82:3: ( oneImport )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==IMPORT) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:82:3: oneImport
            	    {
            	    pushFollow(FOLLOW_oneImport_in_network242);
            	    oneImport11=oneImport();

            	    state._fsp--;

            	    stream_oneImport.add(oneImport11.getTree());

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:82:14: ( varDeclSection )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==VAR) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:82:14: varDeclSection
                    {
                    pushFollow(FOLLOW_varDeclSection_in_network245);
                    varDeclSection12=varDeclSection();

                    state._fsp--;

                    stream_varDeclSection.add(varDeclSection12.getTree());

                    }
                    break;

            }

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:83:3: ( entitySection )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==ENTITIES) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:83:3: entitySection
                    {
                    pushFollow(FOLLOW_entitySection_in_network250);
                    entitySection13=entitySection();

                    state._fsp--;

                    stream_entitySection.add(entitySection13.getTree());

                    }
                    break;

            }

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:83:18: ( structureSection )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==STRUCTURE) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:83:18: structureSection
                    {
                    pushFollow(FOLLOW_structureSection_in_network253);
                    structureSection14=structureSection();

                    state._fsp--;

                    stream_structureSection.add(structureSection14.getTree());

                    }
                    break;

            }

            END15=(Token)match(input,END,FOLLOW_END_in_network258);  
            stream_END.add(END15);

            EOF16=(Token)match(input,EOF,FOLLOW_EOF_in_network260);  
            stream_EOF.add(EOF16);



            // AST REWRITE
            // elements: entitySection, portSignature, varDeclSection, parameters, structureSection, qualifiedId
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 84:11: -> ^( Network qualifiedId ( parameters )? portSignature ( varDeclSection )? ( entitySection )? ( structureSection )? )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:85:5: ^( Network qualifiedId ( parameters )? portSignature ( varDeclSection )? ( entitySection )? ( structureSection )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Network, "Network"), root_1);

                adaptor.addChild(root_1, stream_qualifiedId.nextTree());
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:85:27: ( parameters )?
                if ( stream_parameters.hasNext() ) {
                    adaptor.addChild(root_1, stream_parameters.nextTree());

                }
                stream_parameters.reset();
                adaptor.addChild(root_1, stream_portSignature.nextTree());
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:86:7: ( varDeclSection )?
                if ( stream_varDeclSection.hasNext() ) {
                    adaptor.addChild(root_1, stream_varDeclSection.nextTree());

                }
                stream_varDeclSection.reset();
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:86:23: ( entitySection )?
                if ( stream_entitySection.hasNext() ) {
                    adaptor.addChild(root_1, stream_entitySection.nextTree());

                }
                stream_entitySection.reset();
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:86:38: ( structureSection )?
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

    public static class portSignature_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "portSignature"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:88:1: portSignature : inputPorts DOUBLE_EQUAL_ARROW outputPorts -> inputPorts outputPorts ;
    public final CalParser.portSignature_return portSignature() throws RecognitionException {
        CalParser.portSignature_return retval = new CalParser.portSignature_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DOUBLE_EQUAL_ARROW18=null;
        CalParser.inputPorts_return inputPorts17 = null;

        CalParser.outputPorts_return outputPorts19 = null;


        Object DOUBLE_EQUAL_ARROW18_tree=null;
        RewriteRuleTokenStream stream_DOUBLE_EQUAL_ARROW=new RewriteRuleTokenStream(adaptor,"token DOUBLE_EQUAL_ARROW");
        RewriteRuleSubtreeStream stream_inputPorts=new RewriteRuleSubtreeStream(adaptor,"rule inputPorts");
        RewriteRuleSubtreeStream stream_outputPorts=new RewriteRuleSubtreeStream(adaptor,"rule outputPorts");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:88:14: ( inputPorts DOUBLE_EQUAL_ARROW outputPorts -> inputPorts outputPorts )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:88:16: inputPorts DOUBLE_EQUAL_ARROW outputPorts
            {
            pushFollow(FOLLOW_inputPorts_in_portSignature299);
            inputPorts17=inputPorts();

            state._fsp--;

            stream_inputPorts.add(inputPorts17.getTree());
            DOUBLE_EQUAL_ARROW18=(Token)match(input,DOUBLE_EQUAL_ARROW,FOLLOW_DOUBLE_EQUAL_ARROW_in_portSignature301);  
            stream_DOUBLE_EQUAL_ARROW.add(DOUBLE_EQUAL_ARROW18);

            pushFollow(FOLLOW_outputPorts_in_portSignature303);
            outputPorts19=outputPorts();

            state._fsp--;

            stream_outputPorts.add(outputPorts19.getTree());


            // AST REWRITE
            // elements: outputPorts, inputPorts
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 88:58: -> inputPorts outputPorts
            {
                adaptor.addChild(root_0, stream_inputPorts.nextTree());
                adaptor.addChild(root_0, stream_outputPorts.nextTree());

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
    // $ANTLR end "portSignature"

    public static class inputPorts_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "inputPorts"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:90:1: inputPorts : ( portDecls -> ^( Inputs portDecls ) | -> ^( Inputs Empty ) );
    public final CalParser.inputPorts_return inputPorts() throws RecognitionException {
        CalParser.inputPorts_return retval = new CalParser.inputPorts_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CalParser.portDecls_return portDecls20 = null;


        RewriteRuleSubtreeStream stream_portDecls=new RewriteRuleSubtreeStream(adaptor,"rule portDecls");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:90:11: ( portDecls -> ^( Inputs portDecls ) | -> ^( Inputs Empty ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==ID||LA8_0==MULTI) ) {
                alt8=1;
            }
            else if ( (LA8_0==DOUBLE_EQUAL_ARROW) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:90:13: portDecls
                    {
                    pushFollow(FOLLOW_portDecls_in_inputPorts316);
                    portDecls20=portDecls();

                    state._fsp--;

                    stream_portDecls.add(portDecls20.getTree());


                    // AST REWRITE
                    // elements: portDecls
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 90:23: -> ^( Inputs portDecls )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:90:26: ^( Inputs portDecls )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Inputs, "Inputs"), root_1);

                        adaptor.addChild(root_1, stream_portDecls.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:90:48: 
                    {

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 90:48: -> ^( Inputs Empty )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:90:51: ^( Inputs Empty )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Inputs, "Inputs"), root_1);

                        adaptor.addChild(root_1, (Object)adaptor.create(Empty, "Empty"));

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
    // $ANTLR end "inputPorts"

    public static class outputPorts_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "outputPorts"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:92:1: outputPorts : ( portDecls -> ^( Outputs portDecls ) | -> ^( Outputs Empty ) );
    public final CalParser.outputPorts_return outputPorts() throws RecognitionException {
        CalParser.outputPorts_return retval = new CalParser.outputPorts_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CalParser.portDecls_return portDecls21 = null;


        RewriteRuleSubtreeStream stream_portDecls=new RewriteRuleSubtreeStream(adaptor,"rule portDecls");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:92:12: ( portDecls -> ^( Outputs portDecls ) | -> ^( Outputs Empty ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==ID||LA9_0==MULTI) ) {
                alt9=1;
            }
            else if ( (LA9_0==COLON) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:92:14: portDecls
                    {
                    pushFollow(FOLLOW_portDecls_in_outputPorts341);
                    portDecls21=portDecls();

                    state._fsp--;

                    stream_portDecls.add(portDecls21.getTree());


                    // AST REWRITE
                    // elements: portDecls
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 92:24: -> ^( Outputs portDecls )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:92:27: ^( Outputs portDecls )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Outputs, "Outputs"), root_1);

                        adaptor.addChild(root_1, stream_portDecls.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:92:50: 
                    {

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 92:50: -> ^( Outputs Empty )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:92:53: ^( Outputs Empty )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Outputs, "Outputs"), root_1);

                        adaptor.addChild(root_1, (Object)adaptor.create(Empty, "Empty"));

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
    // $ANTLR end "outputPorts"

    public static class varDeclSection_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "varDeclSection"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:95:1: varDeclSection : VAR ( varDecl )+ -> ( varDecl )+ ;
    public final CalParser.varDeclSection_return varDeclSection() throws RecognitionException {
        CalParser.varDeclSection_return retval = new CalParser.varDeclSection_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token VAR22=null;
        CalParser.varDecl_return varDecl23 = null;


        Object VAR22_tree=null;
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleSubtreeStream stream_varDecl=new RewriteRuleSubtreeStream(adaptor,"rule varDecl");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:95:15: ( VAR ( varDecl )+ -> ( varDecl )+ )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:95:17: VAR ( varDecl )+
            {
            VAR22=(Token)match(input,VAR,FOLLOW_VAR_in_varDeclSection367);  
            stream_VAR.add(VAR22);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:95:21: ( varDecl )+
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
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:95:21: varDecl
            	    {
            	    pushFollow(FOLLOW_varDecl_in_varDeclSection369);
            	    varDecl23=varDecl();

            	    state._fsp--;

            	    stream_varDecl.add(varDecl23.getTree());

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
            // 95:30: -> ( varDecl )+
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:97:1: varDecl : ( MUTABLE )? typeAndId ( ( EQ | COLON_EQUAL ) expression -> ^( VarDecl typeAndId ^( Expression expression ) ) | -> ^( VarDecl typeAndId ) ) SEMICOLON ;
    public final CalParser.varDecl_return varDecl() throws RecognitionException {
        CalParser.varDecl_return retval = new CalParser.varDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MUTABLE24=null;
        Token EQ26=null;
        Token COLON_EQUAL27=null;
        Token SEMICOLON29=null;
        CalParser.typeAndId_return typeAndId25 = null;

        CalParser.expression_return expression28 = null;


        Object MUTABLE24_tree=null;
        Object EQ26_tree=null;
        Object COLON_EQUAL27_tree=null;
        Object SEMICOLON29_tree=null;
        RewriteRuleTokenStream stream_COLON_EQUAL=new RewriteRuleTokenStream(adaptor,"token COLON_EQUAL");
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleTokenStream stream_EQ=new RewriteRuleTokenStream(adaptor,"token EQ");
        RewriteRuleTokenStream stream_MUTABLE=new RewriteRuleTokenStream(adaptor,"token MUTABLE");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_typeAndId=new RewriteRuleSubtreeStream(adaptor,"rule typeAndId");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:97:8: ( ( MUTABLE )? typeAndId ( ( EQ | COLON_EQUAL ) expression -> ^( VarDecl typeAndId ^( Expression expression ) ) | -> ^( VarDecl typeAndId ) ) SEMICOLON )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:97:10: ( MUTABLE )? typeAndId ( ( EQ | COLON_EQUAL ) expression -> ^( VarDecl typeAndId ^( Expression expression ) ) | -> ^( VarDecl typeAndId ) ) SEMICOLON
            {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:97:10: ( MUTABLE )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==MUTABLE) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:97:10: MUTABLE
                    {
                    MUTABLE24=(Token)match(input,MUTABLE,FOLLOW_MUTABLE_in_varDecl382);  
                    stream_MUTABLE.add(MUTABLE24);


                    }
                    break;

            }

            pushFollow(FOLLOW_typeAndId_in_varDecl385);
            typeAndId25=typeAndId();

            state._fsp--;

            stream_typeAndId.add(typeAndId25.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:98:3: ( ( EQ | COLON_EQUAL ) expression -> ^( VarDecl typeAndId ^( Expression expression ) ) | -> ^( VarDecl typeAndId ) )
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
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:98:4: ( EQ | COLON_EQUAL ) expression
                    {
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:98:4: ( EQ | COLON_EQUAL )
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
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:98:5: EQ
                            {
                            EQ26=(Token)match(input,EQ,FOLLOW_EQ_in_varDecl391);  
                            stream_EQ.add(EQ26);


                            }
                            break;
                        case 2 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:98:10: COLON_EQUAL
                            {
                            COLON_EQUAL27=(Token)match(input,COLON_EQUAL,FOLLOW_COLON_EQUAL_in_varDecl395);  
                            stream_COLON_EQUAL.add(COLON_EQUAL27);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_expression_in_varDecl398);
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
                    // 98:34: -> ^( VarDecl typeAndId ^( Expression expression ) )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:98:37: ^( VarDecl typeAndId ^( Expression expression ) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VarDecl, "VarDecl"), root_1);

                        adaptor.addChild(root_1, stream_typeAndId.nextTree());
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:98:57: ^( Expression expression )
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
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:99:5: 
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
                    // 99:5: -> ^( VarDecl typeAndId )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:99:8: ^( VarDecl typeAndId )
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

            SEMICOLON29=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_varDecl427);  
            stream_SEMICOLON.add(SEMICOLON29);


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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:102:1: entitySection : ENTITIES ( entityDecl )+ -> ( entityDecl )+ ;
    public final CalParser.entitySection_return entitySection() throws RecognitionException {
        CalParser.entitySection_return retval = new CalParser.entitySection_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ENTITIES30=null;
        CalParser.entityDecl_return entityDecl31 = null;


        Object ENTITIES30_tree=null;
        RewriteRuleTokenStream stream_ENTITIES=new RewriteRuleTokenStream(adaptor,"token ENTITIES");
        RewriteRuleSubtreeStream stream_entityDecl=new RewriteRuleSubtreeStream(adaptor,"rule entityDecl");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:102:14: ( ENTITIES ( entityDecl )+ -> ( entityDecl )+ )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:102:16: ENTITIES ( entityDecl )+
            {
            ENTITIES30=(Token)match(input,ENTITIES,FOLLOW_ENTITIES_in_entitySection435);  
            stream_ENTITIES.add(ENTITIES30);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:102:25: ( entityDecl )+
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
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:102:25: entityDecl
            	    {
            	    pushFollow(FOLLOW_entityDecl_in_entitySection437);
            	    entityDecl31=entityDecl();

            	    state._fsp--;

            	    stream_entityDecl.add(entityDecl31.getTree());

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
            // 102:37: -> ( entityDecl )+
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:104:1: entityDecl : ID EQ entityExpr SEMICOLON -> ^( EntityDecl ^( Var ID ) entityExpr ) ;
    public final CalParser.entityDecl_return entityDecl() throws RecognitionException {
        CalParser.entityDecl_return retval = new CalParser.entityDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID32=null;
        Token EQ33=null;
        Token SEMICOLON35=null;
        CalParser.entityExpr_return entityExpr34 = null;


        Object ID32_tree=null;
        Object EQ33_tree=null;
        Object SEMICOLON35_tree=null;
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleTokenStream stream_EQ=new RewriteRuleTokenStream(adaptor,"token EQ");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_entityExpr=new RewriteRuleSubtreeStream(adaptor,"rule entityExpr");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:104:11: ( ID EQ entityExpr SEMICOLON -> ^( EntityDecl ^( Var ID ) entityExpr ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:104:13: ID EQ entityExpr SEMICOLON
            {
            ID32=(Token)match(input,ID,FOLLOW_ID_in_entityDecl450);  
            stream_ID.add(ID32);

            EQ33=(Token)match(input,EQ,FOLLOW_EQ_in_entityDecl452);  
            stream_EQ.add(EQ33);

            pushFollow(FOLLOW_entityExpr_in_entityDecl454);
            entityExpr34=entityExpr();

            state._fsp--;

            stream_entityExpr.add(entityExpr34.getTree());
            SEMICOLON35=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_entityDecl456);  
            stream_SEMICOLON.add(SEMICOLON35);



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
            // 104:40: -> ^( EntityDecl ^( Var ID ) entityExpr )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:104:43: ^( EntityDecl ^( Var ID ) entityExpr )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EntityDecl, "EntityDecl"), root_1);

                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:104:56: ^( Var ID )
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:106:1: entityExpr : ID LPAREN ( entityPars )? RPAREN -> ^( EntityExpr ^( Var ID ) ( entityPars )? ) ;
    public final CalParser.entityExpr_return entityExpr() throws RecognitionException {
        CalParser.entityExpr_return retval = new CalParser.entityExpr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID36=null;
        Token LPAREN37=null;
        Token RPAREN39=null;
        CalParser.entityPars_return entityPars38 = null;


        Object ID36_tree=null;
        Object LPAREN37_tree=null;
        Object RPAREN39_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_entityPars=new RewriteRuleSubtreeStream(adaptor,"rule entityPars");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:106:11: ( ID LPAREN ( entityPars )? RPAREN -> ^( EntityExpr ^( Var ID ) ( entityPars )? ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:106:13: ID LPAREN ( entityPars )? RPAREN
            {
            ID36=(Token)match(input,ID,FOLLOW_ID_in_entityExpr477);  
            stream_ID.add(ID36);

            LPAREN37=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_entityExpr479);  
            stream_LPAREN.add(LPAREN37);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:106:23: ( entityPars )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==ID) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:106:23: entityPars
                    {
                    pushFollow(FOLLOW_entityPars_in_entityExpr481);
                    entityPars38=entityPars();

                    state._fsp--;

                    stream_entityPars.add(entityPars38.getTree());

                    }
                    break;

            }

            RPAREN39=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_entityExpr484);  
            stream_RPAREN.add(RPAREN39);



            // AST REWRITE
            // elements: entityPars, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 106:42: -> ^( EntityExpr ^( Var ID ) ( entityPars )? )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:106:45: ^( EntityExpr ^( Var ID ) ( entityPars )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EntityExpr, "EntityExpr"), root_1);

                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:106:58: ^( Var ID )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_2);

                adaptor.addChild(root_2, stream_ID.nextNode());

                adaptor.addChild(root_1, root_2);
                }
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:106:68: ( entityPars )?
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:108:1: entityPars : entityPar ( COMMA entityPar )* -> ( entityPar )+ ;
    public final CalParser.entityPars_return entityPars() throws RecognitionException {
        CalParser.entityPars_return retval = new CalParser.entityPars_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA41=null;
        CalParser.entityPar_return entityPar40 = null;

        CalParser.entityPar_return entityPar42 = null;


        Object COMMA41_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_entityPar=new RewriteRuleSubtreeStream(adaptor,"rule entityPar");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:108:11: ( entityPar ( COMMA entityPar )* -> ( entityPar )+ )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:108:13: entityPar ( COMMA entityPar )*
            {
            pushFollow(FOLLOW_entityPar_in_entityPars506);
            entityPar40=entityPar();

            state._fsp--;

            stream_entityPar.add(entityPar40.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:108:23: ( COMMA entityPar )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==COMMA) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:108:24: COMMA entityPar
            	    {
            	    COMMA41=(Token)match(input,COMMA,FOLLOW_COMMA_in_entityPars509);  
            	    stream_COMMA.add(COMMA41);

            	    pushFollow(FOLLOW_entityPar_in_entityPars511);
            	    entityPar42=entityPar();

            	    state._fsp--;

            	    stream_entityPar.add(entityPar42.getTree());

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
            // 108:42: -> ( entityPar )+
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:110:1: entityPar : ID EQ expression -> ^( EntityPar ^( Var ID ) ^( Expression expression ) ) ;
    public final CalParser.entityPar_return entityPar() throws RecognitionException {
        CalParser.entityPar_return retval = new CalParser.entityPar_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID43=null;
        Token EQ44=null;
        CalParser.expression_return expression45 = null;


        Object ID43_tree=null;
        Object EQ44_tree=null;
        RewriteRuleTokenStream stream_EQ=new RewriteRuleTokenStream(adaptor,"token EQ");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:110:10: ( ID EQ expression -> ^( EntityPar ^( Var ID ) ^( Expression expression ) ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:110:12: ID EQ expression
            {
            ID43=(Token)match(input,ID,FOLLOW_ID_in_entityPar525);  
            stream_ID.add(ID43);

            EQ44=(Token)match(input,EQ,FOLLOW_EQ_in_entityPar527);  
            stream_EQ.add(EQ44);

            pushFollow(FOLLOW_expression_in_entityPar529);
            expression45=expression();

            state._fsp--;

            stream_expression.add(expression45.getTree());


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
            // 110:29: -> ^( EntityPar ^( Var ID ) ^( Expression expression ) )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:110:32: ^( EntityPar ^( Var ID ) ^( Expression expression ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EntityPar, "EntityPar"), root_1);

                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:110:44: ^( Var ID )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_2);

                adaptor.addChild(root_2, stream_ID.nextNode());

                adaptor.addChild(root_1, root_2);
                }
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:110:54: ^( Expression expression )
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:113:1: structureSection : STRUCTURE ( structureStmt )+ -> ( structureStmt )+ ;
    public final CalParser.structureSection_return structureSection() throws RecognitionException {
        CalParser.structureSection_return retval = new CalParser.structureSection_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token STRUCTURE46=null;
        CalParser.structureStmt_return structureStmt47 = null;


        Object STRUCTURE46_tree=null;
        RewriteRuleTokenStream stream_STRUCTURE=new RewriteRuleTokenStream(adaptor,"token STRUCTURE");
        RewriteRuleSubtreeStream stream_structureStmt=new RewriteRuleSubtreeStream(adaptor,"rule structureStmt");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:113:17: ( STRUCTURE ( structureStmt )+ -> ( structureStmt )+ )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:113:19: STRUCTURE ( structureStmt )+
            {
            STRUCTURE46=(Token)match(input,STRUCTURE,FOLLOW_STRUCTURE_in_structureSection555);  
            stream_STRUCTURE.add(STRUCTURE46);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:113:29: ( structureStmt )+
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
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:113:29: structureStmt
            	    {
            	    pushFollow(FOLLOW_structureStmt_in_structureSection557);
            	    structureStmt47=structureStmt();

            	    state._fsp--;

            	    stream_structureStmt.add(structureStmt47.getTree());

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
            // 113:44: -> ( structureStmt )+
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:115:1: structureStmt : c1= connector DOUBLE_DASH_ARROW c2= connector ( attributeSection )? SEMICOLON -> ^( StructureStmt $c1 $c2) ;
    public final CalParser.structureStmt_return structureStmt() throws RecognitionException {
        CalParser.structureStmt_return retval = new CalParser.structureStmt_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DOUBLE_DASH_ARROW48=null;
        Token SEMICOLON50=null;
        CalParser.connector_return c1 = null;

        CalParser.connector_return c2 = null;

        CalParser.attributeSection_return attributeSection49 = null;


        Object DOUBLE_DASH_ARROW48_tree=null;
        Object SEMICOLON50_tree=null;
        RewriteRuleTokenStream stream_DOUBLE_DASH_ARROW=new RewriteRuleTokenStream(adaptor,"token DOUBLE_DASH_ARROW");
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleSubtreeStream stream_attributeSection=new RewriteRuleSubtreeStream(adaptor,"rule attributeSection");
        RewriteRuleSubtreeStream stream_connector=new RewriteRuleSubtreeStream(adaptor,"rule connector");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:115:14: (c1= connector DOUBLE_DASH_ARROW c2= connector ( attributeSection )? SEMICOLON -> ^( StructureStmt $c1 $c2) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:115:16: c1= connector DOUBLE_DASH_ARROW c2= connector ( attributeSection )? SEMICOLON
            {
            pushFollow(FOLLOW_connector_in_structureStmt572);
            c1=connector();

            state._fsp--;

            stream_connector.add(c1.getTree());
            DOUBLE_DASH_ARROW48=(Token)match(input,DOUBLE_DASH_ARROW,FOLLOW_DOUBLE_DASH_ARROW_in_structureStmt574);  
            stream_DOUBLE_DASH_ARROW.add(DOUBLE_DASH_ARROW48);

            pushFollow(FOLLOW_connector_in_structureStmt578);
            c2=connector();

            state._fsp--;

            stream_connector.add(c2.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:115:60: ( attributeSection )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==LBRACE) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:115:60: attributeSection
                    {
                    pushFollow(FOLLOW_attributeSection_in_structureStmt580);
                    attributeSection49=attributeSection();

                    state._fsp--;

                    stream_attributeSection.add(attributeSection49.getTree());

                    }
                    break;

            }

            SEMICOLON50=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_structureStmt583);  
            stream_SEMICOLON.add(SEMICOLON50);



            // AST REWRITE
            // elements: c2, c1
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
            // 115:88: -> ^( StructureStmt $c1 $c2)
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:116:3: ^( StructureStmt $c1 $c2)
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:118:1: connector : v1= ID ( DOT v2= ID -> ^( Connector ^( Var $v1) ^( Var $v2) ) | -> ^( Connector ^( Var $v1) ) ) ;
    public final CalParser.connector_return connector() throws RecognitionException {
        CalParser.connector_return retval = new CalParser.connector_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token v1=null;
        Token v2=null;
        Token DOT51=null;

        Object v1_tree=null;
        Object v2_tree=null;
        Object DOT51_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");

        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:118:10: (v1= ID ( DOT v2= ID -> ^( Connector ^( Var $v1) ^( Var $v2) ) | -> ^( Connector ^( Var $v1) ) ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:118:12: v1= ID ( DOT v2= ID -> ^( Connector ^( Var $v1) ^( Var $v2) ) | -> ^( Connector ^( Var $v1) ) )
            {
            v1=(Token)match(input,ID,FOLLOW_ID_in_connector607);  
            stream_ID.add(v1);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:118:18: ( DOT v2= ID -> ^( Connector ^( Var $v1) ^( Var $v2) ) | -> ^( Connector ^( Var $v1) ) )
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
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:119:3: DOT v2= ID
                    {
                    DOT51=(Token)match(input,DOT,FOLLOW_DOT_in_connector613);  
                    stream_DOT.add(DOT51);

                    v2=(Token)match(input,ID,FOLLOW_ID_in_connector617);  
                    stream_ID.add(v2);



                    // AST REWRITE
                    // elements: v2, v1
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
                    // 119:13: -> ^( Connector ^( Var $v1) ^( Var $v2) )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:119:16: ^( Connector ^( Var $v1) ^( Var $v2) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Connector, "Connector"), root_1);

                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:119:28: ^( Var $v1)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_2);

                        adaptor.addChild(root_2, stream_v1.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:119:39: ^( Var $v2)
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
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:120:5: 
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
                    // 120:5: -> ^( Connector ^( Var $v1) )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:120:8: ^( Connector ^( Var $v1) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Connector, "Connector"), root_1);

                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:120:20: ^( Var $v1)
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:122:1: attributeSection : LBRACE ( attributeDecl )* RBRACE ;
    public final CalParser.attributeSection_return attributeSection() throws RecognitionException {
        CalParser.attributeSection_return retval = new CalParser.attributeSection_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LBRACE52=null;
        Token RBRACE54=null;
        CalParser.attributeDecl_return attributeDecl53 = null;


        Object LBRACE52_tree=null;
        Object RBRACE54_tree=null;

        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:122:17: ( LBRACE ( attributeDecl )* RBRACE )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:122:19: LBRACE ( attributeDecl )* RBRACE
            {
            root_0 = (Object)adaptor.nil();

            LBRACE52=(Token)match(input,LBRACE,FOLLOW_LBRACE_in_attributeSection662); 
            LBRACE52_tree = (Object)adaptor.create(LBRACE52);
            adaptor.addChild(root_0, LBRACE52_tree);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:122:26: ( attributeDecl )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==ID) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:122:26: attributeDecl
            	    {
            	    pushFollow(FOLLOW_attributeDecl_in_attributeSection664);
            	    attributeDecl53=attributeDecl();

            	    state._fsp--;

            	    adaptor.addChild(root_0, attributeDecl53.getTree());

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            RBRACE54=(Token)match(input,RBRACE,FOLLOW_RBRACE_in_attributeSection667); 
            RBRACE54_tree = (Object)adaptor.create(RBRACE54);
            adaptor.addChild(root_0, RBRACE54_tree);


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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:124:1: attributeDecl : ID ( EQ expression SEMICOLON | COLON type SEMICOLON ) ;
    public final CalParser.attributeDecl_return attributeDecl() throws RecognitionException {
        CalParser.attributeDecl_return retval = new CalParser.attributeDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID55=null;
        Token EQ56=null;
        Token SEMICOLON58=null;
        Token COLON59=null;
        Token SEMICOLON61=null;
        CalParser.expression_return expression57 = null;

        CalParser.type_return type60 = null;


        Object ID55_tree=null;
        Object EQ56_tree=null;
        Object SEMICOLON58_tree=null;
        Object COLON59_tree=null;
        Object SEMICOLON61_tree=null;

        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:124:14: ( ID ( EQ expression SEMICOLON | COLON type SEMICOLON ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:124:16: ID ( EQ expression SEMICOLON | COLON type SEMICOLON )
            {
            root_0 = (Object)adaptor.nil();

            ID55=(Token)match(input,ID,FOLLOW_ID_in_attributeDecl675); 
            ID55_tree = (Object)adaptor.create(ID55);
            adaptor.addChild(root_0, ID55_tree);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:124:19: ( EQ expression SEMICOLON | COLON type SEMICOLON )
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
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:124:20: EQ expression SEMICOLON
                    {
                    EQ56=(Token)match(input,EQ,FOLLOW_EQ_in_attributeDecl678); 
                    EQ56_tree = (Object)adaptor.create(EQ56);
                    adaptor.addChild(root_0, EQ56_tree);

                    pushFollow(FOLLOW_expression_in_attributeDecl680);
                    expression57=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression57.getTree());
                    SEMICOLON58=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_attributeDecl682); 
                    SEMICOLON58_tree = (Object)adaptor.create(SEMICOLON58);
                    adaptor.addChild(root_0, SEMICOLON58_tree);


                    }
                    break;
                case 2 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:124:46: COLON type SEMICOLON
                    {
                    COLON59=(Token)match(input,COLON,FOLLOW_COLON_in_attributeDecl686); 
                    COLON59_tree = (Object)adaptor.create(COLON59);
                    adaptor.addChild(root_0, COLON59_tree);

                    pushFollow(FOLLOW_type_in_attributeDecl688);
                    type60=type();

                    state._fsp--;

                    adaptor.addChild(root_0, type60.getTree());
                    SEMICOLON61=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_attributeDecl690); 
                    SEMICOLON61_tree = (Object)adaptor.create(SEMICOLON61);
                    adaptor.addChild(root_0, SEMICOLON61_tree);


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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:129:1: actor : ( oneImport )* ACTOR ID ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN portSignature COLON ( . )* EOF -> ^( Actor ^( Name ID ) ( parameters )? portSignature ) ;
    public final CalParser.actor_return actor() throws RecognitionException {
        CalParser.actor_return retval = new CalParser.actor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ACTOR63=null;
        Token ID64=null;
        Token LBRACKET65=null;
        Token RBRACKET67=null;
        Token LPAREN68=null;
        Token RPAREN70=null;
        Token COLON72=null;
        Token wildcard73=null;
        Token EOF74=null;
        CalParser.oneImport_return oneImport62 = null;

        CalParser.typePars_return typePars66 = null;

        CalParser.parameters_return parameters69 = null;

        CalParser.portSignature_return portSignature71 = null;


        Object ACTOR63_tree=null;
        Object ID64_tree=null;
        Object LBRACKET65_tree=null;
        Object RBRACKET67_tree=null;
        Object LPAREN68_tree=null;
        Object RPAREN70_tree=null;
        Object COLON72_tree=null;
        Object wildcard73_tree=null;
        Object EOF74_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_RBRACKET=new RewriteRuleTokenStream(adaptor,"token RBRACKET");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleTokenStream stream_ACTOR=new RewriteRuleTokenStream(adaptor,"token ACTOR");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_typePars=new RewriteRuleSubtreeStream(adaptor,"rule typePars");
        RewriteRuleSubtreeStream stream_portSignature=new RewriteRuleSubtreeStream(adaptor,"rule portSignature");
        RewriteRuleSubtreeStream stream_parameters=new RewriteRuleSubtreeStream(adaptor,"rule parameters");
        RewriteRuleSubtreeStream stream_oneImport=new RewriteRuleSubtreeStream(adaptor,"rule oneImport");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:129:6: ( ( oneImport )* ACTOR ID ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN portSignature COLON ( . )* EOF -> ^( Actor ^( Name ID ) ( parameters )? portSignature ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:129:8: ( oneImport )* ACTOR ID ( LBRACKET ( typePars )? RBRACKET )? LPAREN ( parameters )? RPAREN portSignature COLON ( . )* EOF
            {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:129:8: ( oneImport )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==IMPORT) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:129:8: oneImport
            	    {
            	    pushFollow(FOLLOW_oneImport_in_actor702);
            	    oneImport62=oneImport();

            	    state._fsp--;

            	    stream_oneImport.add(oneImport62.getTree());

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            ACTOR63=(Token)match(input,ACTOR,FOLLOW_ACTOR_in_actor705);  
            stream_ACTOR.add(ACTOR63);

            ID64=(Token)match(input,ID,FOLLOW_ID_in_actor707);  
            stream_ID.add(ID64);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:130:3: ( LBRACKET ( typePars )? RBRACKET )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==LBRACKET) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:130:4: LBRACKET ( typePars )? RBRACKET
                    {
                    LBRACKET65=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_actor712);  
                    stream_LBRACKET.add(LBRACKET65);

                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:130:13: ( typePars )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==ID) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:130:13: typePars
                            {
                            pushFollow(FOLLOW_typePars_in_actor714);
                            typePars66=typePars();

                            state._fsp--;

                            stream_typePars.add(typePars66.getTree());

                            }
                            break;

                    }

                    RBRACKET67=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_actor717);  
                    stream_RBRACKET.add(RBRACKET67);


                    }
                    break;

            }

            LPAREN68=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_actor723);  
            stream_LPAREN.add(LPAREN68);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:131:10: ( parameters )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==ID) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:131:10: parameters
                    {
                    pushFollow(FOLLOW_parameters_in_actor725);
                    parameters69=parameters();

                    state._fsp--;

                    stream_parameters.add(parameters69.getTree());

                    }
                    break;

            }

            RPAREN70=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_actor728);  
            stream_RPAREN.add(RPAREN70);

            pushFollow(FOLLOW_portSignature_in_actor732);
            portSignature71=portSignature();

            state._fsp--;

            stream_portSignature.add(portSignature71.getTree());
            COLON72=(Token)match(input,COLON,FOLLOW_COLON_in_actor734);  
            stream_COLON.add(COLON72);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:132:23: ( . )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>=Connector && LA26_0<=SHARP)) ) {
                    alt26=1;
                }
                else if ( (LA26_0==EOF) ) {
                    alt26=2;
                }


                switch (alt26) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:132:23: .
            	    {
            	    wildcard73=(Token)input.LT(1);
            	    matchAny(input); 
            	    wildcard73_tree = (Object)adaptor.create(wildcard73);
            	    adaptor.addChild(root_0, wildcard73_tree);


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

            EOF74=(Token)match(input,EOF,FOLLOW_EOF_in_actor739);  
            stream_EOF.add(EOF74);



            // AST REWRITE
            // elements: parameters, ID, portSignature
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 132:30: -> ^( Actor ^( Name ID ) ( parameters )? portSignature )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:133:5: ^( Actor ^( Name ID ) ( parameters )? portSignature )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Actor, "Actor"), root_1);

                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:133:13: ^( Name ID )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Name, "Name"), root_2);

                adaptor.addChild(root_2, stream_ID.nextNode());

                adaptor.addChild(root_1, root_2);
                }
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:133:24: ( parameters )?
                if ( stream_parameters.hasNext() ) {
                    adaptor.addChild(root_1, stream_parameters.nextTree());

                }
                stream_parameters.reset();
                adaptor.addChild(root_1, stream_portSignature.nextTree());

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

    public static class oneImport_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "oneImport"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:138:1: oneImport : IMPORT importRest SEMICOLON ;
    public final CalParser.oneImport_return oneImport() throws RecognitionException {
        CalParser.oneImport_return retval = new CalParser.oneImport_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IMPORT75=null;
        Token SEMICOLON77=null;
        CalParser.importRest_return importRest76 = null;


        Object IMPORT75_tree=null;
        Object SEMICOLON77_tree=null;

        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:138:10: ( IMPORT importRest SEMICOLON )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:138:12: IMPORT importRest SEMICOLON
            {
            root_0 = (Object)adaptor.nil();

            IMPORT75=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_oneImport770); 
            IMPORT75_tree = (Object)adaptor.create(IMPORT75);
            adaptor.addChild(root_0, IMPORT75_tree);

            pushFollow(FOLLOW_importRest_in_oneImport772);
            importRest76=importRest();

            state._fsp--;

            adaptor.addChild(root_0, importRest76.getTree());
            SEMICOLON77=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_oneImport774); 
            SEMICOLON77_tree = (Object)adaptor.create(SEMICOLON77);
            adaptor.addChild(root_0, SEMICOLON77_tree);


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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:140:1: importRest : ( ALL qualifiedId | qualifiedId ( EQ ID )? );
    public final CalParser.importRest_return importRest() throws RecognitionException {
        CalParser.importRest_return retval = new CalParser.importRest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ALL78=null;
        Token EQ81=null;
        Token ID82=null;
        CalParser.qualifiedId_return qualifiedId79 = null;

        CalParser.qualifiedId_return qualifiedId80 = null;


        Object ALL78_tree=null;
        Object EQ81_tree=null;
        Object ID82_tree=null;

        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:140:11: ( ALL qualifiedId | qualifiedId ( EQ ID )? )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==ALL) ) {
                alt28=1;
            }
            else if ( (LA28_0==ID) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:140:13: ALL qualifiedId
                    {
                    root_0 = (Object)adaptor.nil();

                    ALL78=(Token)match(input,ALL,FOLLOW_ALL_in_importRest782); 
                    ALL78_tree = (Object)adaptor.create(ALL78);
                    adaptor.addChild(root_0, ALL78_tree);

                    pushFollow(FOLLOW_qualifiedId_in_importRest784);
                    qualifiedId79=qualifiedId();

                    state._fsp--;

                    adaptor.addChild(root_0, qualifiedId79.getTree());

                    }
                    break;
                case 2 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:141:5: qualifiedId ( EQ ID )?
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_qualifiedId_in_importRest790);
                    qualifiedId80=qualifiedId();

                    state._fsp--;

                    adaptor.addChild(root_0, qualifiedId80.getTree());
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:141:17: ( EQ ID )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==EQ) ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:141:18: EQ ID
                            {
                            EQ81=(Token)match(input,EQ,FOLLOW_EQ_in_importRest793); 
                            EQ81_tree = (Object)adaptor.create(EQ81);
                            adaptor.addChild(root_0, EQ81_tree);

                            ID82=(Token)match(input,ID,FOLLOW_ID_in_importRest795); 
                            ID82_tree = (Object)adaptor.create(ID82);
                            adaptor.addChild(root_0, ID82_tree);


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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:143:1: qualifiedId : ID ( DOT ID )* -> ^( QualifiedId ^( Var ID ) ( ^( Dot DOT ) ^( Var ID ) )* ) ;
    public final CalParser.qualifiedId_return qualifiedId() throws RecognitionException {
        CalParser.qualifiedId_return retval = new CalParser.qualifiedId_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID83=null;
        Token DOT84=null;
        Token ID85=null;

        Object ID83_tree=null;
        Object DOT84_tree=null;
        Object ID85_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");

        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:143:12: ( ID ( DOT ID )* -> ^( QualifiedId ^( Var ID ) ( ^( Dot DOT ) ^( Var ID ) )* ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:143:14: ID ( DOT ID )*
            {
            ID83=(Token)match(input,ID,FOLLOW_ID_in_qualifiedId805);  
            stream_ID.add(ID83);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:143:17: ( DOT ID )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==DOT) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:143:18: DOT ID
            	    {
            	    DOT84=(Token)match(input,DOT,FOLLOW_DOT_in_qualifiedId808);  
            	    stream_DOT.add(DOT84);

            	    ID85=(Token)match(input,ID,FOLLOW_ID_in_qualifiedId810);  
            	    stream_ID.add(ID85);


            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);



            // AST REWRITE
            // elements: ID, DOT, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 143:27: -> ^( QualifiedId ^( Var ID ) ( ^( Dot DOT ) ^( Var ID ) )* )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:143:30: ^( QualifiedId ^( Var ID ) ( ^( Dot DOT ) ^( Var ID ) )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(QualifiedId, "QualifiedId"), root_1);

                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:143:44: ^( Var ID )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_2);

                adaptor.addChild(root_2, stream_ID.nextNode());

                adaptor.addChild(root_1, root_2);
                }
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:143:54: ( ^( Dot DOT ) ^( Var ID ) )*
                while ( stream_DOT.hasNext()||stream_ID.hasNext() ) {
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:143:55: ^( Dot DOT )
                    {
                    Object root_2 = (Object)adaptor.nil();
                    root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Dot, "Dot"), root_2);

                    adaptor.addChild(root_2, stream_DOT.nextNode());

                    adaptor.addChild(root_1, root_2);
                    }
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:143:66: ^( Var ID )
                    {
                    Object root_2 = (Object)adaptor.nil();
                    root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_2);

                    adaptor.addChild(root_2, stream_ID.nextNode());

                    adaptor.addChild(root_1, root_2);
                    }

                }
                stream_DOT.reset();
                stream_ID.reset();

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
    // $ANTLR end "qualifiedId"

    public static class parameter_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parameter"
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:148:1: parameter : typeAndId ( EQ expression -> ^( Parameter typeAndId ^( Expression expression ) ) | -> ^( Parameter typeAndId ) ) ;
    public final CalParser.parameter_return parameter() throws RecognitionException {
        CalParser.parameter_return retval = new CalParser.parameter_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EQ87=null;
        CalParser.typeAndId_return typeAndId86 = null;

        CalParser.expression_return expression88 = null;


        Object EQ87_tree=null;
        RewriteRuleTokenStream stream_EQ=new RewriteRuleTokenStream(adaptor,"token EQ");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_typeAndId=new RewriteRuleSubtreeStream(adaptor,"rule typeAndId");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:148:10: ( typeAndId ( EQ expression -> ^( Parameter typeAndId ^( Expression expression ) ) | -> ^( Parameter typeAndId ) ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:148:12: typeAndId ( EQ expression -> ^( Parameter typeAndId ^( Expression expression ) ) | -> ^( Parameter typeAndId ) )
            {
            pushFollow(FOLLOW_typeAndId_in_parameter849);
            typeAndId86=typeAndId();

            state._fsp--;

            stream_typeAndId.add(typeAndId86.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:149:3: ( EQ expression -> ^( Parameter typeAndId ^( Expression expression ) ) | -> ^( Parameter typeAndId ) )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==EQ) ) {
                alt30=1;
            }
            else if ( (LA30_0==RPAREN||LA30_0==COMMA) ) {
                alt30=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:149:4: EQ expression
                    {
                    EQ87=(Token)match(input,EQ,FOLLOW_EQ_in_parameter854);  
                    stream_EQ.add(EQ87);

                    pushFollow(FOLLOW_expression_in_parameter856);
                    expression88=expression();

                    state._fsp--;

                    stream_expression.add(expression88.getTree());


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
                    // 149:18: -> ^( Parameter typeAndId ^( Expression expression ) )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:149:21: ^( Parameter typeAndId ^( Expression expression ) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Parameter, "Parameter"), root_1);

                        adaptor.addChild(root_1, stream_typeAndId.nextTree());
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:149:43: ^( Expression expression )
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
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:150:5: 
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
                    // 150:5: -> ^( Parameter typeAndId )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:150:8: ^( Parameter typeAndId )
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:152:1: parameters : parameter ( COMMA parameter )* -> ( parameter )+ ;
    public final CalParser.parameters_return parameters() throws RecognitionException {
        CalParser.parameters_return retval = new CalParser.parameters_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA90=null;
        CalParser.parameter_return parameter89 = null;

        CalParser.parameter_return parameter91 = null;


        Object COMMA90_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_parameter=new RewriteRuleSubtreeStream(adaptor,"rule parameter");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:152:11: ( parameter ( COMMA parameter )* -> ( parameter )+ )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:152:13: parameter ( COMMA parameter )*
            {
            pushFollow(FOLLOW_parameter_in_parameters890);
            parameter89=parameter();

            state._fsp--;

            stream_parameter.add(parameter89.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:152:23: ( COMMA parameter )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==COMMA) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:152:24: COMMA parameter
            	    {
            	    COMMA90=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameters893);  
            	    stream_COMMA.add(COMMA90);

            	    pushFollow(FOLLOW_parameter_in_parameters895);
            	    parameter91=parameter();

            	    state._fsp--;

            	    stream_parameter.add(parameter91.getTree());

            	    }
            	    break;

            	default :
            	    break loop31;
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
            // 152:42: -> ( parameter )+
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:157:1: portDecl : ( MULTI )? typeAndId -> ^( PortDecl typeAndId ) ;
    public final CalParser.portDecl_return portDecl() throws RecognitionException {
        CalParser.portDecl_return retval = new CalParser.portDecl_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MULTI92=null;
        CalParser.typeAndId_return typeAndId93 = null;


        Object MULTI92_tree=null;
        RewriteRuleTokenStream stream_MULTI=new RewriteRuleTokenStream(adaptor,"token MULTI");
        RewriteRuleSubtreeStream stream_typeAndId=new RewriteRuleSubtreeStream(adaptor,"rule typeAndId");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:157:9: ( ( MULTI )? typeAndId -> ^( PortDecl typeAndId ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:157:11: ( MULTI )? typeAndId
            {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:157:11: ( MULTI )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==MULTI) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:157:11: MULTI
                    {
                    MULTI92=(Token)match(input,MULTI,FOLLOW_MULTI_in_portDecl912);  
                    stream_MULTI.add(MULTI92);


                    }
                    break;

            }

            pushFollow(FOLLOW_typeAndId_in_portDecl915);
            typeAndId93=typeAndId();

            state._fsp--;

            stream_typeAndId.add(typeAndId93.getTree());


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
            // 157:28: -> ^( PortDecl typeAndId )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:157:31: ^( PortDecl typeAndId )
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:159:1: portDecls : portDecl ( COMMA portDecl )* -> ( portDecl )+ ;
    public final CalParser.portDecls_return portDecls() throws RecognitionException {
        CalParser.portDecls_return retval = new CalParser.portDecls_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA95=null;
        CalParser.portDecl_return portDecl94 = null;

        CalParser.portDecl_return portDecl96 = null;


        Object COMMA95_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_portDecl=new RewriteRuleSubtreeStream(adaptor,"rule portDecl");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:159:10: ( portDecl ( COMMA portDecl )* -> ( portDecl )+ )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:159:12: portDecl ( COMMA portDecl )*
            {
            pushFollow(FOLLOW_portDecl_in_portDecls930);
            portDecl94=portDecl();

            state._fsp--;

            stream_portDecl.add(portDecl94.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:159:21: ( COMMA portDecl )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==COMMA) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:159:22: COMMA portDecl
            	    {
            	    COMMA95=(Token)match(input,COMMA,FOLLOW_COMMA_in_portDecls933);  
            	    stream_COMMA.add(COMMA95);

            	    pushFollow(FOLLOW_portDecl_in_portDecls935);
            	    portDecl96=portDecl();

            	    state._fsp--;

            	    stream_portDecl.add(portDecl96.getTree());

            	    }
            	    break;

            	default :
            	    break loop33;
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
            // 159:39: -> ( portDecl )+
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:164:1: mainParameter : typeAndId EOF -> ^( Parameter typeAndId ) ;
    public final CalParser.mainParameter_return mainParameter() throws RecognitionException {
        CalParser.mainParameter_return retval = new CalParser.mainParameter_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF98=null;
        CalParser.typeAndId_return typeAndId97 = null;


        Object EOF98_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_typeAndId=new RewriteRuleSubtreeStream(adaptor,"rule typeAndId");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:164:14: ( typeAndId EOF -> ^( Parameter typeAndId ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:164:16: typeAndId EOF
            {
            pushFollow(FOLLOW_typeAndId_in_mainParameter953);
            typeAndId97=typeAndId();

            state._fsp--;

            stream_typeAndId.add(typeAndId97.getTree());
            EOF98=(Token)match(input,EOF,FOLLOW_EOF_in_mainParameter955);  
            stream_EOF.add(EOF98);



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
            // 164:30: -> ^( Parameter typeAndId )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:164:33: ^( Parameter typeAndId )
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:166:1: typeAndId : typeName= ID ( ( typeRest )? varName= ID -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName) | -> ^( Var $typeName) ) ;
    public final CalParser.typeAndId_return typeAndId() throws RecognitionException {
        CalParser.typeAndId_return retval = new CalParser.typeAndId_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token typeName=null;
        Token varName=null;
        CalParser.typeRest_return typeRest99 = null;


        Object typeName_tree=null;
        Object varName_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_typeRest=new RewriteRuleSubtreeStream(adaptor,"rule typeRest");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:166:10: (typeName= ID ( ( typeRest )? varName= ID -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName) | -> ^( Var $typeName) ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:166:12: typeName= ID ( ( typeRest )? varName= ID -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName) | -> ^( Var $typeName) )
            {
            typeName=(Token)match(input,ID,FOLLOW_ID_in_typeAndId972);  
            stream_ID.add(typeName);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:167:3: ( ( typeRest )? varName= ID -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName) | -> ^( Var $typeName) )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==LBRACKET||LA35_0==LPAREN||LA35_0==ID) ) {
                alt35=1;
            }
            else if ( (LA35_0==EOF||(LA35_0>=RPAREN && LA35_0<=COLON)||LA35_0==DOUBLE_EQUAL_ARROW||(LA35_0>=EQ && LA35_0<=SEMICOLON)||LA35_0==COMMA) ) {
                alt35=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:167:4: ( typeRest )? varName= ID
                    {
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:167:4: ( typeRest )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==LBRACKET||LA34_0==LPAREN) ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:167:4: typeRest
                            {
                            pushFollow(FOLLOW_typeRest_in_typeAndId977);
                            typeRest99=typeRest();

                            state._fsp--;

                            stream_typeRest.add(typeRest99.getTree());

                            }
                            break;

                    }

                    varName=(Token)match(input,ID,FOLLOW_ID_in_typeAndId982);  
                    stream_ID.add(varName);



                    // AST REWRITE
                    // elements: typeRest, varName, typeName
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
                    // 167:25: -> ^( Type ^( Var $typeName) ( typeRest )? ) ^( Var $varName)
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:167:28: ^( Type ^( Var $typeName) ( typeRest )? )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Type, "Type"), root_1);

                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:167:35: ^( Var $typeName)
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_2);

                        adaptor.addChild(root_2, stream_typeName.nextNode());

                        adaptor.addChild(root_1, root_2);
                        }
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:167:52: ( typeRest )?
                        if ( stream_typeRest.hasNext() ) {
                            adaptor.addChild(root_1, stream_typeRest.nextTree());

                        }
                        stream_typeRest.reset();

                        adaptor.addChild(root_0, root_1);
                        }
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:167:63: ^( Var $varName)
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
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:168:5: 
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
                    // 168:5: -> ^( Var $typeName)
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:168:8: ^( Var $typeName)
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:170:1: type : ID ( typeRest )? -> ^( Type ^( Var ID ) ( typeRest )? ) ;
    public final CalParser.type_return type() throws RecognitionException {
        CalParser.type_return retval = new CalParser.type_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID100=null;
        CalParser.typeRest_return typeRest101 = null;


        Object ID100_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_typeRest=new RewriteRuleSubtreeStream(adaptor,"rule typeRest");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:170:5: ( ID ( typeRest )? -> ^( Type ^( Var ID ) ( typeRest )? ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:170:7: ID ( typeRest )?
            {
            ID100=(Token)match(input,ID,FOLLOW_ID_in_type1026);  
            stream_ID.add(ID100);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:170:10: ( typeRest )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==LBRACKET||LA36_0==LPAREN) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:170:10: typeRest
                    {
                    pushFollow(FOLLOW_typeRest_in_type1028);
                    typeRest101=typeRest();

                    state._fsp--;

                    stream_typeRest.add(typeRest101.getTree());

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
            // 170:20: -> ^( Type ^( Var ID ) ( typeRest )? )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:170:23: ^( Type ^( Var ID ) ( typeRest )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(Type, "Type"), root_1);

                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:170:30: ^( Var ID )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(Var, "Var"), root_2);

                adaptor.addChild(root_2, stream_ID.nextNode());

                adaptor.addChild(root_1, root_2);
                }
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:170:40: ( typeRest )?
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:172:1: typeRest : ( LBRACKET ( typePars )? RBRACKET -> ( typePars )? | LPAREN ( typeAttrs )? RPAREN -> ( typeAttrs )? );
    public final CalParser.typeRest_return typeRest() throws RecognitionException {
        CalParser.typeRest_return retval = new CalParser.typeRest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LBRACKET102=null;
        Token RBRACKET104=null;
        Token LPAREN105=null;
        Token RPAREN107=null;
        CalParser.typePars_return typePars103 = null;

        CalParser.typeAttrs_return typeAttrs106 = null;


        Object LBRACKET102_tree=null;
        Object RBRACKET104_tree=null;
        Object LPAREN105_tree=null;
        Object RPAREN107_tree=null;
        RewriteRuleTokenStream stream_LBRACKET=new RewriteRuleTokenStream(adaptor,"token LBRACKET");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_RBRACKET=new RewriteRuleTokenStream(adaptor,"token RBRACKET");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_typePars=new RewriteRuleSubtreeStream(adaptor,"rule typePars");
        RewriteRuleSubtreeStream stream_typeAttrs=new RewriteRuleSubtreeStream(adaptor,"rule typeAttrs");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:172:9: ( LBRACKET ( typePars )? RBRACKET -> ( typePars )? | LPAREN ( typeAttrs )? RPAREN -> ( typeAttrs )? )
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==LBRACKET) ) {
                alt39=1;
            }
            else if ( (LA39_0==LPAREN) ) {
                alt39=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }
            switch (alt39) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:172:11: LBRACKET ( typePars )? RBRACKET
                    {
                    LBRACKET102=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_typeRest1051);  
                    stream_LBRACKET.add(LBRACKET102);

                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:172:20: ( typePars )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==ID) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:172:20: typePars
                            {
                            pushFollow(FOLLOW_typePars_in_typeRest1053);
                            typePars103=typePars();

                            state._fsp--;

                            stream_typePars.add(typePars103.getTree());

                            }
                            break;

                    }

                    RBRACKET104=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_typeRest1056);  
                    stream_RBRACKET.add(RBRACKET104);



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
                    // 172:39: -> ( typePars )?
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:172:42: ( typePars )?
                        if ( stream_typePars.hasNext() ) {
                            adaptor.addChild(root_0, stream_typePars.nextTree());

                        }
                        stream_typePars.reset();

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:173:5: LPAREN ( typeAttrs )? RPAREN
                    {
                    LPAREN105=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_typeRest1067);  
                    stream_LPAREN.add(LPAREN105);

                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:173:12: ( typeAttrs )?
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0==ID) ) {
                        alt38=1;
                    }
                    switch (alt38) {
                        case 1 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:173:12: typeAttrs
                            {
                            pushFollow(FOLLOW_typeAttrs_in_typeRest1069);
                            typeAttrs106=typeAttrs();

                            state._fsp--;

                            stream_typeAttrs.add(typeAttrs106.getTree());

                            }
                            break;

                    }

                    RPAREN107=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_typeRest1072);  
                    stream_RPAREN.add(RPAREN107);



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
                    // 173:30: -> ( typeAttrs )?
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:173:33: ( typeAttrs )?
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:175:1: typeAttrs : typeAttr ( COMMA typeAttr )* -> ( typeAttr )+ ;
    public final CalParser.typeAttrs_return typeAttrs() throws RecognitionException {
        CalParser.typeAttrs_return retval = new CalParser.typeAttrs_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA109=null;
        CalParser.typeAttr_return typeAttr108 = null;

        CalParser.typeAttr_return typeAttr110 = null;


        Object COMMA109_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_typeAttr=new RewriteRuleSubtreeStream(adaptor,"rule typeAttr");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:175:10: ( typeAttr ( COMMA typeAttr )* -> ( typeAttr )+ )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:175:12: typeAttr ( COMMA typeAttr )*
            {
            pushFollow(FOLLOW_typeAttr_in_typeAttrs1084);
            typeAttr108=typeAttr();

            state._fsp--;

            stream_typeAttr.add(typeAttr108.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:175:21: ( COMMA typeAttr )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==COMMA) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:175:22: COMMA typeAttr
            	    {
            	    COMMA109=(Token)match(input,COMMA,FOLLOW_COMMA_in_typeAttrs1087);  
            	    stream_COMMA.add(COMMA109);

            	    pushFollow(FOLLOW_typeAttr_in_typeAttrs1089);
            	    typeAttr110=typeAttr();

            	    state._fsp--;

            	    stream_typeAttr.add(typeAttr110.getTree());

            	    }
            	    break;

            	default :
            	    break loop40;
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
            // 175:39: -> ( typeAttr )+
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:177:1: typeAttr : ID typeAttrRest -> typeAttrRest ;
    public final CalParser.typeAttr_return typeAttr() throws RecognitionException {
        CalParser.typeAttr_return retval = new CalParser.typeAttr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID111=null;
        CalParser.typeAttrRest_return typeAttrRest112 = null;


        Object ID111_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_typeAttrRest=new RewriteRuleSubtreeStream(adaptor,"rule typeAttrRest");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:177:9: ( ID typeAttrRest -> typeAttrRest )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:177:11: ID typeAttrRest
            {
            ID111=(Token)match(input,ID,FOLLOW_ID_in_typeAttr1103);  
            stream_ID.add(ID111);

            pushFollow(FOLLOW_typeAttrRest_in_typeAttr1105);
            typeAttrRest112=typeAttrRest();

            state._fsp--;

            stream_typeAttrRest.add(typeAttrRest112.getTree());


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
            // 177:27: -> typeAttrRest
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:179:1: typeAttrRest : ( COLON type -> ^( TypeAttr type ) | EQ expression -> ^( ExprAttr ^( Expression expression ) ) );
    public final CalParser.typeAttrRest_return typeAttrRest() throws RecognitionException {
        CalParser.typeAttrRest_return retval = new CalParser.typeAttrRest_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON113=null;
        Token EQ115=null;
        CalParser.type_return type114 = null;

        CalParser.expression_return expression116 = null;


        Object COLON113_tree=null;
        Object EQ115_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_EQ=new RewriteRuleTokenStream(adaptor,"token EQ");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:179:13: ( COLON type -> ^( TypeAttr type ) | EQ expression -> ^( ExprAttr ^( Expression expression ) ) )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==COLON) ) {
                alt41=1;
            }
            else if ( (LA41_0==EQ) ) {
                alt41=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:179:15: COLON type
                    {
                    COLON113=(Token)match(input,COLON,FOLLOW_COLON_in_typeAttrRest1116);  
                    stream_COLON.add(COLON113);

                    pushFollow(FOLLOW_type_in_typeAttrRest1118);
                    type114=type();

                    state._fsp--;

                    stream_type.add(type114.getTree());


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
                    // 179:26: -> ^( TypeAttr type )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:179:29: ^( TypeAttr type )
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
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:180:3: EQ expression
                    {
                    EQ115=(Token)match(input,EQ,FOLLOW_EQ_in_typeAttrRest1130);  
                    stream_EQ.add(EQ115);

                    pushFollow(FOLLOW_expression_in_typeAttrRest1132);
                    expression116=expression();

                    state._fsp--;

                    stream_expression.add(expression116.getTree());


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
                    // 180:17: -> ^( ExprAttr ^( Expression expression ) )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:180:20: ^( ExprAttr ^( Expression expression ) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ExprAttr, "ExprAttr"), root_1);

                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:180:31: ^( Expression expression )
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:182:1: typePars : typePar ( COMMA typePar )* -> ( typePar )+ ;
    public final CalParser.typePars_return typePars() throws RecognitionException {
        CalParser.typePars_return retval = new CalParser.typePars_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA118=null;
        CalParser.typePar_return typePar117 = null;

        CalParser.typePar_return typePar119 = null;


        Object COMMA118_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_typePar=new RewriteRuleSubtreeStream(adaptor,"rule typePar");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:182:9: ( typePar ( COMMA typePar )* -> ( typePar )+ )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:182:11: typePar ( COMMA typePar )*
            {
            pushFollow(FOLLOW_typePar_in_typePars1151);
            typePar117=typePar();

            state._fsp--;

            stream_typePar.add(typePar117.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:182:19: ( COMMA typePar )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==COMMA) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:182:20: COMMA typePar
            	    {
            	    COMMA118=(Token)match(input,COMMA,FOLLOW_COMMA_in_typePars1154);  
            	    stream_COMMA.add(COMMA118);

            	    pushFollow(FOLLOW_typePar_in_typePars1156);
            	    typePar119=typePar();

            	    state._fsp--;

            	    stream_typePar.add(typePar119.getTree());

            	    }
            	    break;

            	default :
            	    break loop42;
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
            // 182:36: -> ( typePar )+
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:184:1: typePar : ID ( LT type )? -> ^( TypePar ID ( type )? ) ;
    public final CalParser.typePar_return typePar() throws RecognitionException {
        CalParser.typePar_return retval = new CalParser.typePar_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID120=null;
        Token LT121=null;
        CalParser.type_return type122 = null;


        Object ID120_tree=null;
        Object LT121_tree=null;
        RewriteRuleTokenStream stream_LT=new RewriteRuleTokenStream(adaptor,"token LT");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:184:8: ( ID ( LT type )? -> ^( TypePar ID ( type )? ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:184:10: ID ( LT type )?
            {
            ID120=(Token)match(input,ID,FOLLOW_ID_in_typePar1170);  
            stream_ID.add(ID120);

            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:184:13: ( LT type )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==LT) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:184:14: LT type
                    {
                    LT121=(Token)match(input,LT,FOLLOW_LT_in_typePar1173);  
                    stream_LT.add(LT121);

                    pushFollow(FOLLOW_type_in_typePar1175);
                    type122=type();

                    state._fsp--;

                    stream_type.add(type122.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: type, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 184:24: -> ^( TypePar ID ( type )? )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:184:27: ^( TypePar ID ( type )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TypePar, "TypePar"), root_1);

                adaptor.addChild(root_1, stream_ID.nextNode());
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:184:40: ( type )?
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:189:1: mainExpression : expression EOF -> ^( Expression expression ) ;
    public final CalParser.mainExpression_return mainExpression() throws RecognitionException {
        CalParser.mainExpression_return retval = new CalParser.mainExpression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF124=null;
        CalParser.expression_return expression123 = null;


        Object EOF124_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:189:15: ( expression EOF -> ^( Expression expression ) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:189:17: expression EOF
            {
            pushFollow(FOLLOW_expression_in_mainExpression1198);
            expression123=expression();

            state._fsp--;

            stream_expression.add(expression123.getTree());
            EOF124=(Token)match(input,EOF,FOLLOW_EOF_in_mainExpression1200);  
            stream_EOF.add(EOF124);



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
            // 189:32: -> ^( Expression expression )
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:189:35: ^( Expression expression )
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:191:1: expression : factor ( binop factor )* ;
    public final CalParser.expression_return expression() throws RecognitionException {
        CalParser.expression_return retval = new CalParser.expression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CalParser.factor_return factor125 = null;

        CalParser.binop_return binop126 = null;

        CalParser.factor_return factor127 = null;



        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:191:11: ( factor ( binop factor )* )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:191:13: factor ( binop factor )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_factor_in_expression1215);
            factor125=factor();

            state._fsp--;

            adaptor.addChild(root_0, factor125.getTree());
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:191:20: ( binop factor )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==MINUS||(LA44_0>=PLUS && LA44_0<=CARET)) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:191:21: binop factor
            	    {
            	    pushFollow(FOLLOW_binop_in_expression1218);
            	    binop126=binop();

            	    state._fsp--;

            	    adaptor.addChild(root_0, binop126.getTree());
            	    pushFollow(FOLLOW_factor_in_expression1220);
            	    factor127=factor();

            	    state._fsp--;

            	    adaptor.addChild(root_0, factor127.getTree());

            	    }
            	    break;

            	default :
            	    break loop44;
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:193:1: unop : (op= MINUS | op= NOT ) -> ^( UnOp $op) ;
    public final CalParser.unop_return unop() throws RecognitionException {
        CalParser.unop_return retval = new CalParser.unop_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token op=null;

        Object op_tree=null;
        RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
        RewriteRuleTokenStream stream_MINUS=new RewriteRuleTokenStream(adaptor,"token MINUS");

        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:193:5: ( (op= MINUS | op= NOT ) -> ^( UnOp $op) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:193:7: (op= MINUS | op= NOT )
            {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:193:7: (op= MINUS | op= NOT )
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==MINUS) ) {
                alt45=1;
            }
            else if ( (LA45_0==NOT) ) {
                alt45=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;
            }
            switch (alt45) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:193:8: op= MINUS
                    {
                    op=(Token)match(input,MINUS,FOLLOW_MINUS_in_unop1232);  
                    stream_MINUS.add(op);


                    }
                    break;
                case 2 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:193:19: op= NOT
                    {
                    op=(Token)match(input,NOT,FOLLOW_NOT_in_unop1238);  
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
            // 193:27: -> ^( UnOp $op)
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:193:30: ^( UnOp $op)
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:195:1: binop : (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET ) -> ^( BinOp $op) ;
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:195:6: ( (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET ) -> ^( BinOp $op) )
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:195:8: (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET )
            {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:195:8: (op= PLUS | op= MINUS | op= TIMES | op= DIV | op= CARET )
            int alt46=5;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt46=1;
                }
                break;
            case MINUS:
                {
                alt46=2;
                }
                break;
            case TIMES:
                {
                alt46=3;
                }
                break;
            case DIV:
                {
                alt46=4;
                }
                break;
            case CARET:
                {
                alt46=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }

            switch (alt46) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:195:9: op= PLUS
                    {
                    op=(Token)match(input,PLUS,FOLLOW_PLUS_in_binop1258);  
                    stream_PLUS.add(op);


                    }
                    break;
                case 2 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:195:19: op= MINUS
                    {
                    op=(Token)match(input,MINUS,FOLLOW_MINUS_in_binop1264);  
                    stream_MINUS.add(op);


                    }
                    break;
                case 3 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:195:30: op= TIMES
                    {
                    op=(Token)match(input,TIMES,FOLLOW_TIMES_in_binop1270);  
                    stream_TIMES.add(op);


                    }
                    break;
                case 4 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:195:41: op= DIV
                    {
                    op=(Token)match(input,DIV,FOLLOW_DIV_in_binop1276);  
                    stream_DIV.add(op);


                    }
                    break;
                case 5 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:195:50: op= CARET
                    {
                    op=(Token)match(input,CARET,FOLLOW_CARET_in_binop1282);  
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
            // 195:60: -> ^( BinOp $op)
            {
                // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:195:63: ^( BinOp $op)
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:197:1: factor : ( term | unop term -> ^( Expression unop term ) );
    public final CalParser.factor_return factor() throws RecognitionException {
        CalParser.factor_return retval = new CalParser.factor_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CalParser.term_return term128 = null;

        CalParser.unop_return unop129 = null;

        CalParser.term_return term130 = null;


        RewriteRuleSubtreeStream stream_unop=new RewriteRuleSubtreeStream(adaptor,"rule unop");
        RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:197:7: ( term | unop term -> ^( Expression unop term ) )
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==LBRACKET||LA47_0==LPAREN||LA47_0==ID||(LA47_0>=FLOAT && LA47_0<=FALSE)) ) {
                alt47=1;
            }
            else if ( ((LA47_0>=MINUS && LA47_0<=NOT)) ) {
                alt47=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;
            }
            switch (alt47) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:197:9: term
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_term_in_factor1299);
                    term128=term();

                    state._fsp--;

                    adaptor.addChild(root_0, term128.getTree());

                    }
                    break;
                case 2 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:198:3: unop term
                    {
                    pushFollow(FOLLOW_unop_in_factor1303);
                    unop129=unop();

                    state._fsp--;

                    stream_unop.add(unop129.getTree());
                    pushFollow(FOLLOW_term_in_factor1305);
                    term130=term();

                    state._fsp--;

                    stream_term.add(term130.getTree());


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
                    // 198:13: -> ^( Expression unop term )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:198:16: ^( Expression unop term )
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:200:1: term : ( atom | LPAREN expression RPAREN -> ^( Expression expression ) );
    public final CalParser.term_return term() throws RecognitionException {
        CalParser.term_return retval = new CalParser.term_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LPAREN132=null;
        Token RPAREN134=null;
        CalParser.atom_return atom131 = null;

        CalParser.expression_return expression133 = null;


        Object LPAREN132_tree=null;
        Object RPAREN134_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:200:5: ( atom | LPAREN expression RPAREN -> ^( Expression expression ) )
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==LBRACKET||LA48_0==ID||(LA48_0>=FLOAT && LA48_0<=FALSE)) ) {
                alt48=1;
            }
            else if ( (LA48_0==LPAREN) ) {
                alt48=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;
            }
            switch (alt48) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:200:7: atom
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_atom_in_term1322);
                    atom131=atom();

                    state._fsp--;

                    adaptor.addChild(root_0, atom131.getTree());

                    }
                    break;
                case 2 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:201:5: LPAREN expression RPAREN
                    {
                    LPAREN132=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_term1328);  
                    stream_LPAREN.add(LPAREN132);

                    pushFollow(FOLLOW_expression_in_term1330);
                    expression133=expression();

                    state._fsp--;

                    stream_expression.add(expression133.getTree());
                    RPAREN134=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_term1332);  
                    stream_RPAREN.add(RPAREN134);



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
                    // 201:30: -> ^( Expression expression )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:201:33: ^( Expression expression )
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
    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:203:1: atom : ( ID -> ^( Var ID ) | FLOAT -> ^( Real FLOAT ) | INTEGER -> ^( Integer INTEGER ) | STRING -> ^( String STRING ) | TRUE -> ^( Boolean TRUE ) | FALSE -> ^( Boolean FALSE ) | LBRACKET ( expression ( COMMA expression )* )? RBRACKET -> ^( List ( expression )* ) );
    public final CalParser.atom_return atom() throws RecognitionException {
        CalParser.atom_return retval = new CalParser.atom_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID135=null;
        Token FLOAT136=null;
        Token INTEGER137=null;
        Token STRING138=null;
        Token TRUE139=null;
        Token FALSE140=null;
        Token LBRACKET141=null;
        Token COMMA143=null;
        Token RBRACKET145=null;
        CalParser.expression_return expression142 = null;

        CalParser.expression_return expression144 = null;


        Object ID135_tree=null;
        Object FLOAT136_tree=null;
        Object INTEGER137_tree=null;
        Object STRING138_tree=null;
        Object TRUE139_tree=null;
        Object FALSE140_tree=null;
        Object LBRACKET141_tree=null;
        Object COMMA143_tree=null;
        Object RBRACKET145_tree=null;
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
            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:203:5: ( ID -> ^( Var ID ) | FLOAT -> ^( Real FLOAT ) | INTEGER -> ^( Integer INTEGER ) | STRING -> ^( String STRING ) | TRUE -> ^( Boolean TRUE ) | FALSE -> ^( Boolean FALSE ) | LBRACKET ( expression ( COMMA expression )* )? RBRACKET -> ^( List ( expression )* ) )
            int alt51=7;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt51=1;
                }
                break;
            case FLOAT:
                {
                alt51=2;
                }
                break;
            case INTEGER:
                {
                alt51=3;
                }
                break;
            case STRING:
                {
                alt51=4;
                }
                break;
            case TRUE:
                {
                alt51=5;
                }
                break;
            case FALSE:
                {
                alt51=6;
                }
                break;
            case LBRACKET:
                {
                alt51=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;
            }

            switch (alt51) {
                case 1 :
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:203:7: ID
                    {
                    ID135=(Token)match(input,ID,FOLLOW_ID_in_atom1347);  
                    stream_ID.add(ID135);



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
                    // 203:10: -> ^( Var ID )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:203:13: ^( Var ID )
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
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:204:3: FLOAT
                    {
                    FLOAT136=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_atom1359);  
                    stream_FLOAT.add(FLOAT136);



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
                    // 204:9: -> ^( Real FLOAT )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:204:12: ^( Real FLOAT )
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
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:205:3: INTEGER
                    {
                    INTEGER137=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_atom1371);  
                    stream_INTEGER.add(INTEGER137);



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
                    // 205:11: -> ^( Integer INTEGER )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:205:14: ^( Integer INTEGER )
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
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:206:3: STRING
                    {
                    STRING138=(Token)match(input,STRING,FOLLOW_STRING_in_atom1383);  
                    stream_STRING.add(STRING138);



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
                    // 206:10: -> ^( String STRING )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:206:13: ^( String STRING )
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
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:207:3: TRUE
                    {
                    TRUE139=(Token)match(input,TRUE,FOLLOW_TRUE_in_atom1395);  
                    stream_TRUE.add(TRUE139);



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
                    // 207:8: -> ^( Boolean TRUE )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:207:11: ^( Boolean TRUE )
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
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:208:3: FALSE
                    {
                    FALSE140=(Token)match(input,FALSE,FOLLOW_FALSE_in_atom1407);  
                    stream_FALSE.add(FALSE140);



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
                    // 208:9: -> ^( Boolean FALSE )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:208:12: ^( Boolean FALSE )
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
                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:209:3: LBRACKET ( expression ( COMMA expression )* )? RBRACKET
                    {
                    LBRACKET141=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_atom1419);  
                    stream_LBRACKET.add(LBRACKET141);

                    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:209:12: ( expression ( COMMA expression )* )?
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( (LA50_0==LBRACKET||LA50_0==LPAREN||LA50_0==ID||(LA50_0>=MINUS && LA50_0<=NOT)||(LA50_0>=FLOAT && LA50_0<=FALSE)) ) {
                        alt50=1;
                    }
                    switch (alt50) {
                        case 1 :
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:209:13: expression ( COMMA expression )*
                            {
                            pushFollow(FOLLOW_expression_in_atom1422);
                            expression142=expression();

                            state._fsp--;

                            stream_expression.add(expression142.getTree());
                            // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:209:24: ( COMMA expression )*
                            loop49:
                            do {
                                int alt49=2;
                                int LA49_0 = input.LA(1);

                                if ( (LA49_0==COMMA) ) {
                                    alt49=1;
                                }


                                switch (alt49) {
                            	case 1 :
                            	    // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:209:25: COMMA expression
                            	    {
                            	    COMMA143=(Token)match(input,COMMA,FOLLOW_COMMA_in_atom1425);  
                            	    stream_COMMA.add(COMMA143);

                            	    pushFollow(FOLLOW_expression_in_atom1427);
                            	    expression144=expression();

                            	    state._fsp--;

                            	    stream_expression.add(expression144.getTree());

                            	    }
                            	    break;

                            	default :
                            	    break loop49;
                                }
                            } while (true);


                            }
                            break;

                    }

                    RBRACKET145=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_atom1433);  
                    stream_RBRACKET.add(RBRACKET145);



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
                    // 209:55: -> ^( List ( expression )* )
                    {
                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:209:58: ^( List ( expression )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(List, "List"), root_1);

                        // D:\\Prog\\repositories\\graphiti-editor\\configuration\\src\\Cal.g:209:65: ( expression )*
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


 

    public static final BitSet FOLLOW_NETWORK_in_network211 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_qualifiedId_in_network213 = new BitSet(new long[]{0x0000005000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_network216 = new BitSet(new long[]{0x0002002000000000L});
    public static final BitSet FOLLOW_typePars_in_network218 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_network221 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LPAREN_in_network227 = new BitSet(new long[]{0x0002008000000000L});
    public static final BitSet FOLLOW_parameters_in_network229 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_RPAREN_in_network232 = new BitSet(new long[]{0x0802040000000000L});
    public static final BitSet FOLLOW_portSignature_in_network236 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_COLON_in_network238 = new BitSet(new long[]{0x02090A0000000000L});
    public static final BitSet FOLLOW_oneImport_in_network242 = new BitSet(new long[]{0x02090A0000000000L});
    public static final BitSet FOLLOW_varDeclSection_in_network245 = new BitSet(new long[]{0x0009020000000000L});
    public static final BitSet FOLLOW_entitySection_in_network250 = new BitSet(new long[]{0x0008020000000000L});
    public static final BitSet FOLLOW_structureSection_in_network253 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_END_in_network258 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_network260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_inputPorts_in_portSignature299 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_DOUBLE_EQUAL_ARROW_in_portSignature301 = new BitSet(new long[]{0x0802000000000000L});
    public static final BitSet FOLLOW_outputPorts_in_portSignature303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_portDecls_in_inputPorts316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_portDecls_in_outputPorts341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_varDeclSection367 = new BitSet(new long[]{0x0002100000000000L});
    public static final BitSet FOLLOW_varDecl_in_varDeclSection369 = new BitSet(new long[]{0x0002100000000002L});
    public static final BitSet FOLLOW_MUTABLE_in_varDecl382 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_typeAndId_in_varDecl385 = new BitSet(new long[]{0x0000E00000000000L});
    public static final BitSet FOLLOW_EQ_in_varDecl391 = new BitSet(new long[]{0x6002005000000000L,0x00000000000000F8L});
    public static final BitSet FOLLOW_COLON_EQUAL_in_varDecl395 = new BitSet(new long[]{0x6002005000000000L,0x00000000000000F8L});
    public static final BitSet FOLLOW_expression_in_varDecl398 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_varDecl427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ENTITIES_in_entitySection435 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_entityDecl_in_entitySection437 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_ID_in_entityDecl450 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_EQ_in_entityDecl452 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_entityExpr_in_entityDecl454 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_entityDecl456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_entityExpr477 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LPAREN_in_entityExpr479 = new BitSet(new long[]{0x0002008000000000L});
    public static final BitSet FOLLOW_entityPars_in_entityExpr481 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_RPAREN_in_entityExpr484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entityPar_in_entityPars506 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_COMMA_in_entityPars509 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_entityPar_in_entityPars511 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_ID_in_entityPar525 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_EQ_in_entityPar527 = new BitSet(new long[]{0x6002005000000000L,0x00000000000000F8L});
    public static final BitSet FOLLOW_expression_in_entityPar529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRUCTURE_in_structureSection555 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_structureStmt_in_structureSection557 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_connector_in_structureStmt572 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_DOUBLE_DASH_ARROW_in_structureStmt574 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_connector_in_structureStmt578 = new BitSet(new long[]{0x0040800000000000L});
    public static final BitSet FOLLOW_attributeSection_in_structureStmt580 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_structureStmt583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_connector607 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_DOT_in_connector613 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ID_in_connector617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_attributeSection662 = new BitSet(new long[]{0x0082000000000000L});
    public static final BitSet FOLLOW_attributeDecl_in_attributeSection664 = new BitSet(new long[]{0x0082000000000000L});
    public static final BitSet FOLLOW_RBRACE_in_attributeSection667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_attributeDecl675 = new BitSet(new long[]{0x0000210000000000L});
    public static final BitSet FOLLOW_EQ_in_attributeDecl678 = new BitSet(new long[]{0x6002005000000000L,0x00000000000000F8L});
    public static final BitSet FOLLOW_expression_in_attributeDecl680 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_attributeDecl682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_attributeDecl686 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_type_in_attributeDecl688 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_attributeDecl690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_oneImport_in_actor702 = new BitSet(new long[]{0x0300000000000000L});
    public static final BitSet FOLLOW_ACTOR_in_actor705 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ID_in_actor707 = new BitSet(new long[]{0x0000005000000000L});
    public static final BitSet FOLLOW_LBRACKET_in_actor712 = new BitSet(new long[]{0x0002002000000000L});
    public static final BitSet FOLLOW_typePars_in_actor714 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_actor717 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_LPAREN_in_actor723 = new BitSet(new long[]{0x0002008000000000L});
    public static final BitSet FOLLOW_parameters_in_actor725 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_RPAREN_in_actor728 = new BitSet(new long[]{0x0802040000000000L});
    public static final BitSet FOLLOW_portSignature_in_actor732 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_COLON_in_actor734 = new BitSet(new long[]{0xFFFFFFFFFFFFFFF0L,0x000000000007FFFFL});
    public static final BitSet FOLLOW_EOF_in_actor739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_oneImport770 = new BitSet(new long[]{0x0402000000000000L});
    public static final BitSet FOLLOW_importRest_in_oneImport772 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_SEMICOLON_in_oneImport774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALL_in_importRest782 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_qualifiedId_in_importRest784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualifiedId_in_importRest790 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_EQ_in_importRest793 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ID_in_importRest795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_qualifiedId805 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_DOT_in_qualifiedId808 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ID_in_qualifiedId810 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_typeAndId_in_parameter849 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_EQ_in_parameter854 = new BitSet(new long[]{0x6002005000000000L,0x00000000000000F8L});
    public static final BitSet FOLLOW_expression_in_parameter856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parameter_in_parameters890 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_COMMA_in_parameters893 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_parameter_in_parameters895 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_MULTI_in_portDecl912 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_typeAndId_in_portDecl915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_portDecl_in_portDecls930 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_COMMA_in_portDecls933 = new BitSet(new long[]{0x0802000000000000L});
    public static final BitSet FOLLOW_portDecl_in_portDecls935 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_typeAndId_in_mainParameter953 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_mainParameter955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_typeAndId972 = new BitSet(new long[]{0x0002005000000002L});
    public static final BitSet FOLLOW_typeRest_in_typeAndId977 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_ID_in_typeAndId982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_type1026 = new BitSet(new long[]{0x0000005000000002L});
    public static final BitSet FOLLOW_typeRest_in_type1028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_typeRest1051 = new BitSet(new long[]{0x0002002000000000L});
    public static final BitSet FOLLOW_typePars_in_typeRest1053 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_typeRest1056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_typeRest1067 = new BitSet(new long[]{0x0002008000000000L});
    public static final BitSet FOLLOW_typeAttrs_in_typeRest1069 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_RPAREN_in_typeRest1072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeAttr_in_typeAttrs1084 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_COMMA_in_typeAttrs1087 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_typeAttr_in_typeAttrs1089 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_ID_in_typeAttr1103 = new BitSet(new long[]{0x0000210000000000L});
    public static final BitSet FOLLOW_typeAttrRest_in_typeAttr1105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_typeAttrRest1116 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_type_in_typeAttrRest1118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_typeAttrRest1130 = new BitSet(new long[]{0x6002005000000000L,0x00000000000000F8L});
    public static final BitSet FOLLOW_expression_in_typeAttrRest1132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typePar_in_typePars1151 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_COMMA_in_typePars1154 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_typePar_in_typePars1156 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_ID_in_typePar1170 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_LT_in_typePar1173 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_type_in_typePar1175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_mainExpression1198 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_mainExpression1200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_factor_in_expression1215 = new BitSet(new long[]{0xA000000000000002L,0x0000000000000007L});
    public static final BitSet FOLLOW_binop_in_expression1218 = new BitSet(new long[]{0x6002005000000000L,0x00000000000000F8L});
    public static final BitSet FOLLOW_factor_in_expression1220 = new BitSet(new long[]{0xA000000000000002L,0x0000000000000007L});
    public static final BitSet FOLLOW_MINUS_in_unop1232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_unop1238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_binop1258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_binop1264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TIMES_in_binop1270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIV_in_binop1276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CARET_in_binop1282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_factor1299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unop_in_factor1303 = new BitSet(new long[]{0x0002005000000000L,0x00000000000000F8L});
    public static final BitSet FOLLOW_term_in_factor1305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_term1322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_term1328 = new BitSet(new long[]{0x6002005000000000L,0x00000000000000F8L});
    public static final BitSet FOLLOW_expression_in_term1330 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_RPAREN_in_term1332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_atom1347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_atom1359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_atom1371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom1383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_atom1395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_atom1407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_atom1419 = new BitSet(new long[]{0x6002007000000000L,0x00000000000000F8L});
    public static final BitSet FOLLOW_expression_in_atom1422 = new BitSet(new long[]{0x0004002000000000L});
    public static final BitSet FOLLOW_COMMA_in_atom1425 = new BitSet(new long[]{0x6002005000000000L,0x00000000000000F8L});
    public static final BitSet FOLLOW_expression_in_atom1427 = new BitSet(new long[]{0x0004002000000000L});
    public static final BitSet FOLLOW_RBRACKET_in_atom1433 = new BitSet(new long[]{0x0000000000000002L});

}