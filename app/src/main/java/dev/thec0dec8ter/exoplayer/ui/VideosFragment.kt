package dev.thec0dec8ter.exoplayer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.thec0dec8ter.exoplayer.R
import dev.thec0dec8ter.exoplayer.adapter.FolderAdapter
import dev.thec0dec8ter.exoplayer.adapter.VideoAdapter
import dev.thec0dec8ter.exoplayer.databinding.FragmentVideosBinding

class VideosFragment : Fragment() {

    private lateinit var binding :FragmentVideosBinding

    private lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        videoAdapter = VideoAdapter()

        for
        videoAdapter.videos.addAll(arguments!!.getStringArrayList("videos"))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentVideosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.videoRecycler.adapter = videoAdapter
    }
}