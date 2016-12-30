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
import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.eclipse.persistence.annotations.Index;

@Entity
@Table(name = "relaties")
@NamedQueries({
		@NamedQuery(name = "RelatieEntity.findAll", query = "SELECT r FROM RelatieEntity r ORDER BY r.relatieCode"),
		@NamedQuery(name = "RelatieEntity.findById", query = "SELECT r FROM RelatieEntity r WHERE r.id = :id"),
		@NamedQuery(name = "RelatieEntity.findByRelatieCode", query = "SELECT r FROM RelatieEntity r WHERE r.relatieCode = :relatieCode") })
public class RelatieEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "relatie_id")
	private long id;
	@Index
	@Column(name = "relatie_code", unique = true)
	private String relatieCode;
	@Column(name = "relatie_omschrijving")
	private String relatieOmschrijving;
	@Column(name = "relatie_naam")
	private String relatieNaam;
	@Column(name = "relatie_adres_straat")
	private String relatieAdresStraat;
	@Column(name = "relatie_adres_postcode")
	private String relatieAdresPostcode;
	@Column(name = "relatie_adres_plaats")
	private String relatieAdresPlaats;
	@Column(name = "relatie_postbus_nummer")
	private String relatiePostbusNummer;
	@Column(name = "relatie_postbus_postcode")
	private String relatiePostbusPostcode;
	@Column(name = "relatie_postbus_plaats")
	private String relatiePostbusPlaats;
	@Column(name = "relatie_bank_naam")
	private String relatieBankNaam;
	@Column(name = "relatie_bank_iban")
	private String relatieBankIBAN;
	@Column(name = "relatie_aantekening")
	private String relatieAantekening;
	@OneToMany(mappedBy = "relatie")
	@CascadeOnDelete
	private List<RelatiePersoonEntity> relatiePersonen;
	@OneToMany(mappedBy = "relatie")
	@CascadeOnDelete
	private List<FaktuurEntity> fakturen;

	public RelatieEntity() {
		super();
	}

	public RelatieEntity(final long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getRelatieCode() {
		return relatieCode;
	}

	public void setRelatieCode(final String relatieCode) {
		this.relatieCode = relatieCode;
	}

	public String getRelatieOmschrijving() {
		return relatieOmschrijving;
	}

	public void setRelatieOmschrijving(final String relatieOmschrijving) {
		this.relatieOmschrijving = relatieOmschrijving;
	}

	public String getRelatieNaam() {
		return relatieNaam;
	}

	public void setRelatieNaam(final String relatieNaam) {
		this.relatieNaam = relatieNaam;
	}

	public String getRelatieAdresStraat() {
		return relatieAdresStraat;
	}

	public void setRelatieAdresStraat(final String relatieAdresStraat) {
		this.relatieAdresStraat = relatieAdresStraat;
	}

	public String getRelatieAdresPostcode() {
		return relatieAdresPostcode;
	}

	public void setRelatieAdresPostcode(final String relatieAdresPostcode) {
		this.relatieAdresPostcode = relatieAdresPostcode;
	}

	public String getRelatieAdresPlaats() {
		return relatieAdresPlaats;
	}

	public void setRelatieAdresPlaats(final String relatieAdresPlaats) {
		this.relatieAdresPlaats = relatieAdresPlaats;
	}

	public String getRelatiePostbusNummer() {
		return relatiePostbusNummer;
	}

	public void setRelatiePostbusNummer(final String relatiePostbusNummer) {
		this.relatiePostbusNummer = relatiePostbusNummer;
	}

	public String getRelatiePostbusPostcode() {
		return relatiePostbusPostcode;
	}

	public void setRelatiePostbusPostcode(final String relatiePostbusPostcode) {
		this.relatiePostbusPostcode = relatiePostbusPostcode;
	}

	public String getRelatiePostbusPlaats() {
		return relatiePostbusPlaats;
	}

	public void setRelatiePostbusPlaats(final String relatiePostbusPlaats) {
		this.relatiePostbusPlaats = relatiePostbusPlaats;
	}

	public String getRelatieBankNaam() {
		return relatieBankNaam;
	}

	public void setRelatieBankNaam(final String relatieBankNaam) {
		this.relatieBankNaam = relatieBankNaam;
	}

	public String getRelatieBankIBAN() {
		return relatieBankIBAN;
	}

	public void setRelatieBankIBAN(final String relatieBankIBAN) {
		this.relatieBankIBAN = relatieBankIBAN;
	}

	public String getRelatieAantekening() {
		return relatieAantekening;
	}

	public void setRelatieAantekening(final String relatieAantekening) {
		this.relatieAantekening = relatieAantekening;
	}

	@Override
	public boolean equals(final Object o) {
		if (!(o instanceof RelatieEntity)) {
			return false;
		}
		RelatieEntity other = (RelatieEntity) o;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(getRelatieCode(), other.getRelatieCode());
		return builder.isEquals();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getRelatieCode());
		return builder.hashCode();
	}

	@Override
	public String toString() {
		return "RelatieEntity [id=" + id + ", relatieCode=" + relatieCode + ", relatieOmschrijving="
				+ relatieOmschrijving + ", relatieNaam=" + relatieNaam + ", relatieAdresStraat=" + relatieAdresStraat
				+ ", relatieAdresPostcode=" + relatieAdresPostcode + ", relatieAdresPlaats=" + relatieAdresPlaats
				+ ", relatiePostbusNummer=" + relatiePostbusNummer + ", relatiePostbusPostcode="
				+ relatiePostbusPostcode + ", relatiePostbusPlaats=" + relatiePostbusPlaats + ", relatieBankNaam="
				+ relatieBankNaam + ", relatieBankIBAN=" + relatieBankIBAN + ", relatieAantekening="
				+ relatieAantekening + "]";
	}
}
