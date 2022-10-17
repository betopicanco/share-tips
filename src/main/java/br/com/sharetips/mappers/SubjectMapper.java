package br.com.sharetips.mappers;

import br.com.sharetips.entities.Subject;
import br.com.sharetips.entities.dto.subject.SubjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class SubjectMapper {
    public static final SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    public abstract Subject toSubject(SubjectDTO subjectDTO);
}
