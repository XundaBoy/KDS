package app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Usuario;
import app.repository.UsuarioRepository;

@Service
public class UsuarioService {

		@Autowired
		private UsuarioRepository usuarioRepository;
		
		public String save (Usuario usuario) {
			if(usuarioRepository.existsByCpf(usuario.getCpf())) {
				throw new IllegalArgumentException("Cpf ja cadastrado");
				
				
			}
			usuarioRepository.save(usuario);
			return "Usuario salvo com sucesso!";
		}
		
		public String delete (long id) {
			usuarioRepository.deleteById(id);
			return "Usuario deletado com sucesso!";
		}
		
		public Usuario findById(Long id) {
			return usuarioRepository.findById(id).orElse(null);
		}
		
		public List<Usuario> findByNomeStartingWith(String nome){
			return this.usuarioRepository.findByNomeStartingWith(nome);
		}
		
		public List<Usuario> findAll(){
			List<Usuario> lista = new ArrayList<>();
			lista = this.usuarioRepository.findAll();
			return lista;
		}
}
