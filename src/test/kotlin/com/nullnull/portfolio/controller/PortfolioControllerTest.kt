package com.nullnull.portfolio.controller

import com.nullnull.portfolio.controller.common.CommonControllerTest
import com.nullnull.portfolio.domain.Certification
import com.nullnull.portfolio.domain.Education
import com.nullnull.portfolio.domain.Profile
import com.nullnull.portfolio.domain.Skill
import com.nullnull.portfolio.domain.WorkExperience
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
    @MockBean private lateinit var skillService: SkillsService
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
            startDate = "2011-03",
            endDate = "2018-08",
            major = "컴퓨터공학"
        )
        val edu2 = Education(
            profile = profile,
            schoolName = "인천고등학교",
            startDate = "2008-02",
            endDate = "2011-02",
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
            issuedDate = LocalDate.of(2024, 6,21),
        )
        val cer2 = Certification(
            profile = profile,
            name = "정보처리기사",
            issuedBy = "한국산업인력공단",
            issuedDate = LocalDate.of(2018, 8,17),
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

    @Test
    fun `profileId로 조회하면 skillResopnse가 출력된다`() {
        // given
        val skill1 = Skill(
            profile = profile,
            category = "Backend",
            items = "Java/SpringBoot, Kotlin/SpringBoot, Proframe/C",
        )

        val skill2 = Skill(
            profile = profile,
            category = "Frontend",
            items = "JSP, HTML/CSS, Bootstrap, Vue3"
        )
        given(skillService.getAllByProfile(profileId)).willReturn(listOf(skill1, skill2))

        // when & then
        mockMvc.get("/portfolio/{profileId}/skills", profileId) {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentTypeCompatibleWith(MediaType.APPLICATION_JSON) }

            jsonPath("$.length()") { value(2)}

            jsonPath("$[0].category") { value("Backend") }
            jsonPath("$[0].items") { value("Java/SpringBoot, Kotlin/SpringBoot, Proframe/C")}

            jsonPath("$[1].category") {value("Frontend")}
            jsonPath("$[1].items") { value("JSP, HTML/CSS, Bootstrap, Vue3")}

        }
    }

    @Test
    fun `profileId로 조회하면 workExperience list를 반환한다`(){
        // given
        val exp1 = WorkExperience(
            profile = profile,
            companyName = "BK S&P",
            companyHeadline = "하나은행 대외계 펌뱅킹 운영·개발",
            title = "Backend Engineer",
            startDate = LocalDate.of(2023, 4, 10),
            endDate = null,
            isCurrent = true,
            location = "인천",
            techStack = "Proframe*C, Neoworks*Java, Xframe*javascript, Oracle",
            summary = "펌뱅킹 대외계 시스템 운영 및 신규 서비스 개발",
            achievements = "- ISO20022 기반 대외 전문 설계 및 MT→MX 변환 모듈 개발\n- 쿠팡 셀러월렛 및 빠른정산 연계 서비스 개발",
            sortOrder = 1,
        )
        val exp2 = WorkExperience(
            profile = profile,
            companyName = "주식회사 어드바이저",
            companyHeadline = "API 개발, CI/CD 구축",
            title = "Backend Engineer",
            startDate = LocalDate.of(2022, 5, 12),
            endDate = LocalDate.of(2023, 1, 1),
            isCurrent = false,
            location = "서울",
            techStack = "Spring Boot, Github Action, AWS EC2, AWS RDS(MySQL)",
            summary = "전자세금계산서 간편발급 서비스 개발",
            achievements = "- 공인인증서 기반 개인 사용자 인증 모듈 설계 및 구현\n- 국세청 전자세금계산서 표준 규격에 맞춘 발급/조회 REST API 설계 및 개발\n- Spring/JSP 기반 레거시코드 유지보수",
            sortOrder = 2,
        )
        given(workExperienceService.getAllByProfile(profileId)).willReturn(listOf(exp1, exp2))

        // when & then
        mockMvc.get("/portfolio/{profileId}/workExperiences", profileId) {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentTypeCompatibleWith(MediaType.APPLICATION_JSON) }

            jsonPath("$.length()") { value(2)}

            jsonPath("$[0].companyName") { value("BK S&P")}
            jsonPath("$[0].companyHeadline") { value("하나은행 대외계 펌뱅킹 운영·개발")}
            jsonPath("$[0].title") {value("Backend Engineer")}
            jsonPath("$[0].startDate") {value("2023-04-10")}
            jsonPath("$[0].endDate") {value(null)}
            jsonPath("$[0].isCurrent") {value(true)}
            jsonPath("$[0].location") {value("인천")}
            jsonPath("$[0].techStack") {value("Proframe*C, Neoworks*Java, Xframe*javascript, Oracle")}
            jsonPath("$[0].summary") {value("펌뱅킹 대외계 시스템 운영 및 신규 서비스 개발")}
            jsonPath("$[0].achievements") {value("- ISO20022 기반 대외 전문 설계 및 MT→MX 변환 모듈 개발\n- 쿠팡 셀러월렛 및 빠른정산 연계 서비스 개발")}
            jsonPath("$[0].sortOrder") {value(1)}
        }
    }
}