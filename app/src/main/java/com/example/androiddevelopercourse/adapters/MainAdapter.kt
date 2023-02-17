package com.example.androiddevelopercourse.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddevelopercourse.R
import com.example.androiddevelopercourse.databinding.VideoItemBinding
import com.example.androiddevelopercourse.model.Popular
import com.squareup.picasso.Picasso

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var listik = emptyList<Popular>()


    class MainViewHolder(item: View): RecyclerView.ViewHolder(item){
        private var binding = VideoItemBinding.bind(item)
        fun bind(popular: Popular) = with(binding){
            textView4.text = popular.title
            textView6.text = popular.overview
            textView7.text = "Vote rate:${popular.vote_average}"
            textView3.text = popular.release_date
            Picasso.get().load("https://image.tmdb.org/t/p/w500${popular.backdrop_path}").into(binding.imageView2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(listik[position])
    }

    override fun getItemCount(): Int {
        return listik.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addElem(popular: ArrayList<Popular>){
        listik = popular
        notifyDataSetChanged()
    }

}