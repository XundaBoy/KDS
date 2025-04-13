package app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
			 if (usuario.getTelefone() == null || usuario.getTelefone().trim().isEmpty()) {
		            usuario.setStatusCadastro("INCOMPLETO"); 
		        } else {
		            usuario.setStatusCadastro("COMPLETO"); 
		        }
			usuarioRepository.save(usuario);
			return "Usuario salvo com sucesso!";
		}
		
		public String update (Usuario usuario, Long id) {
			usuario.setId(id);
			this.usuarioRepository.save(usuario);
			return "Usuario atualizado com sucesso";
		}
		
		public String delete (long id) {
			usuarioRepository.deleteById(id);
			return "Usuario deletado com sucesso!";
		}
		
		public  Optional<Usuario> findById(Long id) {
			return this.usuarioRepository.findById(id);
		}
		
		public List<Usuario> findByNomeStartingWithIgnoreCase(String nome){
			return this.usuarioRepository.findByNomeStartingWithIgnoreCase(nome);
		}
		
		public List<Usuario> findAll(){
			List<Usuario> lista = new ArrayList<>();
			lista = this.usuarioRepository.findAll();
			return lista;
		}
}
