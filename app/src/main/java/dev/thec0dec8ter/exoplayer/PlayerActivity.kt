package dev.thec0dec8ter.exoplayer

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import dev.thec0dec8ter.exoplayer.databinding.ActivityPlayerBinding
import dev.thec0dec8ter.exoplayer.model.Video

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding

    private var exoPlayer: ExoPlayer? = null

    private lateinit var video :Video

    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent != null){
            video = intent.extras?.getParcelable<Video>("video")!!
        }else{
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.title = video.name
        initializePlayer(MediaItem.fromUri(video.uri))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    public override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    private fun initializePlayer(mediaItem: MediaItem) {
        exoPlayer = ExoPlayer.Builder(this)
            .build()
            .also { exoPlayer ->
                binding.playerView.player = exoPlayer
            }
        exoPlayer?.setMediaItem(mediaItem)
        exoPlayer?.playWhenReady = playWhenReady
        exoPlayer?.seekTo(currentWindow, playbackPosition)
        exoPlayer?.prepare()


    }


    private fun releasePlayer() {
        exoPlayer?.run {
//            playbackPosition = this.currentPosition
//            currentWindow = this.currentWindowIndex
//            playWhenReady = this.playWhenReady
            release()
        }
        exoPlayer = null
    }

    private fun hideSystemUi() {
        binding.playerView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }
}