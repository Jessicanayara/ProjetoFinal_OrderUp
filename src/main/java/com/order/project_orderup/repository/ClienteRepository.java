package com.order.project_orderup.repository;

import com.order.project_orderup.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByUsuarioId(long id);

    Cliente findByIdAndUsuarioId(Long clienteId, Long usuarioId);

    Cliente findByCpf(String cpf);

    Cliente findByCnpj(String cnpj);
}
