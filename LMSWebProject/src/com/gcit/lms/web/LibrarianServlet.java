package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopy;
import com.gcit.lms.domain.LibraryBranch;
import com.gcit.lms.service.AdministratorService;
import com.gcit.lms.service.LibrarianService;

/**
 * Servlet implementation class LibrarianServlet
 */
@WebServlet({ "/editBranch","/addCopies","/getBooksByBranch","/updateNumOfCopies"})
		public class LibrarianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibrarianServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String function = request.getRequestURI().substring(
				request.getContextPath().length(), request.getRequestURI().length());
				switch (function) {
				case "/getBooksByBranch":
					getBooksByBranch(request, response);

					break;
				
				default:
					break;
				}

	
	
	}

	/**
	 * @param request
	 * @param response
	 */
	private void getBooksByBranch(HttpServletRequest request,
			HttpServletResponse response) {
     int branchId = Integer.parseInt(request.getParameter("branchId"));
        
		
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String function = request.getRequestURI().substring(
		request.getContextPath().length(), request.getRequestURI().length());
		switch (function) {
		case "/editBranch":
			editBranch(request, response);

			break;
		case "/updateNumOfCopies":
			updateNumOfCopies(request, response);

			break;
		default:
			break;
		}

	
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateNumOfCopies(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
 
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int numOfCopy = Integer.parseInt(request.getParameter("numOfCopy"));
		
		try {
			Book book = new AdministratorService().readOneBookId(bookId);
			LibraryBranch libraryBranch = new LibrarianService().getBranchById(branchId);
			BookCopy bookCopy =new  BookCopy();
			bookCopy.setBook(book);
			bookCopy.setLibraryBranch(libraryBranch);
			bookCopy.setNumOfCopies(numOfCopy);
			if(new LibrarianService().readOne(branchId, bookId)==null){
				new LibrarianService().addBookCopies(bookCopy);

			}
			else{
			new LibrarianService().updateBookCopy(bookCopy);
			}
			request.setAttribute("result", "Num of copies updated Successfully");
		} catch (Exception e) {
			request.setAttribute("result", "Num of copies update failed "+e.getMessage());

		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/UpdateLibraryDetails.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void editBranch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String branchId = request.getParameter("branchId");
		String branchName = request.getParameter("branchName");
		String branchAddress = request.getParameter("branchAddress");
		
		
		LibraryBranch libraryBranch = new LibraryBranch();
		libraryBranch.setBranchId(Integer.parseInt(branchId));
		libraryBranch.setBranchName(branchName);
		libraryBranch.setBranchAddress(branchAddress);
		
		try {
			new LibrarianService().updateBranch(libraryBranch);
			request.setAttribute("result", "branch details update succesfully!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"branch details update failed!: " + e.getMessage());
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/UpdateLibraryDetails.jsp");
		rd.forward(request, response);

	}

}
