import hw2
import random


class Fight:
    def __init__(self, challenger, acceptor, skill: str):
        if challenger == acceptor:
            raise ValueError("A fighter cannot fight themselves.")
        if challenger.wealth < 1:
            raise ValueError(challenger.name + " does not have enough money to fight.")
        if acceptor.wealth < 1:
            raise ValueError(acceptor.name + " does not have enough money to fight.")
        if skill not in ["spear", "unarmed_combat", "mace", "broadsword"]:
            raise ValueError("A valid skill must be used in a fight.")
        self.challenger = challenger
        self.acceptor = acceptor
        self.skill = skill
        self.pot = 0

    def money_pot(self, status: int = 0) -> None:
        """
        Determines how much money is in the pot based on the types of fighters
        status: 0 if same type
                1 if KnightErrant and Warrior
                2 if KnightErrant and Fighter
                3 if Warrior and Fighter
        """
        # equal standing 0
        if status == 0:
            self.challenger.wealth -= 10
            self.acceptor.wealth -= 10
            self.pot += 20
        # ke war 1
        elif status == 1:
            if isinstance(self.challenger, hw2.people.KnightErrant):
                self.challenger.wealth -= 20
                self.acceptor.wealth -= 10
                self.pot += 30
            else:
                self.challenger.wealth -= 10
                self.acceptor.wealth -= 20
                self.pot += 30
        # ke f 2
        elif status == 2:
            if isinstance(self.challenger, hw2.people.KnightErrant):
                self.challenger.wealth -= 40
                self.acceptor.wealth -= 10
                self.pot += 50
            else:
                self.challenger.wealth -= 10
                self.acceptor.wealth -= 40
                self.pot += 50
        # war f 3
        elif status == 3:
            if isinstance(self.challenger, hw2.people.Warrior):
                self.challenger.wealth -= 25
                self.acceptor.wealth -= 10
                self.pot += 35
            else:
                self.challenger.wealth -= 10
                self.acceptor.wealth -= 25
                self.pot += 35
        # make sure that wealth is never negative
        if self.challenger.wealth < 0:
            self.challenger.wealth = 0
        if self.acceptor.wealth < 0:
            self.acceptor.wealth = 0

    def ke_winner(self, winner) -> None:
        """The fight method used if there is at least one KnightErrant"""
        # if both ke
        if isinstance(self.challenger, hw2.people.KnightErrant) and isinstance(self.acceptor, hw2.people.KnightErrant):
            self.eq_winner(winner)
        # if one ke one warrior
        elif isinstance(self.challenger, hw2.people.Warrior) and isinstance(self.acceptor, hw2.people.Warrior):
            self.money_pot(1)
            # getting which skill_up status to use
            if isinstance(self.challenger, hw2.people.KnightErrant):
                c_win = 0
                c_lose = 1
                a_win = 1
                a_lose = 0
            else:
                c_win = 1
                c_lose = 0
                a_win = 0
                a_lose = 1
            winner.wealth += self.pot
            if winner == self.challenger:
                self.challenger.skill_up(self.skill, c_win)
                self.acceptor.skill_up(self.skill, a_lose)
            else:
                self.challenger.skill_up(self.skill, c_lose)
                self.acceptor.skill_up(self.skill, a_win)
        # if one ke one fighter
        else:
            self.money_pot(2)
            if isinstance(self.challenger, hw2.people.KnightErrant):
                c_win = 0
                c_lose = 1
                a_win = 2
                a_lose = 0
            else:
                c_win = 2
                c_lose = 0
                a_win = 0
                a_lose = 1
            winner.wealth += self.pot
            if winner == self.challenger:
                self.challenger.skill_up(self.skill, c_win)
                self.acceptor.skill_up(self.skill, a_lose)
            else:
                self.challenger.skill_up(self.skill, c_lose)
                self.acceptor.skill_up(self.skill, a_win)

    def war_winner(self, winner) -> None:
        """The gain helper method used if there is at least one Warrior and no KnightErrant"""
        # if both warrior
        if isinstance(self.challenger, hw2.people.Warrior) and isinstance(self.acceptor, hw2.people.Warrior):
            self.eq_winner(winner)
        # if one warrior one fighter
        else:
            self.money_pot(3)
            if isinstance(self.challenger, hw2.people.Warrior):
                c_win = 0
                c_lose = 2
                a_win = 1
                a_lose = 0
            else:
                c_win = 1
                c_lose = 0
                a_win = 0
                a_lose = 2
            winner.wealth += self.pot
            if winner == self.challenger:
                self.challenger.skill_up(self.skill, c_win)
                self.acceptor.skill_up(self.skill, a_lose)
            else:
                self.challenger.skill_up(self.skill, c_lose)
                self.acceptor.skill_up(self.skill, a_win)

    def eq_winner(self, winner) -> None:
        """The gain helper method used if the Fighters are of equal standing"""
        self.money_pot()
        winner.wealth += self.pot
        self.challenger.skill_up(self.skill, 0)  # always have a chance to level up skill if fighting on equal level
        self.acceptor.skill_up(self.skill, 0)

    def winner(self) -> hw2.people.Fighter:
        """
        Determines the winner of the fight by checking the skill levels of the fighters
        :return the Fighter that won
        """
        print(self.challenger.name + " is fighting " + self.acceptor.name + " with " + self.skill + ".")
        self.challenger.fights += 1
        self.acceptor.fights += 1
        c_level = self.challenger.skill_level(self.skill)
        a_level = self.acceptor.skill_level(self.skill)
        # equal skill level
        if c_level == a_level:
            winner = random.choice([self.challenger, self.acceptor])
        # clear winner
        elif c_level > a_level:
            winner = self.challenger
        else:
            winner = self.acceptor
        print(winner.name + " won the fight!")
        return winner

    def gain(self, winner):
        """Deals with all the wealth and skill gain depending on the type of Fighters"""
        if isinstance(self.challenger, hw2.people.KnightErrant) or isinstance(self.acceptor, hw2.people.KnightErrant):
            self.ke_winner(winner)
        elif isinstance(self.challenger, hw2.people.Warrior) or isinstance(self.acceptor, hw2.people.Warrior):
            self.war_winner(winner)
        else:
            self.eq_winner(winner)
