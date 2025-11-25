package org.acme.mapper;

import org.acme.entity.Table;
import org.acme.model.ApiTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface TableMapper {

    @Mapping(target = "id", ignore = true)
    void mapToTable(ApiTable apiTable, @MappingTarget Table table);

    ApiTable mapToApiTable(Table table);
}
