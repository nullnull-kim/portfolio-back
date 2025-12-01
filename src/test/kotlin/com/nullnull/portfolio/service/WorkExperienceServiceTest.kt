package com.nullnull.portfolio.service

import com.nullnull.portfolio.domain.Profile
import com.nullnull.portfolio.domain.WorkExperience
import com.nullnull.portfolio.repository.ProfileRepository
import com.nullnull.portfolio.repository.WorkExperienceRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest
import org.springframework.context.annotation.Import
import java.time.LocalDate

@DataJpaTest
@Import(WorkExperienceService::class)
class WorkExperienceServiceTest @Autowired constructor(
    private val workExperienceService: WorkExperienceService,
    private val workExperienceRepository: WorkExperienceRepository,
    private val profileRepository: ProfileRepository,
    ) {

    @Test
    fun `profileId로 WorkExperience를 생성하면 DB에 저장되고 필드가 정상 매핑된다`() {
        // given
        val profile = saveProfile()

        val command = CreateWorkExperienceCommand(
            companyName = "BK S&P",
            companyHeadline = "하나은행 대외계 펌뱅킹 운영·개발",
            title = "Backend Engineer",
            startDate = LocalDate.of(2023, 4, 10),
            endDate = null,
            isCurrent = true,
            location = "인천",
            techStack = "Proframe*C, Neoworks*Java, Xframe*javascript",
            summary = "펌뱅킹 대외계 시스템 운영 및 신규 서비스 개발",
            achievements = "- ISO20022 기반 대외 전문 설계 및 MT→MX 변환 모듈 개발\n- 쿠팡 셀러월렛 및 빠른정산 연계 서비스 개발",
            sortOrder = 1,
        )

        // when
        val saved = workExperienceService.create(profile.id!!, command)

        // then
        val found = workExperienceRepository.findById(saved.id!!).orElseThrow()

        assertThat(found.id).isNotNull()
        assertThat(found.profile.id).isEqualTo(profile.id)
        assertThat(found.companyName).isEqualTo(command.companyName)
        assertThat(found.companyHeadline).isEqualTo(command.companyHeadline)
        assertThat(found.title).isEqualTo(command.title)
        assertThat(found.startDate).isEqualTo(command.startDate)
        assertThat(found.endDate).isEqualTo(command.endDate)
        assertThat(found.isCurrent).isEqualTo(command.isCurrent)
        assertThat(found.location).isEqualTo(command.location)
        assertThat(found.techStack).isEqualTo(command.techStack)
        assertThat(found.summary).isEqualTo(command.summary)
        assertThat(found.achievements).isEqualTo(command.achievements)
        assertThat(found.sortOrder).isEqualTo(command.sortOrder)

        // BaseEntity 공통 필드도 체크
        assertThat(found.createdAt).isNotNull()
        assertThat(found.updatedAt).isNotNull()
        assertThat(found.isEnabled).isTrue()
    }

    // --- 테스트용 Introduction 저장 헬퍼 ---

    private fun saveProfile(): Profile {
        val profile = Profile(
            title = "김태영 백엔드 개발자",
            summary = "6년차 펌뱅킹 및 핀테크 백엔드 개발자",
            contactEmail = "test@example.com",
            github = null,
            blog = null,
            profileImageUrl = null,
        )
        return profileRepository.save(profile)
    }
}