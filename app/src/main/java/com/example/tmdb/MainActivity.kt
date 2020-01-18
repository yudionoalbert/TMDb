package com.example.tmdb

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.ui.MovieAdapter
import com.example.tmdb.viewmodels.TmdbViewModel
import kotlinx.android.synthetic.main.activity_main.toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var tmdbViewModel: TmdbViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        tmdbViewModel = ViewModelProviders.of(this).get(TmdbViewModel::class.java)
        tmdbViewModel.fetchMovies()

        tmdbViewModel.popularMoviesLiveData.observe(this, Observer {
            it?.let {
                findViewById<RecyclerView>(R.id.rcv_movies).apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = MovieAdapter(it)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
