package com.java.gestaoemprestimopix.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.gestaoemprestimopix.dto.conta.ContaCreateRequest;
import com.java.gestaoemprestimopix.dto.conta.ContaResponse;
import com.java.gestaoemprestimopix.entity.Conta;
import com.java.gestaoemprestimopix.entity.Usuario;
import com.java.gestaoemprestimopix.exception.EntityNotFoundException;
import com.java.gestaoemprestimopix.repository.UsuarioRepository;

public class ContaMapper {

    @Autowired
    private static UsuarioRepository usuarioRepository;

    public static Conta map(final ContaCreateRequest request) {
        Usuario usuario = usuarioRepository.findById(UUID.fromString(request.getUsuarioId()))
                .orElseThrow(() -> new EntityNotFoundException(
                        "Usuário com ID " + request.getUsuarioId() + " não encontrado."));
        return new Conta(
                null,
                request.getNumeroConta(),
                request.getAgencia(),
                request.getSaldo(),
                request.getDataCriacao(),
                request.isStatusConta(),
                usuario);
    }

    public static ContaResponse toResponse(final Conta conta) {
        return new ContaResponse(
                conta.getIdConta().toString(),
                conta.getNumeroConta(),
                conta.getAgencia(),
                conta.getSaldo(),
                conta.getDataCriacao(),
                conta.isStatusConta(),
                conta.getUsuario());
    }

    public static List<ContaResponse> toResponseList(final List<Conta> contas) {
        return contas.stream()
                .map(ContaMapper::toResponse)
                .collect(Collectors.toList());
    }

}
