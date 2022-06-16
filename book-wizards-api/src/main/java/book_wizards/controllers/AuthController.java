package book_wizards.controllers;

import book_wizards.models.AppUser;
import book_wizards.domain.AppUserService;
import book_wizards.models.Book;
import book_wizards.security.JwtConverter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtConverter jwtConverter;
    private final AppUserService service;

    public AuthController(AuthenticationManager authManager, JwtConverter jwtConverter, AppUserService service) {
        this.authManager = authManager;
        this.jwtConverter = jwtConverter;
        this.service = service;
    }

    @GetMapping("/{id}")
    public UserDetails findById(@PathVariable int id){ return service.loadUserById(id);}

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> credentials) {

        String username = credentials.get("username");
        String password = credentials.get("password");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = authManager.authenticate(token);
        if (authentication.isAuthenticated()) {
            String jwtToken = jwtConverter.getTokenFromUser((User) authentication.getPrincipal());
            HashMap<String, String> whateverMap = new HashMap<>();
            whateverMap.put("jwt_token", jwtToken);
            return new ResponseEntity<>(whateverMap, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }

    @PostMapping("/create_account")
    public ResponseEntity<?> createAccount(@RequestBody Map<String, String> credentials) {
        AppUser appUser = null;
        try {
            String username = credentials.get("username");
            String password = credentials.get("password");
            appUser = service.create(username, password);
        } catch (ValidationException ex) {
            return new ResponseEntity<>(List.of(ex.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (DuplicateKeyException ex) {
            return new ResponseEntity<>(List.of("The provided username already exists"), HttpStatus.BAD_REQUEST);
        }
        // happy path...
        HashMap<String, Integer> map = new HashMap<>();
        map.put("appUserId", appUser.getAppUserId());
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}