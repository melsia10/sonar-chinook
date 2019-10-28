package be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto;

import java.util.Objects;

public class GenresDto {
	private String name;
	public GenresDto(){}
    public GenresDto(String name) {
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
		GenresDto genresDto = (GenresDto) o;
		return Objects.equals(name, genresDto.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
