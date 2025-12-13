package com.nullnull.portfolio.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.nullnull.portfolio.controller.common.CommonControllerTest
import com.nullnull.portfolio.domain.Profile
import com.nullnull.portfolio.service.ProfileService
import com.nullnull.portfolio.testutil.setId
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import java.time.LocalDate
import kotlin.test.Test
import org.mockito.BDDMockito.given
import org.springframework.test.web.servlet.get

@WebMvcTest(ProfileController::class)
class ProfileControllerTest: CommonControllerTest() {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var profileService: ProfileService

    lateinit var profile: Profile

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

        setId(profile, 1L) //
    }

    @Test
    fun `slug로 profile 조회하면 ProfileResponse를 반환한다`() {
        // given
        given(profileService.getBySlug("test")).willReturn(profile)

        // when & then
        mockMvc.get("/profile/test")
            .andExpect {
                status { isOk() }
                jsonPath("$.name") { value("김태영") }
                jsonPath("$.slug") { value("test") }
            }
    }

}