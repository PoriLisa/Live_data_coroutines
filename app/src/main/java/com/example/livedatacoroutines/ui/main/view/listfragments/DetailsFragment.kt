package com.example.livedatacoroutines.ui.main.view.listfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.livedatacoroutines.R
import kotlinx.android.synthetic.main.fragment_details_list.*
import kotlinx.android.synthetic.main.item_layout.*

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details_list, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val image = activity?.intent?.getStringExtra(getString(R.string.country_id))
        val name = activity?.intent?.getStringExtra(getString(R.string.name))
        setupdata(image, name)
    }

    private fun setupdata(image: String?, name: String?) {
        Glide.with(image_view.context)
            .load(image)
            .into(image_view)
        tv_name.text = name
    }


}