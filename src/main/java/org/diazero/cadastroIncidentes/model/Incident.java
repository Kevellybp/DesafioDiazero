package org.diazero.cadastroIncidentes.model;


import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "incidente")

public class Incident {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idIncident;

	@NotNull
	@Size(min = 3, max = 100)
	private String name;

	@NotNull
	@Size(min = 10, max = 500)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt = new java.sql.Date(System.currentTimeMillis());
	
	
	private LocalDateTime updatedAt;
	
	private LocalDateTime closedAt;

	@ManyToOne
	@JsonIgnoreProperties ("usuario")
		private User user;
	
	public long getIdIncident() {
		return idIncident;
	}

	public void setIdIncident(long idIncident) {
		this.idIncident = idIncident;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatAt() {
		return createdAt;
	}

	public void setCreatAt(Date creatAt) {
		this.createdAt = creatAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getClosedAt() {
		return closedAt;
	}

	public void setClosedAt(LocalDateTime closedAt) {
		this.closedAt = closedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



}
