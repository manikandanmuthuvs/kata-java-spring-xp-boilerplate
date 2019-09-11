Feature: Add movies in video store

    User wants to add movies in video store

    @AddMovie
    Scenario: Add movie in VideoStore
        When User request to add a movie in video store
        Then the movie should be added
