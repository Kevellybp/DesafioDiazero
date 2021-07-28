package org.diazero.cadastroIncidentes.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.diazero.cadastroIncidentes.model.Incident;
import org.diazero.cadastroIncidentes.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/incident")
@CrossOrigin ("*")
public class IncidentController {

	@Autowired
	private IncidentRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Incident>> GetAll (){
		return ResponseEntity.ok(repository.findAll());
	}
	
	
	@GetMapping ("/{idIncident}")
	public ResponseEntity<Incident> GetById (@PathVariable long idIncident) {
		
		return repository.findById(idIncident)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	@GetMapping ("/name/{name}")
	public ResponseEntity<List<Incident>> GetByName (@PathVariable String name) {
		return ResponseEntity.ok(repository.findAllByNameContainingIgnoreCase(name));
	}
	@GetMapping ("/description/{description}")
	public ResponseEntity<List<Incident>> GetByDescriprion (@PathVariable String description) {
		return ResponseEntity.ok(repository.findAllByDescriptionContainingIgnoreCase(description));
	}
	@PostMapping
	public ResponseEntity<Incident> registerIncident (@Valid @RequestBody Incident incident){
		LocalDateTime date = LocalDateTime.now();
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(incident));
	}
	
	
	@PutMapping
	 ResponseEntity<Incident> maintenanceIncident (@Valid @RequestBody Incident incidentUpdate){
		LocalDateTime date = LocalDateTime.now();
		Optional<Incident> existingIncident = repository.findById(incidentUpdate.getIdIncident());
		if (existingIncident.isPresent()) {
		existingIncident.get().setName(incidentUpdate.getName());
		existingIncident.get().setDescription(incidentUpdate.getDescription());
		existingIncident.get().setUpdate(incidentUpdate.getUpdate());
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(existingIncident.get()));
			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping ("/{idIncident}")
	public void removeIncidents (@PathVariable long idIncident) {
		repository.deleteById(idIncident);
	}
	
}
