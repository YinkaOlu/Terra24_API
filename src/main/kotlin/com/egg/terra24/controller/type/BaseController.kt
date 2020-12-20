package com.egg.terra24.controller.type

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/api/v1")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
interface BaseController