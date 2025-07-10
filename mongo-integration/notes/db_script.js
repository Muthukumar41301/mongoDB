
// MongoDB database script
// To execute this script, run: mongo < db_script.js

// Select the database to use.
db = db.getSiblingDB('testdb');

// Drop existing collections
db.roles.drop();
db.users.drop();
db.subjects.drop();
db.student.drop();
db.posts.drop();
db.upload_files.drop();

// Create collections
db.createCollection("roles");
db.createCollection("users");
db.createCollection("subjects");
db.createCollection("student");
db.createCollection("posts");
db.createCollection("upload_files");

// Insert sample data for Roles
db.roles.insertMany([
  { "name": "ROLE_USER" },
  { "name": "ROLE_MODERATOR" },
  { "name": "ROLE_ADMIN" }
]);

// Get role ids
const roleUser = db.roles.findOne({ "name": "ROLE_USER" });
const roleModerator = db.roles.findOne({ "name": "ROLE_MODERATOR" });
const roleAdmin = db.roles.findOne({ "name": "ROLE_ADMIN" });

// Insert sample data for Users
db.users.insertMany([
  {
    "username": "testuser",
    "email": "testuser@example.com",
    "password": "password123",
    "roles": [roleUser._id]
  },
  {
    "username": "moduser",
    "email": "moduser@example.com",
    "password": "password123",
    "roles": [roleModerator._id]
  },
  {
    "username": "adminuser",
    "email": "adminuser@example.com",
    "password": "password123",
    "roles": [roleAdmin._id]
  }
]);

// Insert sample data for Subjects
db.subjects.insertMany([
  { "name": "Mathematics" },
  { "name": "Physics" },
  { "name": "Chemistry" }
]);

// Get subject ids
const math = db.subjects.findOne({ "name": "Mathematics" });
const physics = db.subjects.findOne({ "name": "Physics" });
const chemistry = db.subjects.findOne({ "name": "Chemistry" });

// Insert sample data for Student
db.student.insertMany([
  {
    "name": "John Doe",
    "cgpa": 8.5,
    "has_arrears": false,
    "address": {
      "street": "123 Main St",
      "city": "Anytown",
      "country": "USA"
    },
    "enrollment_date": new ISODate("2023-01-15T00:00:00Z"),
    "subjects": [math._id, physics._id]
  },
  {
    "name": "Jane Smith",
    "cgpa": 9.2,
    "has_arrears": false,
    "address": {
      "street": "456 Oak Ave",
      "city": "Somecity",
      "country": "USA"
    },
    "enrollment_date": new ISODate("2023-02-20T00:00:00Z"),
    "subjects": [chemistry._id]
  }
]);

// Insert sample data for Post
db.posts.insertMany([
    {
        "title": "My first post",
        "content": "This is my first post on this platform."
    },
    {
        "title": "Another post",
        "content": "This is another post."
    }
]);

print("Database script executed successfully.");
