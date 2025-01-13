@Search @mobil
Feature:Search in mobilApp

  Background:
    Given click "Üye Olmadan Devam Et" button

     #Android-01
  Scenario: User should be able to search daily
    When search "Laptop"
    And click filter button