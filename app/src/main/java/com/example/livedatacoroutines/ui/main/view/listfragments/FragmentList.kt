package com.example.livedatacoroutines.ui.main.view.listfragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.livedatacoroutines.R
import com.example.livedatacoroutines.data.api.ApiHelper
import com.example.livedatacoroutines.data.api.RetrofitBuilder
import com.example.livedatacoroutines.model.User
import com.example.livedatacoroutines.ui.base.ViewModelFactory
import com.example.livedatacoroutines.ui.main.adapter.MainAdapter
import com.example.livedatacoroutines.ui.main.view.DetailsActivity
import com.example.livedatacoroutines.ui.main.viewmodel.MainViewModel
import com.example.livedatacoroutines.utills.Status
import kotlinx.android.synthetic.main.activity_main.*


class FragmentList : Fragment(), MainAdapter.ItemOnClick {
lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_list, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
           viewholder()
        setupUI()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun viewholder() {
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))).get(MainViewModel::class.java)
    }
    private fun retrieveList(users: List<User>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }
    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = MainAdapter(arrayListOf(),this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter

    }

    override fun itemOnClick(users: User,itemView: View) {
        val detailsIntent = Intent(context, DetailsActivity::class.java)
        detailsIntent.putExtra(getString(R.string.country_id), users.avatar)
        detailsIntent.putExtra(getString(R.string.name), users.name)
        startActivity(detailsIntent)
    }
}