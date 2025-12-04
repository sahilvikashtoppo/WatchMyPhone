package com.watchmyphone.ui.intruder

import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.watchmyphone.data.local.entity.AppUsageEntity
import com.watchmyphone.databinding.ItemAppUsageBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UsageAdapter(private val pm: PackageManager) :
    ListAdapter<AppUsageEntity, UsageAdapter.VH>(Diff) {

    object Diff : DiffUtil.ItemCallback<AppUsageEntity>() {
        override fun areItemsTheSame(a: AppUsageEntity, b: AppUsageEntity) = a.id == b.id
        override fun areContentsTheSame(a: AppUsageEntity, b: AppUsageEntity) = a == b
    }

    inner class VH(val binding: ItemAppUsageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAppUsageBinding.inflate(inflater, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        val b = holder.binding

        // Time text
        b.time.text = SimpleDateFormat("MMM dd, hh:mm:ss a", Locale.getDefault())
            .format(Date(item.timestamp))

        // App icon + name (with fallback)
        try {
            val appInfo = pm.getApplicationInfo(item.packageName, 0)
            b.icon.setImageDrawable(pm.getApplicationIcon(appInfo))
            b.name.text = pm.getApplicationLabel(appInfo)
        } catch (e: PackageManager.NameNotFoundException) {
            b.icon.setImageResource(android.R.drawable.sym_def_app_icon)
            b.name.text = item.packageName
        }
    }
}
