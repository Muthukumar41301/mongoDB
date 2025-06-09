# MongoDB Command Cheatsheet

## Basic Commands

- `show dbs`  
  Show all databases.

- `use admin`  
  Connect to the `admin` database.

- `db.createCollection("students")`  
  Create a new collection named `students`.

- `db.dropDatabase()`  
  Drop the current database.

## Inserting Data

- `db.students.insertOne({name: "name1", age: 30, gpa: 3.2})`  
  Insert a single document.

- `db.students.insertMany([{...}, {...}, {...}])`  
  Insert multiple documents.

## Querying Data

- `db.students.find()`  
  Retrieve all documents.

- `db.students.find({}, {name: true})`  
  Retrieve only the `name` field from all documents.  
  Format: `.find(query, projection)`

- `db.students.find().sort({name: 1})`  
  Sort documents by `name` in ascending order. (`1` for ASC, `-1` for DESC)

- `db.students.find().sort({name: -1}).limit(1)`  
  Retrieve the document with the highest `name` value.

- `db.students.find().limit(1)`  
  Limit the output to 1 document.

## Updating Data

- `db.students.updateOne({name: "name1"}, {$set: {name: "name2"}})`  
  Update a single document where `name` is `"name1"`.

## Deleting Data

- `db.students.deleteMany({name: "name1"})`  
  Delete all documents where `name` is `"name1"`.

## Comparison Operators

- `db.students.find({name: {$ne: "name"}})`  
  Find documents where `name` is not equal to `"name"`.

- `db.students.find({age: {$lt: 20}})`  
  Find documents where `age` is less than `20`.

- `db.students.find({age: {$gt: 20}})`  
  Find documents where `age` is greater than `20`.

- `db.students.find({age: {$lte: 20}})`  
  Less than or equal to `20`.

- `db.students.find({age: {$gte: 20}})`  
  Greater than or equal to `20`.

## Logical Operators

- `db.students.find({name: {$in: ["name", "name1"]}})`  
  `name` in specified array.

- `db.students.find({name: {$nin: ["name", "name1"]}})`  
  `name` not in specified array.

- `db.students.find({$and: [{name: "name1"}, {age: {$lte: 22}}]})`  
  Match both conditions.

- `db.students.find({$or: [{name: "name1"}, {age: {$lte: 22}}]})`  
  Match either condition.

## Indexes

- `db.students.getIndexes()`  
  View all indexes on the `students` collection.
