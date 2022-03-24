package com.taskTrackerTask.service;
import com.taskTrackerTask.model.Task;
import java.util.List;

public interface TaskService {

	List<Task> findAllProject();

	void saveTask(Task project);

	void deleteProjectById(Long id);

}
