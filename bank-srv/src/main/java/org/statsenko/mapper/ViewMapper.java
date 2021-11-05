package org.statsenko.mapper;

import org.statsenko.entity.AbstractEntity;

import java.util.List;

public interface ViewMapper <E extends AbstractEntity, D>{

    D toDto(E entity);

    List<D> toDtoList(List<E> entityList);

    E toEntity(D dto);

    List<E> toEntityList(List<D> dtoList);


}