package be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto;

import java.util.Objects;

public class TrackDto {
    private String name;
    private GenresDto genre;
    private AlbumDto album;
    private MediatypeDto mediatype;
    private String composer;
    private int milliseconds;
    private int seconds;
    private int bytes;
    private double unitprice;

    public TrackDto(String name,
                    GenresDto genre,
                    AlbumDto album,
                    MediatypeDto mediatype,
                    String composer,
                    int milliseconds,
                    int bytes,
                    double unitprice) {
        this.name = name;
        this.genre = genre;
        this.album = album;
        this.mediatype = mediatype;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitprice = unitprice;
        setSeconds(milliseconds/1000);
    }

    public TrackDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenresDto getGenre() {
        return genre;
    }

    public void setGenre(GenresDto genre) {
        this.genre = genre;
    }

    public AlbumDto getAlbum() {
        return album;
    }

    public void setAlbum(AlbumDto album) {
        this.album = album;
    }

    public MediatypeDto getMediatype() {
        return mediatype;
    }

    public void setMediatype(MediatypeDto mediatype) {
        this.mediatype = mediatype;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public int getSeconds() {
        return milliseconds/1000;
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

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackDto trackDto = (TrackDto) o;
        return seconds == trackDto.seconds &&
                bytes == trackDto.bytes &&
                Double.compare(trackDto.unitprice, unitprice) == 0 &&
                Objects.equals(name, trackDto.name) &&
                Objects.equals(genre, trackDto.genre) &&
                Objects.equals(album, trackDto.album) &&
                Objects.equals(mediatype, trackDto.mediatype) &&
                Objects.equals(composer, trackDto.composer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, genre, album, mediatype, composer, milliseconds, bytes, unitprice);
    }
}
