package com.order.project_orderup.repository;

import com.order.project_orderup.model.Ordem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrdemRepository extends JpaRepository<Ordem, Long> {
    List<Ordem> findByUsuarioCpf(String usuarioId);

    @Query("SELECT MAX(o.numeroOrdem) FROM Ordem o WHERE o.usuario.cpf = :usuarioId")
    Integer findMaxNumeroOrdemByUsuario(@Param("usuarioId") String usuarioId);

    Ordem findByIdAndUsuarioCpf(Long ordemId, String usuarioId);


}
