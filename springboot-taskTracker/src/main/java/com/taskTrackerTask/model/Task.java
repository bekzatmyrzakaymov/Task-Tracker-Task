package com.taskTrackerTask.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long taskId;
	@Column(name = "task_name")
	private String taskName;
	@Column(name = "task_description")
	private String taskDescription;
	@Column(name = "task_status")
	private String taskStatus;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="project_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Project project;

}
