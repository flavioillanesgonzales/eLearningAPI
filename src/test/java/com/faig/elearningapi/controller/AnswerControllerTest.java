package com.faig.elearningapi.controller;

import com.faig.elearningapi.dto.AnswerDTO;
import com.faig.elearningapi.model.Answer;
import com.faig.elearningapi.model.Question;
import com.faig.elearningapi.repository.AnswerRepository;
import com.faig.elearningapi.service.AnswertService;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AnswerControllerTest {

    @Mock
    private AnswerRepository answerRepository;

    @InjectMocks
    private AnswertService answerService;

    private Answer answer;

    private AnswerDTO answerDTO;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        answer = new Answer();
        answer.setId(1L);
        answer.setText("JavaScript");
        answer.setCorrect(true);
        answer.setQuestion(new Question() );

    }

    @Test
    void getAnswerById() {
        // Configurar el mock para que devuelva un Optional que contiene la respuesta
        Mockito.when(answerRepository.findById(1L)).thenReturn(Optional.of(answer));

        // Ejecutar el método que estás probando
        AnswerDTO result = answerService.getAnswerById(1L);

        // Validaciones
        assertNotNull(result); // Asegurar que el resultado no sea nulo
        assertEquals("JavaScript", result.getText());

        // Verificar que el método findById fue llamado en el mock
        Mockito.verify(answerRepository).findById(1L);
    }

}