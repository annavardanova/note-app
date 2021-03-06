package com.note.core.util.datetime;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * Trivial utility class to support redundant ZonedDateTime-related repetitive activities.
 * Potentially to be moved to a separate utility project to be shared with all other modules.
 * @author annavardanova
 *
 */
public class ZonedDateTimeUtil {
	
	
	public static ZonedDateTime getUTCNow() {
		return ZonedDateTime.now(ZoneOffset.UTC);
	}

}
