package ca.sheridancollege.jawedsy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.jawedsy.beans.Course;
import ca.sheridancollege.jawedsy.beans.Department;
import ca.sheridancollege.jawedsy.database.DatabaseAccess;

@Controller
public class StudentController {
	@Autowired
	private DatabaseAccess da;

	@GetMapping("/")
	public String goHome(Model model) {
		model.addAttribute("Department", new Department());
		model.addAttribute("Course", new Course());
		da.insertDepartment();
		return "index";
	}

	@PostMapping("/addDepartment")
	public String addDepartment(Model model, @ModelAttribute Department Department) {
		da.insertDepartment(Department.getDepName()); // Not working properly yet
		model.addAttribute("Department", new Department());
		return "addDepartment";
	}

	@PostMapping("/addCourse")
	public String addCourse(Model model, @ModelAttribute Course course) {
		da.insertCourse(course);
		// model.addAttribute(course);
		model.addAttribute("Course", new Course());
		model.addAttribute("courseList", da.getCourses());
		return "addCourse";
	}
	
	@PostMapping("/viewCourses")
	public String viewCourses(Model model, @ModelAttribute Department Department) {
		model.addAttribute("Department", new Department());
		model.addAttribute("Course", new Course());
		return "viewCourses";
	}

	@GetMapping("/deleteCourse/{id}")
	public String deleteStudent(Model model, @PathVariable Long id) {

		da.deleteCourse(id);
		model.addAttribute("Course", new Course());
		model.addAttribute("courseList", da.getCourses());
		return "index";
	}

	@GetMapping("/editCourse/{id}")
	public String editStudent(Model model, @PathVariable Long id) {
		Course course = da.getCourseById(id).get(0);
		model.addAttribute("Course", course);
		da.deleteCourse(id);
		model.addAttribute("courseList", da.getCourses());
		return "index";
	}
	
	@PostMapping("/search")
	public String search(Model model) {
		model.addAttribute("Department", new Department());
		model.addAttribute("Course", new Course());
		return "search";
	}
	
	@RequestMapping(value = "/getByCourse", method = RequestMethod.GET)
	public String searchByCourse(@RequestParam (value = "courseName", required = false) String courseName, Model model) {
	    model.addAttribute("Found", da.searchByCourse(courseName));
	    return "search";
	}
	
	@RequestMapping(value = "/getByDep", method = RequestMethod.GET)
	public String searchByDepartment(@RequestParam (value = "depName", required = false) String depName, Model model) {
	    model.addAttribute("Found", da.searchByCourse(depName));
	    return "search";
	}
}
