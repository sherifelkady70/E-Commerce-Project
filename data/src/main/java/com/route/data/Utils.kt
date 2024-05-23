package com.route.data

import com.google.gson.Gson
import com.route.data.api.model.Response
import com.route.domain.common.InternetConnection
import com.route.domain.common.Resource
import com.route.domain.common.ServerErrorDC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

suspend fun <T>executeAPI(callAPI : suspend ()->T) : T {
    try {
        val response = callAPI.invoke()
        return response
    }catch (ex:HttpException) {
        if(ex.code() in 400..600){
            val serverResponse = ex.response()?.errorBody()?.string()
            val response = Gson().fromJson<Response<Any>>(serverResponse,Response::class.java)
            throw ServerErrorDC(response.statusMsg,response.message,ex)
        }
        throw ex
    }catch (ex:IOException){
        throw InternetConnection(ex)
    }catch (ex:Exception){
        throw ex
    }

}

suspend fun <T> toFlow(getData : suspend () ->T) : Flow<Resource<T>> {
    return flow {
        emit(Resource.Loading)
        val response = getData.invoke()
        emit(Resource.Success(response))
    }.flowOn(Dispatchers.IO)
        .catch { ex ->
        when(ex){
            is ServerErrorDC -> emit(Resource.ServerError(ex))
            is InternetConnection -> emit(Resource.Fail(ex))
            else -> emit(Resource.Fail(ex))
        }
    }
}