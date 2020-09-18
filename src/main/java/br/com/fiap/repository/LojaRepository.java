package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.model.LojaModel;

@Repository
public interface LojaRepository extends JpaRepository<LojaModel, Long> {

	@Transactional
	@Modifying
	@Query(value = "UPDATE tb_loja SET NOME_LOJA = ?1, URL = ?2 WHERE ID_LOJA = ?3", nativeQuery = true)
	void updateLoja(String nome, String url, long id);

}
