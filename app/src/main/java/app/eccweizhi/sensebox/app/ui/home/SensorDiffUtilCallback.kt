package app.eccweizhi.sensebox.app.ui.home

import androidx.recyclerview.widget.DiffUtil
import app.eccweizhi.sensebox.appcore.model.SensorType

class SensorDiffUtilCallback(
    private val oldList: List<SensorType>,
    private val newList: List<SensorType>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem == newItem
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem == newItem
    }
}