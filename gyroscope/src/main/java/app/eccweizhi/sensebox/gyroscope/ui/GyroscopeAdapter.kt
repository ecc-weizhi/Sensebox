package app.eccweizhi.sensebox.gyroscope.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.eccweizhi.sensebox.appcore.PageTitleVh
import app.eccweizhi.sensebox.gyroscope.R
import app.eccweizhi.sensebox.gyroscope.model.RawDataModel

class GyroscopeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var rawData = RawDataModel(0f, 0f, 0f)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)

        return when (viewType) {
            R.layout.appcore_item_page_title -> {
                PageTitleVh(itemView)
            }
            R.layout.gyroscope_item_raw_data -> {
                RawDataVh(itemView)
            }
            else -> throw IllegalStateException("Unknown view type")
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is PageTitleVh -> {
                holder.bindData(R.string.gyroscope_display_name)
            }
            is RawDataVh -> {
                holder.bindData(rawData)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            // Full bind
            onBindViewHolder(holder, position)
        } else {
            // Partial binding.
            when (holder) {
                is PageTitleVh -> {
                    holder.bindData(R.string.gyroscope_display_name)
                }
                is RawDataVh -> {
                    holder.bindData(rawData)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            POSITION_TITLE -> R.layout.appcore_item_page_title
            POSITION_RAW_DATA -> R.layout.gyroscope_item_raw_data
            else -> throw IllegalStateException("Invalid position $position")
        }
    }

    fun updateRawData(newData: RawDataModel) {
        rawData = newData
        notifyItemChanged(POSITION_RAW_DATA, rawData)
    }

    companion object {
        private const val POSITION_TITLE = 0
        private const val POSITION_RAW_DATA = 1
    }
}