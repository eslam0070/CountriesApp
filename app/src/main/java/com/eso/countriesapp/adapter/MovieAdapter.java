package com.eso.countriesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eso.countriesapp.R;
import com.eso.countriesapp.model.Movie;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Movie> movieList;
    private OnClickItemListener mOnClickItemListener;
    public MovieAdapter(Context context, ArrayList<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.mTvTitle.setText(movie.getOriginalTitle());
        holder.mTvRating.setText(Double.toString(movie.getVoteAverage()));
        String imagePath = "https://image.tmdb.org/t/p/w500/" + movie.getPosterPath();
        Glide.with(context).load(imagePath).into(holder.mIvMovie);
    }

    @Override
    public int getItemCount() {
        if (movieList == null) return 0;
        return movieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mIvMovie;
        TextView mTvTitle,mTvRating;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mIvMovie = itemView.findViewById(R.id.ivMovie);
            mTvTitle = itemView.findViewById(R.id.tvTitle);
            mTvRating = itemView.findViewById(R.id.tvRating);
        }

        @Override
        public void onClick(View v) {
            mOnClickItemListener.onClickItem(v,getAdapterPosition());
        }
    }


    public interface OnClickItemListener{
        void onClickItem(View view,int position);
    }

    public void setmOnClickItemListener(OnClickItemListener mOnClickItemListener) {
        this.mOnClickItemListener = mOnClickItemListener;
    }
}
