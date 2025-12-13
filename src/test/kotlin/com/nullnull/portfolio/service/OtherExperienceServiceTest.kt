package com.nullnull.portfolio.service

import org.assertj.core.api.Assertions.*
import com.nullnull.portfolio.domain.Profile
import com.nullnull.portfolio.repository.OtherExperienceRepository
import com.nullnull.portfolio.repository.ProfileRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import java.time.LocalDate

@Import(OtherExperienceService::class)
class OtherExperienceServiceTest @Autowired constructor(
    private val otherExperienceService: OtherExperienceService,
    private val otherExperienceRepository: OtherExperienceRepository,
    private val profileRepository: ProfileRepository,
): IntegrationTestSupport(){

    private lateinit var profile: Profile
    private lateinit var command0: CreateOtherExperienceCommand
    private lateinit var command1: CreateOtherExperienceCommand


    @BeforeAll
    fun setup() {
        profile = Profile(
            slug = "my",
            name = "김태영",
            contactEmail = "test@example.com",
            github = "https://github.com/nullnull-kim",
            blog = "https://nullnull-kim.github.io/",
            birthday = LocalDate.of(1993,1,23),
            profileImageUrl = null,
        )
        profileRepository.save(profile)

        command0 = CreateOtherExperienceCommand(
            title = "정보통신산업진흥원 주관 다국적 해커톤",
            role = null,
            startedAt = LocalDate.of(2017, 6, 26),
            endedAt = LocalDate.of(2017, 6, 27),
            description = "외국학생과 한 팀을 이뤄 ‘홍익인간 기술’을 주제로 ICTㆍ친환경 그린테크ㆍ융합테크 등 4차 산업혁명 시대의 전반적인 영역에 관한 경연 및 시제품 제작"
        )

        command1 = CreateOtherExperienceCommand(
            title = "인프런, '재고시스템으로 알아보는 동시성이슈 해결방법' 수료",
            role = null,
            startedAt = LocalDate.of(2022, 8, 13),
            endedAt = LocalDate.of(2022, 8, 24),
            description = "재고시스템을 작접 만들면서 Application Level, Database Lock, Redis Distributed Lock을 통해 동시성 이슈를 해결하는 방법을 학습"
        )
    }

    @Test
    fun `create 정상 동작테스트`() {
        profile.disable()
        profileRepository.save(profile)

        // when & then
        assertThatThrownBy{
            otherExperienceService.create(profile.id!!, command0)
        }
            .isInstanceOf(IllegalArgumentException::class.java)

        profile.enable()
        profileRepository.save(profile)

        val enabledProfile = otherExperienceService.create(profile.id!!, command0)

        val found = otherExperienceRepository.findById(enabledProfile.id!!).orElseThrow()

        assertThat(found.title).isEqualTo(command0.title)
        assertThat(found.description).isEqualTo(command0.description)
        assertThat(found.startedAt).isEqualTo(command0.startedAt)
        assertThat(found.endedAt).isEqualTo(command0.endedAt)
        assertThat(found.role).isEqualTo(command0.role)
    }

    @Test
    fun `getAllByProfile 테스트`(){
        profile.disable()
        profileRepository.save(profile)
        assertThatThrownBy{
            otherExperienceService.getAllByProfile(profile.id!!)
        }
            .isInstanceOf(IllegalArgumentException::class.java)

        profile.enable()
        profileRepository.save(profile)

        otherExperienceService.create(profile.id!!, command0)
        otherExperienceService.create(profile.id!!, command1)

        val otherExperiences = otherExperienceService.getAllByProfile(profile.id!!)

        assertThat(otherExperiences).hasSize(2)
        assertThat(otherExperiences[0].title).isEqualTo(command0.title)
        assertThat(otherExperiences[1].title).isEqualTo(command1.title)


    }
}