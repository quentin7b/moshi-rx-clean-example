package fr.lamobilery.android.contracts.repositories

import fr.lamobilery.android.contracts.models.Developer
import io.reactivex.rxjava3.core.Observable

interface DeveloperRepository {

    fun getDevs() : Observable<List<Developer>>

}