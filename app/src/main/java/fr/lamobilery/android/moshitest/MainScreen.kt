package fr.lamobilery.android.moshitest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import fr.lamobilery.android.contracts.repositories.DeveloperRepository
import fr.lamobilery.android.moshitest.repositories.ApiDeveloperRepository
import fr.lamobilery.android.moshitest.services.ESNAPI
import fr.lamobilery.android.moshitest.services.FakeOkHttpClientBuilder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class MainScreen : AppCompatActivity() {

    private val repository: DeveloperRepository by lazy {
        // Do not do this here, it is only for demo purpose
        val api: ESNAPI = Retrofit.Builder()
            .client(FakeOkHttpClientBuilder.build())
            .baseUrl("https://quentin.klein.onl")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(
                MoshiConverterFactory
                    .create(
                        Moshi.Builder()
                            .add(KotlinJsonAdapterFactory())
                            .build()
                    )
            ).build()
            .create(ESNAPI::class.java)
        return@lazy ApiDeveloperRepository(api)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repository.getDevs()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                Log.d("MainScreen", it.joinToString(separator = ","))
            }
    }

}