package org.statsenko.repository.system;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class CustomEntitySpecification<T> implements Specification<T> {

    protected abstract Specification<T> buildQuery();

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return buildQuery()/*.and(notDeleted())*/.toPredicate(root, criteriaQuery, criteriaBuilder);
    }

    protected Specification<T> all(List<Optional<Specification<T>>> specs) {
        return specs.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .reduce(Specification.where(null), Specification::and);
    }

    protected Specification<T> any(List<Optional<Specification<T>>> specs) {
        return specs.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .reduce(Specification.where(null), Specification::or);
    }

    private Specification<T> notDeleted() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("deleted"), false);
    }

    // Basic
    protected Optional<Specification<T>> isNull(SingularAttribute<T, ?> attr) {
        return Optional.of((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNull(root.get(attr)));
    }

    protected Optional<Specification<T>> isNotNull(SingularAttribute<T, ?> attr) {
        return Optional.of((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNotNull(root.get(attr)));
    }

    protected Optional<Specification<T>> equal(SingularAttribute<T, ?> attr, Object value) {
        return Optional.ofNullable(value)
                .map(it -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(attr), it));
    }

    protected Optional<Specification<T>> notEqual(SingularAttribute<T, ?> attr, Object value) {
        return Optional.ofNullable(value)
                .map(it -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.notEqual(root.get(attr), it));
    }

    // Strings
    protected Optional<Specification<T>> startsWith(SingularAttribute<T, String> attr, String value) {
        return Optional.ofNullable(value)
                .filter(it -> !it.isBlank())
                .map(it -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(attr), it + "%"));
    }

    protected Optional<Specification<T>> startsWithIgnoreCase(SingularAttribute<T, String> attr, String value) {
        return Optional.ofNullable(value)
                .filter(it -> !it.isBlank())
                .map(it -> (root, criteriaQuery, criteriaBuilder) ->
                        criteriaBuilder.like(criteriaBuilder.lower(root.get(attr)), (it).toLowerCase() + "%"));
    }

    protected Optional<Specification<T>> endsWith(SingularAttribute<T, String> attr, String value) {
        return Optional.ofNullable(value)
                .filter(it -> !it.isBlank())
                .map(it -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(attr), "%" + it));
    }

    protected Optional<Specification<T>> endsWithIgnoreCase(SingularAttribute<T, String> attr, String value) {
        return Optional.ofNullable(value)
                .filter(it -> !it.isBlank())
                .map(it -> (root, criteriaQuery, criteriaBuilder) ->
                        criteriaBuilder.like(criteriaBuilder.lower(root.get(attr)), "%" + it.toLowerCase()));
    }

    protected Optional<Specification<T>> contains(SingularAttribute<T, String> attr, String value) {
        return Optional.ofNullable(value)
                .filter(it -> !it.isBlank())
                .map(it -> (root, criteriaQuery, criteriaBuilder) ->
                        criteriaBuilder.like(root.get(attr), "%" + it + "%"));
    }

    protected Optional<Specification<T>> containsIgnoreCase(SingularAttribute<T, String> attr, String value) {
        return Optional.ofNullable(value)
                .filter(it -> !it.isBlank())
                .map(it -> (root, criteriaQuery, criteriaBuilder) ->
                        criteriaBuilder.like(criteriaBuilder.lower(root.get(attr)), "%" + it.toLowerCase() + "%"));
    }

    // Comparable

    protected <Y extends Comparable<? super Y>> Optional<Specification<T>> greater(SingularAttribute<T, Y> attr, Y value) {
        return Optional.ofNullable(value)
                .map(it -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(attr), it));
    }

    protected <Y extends Comparable<? super Y>> Optional<Specification<T>> greaterEquals(SingularAttribute<T, Y> attr, Y value) {
        return Optional.ofNullable(value)
                .map(it -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(attr), it));
    }

    protected <Y extends Comparable<? super Y>> Optional<Specification<T>> less(SingularAttribute<T, Y> attr, Y value) {
        return Optional.ofNullable(value)
                .map(it -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThan(root.get(attr), it));
    }

    protected <Y extends Comparable<? super Y>> Optional<Specification<T>> lessEquals(SingularAttribute<T, Y> attr, Y value) {
        return Optional.ofNullable(value)
                .map(it -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(attr), it));
    }

    // Collections

    protected Optional<Specification<T>> empty(SingularAttribute<T, ? extends Collection<?>> attr) {
        return Optional.of((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isEmpty(root.get(attr)));
    }

    protected Optional<Specification<T>> notEmpty(SingularAttribute<T, ? extends Collection<?>> attr) {
        return Optional.of((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNotEmpty(root.get(attr)));
    }

    protected <V> Optional<Specification<T>> inList(SingularAttribute<T, V> attr, Collection<V> list) {
        return Optional.ofNullable(list)
                .map(it -> (root, criteriaQuery, criteriaBuilder) -> root.get(attr).in(it));
    }

    protected <V> Optional<Specification<T>> notInList(SingularAttribute<T, V> attr, Collection<V> list) {
        return Optional.ofNullable(list)
                .map(it -> (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.not(root.get(attr).in(it)));
    }
}
