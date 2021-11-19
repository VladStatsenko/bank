package org.statsenko.repository.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.statsenko.entity.Client;
import org.statsenko.entity.Client_;
import org.statsenko.filter.EntityFilter;
import org.statsenko.repository.system.CustomEntitySpecification;

import java.util.List;

@RequiredArgsConstructor
public class EntitySpecification extends CustomEntitySpecification<Client> {

    private final EntityFilter filter;

    @Override
    protected Specification<Client> buildQuery() {
        return all(List.of(
                containsIgnoreCase(Client_.firstName, filter.getFirstName()),
                containsIgnoreCase(Client_.lastName, filter.getLastName()),
                equal(Client_.lastName, filter.getLastName()),
                containsIgnoreCase(Client_.midName, filter.getMidName()),
                lessEquals(Client_.birthDate, filter.getBirthDate()),
                equal(Client_.tin, filter.getTin())
        ));
    }

}
