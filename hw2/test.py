import hw2


if __name__ == '__main__':
    # p = Person("Bob")
    # print(p)

    print("\n---Testing invalid Fighter creation---\n")

    print("-invalid age-")
    try:
        f = hw2.people.Fighter("Joseph", 17, 5, {"spear": 0, "unarmed_combat": 0, "mace": 0, "broadsword": 0})
    except ValueError as e:
        print(e)

    print("\n-invalid wealth-")
    try:
        f = hw2.people.Fighter("Joseph", 18, 0, {"spear": 0, "unarmed_combat": 0, "mace": 0, "broadsword": 0})
    except ValueError as e:
        print(e)

    print("\n-invalid skill level-")
    try:
        # f = Fighter("Joseph", 18, 10, {"spear": 15, "unarmed_combat": 0, "mace": 0, "broadsword": 0})
        f = hw2.people.Fighter("Joseph", 18, 10, {"spear": -1, "unarmed_combat": 0, "mace": 0, "broadsword": 0})
    except ValueError as e:
        print(e)

    print("\n-invalid number of skills-")
    try:
        f = hw2.people.Fighter("Joseph", 18, 10, {"unarmed_combat": 0, "mace": 0, "broadsword": 0})
    except ValueError as e:
        print(e)

    print("\n---Testing traveling and treasure---\n")

    ke1 = hw2.people.KnightErrant("Lisa", 25, 50, {"spear": 7, "unarmed_combat": 2, "mace": 5, "broadsword": 3})
    ke1.travel()
    ke1.travel()
    ke1.return_from_travel()
    print(ke1)
    ke1.return_from_travel()

    print("\n---Testing fighting between Fighters---\n")

    print("-fighting self-")
    f1 = hw2.people.Fighter("Fred", 45, 500, {"spear": 3, "unarmed_combat": 2, "mace": 2, "broadsword": 6})
    f1.challenge(f1, "spear")
    # f1_1 = hw2.people.Fighter("Fred", 46, 10, {"spear": 3, "unarmed_combat": 2, "mace": 2, "broadsword": 6})
    # f1.challenge(f1_1, "spear")

    print("\n-fighting with an invalid skill-")
    f2 = hw2.people.Fighter("Hammy", 30, 65, {"spear": 0, "unarmed_combat": 3, "mace": 6, "broadsword": 0})
    # inv_skill_fight = f2.challenge(f1, "fists")
    f2.challenge(f1, "spear mace")

    print("\n-fighting without enough money-")
    f3 = hw2.people.Fighter("Rosanna", 24, 2, {"spear": 0, "unarmed_combat": 7, "mace": 0, "broadsword": 1})
    f1.challenge(f3, "broadsword")
    print(f1)
    print(f3)
    f3.challenge(f1, "unarmed_combat")

    print("\n-fighting with equal skill levels-")
    f4 = hw2.people.Fighter("Boy", 18, 100, {"spear": 0, "unarmed_combat": 3, "mace": 6, "broadsword": 0})
    f2.challenge(f4, "spear")
    print(f2)
    print(f4)

    print("\n---Testing fighting between Fighter/Warrior/KnightErrant---\n")

    print("-Fighter vs Warrior-")
    fw = hw2.people.Fighter("Horatio", 50, 100, {"spear": 8, "unarmed_combat": 0, "mace": 0, "broadsword": 0})
    wf = hw2.people.Warrior("Jo", 19, 100, {"spear": 0, "unarmed_combat": 8, "mace": 0, "broadsword": 0})
    wf.challenge(fw, "unarmed_combat")
    print(fw)  # wealth should be 90
    print(wf)  # wealth should be 110
    fw.challenge(wf, "spear")
    wf.accept_first()
    print(fw)  # wealth should be 115
    print(wf)  # wealth should be 85

    print("\n-Fighter vs KnightErrant-")
    fk = hw2.people.Fighter("Lilith", 200, 100, {"spear": 0, "unarmed_combat": 0, "mace": 8, "broadsword": 0})
    kf = hw2.people.KnightErrant("Uriel", 300, 100, {"spear": 8, "unarmed_combat": 0, "mace": 0, "broadsword": 0})
    kf.challenge(fk, "spear")
    print(fk)  # wealth should be 90
    print(kf)  # wealth should be 110
    fk.challenge(kf, "mace")
    kf.accept_random()
    print(fk)  # wealth should be 130
    print(kf)  # wealth should be 70

    print("\n-Warrior vs KnightErrant-")
    wk = hw2.people.Warrior("Rudolph", 23, 100, {"spear": 0, "unarmed_combat": 0, "mace": 0, "broadsword": 8})
    kw = hw2.people.KnightErrant("Santa", 80, 100, {"spear": 0, "unarmed_combat": 8, "mace": 0, "broadsword": 0})
    kw.challenge(wk, "unarmed_combat")
    wk.accept_first()
    print(wk)  # wealth should be 90
    print(kw)  # wealth should be 110
    wk.challenge(kw, "broadsword")
    kw.accept_random()
    print(wk)  # wealth should be 110
    print(kw)  # wealth should be 90

    print("\n-Warrior vs Warrior-")
    ww1 = hw2.people.Warrior("Leif", 37, 100, {"spear": 0, "unarmed_combat": 0, "mace": 0, "broadsword": 8})
    ww2 = hw2.people.Warrior("Erik", 40, 100, {"spear": 0, "unarmed_combat": 0, "mace": 8, "broadsword": 0})
    ww2.challenge(ww1, "mace")
    ww1.accept_first()
    print(ww1)
    print(ww2)
    ww1.challenge(ww2, "broadsword")
    ww2.accept_random()
    print(ww1)
    print(ww2)

    print("\n-KnightErrant vs KnightErrant-")
    kk1 = hw2.people.KnightErrant("Leifer", 37, 100, {"spear": 0, "unarmed_combat": 0, "mace": 0, "broadsword": 8})
    kk2 = hw2.people.KnightErrant("Erikson", 40, 100, {"spear": 0, "unarmed_combat": 0, "mace": 8, "broadsword": 0})
    kk2.challenge(kk1, "mace")
    kk1.accept_first()
    print(kk1)
    print(kk2)
    kk1.challenge(kk2, "broadsword")
    kk2.accept_random()
    print(kk1)
    print(kk2)

    print("\n---Testing challenges list---\n")

    print("-accepting random request-")
    w1 = hw2.people.Warrior("Annie", 47, 100, {"spear": 5, "unarmed_combat": 6, "mace": 5, "broadsword": 7})
    w1.accept_random()
    f2.challenge(w1, "mace")
    f1.challenge(w1, "broadsword")
    # print(w1.challenges)
    w1.accept_random()
    # print(w1.challenges)

    print("\n-challenge list while traveling-")
    ke1.travel()
    f4.challenge(ke1, "spear")
    f2.challenge(ke1, "broadsword")
    w1.challenge(ke1, "mace")
    ke1.accept_first()
    ke1.decline_random()
    # print(ke1.challenges)
    ke1.return_from_travel()
    ke1.accept_random()
    # print(ke1.challenges)

    print("\n-identical\duplicate challenge-")
    f4.challenge(w1, "broadsword")
    f4.challenge(w1, "spear")
    f4.challenge(w1, "broadsword")
    # print(w1.challenges)
    w1.challenge(f4, "spear")
    # print([w1.challenges[i][0:2] for i in range(len(w1.challenges))])

    print("\n-withdrawing-")
    f4.withdraw(w1, "broadsword")
    f4.challenge(f1, "spear")
    f4.withdraw(w1, "mace")
    f4.withdraw(w1, "broadsword")
    # print(w1.challenges)
