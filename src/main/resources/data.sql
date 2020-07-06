INSERT INTO users (name, email, password, enabled)
  values ('brasilprev',
    'admin@brasilprev.com',
    '123456',
    true);
 
INSERT INTO authorities (email, authority)
  values ('admin@brasilprev.com', 'ROLE_USER');

INSERT INTO users (name, email, password, enabled)
  values ('bruno',
    'bruno.ahcor@gmail.com',
    '123456',
    true);
 
INSERT INTO authorities (email, authority)
  values ('bruno.ahcor@gmail.com', 'ROLE_USER');