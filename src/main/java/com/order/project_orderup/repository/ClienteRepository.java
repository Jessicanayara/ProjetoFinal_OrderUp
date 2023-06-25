package com.order.project_orderup.repository;

import com.order.project_orderup.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByUsuarioCpf(String cpf);

    Cliente findByIdAndUsuarioId(Long clienteId, String usuarioId);

    Cliente findByCpf(String cpf);

    Cliente findByCnpj(String cnpj);

    Cliente findByNome(String nome);
}
