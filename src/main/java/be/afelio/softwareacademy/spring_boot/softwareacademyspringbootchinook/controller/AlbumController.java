package be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.controller;

import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto.AlbumDto;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto.CreateAlbumDto;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto.ResponseDto;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto.ResponseDtoStatus;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.exceptions.DuplicatedAlbumException;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.exceptions.InvalidCreateParametersException;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.persistence.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@Controller
@RequestMapping(value = "album")
public class AlbumController {

    @Autowired
    ApplicationRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<List<AlbumDto>>> findAll() {
        ResponseDto<List<AlbumDto>> responseDto = null;
        try {
            List<AlbumDto> listAlbumsDto = repository.findAllAlbums();
            if (listAlbumsDto != null) {
                responseDto = new ResponseDto<List<AlbumDto>>(ResponseDtoStatus.SUCCESS, listAlbumsDto.size() + " albums found");
                responseDto.setPayload(listAlbumsDto);
            }
        } catch (Exception e) {
            responseDto = new ResponseDto<List<AlbumDto>>(ResponseDtoStatus.FAILURE, "unexpected exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(value = "artist/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<List<AlbumDto>>> findAllByArtist(
            @PathVariable("name") String name) {
        ResponseDto<List<AlbumDto>> responseDto = null;
        try {
            List<AlbumDto> listAlbumDtos = repository.findAllByArtistName(name);
            if (listAlbumDtos != null) {
                responseDto = new ResponseDto<List<AlbumDto>>(ResponseDtoStatus.SUCCESS, listAlbumDtos.size() + " albums found");
                responseDto.setPayload(listAlbumDtos);
            }
        } catch (Exception e) {
            responseDto = new ResponseDto<List<AlbumDto>>(ResponseDtoStatus.FAILURE, "unexpected exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(value = "genre/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<List<AlbumDto>>> findAllByGenre(
            @PathVariable("name") String name) {
        ResponseDto<List<AlbumDto>> responseDto = null;
        try {
            List<AlbumDto> albumDtoList = repository.findAllByGenreName(name);
            if(albumDtoList != null){
                responseDto = new ResponseDto<List<AlbumDto>>(ResponseDtoStatus.SUCCESS, albumDtoList.size() + " albums found");
                responseDto.setPayload(albumDtoList);
            }
        } catch (Exception e) {
            responseDto = new ResponseDto<List<AlbumDto>>(ResponseDtoStatus.FAILURE, "unexpected exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(responseDto);
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<Void>> createAlbum(@RequestBody CreateAlbumDto albumDto){
        ResponseDto<Void> responseDto = null;
        try {
          //  repository.createAlbum(albumDto);
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "album created");
        } catch(InvalidCreateParametersException e) {
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "invalid create parameters");
        } catch(DuplicatedAlbumException e) {
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "duplicated album");
        } catch(Exception e) {
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
        }
        return ResponseEntity.ok(responseDto);
    }
}
