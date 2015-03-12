/*
 * @author Kyle Crossman
 * @file Card.js
 */

function Card() {
    this.name;
    this.description;
    this.type;
}

Card.prototype.setName = function(name) {
    this.name = name;
};

Card.prototype.setDescription = function(description) {
    this.description = description;
};

Card.prototype.setType = function(type) {
    this.type = type;
};

Card.prototype.getName = function() {
    return this.name;
};

Card.prototype.getDescription = function() {
    return this.description;
};

Card.prototype.getType = function() {
    return this.type;
};

Card.prototype.toJson = function() {
    var jsonObject = new Object();
    jsonObject.name = this.name;
    jsonObject.description = this.description;
    jsonObject.type = this.type;

    return JSON.stringify(jsonObject);
};