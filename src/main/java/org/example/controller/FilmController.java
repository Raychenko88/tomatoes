package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("film")
@PreAuthorize("hasRole('USER')")

public class FilmController {

    @Autowired
    private FilmService filmService;

    @PutMapping
    public ResponseEntity<Film> save(@RequestBody Film film) {
        try {
            return new ResponseEntity<>(filmService.save(film), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping
    public ResponseEntity<Film> update(@RequestBody Film film) {
        try {
            return new ResponseEntity<>(filmService.update(film), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Film> findById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(filmService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            Film film = filmService.findById(id);
            filmService.delete(film);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(path = "by-movie-title")
    public ResponseEntity<List> findAllByMovieTitle(@RequestParam String movieTitle) {
        try {
            return new ResponseEntity<>(filmService.findAllByMovieTitle(movieTitle), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(path = "by-release-date")
    public ResponseEntity<List> findAllByReleaseDate(@RequestParam LocalDate releaseDate) {
        try {
            return new ResponseEntity<>(filmService.findAllByReleaseDate(releaseDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(path = "by-release-date-before-after")
    public ResponseEntity<List> findAllByReleaseDateBeforeAndReleaseDateIsAfter(@RequestParam LocalDate releaseDateBefore, LocalDate releaseDateAfter) {
        try {
            return new ResponseEntity<>(filmService.findAllByReleaseDateBeforeAndReleaseDateIsAfter(releaseDateBefore, releaseDateAfter), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
