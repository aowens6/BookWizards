package book_wizards.domain;

import book_wizards.data.BookJPARepository;
import book_wizards.models.Author;
import book_wizards.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

  @Autowired
  private final BookJPARepository repository;

  public BookService(BookJPARepository repository) {
    this.repository = repository;
  }

  public List<Book> findAll(){
    return repository.findAll();
  }

  public Book findById(int id){
    return repository.findById(id).orElse(null);
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

    book = repository.save(book);
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

    if (!repository.existsById(book.getBookId())) {
      String msg = String.format("book Id: %s, not found", book.getBookId());
      result.addMessage(msg, ResultType.NOT_FOUND);
    }

    book = repository.save(book);
    result.setPayload(book);

    return result;
  }

  public boolean deleteById(int id){

    if(repository.existsById(id)){
      repository.deleteById(id);
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
