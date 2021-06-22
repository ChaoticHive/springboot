package ca.sheridancollege.jawedsy.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.jawedsy.beans.Car;
import ca.sheridancollege.jawedsy.database.DatabaseAccess;

@Controller
public class HomeController {

	DatabaseAccess da = new DatabaseAccess();

	@GetMapping("/")
	public String goHome() {
		//List<Car> manufacturers = da.getCar();
		return "index";
	}

	@PostMapping("/insert")
	public String goInsert(Model model) {
		//List<Car> manufacturers = new ArrayList<>();
		List<Car> manufacturers = da.getCar();
		model.addAttribute("Car", new Car());
		model.addAttribute("Manufacturers", manufacturers);
		return "insert";
	}

	@PostMapping("/addCar")
	public String addCar(Model model1, @RequestParam(required = false) String manufactererID,
			@RequestParam(required = false) String model, @RequestParam(required = false) Long year,
			@RequestParam(required = false) String color, @RequestParam(required = false) Long price) {
		da.addCar(manufactererID, model, year, color, price);
		//List<Car> manufacturers = da.getCar(); Keeps giving null pointers
		//model1.addAttribute("Manufacturers", manufacturers);
		Car car = new Car();
		model1.addAttribute("Car", new Car());
		return "index";
	}
	
	@PostMapping("/secure/delete")
	public String goDelete(Model model) {
		model.addAttribute("Car", new Car());
		return "delete";
	}
	
	@PostMapping("/update/{id}")
	public String goUpdate(Model model, @PathVariable Long id) {
		Car car = da.getCarByID(id).get(0);
		model.addAttribute("Car", car);
		da.delCar(id);
		return "index";
	}

	@PostMapping("/secure/delete/{id}")
	public String goDelete(Model model, @PathVariable Long id) {
		da.delCar(id);
		return "index";
	}
}
