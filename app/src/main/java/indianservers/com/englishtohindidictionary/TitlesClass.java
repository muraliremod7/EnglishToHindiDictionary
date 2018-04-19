package indianservers.com.englishtohindidictionary;

/**
 * Created by JNTUH on 12-03-2018.
 */

public class TitlesClass {
    public TitlesClass(){
    }

    public TitlesClass(String word, String serial) {
        this.word = word;
        this.serial = serial;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    private String word,serial;
}
