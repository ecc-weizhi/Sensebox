package app.eccweizhi.sensebox.app.ui.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.eccweizhi.sensebox.app.R
import app.eccweizhi.sensebox.appcore.model.SensorType

class SensorTileVh(itemView: View, private val listener: Listener) :
    RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView = itemView.findViewById(R.id.sensorIconImageView)
    private val textView: TextView = itemView.findViewById(R.id.sensorIconTextView)
    private val clickView: View = itemView.findViewById(R.id.clickView)

    init {
        clickView.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    fun bind(model: SensorType) {
        imageView.setImageResource(model.getIconResId())
        textView.setText(model.getDisplayNameResId())
    }

    interface Listener {
        fun onItemClick(adapterPosition: Int)
    }
}