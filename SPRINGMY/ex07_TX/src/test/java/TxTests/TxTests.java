package TxTests;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.domain.dto.MemoDto;
import com.example.app.domain.service.MemoServiceImpl;

import lombok.extern.slf4j.Slf4j;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
class TxTests {

	@Autowired
	private MemoServiceImpl memoService;
	
	
	@Test
	void t1() {
//		memoService.addMemoTx(new MemoDto(9090,"a","a",LocalDateTime.now()));
		memoService.getAllMemo();
	}
	
}
