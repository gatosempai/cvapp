package dev.oscar.ruiz.cvapp.fetchcv.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.oscar.ruiz.cvapp.databinding.ItemCvDataBinding
import dev.oscar.ruiz.cvapp.fetchcv.data.model.response.CvData
import dev.oscar.ruiz.cvapp.fetchcv.ui.view.handlers.ItemClickListener
import dev.oscar.ruiz.cvapp.utils.formatName

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

        private val imageUrl = "https://images.all-free-download.com/images/graphiclarge/harry_potter_icon_6825007.jpg"

        fun bindView(item: CvData) {
            binding.imageUrl = imageUrl
            binding.tvLandingNameItem.text =
                formatName(item.personalInformation.name, item.personalInformation.lastName)
            binding.tvLandingJobItem.text = item.professionalInformation.title
            binding.tvLandingLocationItem.text = item.personalInformation.country
            binding.cvRoot.setOnClickListener {
                clickListener.onItemClicked(item)
            }
        }
    }
}