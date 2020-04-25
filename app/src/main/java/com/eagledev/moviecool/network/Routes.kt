package com.eagledev.moviecool.network

object Routes {

    const val DISCOVER = "discover/movie"
    const val AUTH_V3 = "authentication/session/convert/4"
    const val RATING = " movie/{movie_id}/rating"
    const val FAVORITE = "  account/{account_id}/favorite"

    object Account{
        const val ACCOUNT = "account"
        const val RATED = "$ACCOUNT/{account_id}/movie/rated"
        const val FAVORITES = "$ACCOUNT/{account_id}/movie/favorites"
        const val RECOMMENDATIONS = "$ACCOUNT/{account_id}/movie/recommendations"
    }
    object Auth{
        const val AUTH = "auth"
        const val ACCESS_TOKEN = "$AUTH/access_token"
        const val REQUEST_TOKEN = "$AUTH/request_token"
    }
}