package nl.tinkoczy.villa.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.domain.PostEntity;
import nl.tinkoczy.villa.model.Post;

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
	public void deletePost(final Post post) {
		PostEntity postEntity = convert(post);
		logger.debug("deletePost: " + postEntity.toString());
		DataBroker.delete(postEntity);
	}

	@Override
	public List<Post> getAllPosten() {
		List<Post> results = new ArrayList<>();
		for (PostEntity postEntity : DataBroker.getAllPosten()) {
			results.add(convert(postEntity));
		}
		return results;
	}

	@Override
	public Post getPostById(final int id) {
		PostEntity postEntity = DataBroker.getPostById(id);
		return convert(postEntity);
	}

	@Override
	public Post getPostByPostNummer(final Integer postNummer) {
		PostEntity postEntity = DataBroker.getPostByPostNummer(postNummer);
		return convert(postEntity);
	}

	private Post convert(final PostEntity postEntity) {
		Post post = new Post();
		post.setPostId(postEntity.getId());
		post.setPostNummer(postEntity.getPostNummer());
		post.setPostOmschrijving(postEntity.getPostOmschrijving());
		post.setPostPassivaRekening(postEntity.getPostPassivaRekening());
		post.setPostStandaardBedrag(postEntity.getPostStandaardBedrag());
		post.setPostStandaardBoekingOmschrijving(postEntity.getPostStandaardBoekingOmschrijving());
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
