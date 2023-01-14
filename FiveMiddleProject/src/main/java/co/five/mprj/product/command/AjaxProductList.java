package co.five.mprj.product.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.five.mprj.common.Command;
import co.five.mprj.product.service.ProductService;
import co.five.mprj.product.service.ProductVO;
import co.five.mprj.product.serviceImpl.ProductServiceImpl;

public class AjaxProductList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 강의관리 리스트
		ProductService dao = new ProductServiceImpl(); // dao 호출
		List<ProductVO> list = dao.productSelectList(); // List형식으로 변수 list에 dao에서 호출한 값을 담음

		ObjectMapper mapper = new ObjectMapper();// java 객체를 JSON으로 serialization 할 때 사용하는 Jackson 라이브러리의 클래스

		String json = null;

		try {
			json = mapper.writeValueAsString(list); // 자바의 객체를 json형태의 문자열로 바꿔줌 list => json String
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return "Ajax:" + json;

	}

}
