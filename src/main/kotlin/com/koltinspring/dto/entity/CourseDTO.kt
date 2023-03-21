package com.koltinspring.dto.entity

import jakarta.persistence.*


data class CourseDTO(
    val id: Int?,
    val name: String,
    val category: String
)

@Entity
@Table
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    var name: String,
    var category: String
)