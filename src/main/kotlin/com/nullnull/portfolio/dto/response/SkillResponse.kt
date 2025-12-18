package com.nullnull.portfolio.dto.response

import com.nullnull.portfolio.domain.Skill

data class SkillResponse(
    val category: String,
    val items: String
) {
    companion object {
        fun toResponse(skill : Skill) : SkillResponse = SkillResponse(
            category = skill.category,
            items = skill.items
        )
    }
}
