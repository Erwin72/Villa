package nl.tinkoczy.villa.util;

import java.util.ArrayList;
import java.util.List;

import nl.tinkoczy.villa.model.Post;
import nl.tinkoczy.villa.model.Rubriek;

public class InitRubriekAndPostDataGenerator {

	private static List<Rubriek> rubriekList = new ArrayList<>();
	private static List<Post> postList = new ArrayList<>();

	private InitRubriekAndPostDataGenerator() {
		//
	}

	public static void initDefaultRubieken() {
		for (InitRubriekData rubriekData : InitRubriekData.values()) {
			rubriekList.add(new Rubriek(null, rubriekData));
		}
	}

	public static void initDefaultPosten() {
		for (InitPostData postData : InitPostData.values()) {
			postList.add(new Post(null, postData));
		}
	}

	public static List<Rubriek> getRubriekList() {
		return rubriekList;
	}

	public static List<Post> getPostList() {
		return postList;
	}
}
