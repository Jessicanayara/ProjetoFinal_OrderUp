package com.order.project_orderup.repository;

import com.order.project_orderup.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByUsuarioCpf(String cpf);

    Cliente findByIdAndUsuarioCpf(Long clienteId, String usuarioId);

    Cliente findByCpf(String cpf);

    Cliente findByCnpj(String cnpj);




    Cliente findByNomeAndUsuarioCpf(String nome, String usuarioId);

    @Query("SELECT MAX(c.numeroCliente) FROM Cliente c WHERE c.usuario.cpf = :usuarioId")
    Integer findMaxNumeroClienteByUsuario(@Param("usuarioId") String usuarioId);
}
