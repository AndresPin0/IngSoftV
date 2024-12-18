package co.edu.icesi.dev.outcome_curr_mgmt.service.banner;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.academic_registry.client.BannerAPI;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.faculty.FacultyMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.FacultyServiceImpl;
import co.edu.icesi.dev.outcome_curr_mgmt.service.provider.faculty.FacultyProvider;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.FacultyValidator;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.UserPermAccess;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BannerFacultyServiceImpl implements BannerFacultyService {
    private static final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);
    private final FacultyValidator facultyValidator;
    private final FacultyMapper facultyMapper;
    private final FacultyProvider facultyProvider;
    private final BannerAPI bannerAPI;

    // Metric counters
    private static final Counter facultiesQueryCounter = Metrics.counter("banner.faculty.query.count");
    private static final Counter facultiesImportCounter = Metrics.counter("banner.faculty.import.count");

    // Metric timers
    private static final Timer getFacultiesTimer = Metrics.timer("banner.faculty.get.time");
    private static final Timer getFacultiesPageTimer = Metrics.timer("banner.faculty.page.get.time");
    private static final Timer importFacultiesTimer = Metrics.timer("banner.faculty.import.time");

    @Transactional
    @Override
    public List<FacultyOutDTO> getBannerFaculties() {
        logger.info("Getting faculties from Banner");
        Timer.Sample sample = Timer.start();
        try {
            validateAccess(0L, UserPermAccess.QUERY);
            List<FacultyInDTO> facultiesList = bannerAPI.getFacultiesList();
            if (facultiesList.isEmpty()) {
                logger.error("There are no faculties in Banner.");
                throw new OutCurrException(OutCurrExceptionType.FACULTY_NOT_FOUND);
            }
            facultiesQueryCounter.increment();
            return facultiesList.stream()
                    .map(facultyMapper::facultyInDTOToFacultyOutDTO)
                    .collect(Collectors.toList());
        } finally {
            sample.stop(getFacultiesTimer);
        }
    }

    @Transactional
    @Override
    public Page<FacultyOutDTO> getBannerFacultiesPage(int page, int size) {
        logger.info("Getting faculties from banner in pages");
        Timer.Sample sample = Timer.start();
        try {
            validateAccess(0L, UserPermAccess.QUERY);
            Page<FacultyInDTO> bannerFacultyInDTOS = bannerAPI.getFacultiesPage(page, size);
            if (bannerFacultyInDTOS.getContent().isEmpty()) {
                logger.error("There are no faculties on this page.");
                throw new OutCurrException(OutCurrExceptionType.FACULTY_NOT_FOUND);
            }
            return new PageImpl<>(
                    bannerFacultyInDTOS.stream()
                            .map(facultyMapper::facultyInDTOToFacultyOutDTO)
                            .collect(Collectors.toList()),
                    PageRequest.of(bannerFacultyInDTOS.getNumber(), bannerFacultyInDTOS.getSize()),
                    bannerFacultyInDTOS.getTotalElements()
            );
        } finally {
            sample.stop(getFacultiesPageTimer);
        }
    }

    @Transactional
    @Override
    public List<FacultyOutDTO> importBannerFaculties(List<String> facultiesNames) {
        validateAccess(0L, UserPermAccess.ADMIN);
        logger.info("Importing faculties into db.");
        Timer.Sample sample = Timer.start();
        try {
            facultiesImportCounter.increment();
            return bannerAPI.importFaculties(facultiesNames).stream()
                    .map(facultyProvider::saveFaculty)
                    .toList();
        } finally {
            sample.stop(importFacultiesTimer);
        }
    }

    private void validateAccess(long facultyId, UserPermAccess permAccess) {
        logger.info("Checking permissions to execute this operation.");
        facultyValidator.enforceUsrFacForFaculty(facultyId, permAccess);
    }
}
