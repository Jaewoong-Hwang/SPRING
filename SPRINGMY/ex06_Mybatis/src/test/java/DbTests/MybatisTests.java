package DbTests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.domain.dto.MemoDto;
import com.example.app.domain.mapper.MemoMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
class MybatisTests {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private MemoMapper memoMapper;
	
	@Test
	void t1() {
		assertNotNull(sqlSessionFactory);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		assertNotNull(sqlSession);
	}
	
	
	@Test
	@Disabled
	void test() {
		memoMapper.insert(new MemoDto(1010,"a","a@naever.com",LocalDateTime.now(),null));
	}

}
