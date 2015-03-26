/*
 * @author Kyle Crossman
 * @file Property.js
 */

//An object that holds all the information about the properties.
function Property() {
    this.name;
    this.tax;
    this.house;
    this.cost;
    this.type;
}

//Sets the name.
Property.prototype.setName = function(name) {
    this.name = name;
};

//Sets the tax.
Property.prototype.setTax = function(tax) {
    this.tax = tax;
};

//Sets the house cost.
Property.prototype.setHouse = function(house) {
    this.house = house;
};

//Sets the cost of the property.
Property.prototype.setCost = function(cost) {
    this.cost = cost;
};

//Sets the type.
Property.prototype.setType = function(type) {
    this.type = type;
};

//Gets the name.
Property.prototype.getName = function() {
    return this.name;
};

//Gets the tax.
Property.prototype.getTax = function() {
    return this.tax;
};

//Gets the house cost.
Property.prototype.getHouse = function() {
    return this.house;
};

//Gets the cost of the property.
Property.prototype.getCost = function() {
    return this.cost;
};

//Gets the type;
Property.prototype.getType = function() {
    return this.type;
};

//Converts the property object into a json string.
Property.prototype.toJson = function() {
    var jsonObject = new Object();
    jsonObject.name = this.name;
    jsonObject.tax = this.tax;
    jsonObject.house = this.house;
    jsonObject.cost = this.cost;
    jsonObject.type = this.type;

    return JSON.stringify(jsonObject);
};