import java.util.Optional;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FunctionalUtils{
    public static Collection<String> capitalized(Collection<String> strings){
        return strings.stream().filter(s-> Character.isUpperCase(s.charAt(0))).collect(Collectors.toList());
    }

    public static String longest(Collection<String> strings, boolean from_start){
        return strings.size() == 0 ? null : from_start ? strings.stream().reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2).orElse(null) : strings.stream().reduce((s1, s2) -> s1.length() > s2.length() ? s1 : s2).orElse(null);
    }

    public static <T extends Comparable<T>> T least(Collection<T> items, boolean from_start){
        return items.size() == 0 ? null : from_start ? items.stream().reduce((s1, s2)-> s1.compareTo(s2) <= 0 ? s1 : s2).orElse(null) : items.stream().reduce((s1, s2)-> s1.compareTo(s2) < 0 ? s1 : s2).orElse(null);
    }

    public static <K, V> List<String> flatten(Map<K, V> aMap){
        return aMap.entrySet().stream().map(s -> s.getKey() + " -> " + s.getValue()).collect(Collectors.toList());
    }

}