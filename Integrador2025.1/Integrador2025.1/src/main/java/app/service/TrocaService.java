package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Troca;
import app.repository.TrocaRepository;

@Service
public class TrocaService {

	@Autowired
    private TrocaRepository trocaRepository;

  public String save(Troca troca) {
	  this.trocaRepository.save(troca);
	  return "Troca efetuada com sucesso";
  }
  
 public List<Troca> findAll(){
	 return trocaRepository.findAll();
 }
 
 public Troca findById(Long id) {
	 Troca troca = new Troca();
	 troca = trocaRepository.findById(id).get();
	 return troca;
 }
}
