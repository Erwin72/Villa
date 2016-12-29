package nl.tinkoczy.villa.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "bijdragefrequenties")
@NamedQueries({
		@NamedQuery(name = "BijdrageFrequentieEntity.findAll", query = "SELECT b FROM BijdrageFrequentieEntity b ORDER BY b.bijdrageFrequentieAantalBetaalmomenten desc"),
		@NamedQuery(name = "BijdrageFrequentieEntity.findById", query = "SELECT b FROM BijdrageFrequentieEntity b WHERE b.id = :id"),
		@NamedQuery(name = "BijdrageFrequentieEntity.findByFrequentieCode", query = "SELECT b FROM BijdrageFrequentieEntity b WHERE b.bijdrageFrequentieCode = :bijdrageFrequentieCode") })
public class BijdrageFrequentieEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bijdrage_frequentie_id")
	private long id;
	@Column(name = "bijdrage_frequentie_code")
	private int bijdrageFrequentieCode;
	@Column(name = "bijdrage_frequentie_aantal_betaalmomenten")
	private int bijdrageFrequentieAantalBetaalmomenten;
	@Column(name = "bijdrage_frequentie_omschrijving")
	private String bijdrageFrequentieOmschrijving;
	@OneToMany(mappedBy = "bijdrageFrequentie")
	private List<BijdrageSchemaEntity> bijdrageSchemas;

	public BijdrageFrequentieEntity() {
		super();
	}

	public BijdrageFrequentieEntity(final long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public int getBijdrageFrequentieCode() {
		return bijdrageFrequentieCode;
	}

	public void setBijdrageFrequentieCode(final int bijdrageFrequentieCode) {
		this.bijdrageFrequentieCode = bijdrageFrequentieCode;
	}

	public int getBijdrageFrequentieAantalBetaalmomenten() {
		return bijdrageFrequentieAantalBetaalmomenten;
	}

	public void setBijdrageFrequentieAantalBetaalmomenten(final int bijdrageFrequentieAantalBetaalmomenten) {
		this.bijdrageFrequentieAantalBetaalmomenten = bijdrageFrequentieAantalBetaalmomenten;
	}

	public String getBijdrageFrequentieOmschrijving() {
		return bijdrageFrequentieOmschrijving;
	}

	public void setBijdrageFrequentieOmschrijving(final String bijdrageFrequentieOmschrijving) {
		this.bijdrageFrequentieOmschrijving = bijdrageFrequentieOmschrijving;
	}

	@Override
	public boolean equals(final Object o) {
		if (!(o instanceof BijdrageFrequentieEntity)) {
			return false;
		}
		BijdrageFrequentieEntity other = (BijdrageFrequentieEntity) o;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(getBijdrageFrequentieCode(), other.getBijdrageFrequentieCode());
		return builder.isEquals();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getBijdrageFrequentieCode());
		return builder.hashCode();
	}

	@Override
	public String toString() {
		return "BijdrageFrequentieEntity [id=" + id + ", bijdrageFrequentieCode=" + bijdrageFrequentieCode
				+ ", bijdrageFrequentieAantalBetaalmomenten=" + bijdrageFrequentieAantalBetaalmomenten
				+ ", bijdrageFrequentieOmschrijving=" + bijdrageFrequentieOmschrijving + "]";
	}
}
