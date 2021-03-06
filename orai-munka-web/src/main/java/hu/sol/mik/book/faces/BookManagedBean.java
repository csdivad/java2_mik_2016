package hu.sol.mik.book.faces;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.beanutils.BeanUtils;

import hu.sol.mik.book.bean.Book;
import hu.sol.mik.book.dao.BookDao;
import hu.sol.mik.book.dao.impl.BookDaoImpl;

@ManagedBean
@ViewScoped
public class BookManagedBean {

	private BookDao bookDao;
	private List<Book> bookList;
	private Book editedBook;
	private List<Book> selectedBooks;

	public BookManagedBean() {
		this.bookDao = new BookDaoImpl();
		listBooks();
	}

	private void listBooks() {
		bookList = bookDao.listAll();
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void newBook() {
		editedBook = new Book();
	}

	public void saveBook() {
		if (editedBook.getId() != null) {
			bookDao.updateBook(editedBook);
		} else {
			bookDao.saveBook(editedBook);
		}
		listBooks();
		editedBook = null;
	}

	public void deleteSelectedBooks() {
		if (!selectedBooks.isEmpty()) {
			for (Book b : selectedBooks) {
				bookDao.delete(b);
			}
			listBooks();
			System.out.println("ja");
		}
	}

	public Book getEditedBook() {
		return editedBook;
	}

	public void setEditedBook(Book editedBook) {
		try {
			editedBook = (Book) BeanUtils.cloneBean(editedBook);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Book> getSelectedBooks() {
		return selectedBooks;
	}

	public void setSelectedBooks(List<Book> selectedBooks) {
		this.selectedBooks = selectedBooks;
	}

}
