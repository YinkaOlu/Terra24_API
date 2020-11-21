package com.egg.terra24.service

import com.egg.terra24.data.entities.Profile
import com.egg.terra24.data.repository.ProfileRepository

interface ProfileService {
    fun getProfile(id: String): Profile?
}

class ProfileServiceImpl(
        private val profileRepository: ProfileRepository
): ProfileService {
    override fun getProfile(id: String): Profile? {
        val profileOptional = profileRepository.findById(id)
        return if (profileOptional.isPresent) {
            profileOptional.get()
        } else {
            null
        }
    }
}