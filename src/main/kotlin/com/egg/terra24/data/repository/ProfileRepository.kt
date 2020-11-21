package com.egg.terra24.data.repository

import com.egg.terra24.data.entities.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface ProfileRepository: JpaRepository<Profile, String>