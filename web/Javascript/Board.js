/*
 * @author Kyle Crossman
 * @file Property.js
 */

//An object to hold all of the info about the board.
function Board() {
    this.name;
    this.url;
}

//Sets the board name.
Board.prototype.setName = function(name) {
    this.name = name;
};

//Sets the image url.
Board.prototype.setUrl = function(url) {
    this.url = url;
};

//Gets the board name.
Board.prototype.getName = function() {
    return this.name;
};

//Gets the image url.
Board.prototype.getUrl = function() {
    return this.url;
};

//Converts the board object into a json string and returns it.
Board.prototype.toJson = function() {
    var jsonObject = new Object();
    jsonObject.name = this.name;
    jsonObject.url = this.url;

    return JSON.stringify(jsonObject);
};