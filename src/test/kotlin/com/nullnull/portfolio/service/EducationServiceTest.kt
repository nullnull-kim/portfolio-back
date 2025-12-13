package com.nullnull.portfolio.service

import com.nullnull.portfolio.domain.Profile
import com.nullnull.portfolio.repository.EducationRepository
import com.nullnull.portfolio.repository.ProfileRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import java.time.LocalDate
import java.time.YearMonth

@Import(EducationService::class)
class EducationServiceTest @Autowired constructor(
    private val profileRepository: ProfileRepository,
    private val educationRepository: EducationRepository,
    private val educationService: EducationService
): IntegrationTestSupport(){
    private lateinit var profile: Profile
    private lateinit var command0: CreateEducationCommand
    private lateinit var command1: CreateEducationCommand

    @BeforeAll
    fun setup(){
        profile = Profile(
            slug = "test",
            name = "김태영",
            contactEmail = "test@example.com",
            github = "https://github.com/nullnull-kim",
            blog = "https://nullnull-kim.github.io/",
            birthday = LocalDate.of(1993,1,23),
            profileImageUrl = null,
        )
        profileRepository.save(profile)

        command0 = CreateEducationCommand(
            schoolName = "선문대학교",
            startedAt = YearMonth.of(2011, 3).toString(),
            endedAt = YearMonth.of(2018, 8).toString(),
            major = "컴퓨터공학"
        )

        command1 = CreateEducationCommand(
            schoolName = "인천고등학교",
            startedAt = YearMonth.of(2008, 2).toString(),
            endedAt = YearMonth.of(2011, 2).toString(),
            major = "인문계"
        )
    }

    @Test
    fun `Profile의 enabled가 false이면 craete를 수행할 수 없다`() {
        // given
        profile.disable()
        profileRepository.save(profile)

        // when & then
        assertThatThrownBy{
            educationService.create(profile.id!!, command0)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `entity를 생성하고 조회하면 동일한 내용이 반환된다`() {
        // given
        val created = educationService.create(profile.id!!, command0)

        // when
        val found = educationRepository.findById(created.id!!).orElseThrow()

        // then
        assertThat(found).isNotNull
        assertThat(found.id).isEqualTo(created.id)
        assertThat(found.schoolName).isEqualTo(command0.schoolName)
        assertThat(found.started).isEqualTo(command0.startedAt)
        assertThat(found.ended).isEqualTo(command0.endedAt)
        assertThat(found.major).isEqualTo(command0.major)

    }

    @Test
    fun `Profile의 enabled가 false이면 getAllByProfile을 수행할 수 없다`(){
        // given
        profile.disable()
        profileRepository.save(profile)

        // when & then
        assertThatThrownBy {
            educationService.getAllByProfile(profile.id!!)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `entity의 enabled가 true인 목록이 조회된다`(){
        // given
        profile.enable()
        profileRepository.save(profile)
        educationService.create(profile.id!!, command0)
        val entity_id : Long = educationService.create(profile.id!!, command0).id!!

        //when
        val created = educationRepository.findById(entity_id!!).orElseThrow()
        created.disable()
        educationRepository.save(created)

        // then
        val found = educationService.getAllByProfile(profile.id!!)
        assertThat(found).hasSize(1)
    }
}