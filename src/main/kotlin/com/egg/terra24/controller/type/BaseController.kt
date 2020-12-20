package com.egg.terra24.controller.type

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.cors.CorsConfiguration

@RequestMapping("/api/v1")
@CrossOrigin(value = [CorsConfiguration.ALL])
interface BaseController