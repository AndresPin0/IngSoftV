package co.edu.icesi.dev.outcome_curr_mgmt.mapper.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.model.curriculum_qa.ValueDTO;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-01T20:41:05-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CurrMapMapperImpl implements CurrMapMapper {

    @Override
    public ValueDTO fromMapElementToValueDTO(Map.Entry<String, String> entry) {
        if ( entry == null ) {
            return null;
        }

        ValueDTO.ValueDTOBuilder valueDTO = ValueDTO.builder();

        valueDTO.key( entry.getKey() );
        valueDTO.value( entry.getValue() );

        return valueDTO.build();
    }
}
