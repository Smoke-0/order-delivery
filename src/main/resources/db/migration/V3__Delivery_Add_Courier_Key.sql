alter table courier_deliveries
    add courier_id int null;
alter table courier_deliveries
    add constraint FK_DELIVERY_COURIER
        foreign key (courier_id)
        references couriers(courier_id);