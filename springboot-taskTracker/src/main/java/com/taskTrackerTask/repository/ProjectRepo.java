package com.taskTrackerTask.repository;

import com.taskTrackerTask.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long>{

	@Query(value = "SELECT s from Project s WHERE CONCAT(s.id,'') LIKE %?1%")
	 Project findStudentById(Long id);
}
