package com.watchmyphone.ui.permissions

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.watchmyphone.R
import com.watchmyphone.databinding.SheetOptionalPermissionsBinding

class OptionalPermissionsSheet : BottomSheetDialogFragment() {

    private var _binding: SheetOptionalPermissionsBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if(getNotificationPermissionStatus() && getUsageAccessStatus()) dismiss()

        _binding = SheetOptionalPermissionsBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.switchUsageAccess.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked && !getUsageAccessStatus()) {
                binding.switchUsageAccess.isChecked = true
                startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
            }
        }

        binding.switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked && !getNotificationPermissionStatus()) {
                binding.switchNotifications.isChecked = true
                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                    .putExtra(Settings.EXTRA_APP_PACKAGE, requireContext().packageName)
                startActivity(intent)
            }
        }

        binding.btnCloseOptional.setOnClickListener { dismiss() }

        return view
    }

    override fun onResume() {
        super.onResume()
        val hasUsagePermission = getUsageAccessStatus()
        val hasNotificationPermission = getNotificationPermissionStatus()

        if(hasUsagePermission && hasNotificationPermission) dismiss()

        binding.rowUsage.visibility = if(hasUsagePermission) {
            View.GONE
        } else {
            binding.switchUsageAccess.isChecked = false
            View.VISIBLE
        }
        binding.rowNotifications.visibility = if(hasNotificationPermission){
            View.GONE
        } else {
            binding.switchNotifications.isChecked = false
            View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getUsageAccessStatus(): Boolean {
        val appOps = requireContext().getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            android.os.Process.myUid(),
            requireContext().packageName
        )
        if (mode == AppOpsManager.MODE_DEFAULT) {
            // Check via PackageManager for fallback
            val granted = requireContext().checkCallingOrSelfPermission(android.Manifest.permission.PACKAGE_USAGE_STATS)
            return granted == android.content.pm.PackageManager.PERMISSION_GRANTED
        }
        return mode == AppOpsManager.MODE_ALLOWED
    }

    private fun getNotificationPermissionStatus(): Boolean {
        return NotificationManagerCompat.from(requireContext()).areNotificationsEnabled()
    }
}
