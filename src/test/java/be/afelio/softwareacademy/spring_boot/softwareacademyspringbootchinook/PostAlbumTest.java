package be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook;

import be.afelio.softwareacademy.spring_boot.softwareacademyspringbootchinook.dto.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class PostAlbumTest {
	
	@Autowired TestRestTemplate restTemplate;
	@Autowired JdbcTemplate jdbcTemplate;
	ObjectMapper mapper = new ObjectMapper();

	@Ignore
	@Test
	public void test() throws Exception{
		try{
			CreateAlbumDto albumDto = createAlbumDto();
			RequestEntity<CreateAlbumDto> requestEntity =
					new RequestEntity<CreateAlbumDto>(albumDto, HttpMethod.POST, URI.create("/album"));
			ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
			assertEquals(200, response.getStatusCodeValue());

			String body = response.getBody();
			TypeReference<ResponseDto<Void>> type = new TypeReference<ResponseDto<Void>>() {};
			ResponseDto<Void> responseDto = mapper.readValue(body, type);

			assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
			assertNull(responseDto.getPayload());
			assertTrue(checkAlbumCreated());
		}finally {
			jdbcTemplate.update("delete from track where albumid = (select a.albumid from album a where title = 'If You Can Believe Your Eyes and Ears')");
			jdbcTemplate.update("delete from album where title = 'If You Can Believe Your Eyes and Ears'");
		}
		ResponseEntity<String> response = restTemplate.getForEntity("/album", String.class);
		
		;
		
	}

	private boolean checkAlbumCreated() {
		boolean created = false;
		Integer id = jdbcTemplate.queryForObject("select albumId from album "
				+ "where title = 'If You Can Believe Your Eyes and Ears'", Integer.class);
		created = id != null;
		return created;
	}

	CreateAlbumDto createAlbumDto() {
		GenresDto genresDto = new GenresDto();
		genresDto.setName("Genre");
		GenresDto[] genresDtoTab = {genresDto};
		CreateAlbumDto albumDto = new CreateAlbumDto("If You Can Believe Your Eyes and Ears","The Mamas and the Papas", genresDtoTab);
		CreateTrackDto trackDto = new CreateTrackDto("Monday, Monday", "Rock",0.99,"MPEG audio file", "John Phillips", 7000000 , "3:28" );
		CreateTrackDto[] trackDtoTab = {trackDto};
		albumDto.setTracks(trackDtoTab);
		System.out.println(albumDto);
		return albumDto;
	}

	

}





