package com.example.cheas_stoeckli.data.Fake

import com.example.cheas_stoeckli.domain.models.TeamMember
import com.example.cheas_stoeckli.ui.enums.MemberPosition

val teamList = listOf(
    TeamMember(
        name = "Hans Müller",
        description = "Käsemeister mit 25 Jahren Erfahrung, liebt würzigen Bergkäse.",
        imgDownloadPath = "https://picsum.photos/seed/chef_hans/300/300",
        imgPath = "team/chef_hans.jpg",
        position = MemberPosition.CHEF
    ),
    TeamMember(
        name = "Claudia Steiner",
        description = "Leitet Produktion & Qualitätssicherung – bekannt für ihr Adlerauge.",
        imgDownloadPath = "https://picsum.photos/seed/chef_claudia/300/300",
        imgPath = "team/chef_claudia.jpg",
        position = MemberPosition.CHEF
    ),
    TeamMember(
        name = "Philipp Berger",
        description = "Reiftag-Guru, sorgt für das perfekte Klima im Käsekeller.",
        imgDownloadPath = "https://picsum.photos/seed/philipp/300/300",
        imgPath = "team/philipp.jpg",
        position = MemberPosition.AN
    ),
    TeamMember(
        name = "Anna Schmidt",
        description = "Front-of-House – kennt jeden Stammkunden und deren Lieblingskäse.",
        imgDownloadPath = "https://picsum.photos/seed/anna/300/300",
        imgPath = "team/anna.jpg",
        position = MemberPosition.AN
    ),
    TeamMember(
        name = "Lukas Weber",
        description = "Logistik-Ass: hält Lager & Lieferungen stets auf Kurs.",
        imgDownloadPath = "https://picsum.photos/seed/lukas/300/300",
        imgPath = "team/lukas.jpg",
        position = MemberPosition.AN
    ),
    TeamMember(
        name = "Sophie Keller",
        description = "Marketing-Talent, kreiert die hübschen Social-Media-Posts.",
        imgDownloadPath = "https://picsum.photos/seed/sophie/300/300",
        imgPath = "team/sophie.jpg",
        position = MemberPosition.AN
    ),
    TeamMember(
        name = "Jonas Baumann",
        description = "Kein Tag isch wie de anderi...aber jede Tag bringt wieder neui Erkentnisse, Erfahrige und Erläbniss. Neugierig bliibe und offe sii für neui Idee.",
        imgDownloadPath = "https://picsum.photos/seed/jonas/300/300",
        imgPath = "team/jonas.jpg",
        position = MemberPosition.AN
    )

)

val fakeMember = TeamMember(
    name = "Jonas Baumann",
    description = "Allrounder in Produktion & Verkauf, springt überall ein.",
    imgDownloadPath = "https://picsum.photos/seed/jonas/300/300",
    imgPath = "team/jonas.jpg",
    position = MemberPosition.AN
)