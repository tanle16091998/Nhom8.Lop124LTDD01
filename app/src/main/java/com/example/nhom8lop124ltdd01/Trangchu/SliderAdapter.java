package com.example.nhom8lop124ltdd01.Trangchu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.a3t_appdatvexemphim.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;
public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewAdapter>{

    private List<SliderItem> sliderItems;
    private ViewPager2 viewPager2;

    // Thay đổi constructor thành public
    public SliderAdapter(List<SliderItem> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewAdapter(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slide_item_container_trangchu, parent, false
                ));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewAdapter holder, int position) {
        holder.setImage(sliderItems.get(position));
        if (position == sliderItems.size() - 2) {
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    class SliderViewAdapter extends RecyclerView.ViewHolder {
        private RoundedImageView imageView;

        SliderViewAdapter(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
        }

        void setImage(SliderItem sliderItem) {
            imageView.setImageResource(sliderItem.getImage());
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };
}
