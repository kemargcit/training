package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookLoan;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.LibraryBranch;
import com.gcit.lms.domain.Publisher;
import com.gcit.lms.service.AdministratorService;

/**
 * Servlet implementation class AdministratorServlet
 */
@WebServlet({ "/addAuthor", "/addPublisher", "/addBook", "/deleteAuthor","/addBorrower","/editAuthor","/deleteBook",
	"/editBook","/deletePublisher","/editPublisher","/deleteBorrower","/editBorrower","/editDueDate"})
public class AdministratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministratorServlet() {
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
		case "/deleteAuthor":
			deleteAuthor(request, response);

			break;
		case "/deleteBook":
			deleteBook(request, response);

			break;
		case "/editAuthor":
			editAuthor(request, response);
			break;
		case "/deletePublisher":
			deletePublisher(request, response);
			break;  
		case "/deleteBorrower":
			deleteBorrower(request, response);
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
	private void deleteBorrower(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String cardNo = request.getParameter("cardNo");
		Borrower borrower = new Borrower();
		borrower.setCardNo(Integer.parseInt(cardNo));
		try {
			new AdministratorService().removeBorrower(borrower);
			request.setAttribute("result", "borrower removed succesfully!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"borrower remove failed!: " + e.getMessage());
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/ListBorrowers.jsp");
		rd.forward(request, response);

	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void editAuthor(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("authorId");
		String name =request.getParameter("authorName");
		try {
			Author author = new AdministratorService().readOneAuthor(Integer.parseInt(id));
			author.setAuthorName(name);
			new AdministratorService().updateAuthor(author);
			request.setAttribute("result", "Author updated succesfully!");
		} catch (NumberFormatException | ClassNotFoundException
				| SQLException e) {
			request.setAttribute("result",
					"Author update failed!: " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listAuthors.jsp");
		rd.forward(request, response);
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteAuthor(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String authorId = request.getParameter("authorId");
		Author author = new Author();
		author.setAuthorId(Integer.parseInt(authorId));
		try {
			new AdministratorService().removeAuthor(author);
			request.setAttribute("result", "Author removed succesfully!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Author remove failed!: " + e.getMessage());
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listAuthors.jsp");
		rd.forward(request, response);
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		Book book = new Book();
		book.setBookId(Integer.parseInt(bookId));
		try {
			new AdministratorService().deleteBook(book);
			request.setAttribute("result", "Book removed succesfully!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Book remove failed!: " + e.getMessage());
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listbooks.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String function = request.getRequestURI().substring(
				request.getContextPath().length(), request.getRequestURI().length());

		switch (function) {
		case "/addAuthor":
			addAuthor(request);
			break;  

		case "/addBook":
			try {
				addBook(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/addPublisher":
			addPublisher(request,response);
			break;
		case "/addBorrower":
			addBorrower(request,response);
			break;
		case "/editBook":
			editBook(request, response);
			break;  
		case "/editPublisher":
			editPublisher(request, response);
			break;
		case "/editBorrower":
			editBorrower(request, response);
			break; 
		case "/editDueDate":
			editDueDater(request, response);
			break; 
		default:
			break;
		}
		/*RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/admin.jsp");
		rd.forward(request, response);*/
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void editDueDater(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		String cardNo= request.getParameter("cardNo");
		String branchId= request.getParameter("branchId");
		String dueDate= request.getParameter("dueDate");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    Date newDueDate = null;
		try {
			newDueDate = df.parse(dueDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
	      BookLoan bookLoan = new BookLoan();
		try {
			Book book = new AdministratorService().readOneBookId(Integer.parseInt(bookId));
			LibraryBranch libraryBranch  =  new AdministratorService().getBranchById(Integer.parseInt(branchId));
			Borrower borrower =  new AdministratorService().getBorrowerByCardNo(Integer.parseInt(cardNo));
			bookLoan.setBook(book);
			bookLoan.setBorrower(borrower);
			bookLoan.setLibraryBranch(libraryBranch);
			bookLoan.setDateDue(newDueDate);
			new AdministratorService().overideDueDate(bookLoan);
			request.setAttribute("result", "Due date overide succesfully!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Due date overide failed!: " + e.getMessage());
		} 
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/UpdateBooKLoan.jsp");
		rd.forward(request, response);
		


	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void editBorrower(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String cardNo = request.getParameter("cardNo");
		String name = request.getParameter("borName");
		String address = request.getParameter("borAddress");
		String phone = request.getParameter("borPhone");
		Borrower borrower = new Borrower();
		borrower.setCardNo(Integer.parseInt(cardNo));
		borrower.setBorrowerName(name);
		borrower.setBorrowerAddress(address);
		borrower.setBorrowerPhone(phone);


		try {
			new AdministratorService().updateBorrower(borrower);
			request.setAttribute("result", "Borrower updated succesfully!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Borrower updated failed!: " + e.getMessage());
		} 
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/ListBorrowers.jsp");
		rd.forward(request, response);

	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void editPublisher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("pubId");
		String name = request.getParameter("pubName");
		String address = request.getParameter("pubAddress");
		String phone = request.getParameter("pubPhone");
		Publisher publisher = new Publisher();
		publisher.setId(Integer.parseInt(id));
		publisher.setName(name);
		publisher.setAddress(address);
		publisher.setPhoneNumber(phone);


		try {
			new AdministratorService().updatePublisher(publisher);
			request.setAttribute("result", "publisher updated succesfully!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"publisher updated failed!: " + e.getMessage());
		} 
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/ListPublishers.jsp");
		rd.forward(request, response);
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void deletePublisher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String pubId = request.getParameter("pubId");
		Publisher publisher = new Publisher();
		publisher.setId(Integer.parseInt(pubId));
		try {
			new AdministratorService().removePublisher(publisher);
			request.setAttribute("result", "publisher removed succesfully!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"publisher remove failed!: " + e.getMessage());
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/ListPublishers.jsp");
		rd.forward(request, response);

	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void editBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Publisher publisher = null;
		List<Author> authors = new ArrayList<Author>();
		List<Genre> genres = new ArrayList<Genre>();
		String title = request.getParameter("title");
		String[] authorIds = request.getParameterValues("authorId");
		String [] genreIds = request.getParameterValues("genreId");
		int publisherId = Integer.parseInt(request.getParameter("pubId"));
		Book book = new Book();
		book.setTitle(title);
		try {
			publisher = new AdministratorService().readOnePubById(publisherId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		book.setPublisher(publisher);
		for(int i =0;i<authorIds.length;i++){
			Author author;
			try {
				author = new AdministratorService().readOneAuthor(Integer.parseInt(authorIds[i]));
				authors.add(author);

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for(int i =0;i<genreIds.length;i++){
			Genre genre;
			try {
				genre = new AdministratorService().getGenreById(Integer.parseInt(genreIds[i]));
				genres.add(genre);

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		book.setAuthors(authors);
		book.setGenres(genres);
		try {
			new AdministratorService().addBook(book);
			request.setAttribute("result", "book updated successfully");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			request.setAttribute("result",
					"Book update failed!: " + e.getMessage());
		}
		//request.setAttribute("test", bookAuthorId[0]+bookAuthorId[1]);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listbooks.jsp");
		rd.forward(request, response);



	}

	/**
	 * @param request
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void addBorrower(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String name =request.getParameter("name");
		String address =request.getParameter("address");
		String phone =request.getParameter("phone");

		Borrower borrower = new Borrower();
		borrower.setBorrowerName(name);
		borrower.setBorrowerAddress(address);
		borrower.setBorrowerPhone(phone);
		try {

			new AdministratorService().addBorrower(borrower);
			request.setAttribute("result", "borrower add successfully");

		} catch (Exception e) {
			request.setAttribute("result",
					"Borrower add failed!: " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/admin.jsp");
		rd.forward(request, response);

	}

	/**
	 * @param request
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void addPublisher(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//add publisher
		String pubName =request.getParameter("pubName");
		String pubAddress =request.getParameter("pubAddress");
		String pubPhone =request.getParameter("pubPhone");

		Publisher newPublisher = new Publisher();
		newPublisher.setName(pubName);
		newPublisher.setAddress(pubAddress);
		newPublisher.setPhoneNumber(pubPhone);
		try {

			new AdministratorService().addPublisher(newPublisher);
			request.setAttribute("result","publisher added successfuly");
		} catch (Exception e) {
			request.setAttribute("result","publisher added failed" +e.getMessage());

		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/admin.jsp");
		rd.forward(request, response);
	}

	/**
	 * @param request
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws Exception 
	 */
	public void addBook(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		//addBook
		Publisher publisher = null;
		List<Author> authors = new ArrayList<Author>();
		List<Genre> genres = new ArrayList<Genre>();
		String title = request.getParameter("title");
		String[] authorIds = request.getParameterValues("authorId");
		String [] genreIds = request.getParameterValues("genreId");
		int publisherId = Integer.parseInt(request.getParameter("pubId"));
		Book book = new Book();
		book.setTitle(title);
		try {
			publisher = new AdministratorService().readOnePubById(publisherId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		book.setPublisher(publisher);
		for(int i =0;i<authorIds.length;i++){
			Author author;
			try {
				author = new AdministratorService().readOneAuthor(Integer.parseInt(authorIds[i]));
				authors.add(author);

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for(int i =0;i<genreIds.length;i++){
			Genre genre;
			try {
				genre = new AdministratorService().getGenreById(Integer.parseInt(genreIds[i]));
				genres.add(genre);

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		book.setAuthors(authors);
		book.setGenres(genres);
		try {
			new AdministratorService().addBook(book);
			request.setAttribute("result", "book added successfully");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			request.setAttribute("result",
					"Book add failed!: " + e.getMessage());
		}
		//request.setAttribute("test", bookAuthorId[0]+bookAuthorId[1]);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/addBook.jsp");
		rd.forward(request, response);
	}




	/**
	 * @param request
	 */
	public void addAuthor(HttpServletRequest request) {
		//addAuthor
		String authorName = request.getParameter("authorName");

		Author author = new Author();
		author.setAuthorName(authorName);

		try {
			new AdministratorService().addAuthor(author);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
