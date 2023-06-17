package com.order.project_orderup.repository;

import com.order.project_orderup.model.Ordem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrdemRepository extends JpaRepository<Ordem, Long> {
    List<Ordem> findByUsuarioId(long usuarioId);



    Ordem findByIdAndUsuarioId(Long ordemId, Long usuarioId);
}
