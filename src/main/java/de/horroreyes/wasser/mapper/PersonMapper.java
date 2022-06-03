package de.horroreyes.wasser.mapper;

import de.horroreyes.wasser.dto.PersonDto;
import de.horroreyes.wasser.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDto entityToDto(Person person);

    Person dtoToEntity(PersonDto personDto);
}