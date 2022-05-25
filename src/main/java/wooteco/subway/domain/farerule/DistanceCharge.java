package wooteco.subway.domain.farerule;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public enum DistanceCharge implements Predicate<Integer> {

    NONE(
            distance -> distance > 0 && distance <= 10,
            distance -> 0
    ),
    PER_5KM(
            distance -> distance > 10 && distance <= 50,
            DistanceCharge::calculateHundredPer5km
    ),
    PER_8KM(
            distance -> distance > 50,
            DistanceCharge::calculateHundredPer8km
    );

    private final Predicate<Integer> predicate;
    private final Function<Integer, Integer> calculateCharge;

    DistanceCharge(Predicate<Integer> predicate, Function<Integer, Integer> calculateCharge) {
        this.predicate = predicate;
        this.calculateCharge = calculateCharge;
    }

    public int calculate(int distance) {
        return this.calculateCharge.apply(distance);
    }

    public static int calculateDistanceCharge(int distance) {
        return Arrays.stream(DistanceCharge.values())
                .filter(type -> type.test(distance))
                .findFirst()
                .map(distanceCharge -> distanceCharge.calculate(distance))
                .orElseThrow(() -> new IllegalArgumentException("거리는 음수가 될 수 없습니다."));
    }

    @Override
    public boolean test(Integer integer) {
        return predicate.test(integer);
    }

    private static int calculateHundredPer5km(int distance) {
        return (int) (Math.ceil((distance - 10) / 5) * 100);
    }

    private static int calculateHundredPer8km(int distance) {
        return calculateHundredPer5km(50) + (int) (Math.ceil((distance - 50) / 8) * 100);
    }
}
