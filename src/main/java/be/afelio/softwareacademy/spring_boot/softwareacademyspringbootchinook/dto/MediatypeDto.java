package be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto;

import java.util.Objects;

public class MediatypeDto {
    private String name;

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
        MediatypeDto that = (MediatypeDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
