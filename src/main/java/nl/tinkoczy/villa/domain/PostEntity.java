package nl.tinkoczy.villa.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "posten")
@NamedQueries({ @NamedQuery(name = "PostEntity.findAll", query = "SELECT p FROM PostEntity p ORDER BY p.postNummer"),
		@NamedQuery(name = "PostEntity.findById", query = "SELECT p FROM PostEntity p WHERE p.id = :id"),
		@NamedQuery(name = "PostEntity.findByPostNummer", query = "SELECT p FROM PostEntity p WHERE p.postNummer = :postNummer"),
		@NamedQuery(name = "PostEntity.findAllByRubriek", query = "SELECT p FROM PostEntity p WHERE p.rubriek = :rubriek ORDER BY p.postNummer") })
public class PostEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "post_id")
	private long id;
	@Column(name = "post_nummer")
	private int postNummer;
	@Column(name = "post_omschrijving")
	private String postOmschrijving;
	@Column(name = "post_passiva_rekening")
	private int postPassivaRekening;
	@Column(name = "post_standaard_bedrag")
	private BigDecimal postStandaardBedrag;
	@Column(name = "post_standaard_boeking_omschrijving")
	private String postStandaardBoekingOmschrijving;
	@ManyToOne
	@JoinColumn(name = "rubriek_fk")
	private RubriekEntity rubriek;

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public int getPostNummer() {
		return postNummer;
	}

	public void setPostNummer(final int postNummer) {
		this.postNummer = postNummer;
	}

	public String getPostOmschrijving() {
		return postOmschrijving;
	}

	public void setPostOmschrijving(final String postOmschrijving) {
		this.postOmschrijving = postOmschrijving;
	}

	public int getPostPassivaRekening() {
		return postPassivaRekening;
	}

	public void setPostPassivaRekening(final int postPassivaRekening) {
		this.postPassivaRekening = postPassivaRekening;
	}

	public BigDecimal getPostStandaardBedrag() {
		return postStandaardBedrag;
	}

	public void setPostStandaardBedrag(final BigDecimal postStandaardBedrag) {
		this.postStandaardBedrag = postStandaardBedrag;
	}

	public String getPostStandaardBoekingOmschrijving() {
		return postStandaardBoekingOmschrijving;
	}

	public void setPostStandaardBoekingOmschrijving(final String postStandaardBoekingOmschrijving) {
		this.postStandaardBoekingOmschrijving = postStandaardBoekingOmschrijving;
	}

	public RubriekEntity getRubriek() {
		return rubriek;
	}

	public void setRubriek(final RubriekEntity rubriek) {
		this.rubriek = rubriek;
	}

	@Override
	public boolean equals(final Object o) {
		if (!(o instanceof PostEntity)) {
			return false;
		}
		PostEntity other = (PostEntity) o;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(getPostNummer(), other.getPostNummer());
		return builder.isEquals();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getPostNummer());
		return builder.hashCode();
	}

	@Override
	public String toString() {
		return "PostEntity [id=" + id + ", postNummer=" + postNummer + ", postOmschrijving=" + postOmschrijving
				+ ", postPassivaRekening=" + postPassivaRekening + ", postStandaardBedrag=" + postStandaardBedrag
				+ ", postStandaardBoekingOmschrijving=" + postStandaardBoekingOmschrijving + ", rubriek=" + rubriek
				+ "]";
	}
}
