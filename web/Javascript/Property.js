/*
 * @author Kyle Crossman
 * @file Property.js
 */

function Property() {
    this.name;
    this.tax;
    this.house;
    this.cost;
    this.type;
}

Property.prototype.setName = function(name) {
    this.name = name;
};

Property.prototype.setTax = function(tax) {
    this.tax = tax;
};

Property.prototype.setHouse = function(house) {
    this.house = house;
};

Property.prototype.setCost = function(cost) {
    this.cost = cost;
};

Property.prototype.setType = function(type) {
    this.type = type;
};


Property.prototype.getName = function() {
    return this.name;
};

Property.prototype.getTax = function() {
    return this.tax;
};

Property.prototype.getHouse = function() {
    return this.house;
};

Property.prototype.getCost = function() {
    return this.cost;
};

Property.prototype.getType = function() {
    return this.type;
};


Property.prototype.toJson = function() {
    var jsonObject = new Object();
    jsonObject.name = this.name;
    jsonObject.tax = this.tax;
    jsonObject.house = this.house;
    jsonObject.cost = this.cost;
    jsonObject.type = this.type;

    return JSON.stringify(jsonObject);
};