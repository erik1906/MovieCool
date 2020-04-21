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

class LogInFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: LogInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.log_in_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider( this, viewModelFactory).get(LogInViewModel::class.java)

        b_login.setOnClickListener {
            viewModel.getAuthentication()
        }



        wb_auth.webViewClient = object: WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

                view?.loadUrl(url)
                if (url != null && url.contains("app://www.moviewcool.com/auth")) {
                    Timber.d("Correct")
                    viewModel.getAccessToken()

                }
                return true

            }
        }

        //Handle the second part of the authentication, getting the access token and a session for the api version 3
        viewModel.accessTokenStatus.observe(viewLifecycleOwner, Observer {
            when(it){
                is Result.Success -> {
                   val intent = Intent(activity!!, HomeActivity::class.java)
                    startActivity(intent)

                }
                is Result.Error -> Timber.e(it.exception)
                Result.Loading -> ""
            }
        })

        //Handle the first part of the authentication generating the access token
        viewModel.authResult.observe(viewLifecycleOwner, Observer {
            when(it){
                is Result.Success -> {
                    wb_auth.loadUrl(it.data)
                    wb_auth.visibility = View.VISIBLE
                    b_login.visibility = View.GONE
                }
                is Result.Error -> Timber.e(it.exception)
                Result.Loading -> ""
            }
        })


    }
}
