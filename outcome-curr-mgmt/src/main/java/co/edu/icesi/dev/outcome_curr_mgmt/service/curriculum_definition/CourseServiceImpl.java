package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.Course;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_definition.CourseRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    private final CourseRepository courseRepository;

    @Override
    public List<Course> findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(long acadProgId, long facultyId, long acadProgCurrId) {
        return courseRepository.findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(acadProgId, facultyId, acadProgCurrId);
    }
}
