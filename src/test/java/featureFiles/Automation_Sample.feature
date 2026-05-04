@E2E_Flow
  #Automate Sample Application
  Feature: E2E flow of a basic application
    Scenario Outline: Search Google and launch the first application
      Given the user able to navigate to Google and provide data for "<TestCase>","<ProductName>"

      Examples:
        | TestCase | ProductName     |  |
        | TC_01    | Google_Testdata |  |