package nl.tinkoczy.villa.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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
import org.eclipse.persistence.annotations.Index;

@Entity
@Table(name = "bijdragen")
@NamedQueries({ @NamedQuery(name = "BijdrageEntity.findAll", query = "SELECT b FROM BijdrageEntity b ORDER BY b.id"),
		@NamedQuery(name = "BijdrageEntity.findAllOrderByDatum", query = "SELECT b FROM BijdrageEntity b ORDER BY b.bijdrageDatum, b.id"),
		@NamedQuery(name = "BijdrageEntity.findById", query = "SELECT b FROM BijdrageEntity b WHERE b.id = :id"),
		@NamedQuery(name = "BijdrageEntity.findAllByBijdrageSchema", query = "SELECT b FROM BijdrageEntity b WHERE b.bijdrageSchema = :bijdrageSchema ORDER BY b.id") })
public class BijdrageEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bijdrage_id")
	private long id;
	/*
	 * TODO oder by datum geeft BLOB exception...
	 */
	@Column(name = "bijdrage_datum")
	private LocalDate bijdrageDatum;
	@Column(name = "bijdrage_bedrag")
	private BigDecimal bijdrageBedrag;
	@Column(name = "bijdrage_voldaan")
	private Boolean bijdrageVoldaan;
	@ManyToOne
	@JoinColumn(name = "bijdrage_schema_fk")
	@Index
	private BijdrageSchemaEntity bijdrageSchema;

	public BijdrageEntity() {
		super();
	}

	public BijdrageEntity(final long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public LocalDate getBijdrageDatum() {
		return bijdrageDatum;
	}

	public void setBijdrageDatum(final LocalDate bijdrageDatum) {
		this.bijdrageDatum = bijdrageDatum;
	}

	public BigDecimal getBijdrageBedrag() {
		return bijdrageBedrag;
	}

	public void setBijdrageBedrag(final BigDecimal bijdrageBedrag) {
		this.bijdrageBedrag = bijdrageBedrag;
	}

	public Boolean getBijdrageVoldaan() {
		return bijdrageVoldaan;
	}

	public void setBijdrageVoldaan(final Boolean bijdrageVoldaan) {
		this.bijdrageVoldaan = bijdrageVoldaan;
	}

	public BijdrageSchemaEntity getBijdrageSchema() {
		return bijdrageSchema;
	}

	public void setBijdrageSchema(final BijdrageSchemaEntity bijdrageSchema) {
		this.bijdrageSchema = bijdrageSchema;
	}

	@Override
	public boolean equals(final Object o) {
		if (!(o instanceof BijdrageSchemaEntity)) {
			return false;
		}
		BijdrageSchemaEntity other = (BijdrageSchemaEntity) o;
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
		return "BijdrageEntity [id=" + id + ", bijdrageDatum=" + bijdrageDatum + ", bijdrageBedrag=" + bijdrageBedrag
				+ ", bijdrageVoldaan=" + bijdrageVoldaan + ", bijdrageSchema=" + bijdrageSchema + "]";
	}
}
