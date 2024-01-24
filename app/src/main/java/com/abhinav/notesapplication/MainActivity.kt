package com.abhinav.notesapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhinav.notesapplication.databinding.ActivityMainBinding
import com.abhinav.notesapplication.databinding.AddNoteBsfBinding
import com.abhinav.notesapplication.repository.Repository
import com.abhinav.notesapplication.utils.AddNoteBSF
import com.abhinav.notesapplication.utils.NotesAdapter
import com.abhinav.notesapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var notesAdapter: NotesAdapter

    @Inject
    lateinit var repository : Repository

    @Inject
    lateinit var addNoteBSF: AddNoteBSF

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.fab.setOnClickListener {
            addNoteBSF.isCancelable = false
            addNoteBSF.show(supportFragmentManager,"AddNoteBSF")
        }

        binding.recycler.layoutManager = LinearLayoutManager(this@MainActivity)

        viewModel.getNotes().observe(this){
            notesAdapter.initList(it)
            binding.recycler.adapter = notesAdapter
            notesAdapter.notifyDataSetChanged()
        }
    }
}