package cn.zs.learn.web.frame.spring.annotation.autowired.constructor;

import org.springframework.beans.factory.annotation.Autowired;

public class TextEditor {
    
    private SpellChecker spellCheck;
    @Autowired
    public TextEditor(SpellChecker spellChecker) {
        // TODO Auto-generated constructor stub
        System.out.println("Inside TextEditor constructor.");
        this.spellCheck = spellChecker;
    }  
    public void spellCheck() {
        spellCheck.checkSpelling();
    }
    public SpellChecker getSpellCheck() {
        return spellCheck;
    }
    public void setSpellCheck(SpellChecker spellChecker) {
        this.spellCheck = spellChecker;
    }
    
}
