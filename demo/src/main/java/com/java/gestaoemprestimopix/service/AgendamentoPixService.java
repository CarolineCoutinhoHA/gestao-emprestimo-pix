package com.java.gestaoemprestimopix.service;

import com.java.gestaoemprestimopix.dto.Entity1.Request.NovoAgendamentoPixRequest;
import com.java.gestaoemprestimopix.dto.Entity1.Response.NovoAgendamentoPixResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AgendamentoPixService {

    public List<LocalDate> gerarDatasDeParcelas(){

       return Stream.iterate(LocalDate.now(), data -> data.plusDays(5))
               .limit(4)
               .collect(Collectors.toList());

    }

    public NovoAgendamentoPixResponse criarAgendamentoParaEmprestimo(NovoAgendamentoPixRequest request){

    }
}
