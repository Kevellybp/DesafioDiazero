package org.diazero.cadastroIncidentes.repository;

import java.util.List;

import org.diazero.cadastroIncidentes.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
	public List<Incident> findAllByNameContainingIgnoreCase (String name);	
public List<Incident> findAllByDescriptionContainingIgnoreCase (String description);
	
}
