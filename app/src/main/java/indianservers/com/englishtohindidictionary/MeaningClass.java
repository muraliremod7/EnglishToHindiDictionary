package indianservers.com.englishtohindidictionary;

/**
 * Created by JNTUH on 14-03-2018.
 */

public class MeaningClass {
    public MeaningClass(){

    }
    public MeaningClass(String serial, String word, String ed, String ant, String exm) {
        this.serial = serial;
        this.word = word;
        this.ed = ed;
        this.ant = ant;
        this.exm = exm;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    public String getAnt() {
        return ant;
    }

    public void setAnt(String ant) {
        this.ant = ant;
    }

    public String getExm() {
        return exm;
    }

    public void setExm(String exm) {
        this.exm = exm;
    }

    private String serial,word,ed,ant,exm;

}
