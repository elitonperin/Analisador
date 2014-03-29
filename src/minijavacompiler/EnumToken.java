package minijavacompiler;

/**
 * @author facom
 */
public enum EnumToken 
{
    UNDEF,          // Indefinido
    IF,     
    ELSE,   
    BREAK,  
    FOR,    
    PONTOVIRGULA,
    RELOP,          // 
    NE,             // !=
    GT,             // >
    GE,             // >=
    LT,             // <
    LE,             // <=
    EQ,             // ==
    NEW,        
    PONTO,          // .
    VIRGULA,        // ,
    IGUAL,          // = 
    TYPE,
    DOUBLE,
    DOUBLE_LITERAL,
    STRING,
    STRING_LITERAL,
    INT,
    INTEGER_LITERAL,
    MAIS,           // +
    MENOS,          // -
    VEZES,         // *
    DIV,           // /
    PORCENT,       // %
    ID,
    EOF,
    CONSTRUCTOR,
    CLASS,
    EXTENDS,
    DCHAVES,       // {
    ECHAVES,       // }
    RETURN,     
    SUPER,
    READ,
    PRINT,
    DCONCHETES,     // [
    ECONCHETES,     // ]
    EPARENTESES,    // )
    DPARENTESES     // (
}
