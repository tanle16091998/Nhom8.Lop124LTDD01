// TrangChuFragment.java
package com.example.nhom8lop124ltdd01.Trangchu;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.nhom8lop124ltdd01.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrangChuFragment extends Fragment {
    private Button but_datve;
    public RecyclerView rcvCategory;
    public CategoryAdapter categoryAdapter;
    public List<Category> categoryList = new ArrayList<>(); // Lưu danh sách phim ban đầu
    private EditText edtSearch;
    public ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();
    private Runnable sliderRunnable;
    public List<FILM> listFilmhh = new ArrayList<>();
    public List<FILM> listFilmhd = new ArrayList<>();
    public List<FILM> listFilmtc = new ArrayList<>();
    public List<FILM> listFilmkd = new ArrayList<>();
    public List<ClassPhim> DSFilmhh = new ArrayList<>();
    public List<ClassPhim> DSFilmhd = new ArrayList<>();
    public List<ClassPhim> DSFilmtc = new ArrayList<>();
    public List<ClassPhim> DSFilmkd = new ArrayList<>();
    public List<ClassPhim> dsPhim = new ArrayList<>();
    public Map<Long, Long> phimTheLoaiMap = new HashMap<>();

    private String getURLVideo;
    private static final String ARG_USER_ID = "USER_ID"; // Key để truyền userId qua arguments
    private String userId; // Biến lưu userId

    public TrangChuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Lấy userId từ arguments
        if (getArguments() != null) {
            userId = getArguments().getString(ARG_USER_ID);
            Log.d("TrangChuFragment", "Received User ID: " + userId); // Log to check if userId is received
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);

        initViews(view);
        initViewPager();
        initSearchEditText();
        but_datve = view.findViewById(R.id.btn_datve);

        // Khởi tạo sliderRunnable
        sliderRunnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager2.getCurrentItem();
                int totalItems = viewPager2.getAdapter().getItemCount();
                viewPager2.setCurrentItem((currentItem + 1) % totalItems);
            }
        };

        return view;
    }

    private void initSearchEditText() {
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Không cần xử lý
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Không cần xử lý
            }
        });
    }

    private void filter(String text) {
        List<Category> filteredList = new ArrayList<>();
        for (Category category : categoryList) {
            List<FILM> filteredFilms = new ArrayList<>();
            for (FILM film : category.getFilms()) {
                if (film.getTitle().toLowerCase().contains(text.toLowerCase())) {
                    filteredFilms.add(film);
                }
            }
            if (!filteredFilms.isEmpty()) {
                filteredList.add(new Category(category.getNameCategory(), filteredFilms));
            }
        }
        categoryAdapter.setData(filteredList);
    }

    public void createCategories() {
        categoryList.clear();
        categoryList.add(new Category("Hoạt hình", listFilmhh));
        categoryList.add(new Category("Hành động", listFilmhd));
        categoryList.add(new Category("Kinh dị", listFilmkd));
        categoryList.add(new Category("Tình cảm", listFilmtc));

        // Cập nhật adapter của RecyclerView
        if (categoryAdapter != null) {
            categoryAdapter.notifyDataSetChanged();
        }
    }

    private void updateUI() {
        if (!listFilmhh.isEmpty()) {
            FILM phimDauTien = listFilmhh.get(0);
            String thongTinPhim = "Tên phim: " + phimDauTien.getTitle();
            Toast.makeText(getContext(), thongTinPhim, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Không có phim hoạt hình nào.", Toast.LENGTH_LONG).show();
        }
    }

    private void initViews(View view) {
        rcvCategory = view.findViewById(R.id.rcv_category);
        edtSearch = view.findViewById(R.id.edtSearch);
        viewPager2 = view.findViewById(R.id.viewPagerImagerSlider);
    }

    private void initViewPager() {
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.phim6));
        sliderItems.add(new SliderItem(R.drawable.img_phim2));
        sliderItems.add(new SliderItem(R.drawable.img_phim3));
        sliderItems.add(new SliderItem(R.drawable.img_phim4));
        sliderItems.add(new SliderItem(R.drawable.img_phim5));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000);
    }

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }
}