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
import org.eclipse.persistence.annotations.Index;

@Entity
@Table(name = "bewoners")
@NamedQueries({ @NamedQuery(name = "BewonerEntity.findAll", query = "SELECT b FROM BewonerEntity b ORDER BY b.id"),
		@NamedQuery(name = "BewonerEntity.findById", query = "SELECT b FROM BewonerEntity b WHERE b.id = :id"),
		@NamedQuery(name = "BewonerEntity.findAllByAppartement", query = "SELECT b FROM BewonerEntity b WHERE b.appartement = :appartement") })
public class BewonerEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bewoner_id")
	private long id;
	@Column(name = "bewoner_voornaam")
	private String bewonerVoornaam;
	@Column(name = "bewoner_tussenvoegsel")
	private String bewonerTussenvoegsel;
	@Column(name = "bewoner_achternaam")
	private String bewonerAchternaam;
	@Column(name = "bewoner_geslacht")
	private String bewonerGeslacht;
	@Column(name = "bewoner_telefoon")
	private String bewonerTelefoon;
	@Column(name = "bewoner_mobiel")
	private String bewonerMobiel;
	@Column(name = "bewoner_email")
	private String bewonerEmail;
	@Column(name = "bewoner_internet")
	private String bewonerInternet;
	@Column(name = "bewoner_aantekening")
	private String bewonerAantekening;
	@Column(name = "bewoner_iseigenaar")
	private Boolean bewonerIsEigenaar;
	@ManyToOne
	@JoinColumn(name = "appartement_fk")
	@Index
	private AppartementEntity appartement;

	public BewonerEntity() {
		super();
	}

	public BewonerEntity(final long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getBewonerVoornaam() {
		return bewonerVoornaam;
	}

	public void setBewonerVoornaam(final String bewonerVoornaam) {
		this.bewonerVoornaam = bewonerVoornaam;
	}

	public String getBewonerTussenvoegsel() {
		return bewonerTussenvoegsel;
	}

	public void setBewonerTussenvoegsel(final String bewonerTussenvoegsel) {
		this.bewonerTussenvoegsel = bewonerTussenvoegsel;
	}

	public String getBewonerAchternaam() {
		return bewonerAchternaam;
	}

	public void setBewonerAchternaam(final String bewonerAchternaam) {
		this.bewonerAchternaam = bewonerAchternaam;
	}

	public String getBewonerGeslacht() {
		return bewonerGeslacht;
	}

	public void setBewonerGeslacht(final String bewonerGeslacht) {
		this.bewonerGeslacht = bewonerGeslacht;
	}

	public String getBewonerTelefoon() {
		return bewonerTelefoon;
	}

	public void setBewonerTelefoon(final String bewonerTelefoon) {
		this.bewonerTelefoon = bewonerTelefoon;
	}

	public String getBewonerMobiel() {
		return bewonerMobiel;
	}

	public void setBewonerMobiel(final String bewonerMobiel) {
		this.bewonerMobiel = bewonerMobiel;
	}

	public String getBewonerEmail() {
		return bewonerEmail;
	}

	public void setBewonerEmail(final String bewonerEmail) {
		this.bewonerEmail = bewonerEmail;
	}

	public String getBewonerInternet() {
		return bewonerInternet;
	}

	public void setBewonerInternet(final String bewonerInternet) {
		this.bewonerInternet = bewonerInternet;
	}

	public String getBewonerAantekening() {
		return bewonerAantekening;
	}

	public void setBewonerAantekening(final String bewonerAantekening) {
		this.bewonerAantekening = bewonerAantekening;
	}

	public Boolean getBewonerIsEigenaar() {
		return bewonerIsEigenaar;
	}

	public void setBewonerIsEigenaar(final Boolean bewonerIsEigenaar) {
		this.bewonerIsEigenaar = bewonerIsEigenaar;
	}

	public AppartementEntity getAppartement() {
		return appartement;
	}

	public void setAppartement(final AppartementEntity appartement) {
		this.appartement = appartement;
	}

	@Override
	public boolean equals(final Object o) {
		if (!(o instanceof BewonerEntity)) {
			return false;
		}
		BewonerEntity other = (BewonerEntity) o;
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
		return "BewonerEntity [id=" + id + ", bewonerVoornaam=" + bewonerVoornaam + ", bewonerTussenvoegsel="
				+ bewonerTussenvoegsel + ", bewonerAchternaam=" + bewonerAchternaam + ", bewonerGeslacht="
				+ bewonerGeslacht + ", bewonerTelefoon=" + bewonerTelefoon + ", bewonerMobiel=" + bewonerMobiel
				+ ", bewonerEmail=" + bewonerEmail + ", bewonerInternet=" + bewonerInternet + ", bewonerAantekening="
				+ bewonerAantekening + ", bewonerIsEigenaar=" + bewonerIsEigenaar + ", appartement=" + appartement
				+ "]";
	}
}
