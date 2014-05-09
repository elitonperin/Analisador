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
    ACHAVES,       // {
    FCHAVES,       // }
    RETURN,     
    SUPER,
    READ,
    PRINT,
    ACONCHETES,     // [
    FCONCHETES,     // ]
    FPAREN,    // )
    APAREN,     // (
    WHILE,
    MAIN,
    VOID,
    STATIC,
    TRUE,
    FALSE,
    BOOLEAN,
    SOPRINTLN,
    THIS,
    PUBLIC
    
}
