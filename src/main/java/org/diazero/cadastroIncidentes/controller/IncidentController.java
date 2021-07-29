package org.diazero.cadastroIncidentes.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.diazero.cadastroIncidentes.model.Incident;
import org.diazero.cadastroIncidentes.repository.IncidentRepository;
import org.diazero.cadastroIncidentes.service.IncidentService;
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
@RequestMapping("/incident")
@CrossOrigin("*")
public class IncidentController {

	@Autowired
	private IncidentRepository repository;

	@Autowired
	private IncidentService service;

	@GetMapping
	public ResponseEntity<List<Incident>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{idIncident}")
	public ResponseEntity<Incident> GetById(@PathVariable long idIncident) {

		return repository.findById(idIncident).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<Incident>> GetByName(@PathVariable String name) {
		return ResponseEntity.ok(repository.findAllByNameContainingIgnoreCase(name));
	}

	@GetMapping("/description/{description}")
	public ResponseEntity<List<Incident>> GetByDescriprion(@PathVariable String description) {
		return ResponseEntity.ok(repository.findAllByDescriptionContainingIgnoreCase(description));
	}

	@PostMapping
	public ResponseEntity<Optional<Incident>> registerIncident(@Valid @RequestBody Incident incident) {

		return Optional.ofNullable(ResponseEntity.status(HttpStatus.CREATED).body(service.registerIncident(incident)))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@PutMapping
	ResponseEntity<Optional<Incident>> maintenanceIncident(@Valid @RequestBody Incident incidentUpdate) {
		return Optional
				.ofNullable(
						ResponseEntity.status(HttpStatus.ACCEPTED).body(service.maintenanceIncident(incidentUpdate)))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@PutMapping
	ResponseEntity<Optional<Incident>> removeIncident(@Valid @RequestBody Incident closeIncident) {
		return Optional
				.ofNullable(ResponseEntity.status(HttpStatus.ACCEPTED).body(service.closeIncident(closeIncident)))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@DeleteMapping("/{idIncident}")
	public void removeIncidents(@PathVariable long idIncident) {
		repository.deleteById(idIncident);
	}

}
