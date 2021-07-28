package org.diazero.cadastroIncidentes.repository;

import java.util.Optional;

import org.diazero.cadastroIncidentes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
public Optional<User> findByUser (String User);
	
	
}
