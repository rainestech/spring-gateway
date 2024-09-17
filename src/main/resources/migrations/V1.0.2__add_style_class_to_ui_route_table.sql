ALTER TABLE ui_routes
    ADD COLUMN IF NOT EXISTS style_class VARCHAR (255);
ALTER TABLE upstream_routes
    ADD COLUMN IF NOT EXISTS permissions text;
