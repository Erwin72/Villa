package nl.tinkoczy.villa.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rubriekdata")
public class RubriekListWrapper {

	@XmlElement(name = "rubriek")
	public List<Rubriek> rubrieken = new ArrayList<>();

	@XmlElement(name = "post")
	public List<Post> posten = new ArrayList<>();

	public void add(final Rubriek rubriek) {
		rubrieken.add(rubriek);
	}

	public void add(final Post post) {
		posten.add(post);
	}
}
