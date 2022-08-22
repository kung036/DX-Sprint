package com.example.tour.config

import android.app.Application
import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class ApplicationClass : Application() {
    val API_URL = "https://dxprod.idus-b.shop/"

    val ADDRESS_API_URL = "https://dapi.kakao.com/"

    val OPEN_API_URL = "http://apis.data.go.kr/6260000/FestivalService/"
    // 테스트 서버 주소
    // val API_URL = "http://prod.carrot-market.site:9000/products"

    // 실 서버 주소
    // val API_URL = "https://prod.carrot-market.site/"

    companion object {
        // 만들어져있는 SharedPreferences 를 사용
        lateinit var sSharedPreferences: SharedPreferences
        lateinit var editor : SharedPreferences.Editor
        val X_ACCESS_TOKEN = "X-ACCESS-TOKEN"
        val USER_IDX = "USER_IDX"
        val SELECT_DAY = "SELECT_DAY"
        //소셜로그인시 사용
        val ADDRESS_KEY = "77bf0c5c048b5b248605af87fc714045"
        val NICKNAME_TOKEN = "NICKNAME_TOKEN"
        val USER_EMAIL = "USER-EMAIL"
        val USER_PW = "USER-PW"

        lateinit var sRetrofit: Retrofit

        lateinit var aRetrofit: Retrofit

        lateinit var openRetrofit: Retrofit
    }
    override fun onCreate() {
        super.onCreate()
        sSharedPreferences =
            applicationContext.getSharedPreferences("USER_TOKEN", MODE_PRIVATE)
        editor = sSharedPreferences.edit()
        // 레트로핏 인스턴스 생성
        initRetrofitInstance()
    }

    private fun initRetrofitInstance() {
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            // 로그캣에 okhttp.OkHttpClient로 검색하면 http 통신 내용을 보여줍니다.
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
            .build()

        //소셜로그인시 사용
        val client2: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            // 로그캣에 okhttp.OkHttpClient로 검색하면 http 통신 내용을 보여줍니다.
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(KakaoAccessInterceptor())
            .build()

        //오픈 api 호출시 사용
        val openClient: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            // 로그캣에 okhttp.OkHttpClient로 검색하면 http 통신 내용을 보여줍니다.
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        // sRetrofit 이라는 전역변수에 API url, 인터셉터, Gson을 넣어주고 빌드해주는 코드
        // 이 전역변수로 http 요청을 서버로 보내면 됩니다.
        sRetrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //소셜로그인시 사용
        aRetrofit = Retrofit.Builder()
            .baseUrl(ADDRESS_API_URL)
            .client(client2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //오픈 api 호출시 사용
        openRetrofit = Retrofit.Builder()
            .baseUrl(OPEN_API_URL)
            .client(openClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun provideOkHttpClient(
        interceptor: AppInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .run {
            addInterceptor(interceptor)
            build()
        }
    //소셜로그인시 사용
    class AppInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain)
                : Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("Authorization", "KakaoAK " + ADDRESS_KEY)
                .build()

            proceed(newRequest)
        }
    }
}