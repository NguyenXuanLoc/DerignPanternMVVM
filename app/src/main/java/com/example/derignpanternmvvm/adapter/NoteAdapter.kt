package com.example.derignpanternmvvm.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.derignpanternmvvm.R
import com.example.derignpanternmvvm.model.Note

class NoteAdapter(
    var list: ArrayList<Note>,
    var ctx: Activity,
    var onClickUpdate: (Note) -> Unit,
    var onClickDelete: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.ItemHolder>() {

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var txtId = view.findViewById<TextView>(R.id.txt_id)
        private var txtTitle = view.findViewById<TextView>(R.id.txt_title)
        private var txtDescription = view.findViewById<TextView>(R.id.txt_description)
        private var txtUpdate = view.findViewById<TextView>(R.id.txt_update)
        private var txtDelete = view.findViewById<TextView>(R.id.txt_delete)

        @SuppressLint("SetTextI18n")
        fun bind(model: Note) {
            with(model) {
                txtId.text = "ID: $id"
                txtTitle.text = "TITLE: $title"
                txtDescription.text = "DESCRIPTION: $description"
                txtUpdate.setOnClickListener {
                    onClickUpdate(model)
                }
                txtDelete.setOnClickListener {
                    onClickDelete(model)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        if (itemCount > 0) {
            holder.bind(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setNote(notes: List<Note>) {
        list = notes as ArrayList<Note>
        notifyDataSetChanged()
    }
}