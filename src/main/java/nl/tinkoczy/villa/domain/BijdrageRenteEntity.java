package nl.tinkoczy.villa.domain;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "bijdragerentes")
@NamedQueries({
		@NamedQuery(name = "BijdrageRenteEntity.findAll", query = "SELECT b FROM BijdrageRenteEntity b ORDER BY b.id"),
		@NamedQuery(name = "BijdrageRenteEntity.findById", query = "SELECT b FROM BijdrageRenteEntity b WHERE b.id = :id") })
public class BijdrageRenteEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bijdrage_rente_id")
	private long id;
	@Column(name = "bijdrage_rente_percentage")
	private BigDecimal bijdrageRentePercentage;
	@Column(name = "bijdrage_rente_dagen_na_vervaldatum")
	private int bijdrageRenteNaVervaldatum;

	public BijdrageRenteEntity() {
		super();
	}

	public BijdrageRenteEntity(final long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public BigDecimal getBijdrageRentePercentage() {
		return bijdrageRentePercentage;
	}

	public void setBijdrageRentePercentage(final BigDecimal bijdrageRentePercentage) {
		this.bijdrageRentePercentage = bijdrageRentePercentage;
	}

	public int getBijdrageRenteNaVervaldatum() {
		return bijdrageRenteNaVervaldatum;
	}

	public void setBijdrageRenteNaVervaldatum(final int bijdrageRenteNaVervaldatum) {
		this.bijdrageRenteNaVervaldatum = bijdrageRenteNaVervaldatum;
	}

	@Override
	public boolean equals(final Object o) {
		if (!(o instanceof BijdrageFrequentieEntity)) {
			return false;
		}
		BijdrageFrequentieEntity other = (BijdrageFrequentieEntity) o;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(getId(), other.getId());
		return builder.isEquals();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getId());
		return builder.hashCode();
	}

	@Override
	public String toString() {
		return "BijdrageRenteEntity [id=" + id + ", bijdrageRentePercentage=" + bijdrageRentePercentage
				+ ", bijdrageRenteNaVervaldatum=" + bijdrageRenteNaVervaldatum + "]";
	}
}
