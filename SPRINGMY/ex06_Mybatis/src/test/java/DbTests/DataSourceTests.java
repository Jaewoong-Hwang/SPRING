//package DbTests;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.time.LocalDateTime;
//
//import javax.sql.DataSource;
//
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.example.app.domain.dao.MemoDaoImpl;
//import com.example.app.domain.dto.MemoDto;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//class DataSourceTests {
//	
//	
//	@Autowired
//	private DataSource dataSource1; //업캐스팅, root-context 에 ID와 연결됨 이름 중요!!
//	
//	@Autowired
//	private DataSource dataSource2; //업캐스팅, root-context 에 ID와 연결됨 이름 중요!!
//	
//	@Autowired
//	private DataSource dataSource3; //업캐스팅, root-context 에 ID와 연결됨 이름 중요!
//	
//	
//	@Autowired
//	private MemoDaoImpl memoDaoImpl; // bean에 만들어 놓은 걸 가져와서 씀  -> @ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") 이게 필요함
//	
//	@Test
//	@Disabled
//	void test1() throws Exception{
//		System.out.println(dataSource1);
//		 Connection con = dataSource1.getConnection();
//		 PreparedStatement pstmt = con.prepareStatement("insert into tbl_book values('abcd','abcd','abcd','abcd')");
//		 pstmt.executeUpdate();
//	}
//	
//	@Test
//	@Disabled
//	void test2() throws Exception{
//		memoDaoImpl.insert(new MemoDto(1,"a","a",LocalDateTime.now(),null));
//	}
//
//	@Test
//	@Disabled
//	void test3() throws Exception{
//		System.out.println(dataSource2);
//		 Connection con = dataSource2.getConnection();
//		 PreparedStatement pstmt = con.prepareStatement("insert into tbl_book values('fdas','fdas','fds','asdf')");
//		 pstmt.executeUpdate();
//	}
//	@Test
//	void test4() throws Exception{
//		System.out.println(dataSource3);
//		 Connection con = dataSource3.getConnection();
//		 PreparedStatement pstmt = con.prepareStatement("insert into tbl_book values('a123','a123','a123','a123')");
//		 pstmt.executeUpdate();
//	}
//}
