package com.egg.terra24.errors.exception

import java.lang.RuntimeException

class NoAdventureFoundException(adventureID: String): RuntimeException("No adventure found with id $adventureID") {
}