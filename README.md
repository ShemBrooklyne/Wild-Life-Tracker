# WildlifeTracker

### Shem Brooklyne

## Description :notebook:

  This is a simple java application that allows Rangers to track wildlife sightings in a given area and tracks wildlife categories such as all the animals and the endangered ones


 ### Here's the link to Live site
 - https://wanyamatracker.herokuapp.com/

### Prerequisites

 * An up-to-date browser, preferably chrome or mozilla
 * Internet connection


### Technologies used

   * JAVA
   * Gradle
   * Spark
   * Postgres SQL
   * HTML
   * CSS & Bootstrap
   * Junit

### Installation guide :notebook:

  If you want to use this as your template, here's how to go about it:

  * Install Git on you machine
  * Maneouver to 'clone or download' button and get the link
  * --Linux Users-- open your terminal and run the 'git clone ...' command in a directory of your choice
  * --for Windows Users-- Navigate to the location you'd want the repository located, right click and select "git bash here"
  * Clone the repository
  * Upon completion, navigate to the cloned repository
  Feel free to edit the files to your prefered taste

  * Now build the Database to enable storing of created instances
  * Install Postgres SQL
  * run the following commands in your terminal

        CREATE DATABASE wildlife_tracker;

        CREATE TABLE animals (id serial PRIMARY KEY, name varchar);

        CREATE TABLE sightings (id serial PRIMARY KEY, animal_id serial location varchar, ranger_name varchar, animal_type varchar, sighting timestamp);

        CREATE TABLE endangered_animals(id serial, name varchar, health varchar, age varchar)

        CREATE DATABASE wildlife_tracker_test;

         CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;


### LICENSE

Copyright 2020 Shem Brooklyne Mwangi

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

