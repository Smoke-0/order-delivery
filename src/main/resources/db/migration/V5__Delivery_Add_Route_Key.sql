alter table deliveries
    add route_id int null;
alter table routes
    add delivery_id int null;
alter table deliveries
    add constraint FK_DELIVERY_ROUTE
        foreign key (route_id)
        references routes(route_id);
alter table routes
    add constraint FK_REOUTE_DELIVERY
        foreign key (delivery_id)
            references deliveries(delivery_id);