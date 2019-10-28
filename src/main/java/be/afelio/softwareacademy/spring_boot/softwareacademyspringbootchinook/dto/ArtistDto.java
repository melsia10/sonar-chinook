package be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto;

import java.util.Objects;

public class ArtistDto {
	private String name;
	
	
	public ArtistDto() {}

	public ArtistDto(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ArtistDto artistDto = (ArtistDto) o;
		return Objects.equals(name, artistDto.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
