package org.diazero.cadastroIncidentes.model;

	import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.OneToMany;
	import javax.persistence.Table;
	import javax.validation.constraints.Size;

	import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
	import com.sun.istack.NotNull;

	@Entity
	@Table(name = "tb_usuario")
	public class User {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		@NotNull
		@Size(min = 2, max = 100)
		private String name;
		@NotNull
		@Size(min = 5, max = 100)
		private String user;
		@NotNull
		@Size(min = 5, max = 100)
		private String password;
		
		
		
		@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
		@JsonIgnoreProperties("usuario")
		private List<Incident> incident;



		public long getId() {
			return id;
		}



		public void setId(long id) {
			this.id = id;
		}



		public String getName() {
			return name;
		}



		public void setName(String name) {
			this.name = name;
		}



		public String getUser() {
			return user;
		}



		public void setUser(String user) {
			this.user = user;
		}



		public String getPassword() {
			return password;
		}



		public void setPassword(String password) {
			this.password = password;
		}



		public List<Incident> getPostagem() {
			return incident;
		}



		public void setPostagem(List<Incident> postagem) {
			this.incident = postagem;
		}



		public Optional<User> findByName(String userName) {
			// TODO Auto-generated method stub
			return null;
		}
		
	

	
}
