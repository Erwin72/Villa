package nl.tinkoczy.villa.util;

import javafx.util.StringConverter;
import nl.tinkoczy.villa.model.BijdrageSchema;

/**
 * Converts String to BijdrageSchema
 */
public class BijdrageSchemaStringConverter extends StringConverter<BijdrageSchema> {

	@Override
	public String toString(final BijdrageSchema object) {
		return object.getBijdrageSchemaNaam();
	}

	@Override
	public BijdrageSchema fromString(final String string) {
		return null;
	}

}
