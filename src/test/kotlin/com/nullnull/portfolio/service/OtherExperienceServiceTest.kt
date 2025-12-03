package com.nullnull.portfolio.service

import org.assertj.core.api.Assertions.*
import com.nullnull.portfolio.domain.OtherExperience
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

@DataJpaTest
@Import(OtherExperienceService::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OtherExperienceServiceTest @Autowired constructor(
    private val otherExperienceService: OtherExperienceService,
    private val otherExperienceRepository: OtherExperienceRepository,
    private val profileRepository: ProfileRepository,
){

    private lateinit var profile: Profile
    private lateinit var command1: CreateOtherExperienceCommand


    @BeforeAll
    fun setup() {
        profile = Profile(
            title = "김태영 백엔드 개발자",
            summary = "6년차 펌뱅킹 및 핀테크 백엔드 개발자",
            contactEmail = "test@example.com",
            github = null,
            blog = null,
            profileImageUrl = null,
        )
        profileRepository.save(profile)

        command1 = CreateOtherExperienceCommand(
            title = "정보통신산업진흥원 주관 다국적 해커톤",
            role = null,
            startedAt = LocalDate.of(2017, 6, 26),
            endedAt = LocalDate.of(2017, 6, 27),
            description = "외국학생과 한 팀을 이뤄 ‘홍익인간 기술’을 주제로 ICTㆍ친환경 그린테크ㆍ융합테크 등 4차 산업혁명 시대의 전반적인 영역에 관한 경연 및 시제품 제작"
        )
    }

    @Test
    fun `otherExperience를 create할 때 profile의 isEnabled가 false이면 에러가 발생한다`() {

        profile.disable()
        profileRepository.save(profile)

        // when & then
        assertThatThrownBy{
            otherExperienceService.create(profile.id!!, command1)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
//            .hasMessageContaining("profile is disabled")
    }
}