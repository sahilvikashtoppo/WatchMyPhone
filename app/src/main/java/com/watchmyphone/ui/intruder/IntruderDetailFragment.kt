package com.watchmyphone.ui.intruder

import android.os.Bundle
import android.view.*
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import com.watchmyphone.databinding.FragmentIntruderDetailBinding

class IntruderDetailFragment : Fragment() {
    companion object {
        private const val ARG_PATH = "path"
        private const val ARG_TS = "ts"
        fun newInstance(path: String?, ts: Long) = IntruderDetailFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PATH, path)
                putLong(ARG_TS, ts)
            }
        }
    }

    private var _binding: FragmentIntruderDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentIntruderDetailBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Apply top padding equal to status bar height
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            v.updatePadding(top = statusBarHeight)
            insets
        }
        val path = arguments?.getString(ARG_PATH)
        val ts = arguments?.getLong(ARG_TS) ?: 0L
        binding.tvEvent.text = "Device unlocked"
        if (path != null) binding.ivImage.setImageURI(android.net.Uri.parse(path))
        else binding.ivImage.setImageResource(android.R.drawable.ic_menu_report_image)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
