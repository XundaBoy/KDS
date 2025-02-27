package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Console;

public interface ConsoleRepository extends JpaRepository<Console, Long>{
	
}
