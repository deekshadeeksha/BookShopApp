package com.example.bookshopfirebase;

public class BookImage {
    String imageName;
    int imagePath;

    public BookImage()
    {
        this.imageName="";
        this.imagePath=0;
    }
    public BookImage(String imageName, int imagePath) {
        this.imageName = imageName;
        this.imagePath = imagePath;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }
}
