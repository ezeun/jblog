package jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.PostVo;

@Repository
public class PostRepository {
	private SqlSession sqlSession;	
	
	public PostRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}	

	public int insertDefault(Long categoryId) {
        Map<String, Object> params = new HashMap<>();
        params.put("categoryId", categoryId);
		return sqlSession.insert("post.insertDefault", params);
	}

	public int deleteByCategoryId(Long categoryId) {
		return sqlSession.delete("post.deleteByCategoryId", categoryId);
	}

	public int insert(PostVo postVo) {
		return sqlSession.insert("post.insert", postVo);
	}

	public Long findDefaultPostId(Long categoryId) {
		return sqlSession.selectOne("post.findDefault", categoryId);
	}

	public List<PostVo> findAll(Long categoryId) {
		return sqlSession.selectList("post.findAll", categoryId);
	}

	public PostVo findOne(Long postId) {
		return sqlSession.selectOne("post.findOne", postId);
	}
}
