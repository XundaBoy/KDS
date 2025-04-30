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
	
	public String save (Categoria categoria) {
		categoriaRepository.save(categoria);
		return "Cidade salva com sucesso!";
	}
	public String update (Categoria categoria, Long id) {
		categoria.setId(id);
		this.categoriaRepository.save(categoria);
		return "Cidade atualizado com sucesso";
	}
	
	public String delete(Long id) {
		categoriaRepository.deleteById(id);
		return"Cidade deletada com sucesso!";
	}
	
	public List<Categoria> findAll(){
		List<Categoria> lista = new ArrayList<>();
		lista = this.categoriaRepository.findAll();
		return lista;
	}
	
	public Categoria findById(Long id) {
		return categoriaRepository.findById(id).orElse(null);
	}
}
