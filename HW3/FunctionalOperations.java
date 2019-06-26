import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionalOperations {
    private interface NamedBiFunction<T, U, R> extends BiFunction<T,U,R>{
        String name();
    }

    public static NamedBiFunction<Double, Double, Double> add = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "add";
        }

        @Override
        public Double apply(Double o, Double o2) {
            return o + o2;
        }
    };

    public static NamedBiFunction<Double, Double, Double> diff = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "diff";
        }

        @Override
        public Double apply(Double o, Double o2) {
            return o - o2;
        }
    };

    public static NamedBiFunction<Double, Double, Double> mult = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "mult";
        }

        @Override
        public Double apply(Double o, Double o2) {
            return o * o2;
        }
    };

    public static NamedBiFunction<Double, Double, Double> div = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {
            return "div";
        }

        @Override
        public Double apply(Double o, Double o2) throws ArithmeticException {
            if (o2.intValue() == 0) {
                throw new ArithmeticException();
            }
            return o / o2;
        }
    };

    public static <T> T zip(List<T> args, List<NamedBiFunction<T, T, T>> bifunctions){
        if(bifunctions.size() != args.size()-1){
            throw new IllegalArgumentException();
        }
        for(int i = 0; i < args.size()-1;i++){
            args.set(i+1, bifunctions.get(i).apply(args.get(i), args.get(i+1)));
        }
        return args.get(args.size()-1);
    }

    public static class FunctionComposition<T,U,R>{
        public BiFunction<Function<T, U>, Function<U, R>, Function<T, R>> composition = (f1, f2) -> f1.andThen(f2);
    }
}
