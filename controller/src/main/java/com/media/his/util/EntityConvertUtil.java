package com.media.his.util;

import com.media.his.entity.ContractDO;
import com.media.his.entity.ProjectDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EntityConvertUtil {

    EntityConvertUtil INSTANCE = Mappers.getMapper(EntityConvertUtil.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "projectDO.projectName", target = "contractName"),
            @Mapping(source = "projectDO.projectType", target = "contractType"),
            @Mapping(source = "projectDO.projectDesc",target = "contractDesc")
    })
    ContractDO projectToContract(ProjectDO projectDO);

    List<ContractDO> projectToContracts(List<ProjectDO> projectDOS);
}
