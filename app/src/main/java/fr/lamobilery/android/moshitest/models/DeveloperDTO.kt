package fr.lamobilery.android.moshitest.models

import com.squareup.moshi.JsonClass
import fr.lamobilery.android.contracts.models.Developer

@JsonClass(generateAdapter = true)
data class DeveloperDTO(
    override val id: Int,
    override val name: String,
    override val tools: List<ToolDTO>,
    override val projects: List<ProjectDTO>?
) : Developer