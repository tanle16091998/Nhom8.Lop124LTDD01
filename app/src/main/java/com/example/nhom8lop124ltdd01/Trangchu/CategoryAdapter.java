package com.example.nhom8lop124ltdd01.Trangchu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.nhom8lop124ltdd01.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context nContext;
    private List<Category> nListCategory;
    private OnCategoryClickListener categoryClickListener;

    public CategoryAdapter(Context nContext, List<Category> nListCategory) {
        this.nContext = nContext;
        this.nListCategory = nListCategory;
    }

    public void setData(List<Category> list) {
        this.nListCategory = list;
        notifyDataSetChanged();
    }

    public void setOnCategoryClickListener(OnCategoryClickListener listener) {
        this.categoryClickListener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_trangchu, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = nListCategory.get(position);
        if (category == null) {
            return;
        }
        holder.tvNameCategory.setText(category.getNameCategory());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(nContext, RecyclerView.HORIZONTAL, false);
        holder.rcvFilm.setLayoutManager(linearLayoutManager);
        FilmAdapter filmAdapter = new FilmAdapter(nContext);
        filmAdapter.setData(category.getFilms());
        holder.rcvFilm.setAdapter(filmAdapter);

        holder.itemView.setOnClickListener(v -> {
            if (categoryClickListener != null) {
                categoryClickListener.onCategoryClick(category);
            }
        });

        filmAdapter.setOnFilmClickListener(film -> {
            if (categoryClickListener != null) {
//                // Chuyển đổi FILM thành dsFILMHH
//                dsFILMHH dsfilmhh = new dsFILMHH(film.getTitle(), "", "", "", film.getImageUrl(), film.getFilmId());
//                categoryClickListener.onFilmClick(dsfilmhh);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nListCategory != null ? nListCategory.size() : 0;
    }

    public interface OnCategoryClickListener {
        void onCategoryClick(Category category);
     //   void onFilmClick(dsFILMHH film); // Thêm phương thức để xử lý sự kiện click vào phim
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameCategory;
        private RecyclerView rcvFilm;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCategory = itemView.findViewById(R.id.tv_name_category);
            rcvFilm = itemView.findViewById(R.id.rcv_film);
        }
    }
}