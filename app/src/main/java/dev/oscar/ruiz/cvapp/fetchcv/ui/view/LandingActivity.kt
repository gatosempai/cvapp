package dev.oscar.ruiz.cvapp.fetchcv.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import dev.oscar.ruiz.cvapp.R
import dev.oscar.ruiz.cvapp.databinding.ActivityLandingBinding
import dev.oscar.ruiz.cvapp.fetchcv.data.model.response.CvData
import dev.oscar.ruiz.cvapp.fetchcv.ui.view.adapter.CvDataAdapter
import dev.oscar.ruiz.cvapp.fetchcv.ui.view.handlers.ItemClickListener
import dev.oscar.ruiz.cvapp.fetchcv.ui.viewmodel.CvFetchViewModel
import dev.oscar.ruiz.cvapp.utils.Status
import javax.inject.Inject


class LandingActivity : DaggerAppCompatActivity(), ItemClickListener {

    companion object {
        const val EXTRA_CV_DATA = "cvData"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var activityLandingBinding: ActivityLandingBinding
    private lateinit var cvDataAdapter: CvDataAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLandingBinding = DataBindingUtil.setContentView(this, R.layout.activity_landing)
        setUpViewModel()
        setUpRecyclerView()
    }

    override fun onItemClicked(item: CvData) {
        launchDetailActivity(item)
    }

    private fun setUpViewModel() {
        val viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CvFetchViewModel::class.java)
        showLoader()
        viewModel.fetchCv()
        viewModel.fetchCv.observe(this, Observer {
            hideLoader()
            when (it.status) {
                Status.SUCCESS -> updateCvList(it.cvDataList)
                Status.ERROR -> updateWrongView()
            }
        })
    }

    private fun setUpRecyclerView() {
        val recyclerView = activityLandingBinding.rvLanding
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(false)
        cvDataAdapter = CvDataAdapter(this)
        recyclerView.adapter = cvDataAdapter
    }

    private fun updateCvList(cvDataList: List<CvData>) {
        cvDataAdapter.itemList = cvDataList
        cvDataAdapter.notifyDataSetChanged()
        activityLandingBinding.tvLandingSomethingWrong.visibility = View.GONE
    }

    private fun updateWrongView() {
        activityLandingBinding.tvLandingSomethingWrong.visibility = View.VISIBLE
    }

    private fun launchDetailActivity(item: CvData) {
        val launchingIntent = Intent(this, DetailActivity::class.java)
        launchingIntent.putExtra(EXTRA_CV_DATA, item)
        startActivity(launchingIntent)
    }

    private fun showLoader() {
        activityLandingBinding.pbLoader.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        activityLandingBinding.pbLoader.visibility = View.GONE
    }
}
