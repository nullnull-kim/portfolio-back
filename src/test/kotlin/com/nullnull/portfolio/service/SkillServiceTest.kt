package com.nullnull.portfolio.service

import org.assertj.core.api.Assertions.*
import com.nullnull.portfolio.domain.Profile
import com.nullnull.portfolio.repository.ProfileRepository
import com.nullnull.portfolio.repository.SkillRepository
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import java.time.LocalDate

@Import(SkillsService::class)
class SkillServiceTest @Autowired constructor(
    private val skillsService: SkillsService,
    private val skillRepository: SkillRepository,
    private val profileRepository: ProfileRepository
): IntegrationTestSupport() {

    private lateinit var profile: Profile
    private lateinit var command0: SkillCommand
    private lateinit var command1: SkillCommand
    private lateinit var command2: SkillCommand

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

        command0 = SkillCommand(
            category = "Backend",
            items = "Java/SpringBoot, Kotlin/SpringBoot, Proframe/C",
        )

        command1 = SkillCommand(
            category = "Frontend",
            items = "JSP, HTML/CSS, Bootstrap, Vue3"
        )

        command2 = SkillCommand(
            category = "DB",
            items = "Oracle, MySQL"
        )
    }

    @Test
    fun `Profile의 isEnabled가 false이면 craete를 수행할 수 없다`() {
        profile.disable()
        profileRepository.save(profile)

        assertThatThrownBy{
            skillsService.create(profile.id!!, command0)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `Skills 생성 후 조회하면 동일한 내용이 반환된다`() {
        // given
        val created = skillsService.create(profile.id!!, command0)

        // when
        val found = skillRepository.findById(created.id!!).orElseThrow()

        // then
        assertThat(found).isNotNull
        assertThat(found.id).isEqualTo(created.id)
        assertThat(found.category).isEqualTo(command0.category)
        assertThat(found.items).isEqualTo(command0.items)

    }

    @Test
    fun `Profile의 isEnabled가 false이면 getAllByProfile을 수행할 수 없다`(){

        // given
        profile.disable()
        profileRepository.save(profile)

        // when & then
        assertThatThrownBy {
            skillsService.getAllByProfile(profile.id!!)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `Skills의 isEnabled가 true인 entity만 조회된다`(){
        // given
        profile.enable()
        profileRepository.save(profile)
        skillsService.create(profile.id!!, command1)
        skillsService.create(profile.id!!, command2)
        val entity_id : Long = skillsService.create(profile.id!!, command0).id!!

        //when
        val created = skillRepository.findById(entity_id!!).orElseThrow()
        created.disable()
        skillRepository.save(created)

        // then
        val found = skillsService.getAllByProfile(profile.id!!)
        assertThat(found).hasSize(2)
    }
}