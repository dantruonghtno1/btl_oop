package sample;

public class Word {
    int id;
    String eng, viet;

    public Word(int id, String eng, String viet) {
        this.id = id;
        this.eng = eng;
        this.viet = viet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getViet() {
        return viet;
    }

    public void setViet(String viet) {
        this.viet = viet;
    }
}
