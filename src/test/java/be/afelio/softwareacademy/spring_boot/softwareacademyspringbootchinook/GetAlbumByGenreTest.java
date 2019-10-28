package be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto.AlbumDto;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto.ArtistDto;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto.GenresDto;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto.ResponseDto;
import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto.ResponseDtoStatus;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GetAlbumByGenreTest {
	
	@Autowired TestRestTemplate restTemplate;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void test() throws Exception{
		ResponseEntity<String> response = restTemplate.getForEntity("/album/genre/Heavy Metal", String.class);
		
		assertEquals(200, response.getStatusCodeValue());
		
		String body = response.getBody();
		TypeReference<ResponseDto<List<AlbumDto>>> type = new TypeReference<ResponseDto<List<AlbumDto>>>() {};
		ResponseDto<List<AlbumDto>> responseDto = mapper.readValue(body, type);
		
		assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
		assertNotNull(responseDto.getPayload());
		assertEquals(3, responseDto.getPayload().size());
		AlbumDto albumDto = createAlbumDto();
		assertTrue(responseDto.getPayload().contains(albumDto));
		assertEquals("3 albums found", responseDto.getMessage());
		
	}
	
	AlbumDto createAlbumDto() {
		GenresDto[] genresDto = new GenresDto[2];
		genresDto[0] = new GenresDto("Heavy Metal");
		genresDto[1] = new GenresDto("Metal");
		ArtistDto artistDto = new ArtistDto("Iron Maiden");
		AlbumDto albumDto = new AlbumDto("Live After Death",artistDto,genresDto);
		System.out.println(albumDto);
		return albumDto;
	}

	

}





