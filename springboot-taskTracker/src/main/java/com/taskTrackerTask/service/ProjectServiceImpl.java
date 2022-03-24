package com.taskTrackerTask.service;

import java.util.List;
import java.util.Optional;
import com.taskTrackerTask.model.Project;
import com.taskTrackerTask.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepo projectRepo;

	@Override
	public Page<Project> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.projectRepo.findAll(pageable);
	}

	@Override
	public Project saveProjectAndUpdate(Project project) {
		return projectRepo.save(project);
	}

	@Override
	public void deleteProjectById(Long id) {
		if(id != null) {
			projectRepo.deleteById(id);
		}
		else {
			throw new RuntimeException("Project not found for id"+id);
		}
	}

	@Override
	public Project findProjectById(Long id) {
		Optional<Project> optional = projectRepo.findById(id);
		Project project = null;
		if (optional.isPresent()) {
			project = optional.get();
			
		} else {
			throw new RuntimeException("Project not found for id: " + id);
		}
		return project;
	}


	@Override
	public List<Project> getAllProjects(String keyword) {
		return projectRepo.findAll();
	}

}
