package com.koltinspring.repository

import com.koltinspring.dto.entity.Course
import org.springframework.data.repository.CrudRepository

interface CourseRepository: CrudRepository<Course, Int> {

}