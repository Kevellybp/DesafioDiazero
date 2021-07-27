package org.diazero.cadastroIncidentes.service;

import java.util.List;
import java.util.Optional;

import org.diazero.cadastroIncidentes.model.Incident;
import org.diazero.cadastroIncidentes.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidentService {

	private @Autowired IncidentRepository repositoryI;
	
	public Optional<Incident> registerIncident (Incident newIncident){
		Optional<List<Incident>> nameExisting = Optional.ofNullable(repositoryI.
				findAllByNameContainingIgnoreCase(newIncident.getName()));
		if (nameExisting.isPresent()) {
			return Optional.empty();
		}
		
	
	
	Optional<List<Incident>> descriptionExisting = Optional.ofNullable(repositoryI.
			findAllByDescriptionContainingIgnoreCase(newIncident.getName()));
	if (descriptionExisting.isPresent()) {
		return Optional.empty();
	}
	return null;

}
}
