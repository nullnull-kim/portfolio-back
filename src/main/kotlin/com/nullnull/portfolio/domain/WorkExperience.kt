package com.nullnull.portfolio.domain

import com.nullnull.portfolio.domain.common.BaseEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
class WorkExperience(
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    val profile: Profile,

    @Column(name = "company_name", nullable = false)
    var companyName: String,

    @Column(name = "company_headline", length = 300)
    var companyHeadline: String? = null,   // "300만 유저, 2000여 병원..." 같은 한 줄 설명

    @Column(name = "title", length = 200, nullable = false)
    var title: String,                     // Backend Engineer 등

    @Column(name = "start_date", nullable = false)
    var startDate: LocalDate,

    @Column(name = "end_date")
    var endDate: LocalDate? = null,

    @Column(name = "is_current", nullable = false)
    var isCurrent: Boolean = false,

    @Column(name = "location", length = 100)
    var location: String? = null,

    @Column(name = "tech_stack", length = 500)
    var techStack: String? = null,         // "Kotlin, Spring Boot, Oracle..."

    @Column(name = "summary", length = 400)
    var summary: String? = null,           // 역할 한 줄 요약 (선택)

    @Lob
    @Column(name = "achievements")
    var achievements: String? = null,      // 여러 줄 텍스트로 불릿 성과 저장

    @Column(name = "sort_order")
    var sortOrder: Int? = null,            // Work Experience 섹션 표시 순서
) : BaseEntity() {
}