package cn.zs.learn.web.frame.spring.annotation.autowired.property;

import org.springframework.beans.factory.annotation.Autowired;

public class TextEditor {
    @Autowired
    private SpellChecker spellCheck;
    
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
