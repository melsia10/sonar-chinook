package be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.entities;

import java.util.List;

import javax.persistence.*;

@Entity(name = "Album")
@Table(name = "Album")
public class AlbumEntity {
    @Id
    @GeneratedValue()
    @Column(name = "Albumid")
    private Integer id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "Artistid")
    private ArtistEntity artistEntity;
    @OneToMany(mappedBy="albumEntity")
    private List<TrackEntity> listTracks;

    public AlbumEntity(String title, ArtistEntity artistEntity, List<TrackEntity> listTracks) {
        this.title = title;
        this.artistEntity = artistEntity;
        this.listTracks = listTracks;
    }

    public AlbumEntity() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArtistEntity getArtistEntity() {
        return artistEntity;
    }

    public void setArtistEntity(ArtistEntity artistEntity) {
        this.artistEntity = artistEntity;
    }

	public List<TrackEntity> getListTracks() {
		return listTracks;
	}

	public void setListTracks(List<TrackEntity> listTracks) {
		this.listTracks = listTracks;
	}
    
}
