package dev.oscar.ruiz.cvapp.fetchcv.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.oscar.ruiz.cvapp.databinding.ItemProfessionalHistoryBinding
import dev.oscar.ruiz.cvapp.fetchcv.data.model.response.History
import dev.oscar.ruiz.cvapp.utils.formatNameLocation

class ProfessionalHistoryDataAdapter(
    private val itemList: List<History> = listOf()
) : RecyclerView.Adapter<ProfessionalHistoryDataAdapter.ProfessionalHistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessionalHistoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProfessionalHistoryViewHolder(
            ItemProfessionalHistoryBinding
                .inflate(layoutInflater, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (itemList.isNotEmpty()) itemList.size else 0
    }

    override fun onBindViewHolder(holder: ProfessionalHistoryViewHolder, position: Int) {
        holder.bindView(itemList[position])
    }

    inner class ProfessionalHistoryViewHolder(private val binding: ItemProfessionalHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: History) {
            binding.tvDetailProfessionalCompanyLocation.text = formatNameLocation(item.companyName, item.location)
            binding.tvDetailProfessionalPeriod.text = item.period
            binding.tvDetailProfessionalPositionHeld.text = item.positionHeld
        }
    }
}