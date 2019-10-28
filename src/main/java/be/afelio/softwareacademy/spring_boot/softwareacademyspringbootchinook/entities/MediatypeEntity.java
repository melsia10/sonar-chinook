package be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.entities;

import javax.persistence.*;

@Entity(name = "Mediatype")
@Table(name = "Mediatype")
public class MediatypeEntity {

    @Id
    @GeneratedValue()
    @Column(name = "Mediatypeid")
    private Integer id;
    @Column(name="Name")
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
