package be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto;

import java.util.Objects;

public class CreateTrackDto {
    private String name;
    private String genre;
    private double unitPrice;
    private String mediaType;
    private String composer;
    private int bytes;
    private String duration;

    public CreateTrackDto(String name, String genre, double unitPrice, String mediaType, String composer, int bytes, String duration) {
        this.name = name;
        this.genre = genre;
        this.unitPrice = unitPrice;
        this.mediaType = mediaType;
        this.composer = composer;
        this.bytes = bytes;
        this.duration = duration;
    }

    public CreateTrackDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public MediatypeDto getMediaType() {
    	MediatypeDto mediatypeDto = new MediatypeDto();
    	mediatypeDto.setName(this.mediaType);
        return mediatypeDto;
    }

    public void setMediaType(MediatypeDto mediaType) {
    	
        this.mediaType = mediaType.getName();
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateTrackDto that = (CreateTrackDto) o;
        return Double.compare(that.unitPrice, unitPrice) == 0 &&
                bytes == that.bytes &&
                Objects.equals(name, that.name) &&
                Objects.equals(genre, that.genre) &&
                Objects.equals(mediaType, that.mediaType) &&
                Objects.equals(composer, that.composer) &&
                Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, genre, unitPrice, mediaType, composer, bytes, duration);
    }
}
