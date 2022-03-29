package com.congnizant;

import java.util.Calendar;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//controller
@RestController
@RequestMapping("/personal")
public class AgeRestController {
	
	@GetMapping("/age")
	// public ResponseEntity<String> calculateAge(@RequestParam Integer yob) {
	public ResponseEntity<String> calculateAge(
			@RequestParam(name = "yob", required = false, defaultValue = "8000") Integer yob) {
		if (isValidYear(yob)) {
			int age = ageCalcLogic(yob);
			return new ResponseEntity<String>("Hi, your age is calculated as " + age, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Hi, sorry you have an Invalid Year of Birth (or) a Future Year is given",
				HttpStatus.BAD_REQUEST);
	}
	//added the logic
	private int ageCalcLogic(Integer yob) {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		return currentYear - yob;
	}

	private boolean isValidYear(Integer yob) {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		return currentYear > yob;
	}

	/*--- resource method for path parameters ---*/
	@GetMapping("/calcage/{name}/{yob}")
	public ResponseEntity<String> calcAge(@PathVariable String name, @PathVariable Integer yob) {
		if (isValidYear(yob)) {
			int age = ageCalcLogic(yob);
			return new ResponseEntity<String>("Hi, "+name+" - your age is calculated as " + age, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Sorry "+name+" you have an Invalid Year of Birth (or) a Future Year is given",
				HttpStatus.BAD_REQUEST);
	}
}
