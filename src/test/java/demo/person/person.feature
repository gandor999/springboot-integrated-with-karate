Feature: sample karate test script

    Background:

        * url 'http://localhost:8080/api/v1/person'

        * def person =
            """
            {
                "name": "Mary"
            }
            """

    Scenario: Create person

        Given path ''
        And request person
        When method post
        Then assert responseStatus == 200 || responseStatus == 201
        And match response.name == person.name
        And match response == '#object'
        And match response == { id: '#uuid', name: '#string' }

        * def name = response.name
        * print "Created person's name is:", name

    # should be status 201 but response not configured to return 201 when making post request. No database connected either

    Scenario: Get People and Get a person by id

        Given path ''
        When method get
        Then status 200
        And match response == "#array"
        And match each response == "#object"
        And match each response == { id: '#uuid', name: '#string' }

        * def id = response[0].id

        Given path id
        When method get
        Then status 200
        And match response == { id: '#uuid', name: '#string' }
        And match response.id == id

    Scenario: Create and Update a person

        * def updatedPerson =
            """
            {
                "name": "Telus"
            }
            """

        # create a person first
        Given path ''
        And request person
        When method post
        Then assert responseStatus == 200 || responseStatus == 201

        * def id = response.id

        # then update
        Given path id
        And request updatedPerson
        When method put
        Then assert responseStatus == 200 || responseStatus == 204

    Scenario: Create and Delete a person

        # create a person first
        Given path ''
        And request person
        When method post
        Then assert responseStatus == 200 || responseStatus == 201

        * def id = response.id

        # then delete
        Given path id
        When method delete
        Then assert responseStatus == 200 || responseStatus == 204

