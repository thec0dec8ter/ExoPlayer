package dev.thec0dec8ter.exoplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.exoplayer2.MediaItem
import com.google.android.material.snackbar.Snackbar
import dev.thec0dec8ter.exoplayer.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onSupportNavigateUp(): Boolean {
        return true
//        val navController = findNavController(R.id.nav_host_fragment_content_player)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
    }
}