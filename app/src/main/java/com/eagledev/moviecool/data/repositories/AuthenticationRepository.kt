package com.eagledev.moviecool.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eagledev.moviecool.R
import com.eagledev.moviecool.data.AppSharedPreferences
import com.eagledev.moviecool.data.Result
import com.eagledev.moviecool.model.AccessTokenRequest
import com.eagledev.moviecool.model.AuthRequest
import com.eagledev.moviecool.model.SessionV3Request
import com.eagledev.moviecool.network.MovieDb3Api
import com.eagledev.moviecool.network.MovieDb4Api
import com.eagledev.moviecool.utils.ResourceProvider
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject


/**
 * Handle the authentication process
 */
class AuthenticationRepository @Inject constructor(private val movieDb4Api: MovieDb4Api, private val appSharedPreferences: AppSharedPreferences,
                                                   private val movieDb3Api: MovieDb3Api,
                                                   private val resourceProvider: ResourceProvider) {



    private val _authResponse = MutableLiveData<Result<String>>()

    val authResponse: LiveData<Result<String>>
    get() = _authResponse

    private val _accessTokenStatus = MutableLiveData<Result<Boolean>>()

    val accessTokenStatus: LiveData<Result<Boolean>>
        get() = _accessTokenStatus


    suspend fun getAuthentication(){
        _authResponse.postValue(Result.Loading)


        val response = movieDb4Api.requestToken(
            AuthRequest(redirectTo = "app://www.moviewcool.com/auth")
        )

        if(response.isSuccessful){
            response.body()?.let {
                it.requestToken?.let{token ->
                    appSharedPreferences.putSharedPreference("requestToken", token)
                    _authResponse.postValue(Result.Success("https://www.themoviedb.org/auth/access?request_token=$token"))
                }

            }
        }else{
            _authResponse.postValue(Result.Error(Exception("Error with auth")))
        }
    }

    suspend fun getAccessToken(){

        _accessTokenStatus.postValue(Result.Loading)



        val response = movieDb4Api.requestAccessToken(
            AccessTokenRequest(appSharedPreferences.getSharedPreferences("requestToken"))
        )

        if(response.isSuccessful){
            response.body()?.let {
                if(it.success){
                    appSharedPreferences.putSharedPreference("accessToken", it.accessToken)
                    appSharedPreferences.putSharedPreference("accountId", it.accountId)

                    val v3Res = movieDb3Api.requestAccessToken(resourceProvider.getString(R.string.moviedb_api_3), SessionV3Request(it.accessToken))

                    if(v3Res.isSuccessful){
                        v3Res.body()?.let{sessionV3 ->
                            appSharedPreferences.putSharedPreference("sessionId", sessionV3.sessionId)
                            Timber.tag("Ids").d("Access : ${it.accessToken}  AccountId: ${it.accountId}  SessionId: ${sessionV3.sessionId}")
                            appSharedPreferences.isLogin(true)
                            _accessTokenStatus.postValue(Result.Success(true))
                        }
                    }

                }else{
                    _accessTokenStatus.postValue(Result.Error(Exception("Error with auth")))
                }

            }
        }else{
            _accessTokenStatus.postValue(Result.Error(Exception("Error with auth")))
        }
    }
}