package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.FacultyService;
import co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.FacultyServiceScheduler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class FacultyServiceSchedulerTest {
    @Autowired
    private FacultyServiceScheduler scheduler;

    @MockBean
    private FacultyService facultyService;

    @Test
    void testCleanInactiveFaculties() {
        // Simulamos la ejecución del scheduler
        scheduler.cleanInactiveFaculties();

        // Verifica que la lógica de eliminación de facultades se haya ejecutado correctamente
        Mockito.verify(facultyService, Mockito.atLeastOnce()).deleteFaculty(Mockito.anyLong());
    }
}
