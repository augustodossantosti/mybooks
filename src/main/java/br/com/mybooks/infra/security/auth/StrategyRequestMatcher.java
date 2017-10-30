/*
 * mybooks 1.0 12 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * A classe <code>StrategyRequestMatcher</code> é responsável por 
 * definir as URIs que serão ignoradas e as que serão interceptadas 
 * pelo filtro <code>JwtAuthenticationFilter</code>.
 * 
 * @author Augusto dos Santos
 * @version 1.0 16 de jan de 2017
 */
public class StrategyRequestMatcher implements RequestMatcher {
	
	private List<RequestMatcher> skipMatchers = new ArrayList<>();
    private List<RequestMatcher> processingMatcher = new ArrayList<>();
    
    public StrategyRequestMatcher(final String pathPatternToSkip, final String pathPatternToProcess) {
        skipMatchers.add(new AntPathRequestMatcher(pathPatternToSkip));
        processingMatcher.add(new AntPathRequestMatcher(pathPatternToProcess));
    }
    
    public StrategyRequestMatcher(final List<String> pathsToSkip, final List<String> pathsToProcess) {
        final List<RequestMatcher> skipMatchersList = pathsToSkip.stream()
						.map(path -> new AntPathRequestMatcher(path))
						.collect(Collectors.toList());
        
        final List<RequestMatcher> processMatchersList = pathsToProcess.stream()
				.map(path -> new AntPathRequestMatcher(path))
				.collect(Collectors.toList());
        
        skipMatchers.addAll(skipMatchersList);
        processingMatcher.addAll(processMatchersList);
    }
    
    public StrategyRequestMatcher(final Map<String, HttpMethod> pathsToSkip, final Map<String, HttpMethod> pathsToProcess) {
    	final List<RequestMatcher> skipMatchersList = new ArrayList<>();
    	final List<RequestMatcher> processMatchersList = new ArrayList<>();
		
    	pathsToSkip.forEach((path, httpMethod) -> {
    		skipMatchersList.add(new AntPathRequestMatcher(path, httpMethod.name()));
    	});
    	
    	pathsToProcess.forEach((path, httpMethod) -> {
    		processMatchersList.add(new AntPathRequestMatcher(path, httpMethod.name()));
    	});
    	
        skipMatchers.addAll(skipMatchersList);
        processingMatcher.addAll(processMatchersList);
	}
    
    public StrategyRequestMatcher() {};
    
    public void addPathToSkip(final String path, final HttpMethod httpMethod, final boolean caseSensituve) {
		skipMatchers.add(new AntPathRequestMatcher(path, httpMethod.name(), caseSensituve));
    }
    
    public void addPathsToProcess(final String path, final HttpMethod httpMethod, final boolean caseSensituve) {
    	processingMatcher.add(new AntPathRequestMatcher(path, httpMethod.name(), caseSensituve));
    }

    /**
     * Verifica se o path fornecido deve ou não ser processado pelo filtro do
     * Spring Security. Caso o path verificado não conste na lista de paths a
     * serem processados e nem na lista de paths a serem ignorados pelo filtro
     * será assumido, por padrão, que o path não deverá ser processado.
     */
    @Override
    public boolean matches(final HttpServletRequest request) {
    	for(final RequestMatcher matcher : skipMatchers) {
    		if (matcher.matches(request)) {
                return false;
            }
    	}

    	for(final RequestMatcher matcher : processingMatcher) {
    		if (matcher.matches(request)) {
                return true;
            }
    	}
    	
    	return false;
    }

}
