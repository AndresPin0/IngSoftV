package co.edu.icesi.dev.outcome_curr_mgmt.rs.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_qa.AuthStudentOutcomeController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa.StudOutcomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthStudentOutcomeControllerImpl implements AuthStudentOutcomeController {
    private final StudOutcomeService studOutcomeService;

}
