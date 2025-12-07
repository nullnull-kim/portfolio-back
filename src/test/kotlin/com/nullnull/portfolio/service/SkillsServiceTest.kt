package com.nullnull.portfolio.service

import org.assertj.core.api.Assertions.*
import com.nullnull.portfolio.domain.Profile
import com.nullnull.portfolio.repository.ProfileRepository
import com.nullnull.portfolio.repository.SkillsRepository
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@Import(SkillsService::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SkillsServiceTest @Autowired constructor(
    private val skillsService: SkillsService,
    private val skillsRepository: SkillsRepository,
    private val profileRepository: ProfileRepository
) {

    private lateinit var profile: Profile
    private lateinit var command0: SkillsCommand
    private lateinit var command1: SkillsCommand
    private lateinit var command2: SkillsCommand

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

        command0 = SkillsCommand(
            category = "Backend",
            items = "Java/SpringBoot, Kotlin/SpringBoot, Proframe/C",
        )

        command1 = SkillsCommand(
            category = "Frontend",
            items = "JSP, HTML/CSS, Bootstrap, Vue3"
        )

        command2 = SkillsCommand(
            category = "DB",
            items = "Oracle, MySQL"
        )
    }

    @Test
    fun `create 테스트`() {
        //profile의 isEnabled가 false이면 craete 할 수 없다
        profile.disable()
        profileRepository.save(profile)

        assertThatThrownBy{
            skillsService.create(profile.id!!, command0)
        }
            .isInstanceOf(IllegalArgumentException::class.java)

        profile.enable()
        profileRepository.save(profile)

        val created = skillsService.create(profile.id!!, command0)
        val found = skillsRepository.findById(profile.id!!).orElseThrow()

        assertThat(found).isNotNull
        assertThat(found.id).isEqualTo(created.id)
        assertThat(found.category).isEqualTo(command0.category)
        assertThat(found.items).isEqualTo(command0.items)

    }

    @Test
    fun `Profile의 isEnabled가 false이면 craete 할 수 없다`(){

        // given
        profile.disable()
        profileRepository.save(profile)

        // when & then
        assertThatThrownBy {
            skillsService.getAllByProfile(profile.id!!)
        }
            .isInstanceOf(IllegalArgumentException::class.java)

        // Skills의 isEnabled가 true인 entity만 조회된다
        profile.enable()
        profileRepository.save(profile)

        skillsService.create(profile.id!!, command1)
        skillsService.create(profile.id!!, command2)

        // create 후 비활성화
        val entity_id = skillsService.create(profile.id!!, command0).id
        val created = skillsRepository.findById(entity_id!!).orElseThrow()
        created.disable()
        skillsRepository.save(created)

        // 3건을 create 수행했지만 2건만 조회된다
        val found = skillsService.getAllByProfile(profile.id!!)
        assertThat(found).hasSize(2)
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
        val created = skillsRepository.findById(entity_id!!).orElseThrow()
        created.disable()
        skillsRepository.save(created)

        // then
        val found = skillsService.getAllByProfile(profile.id!!)
        assertThat(found).hasSize(2)
    }
}