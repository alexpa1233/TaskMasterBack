-- Crear la tabla "User"
CREATE TABLE "User" (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,

);

-- Crear la tabla "Task"
CREATE TABLE "Task" (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description TEXT,
  due_date DATE,
  Type VARCHAR(20),
  user_id INT REFERENCES "User" (id) ON DELETE CASCADE
);

-- Crear la tabla "Notification"
CREATE TABLE "Notification" (
  id SERIAL PRIMARY KEY,
  task_id INT REFERENCES "Task" (id) ON DELETE CASCADE,
  notification_date TIMESTAMPTZ,
  recurring_interval VARCHAR(20),
  active BOOLEAN,

);
