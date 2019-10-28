package be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Genre")
@Table(name = "Genre")
public class GenreEntity {
	@Id
	@GeneratedValue()
	@Column(name = "Genreid")
	private Integer id;
	@Column(name = "Name")
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
