package nl.tinkoczy.villa.model;

import java.math.BigDecimal;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Rekening {

	private final ObjectProperty<Long> rekeningId;
	private final StringProperty rekeningNaam;
	private final StringProperty rekeningBankNaam;
	private final StringProperty rekeningIBAN;
	private final StringProperty rekeningSoort;
	private final ObjectProperty<BigDecimal> rekeningBeginSaldo;
	private final BooleanProperty rekeningPassiva;

	public Rekening() {
		this(null, null, null);
	}

	public Rekening(final Long rekeningId, final String rekeningNaam, final BigDecimal rekeningBeginSaldo) {
		this.rekeningId = new SimpleObjectProperty<Long>(rekeningId);
		this.rekeningNaam = new SimpleStringProperty(rekeningNaam);
		this.rekeningBeginSaldo = new SimpleObjectProperty<BigDecimal>(rekeningBeginSaldo);

		// Some initial dummy data, just for convenient testing.
		this.rekeningBankNaam = new SimpleStringProperty("");
		this.rekeningIBAN = new SimpleStringProperty("");
		this.rekeningSoort = new SimpleStringProperty("");
		this.rekeningPassiva = new SimpleBooleanProperty(false);
	}

	public Long getRekeningId() {
		return rekeningId.get();
	}

	public void setRekeningId(final Long rekeningId) {
		this.rekeningId.set(rekeningId);
	}

	public ObjectProperty<Long> rekeningIdProperty() {
		return rekeningId;
	}

	public String getRekeningNaam() {
		return rekeningNaam.get();
	}

	public void setRekeningNaam(final String rekeningNaam) {
		this.rekeningNaam.set(rekeningNaam);
	}

	public StringProperty rekeningNaamProperty() {
		return rekeningNaam;
	}

	public String getRekeningBankNaam() {
		return rekeningBankNaam.get();
	}

	public void setRekeningBankNaam(final String rekeningBankNaam) {
		this.rekeningBankNaam.set(rekeningBankNaam);
	}

	public StringProperty rekeningBankNaamProperty() {
		return rekeningBankNaam;
	}

	public String getRekeningIBAN() {
		return rekeningIBAN.get();
	}

	public void setRekeningIBAN(final String rekeningIBAN) {
		this.rekeningIBAN.set(rekeningIBAN);
	}

	public StringProperty rekeningIBANProperty() {
		return rekeningIBAN;
	}

	public String getRekeningSoort() {
		return rekeningSoort.get();
	}

	public void setRekeningSoort(final String rekeningSoort) {
		this.rekeningSoort.set(rekeningSoort);
	}

	public StringProperty rekeningSoortProperty() {
		return rekeningSoort;
	}

	public BigDecimal getRekeningBeginSaldo() {
		return rekeningBeginSaldo.get();
	}

	public void setRekeningBeginSaldo(final BigDecimal rekeningBeginSaldo) {
		this.rekeningBeginSaldo.set(rekeningBeginSaldo);
	}

	public ObjectProperty<BigDecimal> rekeningBeginSaldoProperty() {
		return rekeningBeginSaldo;
	}

	public Boolean getRekeningPassiva() {
		return rekeningPassiva.get();
	}

	public void setRekeningPassiva(final Boolean rekeningPassiva) {
		this.rekeningPassiva.set(rekeningPassiva);
	}

	public BooleanProperty rekeningPassivaProperty() {
		return rekeningPassiva;
	}
}
