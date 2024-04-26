package co.com.tecso.utoppia.challenge.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class EpochConverter {

	private EpochConverter() {}
	
	public static LocalDateTime toLocalDateTime(long epochTime) {
		Instant instant = Instant.ofEpochSecond(epochTime);
        ZoneId zoneId = ZoneId.of("UTC");
		ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
		return zonedDateTime.toLocalDateTime();
	}
	
}
