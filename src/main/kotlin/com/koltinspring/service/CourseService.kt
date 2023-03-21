package com.koltinspring.service

import com.koltinspring.dto.entity.Course
import com.koltinspring.dto.entity.CourseDTO
import com.koltinspring.repository.CourseRepository
import mu.KLogging
import org.springframework.stereotype.Service


@Service
class CourseService(val courseRepository: CourseRepository) {

    companion object : KLogging()

    fun addCourse(courseDTO: CourseDTO): CourseDTO {
        val courseEntity = courseDTO.let {
            Course(null, name = it.name, category = it.category)
        }
        courseRepository.save(courseEntity)
        logger.info("saved course is : $courseEntity")
        return courseEntity.let {
            CourseDTO(it.id, it.name, it.category)
        }
    }

    fun getAllCourses(): List<CourseDTO> {
        return courseRepository.findAll()
            .map {
                CourseDTO(id = it.id, name = it.name, category = it.category)
            }
    }

    fun updateCourse(courseDTO: CourseDTO, courseId: Int) {
        val existing = courseRepository.findById(courseId)
        if (existing.isPresent){
            existing.get().let {
                it.name = courseDTO.name
                it.category = courseDTO.category
                courseRepository.save(it)
                CourseDTO(id = it.id, name = it.name, category = it.category)
            }
        }else{
            println("No COURSE Found ...Inserting...")
            addCourse(courseDTO)
        }
    }

    fun deleteCourse(courseId: Int) {
        val existing = courseRepository.findById(courseId)
        if (existing.isPresent){
            existing.get().let {
                courseRepository.delete(it)
            }
        }else{
            println("No COURSE Found")
        }
    }
}
