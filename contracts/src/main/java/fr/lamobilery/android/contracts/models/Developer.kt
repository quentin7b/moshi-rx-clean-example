package fr.lamobilery.android.contracts.models

interface Developer {
    val id: Int
    val name: String
    val tools: List<Tool>
    val projects: List<Project>?
}