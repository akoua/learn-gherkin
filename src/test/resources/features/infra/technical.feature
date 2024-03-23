Feature: Process to verify all observability of application

  Background:
    * a root logger set to DEBUG
    * status is "UP"

  Scenario: verify that application is UP
    When the api management calls "/actuator/health"
    Then it receives a status OK_200 and a Map:
    """yml
    status: {{status}}
    groups:
    - custom
    """