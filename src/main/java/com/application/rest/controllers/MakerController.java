package com.application.rest.controllers;

import com.application.rest.entities.dto.MakerDTO;
import com.application.rest.entities.Maker;
import com.application.rest.services.IMakerService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/makers")
public class MakerController {

  private static final String MAKER_BASE_URL = "/api/makers";

  @Autowired
  private IMakerService makerService;

  @GetMapping
  public ResponseEntity<List<MakerDTO>> findAll() {
    List<MakerDTO> makerList = makerService.findAll().stream()
        .map(makerService::toDTO)
        .toList();
    return ResponseEntity.ok(makerList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<MakerDTO> findById(@PathVariable Long id) {
    Optional<Maker> makerOptional = makerService.findById(id);
    if (makerOptional.isPresent()) {
      Maker maker = makerOptional.get();
      MakerDTO makerDTO = makerService.toDTO(maker);
      return ResponseEntity.ok(makerDTO);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<MakerDTO> save(@RequestBody MakerDTO makerDTO) throws URISyntaxException {
    if (makerDTO.getName().isBlank()) {
      return ResponseEntity.badRequest().build();
    }
    Maker maker = makerService.save(Maker.builder()
        .name(makerDTO.getName())
        .build());
    makerDTO.setId(maker.getId());
    return ResponseEntity.created(new URI( MAKER_BASE_URL + "/" + maker.getId())).body(makerDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<MakerDTO> update(@PathVariable Long id, @RequestBody MakerDTO makerDTO) {
    if (makerDTO.getName().isBlank()) {
      return ResponseEntity.badRequest().build();
    }
    Optional<Maker> makerOptional = makerService.findById(id);
    if (makerOptional.isPresent()) {
      Maker maker = makerOptional.get();
      maker.setName(makerDTO.getName());
      makerService.save(maker);
      return ResponseEntity.ok().body(makerService.toDTO(maker));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable Long id) {
    if (id == null) {
      return ResponseEntity.badRequest().build();
    }
    Optional<Maker> makerOptional = makerService.findById(id);
    if (makerOptional.isPresent()) {
      makerService.delete(makerOptional.get());
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
