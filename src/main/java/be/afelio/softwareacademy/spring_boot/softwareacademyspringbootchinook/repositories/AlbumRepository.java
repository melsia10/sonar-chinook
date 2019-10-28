package be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.entities.AlbumEntity;
import org.springframework.data.jpa.repository.Query;

public interface AlbumRepository extends JpaRepository<AlbumEntity, Integer> {

	List<AlbumEntity> findAllByArtistEntityName(String name);

@Query("select a from Album a where exists (select t from Track t where t.genreEntity.name = ?1 and t.albumEntity = a)")
    List<AlbumEntity> findAllByTrackGenreName(String name);

    AlbumEntity findOneByTitleIgnoreCase(String title);
}
