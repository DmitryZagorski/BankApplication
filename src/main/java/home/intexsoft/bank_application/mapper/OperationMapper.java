package home.intexsoft.bank_application.mapper;

import home.intexsoft.bank_application.controller.operationController.dto.OperationDto;
import home.intexsoft.bank_application.models.Operation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OperationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "actions", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    Operation fromOperationDto(OperationDto operationDto);

    @Mapping(target = "actionsDto", ignore = true)
    OperationDto fromOperation(Operation operation);

}