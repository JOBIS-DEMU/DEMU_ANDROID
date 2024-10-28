package com.example.demu_android.feature.Blog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demu_android.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModelBottomSheet : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.list_bottom_sheet_item, container, false)
    }

    companion object {
        const val TAG = "BasicBottomModalSheet"
    }
}