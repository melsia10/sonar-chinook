package be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.controller;


import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto.ResponseDto;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto.ResponseDtoStatus;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto.TrackDto;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.persistence.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "track")
public class TrackController {

    @Autowired
    ApplicationRepository repository;

    @GetMapping(value = "album/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<List<TrackDto>>> findAllByArtist(
            @PathVariable("name") String name) {
        ResponseDto<List<TrackDto>> responseDto = null;
        try {
            List<TrackDto> listTrackDtos = repository.findAllByAlbumName(name);
            if (listTrackDtos != null) {
                responseDto = new ResponseDto<>(ResponseDtoStatus.SUCCESS, listTrackDtos.size() + " tracks found");
                responseDto.setPayload(listTrackDtos);
            }
        } catch (Exception e) {
            responseDto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "unexpected exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(responseDto);
    }

}
