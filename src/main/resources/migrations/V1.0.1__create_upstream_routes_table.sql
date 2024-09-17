CREATE table IF NOT EXISTS upstream_routes
(
    id
    varchar
(
    255
) primary key,
    service varchar
(
    255
),
    host varchar
(
    255
),
    path varchar
(
    255
),
    request_headers text,
    response_headers text,
    request_filters text,
    response_filters text,
    status boolean,
    description varchar
(
    255
),
    response_type varchar
(
    255
)
    )