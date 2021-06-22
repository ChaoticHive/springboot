package ca.sheridancollege.jawedsy.beans;

import lombok.*;;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
	private String manufacturerID;
	private String model;
	private int year;
	private String color;
	private Double price;
}
