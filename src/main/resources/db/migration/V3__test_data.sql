-- Insert sample venues
INSERT INTO venues (name, location, capacity) VALUES
('Centro de Convenciones Cartagena', 'Carrera 8 #12-85, Cartagena', 2000),
('Teatro Heredia', 'Plaza de la Merced, Cartagena', 800),
('Plaza de Toros Cartagena de Indias', 'Getsemaní, Cartagena', 5000),
('Centro de Convenciones Julio César Turbay Ayala', 'Avenida Arroz Barato, Cartagena', 4000),
('Club de Pesca', 'Carrera 1 #6-06, Cartagena', 300);

-- Insert sample events
INSERT INTO events (name, date, capacity, category, venue_id) VALUES
('Festival Internacional de Música', '2025-12-15 19:00:00', 1500, 'Música', 1),
('Obra de Teatro: La Casa de Bernarda Alba', '2025-12-20 20:00:00', 700, 'Teatro', 2),
('Concierto de Rock Clásico', '2026-01-10 21:00:00', 4500, 'Música', 3),
('Congreso de Tecnología 2026', '2026-02-05 09:00:00', 3500, 'Conferencia', 4),
('Noche de Jazz en el Club de Pesca', '2025-12-25 22:00:00', 250, 'Música', 5),
('Feria Gastronómica del Caribe', '2026-01-15 10:00:00', 1800, 'Gastronomía', 1),
('Festival de Danza Contemporánea', '2026-01-20 19:30:00', 900, 'Danza', 2),
('Exposición de Arte Moderno', '2026-02-01 10:00:00', 400, 'Arte', 4);

-- Update the timestamps to be different for each record
UPDATE venues SET created_at = '2025-01-01 00:00:00', updated_at = '2025-01-01 00:00:00';
UPDATE events SET created_at = date, updated_at = date;
