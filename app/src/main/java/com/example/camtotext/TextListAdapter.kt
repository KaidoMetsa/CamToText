package com.example.camtotext

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.camtotext.databinding.ItemTextBinding

class TextListAdapter(
    private val onCopy: (String) -> Unit,
    private val onDelete: (Long) -> Unit,
    private val onEdit: (TextEntry) -> Unit
) : RecyclerView.Adapter<TextListAdapter.TextViewHolder>() {

    private var textEntries: List<TextEntry> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val binding = ItemTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TextViewHolder(binding, onCopy, onDelete, onEdit)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bind(textEntries[position])
    }

    override fun getItemCount() = textEntries.size

    fun submitList(newTextEntries: List<TextEntry>) {
        textEntries = newTextEntries
        notifyDataSetChanged()
    }

    class TextViewHolder(
        private val binding: ItemTextBinding,
        private val onCopy: (String) -> Unit,
        private val onDelete: (Long) -> Unit,
        private val onEdit: (TextEntry) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(textEntry: TextEntry) {
            binding.titleView.text = textEntry.title
            binding.textView.text = textEntry.text

            binding.copyButton.setOnClickListener {
                onCopy(textEntry.text)
            }

            binding.deleteButton.setOnClickListener {
                onDelete(textEntry.id)
            }

            binding.root.setOnClickListener {
                onEdit(textEntry)
            }
        }
    }
}