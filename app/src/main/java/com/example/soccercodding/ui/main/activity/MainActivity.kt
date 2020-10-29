package com.example.soccercodding.ui.main.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.soccercodding.databinding.ActivityMainBinding
import com.example.soccercodding.ui.main.MainFragment
import com.example.soccercodding.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

// Hilt annotation for the place where things are injected
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //  initializing viewModel with a lazy delegate to avoid memory leaks
    private val vm: MainViewModel by viewModels()
    // view binding with lateinit
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // initializing viewBinding to the inflated layout with getLayoutInflater convenience
        binding = ActivityMainBinding.inflate(layoutInflater)
        // setting the content view with the root of the binding
        setContentView(binding.root)
        // getting the fragmentManager and starting edit operations on it
        supportFragmentManager.beginTransaction()
                // adding fragment to the activity state
            .add(
                binding.fragmentContainer.id,
                MainFragment.newInstance(),
                TAG
            )
            // adding this transaction to the backStack
            .addToBackStack(TAG)
                // returns id of this transaction back stack entry
            .commit()


    }

    // companion object for holding the logging TAG
    // its going to bind to the class itself
    companion object {
        private const val TAG = "MainActivity"
    }
}