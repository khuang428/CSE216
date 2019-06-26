import hw2
import random


class Person:
    def __init__(self, name: str, age: int = random.randint(0, 17), wealth: int = 0):
        self.name = name
        if age < 0:
            self.age = 0
        else:
            self.age = age
        if age < 18:
            self.isAdult = False
        else:
            self.isAdult = True
        self.wealth = wealth
        if wealth < 0:
            self.wealth = 0

    def adult_check(self) -> bool:
        """Returns whether this person is an adult or not"""
        return self.isAdult

    def __eq__(self, p):
        """Returns whether or not a person is equal to this one"""
        return p.__str__() == self.__str__()

    def __str__(self):
        """Returns a nicely formatted line showing name, age, and wealth"""
        return self.name + "  Age:" + str(self.age) + "  Wealth:" + str(self.wealth)


class Fighter(Person):
    def __init__(self, name: str, age: int, wealth: int,
                 skills = {"spear": 0, "unarmed_combat": 0, "mace": 0, "broadsword": 0}):
        if wealth < 1 or age < 18:
            raise ValueError(name + " is not qualified to be a fighter.")
        if len(skills) != 4:
            raise ValueError(name + " does not have the required number of skills.")
        for key, val in skills.items():
            if val < 0 or val > 10 or key not in ["spear", "unarmed_combat", "mace", "broadsword"]:
                raise ValueError(name + " does not have a valid skillset.")
        Person.__init__(self, name, age, wealth)
        self.__skills = skills  # skills cannot be accessed by other fighters
        self.fights = 0

    def __str__(self):
        """Returns the Person str with skills added in"""
        return Person.__str__(self) + "  Spear:" + str(self.__skills.get("spear")) \
                                    + "  Unarmed Combat:" + str(self.__skills.get("unarmed_combat")) \
                                    + "  Mace:" + str(self.__skills.get("mace")) \
                                    + "  Broadsword:" + str(self.__skills.get("broadsword"))

    def skill_up(self, skill: str, status: int) -> None:
        """
        Adds skill after a fight depending on who was just fought
        skill: the skill to level up
        status: 0 Fighter win/lose, Warrior lose, KnightErrant lose (chance to level up)
                1 Warrior win (guaranteed 1 level up)
                2 KnightErrant win (guaranteed 2 level ups)
        """
        if status == 0:
            self.__skills[skill] += random.randint(0, 1)
        elif status == 1:
            self.__skills[skill] += 1
        elif status == 2:
            self.__skills[skill] += 2

    def skill_level(self, skill: str) -> int:
        """Returns the skill level of a given skill"""
        return self.__skills[skill]

    def challenge(self, opponent, skill: str) -> None:
        """
        Sends a challenge to an opponent to start a fight
        opponent: a Fighter to challenge
        skill: the skill to use in the fight
        """
        if isinstance(opponent, Warrior):
            if [self, skill] not in [opponent.challenges[i][0:2] for i in range(len(opponent.challenges))]:
                opponent.challenges.append([self, skill, self.fights])
            else:
                print(self.name + " already sent " + opponent.name + " a challenge for " + skill + ".")
        else:
            try:
                fight = hw2.fight.Fight(self, opponent, skill)
                winner = fight.winner()
                fight.gain(winner)
            except Exception as e:
                print(e)

    def withdraw(self, opponent, skill: str) -> None:
        """
        Withdraws from a challenge sent to a Warrior
        opponent: the Warrior to withdraw a challenge from
        skill: the skill used for that challenge
        """
        for challenge in opponent.challenges:
            if challenge[0] == self and challenge[1] == skill and challenge[2] != self.fights:
                opponent.challenges.remove(challenge)
                print(self.name + " withdrew a challenge to " + opponent.name + " for " + skill + ".")
                return
        print(self.name + " cannot withdraw from this fight.")


