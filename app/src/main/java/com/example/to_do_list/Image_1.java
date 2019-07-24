package com.example.to_do_list;

public class Image_1 {
    int image ;
    String text;
    String NDung ;

    public Image_1(int image, String text, String NDung) {
        this.image = image;
        this.text = text;
        this.NDung = NDung;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNDung() {
        return NDung;
    }

    public void setNDung(String NDung) {
        this.NDung = NDung;
    }
}
