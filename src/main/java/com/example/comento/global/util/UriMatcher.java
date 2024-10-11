package com.example.comento.global.util;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriTemplate;

import java.util.Collection;

@RequiredArgsConstructor
public class UriMatcher {
    private final HttpMethod httpMethod;
    private final String path;

    public boolean matchWithExclusion(HttpServletRequest request, Collection<String> excludePatterns){
        if(excludePatterns.stream().anyMatch(i -> i.equals(request.getRequestURI()))) return false;
        UriTemplate uriTemplate = new UriTemplate(path);
        return httpMethod.matches(request.getMethod()) && uriTemplate.matches(request.getRequestURI());
    }

    public boolean match(HttpServletRequest request){
        UriTemplate uriTemplate = new UriTemplate(path);
        return httpMethod.matches(request.getMethod()) && uriTemplate.matches(request.getRequestURI());
    }
}
