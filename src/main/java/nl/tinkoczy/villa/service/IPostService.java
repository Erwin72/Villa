package nl.tinkoczy.villa.service;

import java.util.List;

import nl.tinkoczy.villa.model.Post;

public interface IPostService {

	void saveOrUpdatePost(Post post);

	void deletePost(Post post);

	List<Post> getAllPosten();

	Post getPostById(int id);

	Post getPostByPostNummer(Integer postNummer);
}