INSERT INTO users (name,
                   email) VALUES
                              ('John Doe', 'john@example.com'),
                              ('Jane Smith', 'jane@example.com'),
                              ('Alice Johnson', 'alice@example.com'),
                              ('Dan Hacker', 'pidppid@gmail.com');


-- Insert example lockers
INSERT INTO lockers (address) VALUES
                                  ('123 Main St'),
                                  ('456 Elm St' );


-- Insert cells for Locker LCKR001
INSERT INTO locker_cells (locker_id,
                          cell_size,
                          is_available) VALUES
                                            (1, 'SMALL',  false),
                                            (1, 'MEDIUM', false),
                                            (1, 'MEDIUM', true),
                                            (1, 'LARGE',  true),
                                            (2, 'SMALL',  false),
                                            (2, 'MEDIUM', true),
                                            (2, 'MEDIUM', true),
                                            (2, 'LARGE',  false);

INSERT INTO parcels (locker_id,
                     cell_id,
                     size,
                     parcel_type,
                     sender_id,
                     recipient_id,
                     pickup_code,
                     is_collected) VALUES
                                       (1, 1, 'SMALL', 'SHORT_TERM', 1, 2, 'ABC123', false),
                                       (1, 2, 'MEDIUM', 'LONG_TERM', 2, 1, 'XYZ789', false),
                                       (2, 8, 'LARGE', 'LONG_TERM', 1, 3, 'DEF456',  false),
                                       (2, 5, 'SMALL', 'SHORT_TERM', 3, 2, 'GHI789', false);

