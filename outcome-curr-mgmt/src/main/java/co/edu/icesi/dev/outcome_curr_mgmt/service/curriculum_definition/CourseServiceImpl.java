package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.Course;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_definition.CourseRepository;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    private final CourseRepository courseRepository;

    @Override
    public List<Course> findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(long acadProgId, long facultyId, long acadProgCurrId) {
        MDC.put("operation", "findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId");
        MDC.put("acadProgId", String.valueOf(acadProgId));
        MDC.put("facultyId", String.valueOf(facultyId));
        MDC.put("acadProgCurrId", String.valueOf(acadProgCurrId));
        MDC.put("transactionId", UUID.randomUUID().toString());

        logger.info("Starting to find courses.");

        try {
            List<Course> courses = courseRepository.findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(acadProgId, facultyId, acadProgCurrId);
            logger.info("Successfully retrieved {} courses.", courses.size());
            return courses;
        } catch (Exception e) {
            logger.error("Error while retrieving courses.", e);
            throw e;
        } finally {
            MDC.clear();
        }
    }
}

