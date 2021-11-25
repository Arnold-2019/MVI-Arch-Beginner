package com.example.mvi_arch_beginner.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvi_arch_beginner.MainViewModel
import com.example.mvi_arch_beginner.R
import com.example.mvi_arch_beginner.ViewModelFactory
import com.example.mvi_arch_beginner.adapter.MainAdapter
import com.example.mvi_arch_beginner.data.api.ApiHelperImpl
import com.example.mvi_arch_beginner.data.api.RetrofitBuilder
import com.example.mvi_arch_beginner.data.model.User
import com.example.mvi_arch_beginner.intent.MainIntent
import com.example.mvi_arch_beginner.viewstate.MainState
import kotlinx.android.synthetic.main.activity_main.buttonFetchUser
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.activity_main.recyclerView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    @ExperimentalCoroutinesApi
    private lateinit var mainViewModel: MainViewModel
    private var adapter = MainAdapter(arrayListOf())

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
        setupViewModel()
        observeViewModel()
        setupClicks()
    }


    @ExperimentalCoroutinesApi
    private fun setupClicks() {
        buttonFetchUser.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchUser)
            }

        }
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as
                            LinearLayoutManager).orientation
                )
            )
        }
        recyclerView.adapter = adapter
    }

    @ExperimentalCoroutinesApi
    private fun setupViewModel() {
        mainViewModel =
            ViewModelProviders.of(this, ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService)))
                .get(MainViewModel::class.java)
    }

    @ExperimentalCoroutinesApi
    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {
                    }
                    is MainState.Loading -> {
                        buttonFetchUser.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                    is MainState.Users -> {
                        progressBar.visibility = View.GONE
                        buttonFetchUser.visibility = View.GONE
                        renderList(it.users)
                    }
                    is MainState.Error -> {
                        progressBar.visibility = View.GONE
                        buttonFetchUser.visibility = View.VISIBLE
                        Toast.makeText(
                            this@MainActivity, it.error,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(users: List<User>) {
        recyclerView.visibility = View.VISIBLE
        users.let { listOfUsers ->
            listOfUsers.let {
                adapter.addData(it)
            }
        }
        adapter.notifyDataSetChanged()
    }
}
