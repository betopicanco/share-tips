package br.com.sharetips.entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="tips") // Dicas
public class Tip implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String content;
	@ManyToOne()
	@JoinColumn(name = "id_user")
	private User author;

	@Column(name="created_at")
	private Date createdAt;
//	private Set<Subject> subjects = new HashSet<>();
	
	public Tip() {}

	public Tip(Long id, String title, String content, User author, Date createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	//	public Set<Subject> getSubjects() {
//		return Collections.unmodifiableSet(subjects);
//	}
//
//	public Tip addSubject(Subject subject) {
//		this.subjects.add(subject);
//
//		return this;
//	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tip other = (Tip) obj;
		return Objects.equals(id, other.id);
	}
}
