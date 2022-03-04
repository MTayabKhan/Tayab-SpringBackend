package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Hunters;
import com.example.demo.service.HuntersService;


@RestController

public class HuntersController {

	private HuntersService service;
	
	@Autowired
	public HuntersController(HuntersService service) {
		super();
		this.service = service;
	}
	@PostMapping("/create") // @RequestBody pulls the parameter from the body of the request
	public ResponseEntity<Hunters> createTarnished(@RequestBody Hunters h) {

		Hunters created = this.service.create(h);
		ResponseEntity<Hunters> response = new ResponseEntity<Hunters>(created, HttpStatus.CREATED); // 201 -
																											// created
		return response;
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Hunters>> getAllHunters() {
		return ResponseEntity.ok(this.service.getAll());
	}

	@GetMapping("/get/{id}") // 200 ok
	public Hunters getHunter(@PathVariable Integer id) {
		return this.service.getOne(id);
	}

	@PutMapping("/replace/{id}") // 202 accepted
	public ResponseEntity<Hunters> replaceTarnished(@PathVariable Integer id, @RequestBody Hunters newHunter) {
		Hunters body = this.service.replace(id, newHunter);
		ResponseEntity<Hunters> response = new ResponseEntity<Hunters>(body, HttpStatus.ACCEPTED);
		return response;
	}

	@DeleteMapping("/remove/{id}") // 204 no content
	public ResponseEntity<?> removeHunter(@PathVariable Integer id) {
		this.service.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
//	@GetMapping("/getByName/{name}") // 200 ok
//	public ResponseEntity<List<Hunters>> getHunterByName(@PathVariable String name){
//	List<Hunters> found = this.service.getAllHuntersByName(name);
//	return ResponseEntity.ok(found);
//	}
//	
//	@GetMapping("/getByEchoes/{echoes}") // 200 ok
//	public ResponseEntity<List<Hunters>> getTHunterByEchoes(@PathVariable Integer echoes){
//	List<Hunters> found = this.service.getAllHuntersdByEchoes(echoes);
//	return ResponseEntity.ok(found);
//	}
//	
//	@GetMapping("/getByOldBlood/{oldblood}") // 200 ok
//	public ResponseEntity<List<Hunters>> getHunterByOldBlood(@PathVariable Boolean oldblood){
//	List<Hunters> found = this.service.getAllHuntersByOldBlood(oldblood);
//	return ResponseEntity.ok(found);
//	}
}
