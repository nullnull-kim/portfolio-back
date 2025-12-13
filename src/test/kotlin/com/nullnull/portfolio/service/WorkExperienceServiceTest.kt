package com.nullnull.portfolio.service

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import com.nullnull.portfolio.domain.Profile
import com.nullnull.portfolio.domain.WorkExperience
import com.nullnull.portfolio.repository.ProfileRepository
import com.nullnull.portfolio.repository.WorkExperienceRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
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
        assertThat(found.created).isNotNull()
        assertThat(found.updated).isNotNull()
        assertThat(found.enabled).isTrue()
    }

    @Test
    fun `getAllByProfile - profile과 workExperience 모두 enabled=true이면 정상 조회된다`() {
        // given
        val profile = saveProfile()

        profile.enable()

        val w1 = workExperienceRepository.save(
            WorkExperience(
                profile = profile,
                companyName = "회사1",
                title = "주니어 백엔드",
                startDate = LocalDate.of(2020, 1, 1),
                isCurrent = false,
                sortOrder = 1,
            )
        )

        val w2 = workExperienceRepository.save(
            WorkExperience(
                profile = profile,
                companyName = "회사2",
                title = "시니어 백엔드",
                startDate = LocalDate.of(2021, 1, 1),
                isCurrent = true,
                sortOrder = 2,
            )
        )

        // when
        val result = workExperienceService.getAllByProfile(profile.id!!)

        // then
        assertThat(result).hasSize(2)
        assertThat(result)
            .extracting("companyName")
            .containsExactly("회사1", "회사2")
    }

    @Test
    fun `getAllByProfile - profile이 disabled면 예외가 발생한다`() {
        // given
        val profile = saveProfile()
        profile.disable()
        profileRepository.save(profile)

        // when & then
        assertThatThrownBy {
            workExperienceService.getAllByProfile(profile.id!!)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("profile is disabled")
    }

    /**
     * profile은 enabled = true지만 WorkExperience는 enabled=false이면 조회되지 않아야 함
     */
    @Test
    fun `getAllByProfile - profile은 enabled지만 workExperience가 disabled면 조회되지 않는다`() {
        // given
        val profile = saveProfile()

        val enabled = saveWorkExperience(
            profile = profile,
            companyName = "활성 회사",
            title = "백엔드",
            start = LocalDate.of(2020, 1, 1),
            sort = 1
        )

        val disabled = saveWorkExperience(
            profile = profile,
            companyName = "비활성 회사",
            title = "백엔드",
            start = LocalDate.of(2019, 1, 1),
            sort = 2
        )

        disabled.disable()
        workExperienceRepository.save(disabled)

        // when
        val result = workExperienceService.getAllByProfile(profile.id!!)

        // then
        assertThat(result).hasSize(1)
        assertThat(result[0].companyName).isEqualTo("활성 회사")
        assertThat(result[0].id).isEqualTo(enabled.id)
    }

    // --- 테스트용 ---

    private fun saveProfile(): Profile {
        val profile = Profile(
            title = "김태영 백엔드 개발자",
            summary = "6년차 백엔드 개발자",
            name = "김태영",
            contactEmail = "test@example.com",
            github = "https://github.com/nullnull-kim",
            blog = "https://nullnull-kim.github.io/",
            birthday = LocalDate.of(1993,1,23),
            profileImageUrl = null,
        )
        return profileRepository.save(profile)
    }

    private fun saveWorkExperience(
        profile: Profile,
        companyName: String,
        title: String,
        start: LocalDate,
        end: LocalDate? = null,
        isCurrent: Boolean = false,
        sort: Int? = null,
        techStack: String? = null,
        summary: String? = null,
    ): WorkExperience {
        val exp = WorkExperience(
            profile = profile,
            companyName = companyName,
            companyHeadline = null,
            title = title,
            startDate = start,
            endDate = end,
            isCurrent = isCurrent,
            location = null,
            techStack = techStack,
            summary = summary,
            achievements = null,
            sortOrder = sort,
        )
        return workExperienceRepository.save(exp)
    }
}