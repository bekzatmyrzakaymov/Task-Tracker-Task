package com.taskTrackerTask.service;

import java.util.List;
import com.taskTrackerTask.model.Project;
import org.springframework.data.domain.Page;

public interface ProjectService {

	Page<Project> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);

	Project saveProjectAndUpdate(Project project);

	void deleteProjectById(Long id);

	Project findProjectById(Long id);

	List<Project> getAllProjects(String keyword);
	
}
