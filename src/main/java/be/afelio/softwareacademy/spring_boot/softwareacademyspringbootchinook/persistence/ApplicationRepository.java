package be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto.AlbumDto;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto.ArtistDto;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto.GenresDto;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto.MediatypeDto;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto.TrackDto;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.entities.AlbumEntity;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.entities.ArtistEntity;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.entities.TrackEntity;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.repositories.AlbumRepository;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.repositories.TrackRepository;

@Component
public class ApplicationRepository {

    @Autowired private AlbumRepository albumRepository;
    @Autowired private TrackRepository trackRepository;

    public List<AlbumDto> findAllAlbums() {
        List<AlbumEntity> listAlbums = albumRepository.findAll();
        return createListAlbumDtos(listAlbums);
    }
    public List<AlbumDto> createListAlbumDtos(List<AlbumEntity> listAlbumEntities) {
		List<AlbumDto> listAlbumsDto= new ArrayList<>();
		for (AlbumEntity albumEntity : listAlbumEntities) {
			AlbumDto albumDto = createAlbumDto(albumEntity);
			listAlbumsDto.add(albumDto);
		}
		
		return listAlbumsDto;
		
	}
	private AlbumDto createAlbumDto(AlbumEntity albumEntity) {
		AlbumDto albumDto = new AlbumDto();
		albumDto.setTitle(albumEntity.getTitle());
		albumDto.setArtistDto(createArtistDto(albumEntity.getArtistEntity()));
		albumDto.setGenresDto(createTabGenreDto(albumEntity.getListTracks()));
		return albumDto;
	}
	private ArtistDto createArtistDto(ArtistEntity artistEntity) {
		ArtistDto artistDto = new ArtistDto();
		artistDto.setName(artistEntity.getName());
		return artistDto;
	}

	private GenresDto[] createTabGenreDto(List<TrackEntity> listTrackEntities) {
		List<GenresDto> listGenresDtos = new ArrayList<>();
		for (TrackEntity trackEntity : listTrackEntities) {
			GenresDto genresDto = new GenresDto();
			genresDto.setName(trackEntity.getGenreEntity().getName());
			if(!listGenresDtos.contains(genresDto)) {
				listGenresDtos.add(genresDto);
			}
		}
		return listGenresDtos.toArray(new GenresDto[] {});
	}
	
	public List<AlbumDto> findAllByArtistName(String name) {
		List<AlbumDto> listAlbumDtos = new ArrayList<AlbumDto>();
		List<AlbumEntity> listaAlbumEntities = albumRepository.findAllByArtistEntityName(name);
		listAlbumDtos = createListAlbumDtos(listaAlbumEntities);
		return listAlbumDtos;
	}

    public List<AlbumDto> findAllByGenreName(String name) {
		List<AlbumDto> albumDtoList = new ArrayList<AlbumDto>();
		List<AlbumEntity> albumEntityList = albumRepository.findAllByTrackGenreName(name);
		albumDtoList = createListAlbumDtos(albumEntityList);

		return albumDtoList;
    }

    public List<TrackDto> findAllByAlbumName(String name) {
    	List<TrackDto> trackDtoList = new ArrayList<>();
    	List<TrackEntity> trackEntityList = trackRepository.findAllByAlbumEntityTitle(name);
    	trackDtoList = createListTrackDto(trackEntityList);
    	return trackDtoList;
    }

	private List<TrackDto> createListTrackDto(List<TrackEntity> trackEntityList) {
		List<TrackDto> listTracksDto = new ArrayList<>();
		for (TrackEntity track: trackEntityList) {
			GenresDto genresDto = new GenresDto();
			genresDto.setName(track.getGenreEntity().getName());
			AlbumDto albumDto = new AlbumDto();
			albumDto.setTitle(track.getAlbumEntity().getTitle());
			MediatypeDto mediatypeDto = new MediatypeDto();
			mediatypeDto.setName(track.getMediatype().getName());
			TrackDto trackDto = new TrackDto(
					track.getName(),
					genresDto,
					albumDto,
					mediatypeDto,
					track.getComposer(),
					track.getMilliseconds(),
					track.getBytes(),
					track.getUnitprice());
			listTracksDto.add(trackDto);
		}
		return listTracksDto;
	}
/*
	public void createAlbum(CreateAlbumDto albumDto) {
		if (!validateCreateParameters(albumDto)) {
			throw new InvalidCreateParametersException();
		}
		if (albumRepository.findOneByTitleIgnoreCase(albumDto.getTitle())!= null){
			throw new DuplicatedAlbumException();
		}
    	AlbumEntity albumEntity = createAlbumEntity(albumDto);
    	albumRepository.save(albumEntity);
	}

	private boolean validateCreateParameters(CreateAlbumDto albumDto) {
    	System.out.println(albumDto.getTracks() + albumDto.getTitle() + albumDto.getArtist());
    	String title = albumDto.getTitle();
    	String artist = albumDto.getArtist();
    	CreateTrackDto[] listCreateTrack = albumDto.getTracks();
    	return title != null && !title.isBlank()
				&& artist != null
				&& listCreateTrack != null;
	}

	private AlbumEntity createAlbumEntity(CreateAlbumDto albumDto) {
    	ArtistEntity artistEntity = new ArtistEntity();
    	artistEntity.setName(albumDto.getArtist());
    	List<TrackEntity> trackEntityList = new ArrayList<>();
		for (CreateTrackDto track: albumDto.getTracks()) {
			GenreEntity genreEntity = new GenreEntity();
			genreEntity.setName(track.getGenre());
			MediatypeEntity mediaType = new MediatypeEntity();
			String[] parts = track.getDuration().split(":");
			int millisecond = Integer.parseInt(parts[0])*60 + Integer.parseInt(parts[1]);
			TrackEntity trackEntity = new TrackEntity(
					track.getName(),
					genreEntity,
					null,
					mediaType,
					track.getComposer(),
					millisecond,
					track.getBytes(),
					0);
			trackEntityList.add(trackEntity);
		}
    	AlbumEntity albumEntity = new AlbumEntity(
    			albumDto.getTitle(),
				artistEntity,
				trackEntityList);
		for (TrackEntity track: trackEntityList) {
			track.setAlbumEntity(albumEntity);
		}
		return albumEntity;
	}
	*/
}
