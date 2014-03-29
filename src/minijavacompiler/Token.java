package minijavacompiler;
/**
 * @author bianca
 */

public class Token 
{
    public EnumToken name;
    public EnumToken attribute;
    public TSEntry tsPtr;
    
    public Token(EnumToken name)
    {
        this.name = name;
        attribute = EnumToken.UNDEF;
        tsPtr = null;
    }
    
    public Token(EnumToken name, EnumToken attr)
    {
        this.name = name;
        attribute = attr;
        tsPtr = null;
    }
}
