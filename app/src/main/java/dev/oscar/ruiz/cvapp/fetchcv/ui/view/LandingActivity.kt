package dev.oscar.ruiz.cvapp.fetchcv.ui.view

import android.os.Bundle
import android.widget.Toast
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

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var activityLandingBinding: ActivityLandingBinding
    lateinit var cvDataAdapter: CvDataAdapter


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
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(CvFetchViewModel::class.java)
        viewModel.fetchCv()
        viewModel.fetchCv.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> updateCvList(it.cvDataList)
                Status.ERROR -> println()
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
    }

    private fun launchDetailActivity(item: CvData) {
        Toast.makeText(this, "item clicked " + item.personalInformation.name, Toast.LENGTH_LONG).show()
    }
}
