package com.necatisozer.data.extension

import com.necatisozer.common.exception.NetworkError
import com.necatisozer.common.exception.ServerError
import com.necatisozer.common.helper.Result
import retrofit2.Response
import java.io.IOException

suspend inline fun <reified T> networkRequest(crossinline request: suspend () -> Response<T>): Result<T> {
    return try {
        val response = request()
        if (response.isSuccessful) Result.success(response.body()!!)
        else Result.failure(ServerError(code = response.code(), message = response.message()))
    } catch (exception: IOException) {
        Result.failure<Nothing>(NetworkError())
    } catch (exception: Exception) {
        Result.failure<Nothing>(exception)
    }
}

