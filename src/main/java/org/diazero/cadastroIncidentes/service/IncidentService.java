package org.diazero.cadastroIncidentes.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.diazero.cadastroIncidentes.model.Incident;
import org.diazero.cadastroIncidentes.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IncidentService {

	private @Autowired IncidentRepository repositoryI;

	public Optional<Incident> registerIncident(Incident newIncident) {
		return Optional.ofNullable(repositoryI.save(newIncident));
	}

	public Optional<Incident> maintenanceIncident(Incident incidentUpdated) {
		LocalDateTime date = LocalDateTime.now();
		Optional<Incident> existingIncident = repositoryI.findById(incidentUpdated.getIdIncident());
		if (existingIncident.isPresent()) {
			existingIncident.get().setName(incidentUpdated.getName());
			existingIncident.get().setDescription(incidentUpdated.getDescription());
			existingIncident.get().setUpdatedAt(date);
			return Optional.ofNullable(repositoryI.save(existingIncident.get()));

		} else {
			return Optional.empty();
		}}

	public Optional<Incident> closeIncident(Incident closeIncident) {
		LocalDateTime date = LocalDateTime.now();
		Optional<Incident> existingIncident = repositoryI.findById(closeIncident.getIdIncident());
		if (existingIncident.isPresent()) {
			existingIncident.get().setName(closeIncident.getName());
			existingIncident.get().setDescription(closeIncident.getDescription());
			existingIncident.get().setClosedAt(date);
			return Optional.ofNullable(repositoryI.save(existingIncident.get()));

		} else {
			return Optional.empty();
		}
	}
}
