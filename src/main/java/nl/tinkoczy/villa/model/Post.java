package nl.tinkoczy.villa.model;

import java.math.BigDecimal;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Post {

	private final ObjectProperty<Long> postId;
	private final ObjectProperty<Integer> postNummer;
	private final StringProperty postOmschrijving;
	private final IntegerProperty postPassivaRekening;
	private final ObjectProperty<BigDecimal> postStandaardBedrag;
	private final StringProperty postStandaardBoekingOmschrijving;

	public Post() {
		this(null, null, null);
	}

	public Post(final Long postId, final Integer postNummer, final String postOmschrijving) {
		this.postId = new SimpleObjectProperty<Long>(postId);
		this.postNummer = new SimpleObjectProperty<Integer>(postNummer);
		this.postOmschrijving = new SimpleStringProperty(postOmschrijving);

		// Some initial dummy data, just for convenient testing.
		this.postPassivaRekening = new SimpleIntegerProperty(1);
		this.postStandaardBedrag = new SimpleObjectProperty<BigDecimal>(new BigDecimal(0));
		this.postStandaardBoekingOmschrijving = new SimpleStringProperty("");
	}

	public Long getPostId() {
		return postId.get();
	}

	public void setPostId(final Long postId) {
		this.postId.set(postId);
	}

	public ObjectProperty<Long> postIdProperty() {
		return postId;
	}

	public Integer getPostNummer() {
		return postNummer.get();
	}

	public void setPostNummer(final Integer postNummer) {
		this.postNummer.set(postNummer);
	}

	public ObjectProperty<Integer> postNummerProperty() {
		return postNummer;
	}

	public String getPostOmschrijving() {
		return postOmschrijving.get();
	}

	public void setPostOmschrijving(final String postOmschrijving) {
		this.postOmschrijving.set(postOmschrijving);
	}

	public StringProperty postOmschrijvingProperty() {
		return postOmschrijving;
	}

	public Integer getPostPassivaRekening() {
		return postPassivaRekening.get();
	}

	public void setPostPassivaRekening(final Integer postPassivaRekening) {
		this.postPassivaRekening.set(postPassivaRekening);
	}

	public IntegerProperty postPassivaRekeningProperty() {
		return postPassivaRekening;
	}

	public BigDecimal getPostStandaardBedrag() {
		return postStandaardBedrag.get();
	}

	public void setPostStandaardBedrag(final BigDecimal postStandaardBedrag) {
		this.postStandaardBedrag.set(postStandaardBedrag);
	}

	public ObjectProperty<BigDecimal> postStandaardBedragProperty() {
		return postStandaardBedrag;
	}

	public String getPostStandaardBoekingOmschrijving() {
		return postStandaardBoekingOmschrijving.get();
	}

	public void setPostStandaardBoekingOmschrijving(final String postStandaardBoekingOmschrijving) {
		this.postStandaardBoekingOmschrijving.set(postStandaardBoekingOmschrijving);
	}

	public StringProperty postStandaardBoekingOmschrijvingProperty() {
		return postStandaardBoekingOmschrijving;
	}
}
