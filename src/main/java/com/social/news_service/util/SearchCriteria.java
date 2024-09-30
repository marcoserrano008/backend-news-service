package com.social.news_service.util;

import java.util.HashMap;
import java.util.Map;

public class SearchCriteria {

    private Map<String, Object> criteria = new HashMap<>();

    public void addCriteria(String key, Object value) {
        criteria.put(key, value);
    }

    public Object getCriteria(String key) {
        return criteria.get(key);
    }

    public boolean hasCriteria(String key) {
        return criteria.containsKey(key) && criteria.get(key) != null && !criteria.get(key).toString().isEmpty();
    }

    public Map<String, Object> getAllCriteria() {
        return criteria;
    }
}
