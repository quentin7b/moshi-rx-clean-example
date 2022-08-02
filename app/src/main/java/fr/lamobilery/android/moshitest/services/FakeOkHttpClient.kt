package fr.lamobilery.android.moshitest.services

import okhttp3.*

class FakeOkHttpClientBuilder {

    companion object {

        fun build(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(MockInterceptor()).build()
        }

    }
}

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url().uri().toString()
        val responseString = when {
            uri.endsWith(ESNAPIEndpoints.EMPLOYEES) -> fakeListOfDevelopers
            else -> ""
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(
                ResponseBody.create(
                    MediaType.parse("application/json"),
                    responseString.toByteArray()
                )
            )
            .addHeader("content-type", "application/json")
            .build()
    }

}

const val fakeListOfDevelopers = """
[{
	"id": 1,
	"name": "Axel",
    "tools": [{
        "id": 1,
        "name": "Android Studio"
    }],
    "projects": [{
        "id": 1,
        "name": "Bisom App"
    }]
}, {
	"id": 2,
	"name": "Philippe",
    "tools": [{
        "id": 1,
        "name": "Android Studio"
    }, {
        "id": 2,
        "name": "Samsung Tablet"
    }],
    "projects": [{
        "id": 1,
        "name": "Bisom App"
    }]
}, {
	"id": 3,
	"name": "Quentin",
    "tools": [],
    "projects": []
}]
"""