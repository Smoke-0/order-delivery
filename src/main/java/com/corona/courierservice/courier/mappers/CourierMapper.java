package com.corona.courierservice.courier.mappers;

import com.corona.courierservice.courier.domain.Courier;
import com.corona.courierservice.courier.dto.CourierDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourierMapper {

    CourierMapper INSTANCE = Mappers.getMapper(CourierMapper.class);

    CourierDto courierToCourierDto(Courier courier);

    Courier courierDtoToCourier(CourierDto courierDto);
}
