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
    
    /** 1 - Program -> ClassList
     * 
     *  2
     * 
     * Método para o símbolo inicial da gramática
     */    
    private void program() throws Exception
    {
        //mainClass();
        
        while (lToken.name == EnumToken.CLASS) 
            classDeclaration();
        
        match(EnumToken.EOF);
        
    }
    
    /** 3
     * 
     * @throws Exception
     */
    private void classDeclaration() throws Exception
    {
        //TODO
    	classID();
    	
    	classBody();
    }
    
    /** Auxiliar para tirar a fatoração class id
     * 
     * @throws Exception
     */
    private void classID() throws Exception {
		// TODO Auto-generated method stub
    	match(EnumToken.CLASS);
    	match(EnumToken.ID);
    	
    	if(lToken.name == EnumToken.EXTENDS){
    		advance();
    		match(EnumToken.ID);
    	}
		classBody();	
	}

    /** 4 - 5 - 9 - 11
     * 
     * @throws Exception
     */
	private void classBody() throws Exception {
		// TODO Auto-generated method stub
		
		match(EnumToken.ACHAVES);
		
		
		while(lToken.name == EnumToken.TYPE)
			varDeclaration();
		
		while(lToken.name == EnumToken.CONSTRUCTOR)
			contrutorDecl();
		
		while(lToken.name == EnumToken.TYPE)
			methodDeclaration();
		
		match(EnumToken.FCHAVES);
		
		
		
	}
	
	/**  10
	 * 
	 * @throws Exception
	 */
	private void contrutorDecl() throws Exception {
		// TODO Auto-generated method stub
		advance();
		
		methodyBody();
	}

	/** 13 - 14
	 * 
	 */
	private void methodyBody() throws Exception {
		// TODO Auto-generated method stub
		match(EnumToken.APAREN);
		
		while(lToken.name == EnumToken.TYPE)
			ParamList();
		
		match(EnumToken.FPAREN);
		
		match(EnumToken.ACHAVES);
		
		Statements();
		
		match(EnumToken.FCHAVES);
		
	}
	
	/** 17
	 * 
	 */
	private void Statements() {
		// TODO Auto-generated method stub
		
	}

	/** 15
	 *  ParamList -> ParamList , Param | Param
	 *  
	 *  ParamList -> Param ParamListLine
	 *  
	 *  ParamListLine -> , Param ParamListLine | vazio
	 *  
	 * @throws Exception
	 */
	private void ParamList() throws Exception {
		// TODO Auto-generated method stub
		Param();
		
		ParamListLine();
		
	}
	private void ParamListLine() throws Exception {
		// TODO Auto-generated method stub
		
		while(lToken.name == EnumToken.VIRGULA){
			advance();
			Param();
		}		
	}

	/** 16
	 * 
	 * @throws Exception
	 */
	private void Param() throws Exception {
		// TODO Auto-generated method stub
		Type();
		
		if(lToken.name == EnumToken.ACHAVES){
			advance();
			match(EnumToken.FCHAVES);
		}
		
		match(EnumToken.ID);
	}
	
	/** 6
	 * 
	 * @throws Exception
	 */
	private void varDeclaration() throws Exception
    {
        //TODO		
		Type();
		
		if(lToken.name == EnumToken.ACONCHETES){
			advance();
			match(EnumToken.FCONCHETES);
		}
		
		match(EnumToken.ID);
		
		varDeclarationOption();
		
		match(EnumToken.PONTOVIRGULA);
    }
	
    /** 7
     * 
     * @throws Exception
     */
    private void varDeclarationOption() throws Exception {
		// TODO Auto-generated method stub
    	while(lToken.name == EnumToken.VIRGULA){
    		advance();
    		match(EnumToken.ID);
    		varDeclarationOption();
    	}
    	
    	
	}
    
    /** 8
     * 
     * @throws Exception
     */
	private void Type() throws Exception {
		// TODO Auto-generated method stub
		if(lToken.name == EnumToken.INT){
			advance();
		}
		else if(lToken.name == EnumToken.DOUBLE){
			advance();
		}
		else if(lToken.name == EnumToken.STRING){
			advance();
		}else{
			match(EnumToken.ID);
		}
		
	}
	
	/** 12
	 * @throws Exception 
	 * 
	 */
	private void methodDeclaration() throws Exception
    {
        //TODO
		Type();
		
		if(lToken.name == EnumToken.ACONCHETES){
			advance();
			match(EnumToken.FCONCHETES);
		}
		
		match(EnumToken.ID);
		
		methodyBody();		
    }
    
	/** 18
	 * 
	 * @throws Exception
	 */
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