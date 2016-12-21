package nl.tinkoczy.villa.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Rubriek {

	private final ObjectProperty<Long> rubriekId;
	private final ObjectProperty<Integer> rubriekNummer;
	private final StringProperty rubriekOmschrijving;
	private final StringProperty rubriekType;
	private final StringProperty rubriekInUit;
	private final BooleanProperty rubriekInExploRekening;

	public Rubriek() {
		this(null, null, null);
	}

	public Rubriek(final Long rubriekId, final Integer rubriekNummer, final String rubriekOmschrijving) {
		this.rubriekId = new SimpleObjectProperty<Long>(rubriekId);
		this.rubriekNummer = new SimpleObjectProperty<Integer>(rubriekNummer);
		this.rubriekOmschrijving = new SimpleStringProperty(rubriekOmschrijving);

		// Some initial dummy data, just for convenient testing.
		this.rubriekType = new SimpleStringProperty("T");
		this.rubriekInUit = new SimpleStringProperty("U");
		this.rubriekInExploRekening = new SimpleBooleanProperty(false);
	}

	public Long getRubriekId() {
		return rubriekId.get();
	}

	public void setRubriekId(final Long rubriekId) {
		this.rubriekId.set(rubriekId);
	}

	public ObjectProperty<Long> rubriekIdProperty() {
		return rubriekId;
	}

	public Integer getRubriekNummer() {
		return rubriekNummer.get();
	}

	public void setRubriekNummer(final Integer rubriekNummer) {
		this.rubriekNummer.set(rubriekNummer);
	}

	public ObjectProperty<Integer> rubriekNummerProperty() {
		return rubriekNummer;
	}

	public String getRubriekOmschrijving() {
		return rubriekOmschrijving.get();
	}

	public void setRubriekOmschrijving(final String rubriekOmschrijving) {
		this.rubriekOmschrijving.set(rubriekOmschrijving);
	}

	public StringProperty rubriekOmschrijvingProperty() {
		return rubriekOmschrijving;
	}

	public String getRubriekType() {
		return rubriekType.get();
	}

	public void setRubriekType(final String rubriekType) {
		this.rubriekType.set(rubriekType);
	}

	public StringProperty rubriekTypeProperty() {
		return rubriekType;
	}

	public String getRubriekInUit() {
		return rubriekInUit.get();
	}

	public void setRubriekInUit(final String rubriekInUit) {
		this.rubriekInUit.set(rubriekInUit);
	}

	public StringProperty rubriekInUitProperty() {
		return rubriekInUit;
	}

	public Boolean isRubriekInExploRekening() {
		return rubriekInExploRekening.get();
	}

	public void setRubriekInExploRekening(final Boolean rubriekInExploRekening) {
		this.rubriekInExploRekening.set(rubriekInExploRekening);
	}

	public BooleanProperty rubriekInExploRekeningProperty() {
		return rubriekInExploRekening;
	}
}
