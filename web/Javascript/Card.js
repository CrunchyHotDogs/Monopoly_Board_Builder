/*
 * @author Kyle Crossman
 * @file Card.js
 */

//A class to hold the chance cards and community chest cards.
function Card() {
    this.name;
    this.description;
    this.type;
}

//Sets the name.
Card.prototype.setName = function(name) {
    this.name = name;
};

//Sets the description.
Card.prototype.setDescription = function(description) {
    this.description = description;
};

//Sets the type.
Card.prototype.setType = function(type) {
    this.type = type;
};

//Gets the name.
Card.prototype.getName = function() {
    return this.name;
};

//Gets the description.
Card.prototype.getDescription = function() {
    return this.description;
};

//Gets the type.
Card.prototype.getType = function() {
    return this.type;
};

//Converts the card object into a json string.
Card.prototype.toJson = function() {
    var jsonObject = new Object();
    jsonObject.name = this.name;
    jsonObject.description = this.description;
    jsonObject.type = this.type;

    return JSON.stringify(jsonObject);
};