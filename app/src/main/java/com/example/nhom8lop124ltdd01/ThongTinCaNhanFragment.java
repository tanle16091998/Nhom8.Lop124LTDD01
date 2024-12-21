package com.example.nhom8lop124ltdd01;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ThongTinCaNhanFragment extends Fragment {

    private static final int PICK_IMAGE = 1;
    private ImageView imgAvatar;
    private EditText etHoTen, etMaSinhVien, etNgaySinh, etLop, etNhom;

    public ThongTinCaNhanFragment() {
        // Required empty public constructor
    }

    public static ThongTinCaNhanFragment newInstance(String param1, String param2) {
        ThongTinCaNhanFragment fragment = new ThongTinCaNhanFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString("param1");
            String mParam2 = getArguments().getString("param2");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_tin_ca_nhan, container, false);

        imgAvatar = view.findViewById(R.id.imgAvatar);
        Button btnTaiAnh = view.findViewById(R.id.btnTaiAnh);
        etHoTen = view.findViewById(R.id.etHoTen);
        etMaSinhVien = view.findViewById(R.id.etMaSinhVien);
        etNgaySinh = view.findViewById(R.id.etNgaySinh);
        etLop = view.findViewById(R.id.etLop);
        etNhom = view.findViewById(R.id.etNhom);

        btnTaiAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            imgAvatar.setImageURI(imageUri);
        }
    }
}
