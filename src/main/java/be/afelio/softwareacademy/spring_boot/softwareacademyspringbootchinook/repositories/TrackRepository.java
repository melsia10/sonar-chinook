package be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.repositories;

import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.entities.TrackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface TrackRepository extends JpaRepository<TrackEntity, Integer> {


    List<TrackEntity> findAllByAlbumEntityTitle(String name);
}
