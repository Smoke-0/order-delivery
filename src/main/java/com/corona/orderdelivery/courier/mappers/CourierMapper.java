package com.corona.orderdelivery.courier.mappers;

import com.corona.orderdelivery.courier.domain.Courier;
import com.corona.orderdelivery.courier.dto.CourierDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourierMapper {

    CourierMapper INSTANCE = Mappers.getMapper(CourierMapper.class);

    CourierDto courierToCourierDto(Courier courier);

    Courier courierDtoToCourier(CourierDto courierDto);
}
