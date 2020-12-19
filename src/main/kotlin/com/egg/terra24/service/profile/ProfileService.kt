package com.egg.terra24.service

import com.egg.terra24.data.entities.Profile
import com.egg.terra24.data.repository.ProfileRepository

interface ProfileService {
    fun getProfile(id: String): Profile?
}