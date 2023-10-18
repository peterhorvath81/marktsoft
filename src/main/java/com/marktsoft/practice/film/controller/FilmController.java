package com.marktsoft.practice.film.controller;

import com.marktsoft.practice.film.controller.dto.FilmDTO;
import com.marktsoft.practice.film.controller.dto.FilmResponseDTO;
import com.marktsoft.practice.film.controller.dto.FilmUpdateDTO;
import com.marktsoft.practice.film.service.FilmService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
@AllArgsConstructor
public class FilmController {

    private FilmService filmService;

    @GetMapping
    public List<FilmDTO> getAll() {
        return filmService.findAll();
    }

    @GetMapping("/{id}")
    public FilmDTO findById(@PathVariable("id") Integer id) {
        return filmService.findById(id);
    }

    @PostMapping
    public FilmResponseDTO register(@RequestBody FilmDTO filmDTO) {
        return filmService.create(filmDTO);
    }

    @PutMapping("/{id}")
    public FilmResponseDTO update(@PathVariable("id") Integer filmId, @RequestBody FilmUpdateDTO filmUpdateDTO) {
        return filmService.update(filmId, filmUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        filmService.delete(id);
        return "Film deleted with id: " +id;
    }
}
