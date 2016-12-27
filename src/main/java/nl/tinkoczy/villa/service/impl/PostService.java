package nl.tinkoczy.villa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.domain.PostEntity;
import nl.tinkoczy.villa.domain.RubriekEntity;
import nl.tinkoczy.villa.model.Post;
import nl.tinkoczy.villa.service.IPostService;

public class PostService implements IPostService {

	final static Logger logger = LoggerFactory.getLogger(PostService.class);

	public PostService() {
	}

	@Override
	public void saveOrUpdatePost(final Post post) {
		PostEntity postEntity = convert(post);
		logger.debug("saveOrUpdatePost: " + postEntity.toString());
		DataBroker.saveOrUpdate(postEntity);
	}

	@Override
	public void saveOrUpdatePostWithRubriek(final Post post, final Long rubriekId) {
		RubriekEntity rubriekEntity = new RubriekEntity(rubriekId);
		PostEntity postEntity = convert(post);
		postEntity.setRubriek(rubriekEntity);
		logger.debug("saveOrUpdatePostWithRubriek: " + postEntity.toString());
		DataBroker.saveOrUpdate(postEntity);
	}

	@Override
	public void deletePost(final Post post) {
		PostEntity postEntity = convert(post);
		logger.debug("deletePost: " + postEntity.toString());
		DataBroker.delete(postEntity);
	}

	@Override
	public List<Post> getAllPosten() {
		List<Post> results = new ArrayList<>();
		for (PostEntity postEntity : DataBroker.getAllPosten()) {
			logger.debug("getAllPosten: " + postEntity.toString());
			results.add(convert(postEntity));
		}
		return results;
	}

	@Override
	public Post getPostById(final long id) {
		PostEntity postEntity = DataBroker.getPostById(id);
		logger.debug("getPostById: id=" + id + ", result=" + postEntity.toString());
		return convert(postEntity);
	}

	@Override
	public Post getPostByPostNummer(final Integer postNummer) {
		PostEntity postEntity = DataBroker.getPostByPostNummer(postNummer);
		logger.debug("getPostByPostNummer: postNummer=" + postNummer + ", result=" + postEntity.toString());
		return convert(postEntity);
	}

	@Override
	public List<Post> getPostenByRubriekNummer(final Integer rubriekNummer) {
		List<Post> results = new ArrayList<>();
		for (PostEntity postEntity : DataBroker.getPostenByRubriekNummer(rubriekNummer)) {
			logger.debug(
					"getPostenByRubriekNummer: rubriekNummer=" + rubriekNummer + ", result=" + postEntity.toString());
			results.add(convert(postEntity));
		}
		return results;
	}

	private Post convert(final PostEntity postEntity) {
		Post post = new Post();
		post.setPostId(postEntity.getId());
		post.setPostNummer(postEntity.getPostNummer());
		post.setPostOmschrijving(postEntity.getPostOmschrijving());
		post.setPostPassivaRekening(postEntity.getPostPassivaRekening());
		post.setPostStandaardBedrag(postEntity.getPostStandaardBedrag());
		post.setPostStandaardBoekingOmschrijving(postEntity.getPostStandaardBoekingOmschrijving());
		post.setRubriekNummer(postEntity.getRubriek().getRubriekNummer());
		return post;
	}

	private PostEntity convert(final Post post) {
		PostEntity postEntity = new PostEntity();
		if (post.getPostId() != null) {
			postEntity.setId(post.getPostId());
		}
		postEntity.setPostNummer(post.getPostNummer());
		postEntity.setPostOmschrijving(post.getPostOmschrijving());
		postEntity.setPostPassivaRekening(post.getPostPassivaRekening());
		postEntity.setPostStandaardBedrag(post.getPostStandaardBedrag());
		postEntity.setPostStandaardBoekingOmschrijving(post.getPostStandaardBoekingOmschrijving());
		return postEntity;
	}

}
