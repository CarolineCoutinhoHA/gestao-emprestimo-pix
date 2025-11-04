package com.java.gestaoemprestimopix.mapper;

import com.java.gestaoemprestimopix.dto.Response.EmprestimoResponseDTO;
import com.java.gestaoemprestimopix.entity.model.Emprestimo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmprestimoMapper {

    EmprestimoResponseDTO toResponseDto(Emprestimo emprestimo);

    Emprestimo toEntity (EmprestimoResponseDTO emprestimoResponseDTO);

    List<EmprestimoResponseDTO> toDtoList(List<Emprestimo> emprestimos);
}
