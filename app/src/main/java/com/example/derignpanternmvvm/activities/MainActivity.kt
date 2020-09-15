package com.example.derignpanternmvvm.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.derignpanternmvvm.R
import com.example.derignpanternmvvm.adapter.NoteAdapter
import com.example.derignpanternmvvm.model.Note
import com.example.derignpanternmvvm.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var notes = ArrayList<Note>()
    private val viewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        ).get(NoteViewModel::class.java)
    }
    private val adapter: NoteAdapter by lazy {
        NoteAdapter(
            notes,
            this,
            { onClickUpdate(it) },
            { onClickDelete(it) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        eventHandle()
    }

    private fun eventHandle() {
        viewModel.getAllNote().observe(this, Observer {
            adapter.setNote(it)
            Log.e("TAG", "SIZE: ${notes.size}")
        })
        btnInsert.setOnClickListener { startActivity(Intent(this, InsertNoteActivity::class.java)) }
    }

    private fun init() {
        rclNote.setHasFixedSize(true)
        rclNote.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rclNote.adapter = adapter
    }

    private fun onClickUpdate(model: Note) {}
    private fun onClickDelete(model: Note) {
        var alert: AlertDialog.Builder = AlertDialog.Builder(this)
        alert.setTitle("DELETE ?")
        alert.setMessage("DO YOU WANT DELETE IT ?")
        alert.setPositiveButton(
            "YES"
        ) { _, _ ->
            viewModel.deleteNote(model)
        }
        alert.setNegativeButton(
            "NO"
        ) { _, _ -> }
        alert.show()
    }
}