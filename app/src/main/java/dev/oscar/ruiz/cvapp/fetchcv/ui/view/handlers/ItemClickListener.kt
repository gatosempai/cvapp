package dev.oscar.ruiz.cvapp.fetchcv.ui.view.handlers

import dev.oscar.ruiz.cvapp.fetchcv.data.model.response.CvData

interface ItemClickListener {
    fun onItemClicked(item: CvData)
}