package fr.lamobilery.android.moshitest.services

import fr.lamobilery.android.contracts.models.Developer
import fr.lamobilery.android.moshitest.models.DeveloperDTO
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

class ESNAPIEndpoints {
    companion object {
        const val EMPLOYEES = "developers"
    }
}

interface ESNAPI {

    @GET(ESNAPIEndpoints.EMPLOYEES)
    fun getEmployees(): Observable<List<DeveloperDTO>>

}