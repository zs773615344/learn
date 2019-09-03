package cn.zs.learn.web.frame.spring.autowire;

public class TextEditor {
    private SpellChecker spellCheck;
    private String name;
    
    public TextEditor() {
        // TODO Auto-generated constructor stub
    }
    public TextEditor(SpellChecker spellChecker,String name) {
        this.spellCheck = spellChecker;
        this.name = name;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
