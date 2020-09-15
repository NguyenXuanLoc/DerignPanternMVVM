package com.example.derignpanternmvvm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.derignpanternmvvm.R
import com.example.derignpanternmvvm.model.Note
import com.example.derignpanternmvvm.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_insert_note.*
import kotlinx.android.synthetic.main.activity_main.*

class InsertNoteActivity : AppCompatActivity() {
    private val viewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        ).get(NoteViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_note)
        init()
        eventHandle()
    }

    private fun eventHandle() {
        btnSubmit.setOnClickListener {
            var model = Note(edtTitle.text.toString(), edtDescription.text.toString())
            viewModel.insertNote(model)
            finish()
        }
    }

    private fun init() {

    }
}