package de.horroreyes.wasser.mapper;

import org.mapstruct.Mapper;

import de.horroreyes.wasser.dto.PersonDto;
import de.horroreyes.wasser.model.Person;

@Mapper(componentModel = "spring")
public interface PersonDtoMapper {
    PersonDto entityToDto(PersonDto person);

    Person dtoToEntity(Person personDto);
}