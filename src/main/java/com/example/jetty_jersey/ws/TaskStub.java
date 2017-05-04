package com.example.jetty_jersey.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jetty_jersey.util.TaskInfo;
import com.example.jetty_jersey.dao.*;
import com.example.jetty_jersey.dao_interface.TaskDao;
import com.example.jetty_jersey.db.Utility;

@Path("/task")
public class TaskStub
{
	private static Logger log = LogManager.getLogger(TaskStub.class.getName());
	private static TaskDao taskDao = DAO.getTaskDao();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<TaskInfo> allTasks()
	{
		if (LoginStub.connected)
		{
			log.debug("Login connected : " + LoginStub.connected);
			return DAO.getTaskDao().getAllTasks();
		} else
			return new ArrayList<TaskInfo>();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/plane/{id}")
	public List<TaskInfo> allTasksByPlaneId(@PathParam("id") int id)
	{
		if (LoginStub.connected)
		{
			return taskDao.getTasksByPlaneId(id);
		}
		return new ArrayList<TaskInfo>();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public TaskInfo taskById(@PathParam("id") int id)
	{
		if (LoginStub.connected)
		{
			return DAO.getTaskDao().getTasksById(id);
		}
		return null;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public void addTask(TaskInfo taskInfo)
	{

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/modify/{id}")
	public void modifyTaskById(@PathParam("id") int id)
	{

	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update/{id}")
	public void updateTaskById(@PathParam("id") int id)
	{

	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete/{id}")
	public void deleteTaskById(@PathParam("id") int id)
	{
		taskDao.deleteTask(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addTasks/{tasks}")
	public void addTasks(@PathParam("task") String task)
	{
		String[] splitedFile;
		String[] splitedLine;
		try
		{
			splitedFile = task.split("\n");
			for (int i = 0; i < splitedFile.length; i++)
			{
				splitedLine = splitedFile[i].split(",");
				if (splitedLine.length != 10)
				{
					log.error("Le fichier n'est pas dans le bon format!");
				} else
				{
					int id = Integer.parseInt(splitedLine[0]);
					Date starTime = Utility.convertDateString(splitedLine[1]);
					Date endTime = Utility.convertDateString(splitedLine[2]);
					String description = splitedLine[3];
					String periodicity = splitedLine[4];
					String ataCategory = splitedLine[5];
					boolean needHangar = Utility.convertBoolString(splitedLine[6]);
					int planeId = Integer.parseInt(splitedLine[7]);
					int statut = Integer.parseInt(splitedLine[8]);
					int mroId = Integer.parseInt(splitedLine[9]);
					Task t = new Task(id, -1, starTime, endTime, planeId, statut, mroId);
					taskDao.addTask(t);
				}

			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
