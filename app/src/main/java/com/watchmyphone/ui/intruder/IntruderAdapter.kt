package com.watchmyphone.ui.intruder

import android.content.res.ColorStateList
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Build
import android.os.CancellationSignal
import android.util.Size
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.watchmyphone.R
import com.watchmyphone.data.local.entity.IntruderEntity
import com.watchmyphone.databinding.ItemIntruderBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class IntruderAdapter(
    private val onClick: (IntruderEntity) -> Unit,
    private val onSelectionChanged: (Int) -> Unit
) : ListAdapter<IntruderEntity, IntruderAdapter.VH>(DIFF) {

    private val selectedIds = mutableSetOf<Long>()
    var selectionMode = false
        private set

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<IntruderEntity>() {
            override fun areItemsTheSame(old: IntruderEntity, new: IntruderEntity) = old.id == new.id
            override fun areContentsTheSame(old: IntruderEntity, new: IntruderEntity) = old == new
        }
    }

    inner class VH(private val b: ItemIntruderBinding) :
        RecyclerView.ViewHolder(b.root) {

        fun bind(item: IntruderEntity) {

            val sdf = SimpleDateFormat("MMM dd, hh:mm:ss a", Locale.getDefault())
            b.tvTime.text = sdf.format(Date(item.timestamp))

            b.tvEvent.text = when (item.event) {
                "user_unlocked" -> "Device unlocked"
                "screen_on" -> "Screen on"
                else -> "Someone tried to unlock your phone."
            }

            val isSelected = selectedIds.contains(item.id)

            if (isSelected) {
                b.ivThumb.let {
                    it.setImageResource(R.drawable.ic_tick)
                    it.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(b.root.context, android.R.color.white))
                }
                changeItemTextAlpha(b, 0.6f)
            } else {
                b.ivThumb.imageTintList = null

                if (item.imagePath != null) {
                    val uri = Uri.parse(item.imagePath)

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        val file = File(item.imagePath)
                        val bitmap = ThumbnailUtils.createImageThumbnail(
                            file, Size(200, 200), null)
                        b.ivThumb.setImageBitmap(bitmap)
                    } else
                        b.ivThumb.setImageURI(uri) // fallback
                }
                else
                    b.ivThumb.setImageResource(android.R.drawable.sym_def_app_icon)

                changeItemTextAlpha(b, 1f)
            }

            b.root.setOnLongClickListener {
                if (!selectionMode) {
                    selectionMode = true
                    toggleSelection(item.id)
                }
                true
            }

            b.root.setOnClickListener {
                if (selectionMode) {
                    toggleSelection(item.id)
                } else {
                    onClick(item)
                }
            }
        }
    }

    private fun toggleSelection(id: Long) {
        if (selectedIds.contains(id)) selectedIds.remove(id)
        else selectedIds.add(id)

        notifyDataSetChanged()
        onSelectionChanged(selectedIds.size)

        if (selectedIds.isEmpty()) selectionMode = false
    }

    fun toggleSelectAll(items: List<IntruderEntity>) {
        if (selectedIds.size == items.size) {
            selectedIds.clear()
            selectionMode = false
        } else {
            selectedIds.clear()
            selectedIds.addAll(items.map { it.id })
            selectionMode = true
        }

        notifyDataSetChanged()
        onSelectionChanged(selectedIds.size)
    }

    fun getSelectedIds(): List<Long> = selectedIds.toList()

    fun clearSelection() {
        selectedIds.clear()
        selectionMode = false
        notifyDataSetChanged()
        onSelectionChanged(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemIntruderBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    fun changeItemTextAlpha(b: ItemIntruderBinding, alpha: Float) {
        b.tvTime.alpha = alpha
        b.tvEvent.alpha = alpha
    }
}

