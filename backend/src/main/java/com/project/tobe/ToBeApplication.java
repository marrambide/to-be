package com.project.tobe;

import com.project.tobe.models.Book;
import com.project.tobe.models.GroceryItem;
import com.project.tobe.repositories.BookRepository;
import com.project.tobe.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class ToBeApplication implements CommandLineRunner {

	@Autowired
	ItemRepository groceryItemRepo;

	@Autowired
	BookRepository bookRepo;

	List<GroceryItem> itemList = new ArrayList<GroceryItem>();

	public static void main(String[] args) {
		SpringApplication.run(ToBeApplication.class, args);
	}

	public void run(String... args) throws Exception {
		System.out.println("-------------CREATE BOOKS-------------------------------\n");

		createBooks();

		System.out.println("\n----------------SHOW ALL BOOKS---------------------------\n");

		showAllBooks();
		//showAllGroceryItems();

		System.out.println("\n--------------GET BOOK BY ISBN-----------------------------------\n");

		getBookById("0439708184");
		//getGroceryItemByName("Whole Wheat Biscuit");

		System.out.println("\n-----------GET ITEMS BY NAME---------------------------------\n");

		getBookByName("Harry Potter and the Chamber of Secrets");
		//getItemsByCategory("millets");

		System.out.println("\n-----------GET ITEMS BY AUTHOR---------------------------------\n");

		getBooksByAuthor("J.K. Rowling");

		System.out.println("\n-----------UPDATE BOOK TO FINISHED----------------\n");

		updateFinished("Harry Potter and the Chamber of Secrets", true);
		//updateCategoryName("snacks");

		System.out.println("\n----------DELETE A GROCERY ITEM----------------------------------\n");

		//deleteGroceryItem("Kodo Millet");

		System.out.println("\n------------FINAL COUNT OF GROCERY ITEMS-------------------------\n");

		findCountOfBooks();

		System.out.println("\n-------------------THANK YOU---------------------------");
	}

	// CREATE
	void createBooks() {
		System.out.println("Data creation started...");

		bookRepo.save(new Book("0439708184", "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "Fantasy", 223));
		bookRepo.save(new Book("0439064872", "Harry Potter and the Chamber of Secrets", "J.K. Rowling", "Fantasy", 223));

		System.out.println("Data creation complete...");
	}

	// READ
	// 1. Show all the data
	public void showAllBooks() {
		bookRepo.findAll().forEach(book -> System.out.println(getBookDetails(book)));
	}

	// 2. Get item by name
	public void getBookById(String id) {
		System.out.println("Getting book by ISBN: " + id);
		Book book = bookRepo.findBookByISBN(id);
		System.out.println(book);
		System.out.println(getBookDetails(book));
	}

	public void getBookByName(String name) {
		System.out.println("Getting book by name: " + name);
		Book book = bookRepo.findBookByName(name);
		System.out.println(book);
		System.out.println(getBookDetails(book));
	}
	public Book getBookbyName(String name) {
		return bookRepo.findBookByName(name);
	}

	public void getBooksByAuthor(String author) {
		System.out.println("Getting books by author name: " + author);
		List<Book> books = bookRepo.findBooksByAuthor(author);
		books.forEach(item -> System.out.println(getBookDetails(item)));

	}

	// 3. Get name and quantity of all items of a particular category
	public void getItemsByCategory(String category) {
		System.out.println("Getting items for the category " + category);
		List<GroceryItem> list = groceryItemRepo.findAll(category);

		list.forEach(item -> System.out.println("Name: " + item.getName() + ", Quantity: " + item.getQuantity()));
	}

	// 4. Get count of documents in the collection
	public void findCountOfBooks() {
		long count = bookRepo.count();
		System.out.println("Number of documents in the collection: " + count);
	}

	// Print details in readable form
	public String getBookDetails(Book book) {

		System.out.println(
				"Book Name: " + book.getName() +
						"\nISBN: " + book.getId() +
						"\nAuthor: " + book.getAuthor() +
						"\nGenre: " + book.getGenre() +
						"\nPages: " + book.getPages()
		);

		return "";
	}

	// UPDATE
	public void updateFinished(String name, Boolean finished) {
		Book book = getBookbyName(name);
		book.setFinished(finished);
		book.setCurrPage(book.getPages());
//		String newCategory = "munchies";

//		List<GroceryItem> list = groceryItemRepo.findAll(category);
//
//		list.forEach(item -> {
//			item.setCategory(newCategory);
//		});

		Book bookUpdated = bookRepo.save(book);
	}

	// DELETE
	public void deleteBook(String id) {
		bookRepo.deleteById(id);
//		groceryItemRepo.deleteById(id);
		System.out.println("Book with id " + id + " deleted...");
	}


}
