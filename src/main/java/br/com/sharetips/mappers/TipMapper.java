package br.com.sharetips.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class  TipMapper {
    public static final TipMapper INSTANCE = Mappers.getMapper(TipMapper.class);


}
