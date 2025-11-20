package com.watchmyphone.ui.permissions

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.watchmyphone.databinding.SheetEssentialPermissionsBinding

class EssentialPermissionsSheet(
    private val onPermissionGranted: () -> Unit
) : BottomSheetDialogFragment() {

    private var _binding: SheetEssentialPermissionsBinding? = null
    private val binding get() = _binding!!

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            Toast.makeText(requireContext(), "Camera permission granted!", Toast.LENGTH_SHORT).show()
            dismiss()
            onPermissionGranted()
        } else {
            Toast.makeText(requireContext(), "Camera permission is required!", Toast.LENGTH_SHORT).show()
            binding.switchCamera.isChecked = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SheetEssentialPermissionsBinding.inflate(inflater, container, false)

        binding.switchCamera.isChecked = isCameraPermissionGranted()

        binding.switchCamera.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked && !isCameraPermissionGranted()) {
                binding.switchCamera.isChecked = true
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            } else if (!isChecked && isCameraPermissionGranted()) {
                // Just for UI feedback, Android doesn’t allow revoking programmatically
                Toast.makeText(requireContext(), "You can revoke this permission from Settings.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnClose.setOnClickListener { dismiss() }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val hasCameraPermission = isCameraPermissionGranted()
        if(hasCameraPermission) dismiss()
        binding.switchCamera.isChecked = hasCameraPermission
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun isCameraPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }
}
