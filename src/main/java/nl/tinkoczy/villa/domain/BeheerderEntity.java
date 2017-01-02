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
@Table(name = "beheerders")
@NamedQueries({ @NamedQuery(name = "BeheerderEntity.findAll", query = "SELECT b FROM BeheerderEntity b ORDER BY b.id"),
		@NamedQuery(name = "BeheerderEntity.findById", query = "SELECT b FROM BeheerderEntity b WHERE b.id = :id"),
		@NamedQuery(name = "BeheerderEntity.findAllByAppartement", query = "SELECT b FROM BeheerderEntity b WHERE b.appartement = :appartement") })
public class BeheerderEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "beheerder_id")
	private long id;
	@Column(name = "beheerder_voornaam")
	private String beheerderVoornaam;
	@Column(name = "beheerder_tussenvoegsel")
	private String beheerderTussenvoegsel;
	@Column(name = "beheerder_achternaam")
	private String beheerderAchternaam;
	@Column(name = "beheerder_adres_straat")
	private String beheerderAdresStraat;
	@Column(name = "beheerder_adres_postcode")
	private String beheerderAdresPostcode;
	@Column(name = "beheerder_adres_plaats")
	private String beheerderAdresPlaats;
	@Column(name = "beheerder_adres_land")
	private String beheerderAdresLand;
	@Column(name = "beheerder_geslacht")
	private String beheerderGeslacht;
	@Column(name = "beheerder_telefoon")
	private String beheerderTelefoon;
	@Column(name = "beheerder_mobiel")
	private String beheerderMobiel;
	@Column(name = "beheerder_email")
	private String beheerderEmail;
	@Column(name = "beheerder_internet")
	private String beheerderInternet;
	@Column(name = "beheerder_aantekening")
	private String beheerderAantekening;
	@Column(name = "beheerder_isbewoner")
	private Boolean beheerderIsBewoner;
	@ManyToOne
	@JoinColumn(name = "appartement_fk")
	@Index
	private AppartementEntity appartement;

	public BeheerderEntity() {
		super();
	}

	public BeheerderEntity(final long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getBeheerderVoornaam() {
		return beheerderVoornaam;
	}

	public void setBeheerderVoornaam(final String beheerderVoornaam) {
		this.beheerderVoornaam = beheerderVoornaam;
	}

	public String getBeheerderTussenvoegsel() {
		return beheerderTussenvoegsel;
	}

	public void setBeheerderTussenvoegsel(final String beheerderTussenvoegsel) {
		this.beheerderTussenvoegsel = beheerderTussenvoegsel;
	}

	public String getBeheerderAchternaam() {
		return beheerderAchternaam;
	}

	public void setBeheerderAchternaam(final String beheerderAchternaam) {
		this.beheerderAchternaam = beheerderAchternaam;
	}

	public String getBeheerderAdresStraat() {
		return beheerderAdresStraat;
	}

	public void setBeheerderAdresStraat(final String beheerderAdresStraat) {
		this.beheerderAdresStraat = beheerderAdresStraat;
	}

	public String getBeheerderAdresPostcode() {
		return beheerderAdresPostcode;
	}

	public void setBeheerderAdresPostcode(final String beheerderAdresPostcode) {
		this.beheerderAdresPostcode = beheerderAdresPostcode;
	}

	public String getBeheerderAdresPlaats() {
		return beheerderAdresPlaats;
	}

	public void setBeheerderAdresPlaats(final String beheerderAdresPlaats) {
		this.beheerderAdresPlaats = beheerderAdresPlaats;
	}

	public String getBeheerderAdresLand() {
		return beheerderAdresLand;
	}

	public void setBeheerderAdresLand(final String beheerderAdresLand) {
		this.beheerderAdresLand = beheerderAdresLand;
	}

	public String getBeheerderGeslacht() {
		return beheerderGeslacht;
	}

	public void setBeheerderGeslacht(final String beheerderGeslacht) {
		this.beheerderGeslacht = beheerderGeslacht;
	}

	public String getBeheerderTelefoon() {
		return beheerderTelefoon;
	}

	public void setBeheerderTelefoon(final String beheerderTelefoon) {
		this.beheerderTelefoon = beheerderTelefoon;
	}

	public String getBeheerderMobiel() {
		return beheerderMobiel;
	}

	public void setBeheerderMobiel(final String beheerderMobiel) {
		this.beheerderMobiel = beheerderMobiel;
	}

	public String getBeheerderEmail() {
		return beheerderEmail;
	}

	public void setBeheerderEmail(final String beheerderEmail) {
		this.beheerderEmail = beheerderEmail;
	}

	public String getBeheerderInternet() {
		return beheerderInternet;
	}

	public void setBeheerderInternet(final String beheerderInternet) {
		this.beheerderInternet = beheerderInternet;
	}

	public String getBeheerderAantekening() {
		return beheerderAantekening;
	}

	public void setBeheerderAantekening(final String beheerderAantekening) {
		this.beheerderAantekening = beheerderAantekening;
	}

	public Boolean getBeheerderIsBewoner() {
		return beheerderIsBewoner;
	}

	public void setBeheerderIsBewoner(final Boolean beheerderIsBewoner) {
		this.beheerderIsBewoner = beheerderIsBewoner;
	}

	public AppartementEntity getAppartement() {
		return appartement;
	}

	public void setAppartement(final AppartementEntity appartement) {
		this.appartement = appartement;
	}

	@Override
	public boolean equals(final Object o) {
		if (!(o instanceof BeheerderEntity)) {
			return false;
		}
		BeheerderEntity other = (BeheerderEntity) o;
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
		return "BeheerderEntity [id=" + id + ", beheerderVoornaam=" + beheerderVoornaam + ", beheerderTussenvoegsel="
				+ beheerderTussenvoegsel + ", beheerderAchternaam=" + beheerderAchternaam + ", beheerderAdresStraat="
				+ beheerderAdresStraat + ", beheerderAdresPostcode=" + beheerderAdresPostcode
				+ ", beheerderAdresPlaats=" + beheerderAdresPlaats + ", beheerderAdresLand=" + beheerderAdresLand
				+ ", beheerderGeslacht=" + beheerderGeslacht + ", beheerderTelefoon=" + beheerderTelefoon
				+ ", beheerderMobiel=" + beheerderMobiel + ", beheerderEmail=" + beheerderEmail + ", beheerderInternet="
				+ beheerderInternet + ", beheerderAantekening=" + beheerderAantekening + ", beheerderIsBewoner="
				+ beheerderIsBewoner + ", appartement=" + appartement + "]";
	}
}
