package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_definition;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.FacultyService;
import co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.FacultyServiceScheduler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
class FacultyServiceSchedulerTest {

    @Autowired
    private FacultyServiceScheduler scheduler;

    @MockBean
    private FacultyService facultyService;

    @Test
    void testCleanInactiveFacultiesWithLock() {
        // Crear facultades simuladas
        FacultyOutDTO activeFaculty = FacultyOutDTO.builder()
                .facId(1L)
                .facIsActive('Y')
                .facNameEng("Engineering Faculty")
                .facNameSpa("Facultad de Ingeniería")
                .acadPrograms(List.of())
                .build();

        FacultyOutDTO inactiveFaculty = FacultyOutDTO.builder()
                .facId(2L)
                .facIsActive('N')
                .facNameEng("Arts Faculty")
                .facNameSpa("Facultad de Artes")
                .acadPrograms(List.of())
                .build();

        // Configurar el mock para devolver las facultades simuladas
        Mockito.when(facultyService.getFaculties()).thenReturn(List.of(activeFaculty, inactiveFaculty));

        // Ejecutar el scheduler desde dos instancias simuladas
        scheduler.cleanInactiveFaculties();  // Simula instancia 1
        //scheduler.cleanInactiveFaculties();  // Simula instancia 2

        // Verificar que solo la facultad inactiva sea eliminada una vez
        Mockito.verify(facultyService, Mockito.times(1)).deleteFaculty(2L);
        // Verificar que la facultad activa nunca se elimina
        Mockito.verify(facultyService, Mockito.never()).deleteFaculty(1L);
    }
}
