package org.statsenko.mapper.filter;

import dto.request.ClientFilterDto;
import org.mapstruct.Mapper;
import org.statsenko.filter.EntityFilter;

@Mapper
public interface ClientFilterMapper {

    EntityFilter toFilter(ClientFilterDto dto);
}
