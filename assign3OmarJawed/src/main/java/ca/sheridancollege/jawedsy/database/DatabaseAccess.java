package ca.sheridancollege.jawedsy.database;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.jawedsy.beans.Car;

@Repository
public class DatabaseAccess {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	// @Autowired
	// private BCryptPasswordEncoder passwordEncoder;

	public void insertCar() {
		String query = "INSERT INTO CAR(manufactererID, model, year, color, price)"
				+ " values (1,'Sentra',2011,'Silver',15295.00)";
		int rowsAffected = jdbc.update(query, new HashMap());
		if (rowsAffected > 0) {
			System.out.println("Inserted student into database.");
		}
	}

	public List<Car> getCar() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM car";
		return jdbc.query(query, parameters, new BeanPropertyRowMapper<Car>(Car.class));
	}

	public void addCar(String manufactererID, String model, Long year, String color, double price) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO CAR (manufactererID, model, year, color, price)"
				+ " values (:manufactererID, :model, :year, :color, :price)";
		parameters.addValue("manufactererID", manufactererID);
		parameters.addValue("model", model);
		parameters.addValue("year", year);
		parameters.addValue("color", color);
		parameters.addValue("price", price);
		jdbc.update(query, parameters);
	}

	public List<Car> getCarByID(Long id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM CAR WHERE id =:carID";
		return jdbc.query(query, parameters, new BeanPropertyRowMapper<Car>(Car.class));
	}

	public void delCar(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE FROM CAR WHERE id =:carID";
		namedParameters.addValue("carID", id);
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0)
			System.out.println("Deleted vehicle " + id + " from database");
	}
}
