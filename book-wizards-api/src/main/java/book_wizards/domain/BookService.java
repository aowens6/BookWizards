package book_wizards.domain;

import book_wizards.data.AuthorJPARepository;
import book_wizards.data.BookJPARepository;
import book_wizards.data.GenreJPARepository;
import book_wizards.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

  @Autowired
  private final BookJPARepository bookRepo;

  @Autowired
  private final AuthorJPARepository authorRepo;

  @Autowired
  private final GenreJPARepository genreRepo;

  public BookService(BookJPARepository repository, AuthorJPARepository authorRepo, GenreJPARepository genreRepo) {
    this.bookRepo = repository;
    this.authorRepo = authorRepo;
    this.genreRepo = genreRepo;
  }

  public List<Book> findAll(){

    List<Book> allBooks = bookRepo.findAll();

    for(Book b : allBooks){
      b.setAuthor(authorRepo.getById(b.getAuthorId()));
      b.setGenre(genreRepo.getById(b.getGenreId()));
    }

    return allBooks;
  }

  public Book findById(int id){

    Book book = bookRepo.findById(id).orElse(null);

    book.setAuthor(authorRepo.getById(book.getAuthorId()));
    book.setGenre(genreRepo.getById(book.getGenreId()));

    return book;
  }

  public Result<Book> add(Book book){
    Result<Book> result = validate(book);

    if(!result.isSuccess()){
      return result;
    }

    if(book.getBookId() != 0) {
      result.addMessage("Book id cannot be set for `add` operation", ResultType.INVALID);
      return result;
    }

    book = bookRepo.save(book);
    result.setPayload(book);
    return result;
  }

  public Result<Book> update(Book book){
    Result<Book> result = validate(book);

    if (!result.isSuccess()) {
      return result;
    }

    if (book.getBookId() <= 0) {
      result.addMessage("id must be set for `update` operation", ResultType.INVALID);
      return result;
    }

    if (!bookRepo.existsById(book.getBookId())) {
      String msg = String.format("book Id: %s, not found", book.getBookId());
      result.addMessage(msg, ResultType.NOT_FOUND);
    }

    book = bookRepo.save(book);
    result.setPayload(book);

    return result;
  }

  public boolean deleteById(int id){

    if(bookRepo.existsById(id)){
      bookRepo.deleteById(id);
      return true;
    }else{
      return false;
    }

  }

  private Result<Book> validate(Book book){

    Result<Book> result = new Result<>();

    if(book == null){
      result.addMessage("Book cannot be null", ResultType.INVALID);
    }

    if (Validations.isNullOrBlank(book.getTitle())) {
      result.addMessage("Title is required!", ResultType.INVALID);
    }

    if (book.getAuthorId() == 0) {
      result.addMessage("Author id is required!", ResultType.INVALID);
    }

    if (book.getGenreId() == 0) {
      result.addMessage("Genre id is required!", ResultType.INVALID);
    }

    return result;
  }
}
