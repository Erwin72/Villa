package nl.tinkoczy.villa.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "boekstukken")
@NamedQueries({
		@NamedQuery(name = "BoekstukEntity.findAllByDatum", query = "SELECT b FROM BoekstukEntity b ORDER BY b.boekstukDatum"),
		@NamedQuery(name = "BoekstukEntity.findAllByVolgnummer", query = "SELECT b FROM BoekstukEntity b ORDER BY b.boekstukVolgnummer"),
		@NamedQuery(name = "BoekstukEntity.findById", query = "SELECT b FROM BoekstukEntity b WHERE b.id = :id"),
		@NamedQuery(name = "BoekstukEntity.findByVolgnummer", query = "SELECT b FROM BoekstukEntity b WHERE b.boekstukVolgnummer = :boekstukVolgnummer"),
		@NamedQuery(name = "BoekstukEntity.findByAfschriftnummer", query = "SELECT b FROM BoekstukEntity b WHERE b.boekstukAfschriftnummer = :boekstukAfschriftnummer"),
		@NamedQuery(name = "BoekstukEntity.findAllByRekening", query = "SELECT b FROM BoekstukEntity b WHERE b.rekening = :rekening ORDER BY b.boekstukVolgnummer") })
public class BoekstukEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "boekstuk_id")
	private long id;
	@Column(name = "boekstuk_volgnummer", unique = true)
	private int boekstukVolgnummer;
	@Column(name = "boekstuk_datum")
	private LocalDate boekstukDatum;
	@Column(name = "boekstuk_afschriftnummer")
	private int boekstukAfschriftnummer;
	@Column(name = "boekstuk_afschrift_opmerking")
	private String boekstukAfschriftOpmerking;
	@Column(name = "boekstuk_aantekening")
	private String boekstukAantekening;

	@ManyToOne
	@JoinColumn(name = "rekening_fk")
	private RekeningEntity rekening;

	@OneToMany(mappedBy = "boekstuk")
	private List<BoekingEntity> boekingen;

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public int getBoekstukVolgnummer() {
		return boekstukVolgnummer;
	}

	public void setBoekstukVolgnummer(final int boekstukVolgnummer) {
		this.boekstukVolgnummer = boekstukVolgnummer;
	}

	public LocalDate getBoekstukDatum() {
		return boekstukDatum;
	}

	public void setBoekstukDatum(final LocalDate boekstukDatum) {
		this.boekstukDatum = boekstukDatum;
	}

	public int getBoekstukAfschriftnummer() {
		return boekstukAfschriftnummer;
	}

	public void setBoekstukAfschriftnummer(final int boekstukAfschriftnummer) {
		this.boekstukAfschriftnummer = boekstukAfschriftnummer;
	}

	public String getBoekstukAfschriftOpmerking() {
		return boekstukAfschriftOpmerking;
	}

	public void setBoekstukAfschriftOpmerking(final String boekstukAfschriftOpmerking) {
		this.boekstukAfschriftOpmerking = boekstukAfschriftOpmerking;
	}

	public String getBoekstukAantekening() {
		return boekstukAantekening;
	}

	public void setBoekstukAantekening(final String boekstukAantekening) {
		this.boekstukAantekening = boekstukAantekening;
	}

	public RekeningEntity getRekening() {
		return rekening;
	}

	public void setRekening(final RekeningEntity rekening) {
		this.rekening = rekening;
	}

	@Override
	public boolean equals(final Object o) {
		if (!(o instanceof BoekstukEntity)) {
			return false;
		}
		BoekstukEntity other = (BoekstukEntity) o;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(getBoekstukVolgnummer(), other.getBoekstukVolgnummer());
		return builder.isEquals();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getBoekstukVolgnummer());
		return builder.hashCode();
	}

	@Override
	public String toString() {
		return "BoekstukEntity [id=" + id + ", boekstukVolgnummer=" + boekstukVolgnummer + ", boekstukDatum="
				+ boekstukDatum + ", boekstukAfschriftnummer=" + boekstukAfschriftnummer
				+ ", boekstukAfschriftOpmerking=" + boekstukAfschriftOpmerking + ", boekstukAantekening="
				+ boekstukAantekening + ", rekening=" + rekening + "]";
	}

}
