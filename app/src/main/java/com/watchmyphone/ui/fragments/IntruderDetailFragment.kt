package com.watchmyphone.ui.fragments

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.watchmyphone.R
import com.watchmyphone.databinding.FragmentIntruderDetailBinding
import com.watchmyphone.ui.adapters.UsageAdapter
import com.watchmyphone.viewmodel.IntruderViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IntruderDetailFragment : Fragment() {

    private var _binding: FragmentIntruderDetailBinding? = null
    private val binding get() = _binding!!
    private val intruderViewModel: IntruderViewModel by viewModels()


    companion object {
        private const val ARG_PATH = "path"
        private const val ARG_TS = "ts"
        private const val ARG_SESSION_ID = "sessionId"

        fun newInstance(path: String?, ts: Long, sessionId: Long) = IntruderDetailFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PATH, path)
                putLong(ARG_TS, ts)
                putLong(ARG_SESSION_ID, sessionId)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentIntruderDetailBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initializeUsageUI()
        val path = arguments?.getString(ARG_PATH)
        val ts = arguments?.getLong(ARG_TS) ?: 0L
        val sessionId = arguments?.getLong(ARG_SESSION_ID) ?: 0L

        // Show image
        if (path != null) binding.ivImage.setImageURI(android.net.Uri.parse(path))
        else binding.ivImage.setImageResource(android.R.drawable.ic_menu_report_image)

        binding.tvEvent.text = getText(R.string.device_unlocked)

        val usageAdapter = UsageAdapter(requireContext().packageManager)
        binding.rvUsage.adapter = usageAdapter
        binding.rvUsage.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            intruderViewModel.observeUsage(sessionId).collectLatest { list ->

                val hasPermission = hasUsageAccess()
                val hasData = list.isNotEmpty()

                when {
                    !hasPermission -> {
                        binding.layoutSwitch.visibility = View.VISIBLE
                        binding.rvUsage.visibility = View.GONE
                        binding.tvUsageInfo.visibility = View.GONE
                        binding.tvEvent.visibility = View.VISIBLE
                        binding.dividerUsage.visibility = View.GONE
                    }
                    hasPermission && !hasData -> {
                        binding.layoutSwitch.visibility = View.GONE
                        binding.rvUsage.visibility = View.GONE
                        binding.tvEvent.visibility = View.GONE
                        binding.tvUsageInfo.visibility = View.VISIBLE
                        binding.dividerUsage.visibility = View.VISIBLE
                    }
                    hasPermission && hasData -> {
                        binding.layoutSwitch.visibility = View.GONE
                        binding.tvUsageInfo.visibility = View.GONE
                        binding.tvEvent.visibility = View.GONE
                        binding.dividerUsage.visibility = View.GONE
                        binding.rvUsage.visibility = View.VISIBLE
                        usageAdapter.submitList(list)
                    }
                }
            }
        }



        // Handle usage access switch
        binding.switchUsage.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (!hasUsageAccess()) {
                    openUsageAccessSettings()
                    binding.switchUsage.isChecked = false
                } else {
                    enableUsageUI()
                }
            }
        }

        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Auto-update UI if permission already granted
        if (hasUsageAccess()) {
            enableUsageUI()
        }
    }

    /** Check Usage Stats permission */
    private fun hasUsageAccess(): Boolean {
        val appOps = requireContext().getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            Process.myUid(),
            requireContext().packageName
        )
        return mode == AppOpsManager.MODE_ALLOWED
    }

    /** Open system usage access screen */
    private fun openUsageAccessSettings() {
        val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
        startActivity(intent)
    }

    private fun initializeUsageUI() {
        binding.rvUsage.visibility = View.GONE
        binding.tvUsageInfo.visibility = View.GONE
        binding.dividerUsage.visibility = View.GONE

        binding.tvEvent.visibility = View.GONE
        binding.layoutSwitch.visibility = View.VISIBLE
    }


    /** When permission is granted, update UI */
    private fun enableUsageUI() {
        binding.layoutSwitch.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        // If user returns after enabling permission
        if (hasUsageAccess()) enableUsageUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
