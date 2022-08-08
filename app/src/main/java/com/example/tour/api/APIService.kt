package com.example.tour.api

import com.example.tour.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

//interface APIService {
//    @GET(BuildConfig.ENDPOINT_GET_VACCINE_STATUS)
//    fun getInfo(
//        @Query("resultType")ResultType:String,
////        @Query("cond[baseDate::EQ")FindDate:String,
//        @Query("serviceKey")ServiceKey:String = BuildConfig.API_KEY
//    ): Call<VaccineBody>
//}
interface APIService {
    @GET(BuildConfig.ENDPOINT_GET_VACCINE_STATUS)
    fun getInfo(
        @Query("perPage")PerPage:Int,
        @Query("page")Page:Int,
        @Query("cond[baseDate::EQ]")FindDate:String,
        @Query("serviceKey")ServiceKey:String = BuildConfig.API_KEY
    ):Call<VaccineBody>
}