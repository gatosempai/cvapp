package dev.oscar.ruiz.cvapp.fetchcv.ui.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import dev.oscar.ruiz.cvapp.R
import dev.oscar.ruiz.cvapp.fetchcv.ui.viewmodel.CvFetchViewModel
import dev.oscar.ruiz.cvapp.utils.Status.ERROR
import dev.oscar.ruiz.cvapp.utils.Status.SUCCESS
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViewModel()
    }

    private fun setUpViewModel() {
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(CvFetchViewModel::class.java)
        viewModel.fetchCv()
        viewModel.fetchCv.observe(this, Observer {
            when (it.status) {
                SUCCESS -> println()
                ERROR -> println()
            }
            //println("ORP "+it.personalInformation.name)

        })
    }

    private fun update() {

    }
}