class Warrior(Fighter):
    def __init__(self, name: str, age: int, wealth: int, skills: dict):
        Fighter.__init__(self, name, age, wealth, skills)
        self.__skills = skills
        self.challenges = []

    def skill_up(self, skill: str, status: int) -> None:
        """
        Adds skill after a fight depending on who was just fought
        skill: the skill to level up
        status: 0 Fighter win, Warrior win/lose, KnightErrant lose (chance to level up)
                1 KnightErrant win (guaranteed 1 level up)
        """
        if status == 0:
            self.__skills[skill] += random.randint(0, 1)
        elif status == 1:
            self.__skills[skill] += 1

    def accept_random(self) -> None:
        """Accepts a random challenge from the list of challenges"""
        if len(self.challenges) == 0:
            print(self.name + " has no challenges to accept.")
        else:
            index = random.randrange(0, len(self.challenges))
            challenge = self.challenges[index]
            self.challenges.pop(index)
            try:
                fight = hw2.fight.Fight(self, challenge[0], challenge[1])
                winner = fight.winner()
                fight.gain(winner)
            except Exception as e:
                print(e)

    def decline_random(self) -> None:
        """Declines a random challenge from the list of challenges"""
        if len(self.challenges) == 0:
            print(self.name + " has no challenges to decline.")
        else:
            self.challenges.pop(random.randrange(0, len(self.challenges)))

    def accept_first(self) -> None:
        """Accepts the first challenge in the list of challenges"""
        if len(self.challenges) == 0:
            print(self.name + " has no challenges to accept.")
        else:
            challenge = self.challenges[0]
            self.challenges.pop(0)
            try:
                fight = hw2.fight.Fight(self, challenge[0], challenge[1])
                winner = fight.winner()
                fight.gain(winner)
            except Exception as e:
                print(e)

    def decline_first(self) -> None:
        """Declines the first challenge in the list of challenges"""
        if len(self.challenges) == 0:
            print(self.name + " has no challenges to decline.")
        else:
            self.challenges.pop(0)

    def challenge(self, opponent, skill: str) -> None:
        """
        Sends a challenge to an opponent to start a fight unless the same challenge is already in the list
        opponent: a Fighter to challenge
        skill: the skill to use in the fight
        """
        if [opponent, skill] in [self.challenges[i][0:2] for i in range(len(self.challenges))]:
            print(self.name + " already has a challenge for " + skill + " from " + opponent.name + ".")
        else:
            Fighter.challenge(self, opponent, skill)


class KnightErrant(Warrior):
    def __init__(self, name: str, age: int, wealth: int, skills: dict):
        Warrior.__init__(self, name, age, wealth, skills)
        self.__skills = skills
        self.traveling = False

    def travel(self) -> None:
        """Sets the traveling status to true"""
        if not self.traveling:
            self.traveling = True
            print(self.name + " is going on an adventure.")
        else:
            print(self.name + " is already traveling.")

    def return_from_travel(self) -> None:
        """Sets the traveling status to false and adds some wealth if treasure is found"""
        if self.traveling:
            self.traveling = False
            print(self.name + " has returned.")
            if random.randrange(0, 100) > 50:
                print(self.name + " found some treasure!")
                self.wealth += random.randint(1, 100)
        else:
            print(self.name + " isn't traveling right now.")

    def skill_up(self, skill: str, status: int = 0) -> None:
        """
        Has a chance to add a level to the given skill
        skill: the skill to level up
        status: 0 Fighter win, Warrior win, KnightErrant win/lose (chance to level up)
        """
        if status == 0:
            self.__skills[skill] += random.randint(0, 1)

    def accept_random(self) -> None:
        """Accepts a random challenge in the list of challenges unless traveling"""
        if self.traveling:
            print(self.name + " cannot accept challenges while traveling.")
        else:
            Warrior.accept_random(self)

    def decline_random(self) -> None:
        """Declines a random challenge in the list of challenges unless traveling"""
        if self.traveling:
            print(self.name + " cannot decline challenges while traveling.")
        else:
            Warrior.decline_random(self)

    def accept_first(self) -> None:
        """Accepts the first challenge in the list of challenges unless traveling"""
        if self.traveling:
            print(self.name + " cannot accept challenges while traveling.")
        else:
            Warrior.accept_first(self)

    def decline_first(self) -> None:
        """Declines the first challenge in the list of challenges unless traveling"""
        if self.traveling:
            print(self.name + " cannot declines challenges while traveling.")
        else:
            Warrior.decline_first(self)

    def challenge(self, opponent, skill: str) -> None:
        """
        Sends a challenge to an opponent to start a fight unless traveling
        opponent: a Fighter to challenge
        skill: the skill to use in the fight
        """
        if self.traveling:
            print(self.name + " cannot challenge others while traveling.")
        else:
            Warrior.challenge(self, opponent, skill)
