from functools import reduce;

def flatten(l) -> list:
    if l == []:
        return l
    if not isinstance(l, list):
        return [l]
    else:
        return flatten(l[0]) + flatten(l[1:])


def reverse(l) -> list:
    if l == []:
        return l
    if not isinstance(l[0], list):
        return  reverse(l[1:]) + [l[0]]
    else:
        return reverse(l[1:]) + [reverse(l[0])]


def compress(l: list) -> list:
    if len(l) < 2:
        return l
    if l[0] == l[1]:
        return compress(l[1:])
    else:
        return [l[0]] + compress(l[1:])


def capitalized(items: list) -> list:
    return list(filter(lambda i: i[0].isupper(), items))


def longest(strings: list, from_start=True) -> object:
    return reduce(lambda s1, s2: s1 if len(s1) >= len(s2) else s2, strings) if from_start else reduce(lambda s1, s2: s1 if len(s1) > len(s2) else s2, strings)


def composition(f1, f2):
    return lambda *args, **kwargs: f2(f1(*args, **kwargs))


def n_composition(*functions):
    return reduce(composition, *functions) if len(*functions) != 0 else None

