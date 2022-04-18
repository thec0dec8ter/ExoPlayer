package dev.thec0dec8ter.exoplayer.adapter

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.thec0dec8ter.exoplayer.PlayerActivity
import dev.thec0dec8ter.exoplayer.databinding.ItemVideoBinding
import dev.thec0dec8ter.exoplayer.model.Video

class VideoAdapter() : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private lateinit var binding : ItemVideoBinding
    var videos :ArrayList<Video> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        binding = ItemVideoBinding.inflate( LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videos.get(position))
    }

    override fun getItemCount(): Int {
       return videos.size
    }

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context = itemView.context
        val binding :ItemVideoBinding = ItemVideoBinding.bind(itemView)

        fun bind(video: Video){
            Glide.with(context)
                .load(video.uri)
                .into(binding.imgThumbnail);

            binding.txtVideoName.text = video.name

            itemView.setOnClickListener(){
                val intent = Intent(context, PlayerActivity::class.java)
                intent.putExtra("video", video)
                context.startActivity(intent)
            }
        }
    }
}