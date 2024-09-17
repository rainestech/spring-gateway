CREATE TABLE IF NOT EXISTS privileges
(
    id
    varchar
(
    255
) primary key,
    name varchar
(
    255
),
    service varchar
(
    255
)
    );

ALTER TABLE upstream_routes
    ADD COLUMN IF NOT EXISTS privilege_id VARCHAR (255);
ALTER TABLE upstream_routes
    ADD FOREIGN KEY (privilege_id) REFERENCES privileges (id) ON DELETE no action ON UPDATE no action;
