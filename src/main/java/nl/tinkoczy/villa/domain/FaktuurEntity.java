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
@Table(name = "fakturen")
@NamedQueries({
		@NamedQuery(name = "FaktuurEntity.findAllSortByDatum", query = "SELECT f FROM FaktuurEntity f ORDER BY f.faktuurDatum"),
		@NamedQuery(name = "FaktuurEntity.findById", query = "SELECT f FROM FaktuurEntity f WHERE f.id = :id"),
		@NamedQuery(name = "FaktuurEntity.findByFaktuurNummer", query = "SELECT f FROM FaktuurEntity f WHERE f.faktuurNummer = :faktuurNummer"),
		@NamedQuery(name = "FaktuurEntity.findAllByRelatieSortByDatum", query = "SELECT f FROM FaktuurEntity f WHERE f.relatie = :relatie ORDER BY f.faktuurDatum") })
public class FaktuurEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "faktuur_id")
	private long id;
	@Column(name = "faktuur_nummer")
	private String faktuurNummer;
	@Column(name = "faktuur_datum")
	private LocalDate faktuurDatum;
	@Column(name = "faktuur_bedrag")
	private BigDecimal faktuurBedrag;
	@Column(name = "faktuur_saldo")
	private BigDecimal faktuurSaldo;
	@Column(name = "faktuur_omschrijving")
	private String faktuurOmschrijving;
	@Column(name = "faktuur_vorige_periode")
	private Boolean faktuurVorigePeriode;
	@Column(name = "faktuur_verwerkt")
	private Boolean faktuurVerwerkt;
	@ManyToOne
	@JoinColumn(name = "relatie_fk")
	private RelatieEntity relatie;
	@ManyToOne
	@JoinColumn(name = "post_fk")
	private PostEntity post;

	public FaktuurEntity() {
		super();
	}

	public FaktuurEntity(final long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getFaktuurNummer() {
		return faktuurNummer;
	}

	public void setFaktuurNummer(final String faktuurNummer) {
		this.faktuurNummer = faktuurNummer;
	}

	public LocalDate getFaktuurDatum() {
		return faktuurDatum;
	}

	public void setFaktuurDatum(final LocalDate faktuurDatum) {
		this.faktuurDatum = faktuurDatum;
	}

	public BigDecimal getFaktuurBedrag() {
		return faktuurBedrag;
	}

	public void setFaktuurBedrag(final BigDecimal faktuurBedrag) {
		this.faktuurBedrag = faktuurBedrag;
	}

	public BigDecimal getFaktuurSaldo() {
		return faktuurSaldo;
	}

	public void setFaktuurSaldo(final BigDecimal faktuurSaldo) {
		this.faktuurSaldo = faktuurSaldo;
	}

	public String getFaktuurOmschrijving() {
		return faktuurOmschrijving;
	}

	public void setFaktuurOmschrijving(final String faktuurOmschrijving) {
		this.faktuurOmschrijving = faktuurOmschrijving;
	}

	public Boolean getFaktuurVorigePeriode() {
		return faktuurVorigePeriode;
	}

	public void setFaktuurVorigePeriode(final Boolean faktuurVorigePeriode) {
		this.faktuurVorigePeriode = faktuurVorigePeriode;
	}

	public Boolean getFaktuurVerwerkt() {
		return faktuurVerwerkt;
	}

	public void setFaktuurVerwerkt(final Boolean faktuurVerwerkt) {
		this.faktuurVerwerkt = faktuurVerwerkt;
	}

	public RelatieEntity getRelatie() {
		return relatie;
	}

	public void setRelatie(final RelatieEntity relatie) {
		this.relatie = relatie;
	}

	public PostEntity getPost() {
		return post;
	}

	public void setPost(final PostEntity post) {
		this.post = post;
	}

	@Override
	public boolean equals(final Object o) {
		if (!(o instanceof FaktuurEntity)) {
			return false;
		}
		FaktuurEntity other = (FaktuurEntity) o;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(getFaktuurNummer(), other.getFaktuurNummer());
		return builder.isEquals();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getFaktuurNummer());
		return builder.hashCode();
	}

	@Override
	public String toString() {
		return "FaktuurEntity [id=" + id + ", faktuurNummer=" + faktuurNummer + ", faktuurDatum=" + faktuurDatum
				+ ", faktuurBedrag=" + faktuurBedrag + ", faktuurSaldo=" + faktuurSaldo + ", faktuurOmschrijving="
				+ faktuurOmschrijving + ", faktuurVorigePeriode=" + faktuurVorigePeriode + ", faktuurVerwerkt="
				+ faktuurVerwerkt + ", relatie=" + relatie + ", post=" + post + "]";
	}
}
