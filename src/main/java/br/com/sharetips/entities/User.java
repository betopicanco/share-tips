package br.com.sharetips.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users") // Usuários
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String password;
	private String profession;
	@ManyToMany
	@JoinTable(
			name = "tb_user_subject",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private Set<Subject> favoriteSubjects;

	public Set<Subject> getFavoriteSubjects() {
		return favoriteSubjects;
	}

	public void addFavoriteSubjects(Subject subject) {
		favoriteSubjects.add(subject);
	}
}