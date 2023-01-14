package co.five.mprj.product.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.five.mprj.common.Command;

public class AjaxProduct implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// Ajax
		return "product/AjaxProduct.tiles";
	}

}
