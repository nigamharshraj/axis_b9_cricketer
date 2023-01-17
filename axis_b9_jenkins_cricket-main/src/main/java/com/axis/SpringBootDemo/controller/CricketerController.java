package com.axis.SpringBootDemo.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.axis.SpringBootDemo.entity.Cricketer;

@RestController
public class CricketerController {

	private static ArrayList<Cricketer> crickList;
	static {
		crickList = new ArrayList<>();
		crickList.add(new Cricketer(1001, "Virat Kohali", 111, 60, 8, 6, 217.3));
		crickList.add(new Cricketer(1002, "Surrya kumar Yadav", 112, 55, 9, 5, 230.4));
		crickList.add(new Cricketer(1003, "Rohit Sharma", 151, 70, 7, 3, 217.4));
		crickList.add(new Cricketer(1004, "Akashar Patel", 90, 40, 3, 6, 180.2));
		crickList.add(new Cricketer(1005, "Smirthi Mandhana", 120, 68, 9, 4, 200.0));
	}
	
	@GetMapping("/message")
	public String getMessage() {
		return "Hello. First Spring Boot Project. Good Morning. ";
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome Spring Boot";
	}
	
	// Get all cricketer data
	@GetMapping("/cricketer")
	public ArrayList<Cricketer> getCricketers(){
		return crickList;
	}
	
	// Get cricketer data by ID
	@GetMapping("/cricketer/{cricketerId}")
	public Cricketer getCricketerById(@PathVariable int cricketerId) {
		for(Cricketer ck: crickList) {
			if(ck.getCricketerId() == cricketerId) {
				return ck; // Return when cricketer Id is found
			}
		}
		return null; // Return null when cricketer Id is not found
	}
	
	// Add cricketer to cricketer data
	@PostMapping("/cricketer")
	public ResponseEntity<String> addCricketer(@RequestBody Cricketer cricketer) {
		crickList.add(cricketer);
		return new ResponseEntity<String>("Cricketer addded Successfully.", HttpStatus.OK);
	}
	
	// Update Request
	@PutMapping("/cricketer/update/{cricketerId}")
	public ResponseEntity<String> updateCricketer
	(@PathVariable int cricketerId, @RequestBody Cricketer updateCricketer){
		if(cricketerId != updateCricketer.getCricketerId()) {
			return new ResponseEntity<String>("Cricket ID's do not match.",HttpStatus.BAD_REQUEST);
		}
		
		int index = crickList.indexOf(updateCricketer);
		if(index == -1) {
			return new ResponseEntity<String>("Cricket with Id "+cricketerId+ " not found ", HttpStatus.NOT_FOUND);
		}else {
			crickList.get(index).setBalls(updateCricketer.getBalls());
			crickList.get(index).setRunsScored(updateCricketer.getRunsScored());
			crickList.get(index).setFours(updateCricketer.getFours());
			crickList.get(index).setSixes(updateCricketer.getSixes());
			crickList.get(index).setStrikeRate(updateCricketer.getStrikeRate());
			return new ResponseEntity<String>("Cricketers data updated successfully.", HttpStatus.OK);
		}	
	}
	
	// Delete Request 
	@DeleteMapping("/cricketer/delete/{cricketerId}")
	public ResponseEntity<String> deleteCricketer (@PathVariable int cricketerId){
		
		Cricketer cricketer = getCricketerById(cricketerId);
		if(cricketer == null) {
			return new ResponseEntity<String>("Cricket with Id "+cricketerId+ " not found ", HttpStatus.NOT_FOUND);
		}else {
			crickList.remove(cricketer);
			return new ResponseEntity<String>("Cricket with Id "+cricketerId+ " Deleted Successfully", HttpStatus.OK);
		}
		
	}
	
}
