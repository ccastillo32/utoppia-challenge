package co.com.tecso.utoppia.challenge.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EpochConverterTests {

	@Test
	void toLocalDateTime() {
		
		LocalDateTime result = EpochConverter.toLocalDateTime(1714144111);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime expected = LocalDateTime.parse("2024-04-26 15:08:31", formatter);
		
		assertEquals(expected, result);
		
	}
	
}
