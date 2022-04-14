package dev.thec0dec8ter.exoplayer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.thec0dec8ter.exoplayer.databinding.ItemVideoBinding

class VideoAdapter() : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private lateinit var binding : ItemVideoBinding
    val videos :ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        binding = ItemVideoBinding.inflate( LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videos?.get(position))
    }

    override fun getItemCount(): Int {
       return videos.size
    }

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(video: String?){

        }

    }
}