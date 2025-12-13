package com.nullnull.portfolio.service

import com.nullnull.portfolio.domain.Profile
import com.nullnull.portfolio.repository.ProfileRepository
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import java.time.LocalDate

@Import(ProfileService::class)
class ProfileServiceTest @Autowired constructor(
    val profileService: ProfileService,
    val profileRepository: ProfileRepository
): IntegrationTestSupport(){

    lateinit var profile: Profile
    lateinit var command0: CreateProfileCommand

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

        command0 = CreateProfileCommand(
            slug = "my",
            name = "김태영",
            contactEmail = "test@example.com",
            github = "https://github.com/nullnull-kim",
            blog = "https://nullnull-kim.github.io/",
            birthday = LocalDate.of(1993,1,23),
            profileImageUrl = null,
        )
    }

    @Test
    fun `entity를 생성하고 조회하면 동일한 내용이 반환된다`() {

        // given
        val created = profileService.create(command0)

        // when
        val found = profileRepository.findById(created.id!!).orElseThrow()

        // then
        assertThat(found).isNotNull
        assertThat(found.name).isEqualTo(created.name)
        assertThat(found.contactEmail).isEqualTo(created.contactEmail)
        assertThat(found.birthday).isEqualTo(created.birthday)
        assertThat(found.profileImageUrl).isEqualTo(created.profileImageUrl)
    }



    @Test
    fun `entity의 enabled가 false이면 getByProfileId를 수행할 수 없다`() {
        // given
        profile.disable()
        val created = profileRepository.save(profile)

        // when & then
        assertThatThrownBy {
            profileService.getByProfileId(created.id!!)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `getByProfileId는 profile을 반환한다`() {
        // given
        profile.enable()
        val created = profileRepository.save(profile)

        // when
        val found = profileService.getByProfileId(created.id!!)

        // then
        assertThat(found).isNotNull
        assertThat(found.slug).isEqualTo(created.slug)
        assertThat(found.name).isEqualTo(created.name)
        assertThat(found.contactEmail).isEqualTo(created.contactEmail)
        assertThat(found.birthday).isEqualTo(created.birthday)
        assertThat(found.profileImageUrl).isEqualTo(created.profileImageUrl)
        assertThat(found.slug).isEqualTo(created.slug)

    }

    @Test
    fun `getBySlug는 profile을 반환한다`() {
        // given
        val created = profileRepository.save(profile)

        // when
        val found = profileService.getBySlug(created.slug!!)

        // then
        assertThat(found).isNotNull
        assertThat(found.slug).isEqualTo(created.slug)
        assertThat(found.name).isEqualTo(created.name)
        assertThat(found.contactEmail).isEqualTo(created.contactEmail)
        assertThat(found.birthday).isEqualTo(created.birthday)
        assertThat(found.profileImageUrl).isEqualTo(created.profileImageUrl)
        assertThat(found.slug).isEqualTo(created.slug)

    }
}