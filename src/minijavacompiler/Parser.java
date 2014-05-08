/**
 * 
 */
package minijavacompiler;

/**
 * @author Administrador
 *
 */
public class Parser 
{
    private Scanner scan;
    private SymbolTable globalST;
    private SymbolTable currentST;
    private Token lToken;
    
    public Parser(String inputFile)    
    {
        //Instancia a tabela de símbolos global e a inicializa
        globalST = new SymbolTable<STEntry>();
        initSymbolTable();
     
        //Faz o ponteiro para a tabela do escopo atual apontar para a tabela global
        currentST = globalST;
        
        //Instancia o analisador léxico
        scan = new Scanner(globalST, inputFile);
    }
    
    /*
     * Método que inicia o processo de análise sintática do compilador
     */
    public void execute()
    {
        lToken = scan.nextToken();
        try
        {
            program();
        }
        catch(Exception e)
        {
            System.err.println("Erro!");
        }
    }
    
    private void advance()
    {
        lToken = scan.nextToken();
    }
    
    private void match(EnumToken cTokenName) throws Exception
    {
        if (lToken.name == cTokenName)
            advance();
        else
        {            //Erro
            throw new Exception("Token inesperado");
        }
    }
    
    private boolean isType(Token cToken)
    {
        if (cToken.name == EnumToken.INT || cToken.name == EnumToken.DOUBLE
            || cToken.name == EnumToken.STRING || cToken.name == EnumToken.BOOLEAN)
            return true;
        
        return false;
    }
    
    /*
     * Método para o símbolo inicial da gramática
     */    
    private void program() throws Exception
    {
        mainClass();
        
        while (lToken.name == EnumToken.CLASS) 
            classDeclaration();
        
        match(EnumToken.EOF);
        
    }
    
    private void mainClass() throws Exception
    {
        //TODO
    }
    
    private void classDeclaration() throws Exception
    {
        //TODO
    }
    
    private void varDeclaration()
    {
        //TODO
    }
    
    private void methodDeclaration()
    {
        //TODO
    }
    
    private void statement() throws Exception
    {
        //TODO
    }
    
    private void initSymbolTable()
    {
        Token t = new Token(EnumToken.CLASS);
        globalST.add(new STEntry(t, "class", true));
        t = new Token(EnumToken.PUBLIC);
        globalST.add(new STEntry(t, "public", true));
        t = new Token(EnumToken.STATIC);
        globalST.add(new STEntry(t, "static", true));
        t = new Token(EnumToken.VOID);
        globalST.add(new STEntry(t, "void", true));
        t = new Token(EnumToken.MAIN);
        globalST.add(new STEntry(t, "main", true));
        t = new Token(EnumToken.RETURN);
        globalST.add(new STEntry(t, "return", true));
        t = new Token(EnumToken.IF);
        globalST.add(new STEntry(t, "if", true));
        t = new Token(EnumToken.ELSE);
        globalST.add(new STEntry(t, "else", true));
        t = new Token(EnumToken.WHILE);
        globalST.add(new STEntry(t, "while", true));
        t = new Token(EnumToken.SOPRINTLN);
        globalST.add(new STEntry(t, "System.out.println", true));
        t = new Token(EnumToken.INT);
        globalST.add(new STEntry(t, "int", true));
        t = new Token(EnumToken.DOUBLE);
        globalST.add(new STEntry(t, "double", true));
        t = new Token(EnumToken.STRING);
        globalST.add(new STEntry(t, "String", true));
        t = new Token(EnumToken.TRUE);
        globalST.add(new STEntry(t, "true", true));
        t = new Token(EnumToken.FALSE);
        globalST.add(new STEntry(t, "false", true));
        t = new Token(EnumToken.THIS);
        globalST.add(new STEntry(t, "this", true));
        t = new Token(EnumToken.NEW);
        globalST.add(new STEntry(t, "new", true));
        t = new Token(EnumToken.EXTENDS);
        globalST.add(new STEntry(t, "extends", true));
    }
}