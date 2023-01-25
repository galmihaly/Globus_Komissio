package hu.unideb.inf.globus_komissio.databases.models;

import androidx.room.Entity;

@Entity
public class Translations {
    private String languageCode;
    private String wordCode;
    private String text;

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getWordCode() {
        return wordCode;
    }

    public void setWordCode(String wordCode) {
        this.wordCode = wordCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Translations{" +
                "languageCode='" + languageCode + '\'' +
                ", wordCode='" + wordCode + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
