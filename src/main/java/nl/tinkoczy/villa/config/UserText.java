package nl.tinkoczy.villa.config;

public enum UserText implements ConfigItemEnum {

    // Window title.
    APPLICATIETITEL("Villa"),

    // Tooltips for toolbar buttons
    TOOLBAR_TOOLTIP_INSTELLEN_WERKDATUM("Instellen werkdatum"),
    TOOLBAR_TOOLTIP_BEHEER_APPARTEMENTEN("Gegevens appartementen"),
    TOOLBAR_TOOLTIP_SELECTEER_APPARTEMENTEN("Selecteren van appartementen voor het printen van overzichten"),
    TOOLBAR_TOOLTIP_BEHEER_BOEKSTUKKEN("Invoeren en beheer van boekstukken en boekingen"),
    TOOLBAR_TOOLTIP_BEHEER_FAKTUREN("Invoeren en beheer van fakturen"),
    TOOLBAR_TOOLTIP_SELECTEER_FAKTUREN("Selecteren van fakturen voor het printen van overzichten"),
    TOOLBAR_TOOLTIP_BEHEER_RUBRIEKEN("Invoeren en beheer van rubrieken en posten"),
    TOOLBAR_TOOLTIP_OVERZICHT_SALDI("Overzicht van de saldi van uw rekeningen"),
    TOOLBAR_TOOLTIP_BEHEER_RELATIES("Invoeren en beheer van relaties"),
    TOOLBAR_TOOLTIP_CLOSE_VILLA("Verlaat villa");

	private final Value defaultValue;

	private UserText(final String stringDefaultValue) {
		this.defaultValue = new StringValue(stringDefaultValue);
	}

	@Override
	public ConfigType getType() {
		return ConfigType.STRING;
	}

	@Override
	public Value getDefaultValue() {
		return defaultValue;
	}

}
