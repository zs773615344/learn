package cn.zs.learn.web.frame.spring.annotation.autowired.setter;

import org.springframework.beans.factory.annotation.Autowired;

public class TextEditor {
    private SpellChecker spellCheck;
    
    public void spellCheck() {
        spellCheck.checkSpelling();
    }
    public SpellChecker getSpellCheck() {
        return spellCheck;
    }
    @Autowired
    public void setSpellCheck(SpellChecker spellChecker) {
        this.spellCheck = spellChecker;
    }
    
}
