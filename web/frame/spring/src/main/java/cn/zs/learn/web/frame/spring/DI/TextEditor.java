package cn.zs.learn.web.frame.spring.DI;

public class TextEditor {
    private SpellChecker spellCheck;
    public TextEditor() {
        // TODO Auto-generated constructor stub
    }
    public TextEditor(SpellChecker spellChecker) {
        System.out.println("Inside TextEditor constructor");
        this.spellCheck = spellChecker;
    }
    
    public void spellCheck() {
        spellCheck.checkSpelling();
    }
    public SpellChecker getSpellCheck() {
        return spellCheck;
    }
    public void setSpellCheck(SpellChecker spellChecker) {
        System.out.println("Inside setSpellChecker");
        this.spellCheck = spellChecker;
    }
    
    
}
