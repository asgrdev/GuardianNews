package org.asghari.guardiannews.domain.repositories

import org.asghari.guardiannews.data.models.sections.Sections

interface SectionsRepository {
    suspend fun getSections(): Sections

    suspend fun getSectionsById(id:String): Sections
}