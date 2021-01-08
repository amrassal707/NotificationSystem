package net.codejava;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
 
import org.springframework.web.bind.annotation.*;
 
@RestController
public class templateController implements Controllers<template> {

	
@Autowired 
private templateService service;
//read
@Override
@GetMapping("/templates") // localhost/templates
public List<template> list() {
	return service.listAll();
}



@Override
@GetMapping("/templates/{id}")
public ResponseEntity<template> get(@PathVariable Integer id) {
    try {
    	System.out.println("checking the function");
    	template template=service.get(id);
    	 return new ResponseEntity<template>(template, HttpStatus.OK);
    }
    catch (Exception e) {
    	return new ResponseEntity<template>(HttpStatus.NOT_FOUND);
	}
       
       
}





//create
@Override

@PostMapping("/addtemplate")
public void addSign(@RequestBody template template) {
	System.out.println("checking the function");
    service.save(template);
}





//update
@Override
@PutMapping("/templates/{id}")
public ResponseEntity<?> update(@RequestBody template template, @PathVariable Integer id) {
    try {
    	System.out.println("checking the function");
        template existtemplate = service.get(id);
        service.save(template);
        return new ResponseEntity<>(HttpStatus.OK);
    } catch (NoSuchElementException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }      
}






//delete
@Override
@DeleteMapping("/templates/{id}")
public void delete(@PathVariable Integer id) {
    service.delete(id);
    //deleted
}














}