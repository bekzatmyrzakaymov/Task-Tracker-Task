package com.taskTrackerTask.controller;

import java.util.List;
import com.taskTrackerTask.model.Project;
import com.taskTrackerTask.model.Task;
import com.taskTrackerTask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.taskTrackerTask.service.ProjectService;

@Controller
public class TaskTrackerController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private TaskService taskService;

	// display list of project
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "projectName", "asc", model);
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 10;
		Page<Project> page = projectService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Project> listProjects = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("projects", listProjects);
		model.addAttribute("listProjects", listProjects);
		model.addAttribute("taskList",taskService.findAllProject());
		return "list";
	}

	// new project form
	@GetMapping("/showNewProjectForm")
	public String showNewProjectForm(Model model) {
		Project project = new Project();
		model.addAttribute("project", project);
		return "addProject";
	}

	// saving new project
	@PostMapping("/saveProject")
	public String saveStudent(@ModelAttribute("project") Project project) {
		projectService.saveProjectAndUpdate(project);
		return "redirect:/";
	}

	
	//delete project
	@GetMapping("/deleteProject/{id}")
	public String deleteStudent(@PathVariable(value = "id") long id) {
		projectService.deleteProjectById(id);
		return "redirect:/";
	}
	

	//update project form
	@GetMapping("/updateProjectForm/{id}")
	public String updateStudentForm(@PathVariable(value = "id") Long projectId, Model model) {
		Project project= projectService.findProjectById(projectId);
		model.addAttribute("project", project);
		return "updateProjects";
	}
	
	//update project
	@PostMapping("/updateProject")
	public String updateStudent(@ModelAttribute("project") Project project) {
		projectService.saveProjectAndUpdate(project);
		return "redirect:/";
	}
	
	
	//add task for project form
	@GetMapping("/addTaskForProject/{id}")
	public String addStudentProjectForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("project", projectService.findProjectById(id));
		model.addAttribute("taskList",taskService.findAllProject());
		model.addAttribute("task", new Task());
		return "addTaskForProject";
	}
	
	//save tasks
	@PostMapping("/task/{id}/saveTask")
	public String saveStudentProject(@PathVariable(value ="id") Long id,
									 @ModelAttribute("task") Task task, Model model) {
		Project project=projectService.findProjectById(id);
		task.setProject(project);
		taskService.saveTask(task);
		
		model.addAttribute("project", project);
		model.addAttribute("taskList",taskService.findAllProject());
		
		return "addTaskForProject";
	}

	//delete project
	@GetMapping("/project/{projectId}/deleteTask/{id}")
	public String deleteProject(@PathVariable(value = "projectId") Long studentId,
			@PathVariable(value = "id") Long id, Model model) {
		taskService.deleteProjectById(id);
		model.addAttribute("project", projectService.findProjectById(studentId));
		model.addAttribute("taskList",taskService.findAllProject());
		model.addAttribute("task", new Task());
		return "addTaskForProject";
	}
}
 