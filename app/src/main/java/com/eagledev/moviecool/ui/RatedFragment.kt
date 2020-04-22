package com.eagledev.moviecool.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.eagledev.moviecool.R
import com.eagledev.moviecool.utils.ViewModelFactory
import com.eagledev.moviecool.data.Result
import com.eagledev.moviecool.di.Injectable
import kotlinx.android.synthetic.main.log_in_fragment.*
import timber.log.Timber

import javax.inject.Inject

class RatedFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: RatedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider( this, viewModelFactory).get(RatedViewModel::class.java)

    }
}