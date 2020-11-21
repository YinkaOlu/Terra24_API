package com.egg.terra24.controller

import com.egg.terra24.controller.type.BaseController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController: BaseController {
    @GetMapping("/home")
    fun test(): ResponseEntity<String> = ResponseEntity("Test", HttpStatus.OK)
}