package co.edu.icesi.dev.outcome_curr_mgmt.service.faculty;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FacultyServiceScheduler {
    private static final Logger logger = LoggerFactory.getLogger(FacultyServiceScheduler.class);
    private final FacultyService facultyService;

    // Programación de la tarea: cada media noche
    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanInactiveFaculties() {
        logger.info("Scheduled task: Cleaning inactive faculties.");
        List<FacultyOutDTO> faculties = facultyService.getFaculties();
        faculties.stream()
                .filter(faculty -> faculty.facIsActive() == 'N') // Facultades inactivas
                .forEach(faculty -> {
                    try {
                        facultyService.deleteFaculty(faculty.facId());
                        logger.info("Inactive faculty deleted: {}", faculty.facId());
                    } catch (Exception e) {
                        logger.error("Failed to delete faculty {}: {}", faculty.facId(), e.getMessage());
                    }
                });
    }

}
