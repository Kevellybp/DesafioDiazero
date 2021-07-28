package org.diazero.cadastroIncidentes.controller;

import java.util.List;
import java.util.Optional;

import org.diazero.cadastroIncidentes.model.User;
import org.diazero.cadastroIncidentes.model.UserLogin;
import org.diazero.cadastroIncidentes.repository.UserRepository;
import org.diazero.cadastroIncidentes.service.UserService;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository repositoryU;

	@PostMapping("/login")
	public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user) {
		return userService.login(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/register")
	public ResponseEntity<Optional<User>> Post(@RequestBody User newUser) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.UserRegister(newUser));
	}

	@GetMapping
	ResponseEntity<List<User>> getAllUsuario() {
		List<User> listaDeUsuarios = repositoryU.findAll();

		if (!listaDeUsuarios.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(listaDeUsuarios);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@GetMapping("id/{id}")
	ResponseEntity<User> getUsuarioById(@PathVariable Long id) {
		return repositoryU.findById(id).map(resp -> ResponseEntity.ok().body(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping
	public ResponseEntity<User> put(@RequestBody User usuario) {
		return ResponseEntity.status(HttpStatus.OK).body(repositoryU.save(usuario));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repositoryU.deleteById(id);
	}
}
