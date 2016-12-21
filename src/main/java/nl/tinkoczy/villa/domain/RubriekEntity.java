package nl.tinkoczy.villa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "rubrieken")
@NamedQueries({
		@NamedQuery(name = "RubriekEntity.findAll", query = "SELECT r FROM RubriekEntity r ORDER BY r.rubriekNummer"),
		@NamedQuery(name = "RubriekEntity.findById", query = "SELECT r FROM RubriekEntity r WHERE r.id = :id"),
		@NamedQuery(name = "RubriekEntity.findByRubriekNummer", query = "SELECT r FROM RubriekEntity r WHERE r.rubriekNummer = :rubriekNummer") })
public class RubriekEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rubriek_id")
	private long id;
	@Column(name = "rubriek_nummer")
	private int rubriekNummer;
	@Column(name = "rubriek_omschrijving")
	private String rubriekOmschrijving;
	@Column(name = "rubriek_type")
	private String rubriekType;
	@Column(name = "rubriek_in_uit")
	private String rubriekInUit;
	@Column(name = "rubriek_inexplo_rekening")
	private Boolean rubriekInExploRekening;

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public int getRubriekNummer() {
		return rubriekNummer;
	}

	public void setRubriekNummer(final int rubriekNummer) {
		this.rubriekNummer = rubriekNummer;
	}

	public String getRubriekOmschrijving() {
		return rubriekOmschrijving;
	}

	public void setRubriekOmschrijving(final String rubriekOmschrijving) {
		this.rubriekOmschrijving = rubriekOmschrijving;
	}

	public String getRubriekType() {
		return rubriekType;
	}

	public void setRubriekType(final String rubriekType) {
		this.rubriekType = rubriekType;
	}

	public String getRubriekInUit() {
		return rubriekInUit;
	}

	public void setRubriekInUit(final String rubriekInUit) {
		this.rubriekInUit = rubriekInUit;
	}

	public Boolean getRubriekInExploRekening() {
		return rubriekInExploRekening;
	}

	public void setRubriekInExploRekening(final Boolean rubriekInExploRekening) {
		this.rubriekInExploRekening = rubriekInExploRekening;
	}

	@Override
	public boolean equals(final Object o) {
		if (!(o instanceof RubriekEntity)) {
			return false;
		}
		RubriekEntity other = (RubriekEntity) o;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(getRubriekNummer(), other.getRubriekNummer());
		return builder.isEquals();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getRubriekNummer());
		return builder.hashCode();
	}

	@Override
	public String toString() {
		return "RubriekEntity [id=" + id + ", rubriekNummer=" + rubriekNummer + ", rubriekOmschrijving="
				+ rubriekOmschrijving + ", rubriekType=" + rubriekType + ", rubriekInUit=" + rubriekInUit
				+ ", rubriekInExploRekening=" + rubriekInExploRekening + "]";
	}
}
