package com.gcit.lms.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopy;
import com.gcit.lms.domain.BookLoan;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.LibraryBranch;
import com.gcit.lms.service.AdministratorService;
import com.gcit.lms.service.BorrowerService;

/**
 * Servlet implementation class BorrowerServlet
 */
@WebServlet({"/getBorrowerInfo","/getBooksByBranchId","/borrowBook","/Returns","/submitReturn"})
public class BorrowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/*
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String function = request.getRequestURI().substring(
				request.getContextPath().length(), request.getRequestURI().length());

		switch (function) {
		
		case "/getBooksByBranchId":
			getBooksByBranch(request, response);
			break;
		case "/borrowBook":
			borrowBook(request, response);

			break;
		case "/Returns":
			returnsSetup(request, response);

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
	private void returnsSetup(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	      int cardNo = Integer.parseInt(request.getParameter("cardNo"));
	      
	      try {
			Borrower borrower = new BorrowerService().getBorrowerByCardNo(cardNo);
			request.setAttribute("borrower", borrower);
			List<BookLoan> bookLoans = new BorrowerService().readAllByBorrower(cardNo);
			
			request.setAttribute("bookloans", bookLoans);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      
	      RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/ReturnBook.jsp");
			rd.forward(request, response);
			
	      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String function = request.getRequestURI().substring(
				request.getContextPath().length(), request.getRequestURI().length());

		switch (function) {
		case "/getBorrowerInfo":
			getBorrowerInfo(request, response);

			break;
		case "/submitReturn":
			submitReturn(request, response);

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
	private void submitReturn(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

   		String[] selectedBookIds = request.getParameterValues("bookId");
   		int cardNo = Integer.parseInt(request.getParameter("cardNo"));
   		int branchId =Integer.parseInt(request.getParameter("branchId"));
   		
   		try {
			Borrower borrower = new BorrowerService().getBorrowerByCardNo(cardNo);
			LibraryBranch libraryBranch = new AdministratorService().getBranchById(branchId);
			List<Book> selectedBooks = new ArrayList<Book>();
			for(int i=0; i<selectedBookIds.length;i++){
				Book book = new AdministratorService().readOneBookId(Integer.parseInt(selectedBookIds[i]));
				selectedBooks.add(book);
			}
			
			for(Book book : selectedBooks){
				BookLoan bookLoan = new BookLoan();
				bookLoan.setBook(book);
				bookLoan.setBorrower(borrower);
				BookLoan original  =new BorrowerService().readAllByBorrowerBook(borrower, book);
				
				bookLoan.setLibraryBranch(libraryBranch);
				new BorrowerService().deleteBookLoan(bookLoan,original.getLibraryBranch());
			}
			request.setAttribute("result", "book returns  succesfull!");

		} catch (Exception e) {
			request.setAttribute("result",
					"book returns  failed!: " + e.getMessage());
		}
   		
   		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/ReturnBook.jsp");
		rd.forward(request, response);
   				
   				
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void borrowBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
      int bookId = Integer.parseInt(request.getParameter("bookId"));
      int cardNo = Integer.parseInt(request.getParameter("cardNo"));
      int branchId = Integer.parseInt(request.getParameter("branchId"));
   	
		Book book = null;
		Borrower borrower = null;
		LibraryBranch libraryBranch = null;
		try {
			book = new AdministratorService().readOneBookId(bookId);
			borrower = new AdministratorService().getBorrowerByCardNo(cardNo);
			libraryBranch = new AdministratorService().getBranchById(branchId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BookLoan bookLoan = new BookLoan();
		bookLoan.setBook(book);
		bookLoan.setBorrower(borrower);
		bookLoan.setLibraryBranch(libraryBranch);
	
		
		try {
			new BorrowerService().addBookLoan(bookLoan);
			request.setAttribute("result", "book loan  succesfull!");

		} catch (Exception e) {
			request.setAttribute("result",
					"book loan  failed!: " + e.getMessage());
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/CheckOutBook.jsp");
		rd.forward(request, response);
		

	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getBooksByBranch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
int branchId = Integer.parseInt(request.getParameter("branchId"));

try {
	List<BookCopy> bookCopies = new BorrowerService().readBooksByBranch(branchId);
	request.setAttribute("bookcopies",bookCopies);
	request.setAttribute("branchId",branchId);
	request.setAttribute("cardNo",request.getParameter("cardNo"));

} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
RequestDispatcher rd = getServletContext().getRequestDispatcher(
		"/CheckOutBook.jsp");
rd.forward(request, response);	
	}

	/**
	 * @param request
	 * @param response
	 */
	private void getBorrowerInfo(HttpServletRequest request,
			HttpServletResponse response) {
		
		int cardNo = Integer.parseInt(request.getParameter("cardNo"));
		try {
			Borrower borrower = new BorrowerService().getBorrowerByCardNo(cardNo);
			request.setAttribute("borrower", borrower);
			List<BookLoan> bookLoans = new BorrowerService().readAllByBorrower(cardNo);
			request.setAttribute("bookloans",bookLoans);

			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/borrowerMain.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
