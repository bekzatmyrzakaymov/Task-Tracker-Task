package com.taskTrackerTask.service;

import java.util.List;
import com.taskTrackerTask.model.Task;
import com.taskTrackerTask.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
    TaskRepo taskRepo;

	@Override
	public List<Task> findAllProject() {
		return taskRepo.findAll();
	}

	@Override
	public void saveTask(Task task) {
		taskRepo.save(task);
	}

	@Override
	public void deleteProjectById(Long id) {
		taskRepo.deleteById(id);
	}

}
