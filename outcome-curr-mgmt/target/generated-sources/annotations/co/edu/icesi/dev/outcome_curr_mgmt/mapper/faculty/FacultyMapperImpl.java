package co.edu.icesi.dev.outcome_curr_mgmt.mapper.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.AcadProgramOutDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.Faculty;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-01T20:41:05-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class FacultyMapperImpl implements FacultyMapper {

    @Override
    public Faculty facultyInDTOToFaculty(FacultyInDTO facultyInDTO) {
        if ( facultyInDTO == null ) {
            return null;
        }

        Faculty.FacultyBuilder faculty = Faculty.builder();

        faculty.externalId( facultyInDTO.externalId() );
        faculty.facNameEng( facultyInDTO.facNameEng() );
        faculty.facNameSpa( facultyInDTO.facNameSpa() );

        faculty.facIsActive( facultyInDTO.isActive().charAt(0) );

        return faculty.build();
    }

    @Override
    public FacultyOutDTO facultyToFacultyOutDTO(Faculty faculty) {
        if ( faculty == null ) {
            return null;
        }

        FacultyOutDTO.FacultyOutDTOBuilder facultyOutDTO = FacultyOutDTO.builder();

        facultyOutDTO.facId( faculty.getFacId() );
        facultyOutDTO.facIsActive( faculty.getFacIsActive() );
        facultyOutDTO.facNameEng( faculty.getFacNameEng() );
        facultyOutDTO.facNameSpa( faculty.getFacNameSpa() );
        facultyOutDTO.acadPrograms( acadProgramListToAcadProgramOutDTOList( faculty.getAcadPrograms() ) );

        return facultyOutDTO.build();
    }

    @Override
    public List<FacultyOutDTO> facultiesToFacultiesOutDTO(List<Faculty> all) {
        if ( all == null ) {
            return null;
        }

        List<FacultyOutDTO> list = new ArrayList<FacultyOutDTO>( all.size() );
        for ( Faculty faculty : all ) {
            list.add( facultyToFacultyOutDTO( faculty ) );
        }

        return list;
    }

    @Override
    public FacultyOutDTO facultyInDTOToFacultyOutDTO(FacultyInDTO facultyInDTO) {
        if ( facultyInDTO == null ) {
            return null;
        }

        FacultyOutDTO.FacultyOutDTOBuilder facultyOutDTO = FacultyOutDTO.builder();

        facultyOutDTO.facNameEng( facultyInDTO.facNameEng() );
        facultyOutDTO.facNameSpa( facultyInDTO.facNameSpa() );

        facultyOutDTO.facIsActive( facultyInDTO.isActive().charAt(0) );

        return facultyOutDTO.build();
    }

    protected AcadProgramOutDTO acadProgramToAcadProgramOutDTO(AcadProgram acadProgram) {
        if ( acadProgram == null ) {
            return null;
        }

        AcadProgramOutDTO.AcadProgramOutDTOBuilder acadProgramOutDTO = AcadProgramOutDTO.builder();

        acadProgramOutDTO.acpId( acadProgram.getAcpId() );
        acadProgramOutDTO.acpIsActive( acadProgram.getAcpIsActive() );
        acadProgramOutDTO.acpProgNameSpa( acadProgram.getAcpProgNameSpa() );
        acadProgramOutDTO.acpProgNameEng( acadProgram.getAcpProgNameEng() );
        acadProgramOutDTO.acpProgDescSpa( acadProgram.getAcpProgDescSpa() );
        acadProgramOutDTO.acpProgDescEng( acadProgram.getAcpProgDescEng() );
        acadProgramOutDTO.acpSnies( acadProgram.getAcpSnies() );

        return acadProgramOutDTO.build();
    }

    protected List<AcadProgramOutDTO> acadProgramListToAcadProgramOutDTOList(List<AcadProgram> list) {
        if ( list == null ) {
            return null;
        }

        List<AcadProgramOutDTO> list1 = new ArrayList<AcadProgramOutDTO>( list.size() );
        for ( AcadProgram acadProgram : list ) {
            list1.add( acadProgramToAcadProgramOutDTO( acadProgram ) );
        }

        return list1;
    }
}
