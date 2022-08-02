package fr.lamobilery.android.moshitest.repositories

import fr.lamobilery.android.contracts.models.Developer
import fr.lamobilery.android.contracts.repositories.DeveloperRepository
import fr.lamobilery.android.moshitest.services.ESNAPI
import io.reactivex.rxjava3.core.Observable

class ApiDeveloperRepository(
    private val api: ESNAPI
) : DeveloperRepository {
    override fun getDevs(): Observable<List<Developer>> {
        return api.getEmployees().flatMap { devs ->
            Observable.fromIterable(devs)
                .map { it as Developer }
                .toList()
                .toObservable()
        }
    }
}