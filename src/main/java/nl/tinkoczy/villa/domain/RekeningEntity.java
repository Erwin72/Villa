package nl.tinkoczy.villa.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
@Table(name = "rekeningen")
@NamedQueries({
		@NamedQuery(name = "RekeningEntity.findAll", query = "SELECT r FROM RekeningEntity r ORDER BY r.rekeningNaam"),
		@NamedQuery(name = "RekeningEntity.findById", query = "SELECT r FROM RekeningEntity r WHERE r.id = :id"),
		@NamedQuery(name = "RekeningEntity.findByRekeningNaam", query = "SELECT r FROM RekeningEntity r WHERE r.rekeningNaam = :rekeningNaam") })
public class RekeningEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rekening_id")
	private long id;
	@Column(name = "rekening_naam", unique = true)
	private String rekeningNaam;
	@Column(name = "rekening_banknaam")
	private String rekeningBankNaam;
	@Column(name = "rekening_iban")
	private String rekeningIBAN;
	@Column(name = "rekening_soort")
	private String rekeningSoort;
	@Column(name = "rekening_beginsaldo")
	private BigDecimal rekeningBeginSaldo;
	@Column(name = "rekening_passiva")
	private Boolean rekeningPassiva;

	@OneToMany(mappedBy = "rekening")
	@CascadeOnDelete
	private List<BoekstukEntity> boekstukken;

	public RekeningEntity() {
		super();
	}

	public RekeningEntity(final long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getRekeningNaam() {
		return rekeningNaam;
	}

	public void setRekeningNaam(final String rekeningNaam) {
		this.rekeningNaam = rekeningNaam;
	}

	public String getRekeningBankNaam() {
		return rekeningBankNaam;
	}

	public void setRekeningBankNaam(final String rekeningBankNaam) {
		this.rekeningBankNaam = rekeningBankNaam;
	}

	public String getRekeningIBAN() {
		return rekeningIBAN;
	}

	public void setRekeningIBAN(final String rekeningIBAN) {
		this.rekeningIBAN = rekeningIBAN;
	}

	public String getRekeningSoort() {
		return rekeningSoort;
	}

	public void setRekeningSoort(final String rekeningSoort) {
		this.rekeningSoort = rekeningSoort;
	}

	public BigDecimal getRekeningBeginSaldo() {
		return rekeningBeginSaldo;
	}

	public void setRekeningBeginSaldo(final BigDecimal rekeningBeginSaldo) {
		this.rekeningBeginSaldo = rekeningBeginSaldo;
	}

	public Boolean getRekeningPassiva() {
		return rekeningPassiva;
	}

	public void setRekeningPassiva(final Boolean rekeningPassiva) {
		this.rekeningPassiva = rekeningPassiva;
	}

	@Override
	public boolean equals(final Object o) {
		if (!(o instanceof RekeningEntity)) {
			return false;
		}
		RekeningEntity other = (RekeningEntity) o;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(getRekeningNaam(), other.getRekeningNaam());
		return builder.isEquals();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getRekeningNaam());
		return builder.hashCode();
	}

	@Override
	public String toString() {
		return "RekeningEntity [id=" + id + ", rekeningNaam=" + rekeningNaam + ", rekeningBankNaam=" + rekeningBankNaam
				+ ", rekeningIBAN=" + rekeningIBAN + ", rekeningSoort=" + rekeningSoort + ", rekeningBeginSaldo="
				+ rekeningBeginSaldo + ", rekeningPassiva=" + rekeningPassiva + "]";
	}
}
