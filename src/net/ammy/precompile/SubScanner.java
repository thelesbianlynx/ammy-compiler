package net.ammy.precompile;

// import java.util.ArrayList;
// import java.util.List;
// import net.sw.lynx.ir.DatatypeComponent;
// import net.sw.lynx.ir.DatatypeComponent.*;
// import net.sw.lynx.ir.ExpressionComponent;
// import net.sw.lynx.ir.ExpressionComponent.*;
// import net.sw.lynx.ir.IteratorComponent;
// import net.sw.lynx.ir.IteratorComponent.*;
// import net.sw.lynx.ir.LValueComponent;
// import net.sw.lynx.ir.LValueComponent.*;
// import net.sw.lynx.ir.Operators;
// import net.sw.lynx.ir.PatternComponent;
// import net.sw.lynx.ir.PatternComponent.*;
// import net.sw.lynx.ir.ProgramComponent;
// import net.sw.lynx.ir.ProgramComponent.*;
// import net.sw.lynx.lang.MetaTable;
// import static net.sw.lynx.precompile.SubSymbol.*;

/**
 * Scans a sublynx program. Parses a sublynx token sequence into a sublynx tree
 *
 */
public class SubScanner {
    //
    // /* Common */
    //
    // // Token List.
    // private List <String> substrings;
    //
    // // Index into token list.
    // private int index;
    //
    // // Current Token.
    // private String current;
    //
    // // Constructor.
    // public SubScanner (List <String> _substrings) {
    //     substrings = _substrings;
    //     index = 0;
    //     current = substrings.size() > 0 ? substrings.get(0) : null;
    // }
    //
    // // Advance to next symbol.
    // private void next () {
    //     // End of sequence reached.
    //     if (current == null) return;
    //
    //     // Advance.
    //     ++index;
    //
    //     // Update current
    //     if (substrings.size() > index) {
    //         current = substrings.get(index);
    //     } else {
    //         current = null;
    //     }
    // }
    //
    // /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    //
    // /* Program Component Parsing */
    //
    // // Parses a Program (Preamble + Definitions).
    // public ProgramComponent parseProgram () {
    //
    //     /* Data to be parsed */
    //
    //     String globalpackage = null;
    //     List <Include> includes = new ArrayList<>();
    //     List <ProgramComponent> definitions = new ArrayList<>();
    //
    //     /* Case: Empty file */
    //     if (current == null) {
    //         return new Program(globalpackage,
    //                 includes.toArray(new Include[includes.size()]),
    //                 definitions.toArray(new ProgramComponent[definitions.size()])
    //         );
    //     }
    //
    //
    //     /* Preamble */
    //
    //     // Global Package.
    //     if (symbol(current) == GBL) {
    //         next();
    //         globalpackage = current;
    //         next();
    //     }
    //
    //     // Add Include.
    //     while (symbol(current) == GET) {
    //         next();
    //         String path = current;
    //         next();
    //
    //         // Add Symbols.
    //         List <String> symbols = new ArrayList();
    //         while (symbol(current) == SYM) {
    //             next();
    //             symbols.add(current);
    //             next();
    //         }
    //
    //         Include inc;
    //         if (symbols.isEmpty()) {
    //             inc = new Include(path, null);
    //         } else {
    //             inc = new Include(path, symbols.toArray(new String[symbols.size()]));
    //         }
    //
    //         includes.add(inc);
    //     }
    //
    //     /* Definitions */
    //
    //     while (current != null) {
    //        ProgramComponent definition = parseDefinition();
    //        definitions.add(definition);
    //     }
    //
    //     /*  Return */
    //
    //     return new Program(globalpackage,
    //             includes.toArray(new Include[includes.size()]),
    //             definitions.toArray(new ProgramComponent[definitions.size()])
    //     );
    // }
    //
    //
    // private ProgramComponent parseDefinition () {
    //     // Parse Modifier.
    //     Modifiers modifiers = parseModifiers();
    //
    //     // Parse Definition.
    //     switch (symbol(current)) {
    //         case PKG: {
    //             next();
    //             String name = current;
    //             next();
    //
    //             List <ProgramComponent> subDefinitions = new ArrayList<>();
    //             while (symbol(current) != X) {
    //                 ProgramComponent definition = parseDefinition();
    //                 subDefinitions.add(definition);
    //             }
    //
    //             next();
    //
    //             return new SubPackage(modifiers, name,
    //                     subDefinitions.toArray(new ProgramComponent[subDefinitions.size()])
    //             );
    //         }
    //         case STC:
    //             return parseStatic(modifiers);
    //         case DEF:
    //             return parseDefine(modifiers);
    //         case FUN:
    //             return parseDefineFunc(modifiers);
    //         case TYPE: {
    //             next();
    //             String name = current;
    //             next();
    //             String typestring = current;
    //             next();
    //
    //             return new TypeDef(modifiers, name, typestring);
    //         }
    //         case DATATYPE: {
    //             next();
    //             String name = current;
    //             next();
    //             String[] exts = current.split(";");
    //             next();
    //             DatatypeComponent[] dataDefinitions = parseDataDefinitionList();
    //
    //             return new DatatypeDefinition(modifiers, name, exts, dataDefinitions);
    //         }
    //         case ABSDATATYPE: {
    //             next();
    //             String name = current;
    //             next();
    //             String[] exts = current.split(";");
    //             next();
    //             DatatypeComponent[] dataDefinitions = parseDataDefinitionList();
    //
    //             return new AbstractDatatypeDefinition(modifiers, name, exts, dataDefinitions);
    //         }
    //         case INTERFACE: {
    //             next();
    //             String name = current;
    //             next();
    //             String[] exts = current.split(";");
    //             next();
    //             DatatypeComponent[] dataDefinitions = parseDataDefinitionList();
    //
    //             return new InterfaceDefinition(modifiers, name, exts, dataDefinitions);
    //         }
    //     }
    //
    //     // Invalid Definiiton.
    //     throw new RuntimeException("Invalid Definition in Sublynx Program");
    // }
    //
    // // Parse Definition Modifiers.
    // private Modifiers parseModifiers () {
    //     // Access Level.
    //     // Default 0 (public).
    //     int access_level = MetaTable.ACL_PUBLIC;
    //
    //     if (symbol(current) == ACL) {
    //         next();
    //         switch (current) {
    //             case "public":
    //                 access_level = MetaTable.ACL_PUBLIC;
    //                 break;
    //             case "private":
    //                 access_level = MetaTable.ACL_PRIVATE;
    //                 break;
    //             case "common":
    //                 access_level = MetaTable.ACL_COMMON;
    //                 break;
    //             case "protected":
    //                 access_level = MetaTable.ACL_PROTECTED;
    //                 break;
    //             default:
    //                 throw new RuntimeException("Invalid Modifier in Sublynx Program");
    //         }
    //         next();
    //     }
    //
    //     // Eventually add different modifiers or something.
    //     return new Modifiers(access_level);
    // }
    //
    // // Parse Static Definition.
    // private Static parseStatic (Modifiers modifiers) {
    //     next();
    //     String name = current;
    //     next();
    //     String typestring = current;
    //     next();
    //     ExpressionComponent expr = parseExpression();
    //
    //     return new Static(modifiers, name, typestring, expr);
    // }
    //
    // // Parse Define Definition.
    // private Define parseDefine (Modifiers modifiers) {
    //     next();
    //     String name = current;
    //     next();
    //     String typestring = current;
    //     next();
    //     ExpressionComponent expr = parseExpression();
    //
    //     return new Define(modifiers, name, typestring, expr);
    // }
    //
    // // Parse FunctionDefinition.
    // private DefineFunc parseDefineFunc (Modifiers modifiers) {
    //     next();
    //     String name = current;
    //     next();
    //
    //     // Arguments.
    //     List <FunctionArgument> arguments = new ArrayList<>();
    //     while (symbol(current) == ARG) {
    //         FunctionArgument argument = parseFunctionArgument();
    //         arguments.add(argument);
    //     }
    //
    //     // Return value.
    //     String typestring;
    //     if (symbol(current) == RET){
    //         next();
    //         typestring = current;
    //         next();
    //     } else {
    //         throw new RuntimeException("Invalid Definition in Sublynx Program");
    //     }
    //
    //     // Body.
    //     ExpressionComponent expr = parseExpression();
    //
    //     return new DefineFunc(modifiers, name,
    //             arguments.toArray(new FunctionArgument[arguments.size()]),
    //             typestring, expr
    //     );
    // }
    //
    // // Parse Function Argument.
    // private FunctionArgument parseFunctionArgument () {
    //     next();
    //
    //     // Declaration String.
    //     String dclstring = current;
    //     next();
    //
    //     String[] components = dclstring.split(":");
    //
    //     if (components.length != 2) {
    //         throw new RuntimeException("Invalid Function Argument in Sublynx Program");
    //     }
    //
    //     return new FunctionArgument(components[0], components[1]);
    // }
    //
    //
    // /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    //
    // /* Datatype Component Parsing */
    //
    // // Parse Data-Definition.
    // public DatatypeComponent parseDataDefinition () {
    //     // Parse Modifiers.
    //     Modifiers modifiers = parseModifiers();
    //
    //     // Parse Definition.
    //     switch (symbol(current)) {
    //         case PATTERN:
    //             return parsePatternDefinition(modifiers);
    //         case DATA:
    //             return parseDataPatternDefinition(modifiers);
    //         case LET:
    //             return parseLetField();
    //         case VAR:
    //             return parseVarField();
    //         case CONS:
    //             return parseConstructor(modifiers);
    //         case COPY:
    //             return parseCopyConstructor(modifiers);
    //         case DECONS:
    //             return parseDeconstructor(modifiers);
    //         case DEF:
    //             return parseProperty(modifiers);
    //         case FUN:
    //             return parseFunctionProperty(modifiers);
    //         case ABSDEF:
    //             return parseAbstractProperty(modifiers);
    //         case ABSFUN:
    //             return parseAbstractFunctionProperty(modifiers);
    //         case FOR: {
    //             next();
    //             String[] patterns = current.split(";");
    //             next();
    //
    //             DatatypeComponent[] dataDefinitions = parseDataDefinitionList();
    //
    //             return new PatternBlock(patterns, dataDefinitions);
    //         }
    //     }
    //
    //     // Invalid Data-Definiiton.
    //     throw new RuntimeException("Invalid Datatype Definition in Sublynx Program");
    // }
    //
    // // Parse List of Data-Definitons.
    // private DatatypeComponent[] parseDataDefinitionList () {
    //     List <DatatypeComponent> dataDefinitions = new ArrayList<>();
    //
    //     while (symbol(current) != X) {
    //         DatatypeComponent datadef = parseDataDefinition();
    //         dataDefinitions.add(datadef);
    //     }
    //
    //     next();
    //
    //     return dataDefinitions.toArray(new DatatypeComponent[dataDefinitions.size()]);
    // }
    //
    //
    // // Parse Pattern Definition.
    // private PatternDefinition parsePatternDefinition (Modifiers modifiers) {
    //     next();
    //     String name = current;
    //     next();
    //
    //     return new PatternDefinition(modifiers, name);
    // }
    //
    // // Parse Data-Pattern Definition.
    // private DataPatternDefinition parseDataPatternDefinition (Modifiers modifiers) {
    //     next();
    //     String name = current;
    //     next();
    //     String typestring = current;
    //     next();
    //
    //     return new DataPatternDefinition(modifiers, name, typestring);
    // }
    //
    // // Parse 'let' Field Declaration.
    // private LetField parseLetField () {
    //     next();
    //     String dclstring = current;
    //     next();
    //
    //     return new LetField(dclstring);
    // }
    //
    // // Parse 'var' Field Declaration.
    // private VarField parseVarField () {
    //     next();
    //     String dclstring = current;
    //     next();
    //
    //     return new VarField(dclstring);
    // }
    //
    // // Parse Constructor.
    // private Constructor parseConstructor (Modifiers modifiers) {
    //     next();
    //     String name = current;
    //     next();
    //
    //     // Arguments.
    //     List <FunctionArgument> arguments = new ArrayList<>();
    //     while (symbol(current) == ARG) {
    //         FunctionArgument argument = parseFunctionArgument();
    //         arguments.add(argument);
    //     }
    //
    //     if (symbol(current) != C){
    //         throw new RuntimeException("Invalid Constructor in Sublynx Program");
    //     }
    //     next();
    //
    //     // Body.
    //     ExpressionComponent expr = parseExpression();
    //
    //     return new Constructor(modifiers, name,
    //             arguments.toArray(new FunctionArgument[arguments.size()]),
    //             expr
    //     );
    // }
    //
    // // Parse Copy-Constructor.
    // private CopyConstructor parseCopyConstructor (Modifiers modifiers) {
    //     next();
    //     String name = current;
    //     next();
    //
    //     // Arguments.
    //     List <FunctionArgument> arguments = new ArrayList<>();
    //     while (symbol(current) == ARG) {
    //         FunctionArgument argument = parseFunctionArgument();
    //         arguments.add(argument);
    //     }
    //
    //     if (symbol(current) != C){
    //         throw new RuntimeException("Invalid Constructor in Sublynx Program");
    //     }
    //     next();
    //
    //     // Body.
    //     ExpressionComponent expr = parseExpression();
    //
    //     return new CopyConstructor(modifiers, name,
    //             arguments.toArray(new FunctionArgument[arguments.size()]),
    //             expr
    //     );
    // }
    //
    // // Parse Deconstructor.
    // private Deconstructor parseDeconstructor (Modifiers modifiers) {
    //     next();
    //     String name = current;
    //     next();
    //     String typestring = current;
    //     next();
    //     ExpressionComponent expr = parseExpression();
    //
    //     return new Deconstructor(modifiers, name, typestring, expr);
    // }
    //
    // // Parse Property Definition.
    // private Property parseProperty (Modifiers modifiers) {
    //     next();
    //     String name = current;
    //     next();
    //     String typestring = current;
    //     next();
    //     ExpressionComponent expr = parseExpression();
    //
    //     return new Property(modifiers, name, typestring, expr);
    // }
    //
    // // Parse Function Property Definition.
    // private FunctionProperty parseFunctionProperty (Modifiers modifiers) {
    //     next();
    //     String name = current;
    //     next();
    //
    //     // Arguments.
    //     List <FunctionArgument> arguments = new ArrayList<>();
    //     while (symbol(current) == ARG) {
    //         FunctionArgument argument = parseFunctionArgument();
    //         arguments.add(argument);
    //     }
    //
    //     // Return type.
    //     String typestring;
    //     if (symbol(current) == RET){
    //         next();
    //         typestring = current;
    //         next();
    //     } else {
    //         throw new RuntimeException("Invalid Definition in Sublynx Program");
    //     }
    //
    //     // Body.
    //     ExpressionComponent expr = parseExpression();
    //
    //     return new FunctionProperty(modifiers, name,
    //             arguments.toArray(new FunctionArgument[arguments.size()]),
    //             typestring, expr
    //     );
    // }
    //
    // // Parse Abstract Property Definition.
    // private AbstractProperty parseAbstractProperty (Modifiers modifiers) {
    //     next();
    //     String name = current;
    //     next();
    //     String typestring = current;
    //     next();
    //
    //     return new AbstractProperty(modifiers, name, typestring);
    // }
    //
    // // Parse Abstract Function Property Definition.
    // private AbstractFunctionProperty parseAbstractFunctionProperty (Modifiers modifiers) {
    //     next();
    //     String name = current;
    //     next();
    //
    //     // Arguments.
    //     List <FunctionArgument> arguments = new ArrayList<>();
    //     while (symbol(current) == ARG) {
    //         FunctionArgument argument = parseFunctionArgument();
    //         arguments.add(argument);
    //     }
    //
    //     // Return type.
    //     String typestring;
    //     if (symbol(current) == RET){
    //         next();
    //         typestring = current;
    //         next();
    //     } else {
    //         throw new RuntimeException("Invalid Definition in Sublynx Program");
    //     }
    //
    //     return new AbstractFunctionProperty(modifiers, name,
    //             arguments.toArray(new FunctionArgument[arguments.size()]),
    //             typestring
    //     );
    // }
    //
    //
    // /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    //
    // /* Expression Component Parsing */
    //
    // // Base Expression Parsing Function.
    // private ExpressionComponent parseExpression () {
    //     switch (symbol(current)) {
    //         case VAL:
    //             return parseValue();
    //         case LOAD:
    //             return parseLoad();
    //         case STORE:
    //             return parseStore();
    //         case STOREADDR:
    //             return parseStoreADDR();
    //         case FIELD:
    //             return parseFieldAcc();
    //         case FIELDN:
    //             return parseFieldNoAcc();
    //         case ACCESS:
    //             return parseMethodAcc();
    //         case TYPEARG:
    //             return parseTypeArg();
    //         case GET:
    //             return parseOperator(Operators.GET);
    //         case ABS:
    //             return parseOperator(Operators.ABS);
    //         case NEG:
    //             return parseOperator(Operators.NEG);
    //         case NOT:
    //             return parseOperator(Operators.NOT);
    //         case CMPL:
    //             return parseOperator(Operators.CMPL);
    //         case ACC:
    //             return parseBiOperator(Operators.ACC);
    //         case ADD:
    //             return parseBiOperator(Operators.ADD);
    //         case SUB:
    //             return parseBiOperator(Operators.SUB);
    //         case MUL:
    //             return parseBiOperator(Operators.MUL);
    //         case DIV:
    //             return parseBiOperator(Operators.DIV);
    //         case MOD:
    //             return parseBiOperator(Operators.MOD);
    //         case EQ:
    //             return parseBiOperator(Operators.EQ);
    //         case NEQ:
    //             return parseBiOperator(Operators.NEQ);
    //         case EQUALS:
    //             return parseBiOperator(Operators.EQUALS);
    //         case LESS:
    //             return parseBiOperator(Operators.LESS);
    //         case LESSEQ:
    //             return parseBiOperator(Operators.LESSEQ);
    //         case GRT:
    //             return parseBiOperator(Operators.GRT);
    //         case GRTEQ:
    //             return parseBiOperator(Operators.GRTEQ);
    //         case AND:
    //             return parseBiOperator(Operators.AND);
    //         case OR:
    //             return parseBiOperator(Operators.OR);
    //         case XOR:
    //             return parseBiOperator(Operators.XOR);
    //         case BAND:
    //             return parseBiOperator(Operators.BAND);
    //         case BOR:
    //             return parseBiOperator(Operators.BOR);
    //         case BXOR:
    //             return parseBiOperator(Operators.BXOR);
    //         case SLEFT:
    //             return parseBiOperator(Operators.SLEFT);
    //         case SRIGHT:
    //             return parseBiOperator(Operators.SRIGHT);
    //         case URIGHT:
    //             return parseBiOperator(Operators.URIGHT);
    //         case CALL:
    //             return parseCall();
    //         case CONT:
    //             return parseContinue();
    //         case STRUCT:
    //             return parseStructure();
    //         case ARRAY:
    //             return parseArray();
    //         case TIMES:
    //             return parseArrayOperator(Operators.TIMES);
    //         case MAP:
    //             return parseArrayOperator(Operators.MAP);
    //         case COND:
    //             return parseConditional();
    //         case SELECT:
    //             return parseSelect();
    //         case WITH:
    //             return parseWith();
    //         case LET:
    //             return parseLet(false);
    //         case LETV:
    //             return parseLet(true);
    //         case VAR:
    //             return parseVar(false);
    //         case VARV:
    //             return parseVar(true);
    //         case FN:
    //             return parseLambda();
    //         case IFN:
    //             return parseImplicit();
    //         case FOR:
    //             return parseFor();
    //         case RAISE:
    //             return parseRaise();
    //         case TRY:
    //             return parseTryResume();
    //         case DO:
    //             return parseDoBlock();
    //         case WHILE:
    //             return parseWhileBlock();
    //         case FORLOOP:
    //             return parseForLoop();
    //         case BREAK:
    //             return parseBreak();
    //         case CONTINUE:
    //             return parseNext();
    //         case RETURN:
    //             return parseReturn(false);
    //         case RETURNV:
    //             return parseReturn(true);
    //         case SUPER:
    //             return parseSuperConstructor();
    //         case INIT:
    //             return parseFieldInit();
    //     }
    //
    //     throw new RuntimeException("Invalid Expression in Sublynx Program");
    // }
    //
    // // Parse Expression List.
    // // Advances past ending (V) symbol.
    // private ExpressionComponent[] parseExpressionList () {
    //     List <ExpressionComponent> expressions = new ArrayList<>();
    //
    //     while (symbol(current) != V) {
    //         ExpressionComponent expr = parseExpression();
    //         expressions.add(expr);
    //     }
    //
    //     next();
    //
    //     return expressions.toArray(new ExpressionComponent[expressions.size()]);
    // }
    //
    // // Parse Value Component.
    // private Value parseValue () {
    //     next();
    //     String value = current;
    //     next();
    //     return new Value(value);
    // }
    //
    // // Parse Load Component.
    // private Load parseLoad () {
    //     next();
    //     String name = current;
    //     next();
    //     return new Load(name);
    // }
    //
    // // Parse Store Component.
    // private Store parseStore () {
    //     next();
    //     LValueComponent lvalue = parseLValue();
    //     ExpressionComponent expr = parseExpression();
    //     return new Store(lvalue, expr);
    // }
    //
    // // Parse StoreAddress Component
    // private StoreAddress parseStoreADDR () {
    //     next();
    //
    //
    //     return null;
    // }
    //
    // // Parse Field Component.
    // private FieldAcc parseFieldAcc () {
    //     next();
    //     String fieldname = current;
    //     next();
    //     ExpressionComponent expr = parseExpression();
    //     return new FieldAcc(fieldname, expr);
    // }
    //
    // // Parse Field-Index Component.
    // private FieldNoAcc parseFieldNoAcc () {
    //     next();
    //     int id = Integer.parseInt(current);
    //     next();
    //     ExpressionComponent expr = parseExpression();
    //     return new FieldNoAcc(id, expr);
    // }
    //
    // // Parse Method Component.
    // private MethodAcc parseMethodAcc () {
    //     next();
    //     String methodname = current;
    //     next();
    //     ExpressionComponent expr = parseExpression();
    //     return new MethodAcc(methodname, expr);
    // }
    //
    // // Parse Type Specialize Component.
    // private TypeArg parseTypeArg () {
    //     next();
    //     String typestring = current;
    //     next();
    //     ExpressionComponent expr = parseExpression();
    //     return new TypeArg(typestring, expr);
    // }
    //
    // // Parse Operator Component.
    // private Operator parseOperator (Operators operator) {
    //     next();
    //     ExpressionComponent expr = parseExpression();
    //     return new Operator(operator, expr);
    // }
    //
    // // Parse Binary Operator Component.
    // private BiOperator parseBiOperator (Operators operator) {
    //     next();
    //     ExpressionComponent exprLeft = parseExpression();
    //     ExpressionComponent exprRight = parseExpression();
    //     return new BiOperator(operator, exprLeft, exprRight);
    // }
    //
    // // Parse Array Operator Component.
    // private ArrayOperator parseArrayOperator (Operators operator) {
    //     next();
    //     ExpressionComponent exprLeft = parseExpression();
    //     ExpressionComponent exprRight = parseExpression();
    //     return new ArrayOperator(operator, exprLeft, exprRight);
    // }
    //
    // // Parse Function Call Component.
    // private Call parseCall () {
    //     next();
    //     ExpressionComponent[] exprList = parseExpressionList();
    //     return new Call(exprList);
    // }
    //
    // // Parse Continue Call Component.
    // private Continue parseContinue () {
    //     next();
    //     ExpressionComponent[] exprList = parseExpressionList();
    //     return new Continue(exprList);
    // }
    //
    // // Parse Structure Component.
    // private Structure parseStructure () {
    //     next();
    //     ExpressionComponent[] exprList = parseExpressionList();
    //     return new Structure(exprList);
    // }
    //
    // // Parse Array Component.
    // private Array parseArray () {
    //     next();
    //     String typestring = current;
    //     next();
    //     ExpressionComponent[] exprList = parseExpressionList();
    //     return new Array(typestring, exprList);
    // }
    //
    // // Parse Conditional Component.
    // private Conditional parseConditional () {
    //     next();
    //     ExpressionComponent exprCond = parseExpression();
    //     ExpressionComponent exprTrue = parseExpression();
    //     ExpressionComponent exprFalse = parseExpression();
    //     return new Conditional(exprCond, exprTrue, exprFalse);
    // }
    //
    // // Parse Select Component.
    // private Select parseSelect () {
    //     next();
    //     ExpressionComponent expr = parseExpression();
    //
    //     List <Match> matches = new ArrayList<>();
    //     while (symbol(current) != V) {
    //         Match match = parseMatch();
    //         matches.add(match);
    //     }
    //
    //     next();
    //
    //     Match[] m = matches.toArray(new Match[matches.size()]);
    //     return new Select(expr, m);
    // }
    //
    // // Parse Match Component.
    // private Match parseMatch () {
    //     PatternComponent pattern;
    //     switch (symbol(current)) {
    //         case ELSE:
    //             pattern = new Else();
    //             break;
    //         case DEFAULT:
    //             pattern = new Default();
    //             break;
    //         case CASE:
    //             pattern = parseCase();
    //             break;
    //         case RANGE:
    //             pattern = parseRange();
    //             break;
    //         case TYMATCH:
    //             pattern = parseTypePattern();
    //             break;
    //         case MATCH:
    //             pattern = parsePattern();
    //             break;
    //         default:
    //             throw new RuntimeException("Invalid Pattern in Sublynx Program");
    //     }
    //
    //     ExpressionComponent expr = parseExpression();
    //
    //     return new Match(pattern, expr);
    // }
    //
    // // Parse With Component.
    // private With parseWith () {
    //     next();
    //     ExpressionComponent[] exprList = parseExpressionList();
    //     return new With(exprList);
    // }
    //
    // // Parse Let Component.
    // private Let parseLet (boolean initialize) {
    //     next();
    //     String dclstring = current;
    //     next();
    //
    //     ExpressionComponent initializer = null;
    //     if (initialize) {
    //         initializer = parseExpression();
    //     }
    //
    //     return new Let(dclstring, initializer);
    // }
    //
    // // Parse Var Component.
    // private Var parseVar (boolean initialize) {
    //     next();
    //     String dclstring = current;
    //     next();
    //
    //     ExpressionComponent initializer = null;
    //     if (initialize) {
    //         initializer = parseExpression();
    //     }
    //
    //     return new Var(dclstring, initializer);
    // }
    //
    // // Parse Lambda Argument Component.
    // private Arg parseArg (boolean initialize) {
    //     next();
    //     String dclstring = current;
    //     next();
    //
    //     ExpressionComponent initializer = null;
    //     if (initialize) {
    //         initializer = parseExpression();
    //     }
    //
    //     return new Arg(dclstring, initializer);
    // }
    //
    // // Parse Lambda Function Component.
    // private Lambda parseLambda () {
    //     next();
    //     String name = current;
    //     next();
    //
    //     List <Arg> arguments = new ArrayList<>();
    //     A:for (;;) {
    //         switch (symbol(name)) {
    //             case ARG:
    //                 arguments.add(parseArg(false));
    //                 continue;
    //             case ARGV:
    //                 arguments.add(parseArg(true));
    //                 continue;
    //             case RET:
    //                 break A;
    //         }
    //         throw new RuntimeException("Invalid Expression in Sublynx Program");
    //     }
    //
    //     next();
    //     String return_typestring = current;
    //     next();
    //     ExpressionComponent expr = parseExpression();
    //
    //     Arg[] args = arguments.toArray(new Arg[arguments.size()]);
    //     return new Lambda(name, args, return_typestring, expr);
    // }
    //
    // // Parse Implicit Function Component.
    // private Implicit parseImplicit () {
    //     next();
    //     String name = current;
    //     next();
    //
    //     List <Arg> arguments = new ArrayList<>();
    //     A:for (;;) {
    //         switch (symbol(name)) {
    //             case ARG:
    //                 arguments.add(parseArg(false));
    //                 continue;
    //             case ARGV:
    //                 arguments.add(parseArg(true));
    //                 continue;
    //             case RET:
    //                 break A;
    //         }
    //         throw new RuntimeException("Invalid Expression in Sublynx Program");
    //     }
    //
    //     next();
    //     String return_typestring = current;
    //     next();
    //     ExpressionComponent expr = parseExpression();
    //
    //     Arg[] args = arguments.toArray(new Arg[arguments.size()]);
    //     return new Implicit(name, args, return_typestring, expr);
    // }
    //
    // // Parse 'For' Component
    // private ExpressionComponent parseFor() {
    //     next();
    //     IteratorComponent[] iteratorItems = parseIteratorList();
    //     ExpressionComponent expr = parseExpression();
    //
    //     return new For(iteratorItems, expr);
    // }
    //
    // // Parse Raise Component.
    // private Raise parseRaise () {
    //     next();
    //     String typestring = current;
    //     next();
    //     ExpressionComponent[] exprList = parseExpressionList();
    //     return new Raise(typestring, exprList);
    // }
    //
    // // Parse Try-Resume Component.
    // private TryResume parseTryResume () {
    //     next();
    //     ExpressionComponent expr = parseExpression();
    //
    //     List <ResumeHandler> handlers = new ArrayList<>();
    //     while (symbol(current) == RESUME) {
    //         ResumeHandler handler = parseResumeHandler();
    //         handlers.add(handler);
    //     }
    //
    //     if (symbol(current) != V)
    //         throw new RuntimeException("Invalid Expression in Sublynx Program");
    //
    //     next();
    //
    //     ResumeHandler[] r = handlers.toArray(new ResumeHandler[handlers.size()]);
    //     return new TryResume(expr, r);
    // }
    //
    // // Parse Resume Handler.
    // private ResumeHandler parseResumeHandler () {
    //     next();
    //
    //     // Arguments.
    //     List <Arg> arguments = new ArrayList<>();
    //     while (symbol(current) == ARG) {
    //         Arg argument = parseArg(false);
    //         arguments.add(argument);
    //     }
    //
    //     // Return value.
    //     String return_typestring;
    //     if (symbol(current) == RET){
    //         next();
    //         return_typestring = current;
    //         next();
    //     } else {
    //         throw new RuntimeException("Invalid Expression in Sublynx Program");
    //     }
    //
    //     // Body.
    //     ExpressionComponent expr = parseExpression();
    //
    //     Arg[] args = arguments.toArray(new Arg[arguments.size()]);
    //     return new ResumeHandler(args, return_typestring, expr);
    // }
    //
    // // Parse Do/Do-While Block Component.
    // private ExpressionComponent parseDoBlock () {
    //     next();
    //     String label = current;
    //     next();
    //
    //     List <ExpressionComponent> expressions = new ArrayList<>();
    //     for (;;) {
    //
    //         // End of Do Block.
    //         if (symbol(current) == END) {
    //             next();
    //             ExpressionComponent[] exprBlock = expressions.toArray(
    //                     new ExpressionComponent[expressions.size()]
    //             );
    //             return new DoBlock(label, exprBlock);
    //         }
    //
    //         // End of Do-While Block.
    //         if (symbol(current) == WHILE) {
    //             next();
    //             ExpressionComponent exprCond = parseExpression();
    //             ExpressionComponent[] exprBlock = expressions.toArray(
    //                     new ExpressionComponent[expressions.size()]
    //             );
    //             return new DoWhileBlock(label, exprCond, exprBlock);
    //         }
    //
    //         // Add next Expression.
    //         ExpressionComponent expr = parseExpression();
    //         expressions.add(expr);
    //     }
    // }
    //
    // // Parse While Block Component.
    // private WhileBlock parseWhileBlock () {
    //     next();
    //     String label = current;
    //     next();
    //     ExpressionComponent exprCond = parseExpression();
    //
    //     if (symbol(current) != WHILE)
    //         throw new RuntimeException("Invalid Expression in Sublynx Program");
    //
    //     next();
    //
    //     List <ExpressionComponent> expressions = new ArrayList<>();
    //     while (symbol(current) != END) {
    //         ExpressionComponent expr = parseExpression();
    //         expressions.add(expr);
    //     }
    //
    //     next();
    //
    //     ExpressionComponent[] exprBlock = expressions.toArray(
    //         new ExpressionComponent[expressions.size()]
    //     );
    //     return new WhileBlock(label, exprCond, exprBlock);
    // }
    //
    // // Parse For Loop Component
    // private ExpressionComponent parseForLoop() {
    //     next();
    //     String label = current;
    //     next();
    //     IteratorComponent[] iteratorItems = parseIteratorList();
    //
    //     List <ExpressionComponent> expressions = new ArrayList<>();
    //     while (symbol(current) != END) {
    //         ExpressionComponent expr = parseExpression();
    //         expressions.add(expr);
    //     }
    //
    //     next();
    //
    //     ExpressionComponent[] exprBlock = expressions.toArray(
    //         new ExpressionComponent[expressions.size()]
    //     );
    //
    //     return new ForLoop(label, iteratorItems, exprBlock);
    // }
    //
    // // Parse Break Statement Component.
    // private Break parseBreak () {
    //     next();
    //     String label = current;
    //     next();
    //     return new Break(label);
    // }
    //
    // // Parse Continue (Next) statement Component.
    // private Next parseNext () {
    //     next();
    //     String label = current;
    //     next();
    //     return new Next(label);
    // }
    //
    // // Parse Return Statement Component.
    // private Return parseReturn (boolean returnvalue) {
    //     ExpressionComponent expr = null;
    //     if (returnvalue) {
    //         expr = parseExpression();
    //     }
    //     return new Return(expr);
    // }
    //
    // // Parse 'super' Constructor Call Component.
    // private SuperConstructor parseSuperConstructor () {
    //     next();
    //     String constructorName = current;
    //     next();
    //     ExpressionComponent[] exprList = parseExpressionList();
    //     return new SuperConstructor(constructorName, exprList);
    // }
    //
    // // Parse Field Initialize Component.
    // private FieldInit parseFieldInit () {
    //     next();
    //     String name = current;
    //     next();
    //     ExpressionComponent expr = parseExpression();
    //     return new FieldInit(name, expr);
    // }
    //
    // /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    //
    // /* Pattern Components */
    //
    // // Parse Name Pattern Component.
    // private Name parseName () {
    //     next();
    //     String name = current;
    //     next();
    //     return new Name(name);
    // }
    //
    // // Parse Case Pattern Component.
    // private Case parseCase () {
    //     next();
    //     ExpressionComponent expr = parseExpression();
    //     return new Case(expr);
    // }
    //
    // // Parse Range Pattern Component.
    // private Range parseRange () {
    //     next();
    //     ExpressionComponent exprLower = parseExpression();
    //     ExpressionComponent exprUpper = parseExpression();
    //     return new Range(exprLower, exprUpper);
    // }
    //
    // // Parse Type Pattern Component.
    // private TypePattern parseTypePattern () {
    //     next();
    //     String dclstring = current;
    //     next();
    //
    //     return new TypePattern(dclstring);
    // }
    //
    // // Parses a Pattern Component.
    // private PatternComponent parsePattern() {
    //     next();
    //     String name = current;
    //     next();
    //
    //     if (symbol(current) == P) {
    //         next();
    //         return new Pattern(name, null);
    //     }
    //
    //     List <PatternComponent> patternItems = new ArrayList<>();
    //
    //     while (symbol(current) != P) {
    //         PatternComponent patternItem;
    //         switch (symbol(current)) {
    //             case NAME:
    //                 patternItem = parseName();
    //                 break;
    //             case CASE:
    //                 patternItem = parseCase();
    //                 break;
    //             case RANGE:
    //                 patternItem = parseRange();
    //                 break;
    //             case TYMATCH:
    //                 patternItem = parseTypePattern();
    //                 break;
    //             case MATCH:
    //                 patternItem = parsePattern();
    //             default:
    //                 throw new RuntimeException("Invalid Pattern in Sublynx Program");
    //         }
    //         patternItems.add(patternItem);
    //     }
    //
    //     next();
    //
    //     PatternComponent[] patternList = patternItems.toArray(new PatternComponent[patternItems.size()]);
    //     return new Pattern(name, patternList);
    // }
    //
    // /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    //
    // /* Iterator Components */
    //
    // private IteratorComponent parseIterator () {
    //     switch (symbol(current)) {
    //         case FOREACH:
    //             return parseForEach();
    //         case ITERATE:
    //             return parseForIterator();
    //         case COUNT:
    //             return parseCount();
    //         case COUNTBY:
    //             return parseCountBy();
    //         case LET:
    //             return parseILet();
    //         case GUARD:
    //             return parseGuard();
    //     }
    //
    //     throw new RuntimeException("Invalid LValue in Sublynx Program");
    // }
    //
    // private IteratorComponent[] parseIteratorList () {
    //     List <IteratorComponent> expressions = new ArrayList<>();
    //
    //     while (symbol(current) != I) {
    //         IteratorComponent expr = parseIterator();
    //         expressions.add(expr);
    //     }
    //
    //     next();
    //
    //     return expressions.toArray(new IteratorComponent[expressions.size()]);
    // }
    //
    // private ForEach parseForEach () {
    //     next();
    //     String dclString = current;
    //     next();
    //     ExpressionComponent expr = parseExpression();
    //
    //     return new ForEach(dclString, expr);
    // }
    //
    // private ForIterator parseForIterator () {
    //     next();
    //     String dclString = current;
    //     next();
    //     ExpressionComponent exprInit = parseExpression();
    //     ExpressionComponent exprCond = parseExpression();
    //     ExpressionComponent exprNext = parseExpression();
    //
    //     return new ForIterator(dclString, exprInit, exprCond, exprNext);
    // }
    //
    // private Count parseCount () {
    //     next();
    //     String dclString = current;
    //     next();
    //     ExpressionComponent exprStart = parseExpression();
    //     ExpressionComponent exprEnd = parseExpression();
    //
    //     return new Count(dclString, exprStart, exprEnd);
    // }
    //
    // private CountBy parseCountBy () {
    //     next();
    //     String dclString = current;
    //     next();
    //     ExpressionComponent exprStart = parseExpression();
    //     ExpressionComponent exprEnd = parseExpression();
    //     ExpressionComponent exprBy = parseExpression();
    //
    //     return new CountBy(dclString, exprStart, exprEnd, exprBy);
    // }
    //
    // private ILet parseILet () {
    //     next();
    //     String dclString = current;
    //     next();
    //     ExpressionComponent expr = parseExpression();
    //
    //     return new ILet(dclString, expr);
    // }
    //
    // private Guard parseGuard () {
    //     next();
    //     ExpressionComponent expr = parseExpression();
    //
    //     return new Guard(expr);
    // }
    //
    // /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    //
    // /* L-Value Components */
    //
    // // Parses an L-Value Component
    // private LValueComponent parseLValue() {
    //     switch (symbol(current)) {
    //         case NAME:
    //             return parseLocalName();
    //         case ADDR:
    //             return parseAddress();
    //         case ARRAY:
    //             return parseArrayIndex();
    //     }
    //
    //     throw new RuntimeException("Invalid LValue in Sublynx Program");
    // }
    //
    // // Parses a Local Name Component
    // private LocalName parseLocalName() {
    //     next();
    //     String name = current;
    //     next();
    //
    //     return new LocalName(name);
    // }
    //
    // // Parses an Address Component
    // private Address parseAddress() {
    //     next();
    //     ExpressionComponent expr = parseExpression();
    //
    //     return new Address(expr);
    // }
    //
    // // Parses an Array-Index Component.
    // private ArrayIndex parseArrayIndex() {
    //     next();
    //     ExpressionComponent expr1 = parseExpression();
    //     ExpressionComponent expr2 = parseExpression();
    //
    //     return new ArrayIndex(expr1, expr2);
    // }
    //
    //

}
