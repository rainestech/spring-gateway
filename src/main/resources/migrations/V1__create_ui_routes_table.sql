CREATE table IF NOT EXISTS ui_routes
(
    id
    varchar
(
    255
) primary key,
    app varchar
(
    255
),
    status boolean,
    url varchar
(
    255
),
    module varchar
(
    255
),
    name varchar
(
    255
),
    order_no int,
    has_children boolean,
    icon varchar
(
    255
),
    privilege varchar
(
    255
)
    )