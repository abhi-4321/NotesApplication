package com.abhinav.notesapplication.utils

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.abhinav.notesapplication.MainActivity
import com.abhinav.notesapplication.databinding.AddNoteBsfBinding
import com.abhinav.notesapplication.model.Note
import com.abhinav.notesapplication.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class AddNoteBSF @Inject constructor() : BottomSheetDialogFragment() {
    private fun getWindowHeight() = resources.displayMetrics.heightPixels
    private lateinit var behavior: BottomSheetBehavior<FrameLayout>
    private lateinit var binding: AddNoteBsfBinding

    private lateinit var viewModel: MainViewModel

    override fun onStart() {
        super.onStart()

        val view: FrameLayout? =
            dialog?.findViewById(com.google.android.material.R.id.design_bottom_sheet)
        view?.layoutParams?.height = ViewGroup.LayoutParams.MATCH_PARENT
        view?.layoutParams?.width = ViewGroup.LayoutParams.MATCH_PARENT

        behavior = BottomSheetBehavior.from(view!!)
        behavior.peekHeight = getWindowHeight()

        behavior.state = BottomSheetBehavior.STATE_EXPANDED

        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    dismiss()
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        binding = AddNoteBsfBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.time.text = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(System.currentTimeMillis()).toString()
        binding.done.setOnClickListener {
            done()
        }
        binding.close.setOnClickListener { dismiss() }

        return binding.root
    }

    private fun done() {
        val title = binding.title.text.toString()
        val body = binding.body.text.toString()
        val time = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(System.currentTimeMillis()).toString()

        viewModel.insert(Note(time,time,title,body,0))
        dismiss()
    }

    override fun onResume() {
        super.onResume()
        dialog?.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (event.action != KeyEvent.ACTION_DOWN) {
                    dismiss()
                    true
                } else {
                    true // pretend we've processed it
                }
            } else false // pass on to be processed as normal
        }
    }
}