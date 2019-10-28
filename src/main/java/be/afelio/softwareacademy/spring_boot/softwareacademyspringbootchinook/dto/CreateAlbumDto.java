package be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto;


import java.util.Objects;

public class CreateAlbumDto {


    private String title;
    private String artistName;
    private CreateTrackDto[] tracks;

	public CreateAlbumDto(String title, String artist, GenresDto[] genres ) {
		this.title = title;
		this.artistName = artist;
	}

	public CreateAlbumDto() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artistName;
	}

	public void setArtist(String artistDto) {
		this.artistName = artistName;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public CreateTrackDto[] getTracks() {
		return tracks;
	}

	public void setTracks(CreateTrackDto[] tracks) {
		this.tracks = tracks;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CreateAlbumDto albumDto = (CreateAlbumDto) o;
		return Objects.equals(title, albumDto.title) &&
				Objects.equals(artistName, albumDto.artistName);
	}

}
