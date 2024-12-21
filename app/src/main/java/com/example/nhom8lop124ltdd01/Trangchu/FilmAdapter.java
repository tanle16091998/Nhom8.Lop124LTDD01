package com.example.nhom8lop124ltdd01.Trangchu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nhom8lop124ltdd01.R;


import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {
    private Context context;
    private List<FILM> nFilms;
    private OnFilmClickListener filmClickListener;

    public FilmAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<FILM> list) {
        this.nFilms = list;
        notifyDataSetChanged();
    }

    public void setOnFilmClickListener(OnFilmClickListener listener) {
        this.filmClickListener = listener;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_films_trangchu, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        FILM film = nFilms.get(position);
        if (film == null) {
            return;
        }
        holder.tvTitle.setText(film.getTitle());
        Glide.with(context)
                .load(film.getImageUrl())
                .into(holder.imgFilm);

        holder.itemView.setOnClickListener(v -> {
            if (filmClickListener != null) {
                filmClickListener.onFilmClick(film);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nFilms != null ? nFilms.size() : 0;
    }

    public interface OnFilmClickListener {
        void onFilmClick(FILM film);
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFilm;
        private TextView tvTitle;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFilm = itemView.findViewById(R.id.img_film);
            tvTitle = itemView.findViewById(R.id.text_title);
        }
    }
}