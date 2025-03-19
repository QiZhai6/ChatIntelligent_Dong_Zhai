Feature: Gestion des Chats Intelligents

  En tant qu'utilisateur
  Je veux que mon chat puisse apprendre des mots et avoir une maison
  Afin qu'il puisse interagir avec moi et avoir un foyer

  Scenario Outline: Un chat apprend un nouveau mot
    Given un chat appelé "<nom>" âgé de <âge> ans
    When il apprend <nombre_de_mots> mot(s)
    Then il doit connaître <nombre_de_mots> mot(s)

    Examples:
      | nom   | âge | mot     | nombre_de_mots |
      | Felix | 3   | bonjour | 1             |
      | Luna  | 2   | beau    | 1             |
      | Milo  | 5   | salut   | 1             |

  Scenario Outline: Un chat rejoint une maison
    Given un chat appelé "<nom>" âgé de <âge> ans
    And une maison située à "<adresse>"
    When "<nom>" rejoint cette maison
    Then la maison doit contenir 1 chat
    And "<nom>" doit habiter à "<adresse>"

    Examples:
      | nom   | âge | adresse                 |
      | Felix | 3   | 123 Rue des Chats       |
      | Luna  | 4   | 456 Avenue des Félins   |
      | Milo  | 2   | 789 Boulevard des Ronrons |

  Scenario Outline: Un chat change de maison
    Given un chat appelé "<nom>" âgé de <âge> ans
    And une maison située à "<ancienne_maison>"
    And une autre maison située à "<nouvelle_maison>"
    When "<nom>" rejoint la maison "<ancienne_maison>"
    Then la maison "<ancienne_maison>" doit contenir 1 chat

    When "<nom>" rejoint la maison "<nouvelle_maison>"
    Then la maison "<ancienne_maison>" ne doit plus contenir de chat
    And la maison "<nouvelle_maison>" doit contenir 1 chat
    And "<nom>" doit habiter à "<nouvelle_maison>"

    Examples:
      | nom   | âge | ancienne_maison       | nouvelle_maison        |
      | Felix | 3   | 123 Rue des Chats     | 456 Avenue des Félins  |
      | Luna  | 4   | 456 Avenue des Félins | 789 Boulevard des Ronrons |
