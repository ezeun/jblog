package jblog.repository;

import java.util.HashMap;
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
}
