package com.medicalmanager.domain.model


sealed class BookedEvents {

    data class GetBookedDoctors(
        val doctorName: List<String>
    ) : BookedEvents()
}