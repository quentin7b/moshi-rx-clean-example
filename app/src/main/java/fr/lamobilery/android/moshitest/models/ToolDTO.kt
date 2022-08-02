package fr.lamobilery.android.moshitest.models

import com.squareup.moshi.JsonClass
import fr.lamobilery.android.contracts.models.Tool

@JsonClass(generateAdapter = true)
data class ToolDTO(override val id: Int, override val name: String): Tool