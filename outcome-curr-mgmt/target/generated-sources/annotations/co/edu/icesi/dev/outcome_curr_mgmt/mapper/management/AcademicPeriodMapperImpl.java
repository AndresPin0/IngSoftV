package co.edu.icesi.dev.outcome_curr_mgmt.mapper.management;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.management.AcadPeriodInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.management.AcadPeriodOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.AcPeriod;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-30T13:31:15-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class AcademicPeriodMapperImpl implements AcademicPeriodMapper {

    @Override
    public AcPeriod fromAcadPeriodInDTO(AcadPeriodInDTO sourceAcadPeriodInDTO) {
        if ( sourceAcadPeriodInDTO == null ) {
            return null;
        }

        AcPeriod.AcPeriodBuilder acPeriod = AcPeriod.builder();

        acPeriod.acPeriodNameEng( sourceAcadPeriodInDTO.acPeriodNameEng() );
        acPeriod.acPeriodNameSpa( sourceAcadPeriodInDTO.acPeriodNameSpa() );
        acPeriod.acPeriodNumeric( sourceAcadPeriodInDTO.acPeriodNumeric() );

        return acPeriod.build();
    }

    @Override
    public AcadPeriodOutDTO fromAcadPeriod(AcPeriod sourceAcademicPeriod) {
        if ( sourceAcademicPeriod == null ) {
            return null;
        }

        AcadPeriodOutDTO.AcadPeriodOutDTOBuilder acadPeriodOutDTO = AcadPeriodOutDTO.builder();

        acadPeriodOutDTO.acPeriodId( sourceAcademicPeriod.getAcPeriodId() );
        acadPeriodOutDTO.acPeriodNameEng( sourceAcademicPeriod.getAcPeriodNameEng() );
        acadPeriodOutDTO.acPeriodNameSpa( sourceAcademicPeriod.getAcPeriodNameSpa() );
        acadPeriodOutDTO.acPeriodNumeric( sourceAcademicPeriod.getAcPeriodNumeric() );

        return acadPeriodOutDTO.build();
    }
}
