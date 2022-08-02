package fr.lamobilery.android.moshitest.models

import com.squareup.moshi.JsonClass
import fr.lamobilery.android.contracts.models.Project

@JsonClass(generateAdapter = true)
data class ProjectDTO(override val id: Int, override val name: String) : Project