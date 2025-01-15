package jblog.repository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	private SqlSession sqlSession;	
	
	public CategoryRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}	
	
	public Long insertDefault(String blogId) {
        Map<String, Object> params = new HashMap<>();
        params.put("blogId", blogId); // 블로그 ID를 매개변수로 전달
        sqlSession.insert("category.insertDefault", params);
        
        // 삽입된 카테고리 ID 반환 (기본 post를 넣기 위함)
        Object result = params.get("categoryId");
        if (result instanceof BigInteger) { // MyBatis가 반환한 값을 Long으로 변환
            return ((BigInteger) result).longValue();
        } else {
            return (Long) result;
        }
	}

	public List<CategoryVo> findAll(String blogId) {
		return sqlSession.selectList("category.findAll", blogId);
	}

	public int insert(CategoryVo categoryVo) {
		return sqlSession.insert("category.insert", categoryVo);
	}
}
