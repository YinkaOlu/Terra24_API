package com.egg.terra24.service.profile

import com.egg.terra24.data.entities.Profile
import com.egg.terra24.data.repository.ProfileRepository
import com.egg.terra24.service.ProfileService

class ProfileServiceImpl(
    private val profileRepository: ProfileRepository
): ProfileService {
    override fun getProfile(id: String): Profile? {
        val profileOptional = profileRepository.findById(id)
        return if (profileOptional.isPresent && !profileOptional.isEmpty) {
            profileOptional.get()
        } else {
            null
        }
    }
}