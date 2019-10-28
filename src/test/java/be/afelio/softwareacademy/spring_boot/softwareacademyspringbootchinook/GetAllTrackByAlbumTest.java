package be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook;

import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GetAllTrackByAlbumTest {
	
	@Autowired TestRestTemplate restTemplate;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void test() throws Exception{
		ResponseEntity<String> response = restTemplate.getForEntity("/track/album/Live After Death", String.class);
		
		assertEquals(200, response.getStatusCodeValue());
		
		String body = response.getBody();
		TypeReference<ResponseDto<List<TrackDto>>> type = new TypeReference<ResponseDto<List<TrackDto>>>() {};
		ResponseDto<List<TrackDto>> responseDto = mapper.readValue(body, type);
		
		assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
		assertNotNull(responseDto.getPayload());
		assertEquals(18, responseDto.getPayload().size());
		TrackDto trackDto = createTrackDto();
		assertTrue(responseDto.getPayload().contains(trackDto));
		assertEquals("18 tracks found", responseDto.getMessage());
		
	}
	
	TrackDto createTrackDto() {
		GenresDto genresDto = new GenresDto();
		genresDto.setName("Heavy Metal");
		AlbumDto albumDto = new AlbumDto();
		albumDto.setTitle("Live After Death");
		MediatypeDto mediatypeDto = new MediatypeDto();
		mediatypeDto.setName("MPEG audio file");
		TrackDto trackDto = new TrackDto("Phantom Of The Opera", genresDto, albumDto, mediatypeDto, "Steve Harris", 441000, 10589917, 0.99);
		return trackDto;
	}


	

}





