package dev.oscar.ruiz.cvapp.fetchcv.ui.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import dev.oscar.ruiz.cvapp.R
import dev.oscar.ruiz.cvapp.databinding.ActivityLandingBinding
import dev.oscar.ruiz.cvapp.fetchcv.ui.viewmodel.CvFetchViewModel
import dev.oscar.ruiz.cvapp.utils.Status
import javax.inject.Inject

class LandingActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var activityLandingBinding: ActivityLandingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLandingBinding = DataBindingUtil.setContentView(this, R.layout.activity_landing)
        //setContentView(R.layout.activity_landing)
        setUpViewModel()
        setUpRecyclerview()
        setUpHandlers()
    }

    private fun setUpViewModel() {
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(CvFetchViewModel::class.java)
        viewModel.fetchCv()
        viewModel.fetchCv.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> println()
                Status.ERROR -> println()
            }
            //println("ORP "+it.personalInformation.name)

        })
    }

    private fun setUpRecyclerview() {
        val recyclerView = activityLandingBinding.rvLanding

    }

    private fun setUpHandlers() {

    }

    private fun update() {

    }
}
