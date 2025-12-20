package com.nullnull.portfolio.service

import com.nullnull.portfolio.domain.Profile
import com.nullnull.portfolio.repository.CertificationRepository
import com.nullnull.portfolio.repository.ProfileRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import java.time.LocalDate


@Import(CertificationService::class)
class CertificationServiceTest @Autowired constructor(
    private val profileRepository: ProfileRepository,
    private val certificationService: CertificationService,
    private val certificationRepository: CertificationRepository
): IntegrationTestSupport() {
    private lateinit var profile: Profile
    private lateinit var command0: CreateCertificationCommand
    private lateinit var command1: CreateCertificationCommand

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

        command0 = CreateCertificationCommand(
            name = "SQLD",
            issuedBy = "한국데이터베이스진흥센터",
            issuedAt = LocalDate.of(2024, 6,21),
        )

        command1 = CreateCertificationCommand(
            name = "정보처리기사",
            issuedBy = "한국산업인력공단",
            issuedAt = LocalDate.of(2018, 8,17),
        )
    }

    @Test
    fun `Profile의 enabled가 false이면 craete를 수행할 수 없다`() {
        // given
        profile.disable()
        profileRepository.save(profile)

        // when & then
        assertThatThrownBy{
            certificationService.create(profile.id!!, command0)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `Certification 생성 후 조회하면 동일한 내용이 반환된다`() {
        // given
        val created = certificationService.create(profile.id!!, command0)

        // when
        val found = certificationRepository.findById(created.id!!).orElseThrow()

        // then
        assertThat(found).isNotNull
        assertThat(found.id).isEqualTo(created.id)
        assertThat(found.name).isEqualTo(command0.name)
        assertThat(found.issuedBy).isEqualTo(command0.issuedBy)
        assertThat(found.issuedDate).isEqualTo(command0.issuedAt)

    }

    @Test
    fun `Profile의 enabled가 false이면 getAllByProfile을 수행할 수 없다`(){

        // given
        profile.disable()
        profileRepository.save(profile)

        // when & then
        assertThatThrownBy {
            certificationService.getAllByProfile(profile.id!!)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `Skills의 isEnabled가 true인 entity만 조회된다`(){
        // given
        profile.enable()
        profileRepository.save(profile)
        certificationService.create(profile.id!!, command0)
        val entity_id : Long = certificationService.create(profile.id!!, command0).id!!

        //when
        val created = certificationRepository.findById(entity_id!!).orElseThrow()
        created.disable()
        certificationRepository.save(created)

        // then
        val found = certificationService.getAllByProfile(profile.id!!)
        assertThat(found).hasSize(1)
    }



}