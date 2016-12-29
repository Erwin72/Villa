package nl.tinkoczy.villa.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.tinkoczy.villa.model.BijdrageFrequentie;
import nl.tinkoczy.villa.model.Post;
import nl.tinkoczy.villa.model.Rubriek;
import nl.tinkoczy.villa.service.IBijdrageFrequentieService;
import nl.tinkoczy.villa.service.IPostService;
import nl.tinkoczy.villa.service.IRubriekService;
import nl.tinkoczy.villa.service.impl.BijdrageFrequentieService;
import nl.tinkoczy.villa.service.impl.PostService;
import nl.tinkoczy.villa.service.impl.RubriekService;

public final class InitVillaData {

	final static Logger logger = LoggerFactory.getLogger(InitVillaData.class);

	private static final ObservableList<Rubriek> rubriekData = FXCollections.observableArrayList();
	private static final ObservableList<Post> postData = FXCollections.observableArrayList();
	private static final ObservableList<BijdrageFrequentie> bijdrageFrequentieData = FXCollections
			.observableArrayList();

	private static IRubriekService rService;
	private static IPostService pService;
	private static IBijdrageFrequentieService bService;

	private InitVillaData() {
		// static
	}

	public static void parseData() {

		InitRubriekAndPostDataGenerator.initDefaultRubieken();
		InitRubriekAndPostDataGenerator.initDefaultPosten();
		InitBijdrageFrequentieDataGenerator.initDefaultBijdrageFrequenties();

		rubriekData.addAll(InitRubriekAndPostDataGenerator.getRubriekList());
		postData.addAll(InitRubriekAndPostDataGenerator.getPostList());
		bijdrageFrequentieData.addAll(InitBijdrageFrequentieDataGenerator.getBijdrageFrequentieList());
	}

	public static void persistData() {

		rService = new RubriekService();
		for (Rubriek rubriek : getRubriekData()) {
			rService.saveOrUpdateRubriek(rubriek);
		}
		pService = new PostService();
		for (Post post : getPostData()) {
			if (post.getRubriekNummer() != null) {
				Rubriek rubriek = rService.getRubriekByRubriekNummer(post.getRubriekNummer());
				pService.saveOrUpdatePostWithRubriek(post, rubriek.getRubriekId());
			} else {
				pService.saveOrUpdatePost(post);
			}
		}
		bService = new BijdrageFrequentieService();
		for (BijdrageFrequentie bijdrageFrequentie : getBijdrageFrequentieData()) {
			bService.saveOrUpdateBijdrageFrequentie(bijdrageFrequentie);
		}
	}

	public static ObservableList<Rubriek> getRubriekData() {
		return rubriekData;
	}

	public static ObservableList<Post> getPostData() {
		return postData;
	}

	public static ObservableList<BijdrageFrequentie> getBijdrageFrequentieData() {
		return bijdrageFrequentieData;
	}

}
