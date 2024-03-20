package com.medicalmanager.domain.use_case

import com.medicalmanager.domain.model.BookedEvents

data class BookedUseCases (
    val getBookedDoctors: BookedEvents.GetBookedDoctors
)