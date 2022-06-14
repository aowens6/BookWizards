package book_wizards.domain;

import book_wizards.data.GenreJPARepository;
import book_wizards.models.Book;
import book_wizards.models.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

  @Autowired
  private final GenreJPARepository repository;


  public GenreService(GenreJPARepository repository) {
    this.repository = repository;
  }

  public List<Genre> findAll(){
    return repository.findAll();
  }

  public Genre findById(int id){
    return repository.findById(id).orElse(null);
  }

  public Result<Genre> add(Genre genre){
    Result<Genre> result = validate(genre);

    if(!result.isSuccess()){
      return result;
    }

    if(genre.getGenreId() != 0) {
      result.addMessage("Genre id cannot be set for `add` operation", ResultType.INVALID);
      return result;
    }

    genre = repository.save(genre);
    result.setPayload(genre);
    return result;
  }

  public Result<Genre> update(Genre genre){
    Result<Genre> result = validate(genre);

    if (!result.isSuccess()) {
      return result;
    }

    if (genre.getGenreId() <= 0) {
      result.addMessage("id must be set for `update` operation", ResultType.INVALID);
      return result;
    }

    if (!repository.existsById(genre.getGenreId())) {
      String msg = String.format("Genre Id: %s, not found", genre.getGenreId());
      result.addMessage(msg, ResultType.NOT_FOUND);
    }

    genre = repository.save(genre);
    result.setPayload(genre);

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

  private Result<Genre> validate(Genre genre){

    Result<Genre> result = new Result<>();

    if(genre == null){
      result.addMessage("Genre cannot be null", ResultType.INVALID);
    }

    if (Validations.isNullOrBlank(genre.getGenreName())) {
      result.addMessage("Genre name is required!", ResultType.INVALID);
    }

    return result;
  }
}
