package com.java.gestaoemprestimopix.service;

import com.java.gestaoemprestimopix.dto.Response.EmprestimoResponseDTO;
import com.java.gestaoemprestimopix.entity.model.Emprestimo;
import com.java.gestaoemprestimopix.exception.BusinessException;
import com.java.gestaoemprestimopix.exception.ResourceNotFoundException;
import com.java.gestaoemprestimopix.mapper.EmprestimoMapper;
import com.java.gestaoemprestimopix.repository.EmprestimoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

// Local: src/main/java/com/java/gestaoemprestimopix.service/EmprestimoService.java

// ... (Imports omitidos) ...

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true) // Padrão: A maioria é leitura
public class EmprestimoService {

    // ====================================================
    // ATRIBUTOS (DEPENDÊNCIAS)
    // ====================================================
    private final EmprestimoRepository emprestimoRepository;
    private final EmprestimoMapper mapper;
    private final SimulacaoRepository simulacaoRepository;
    private final ClienteRepository clienteRepository; // Exemplo de outra dependência

    // ... (Métodos Helpers find/validar omitidos, mas seriam incluídos aqui) ...

    // ====================================================
    // MÉTODOS PÚBLICOS DE LEITURA (@Transactional(readOnly = true))
    // ====================================================

    /**
     * + buscarEmprestimo: retorna Emprestimo
     * Busca um único empréstimo por ID.
     */
    public EmprestimoResponseDTO buscarEmprestimo(Long id) {
        log.debug("Buscando empréstimo pelo ID: {}", id);
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empréstimo não encontrado."));
        return mapper.toResponseDto(emprestimo);
    }

    /**
     * + buscarEmprestimos: List<Emprestimo>
     * Lista todos os empréstimos (pode ser paginado em sistemas reais).
     */
    public List<EmprestimoResponseDTO> buscarEmprestimos() {
        log.debug("Buscando todos os empréstimos registrados.");
        return emprestimoRepository.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * + buscarEmprestimosPagos: List<Emprestimo>
     * Busca contratos já quitados/finalizados.
     */
    public List<EmprestimoResponseDTO> buscarEmprestimosPagos() {
        log.debug("Buscando empréstimos com status de pagos/liquidados.");
        // Assumindo que o Repository tem um método customizado:
        return emprestimoRepository.findByStatusEmprestimo("LIQUIDADO").stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * + buscarEmprestimosPorStatus: List<Emprestimo>
     * Busca empréstimos filtrados por um status específico (ex: ATIVO, ATRASADO).
     */
    public List<EmprestimoResponseDTO> buscarEmprestimosPorStatus(String status) {
        log.debug("Buscando empréstimos por status: {}", status);
        // Assumindo que o Repository tem um método customizado:
        return emprestimoRepository.findByStatusEmprestimo(status).stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // ====================================================
    // MÉTODOS PÚBLICOS DE ESCRITA (@Transactional)
    // ====================================================

    /**
     * + cadastrarEmprestimo: retorna Emprestimo
     * Ação complexa que envolve validação de simulação.
     * Delegamos a lógica completa ao método 'criarEmprestimo' (visto anteriormente).
     */
    @Transactional
    public EmprestimoResponseDTO cadastrarEmprestimo(SimulacaoRequestDTO simulacaoRequestDTO) {
        // Renomeado e adaptado para a lógica de contratação já discutida.
        return criarEmprestimo(simulacaoRequestDTO.simulacaoId());
    }

    // (O método criarEmprestimo completo, com Hard Delete, estaria aqui)

    /**
     * + quitarEmprestimo
     * Marca o contrato como liquidado (RN: Deve calcular juros/multas até a data).
     */
    @Transactional
    public EmprestimoResponseDTO quitarEmprestimo(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new ResourceNotFoundException("Empréstimo não encontrado."));

        // 🚨 RN: Lógica complexa de cálculo de liquidação antecipada
        // emprestimo.calcularValorQuitacao();
        emprestimo.setStatusEmprestimo("LIQUIDADO");

        log.info("Empréstimo ID {} quitado antecipadamente.", emprestimoId);
        return mapper.toResponseDto(emprestimoRepository.save(emprestimo));
    }

    /**
     * + marcarEmprestimoComoAtrasado: void
     * Altera o status do contrato para atraso. (Geralmente feito por rotinas agendadas).
     */
    @Transactional
    public void marcarEmprestimoComoAtrasado(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new ResourceNotFoundException("Empréstimo não encontrado."));

        // 🚨 RN: Aplicar multa/juros de atraso aqui, antes de mudar o status.
        // emprestimo.aplicarMultaAtraso();
        emprestimo.setStatusEmprestimo("ATRASADO");

        emprestimoRepository.save(emprestimo);
        log.warn("Empréstimo ID {} marcado como ATRASADO.", emprestimoId);
    }

    /**
     * + marcarParcelaComoPaga: void
     * Ação que interage com a entidade Parcela (ou a lista de Parcelas dentro de Emprestimo).
     */
    @Transactional
    public void marcarParcelaComoPaga(Long emprestimoId, Integer numeroParcela) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new ResourceNotFoundException("Empréstimo não encontrado."));

        // 🚨 RN: Encontrar a parcela, validar o valor pago e registrar o pagamento.
        // emprestimo.registrarPagamentoParcela(numeroParcela);

        // Salva as alterações na entidade Empréstimo (que deve cascatear para Parcela)
        emprestimoRepository.save(emprestimo);
        log.info("Parcela {} do Empréstimo ID {} marcada como paga.", numeroParcela, emprestimoId);
    }
}


