package com.example.camtotext

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.camtotext.databinding.FragmentTextBinding

class TextFragment : Fragment() {

    private lateinit var binding: FragmentTextBinding
    private val sharedViewModel: SharedTextViewModel by viewModels {
        SharedTextViewModelFactory((requireActivity().application as CamToTextApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTextBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textListAdapter = TextListAdapter(
            onCopy = { text -> copyToClipboard(text, "Copied Text") },
            onDelete = { id -> sharedViewModel.deleteEntry(id) },
            onEdit = { entry -> showEditDialog(entry) }
        )
        binding.textListView.adapter = textListAdapter

        sharedViewModel.allEntries.observe(viewLifecycleOwner) { entries ->
            entries?.let { textListAdapter.submitList(it) }
        }

        binding.copyAllButton.text = "Copy All"
        binding.copyAllButton.setOnClickListener {
            val allText = sharedViewModel.allEntries.value?.joinToString("\n") { it.text }
            if (!allText.isNullOrEmpty()) {
                copyToClipboard(allText, "All Copied Text")
            }
        }
    }

    private fun showEditDialog(entry: TextEntry) {
        val editText = EditText(requireContext()).apply {
            setText(entry.text)
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Paranda teksti")
            .setView(editText)
            .setPositiveButton("Salvesta") { _, _ ->
                val newText = editText.text.toString()
                sharedViewModel.updateEntry(entry.copy(text = newText))
            }
            .setNegativeButton("Tühista", null)
            .show()
    }

    private fun copyToClipboard(text: String, label: String) {
        val clipboard = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(requireContext(), "Text copied to clipboard", Toast.LENGTH_SHORT).show()
    }
}