package com.example.cheas_stoeckli.domain.mappers

import com.example.cheas_stoeckli.domain.models.FirebaseTeamMember
import com.example.cheas_stoeckli.domain.models.TeamMember
import com.example.cheas_stoeckli.ui.enums.MemberPosition

object TeamMemberMapper {

    fun toApp(dto: FirebaseTeamMember): TeamMember {
        return TeamMember(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            imgDownloadPath = dto.imgDownloadPath,
            imgPath = dto.imgPath,
            position = MemberPosition.convertRawValue(dto.position) ?: MemberPosition.NONE,
            createdAt = dto.createdAt
        )
    }

    fun toFirebase(teamMember: TeamMember): FirebaseTeamMember {
        return FirebaseTeamMember(
            id = teamMember.id,
            name = teamMember.name,
            description = teamMember.description,
            imgDownloadPath = teamMember.imgDownloadPath,
            imgPath = teamMember.imgPath,
            position = teamMember.position.rawValue,
            createdAt = teamMember.createdAt
        )
    }
}