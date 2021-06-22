package ca.sheridancollege.jawedsy.database;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.jawedsy.beans.Course;
import ca.sheridancollege.jawedsy.beans.Department;
@Repository
public class DatabaseAccess {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	public void insertDepartment() {
		//String query = "INSERT INTO department(depName) VALUES('Engineering')";
		//int rowsAffected = jdbc.update(query, new HashMap());
		//if (rowsAffected > 0) {
		//	System.out.println("Inserted department into database.");
		//}
	}

	public void insertDepartment(String string) {
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		String query = "INSERT INTO department(depName)VALUES(:name)";
		namedParameter.addValue("name", string);
		int rowsAffected = jdbc.update(query, namedParameter);
		if (rowsAffected > 0) {
			System.out.println("Inserted department into database.");
		}
	}

	public List<Department> getDepartments() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM department";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Department>(Department.class));
	}

	public List<Department> getDepartmentById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM department WHERE id = :id";
		namedParameters.addValue("id", id);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Department>(Department.class));
	}

	public void insertCourse() {
		String query = "INSERT INTO course(courseName, courseCredit, termOffered, department) VALUES('Test',0,'Test','Test')";
		int rowsAffected = jdbc.update(query, new HashMap());
		if (rowsAffected > 0) {
			System.out.println("Inserted course into database.");
		}
	}

	public void insertCourse(Course course) {
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		String query = "INSERT INTO course(courseName, courseCredit, termOffered, department)VALUES(:courseName,:courseCredit,:termOffered,:department)";
		namedParameter.addValue("courseName", course.getCourseName());
		int rowsAffected = jdbc.update(query, namedParameter);
		if (rowsAffected > 0) {
			System.out.println("Inserted course into database.");
		}
	}

	public void deleteCourse(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE FROM course WHERE id =:id";
		namedParameters.addValue("id", id);
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0)
			System.out.println("Deleted course " + id + " from database");
	}

	public List<Course> getCourseById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM course WHERE id = :id";
		namedParameters.addValue("id", id);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper <Course> (Course.class));
	}
	
	public List<Course> getCourses() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM course";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Course>(Course.class));
	}

	public Object searchByCourse(String courseName) {
		// TODO Auto-generated method stub
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT courseName FROM course";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Course>(Course.class));
	}
	
	public Object searchByDepartment(String depName) {
		// TODO Auto-generated method stub
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT department FROM course";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Course>(Course.class));
	}
}