package com.minsx.starter.test.jpa;


import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class SingleSpecification<T> implements Specification<T> {

    private Query query;

    public SingleSpecification(Query query) {
        this.query = query;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = null;
        switch (query.getOperator()) {
            case "equal":
                predicate = criteriaBuilder.equal(root.get(query.getName()).as(String.class), query.getValue());
                break;
            case "notEqual":
                predicate = criteriaBuilder.notEqual(root.get(query.getName()).as(String.class), query.getValue());
                break;
            case "isEmpty":
                predicate = criteriaBuilder.isEmpty(root.get(query.getName()));
                break;
            case "isNotEmpty":
                predicate = criteriaBuilder.isNotEmpty(root.get(query.getName()));
                break;
            case "isTrue":
                predicate = criteriaBuilder.isTrue(root.get(query.getName()).as(Boolean.class));
                break;
            case "isFalse":
                predicate = criteriaBuilder.isFalse(root.get(query.getName()).as(Boolean.class));
                break;
            case "like":
                predicate = criteriaBuilder.like(root.get(query.getName()).as(String.class), "%" + query.getValue() + "%");
                break;
            case "notLike":
                predicate = criteriaBuilder.notLike(root.get(query.getName()).as(String.class), "%" + query.getValue() + "%");
                break;
            default:
                predicate = criteriaBuilder.like(root.get(query.getName()).as(String.class), "%" + query.getValue() + "%");
                break;
        }
        criteriaQuery.where(predicate);
        return criteriaQuery.getRestriction();
    }
}
