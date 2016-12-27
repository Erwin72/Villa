package nl.tinkoczy.villa.model;

import java.math.BigDecimal;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

public class BijdrageRente {

	private final LongProperty bijdrageRenteId;
	private final ObjectProperty<BigDecimal> bijdrageRentePercentage;
	private final IntegerProperty bijdrageRenteNaVervaldatum;

	public BijdrageRente() {
		this(null, null, null);
	}

	public BijdrageRente(final Long bijdrageRenteId, final BigDecimal bijdrageRentePercentage,
			final Integer bijdrageRenteNaVervaldatum) {
		this.bijdrageRenteId = new SimpleLongProperty(bijdrageRenteId);
		this.bijdrageRentePercentage = new SimpleObjectProperty<>(bijdrageRentePercentage);
		this.bijdrageRenteNaVervaldatum = new SimpleIntegerProperty(bijdrageRenteNaVervaldatum);
	}

	public Long getBijdrageRenteId() {
		return bijdrageRenteId.get();
	}

	public void setBijdrageRenteId(final Long bijdrageRenteId) {
		this.bijdrageRenteId.set(bijdrageRenteId);
	}

	public LongProperty bijdrageRenteIdProperty() {
		return bijdrageRenteId;
	}

	public BigDecimal getBijdrageRentePercentage() {
		return bijdrageRentePercentage.get();
	}

	public void setBijdrageRentePercentage(final BigDecimal bijdrageRentePercentage) {
		this.bijdrageRentePercentage.set(bijdrageRentePercentage);
	}

	public ObjectProperty<BigDecimal> bijdrageRentePercentageProperty() {
		return bijdrageRentePercentage;
	}

	public Integer getBijdrageRenteNaVervaldatum() {
		return bijdrageRenteNaVervaldatum.get();
	}

	public void setBijdrageRenteNaVervaldatum(final Integer bijdrageRenteNaVervaldatum) {
		this.bijdrageRenteNaVervaldatum.set(bijdrageRenteNaVervaldatum);
	}

	public IntegerProperty bijdrageRenteNaVervaldatumProperty() {
		return bijdrageRenteNaVervaldatum;
	}
}
