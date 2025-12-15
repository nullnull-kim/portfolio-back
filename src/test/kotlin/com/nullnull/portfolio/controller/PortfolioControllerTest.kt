package com.nullnull.portfolio.controller

import com.nullnull.portfolio.controller.common.CommonControllerTest
import com.nullnull.portfolio.domain.Certification
import com.nullnull.portfolio.domain.Education
import com.nullnull.portfolio.domain.Profile
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc

import com.nullnull.portfolio.service.CertificationService
import com.nullnull.portfolio.service.EducationService
import com.nullnull.portfolio.service.OtherExperienceService
import com.nullnull.portfolio.service.SkillsService
import com.nullnull.portfolio.service.WorkExperienceService
import org.junit.jupiter.api.BeforeEach
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get
import java.time.LocalDate

@WebMvcTest(PortfolioController::class)
class PortfolioControllerTest: CommonControllerTest() {

    @Autowired lateinit var mockMvc: MockMvc

    @MockBean private lateinit var educationService: EducationService
    @MockBean private lateinit var certificationService: CertificationService
    @MockBean private lateinit var otherExperienceService: OtherExperienceService
    @MockBean private lateinit var skillsService: SkillsService
    @MockBean private lateinit var workExperienceService: WorkExperienceService

    private lateinit var profile: Profile
    private var profileId: Long = 1L

    @BeforeEach
    fun setUp() {
        profile = Profile(
            slug = "test",
            name = "김태영",
            contactEmail = "test@example.com",
            github = "https://github.com/nullnull-kim",
            blog = "https://nullnull-kim.github.io/",
            birthday = LocalDate.of(1993, 1, 23),
            profileImageUrl = null
        ).apply { enable() }
    }

    @Test
    fun `profileId로 조회하면 educations가 출력된다`(){
        // given
        val edu1 = Education(
            profile = profile,
            schoolName = "선문대학교",
            started = "2011-03",
            ended = "2018-08",
            major = "컴퓨터공학"
        )
        val edu2 = Education(
            profile = profile,
            schoolName = "인천고등학교",
            started = "2008-02",
            ended = "2011-02",
            major = "인문계"
        )
        given(educationService.getAllByProfile(profileId)).willReturn(listOf(edu1, edu2))

        // when & then
        mockMvc.get("/portfolio/{profileId}/educations", profileId) {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentTypeCompatibleWith(MediaType.APPLICATION_JSON) }

            jsonPath("$.length()") { value(2) }

            jsonPath("$[0].schoolName") { value("선문대학교") }
            jsonPath("$[0].started") { value("2011-03") }
            jsonPath("$[0].ended") { value("2018-08") }
            jsonPath("$[0].major") { value("컴퓨터공학") }

            jsonPath("$[1].schoolName") { value("인천고등학교") }
            jsonPath("$[1].started") { value("2008-02") }
            jsonPath("$[1].ended") { value("2011-02") }
            jsonPath("$[1].major") { value("인문계") }
        }
    }

    @Test
    fun `profileId로 조회하면 certifications가 출력된다`(){
        // given
        val cer1 = Certification(
            profile = profile,
            name = "SQLD",
            issuedBy = "한국데이터베이스진흥센터",
            issuedAt = LocalDate.of(2024, 6,21),
        )
        val cer2 = Certification(
            profile = profile,
            name = "정보처리기사",
            issuedBy = "한국산업인력공단",
            issuedAt = LocalDate.of(2018, 8,17),
        )
        given(certificationService.getAllByProfile(profileId)).willReturn(listOf(cer1, cer2))

        // when & then
        mockMvc.get("/portfolio/{profileId}/certifications", profileId) {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentTypeCompatibleWith(MediaType.APPLICATION_JSON) }

            jsonPath("$.length()") {value(2)}

            jsonPath("$[0].name") { value("SQLD") }
            jsonPath("$[0].issuedBy") { value("한국데이터베이스진흥센터")}
            jsonPath("$[0].issuedAt") { value("2024-06-21")}

            jsonPath("$[1].name") { value("정보처리기사") }
            jsonPath("$[1].issuedBy") { value("한국산업인력공단")}
            jsonPath("$[1].issuedAt") { value("2018-08-17")}
        }
    }
}