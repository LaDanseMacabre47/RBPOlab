INSERT INTO movies (title, duration_minutes) VALUES ('Interstellar', 169) ON CONFLICT DO NOTHING;
INSERT INTO movies (title, duration_minutes) VALUES ('Inception', 148) ON CONFLICT DO NOTHING;
INSERT INTO halls (name, capacity) VALUES ('Main Hall', 100) ON CONFLICT DO NOTHING;
INSERT INTO halls (name, capacity) VALUES ('Small Hall', 2) ON CONFLICT DO NOTHING;
INSERT INTO customers (name, email) VALUES ('Alice','alice@example.com') ON CONFLICT DO NOTHING;
INSERT INTO customers (name, email) VALUES ('Bob','bob@example.com') ON CONFLICT DO NOTHING;
INSERT INTO screenings (movie_id, hall_id, start_time, price)
  SELECT m.id, h.id, (NOW() + INTERVAL '1 day')::timestamp, 10.0
  FROM movies m, halls h WHERE m.title='Interstellar' AND h.name='Small Hall' LIMIT 1
ON CONFLICT DO NOTHING;
