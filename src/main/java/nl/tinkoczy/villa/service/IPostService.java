package nl.tinkoczy.villa.service;

import java.util.List;

import nl.tinkoczy.villa.model.Post;

public interface IPostService {

	void saveOrUpdatePost(Post post);

	void saveOrUpdatePostWithRubriek(Post post, Long rubriekId);

	void deletePost(Post post);

	List<Post> getAllPosten();

	Post getPostById(int id);

	Post getPostByPostNummer(Integer postNummer);

	List<Post> getPostenByRubriekNummer(Integer rubriekNummer);
}