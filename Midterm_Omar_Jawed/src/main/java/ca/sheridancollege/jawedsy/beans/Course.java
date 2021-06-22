package ca.sheridancollege.jawedsy.beans;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	private Department department;
	private Long id;
	private String courseName;
	private double courseCredit;
	private String termOffered;
}
