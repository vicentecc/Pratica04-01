package br.edu.ifpb.pweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/valide")
public class ValidaLoginServle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// pega os parâmetros fornecidos pelo formulario
		

		Properties passwdFile;
		passwdFile = new Properties();
		try{
			passwdFile.load(this.getServletContext().getResourceAsStream("/WEB-INF/senhas.properties"));
		}catch(IOException e){
			response.sendError(response.SC_BAD_REQUEST,"Erro ao ler arquivo de senhas!");
			return; 
		}
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		if (passwdFile.containsKey(login) && senha.equals(passwdFile.get(login))) {
			request.setAttribute("login", login);
			request.getRequestDispatcher("loginValido.jsp").forward(request, response);
		} else {
			response.sendRedirect("index.html");
		}

	}


}
