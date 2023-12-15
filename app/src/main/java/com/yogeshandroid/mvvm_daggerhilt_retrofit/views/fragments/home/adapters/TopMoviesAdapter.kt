package com.yogeshandroid.mvvm_daggerhilt_retrofit.views.fragments.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogeshandroid.mvvm_daggerhilt_retrofit.R
import com.yogeshandroid.mvvm_daggerhilt_retrofit.utils.Constants
import com.yogeshandroid.mvvm_daggerhilt_retrofit.utils.RecyclerViewItemClickListener
import com.yogeshandroid.mvvm_daggerhilt_retrofit.views.fragments.home.models.TopMoviesResp

class TopMoviesAdapter(
    private val context: Context?,
    private val list: List<TopMoviesResp.Results>,
    private val recyclerViewItemClickListener: RecyclerViewItemClickListener
) :
    RecyclerView.Adapter<TopMoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_top_movies, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val singleUnit: TopMoviesResp.Results = list[position]

        if (context != null) {
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/original/" + singleUnit.posterPath)
                .into(holder.imageView)
        }

        holder.textView.text = singleUnit.originalTitle

        holder.itemView.setOnClickListener(View.OnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION)
                recyclerViewItemClickListener.onClick(position, Constants.TOP_MOVIES)
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val textView: TextView = itemView.findViewById(R.id.text)
    }
}