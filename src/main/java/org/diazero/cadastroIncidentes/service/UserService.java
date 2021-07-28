package org.diazero.cadastroIncidentes.service;

import java.nio.charset.Charset;
import org.diazero.cadastroIncidentes.model.User;
import org.diazero.cadastroIncidentes.model.UserLogin;
import org.diazero.cadastroIncidentes.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;

	public Optional<User> UserRegister(User newUser) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCriptografada = encoder.encode(newUser.getPassword());
		newUser.setPassword(senhaCriptografada);
		return Optional.ofNullable(repository.save(newUser));
	}

	public Optional<UserLogin> login(Optional<UserLogin> loginUser) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<User> presentUser = repository.findByUser(loginUser.get().getUsuario());
		if (presentUser.isPresent()) {
			if (encoder.matches(loginUser.get().getSenha(), presentUser.get().getPassword())) {
				String auth = loginUser.get().getUsuario() + ":" + loginUser.get().getSenha();
				byte[] encodedAuth = org.apache.tomcat.util.codec.binary.Base64
						.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String token = "Basic " + new String(encodedAuth);
				loginUser.get().setToken(token);
				loginUser.get().setId(presentUser.get().getId());
				loginUser.get().setNome(presentUser.get().getName());
				loginUser.get().setSenha(presentUser.get().getPassword());

				return loginUser;
			}
		}
		return null;
	}
}
