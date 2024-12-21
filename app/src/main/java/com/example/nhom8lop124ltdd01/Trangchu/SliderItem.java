package com.example.nhom8lop124ltdd01.Trangchu;

public class SliderItem {

    private int image;

    // Đổi constructor thành public để có thể truy cập từ các class khác
    public SliderItem(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }
}
