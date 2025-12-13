package com.nullnull.portfolio.controller.common

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
abstract class CommonControllerTest {
}