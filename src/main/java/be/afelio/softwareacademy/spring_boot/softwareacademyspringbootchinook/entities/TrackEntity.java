package be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Track")
@Table(name = "Track")
public class TrackEntity {

	@Id
	@GeneratedValue()
	@Column(name = "Trackid")
	private Integer id;
	@Column(name="Name")
	private String name;
	@ManyToOne
	@JoinColumn(name="Genreid")
	private GenreEntity genreEntity;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name= "Albumid")
	private AlbumEntity albumEntity;
	@ManyToOne
	@JoinColumn(name = "Mediatypeid")
	private MediatypeEntity mediatype;
	@Column(name = "Composer")
	private String composer;
	@Column(name = "Milliseconds")
	private int milliseconds;
	@Column(name = "Bytes")
	private int bytes;
	@Column(name = "Unitprice")
	private double unitprice;

	public TrackEntity() {
	}

	public TrackEntity(String name, GenreEntity genreEntity, AlbumEntity albumEntity, MediatypeEntity mediatype, String composer, int milliseconds, int bytes, double unitprice) {
		this.name = name;
		this.genreEntity = genreEntity;
		this.albumEntity = albumEntity;
		this.mediatype = mediatype;
		this.composer = composer;
		this.milliseconds = milliseconds;
		this.bytes = bytes;
		this.unitprice = unitprice;
	}

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
	public GenreEntity getGenreEntity() {
		return genreEntity;
	}
	public void setGenreEntity(GenreEntity genreEntity) {
		this.genreEntity = genreEntity;
	}

	public AlbumEntity getAlbumEntity() {
		return albumEntity;
	}

	public void setAlbumEntity(AlbumEntity albumEntity) {
		this.albumEntity = albumEntity;
	}

	public MediatypeEntity getMediatype() {
		return mediatype;
	}

	public void setMediatype(MediatypeEntity mediatype) {
		this.mediatype = mediatype;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public int getMilliseconds() {
		return milliseconds;
	}

	public void setMilliseconds(int milliseconds) {
		this.milliseconds = milliseconds;
	}

	public int getBytes() {
		return bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	public double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}
}
