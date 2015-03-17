/*
 * @author Kyle Crossman
 * @file Property.js
 */

function Board() {
    this.name;
    this.url;
}

Board.prototype.setName = function(name) {
    this.name = name;
};

Board.prototype.setUrl = function(url) {
    this.url = url;
};

Board.prototype.getName = function() {
    return this.name;
};

Board.prototype.getUrl = function() {
    return this.url;
};

Board.prototype.toJson = function() {
    var jsonObject = new Object();
    jsonObject.name = this.name;
    jsonObject.url = this.url;

    return JSON.stringify(jsonObject);
};