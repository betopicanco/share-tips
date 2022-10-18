package br.com.sharetips.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Data
@Entity
@Table(name="tips") // Dicas
public class Tip implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String content;
	@Column(name="created_at")
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date createdAt;
	@ManyToOne()
	@JoinColumn(name = "id_user")
	private User author;

	@ManyToMany
	@JoinTable(
			name = "tb_tip_subject",
			joinColumns = @JoinColumn(name = "tip_id"),
			inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private Set<Subject> subjects = new HashSet<>();


	public Tip() { }

	public Tip(Long id, String title, String content, Date createdAt, User author) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
		this.author = author;
	}

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void addSubject(Subject subject) {
		subjects.add(subject);
	}
}
