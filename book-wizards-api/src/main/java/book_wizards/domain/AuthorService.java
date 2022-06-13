package book_wizards.domain;

import book_wizards.data.AuthorJPARepository;
import book_wizards.models.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

  private final AuthorJPARepository repository;


  public AuthorService(AuthorJPARepository repository) {
    this.repository = repository;
  }

  public List<Author> findAll(){
    return repository.findAll();
  }

  public Author findById(int id){
    return repository.findById(id).orElse(null);
  }

  public Result<Author> add(Author author){
    Result<Author> result = validate(author);

    if(!result.isSuccess()){
      return result;
    }

    if(author.getAuthorId() != 0) {
      result.addMessage("Author id cannot be set for `add` operation", ResultType.INVALID);
      return result;
    }

    author = repository.save(author);
    result.setPayload(author);
    return result;
  }

  public Result<Author> update(Author author){
    Result<Author> result = validate(author);

    if (!result.isSuccess()) {
      return result;
    }

    if (author.getAuthorId() <= 0) {
      result.addMessage("id must be set for `update` operation", ResultType.INVALID);
      return result;
    }

    if (!repository.existsById(author.getAuthorId())) {
      String msg = String.format("authorId: %s, not found", author.getAuthorId());
      result.addMessage(msg, ResultType.NOT_FOUND);
    }

    author = repository.save(author);
    result.setPayload(author);
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

  private Result<Author> validate(Author author){

    Result<Author> result = new Result<>();

    if(author == null){
      result.addMessage("Author cannot be null", ResultType.INVALID);
    }

    if (Validations.isNullOrBlank(author.getFirstName())) {
      result.addMessage("First name is required!", ResultType.INVALID);
    }

    if (Validations.isNullOrBlank(author.getLastName())) {
      result.addMessage("Last name is required!", ResultType.INVALID);
    }

    return result;
  }

}
