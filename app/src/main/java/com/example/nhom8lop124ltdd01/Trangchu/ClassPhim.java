package com.example.nhom8lop124ltdd01.Trangchu;

import android.os.Parcel;
import android.os.Parcelable;

public class ClassPhim implements Parcelable {
    public Integer MaPhim;
    public String TenPhim;
    public String NoiDung;
    public Integer ThoiLuong;
    public String DaoDien;
    public String DienVien;
    public Integer GioiHanTuoi;
    public Float DonGia;
    public String HinhAnh;
    public String Video;
    public Float DiemDanhGia;
    public String NgayKhoiChieu;
    public String NgayKetThuc;
    public String QuocGia;
    public Integer TrangThai;
    public String MaLich;



    public ClassPhim() {
    }

    public ClassPhim(Integer maPhim, Integer trangThai, String quocGia, String ngayKetThuc, String ngayKhoiChieu, Float diemDanhGia, String video, String hinhAnh, Float donGia, Integer gioiHanTuoi, String dienVien, String daoDien, Integer thoiLuong, String noiDung, String tenPhim, String maLich) {
        MaPhim = maPhim;
        TrangThai = trangThai;
        QuocGia = quocGia;
        NgayKetThuc = ngayKetThuc;
        NgayKhoiChieu = ngayKhoiChieu;
        DiemDanhGia = diemDanhGia;
        Video = video;
        HinhAnh = hinhAnh;
        DonGia = donGia;
        GioiHanTuoi = gioiHanTuoi;
        DienVien = dienVien;
        DaoDien = daoDien;
        ThoiLuong = thoiLuong;
        NoiDung = noiDung;
        TenPhim = tenPhim;
        MaLich = maLich;


    }

    protected ClassPhim(Parcel in) {
        MaPhim = in.readInt();
        TenPhim = in.readString();
        NoiDung = in.readString();
        ThoiLuong = in.readInt();
        DaoDien = in.readString();
        DienVien = in.readString();
        GioiHanTuoi = in.readInt();
        DonGia = in.readFloat();
        HinhAnh = in.readString();
        Video = in.readString();
        DiemDanhGia = in.readFloat();
        NgayKhoiChieu = in.readString();
        NgayKetThuc = in.readString();
        QuocGia = in.readString();
        TrangThai = in.readInt();
        MaLich = in.readString();


    }

    public static final Creator<ClassPhim> CREATOR = new Creator<ClassPhim>() {
        @Override
        public ClassPhim createFromParcel(Parcel in) {
            return new ClassPhim(in);
        }

        @Override
        public ClassPhim[] newArray(int size) {
            return new ClassPhim[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(MaPhim);
        dest.writeString(TenPhim);
        dest.writeString(NoiDung);
        dest.writeInt(ThoiLuong);
        dest.writeString(DaoDien);
        dest.writeString(DienVien);
        dest.writeInt(GioiHanTuoi);
        dest.writeFloat(DonGia);
        dest.writeString(HinhAnh);
        dest.writeString(Video);
        dest.writeFloat(DiemDanhGia);
        dest.writeString(NgayKhoiChieu);
        dest.writeString(NgayKetThuc);
        dest.writeString(QuocGia);
        dest.writeInt(TrangThai);
        dest.writeString(MaLich);


    }


}