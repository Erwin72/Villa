package nl.tinkoczy.villa.config;

import static nl.tinkoczy.villa.config.ConfigType.BOOLEAN;
import static nl.tinkoczy.villa.config.ConfigType.DOUBLE;
import static nl.tinkoczy.villa.config.ConfigType.INT;
import static nl.tinkoczy.villa.config.ConfigType.STRING;

public enum ApplicationConfiguration implements ConfigItemEnum {

	DATABASE_NAME("VillaDB"),

	FXML_ROOT_LAYOUT("view/RootLayout.fxml"),
	FXML_SELECTEER_WERK_DATUM_DIALOG("view/SelecteerWerkDatumDialog.fxml"),

	FXML_APPARTEMENT_BIJDRAGE_OVERVIEW("view/appartement/AppartementBijdrageOverview.fxml"),
	FXML_APPARTEMENT_EDIT_DIALOG("view/appartement/AppartementEditDialog.fxml"),
	FXML_APPARTEMENT_OVERVIEW("view/appartement/AppartementOverview.fxml"),
	FXML_AUTO_TOEVOEGING_APPARTEMENT_DIALOG("view/appartement/AutoToevoegingAppartementenDialog.fxml"),

	FXML_BIJDRAGE_EDIT_DIALOG("view/bijdrage/BijdrageEditDialog.fxml"),
	FXML_BIJDRAGE_OVERVIEW("view/bijdrage/BijdrageOverview.fxml"),
	FXML_BIJDRAGE_SCHEMA_AUTOINVULLING_EDIT_DIALOG("view/bijdrage/BijdrageSchemaAutoInvullingEditDialog.fxml"),
	FXML_BIJDRAGE_SCHEMA_EDIT_DIALOG("view/bijdrage/BijdrageSchemaEditDialog.fxml"),
	FXML_BIJDRAGE_SCHEMA_OVERVIEW("view/bijdrage/BijdrageSchemaOverview.fxml"),

	FXML_RELATIE_EDIT_DIALOG("view/relatie/RelatieEditDialog.fxml"),
	FXML_RELATIE_OVERVIEW("view/relatie/RelatieOverview.fxml"),
	FXML_RELATIE_PERSOON_EDIT_DIALOG("view/relatie/RelatiePersoonEditDialog.fxml"),
	FXML_RELATIE_PERSOON_OVERVIEW("view/relatie/RelatiePersoonOverview.fxml"),

	FXML_POST_EDIT_DIALOG("view/rubriekpost/PostEditDialog.fxml"),
	FXML_POST_OVERVIEW("view/rubriekpost/PostOverview.fxml"),
	FXML_RUBRIEK_EDIT_DIALOG("view/rubriekpost/RubriekEditDialog.fxml"),
	FXML_RUBRIEK_OVERVIEW("view/rubriekpost/RubriekOverview.fxml");

	private final ConfigType type;
	private final Value defaultValue;

	private ApplicationConfiguration(final String stringDefaultValue) {
		type = STRING;
		defaultValue = new StringValue(stringDefaultValue);
	}

	private ApplicationConfiguration(final int intDefaultValue) {
		type = INT;
		defaultValue = new IntegerValue(intDefaultValue);
	}

	private ApplicationConfiguration(final boolean booleanDefaultValue) {
		type = BOOLEAN;
		defaultValue = new BooleanValue(booleanDefaultValue);
	}

	private ApplicationConfiguration(final double doubleDefaultValue) {
		type = DOUBLE;
		defaultValue = new DoubleValue(doubleDefaultValue);
	}

	@Override
	public ConfigType getType() {
		return type;
	}

	@Override
	public Value getDefaultValue() {
		return defaultValue;
	}
}
