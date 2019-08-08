package dev.oscar.ruiz.cvapp.utils

import dev.oscar.ruiz.cvapp.fetchcv.data.model.response.Location

fun formatName(name: String, lastName: String): String = "$name $lastName"

fun formatNameLocation(company: String, location: Location) =
    "$company, ${location.city}, ${location.country}"

fun formatDegree(degree: String, degreeName: String) = "$degree, $degreeName"

fun formatLookingJob(isLookingJob: Boolean) = "Is looking for Job: $isLookingJob"

fun formatLastUpdate(lastUpdate: String) = "Last Update: $lastUpdate"

fun formatAge(age: Int) = "Age: $age"

fun formatPhone(phone: String) = "Phone: $phone"

fun formatMail(mail: String) = "Email: $mail"

fun formatCountry(country: String) = "Location: $country"