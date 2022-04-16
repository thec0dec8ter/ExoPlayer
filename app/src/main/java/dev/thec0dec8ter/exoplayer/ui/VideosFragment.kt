package dev.thec0dec8ter.exoplayer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dev.thec0dec8ter.exoplayer.R
import dev.thec0dec8ter.exoplayer.adapter.FolderAdapter
import dev.thec0dec8ter.exoplayer.adapter.VideoAdapter
import dev.thec0dec8ter.exoplayer.databinding.FragmentVideosBinding
import dev.thec0dec8ter.exoplayer.model.Video

class VideosFragment : Fragment() {

    private lateinit var binding :FragmentVideosBinding

    private lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        videoAdapter = VideoAdapter()
        videoAdapter.videos = arguments?.getSerializable("videos") as ArrayList<Video>
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentVideosBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.videoRecycler.adapter = videoAdapter
    }
}