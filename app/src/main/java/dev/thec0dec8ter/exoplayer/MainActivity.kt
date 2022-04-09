package dev.thec0dec8ter.exoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_search -> {
                Toast.makeText(applicationContext, "click on sarch", Toast.LENGTH_LONG).show()
                true
            }
            R.id.menu_view ->{
                Toast.makeText(applicationContext, "click on view", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.menu_more ->{
                Toast.makeText(applicationContext, "click on more", Toast.LENGTH_LONG).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
