package dev.oscar.ruiz.cvapp.fetchcv.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.oscar.ruiz.cvapp.databinding.ItemCvDataBinding
import dev.oscar.ruiz.cvapp.fetchcv.data.model.response.CvData
import dev.oscar.ruiz.cvapp.fetchcv.ui.view.handlers.ItemClickListener

class CvDataAdapter(
    private val clickListener: ItemClickListener,
    var itemList: List<CvData> = listOf()
) : RecyclerView.Adapter<CvDataAdapter.CvDataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CvDataViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CvDataViewHolder(ItemCvDataBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int {
        return if (itemList.isNotEmpty()) itemList.size else 0
    }

    override fun onBindViewHolder(holder: CvDataViewHolder, position: Int) {
        holder.bindView(itemList[position])
    }

    inner class CvDataViewHolder(private val binding: ItemCvDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: CvData) {
            val name = item.personalInformation.name + item.personalInformation.lastName
            binding.imageUrl = "https://images.all-free-download.com/images/graphiclarge/harry_potter_icon_6825007.jpg"
            binding.tvLandingNameItem.text = name
            binding.tvLandingJobItem.text = item.professionalInformation.title
            binding.tvLandingLocationItem.text = item.personalInformation.country
            binding.cvRoot.setOnClickListener {
                clickListener.onItemClicked(item)
            }
        }
    }
}