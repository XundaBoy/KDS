package app.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Categoria;
import app.repository.CategoriaRepository;


@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	 public Categoria save(Categoria categoria) {
	        return categoriaRepository.save(categoria);
	    }
	 
	 
	 public Categoria update(Categoria categoria, Long id) {
	        Categoria existente = findById(id);
	        existente.setNome(categoria.getNome());
	        return categoriaRepository.save(existente);
	    }
	
	 public void delete(Long id) {
	        if(!categoriaRepository.existsById(id)) {
	            throw new RuntimeException("Categoria não encontrada");
	        }
	        categoriaRepository.deleteById(id);
	    }
	
	public List<Categoria> findAll(){
		List<Categoria> lista = new ArrayList<>();
		lista = this.categoriaRepository.findAll();
		return lista;
	}
	
	public Categoria findById(Long id) {
	    return categoriaRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
	}

}
