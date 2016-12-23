package nl.tinkoczy.villa.domain;

import java.io.Serializable;

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
@Table(name = "relatiepersonen")
@NamedQueries({
		@NamedQuery(name = "RelatiePersoonEntity.findAll", query = "SELECT r FROM RelatiePersoonEntity r ORDER BY r.id"),
		@NamedQuery(name = "RelatiePersoonEntity.findById", query = "SELECT r FROM RelatiePersoonEntity r WHERE r.id = :id"),
		@NamedQuery(name = "RelatiePersoonEntity.findAllByRelatie", query = "SELECT r FROM RelatiePersoonEntity r WHERE r.relatie = :relatie ORDER BY r.id") })
public class RelatiePersoonEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "relatie_persoon_id")
	private long id;
	@Column(name = "relatie_persoon_voornaam")
	private String relatiePersoonVoornaam;
	@Column(name = "relatie_persoon_tussenvoegsel")
	private String relatiePersoonTussenvoegsel;
	@Column(name = "relatie_persoon_achternaam")
	private String relatiePersoonAchternaam;
	@Column(name = "relatie_persoon_omschrijving")
	private String relatiePersoonOmschrijving;
	@Column(name = "relatie_persoon_telefoon")
	private String relatiePersoonTelefoon;
	@Column(name = "relatie_persoon_mobiel")
	private String relatiePersoonMobiel;
	@Column(name = "relatie_persoon_email")
	private String relatiePersoonEmail;
	@Column(name = "relatie_persoon_internet")
	private String relatiePersoonInternet;
	@Column(name = "relatie_persoon_aantekening")
	private String relatiePersoonAantekening;
	@ManyToOne
	@JoinColumn(name = "relatie_fk")
	private RelatieEntity relatie;

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getRelatiePersoonVoornaam() {
		return relatiePersoonVoornaam;
	}

	public void setRelatiePersoonVoornaam(final String relatiePersoonVoornaam) {
		this.relatiePersoonVoornaam = relatiePersoonVoornaam;
	}

	public String getRelatiePersoonTussenvoegsel() {
		return relatiePersoonTussenvoegsel;
	}

	public void setRelatiePersoonTussenvoegsel(final String relatiePersoonTussenvoegsel) {
		this.relatiePersoonTussenvoegsel = relatiePersoonTussenvoegsel;
	}

	public String getRelatiePersoonAchternaam() {
		return relatiePersoonAchternaam;
	}

	public void setRelatiePersoonAchternaam(final String relatiePersoonAchternaam) {
		this.relatiePersoonAchternaam = relatiePersoonAchternaam;
	}

	public String getRelatiePersoonOmschrijving() {
		return relatiePersoonOmschrijving;
	}

	public void setRelatiePersoonOmschrijving(final String relatiePersoonOmschrijving) {
		this.relatiePersoonOmschrijving = relatiePersoonOmschrijving;
	}

	public String getRelatiePersoonTelefoon() {
		return relatiePersoonTelefoon;
	}

	public void setRelatiePersoonTelefoon(final String relatiePersoonTelefoon) {
		this.relatiePersoonTelefoon = relatiePersoonTelefoon;
	}

	public String getRelatiePersoonMobiel() {
		return relatiePersoonMobiel;
	}

	public void setRelatiePersoonMobiel(final String relatiePersoonMobiel) {
		this.relatiePersoonMobiel = relatiePersoonMobiel;
	}

	public String getRelatiePersoonEmail() {
		return relatiePersoonEmail;
	}

	public void setRelatiePersoonEmail(final String relatiePersoonEmail) {
		this.relatiePersoonEmail = relatiePersoonEmail;
	}

	public String getRelatiePersoonInternet() {
		return relatiePersoonInternet;
	}

	public void setRelatiePersoonInternet(final String relatiePersoonInternet) {
		this.relatiePersoonInternet = relatiePersoonInternet;
	}

	public String getRelatiePersoonAantekening() {
		return relatiePersoonAantekening;
	}

	public void setRelatiePersoonAantekening(final String relatiePersoonAantekening) {
		this.relatiePersoonAantekening = relatiePersoonAantekening;
	}

	public RelatieEntity getRelatie() {
		return relatie;
	}

	public void setRelatie(final RelatieEntity relatie) {
		this.relatie = relatie;
	}

	@Override
	public boolean equals(final Object o) {
		if (!(o instanceof PostEntity)) {
			return false;
		}
		PostEntity other = (PostEntity) o;
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
		return "RelatiePersoonEntity [id=" + id + ", relatiePersoonVoornaam=" + relatiePersoonVoornaam
				+ ", relatiePersoonTussenvoegsel=" + relatiePersoonTussenvoegsel + ", relatiePersoonAchternaam="
				+ relatiePersoonAchternaam + ", relatiePersoonOmschrijving=" + relatiePersoonOmschrijving
				+ ", relatiePersoonTelefoon=" + relatiePersoonTelefoon + ", relatiePersoonMobiel="
				+ relatiePersoonMobiel + ", relatiePersoonEmail=" + relatiePersoonEmail + ", relatiePersoonInternet="
				+ relatiePersoonInternet + ", relatiePersoonAantekening=" + relatiePersoonAantekening + ", relatie="
				+ relatie + "]";
	}
}
