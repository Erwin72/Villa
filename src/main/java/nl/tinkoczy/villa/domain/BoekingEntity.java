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

@Entity
@Table(name = "boekingen")
@NamedQueries({
		@NamedQuery(name = "BoekingEntity.findAllSortByDatum", query = "SELECT b FROM BoekingEntity b ORDER BY b.boekingDatum"),
		@NamedQuery(name = "BoekingEntity.findAllSortByPostAndDatum", query = "SELECT b FROM BoekingEntity b ORDER BY b.post, b.boekingDatum"),
		@NamedQuery(name = "BoekingEntity.findById", query = "SELECT b FROM BoekingEntity b WHERE b.id = :id"),
		@NamedQuery(name = "BoekingEntity.findAllByBoekstuk", query = "SELECT b FROM BoekingEntity b WHERE b.boekstuk = :boekstuk"),
		@NamedQuery(name = "BoekingEntity.findAllByPost", query = "SELECT b FROM BoekingEntity b WHERE b.post = :post") })
public class BoekingEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "boeking_id")
	private long id;
	@Column(name = "boeking_datum")
	private LocalDate boekingDatum;
	@Column(name = "boeking_bedrag")
	private BigDecimal boekingBedrag;
	@Column(name = "boeking_omschrijving")
	private String boekingOmschrijving;
	@Column(name = "boeking_vorige_periode")
	private Boolean boekingVorigePeriode;

	@ManyToOne
	@JoinColumn(name = "boekstuk_fk")
	private BoekstukEntity boekstuk;
	@ManyToOne
	@JoinColumn(name = "post_fk")
	private PostEntity post;
	@ManyToOne
	@JoinColumn(name = "faktuur_fk")
	private FaktuurEntity faktuur;

	public BoekingEntity() {
		super();
	}

	public BoekingEntity(final long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public LocalDate getBoekingDatum() {
		return boekingDatum;
	}

	public void setBoekingDatum(final LocalDate boekingDatum) {
		this.boekingDatum = boekingDatum;
	}

	public BigDecimal getBoekingBedrag() {
		return boekingBedrag;
	}

	public void setBoekingBedrag(final BigDecimal boekingBedrag) {
		this.boekingBedrag = boekingBedrag;
	}

	public String getBoekingOmschrijving() {
		return boekingOmschrijving;
	}

	public void setBoekingOmschrijving(final String boekingOmschrijving) {
		this.boekingOmschrijving = boekingOmschrijving;
	}

	public Boolean getBoekingVorigePeriode() {
		return boekingVorigePeriode;
	}

	public void setBoekingVorigePeriode(final Boolean boekingVorigePeriode) {
		this.boekingVorigePeriode = boekingVorigePeriode;
	}

	public BoekstukEntity getBoekstuk() {
		return boekstuk;
	}

	public void setBoekstuk(final BoekstukEntity boekstuk) {
		this.boekstuk = boekstuk;
	}

	public PostEntity getPost() {
		return post;
	}

	public void setPost(final PostEntity post) {
		this.post = post;
	}

	public FaktuurEntity getFaktuur() {
		return faktuur;
	}

	public void setFaktuur(final FaktuurEntity faktuur) {
		this.faktuur = faktuur;
	}

	@Override
	public boolean equals(final Object o) {
		if (!(o instanceof BoekingEntity)) {
			return false;
		}
		BoekingEntity other = (BoekingEntity) o;
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
		return "BoekingEntity [id=" + id + ", boekingDatum=" + boekingDatum + ", boekingBedrag=" + boekingBedrag
				+ ", boekingOmschrijving=" + boekingOmschrijving + ", boekingVorigePeriode=" + boekingVorigePeriode
				+ ", boekstuk=" + boekstuk + ", post=" + post + ", faktuur=" + faktuur + "]";
	}
}
