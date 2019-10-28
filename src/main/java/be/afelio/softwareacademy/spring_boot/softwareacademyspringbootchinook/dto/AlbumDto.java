package be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AlbumDto {


    private String title;
    private ArtistDto artistDto;
    private GenresDto[] genresDto;
    private List<TrackDto> listTrackDto;

	public AlbumDto(String title, ArtistDto artist, GenresDto[] genres ) {
		this.title = title;
		this.artistDto = artist;
		this.genresDto = genres;
	}

	public AlbumDto() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArtistDto getArtistDto() {
		return artistDto;
	}

	public void setArtistDto(ArtistDto artistDto) {
		this.artistDto = artistDto;
	}

	public GenresDto[] getGenresDto() {
		return genresDto;
	}

	public void setGenresDto(GenresDto[] genresDto) {
		this.genresDto = genresDto;
	}

	public List<TrackDto> getListTrackDto() {
		return listTrackDto;
	}

	public void setListTrackDto(List<TrackDto> listTrackDto) {
		this.listTrackDto = listTrackDto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AlbumDto albumDto = (AlbumDto) o;
		return Objects.equals(title, albumDto.title) &&
				Objects.equals(artistDto, albumDto.artistDto) &&
				Arrays.equals(genresDto, albumDto.genresDto);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(title, artistDto);
		result = 31 * result + Arrays.hashCode(genresDto);
		return result;
	}
}
