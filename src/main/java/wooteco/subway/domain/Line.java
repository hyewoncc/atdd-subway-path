package wooteco.subway.domain;

import java.util.List;
import java.util.Objects;

public class Line {

    private Long id;
    private String name;
    private String color;
    private ExtraFare extraFare;
    private Sections sections;

    private Line() {
    }

    public Line(String name, String color, int extraFare) {
        validateNameNotEmpty(name);
        validateColorNotEmpty(color);
        this.name = name;
        this.color = color;
        this.extraFare = new ExtraFare(extraFare);
    }

    public Line(String name, String color, Sections sections, int extraFare) {
        this(name, color, extraFare);
        this.sections = sections;
    }

    public Line(Long id, String name, String color, Sections sections, int extraFare) {
        this(name, color, sections, extraFare);
        this.id = id;
    }

    public List<Station> getStations() {
        return sections.getStations();
    }

    public void add(Section section) {
        sections.add(section);
    }

    public void delete(Station station) {
        sections.delete(station);
    }

    private void validateNameNotEmpty(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 비워둘 수 없습니다.");
        }
    }

    private void validateColorNotEmpty(String color) {
        if (color.isBlank()) {
            throw new IllegalArgumentException("색상은 비워둘 수 없습니다.");
        }
    }

    public void updateNameAndColor(String name, String color) {
        validateNameNotEmpty(name);
        validateColorNotEmpty(color);
        this.name = name;
        this.color = color;
    }

    public List<Section> getSections() {
        return sections.getSections();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getExtraFare() {
        return extraFare.getExtraFare();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Line line = (Line) o;
        return Objects.equals(name, line.name) && Objects.equals(color, line.color)
                && Objects.equals(sections, line.sections) && Objects.equals(extraFare, line.extraFare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color, sections, extraFare);
    }

    private static class ExtraFare {

        private int extraFare;

        public ExtraFare(int extraFare) {
            validateNotUnderZero(extraFare);
            this.extraFare = extraFare;
        }

        private void validateNotUnderZero(int extraFare) {
            if (extraFare < 0) {
                throw new IllegalArgumentException("추가 요금은 음수가 될 수 없습니다.");
            }
        }

        public int getExtraFare() {
            return extraFare;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ExtraFare extraFare1 = (ExtraFare) o;
            return extraFare == extraFare1.extraFare;
        }

        @Override
        public int hashCode() {
            return Objects.hash(extraFare);
        }
    }
}
