package dev.oscar.ruiz.cvapp.fetchcv.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.oscar.ruiz.cvapp.databinding.ItemProfessionalHistoryBinding
import dev.oscar.ruiz.cvapp.fetchcv.data.model.response.Education
import dev.oscar.ruiz.cvapp.utils.formatDegree
import dev.oscar.ruiz.cvapp.utils.formatNameLocation

class EducationDataAdapter(
    private val itemList: List<Education> = listOf()
) : RecyclerView.Adapter<EducationDataAdapter.EducationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EducationViewHolder(
            ItemProfessionalHistoryBinding
                .inflate(layoutInflater, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (itemList.isNotEmpty()) itemList.size else 0
    }

    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        holder.bindView(itemList[position])
    }

    inner class EducationViewHolder(private val binding: ItemProfessionalHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: Education) {
            binding.tvDetailProfessionalCompanyLocation.text = formatNameLocation(item.universityName, item.location)
            binding.tvDetailProfessionalPeriod.text = item.period
            binding.tvDetailProfessionalPositionHeld.text = formatDegree(item.degree, item.degreeName)
        }
    }
}