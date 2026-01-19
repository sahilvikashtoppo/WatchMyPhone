package com.watchmyphone.ui.fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.watchmyphone.R
import com.watchmyphone.databinding.BottomDeleteConfirmationBinding
import com.watchmyphone.databinding.FragmentIntruderListBinding
import com.watchmyphone.service.MonitorService
import com.watchmyphone.ui.adapters.IntruderAdapter
import com.watchmyphone.ui.permissions.EssentialPermissionsSheet
import com.watchmyphone.ui.permissions.OptionalPermissionsSheet
import com.watchmyphone.viewmodel.IntruderViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IntruderListFragment : Fragment() {

    private var _binding: FragmentIntruderListBinding? = null
    private val binding get() = _binding!!
    private val intruderViewModel: IntruderViewModel by viewModels()
    private lateinit var adapter: IntruderAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIntruderListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        setupListeners()
        observeData()
        observeServiceState()
    }

    private fun setupRecycler() {
        adapter = IntruderAdapter(
            onClick = { item ->
                parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.container,
                        IntruderDetailFragment.newInstance(item.imagePath, item.timestamp, item.id)
                    )
                    .addToBackStack(null)
                    .commit()
            },
            onSelectionChanged = { count ->
                updateSelectionUI(count)
            }
        )
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            intruderViewModel.uiState.collectLatest { list ->
                adapter.submitList(list)
            }
        }
    }

    /** Observe service ON/OFF state from DataStore */
    private fun observeServiceState() {
        viewLifecycleOwner.lifecycleScope.launch {
            intruderViewModel.serviceEnabled.collectLatest { enabled ->
                if (enabled) {
                    binding.eye.setImageResource(R.drawable.eye_open)
                    binding.eye.setColorFilter(ContextCompat.getColor(requireContext(), android.R.color.white))
                    binding.btnToggle.setBackgroundResource(R.drawable.round_bg_on)

                } else {
                    binding.eye.setImageResource(R.drawable.eye_close)
                    binding.eye.setColorFilter(ContextCompat.getColor(requireContext(), android.R.color.darker_gray))
                    binding.btnToggle.setBackgroundResource(R.drawable.round_bg_off)

                }
            }
        }
    }

    private fun startMonitorService() {
        Log.d("MonitorService", "Service Started")
        try {
            val intent = Intent(requireContext(), MonitorService::class.java).apply {
                action = MonitorService.ACTION_START_MONITORING
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                requireContext().startForegroundService(intent)
            else
                requireContext().startService(intent)

            intruderViewModel.toggleService(true) // Save preference
            /*Snackbar.make(binding.root, "Monitoring enabled", Snackbar.LENGTH_SHORT).show()*/
        } catch (e: Exception) {
            e.printStackTrace()
            Snackbar.make(binding.root, "Failed to start service.", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun stopMonitorService() {
        Log.d("MonitorService", "Service Stopped")
        try {
            val intent = Intent(requireContext(), MonitorService::class.java)
            requireContext().stopService(intent)

            intruderViewModel.toggleService(false)
            /*Snackbar.make(binding.root, "Monitoring stopped", Snackbar.LENGTH_SHORT).show()*/
        } catch (e: Exception) {
            e.printStackTrace()
            Snackbar.make(binding.root, "Failed to stop service.", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun updateSelectionUI(count: Int) {

        if (count > 0) {
            binding.selectionActions.visibility = View.VISIBLE
            binding.txtSelectionCount.visibility = View.VISIBLE
            binding.txtSelectionCount.text = "$count selected"
        } else {
            binding.selectionActions.visibility = View.GONE
            binding.txtSelectionCount.visibility = View.GONE
        }
    }

    private fun setupListeners() {

        binding.btnToggle.setOnClickListener {
            val enabled = intruderViewModel.serviceEnabled.value
            if (!enabled) {
                val cameraGranted = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) ==
                        PackageManager.PERMISSION_GRANTED

                if (!cameraGranted) {
                    EssentialPermissionsSheet {
                        startMonitorService()
                        OptionalPermissionsSheet().show(parentFragmentManager, "optional_permissions")
                    }.show(parentFragmentManager, "essential_permissions")

                } else {
                    startMonitorService()
                    OptionalPermissionsSheet().show(parentFragmentManager, "optional_permissions")
                }
            } else {
                stopMonitorService()
            }
        }

        binding.btnSelectAll.setOnClickListener {
            adapter.toggleSelectAll(intruderViewModel.uiState.value)
        }

        binding.btnDelete.setOnClickListener {
            showDeleteConfirmationSheet()
        }

        binding.btnSettings.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, SettingsFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun showDeleteConfirmationSheet() {
        val bottomSheet = com.google.android.material.bottomsheet.BottomSheetDialog(requireContext())

        val bindingSheet = BottomDeleteConfirmationBinding.inflate(layoutInflater)
        bottomSheet.setContentView(bindingSheet.root)

        bindingSheet.btnCancel.setOnClickListener {
            bottomSheet.dismiss()
        }

        bindingSheet.btnOk.setOnClickListener {
            val ids = adapter.getSelectedIds()

            lifecycleScope.launch {
                ids.forEach { intruderViewModel.delete(it) }
            }

            adapter.clearSelection()
            bottomSheet.dismiss()
        }

        bottomSheet.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
