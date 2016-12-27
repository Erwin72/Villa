package nl.tinkoczy.villa.domain;

import java.io.Serializable;
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

@Entity
@Table(name = "bijdrageschemas")
@NamedQueries({
		@NamedQuery(name = "BijdrageSchemaEntity.findAll", query = "SELECT b FROM BijdrageSchemaEntity b ORDER BY b.id"),
		@NamedQuery(name = "BijdrageSchemaEntity.findById", query = "SELECT b FROM BijdrageSchemaEntity b WHERE b.id = :id") })
public class BijdrageSchemaEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bijdrage_schema_id")
	private long id;
	@Column(name = "bijdrage_schema_naam")
	private String bijdrageSchemaNaam;
	@ManyToOne
	@JoinColumn(name = "bijdrage_frequentie_fk")
	private BijdrageFrequentieEntity bijdrageFrequentie;
	@ManyToOne
	@JoinColumn(name = "bijdrage_rente_fk")
	private BijdrageRenteEntity bijdrageRente;
	@OneToMany(mappedBy = "bijdrageSchema")
	@CascadeOnDelete
	private List<BijdrageEntity> bijdragen;

	public BijdrageSchemaEntity() {
		super();
	}

	public BijdrageSchemaEntity(final long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getBijdrageSchemaNaam() {
		return bijdrageSchemaNaam;
	}

	public void setBijdrageSchemaNaam(final String bijdrageSchemaNaam) {
		this.bijdrageSchemaNaam = bijdrageSchemaNaam;
	}

	public BijdrageFrequentieEntity getBijdrageFrequentie() {
		return bijdrageFrequentie;
	}

	public void setBijdrageFrequentie(final BijdrageFrequentieEntity bijdrageFrequentie) {
		this.bijdrageFrequentie = bijdrageFrequentie;
	}

	public BijdrageRenteEntity getBijdrageRente() {
		return bijdrageRente;
	}

	public void setBijdrageRente(final BijdrageRenteEntity bijdrageRente) {
		this.bijdrageRente = bijdrageRente;
	}

	@Override
	public boolean equals(final Object o) {
		if (!(o instanceof BijdrageSchemaEntity)) {
			return false;
		}
		BijdrageSchemaEntity other = (BijdrageSchemaEntity) o;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(getBijdrageSchemaNaam(), other.getBijdrageSchemaNaam());
		return builder.isEquals();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getBijdrageSchemaNaam());
		return builder.hashCode();
	}

	@Override
	public String toString() {
		return "BijdrageSchemaEntity [id=" + id + ", bijdrageSchemaNaam=" + bijdrageSchemaNaam + ", bijdrageFrequentie="
				+ bijdrageFrequentie + ", bijdrageRente=" + bijdrageRente + "]";
	}
}
