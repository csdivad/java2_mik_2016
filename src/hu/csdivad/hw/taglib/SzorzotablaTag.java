package hu.csdivad.hw.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SzorzotablaTag extends SimpleTagSupport {
	private int m, n;

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		out.print("<table border=1 cellpadding=5>");

		sorNyit(out);
		header(out, null);
		for (int i = 1; i <= n; i++) {
			header(out, i);
		}
		sorZar(out);

		for (int i = 1; i <= m; i++) {
			sorNyit(out);
			header(out, i);
			for (int j = 1; j <= n; j++) {
				cell(out, i * j);
			}
			sorZar(out);
		}

		out.print("</table>");
		out.flush();
	}

	private void header(JspWriter out, Object value) throws IOException {
		out.print("<th>");
		if (value != null) {
			out.print(value);
		}
		out.print("</th>");
	}

	private void cell(JspWriter out, Object value) throws IOException {
		out.print("<td align=\"center\">");
		if (value != null) {
			out.print(value);
		}
		out.print("</td>");
	}

	private void sorNyit(JspWriter out) throws IOException {
		out.print("<tr>");
	}

	private void sorZar(JspWriter out) throws IOException {
		out.print("</tr>");
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

}
