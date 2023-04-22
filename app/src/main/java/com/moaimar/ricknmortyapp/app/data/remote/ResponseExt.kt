package com.moaimar.ricknmortyapp.app.data.remote

import android.content.res.Resources.NotFoundException
import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.funtional.Either
import com.moaimar.ricknmortyapp.app.funtional.left
import com.moaimar.ricknmortyapp.app.funtional.right
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Either<ErrorApp, T> {
    val response: Response<T>
    try {
        response = call.invoke()
    } catch (exception: Throwable) {
        return when (exception) {
            is ConnectException -> ErrorApp.NoInternetError.left()
            is UnknownHostException -> ErrorApp.NoInternetError.left()
            is SocketTimeoutException -> ErrorApp.TimeOutError.left()
            else -> ErrorApp.UnKnowError.left()
        }
    }
    if (!response.isSuccessful) {
        if (response.code()==404){
            return ErrorApp.NotFoundError.left()
        }
        return ErrorApp.UnKnowError.left()
    }


    return response.body()!!.right()
}