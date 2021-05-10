package com.automobile.autodealer;

public class autosControllerTests {
    // Schema:
    // int year
    // String make
    // string model
    // String color
    // String owner
    // String vin
    // GET: /autos
    // responses
    // 200 - return a list of all autos (if any exist) as a JSON object has an automobiles key whose value is an array of all autos.
    // 204 - no autos exist
    // GET: /autos/{vin}
    // 200 - return an auto by its vin
    // 204 - not found
    //POST: /autos
    // request body - object same as schema
    // response
    // 200 - automobile added successfully
    // 400 - bad request
    //PATCH: /autos/{vin}
    // request body: only update owner and color
    // response
    // 200 - automobile added successfully
    // 400 - bad request
    //DELETE: /autos/{vin}
    // response
    // 202 - automobile delete request accepted
    // 204 - Vehicle not found
}
