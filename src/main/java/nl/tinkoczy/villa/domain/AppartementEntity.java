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
import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.eclipse.persistence.annotations.Index;

@Entity
@Table(name = "appartementen")
@NamedQueries({
		@NamedQuery(name = "AppartementEntity.findAll", query = "SELECT a FROM AppartementEntity a ORDER BY a.appartementCode"),
		@NamedQuery(name = "AppartementEntity.findById", query = "SELECT a FROM AppartementEntity a WHERE a.id = :id"),
		@NamedQuery(name = "AppartementEntity.findByAppartementCode", query = "SELECT a FROM AppartementEntity a WHERE a.appartementCode = :appartementCode") })
public class AppartementEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appartement_id")
	private long id;
	@Index
	@Column(name = "appartement_code", unique = true)
	private String appartementCode;
	@Column(name = "appartement_transportdatum")
	private LocalDate appartementTransportdatum;
	@Column(name = "appartement_adres_straat")
	private String appartementAdresStraat;
	@Column(name = "appartement_adres_postcode")
	private String appartementAdresPostcode;
	@Column(name = "appartement_adres_plaats")
	private String appartementAdresPlaats;
	@ManyToOne
	@JoinColumn(name = "bijdrageschema_fk")
	@Index
	private BijdrageSchemaEntity bijdrageSchema;
	@OneToMany(mappedBy = "appartement")
	@CascadeOnDelete
	@Index
	private List<BoekingEntity> boekingen;
	@OneToMany(mappedBy = "appartement")
	@CascadeOnDelete
	@Index
	private List<BeheerderEntity> beheerders;
	@OneToMany(mappedBy = "appartement")
	@CascadeOnDelete
	@Index
	private List<BewonerEntity> bewoners;

	public AppartementEntity() {
		super();
	}

	public AppartementEntity(final long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getAppartementCode() {
		return appartementCode;
	}

	public void setAppartementCode(final String appartementCode) {
		this.appartementCode = appartementCode;
	}

	public LocalDate getAppartementTransportdatum() {
		return appartementTransportdatum;
	}

	public void setAppartementTransportdatum(final LocalDate appartementTransportdatum) {
		this.appartementTransportdatum = appartementTransportdatum;
	}

	public String getAppartementAdresStraat() {
		return appartementAdresStraat;
	}

	public void setAppartementAdresStraat(final String appartementAdresStraat) {
		this.appartementAdresStraat = appartementAdresStraat;
	}

	public String getAppartementAdresPostcode() {
		return appartementAdresPostcode;
	}

	public void setAppartementAdresPostcode(final String appartementAdresPostcode) {
		this.appartementAdresPostcode = appartementAdresPostcode;
	}

	public String getAppartementAdresPlaats() {
		return appartementAdresPlaats;
	}

	public void setAppartementAdresPlaats(final String appartementAdresPlaats) {
		this.appartementAdresPlaats = appartementAdresPlaats;
	}

	public BijdrageSchemaEntity getBijdrageSchema() {
		return bijdrageSchema;
	}

	public void setBijdrageSchema(final BijdrageSchemaEntity bijdrageSchema) {
		this.bijdrageSchema = bijdrageSchema;
	}

	public List<BoekingEntity> getBoekingen() {
		return boekingen;
	}

	public void setBoekingen(final List<BoekingEntity> boekingen) {
		this.boekingen = boekingen;
	}

	public List<BeheerderEntity> getBeheerders() {
		return beheerders;
	}

	public void setBeheerders(final List<BeheerderEntity> beheerders) {
		this.beheerders = beheerders;
	}

	public List<BewonerEntity> getBewoners() {
		return bewoners;
	}

	public void setBewoners(final List<BewonerEntity> bewoners) {
		this.bewoners = bewoners;
	}

	@Override
	public boolean equals(final Object o) {
		if (!(o instanceof AppartementEntity)) {
			return false;
		}
		AppartementEntity other = (AppartementEntity) o;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(getAppartementCode(), other.getAppartementCode());
		return builder.isEquals();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getAppartementCode());
		return builder.hashCode();
	}

	@Override
	public String toString() {
		return "AppartementEntity [id=" + id + ", appartementCode=" + appartementCode + ", appartementTransportdatum="
				+ appartementTransportdatum + ", appartementAdresStraat=" + appartementAdresStraat
				+ ", appartementAdresPostcode=" + appartementAdresPostcode + ", appartementAdresPlaats="
				+ appartementAdresPlaats + ", bijdrageSchema=" + bijdrageSchema + ", boekingen=" + boekingen
				+ ", beheerders=" + beheerders + ", bewoners=" + bewoners + "]";
	}

}
