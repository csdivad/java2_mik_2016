package hu.csdivad.hw.taglib;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Taglib1 extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		JspFragment jspBody = getJspBody();
		StringWriter sw = new StringWriter();
		jspBody.invoke(sw);
		JspWriter out = getJspContext().getOut();
		out.print("Hello ");
		out.print(sw);
		out.print('!');
		out.flush();
	}
	
}
