	package app.repository;
	
	
	
	import org.springframework.data.jpa.repository.JpaRepository;
	
	import app.entity.Categoria;
	
	
	public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
		
	}
	
