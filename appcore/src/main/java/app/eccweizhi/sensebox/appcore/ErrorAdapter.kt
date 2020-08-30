package app.eccweizhi.sensebox.appcore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ErrorAdapter(private val titleResId: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)

        return when (viewType) {
            R.layout.appcore_item_page_title -> {
                PageTitleVh(itemView)
            }
            R.layout.appcore_item_no_sensor -> {
                MissingSensorVh(itemView)
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
                holder.bindData(titleResId)
            }
            is MissingSensorVh -> {
                holder.bindData(titleResId)
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
                    holder.bindData(titleResId)
                }
                is MissingSensorVh -> {
                    holder.bindData(titleResId)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            POSITION_TITLE -> R.layout.appcore_item_page_title
            POSITION_MISSING_SENSOR -> R.layout.appcore_item_no_sensor
            else -> throw IllegalStateException("Invalid position $position")
        }
    }

    companion object {
        private const val POSITION_TITLE = 0
        private const val POSITION_MISSING_SENSOR = 1
    }
}