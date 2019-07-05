package com.necatisozer.data.extension

import com.necatisozer.common.exception.NetworkError
import com.necatisozer.common.exception.ServerError
import com.necatisozer.common.helper.Result
import retrofit2.HttpException
import java.io.IOException

suspend inline fun <reified T> networkRequest(crossinline request: suspend () -> T): Result<T> {
    return try {
        Result.success(request())
    } catch (exception: HttpException) {
        Result.failure(ServerError(code = exception.code(), message = exception.message()))
    } catch (exception: IOException) {
        Result.failure(NetworkError())
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}

suspend inline fun <reified T> databaseOperation(crossinline request: suspend () -> T): Result<T> {
    return try {
        Result.success(request())
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}

