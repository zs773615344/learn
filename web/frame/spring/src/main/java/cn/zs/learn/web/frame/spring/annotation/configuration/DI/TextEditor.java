package cn.zs.learn.web.frame.spring.annotation.configuration.DI;

public class TextEditor {
    private SpellChecker spellChecker;
    public TextEditor(SpellChecker spellChecker) {
        System.out.println("Inside TextEditor constructor");
        this.spellChecker = spellChecker;
    }
    
    public void spellCheck(){
        spellChecker.checkSpelling();
    }

    public SpellChecker getSpellChecker() {
        return spellChecker;
    }

    public void setSpellChecker(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }
    
}
